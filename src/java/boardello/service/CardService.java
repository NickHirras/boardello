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
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
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
    
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
            
    @GET
    @Path("{cardId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CardContents get(@PathParam("cardId") Long cardId) {
        Account currentUser = UserService.getCurrentUser(request.getSession());
        
        if(currentUser == null) {
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
}
 