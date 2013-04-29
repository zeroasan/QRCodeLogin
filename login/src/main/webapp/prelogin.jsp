<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.apusic.login.LoginContext"%>
<%@ page import="com.apusic.login.service.*"%>
<%
	LoginContextManager contextManager = ServiceFactory.createService(LoginContextManager.class, true);
	String contextSN = (String)session.getAttribute("contextSerialNumber");
	LoginContext loginContext = null;
	if(contextSN == null) {
		loginContext = contextManager.createLoginContext();
	} else {
		loginContext = contextManager.getLoginContext(contextSN);
		if(loginContext.isTimeout()) {
			loginContext = contextManager.createLoginContext();
		}
	}
	session.setAttribute("contextSerialNumber", loginContext.getSerialNumber());
%>
