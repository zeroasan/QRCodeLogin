<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.apusic.login.*"%>
<%@ page import="com.apusic.login.service.*"%>

<jsp:include page="prelogin.jsp"></jsp:include>

<!DOCTYPE html>

<html lang="cn_zh">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<style type="text/css">
	body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

	.form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
</style>


<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function checkLoginStatus() {
		try {console.log("check login status."); } catch(e){};

		$.ajax({
			url : "<%=request.getContextPath() %>/status",
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				try { console.log("status=" + data.status); } catch(e){};
				if (data.status == 0) {
					//relocate to welcome page
					window.location = "<%=request.getContextPath() %>" + "/";
				} else {
					//check again after 3 seconds
					setTimeout(checkLoginStatus, 3000);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				try { console.log(textStatus); } catch(e){};
				setTimeout(checkLoginStatus, 3000);
			},
			cache : false
		});
	}
	
	function login() {
		try { console.log("login action"); } catch(e){};
		
		var username = $("input[name=username]").val();
		var password = $("input[name=password]").val();
		
		
		$.ajax({
			url : "<%=request.getContextPath() %>/login",
			type: "POST",
			data : {"username": username, "password": password},
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				try { console.log("result=" + data.result); } catch(e){};
				if (data.result == "successful") {
					//relocate to welcome page
					window.location = "<%=request.getContextPath() %>" + "/";
				} else {
					$("#message").text("User Name or password is wrong.");
					$("#message").css("visibility", "");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				try { console.log(textStatus); } catch(e){};
			},
			cache : false
		});
	}

	$(function() {
		checkLoginStatus();
	});
</script>
</head>
<body>
	<div class="container">
		<form class="form-signin">
			<div id="message" style="visibility: hidden; color: red;"></div>
			<h2 class="form-signin-heading">Please sign in</h2>
			<input name="username" type="text" class="input-block-level" placeholder="Email address"> 
			<input name="password" type="password" class="input-block-level" placeholder="Password"> 
			<input type="button" class="btn btn-large btn-primary" value="Sign in" onclick="login();">
		</form>
		<div>
			<img alt="sn qr image" src="<%=request.getContextPath() %>/qr">
			<h2>
				Login Context Serial Number is <span id="sn"><%=(String) session.getAttribute("contextSerialNumber")%></span>
			</h2>
		</div>
		<div>
			<a href="https://github.com/zeroasan/QRCodeLogin/raw/master/bin/20130512/QRCodeLogin4A.apk">下载Android应用</a>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
