package com.apusic.login.service;

public interface AuthenticationService {

	boolean authSimple(String username, String password);
	
	boolean auth();

	boolean authToken(AuthnToken token);
	
}
