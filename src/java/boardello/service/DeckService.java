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
import boardello.model.Deck;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nicholas.e.smith
 */
@Path("decks")
public class DeckService {

  @Context
  HttpServletRequest request;
  @Context
  HttpServletResponse response;

  @POST
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Deck create(Deck deck) {

    Account currentUser = UserService.getCurrentUser(request.getSession());

    if (currentUser == null) {
      throw new NotAuthorizedException(response);
    }

    EntityManager em = EntityManagerUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    // Make sure board we're asked to attach to belongs to logged-in user.
    Board board = em.find(Board.class, deck.getBoardId());

    if (board.getAccountId() != currentUser.getId()) {
      throw new NotAllowedException("User not allowed to add a deck to the requested board.");
    }

    // Now find out what position we should initialize this to
    long nextPosition;
    try {
      nextPosition = em.createQuery(
              "select max(d.position)+1 from Deck d where d.boardId = :boardId", Long.class)
              .setParameter("boardId", board.getId())
              .getSingleResult();
    } catch (Exception e) {
      nextPosition = 1L;
    }

    // Wrap if necessary
    if (nextPosition == Long.MAX_VALUE) {
      nextPosition = Long.MIN_VALUE;
    }

    deck.setPosition(nextPosition);

    try {
      tx.begin();
      em.persist(deck);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      throw e;
    }

    return deck;
  }

}
