package com.apusic.login;

public class LoginContext {
	
	public static final long TIMEOUT_INTERVAL = 10*60*1000L;

	private String serialNumber;
	
	private String principalName;
	
	private long date;
	
	private boolean authenticated;
	
	private boolean granted;
	
	private String granteeSerialNumberNumber;
	
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


	public boolean isGranted() {
		return granted;
	}


	public void setGranted(boolean granted) {
		this.granted = granted;
	}
	
	
	public void grantAuthentication(String granteeSerialNumberNumber, String principalName) {
		this.authenticated = true;
		this.granted = true;
		this.granteeSerialNumberNumber = granteeSerialNumberNumber;
		this.principalName = principalName;
	}


	public String getGranteeSerialNumberNumber() {
		return granteeSerialNumberNumber;
	}

}
