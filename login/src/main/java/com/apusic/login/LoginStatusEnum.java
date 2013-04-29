package com.apusic.login;


public enum LoginStatusEnum {
	
	LOGINED(0),

	CONTEXT_EMPTY(1),

	CONTEXT_TIME_OUT(2),
	
	NOT_LOGINED(3);
	
	
	private int statusCode;
	
	private LoginStatusEnum(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}
	
	public static LoginStatusEnum getStatus(int statusCode) {
		LoginStatusEnum[] enums = LoginStatusEnum.values();
		for(LoginStatusEnum e : enums) {
			if(e.getStatusCode() == statusCode) {
				return e;
			}
		}
		return null;
	}

}
