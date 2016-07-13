package com.kcfy.platform.server.kernal.service;

public interface SpringApplicationServiceGetService {

	public Object getService(Class<?> clazz);
	
	public Object getService(String serviceName);
	
}
