package com.apusic.login.service;


/**
 * 
 * �������˴��ظ��ͻ��˵�responseΪ
 * serverResp = encrypt(username:[ip:]expired, serverPublic)
 *
 */
public class StoredToken {
	
	/** �û���. */
	private String username;
	
	/** ʧЧʱ��. */
	private String expired; 
	
	/** �ͻ��˼���Ľ��. */
	private String response;
	
	
	public StoredToken(String username, String expired) {
		this.username = username;
		this.expired = expired;
	}
	
	public StoredToken(String response) {
		this.response = response;
	}
	
}
