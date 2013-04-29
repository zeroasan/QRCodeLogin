package com.apusic.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apusic.login.LoginContext;
import com.apusic.login.service.LoginContextManager;
import com.apusic.login.service.ServiceFactory;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private LoginContextManager contextManager;

	@Override
	public void init() throws ServletException {
		super.init();
		contextManager = ServiceFactory.createService(LoginContextManager.class, true);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
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
		
		request.setAttribute("loginContext", loginContext);
		if(loginContext.isAuthenticated()) {
			toNormalPage(request, response);
		} else {
			if(attemptToLogin(request, response)) {
				toNormalPage(request, response);
			} else {
				toLoginPage(request, response);
			}
		}
		
	}

	private boolean attemptToLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if("123456".equals(password)) {
			LoginContext context = ((LoginContext)request.getAttribute("loginContext"));
			context.setAuthenticated(true);
			context.setPrincipalName(username == null ? "[empty]" : username);
			return true;
		}
		return false;
	}

	private void toLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	private void toNormalPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/authLogin.jsp").forward(request, response);
	}

}
