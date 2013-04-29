package com.apusic.login.service.impl;

import com.apusic.login.LoginContext;
import com.apusic.login.service.LoginContextManager;
import com.apusic.login.service.SerialNumberGenerator;
import com.apusic.login.service.Storage;

public class SimpleLoginContextManager implements LoginContextManager {

	private Storage<String, LoginContext> storage = new SimpleStorage<String, LoginContext>();
	
	public LoginContext createLoginContext() {
		LoginContext loginContext = new LoginContext();
		
		loginContext.setSerialNumber(SerialNumberGenerator.gen());
		loginContext.setDate(System.currentTimeMillis());
		
		storage.store(loginContext.getSerialNumber(), loginContext);
		return loginContext;
	}

	public LoginContext getLoginContext(String serialNumber) {
		return storage.retrive(serialNumber);
	}
	
	

}
