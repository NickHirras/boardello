/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardello.service;

import boardello.http.EntityManagerUtil;
import boardello.http.UserService;
import boardello.model.Account;
import boardello.model.Board;
import boardello.model.Card;
import boardello.model.Checklist;
import boardello.model.ChecklistItem;
import boardello.model.Deck;
import boardello.model.Label;
import boardello.service.dto.CardContents;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nicholas
 */
@Path("cards")
public class CardService {

  @Context
  HttpServletRequest request;
  @Context
  HttpServletResponse response;

  @GET
  @Path("{cardId}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public CardContents get(@PathParam("cardId") Long cardId) {
    Account currentUser = UserService.getCurrentUser(request.getSession());

    if (currentUser == null) {
      throw new NotAuthorizedException(response);
    }

    EntityManager em = EntityManagerUtil.getEntityManager();
    CardContents dat = new CardContents();
    dat.setCard(em.find(Card.class, cardId));
    dat.setDeck(em.find(Deck.class, dat.getCard().getDeckId()));
    dat.setBoard(em.find(Board.class, dat.getDeck().getBoardId()));
    dat.setLabels(em.createQuery("select label from Label label where label.boardId = :boardId", Label.class)
            .setParameter("boardId", dat.getBoard().getId()).getResultList());
    dat.setChecklists(em.createQuery("select checklist from Checklist checklist where checklist.cardId = :cardId", Checklist.class)
            .setParameter("cardId", dat.getCard().getId()).getResultList());
    dat.setChecklistItems(em.createQuery("select item from ChecklistItem item where item.checklistId in (select checklist.id from Checklist checklist where checklist.cardId = :cardId)",
            ChecklistItem.class)
            .setParameter("cardId", dat.getCard().getId()).getResultList());
    return dat;
  }

  @POST
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Card create(Card card) {

    Account currentUser = UserService.getCurrentUser(request.getSession());

    if (currentUser == null) {
      throw new NotAuthorizedException(response);
    }

    EntityManager em = EntityManagerUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    // Make sure deck we're asked to attach to belongs to logged-in user.
    Deck deck = em.find(Deck.class, card.getDeckId());
    Board board = em.find(Board.class, deck.getBoardId());

    if (board.getAccountId() != currentUser.getId()) {
      throw new NotAllowedException("User not allowed to add a card to the requested deck.");
    }

    // Now find out what position we should initialize this to
    long nextPosition;
    try {
      nextPosition = em.createQuery(
              "select max(c.position)+1 from Card c where c.deckId = :deckId", Long.class)
              .setParameter("deckId", deck.getId())
              .getSingleResult();
    } catch (Exception e) {
      nextPosition = 1L;
    }

    // Wrap if necessary
    if (nextPosition == Long.MAX_VALUE) {
      nextPosition = Long.MIN_VALUE;
    }

    card.setPosition(nextPosition);
    card.setDeckId(deck.getId());
    card.setLastActivityAt(new Date());
    card.setSlug(ContentUtils.createSlug(card.getDescription()));

    card.setName(ContentUtils.createTitle(card.getDescription()));

    try {
      tx.begin();
      em.persist(card);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw e;
    }

    return card;
  }
}
