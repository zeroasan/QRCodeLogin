package com.apusic.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.apusic.login.service.Storage;

public class SimpleStorage<K, V> implements Storage<K, V> {
	
	private Map<K, V> map = new HashMap<K, V>();

	public void store(K key, V value) {
		map.put(key, value);
	}

	public V retrive(K key) {
		return map.get(key);
	}

}
