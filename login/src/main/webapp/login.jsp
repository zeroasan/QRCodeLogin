<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.apusic.login.*"%>
<%@ page import="com.apusic.login.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="prelogin.jsp"></jsp:include>

<html>
	<body>
		<h2>Hello World! Your login Context Serial Number is <span id="sn"><%=(String)session.getAttribute("contextSerialNumber") %></span></h2>
		<form action="login" method="post">
			Username: <input type="text" size="12" name="username"></br>
			Password: <input type="password" size="12" name="password"></br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
