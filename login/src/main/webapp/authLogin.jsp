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
	<head>
		<script type="text/javascript" src="/login/js/jquery-1.9.1.js"></script>
		<script type="text/javascript">
			function grant() {
				
				var sn = $("input[name=sn]").val();
				
				$.ajax({
					url : "/login/authz",
					dataType : "json",
					data : {"sn": sn},
					success : function(data, textStatus, jqXHR) {
						console.log("result=" + data.result);
						if (data.result == "successful") {
							$("#message").text("Successful.");
							$("#message").css("visibility", "");
						} else {
							$("#message").text("Failed.");
							$("#message").css("visibility", "");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
						setTimeout(checkLoginStatus, 3000);
					},
					cache : false
				});
			}
		</script>
	</head>
	<body>
		<h2><span id="message" style="visibility: hidden; color: red;"></span></h2>
		<h2>Hello, <%=loginContext.getPrincipalName() %></h2>
		<h2>You can clone another session from current session.</h2>
			Other LoginContext SN: <input type="text" size="12" name="sn"></br>
			<input type="button" onclick="grant();" value="Grant">
	</body>
</html>