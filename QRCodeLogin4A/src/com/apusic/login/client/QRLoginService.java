package com.apusic.login.client;

public interface QRLoginService {
	
	/**
	 * 返回Cookie
	 * @param username
	 * @param password
	 * @return
	 */
	boolean grant(String serialNumber);

	void logout();

	void refreshSession() throws Exception;

}
