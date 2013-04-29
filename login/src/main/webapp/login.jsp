<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.apusic.login.*"%>
<%@ page import="com.apusic.login.service.*"%>
<jsp:include page="prelogin.jsp"></jsp:include>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<script type="text/javascript" src="/login/js/jquery-1.9.1.js"></script>
		<script type="text/javascript">
			function checkLoginStatus() {
				console.log("check login status.");
				
				$.ajax({
					url: "/login/status",
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log("status=" + data.status);
						if(data.status == 0) {
							//relocate to welcome page
							window.location = "/login/";
						} else {
							//check again after 3 seconds
							setTimeout(checkLoginStatus, 3000);
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
						setTimeout(checkLoginStatus, 3000);
					},
					cache: false
				});
			}
			
			$(function(){
				checkLoginStatus();
			});
		</script>
	</head>
	<body>
		<h2>Hello World! Your login Context Serial Number is <span id="sn"><%=(String)session.getAttribute("contextSerialNumber") %></span></h2>
		<form action="login" method="post">
			Username: <input type="text" size="12" name="username"></br>
			Password: <input type="password" size="12" name="password"></br>
			<input type="submit" value="Submit">
		</form>
		
		
		<div>
			<img alt="sn qr image" src="/login/qr">
		</div>
	</body>
</html>
