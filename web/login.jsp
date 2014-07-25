<%@page import="javax.persistence.NoResultException"%>
<%@page import="boardello.http.UserService"%>
<%@page import="boardello.model.Account"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="boardello.http.EntityManagerUtil"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="boardello.crypto.HashUtil"%>
<!DOCTYPE html>
<%
    String error = "";
    
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String returnTo = request.getParameter("returnTo");
    
    if(returnTo == null || returnTo.isEmpty()) {
        returnTo = request.getContextPath();
    }
    
    if(email != null && password != null) {
        email = email.toLowerCase();
        String passwordDigest = HashUtil.sha512(password.getBytes(), email.getBytes());
        // Attempt to login user.       
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();       
            Account account = em.createQuery(
                    "select a from Account a where a.email = :email and a.passwordDigest = :passwordDigest", 
                    Account.class)
                    .setParameter("email", email)
                    .setParameter("passwordDigest", passwordDigest)
                    .getSingleResult();
            UserService.setCurrentUser(session, account);
            response.sendRedirect(returnTo);            
        } catch(NoResultException ex) {
            error = "Incorrect email or password.";
        }
    }
%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/lib/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
      <script src="<%=request.getContextPath()%>/lib/html5shiv-3.7.0/html5shiv.js"></script>
      <script src="<%=request.getContextPath()%>/lib/respond.js-1.4.2/respond.min.js"></script>
    <![endif]-->

 	<link href="<%=request.getContextPath()%>/login.css" rel="stylesheet">

  </head>  
  <body>

	<div class="container">
            <% if(error != null && error.length() > 0) { %>
                <div class="alert alert-danger">
                    <%= error %>
                </div>
            <% } %>
	  <form class="form-signin" role="form" method="POST">
	    <h2 class="form-signin-heading">Please sign in</h2>
	    <input name="email" type="email" value="<%= email == null ? "" : email %>" class="form-control" placeholder="Email address" required autofocus>
	    <input name="password" type="password" value="<%= password == null ? "" : password %>" class="form-control" placeholder="Password" required>
	    <button name="signIn" value="signIn" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	  </form>
	
	</div> <!-- /container -->    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=request.getContextPath()%>/lib/jquery-1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath()%>/lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
  </body>
</html>