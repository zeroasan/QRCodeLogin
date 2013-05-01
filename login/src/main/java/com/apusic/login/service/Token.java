package com.apusic.login.service;

import java.util.Date;


/**
 * 对用户名，用用户密码的MD5之后的密钥加密，然后再使用服务器自己的密钥加时间戳、随机数加密。
 * 这样Token就只能被服务器所识别，还能验证用户的身份。
 * 
 * HA = MD5(username:password)
 * HA1 = MD5(username)
 * response = MD5(HA1:nonce:date)
 *
 */
public class Token {
	
	private String username;
	
	private String nonce;
	
	private String response;

	private String date; 
	

}
