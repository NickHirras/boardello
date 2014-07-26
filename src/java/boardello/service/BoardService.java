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
            
    
//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Board> list() {
//        
//        Account currentUser = UserService.getCurrentUser(request.getSession());
//        
//        if(currentUser == null) {
//            throw new NotAuthorizedException(response);
//        }
//        
//        EntityManager em = EntityManagerUtil.getEntityManager();
//        return em.createQuery(
//                "select b from Board b where b.account = :account", 
//                Board.class)
//                .setParameter("account", currentUser)
//                .getResultList();
//    
//    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Board> list() {
        
        Account currentUser = UserService.getCurrentUser(request.getSession());
        
        if(currentUser == null) {
            throw new NotAuthorizedException(response);
        }
        
        EntityManager em = EntityManagerUtil.getEntityManager();
        return em.createQuery(
                "select new boardello.model.Board(b.id, b.name, b.slug, b.lastActivityAt, b.lastViewedAt) from Board as b where b.account = :account", 
                Board.class)
                .setParameter("account", currentUser)
                .getResultList();
        
        
    
    }

    @GET
    @Path("{boardId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Board get(@PathParam("boardId") Long boardId) {
        Account currentUser = UserService.getCurrentUser(request.getSession());
        
        if(currentUser == null) {
            throw new NotAuthorizedException(response);
        }
 
        EntityManager em = EntityManagerUtil.getEntityManager();
        return em.find(Board.class, boardId);
        
    }
    
}
