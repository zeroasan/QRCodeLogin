package com.apusic.login.service;

public class SerialNumberGenerator {

	public static String gen() {
		return String.valueOf(System.currentTimeMillis());
	}
}
