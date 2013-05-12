<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.apusic.login.*"%>
<%@ page import="com.apusic.login.service.*"%>
<%@ page import="com.apusic.login.utils.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	session.removeAttribute("contextSerialNumber");
	response.sendRedirect(request.getContextPath() + "/");
%>

