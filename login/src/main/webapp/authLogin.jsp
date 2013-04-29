<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.apusic.login.*"%>
<%@ page import="com.apusic.login.service.*"%>
<%@ page import="com.apusic.login.utils.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if(!LoginUtils.checkLogin(request, response))
		return;
	LoginContextManager contextManager = ServiceFactory.createService(LoginContextManager.class, true);
	String contextSN = (String)session.getAttribute("contextSerialNumber");
	
	LoginContext loginContext = contextManager.getLoginContext(contextSN);
%>

<html>
	<body>
		<h2><span style="color: red;">${message}</span></h2>
		<h2>Hello, <%=loginContext.getPrincipalName() %></h2>
		<h2>You can clone another session from current session.</h2>
		<form action="authz">
			Other LoginContext SN: <input type="text" size="12" name="sn"></br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>