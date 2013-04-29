package com.apusic.login;

public class LoginContext {
	
	public static final long TIMEOUT_INTERVAL = 10*60*1000L;

	private String serialNumber;
	
	private String principalName;
	
	private long date;
	
	private boolean authenticated;
	
	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getPrincipalName() {
		return principalName;
	}


	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}


	public long getDate() {
		return date;
	}


	public void setDate(long date) {
		this.date = date;
	}


	public boolean isAuthenticated() {
		return authenticated;
	}


	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}


	public boolean isTimeout() {
		return !authenticated && date + TIMEOUT_INTERVAL < System.currentTimeMillis();
	}
}
