package com.apusic.login.service;

import java.util.Date;


/**
 * ���û��������û������MD5֮�����Կ���ܣ�Ȼ����ʹ�÷������Լ�����Կ��ʱ�������������ܡ�
 * ����Token��ֻ�ܱ���������ʶ�𣬻�����֤�û�����ݡ�
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
