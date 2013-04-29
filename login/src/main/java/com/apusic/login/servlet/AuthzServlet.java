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

public class AuthzServlet extends HttpServlet {

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
		//validate user has logined already.
		//Get the current principal name
		HttpSession session = request.getSession(true);
		String contextSN = (String)session.getAttribute("contextSerialNumber");
		LoginContext currentContext = contextManager.getLoginContext(contextSN);
		
		if(currentContext == null || !currentContext.isAuthenticated()) {
			toLoginPage(request, response);
			return;
		}
		
		//get the loginContext that you want to auth login
		String sn = request.getParameter("sn");
		LoginContext otherLoginContext = contextManager.getLoginContext(sn);
		otherLoginContext.setAuthenticated(true);
		otherLoginContext.setPrincipalName(currentContext.getPrincipalName());
		
		request.setAttribute("message", "Successful");
		request.getRequestDispatcher("authLogin.jsp").forward(request, response);
		
	}
	
	private void toLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
