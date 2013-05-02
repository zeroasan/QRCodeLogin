package com.apusic.login.service;


/**
 * 
 * 服务器端传回给客户端的response为
 * serverResp = encrypt(username:[ip:]expired, serverPublic)
 *
 */
public class StoredToken {
	
	/** 用户名. */
	private String username;
	
	/** 失效时间. */
	private String expired; 
	
	/** 客户端计算的结果. */
	private String response;
	
	
	public StoredToken(String username, String expired) {
		this.username = username;
		this.expired = expired;
	}
	
	public StoredToken(String response) {
		this.response = response;
	}
	
}
