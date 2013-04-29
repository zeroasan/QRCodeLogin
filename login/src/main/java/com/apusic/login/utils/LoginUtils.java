package com.apusic.login.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apusic.login.LoginContext;
import com.apusic.login.service.LoginContextManager;
import com.apusic.login.service.ServiceFactory;


public class LoginUtils {
	
	public static String LOGIN_PAGE = "login.jsp";

	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginContextManager contextManager = ServiceFactory.createService(LoginContextManager.class, true);
		HttpSession session = request.getSession(true);
		String contextSN = (String)session.getAttribute("contextSerialNumber");
		LoginContext loginContext = null;
		if(contextSN == null) {
			redirectToLoginPage(request, response);
			return false;
		} else {
			loginContext = contextManager.getLoginContext(contextSN);
			if(loginContext.isTimeout() || !loginContext.isAuthenticated()) {
				redirectToLoginPage(request, response);
				return false;
			}
		}
		return true;
	}
	
	public static void redirectToLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
}
