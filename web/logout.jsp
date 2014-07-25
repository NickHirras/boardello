<%@page import="boardello.http.UserService"%>
<% 

	UserService.setCurrentUser(session, null);
	session.invalidate();
	response.sendRedirect(request.getContextPath());

%>