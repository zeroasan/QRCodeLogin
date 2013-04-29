package com.apusic.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apusic.login.LoginContext;
import com.apusic.login.LoginStatusEnum;
import com.apusic.login.service.LoginContextManager;
import com.apusic.login.service.ServiceFactory;


public class CheckStatusServlet extends HttpServlet {

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
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String contextSN = (String)session.getAttribute("contextSerialNumber");
		
		
		LoginStatusEnum status = null;
		if(contextSN == null) {
			status = LoginStatusEnum.CONTEXT_EMPTY;
		} else {
			LoginContext loginContext = contextManager.getLoginContext(contextSN);
			if(loginContext.isTimeout()) {
				status = LoginStatusEnum.CONTEXT_TIME_OUT;
			} else if(loginContext.isAuthenticated()) {
				status = LoginStatusEnum.LOGINED;
			} else {
				status = LoginStatusEnum.NOT_LOGINED;
			}
		}
		response.getWriter().println(getStatusResult(status));
		
	}
	
	private String getStatusResult(LoginStatusEnum e) {
		return String.format("{\"status\": %d}", e.getStatusCode());
	}
	
}
