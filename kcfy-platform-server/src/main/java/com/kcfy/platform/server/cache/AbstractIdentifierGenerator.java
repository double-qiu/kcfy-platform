package com.kcfy.platform.server.cache;


public abstract class AbstractIdentifierGenerator implements IdentifierGenerator {
	
	@Override
	public final String key(Object object) {
		return namespace()+getKey(object);
	}
	
	protected abstract String getKey(Object object);
	
}
