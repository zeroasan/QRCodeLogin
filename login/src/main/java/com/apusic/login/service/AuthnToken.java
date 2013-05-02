package com.apusic.login.service;



/**
 * 对用户名，用用户密码的MD5之后的密钥加密，然后再使用服务器自己的密钥加失效时间、随机数加密。
 * 这样Token就只能被服务器所识别，还能验证用户的身份。
 * 
 * HA = MD5(username:password)
 * HA1 = MD5(uri)
 * response = MD5(HA1:nonce:expired)
 *
 *
 *
 * 
 * 未来加入用户设备唯一编号或者IP等，当IP信息变动时，则Token验证将失败。
 * 
 * 
 * AuthnToken客户端提交给服务器的认证令牌.
 */
public class AuthnToken {
	
	/** 用户名. */
	private String username;
	
	/** 随机数. */
	private String nonce;
	
	/** 失效时间. */
	private String expired; 
	
	/** 客户端计算的结果. */
	private String response;

}
