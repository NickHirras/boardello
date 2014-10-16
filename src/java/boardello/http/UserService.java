/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardello.http;

import boardello.model.Account;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nicholas.e.smith
 */
public class UserService {

  public static String SESSION_KEY = "WHOAMI";

  public static Account getCurrentUser(HttpSession session) {
    if (session == null) {
      return null;
    }
    return (Account) session.getAttribute(SESSION_KEY);
  }

  public static void setCurrentUser(HttpSession session, Account user) {
    session.setAttribute(SESSION_KEY, user);
  }

}
