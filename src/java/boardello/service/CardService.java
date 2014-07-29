/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.service;

import boardello.http.EntityManagerUtil;
import boardello.http.UserService;
import boardello.model.Account;
import boardello.model.Card;
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
@Path("cards")
public class CardService {
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
 
    @GET
    @Path("{cardId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Card get(@PathParam("cardId") Long cardId) {
        Account currentUser = UserService.getCurrentUser(request.getSession());
        
        if(currentUser == null) {
            throw new NotAuthorizedException(response);
        }
 
        EntityManager em = EntityManagerUtil.getEntityManager();
        return em.find(Card.class, cardId);
        
    }
    
    
}
