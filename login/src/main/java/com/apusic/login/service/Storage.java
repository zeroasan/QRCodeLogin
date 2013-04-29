package com.apusic.login.service;

public interface Storage<K, V> {

	void store(K key, V value);
	
	V retrive(K key);
}
