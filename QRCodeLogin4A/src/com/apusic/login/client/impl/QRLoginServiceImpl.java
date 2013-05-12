package com.apusic.login.client.impl;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.apusic.login.client.QRLoginService;

public class QRLoginServiceImpl implements QRLoginService {
	
	private static final String LOGIN_PATH = "/login";
	
	private static final String GRANT_PATH = "/grant";
	
	private static final int RETRY_TIME = 3;
	
	private String loginUrl = null;
	
	private String grantUrl = null;
	
	private String username;
	
	private String password;
	
	private String jsessionId;
	
	private static QRLoginService qrLoginService;
	
	
	private QRLoginServiceImpl(String appUrl, String username, String password) {
		loginUrl = appUrl + LOGIN_PATH;
		grantUrl = appUrl + GRANT_PATH;
		
		this.username = username;
		this.password = password;
	}
	
	public void refreshSession() throws Exception {
		//1. try to connect server with username and password
		//2. if login successfully, store the session cookie
		//3. else throw exception
		
		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(loginUrl);
		httpPost.setContentChunked(true);
		httpPost.setParameter("username", username);
		httpPost.setParameter("password", password);
		
		client.executeMethod(httpPost);
		
		String result = httpPost.getResponseBodyAsString();
		//the result is json format: {"result" : "successful" | "failed"}
		if(!checkResult(result)) {
			throw new RuntimeException("Login failed.");
		}
		
		Header header = httpPost.getResponseHeader("Set-Cookie");
		String cookies = header.getValue();
		
		jsessionId = extractSessionCookie(cookies);
	}
	
	
	private String extractSessionCookie(String cookies) {
		int index = cookies.indexOf("JSESSIONID");
		if(index == -1) {
			return "";
		} else {
			int separator = cookies.indexOf(';', index);
			if(separator == -1) {
				separator = cookies.length();
			}
			return cookies.substring(index, separator);
		}
	}

	private boolean checkResult(String result) {
		return result != null && result.contains("successful");
	}

	public static QRLoginService createInstance(String appUrl, String username, String password) {
		qrLoginService = new QRLoginServiceImpl(appUrl, username, password);
		return qrLoginService;
	}
	
	public static QRLoginService getInstance() {
		return qrLoginService;
	}

	//TODO Exception 
	@Override
	public boolean grant(String serialNumber) {
		try {
			if(this.jsessionId == null) {
				refreshSession();
			}
			
			for(int i = 0; i < RETRY_TIME; i++) {
				if(doGrant(serialNumber)) {
					return true;
				} 
				refreshSession();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	private boolean doGrant(String sn) {
		try {
			HttpClient client = new HttpClient();
			PostMethod httpPost = new PostMethod(grantUrl);
			httpPost.setContentChunked(true);
			String sessionCookie = jsessionId;
			httpPost.addRequestHeader("Cookie", sessionCookie);
			httpPost.addParameter("sn", sn);
			
			client.executeMethod(httpPost);
			
			String result = httpPost.getResponseBodyAsString();
			//the result is json format: {"result" : "successful" | "failed"}
			
			//check session. if it is timeout
			return checkResult(result);
		} catch(Exception ex1) {
			ex1.printStackTrace();
		}
		return false;
	}

	@Override
	public void logout() {
		jsessionId = null;
		username = null;
		password = null;
	}

}
