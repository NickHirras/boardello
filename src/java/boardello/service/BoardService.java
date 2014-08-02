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
import boardello.model.Deck;
import boardello.model.Label;
import boardello.service.dto.BoardContents;
import java.util.List;
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
 * @author nicholas.e.smith
 */

@Path("boards")
public class BoardService {
    
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
            
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Board> list() {
        
        Account currentUser = UserService.getCurrentUser(request.getSession());
        
        if(currentUser == null) {
            throw new NotAuthorizedException(response);
        }
        
        EntityManager em = EntityManagerUtil.getEntityManager();
        return em.createQuery(
                "select b from Board b where b.accountId = :accountId", 
                Board.class)
                .setParameter("accountId", currentUser.getId())
                .getResultList();
    
    }


    @GET
    @Path("{boardId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public BoardContents get(@PathParam("boardId") Long boardId) {
        Account currentUser = UserService.getCurrentUser(request.getSession());
        
        if(currentUser == null) {
            throw new NotAuthorizedException(response);
        }
 
        EntityManager em = EntityManagerUtil.getEntityManager();
        
        BoardContents data = new BoardContents();
        
        data.setAccount(em.find(Account.class, currentUser.getId()));
        data.setBoard(em.find(Board.class, boardId));
        data.setLabels(em.createQuery("select label from Label label where label.boardId = :boardId", Label.class)                   
                        .setParameter("boardId", data.getBoard().getId()).getResultList());
        data.setDecks(em.createQuery("select deck from Deck deck where deck.boardId = :boardId", Deck.class)
                        .setParameter("boardId", data.getBoard().getId()).getResultList());
        for(Deck deck : data.getDecks()) {
            List<Card> cards = em.createQuery("select card from Card card where card.deckId = :deckId", Card.class)
                .setParameter("deckId", deck.getId()).getResultList();
                    
            if(data.getCards() == null) {
                data.setCards(cards);
            } else {
                data.getCards().addAll(cards);
            }            
        }
        return data;
        
    }
    
}
