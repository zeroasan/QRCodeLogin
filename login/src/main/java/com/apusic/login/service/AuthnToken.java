package com.apusic.login.service;



/**
 * ���û��������û������MD5֮�����Կ���ܣ�Ȼ����ʹ�÷������Լ�����Կ��ʧЧʱ�䡢��������ܡ�
 * ����Token��ֻ�ܱ���������ʶ�𣬻�����֤�û�����ݡ�
 * 
 * HA = MD5(username:password)
 * HA1 = MD5(uri)
 * response = MD5(HA1:nonce:expired)
 *
 *
 *
 * 
 * δ�������û��豸Ψһ��Ż���IP�ȣ���IP��Ϣ�䶯ʱ����Token��֤��ʧ�ܡ�
 * 
 * 
 * AuthnToken�ͻ����ύ������������֤����.
 */
public class AuthnToken {
	
	/** �û���. */
	private String username;
	
	/** �����. */
	private String nonce;
	
	/** ʧЧʱ��. */
	private String expired; 
	
	/** �ͻ��˼���Ľ��. */
	private String response;

}
