package com.apusic.login.service;

import java.util.HashMap;
import java.util.Map;

import com.apusic.login.service.impl.SimpleLoginContextManager;

public class ServiceFactory {
	
	private static Map<Class, Object> serviceMap = new HashMap<Class, Object>();

	@SuppressWarnings("unchecked")
	public static synchronized <T> T createService(Class<T> clazz, boolean singleton) {
		if(singleton) {
			if(serviceMap.containsKey(clazz)) {
				return (T)serviceMap.get(clazz);
			} else {
				if(clazz == LoginContextManager.class) {
					serviceMap.put(clazz, new SimpleLoginContextManager());
					return (T)serviceMap.get(clazz);
				}
			}
		} 
		
		return null;
	}
}
