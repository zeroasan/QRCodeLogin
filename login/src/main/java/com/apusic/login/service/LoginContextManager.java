package com.apusic.login.service;

import com.apusic.login.LoginContext;

public interface LoginContextManager {

	LoginContext createLoginContext();

	LoginContext getLoginContext(String serialNumber);
	
}
