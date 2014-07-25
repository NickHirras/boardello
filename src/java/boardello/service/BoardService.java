/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.service;

import boardello.http.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 *
 * @author nicholas.e.smith
 */

@Path("boards")
public class BoardService {
    
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
            
    
    @GET
    public String test() {
        
        if(UserService.getCurrentUser(request.getSession()) == null) {
            throw new NotAuthorizedException(response);
        }
        
        return "Hello!";
    }
    
}
