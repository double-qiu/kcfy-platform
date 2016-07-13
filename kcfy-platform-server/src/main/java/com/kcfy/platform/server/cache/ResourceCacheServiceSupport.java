package com.kcfy.platform.server.cache;

import java.util.HashSet;
import java.util.Set;

import com.kcfy.platform.server.kernal.service.JServiceLazyProxy;
import com.kcfy.platform.server.kernal.service.ServiceSupport;

public class ResourceCacheServiceSupport<T,M> extends ServiceSupport implements ResourceCacheService<T>,InitialResource{

	private JCacheService cacheService=JServiceLazyProxy.proxy(EhCacheService.class);
	
	private static DefaultIdentifierGenerator defaultIdentifierGenerator=new DefaultIdentifierGenerator();
	
	private static Set<InitialResource> initialResources=new HashSet<InitialResource>();
	
	public static Set<InitialResource> getInitialResources() {
		return initialResources;
	}
	
	public ResourceCacheServiceSupport() {
		initialResources.add(this);
	}
	
	@Override
	public IdentifierGenerator generator() {
		return defaultIdentifierGenerator;
	}

	@Override
	public T get(String key) {
		return (T) cacheService.get(generator().key(key));
	}

	@Override
	public T remove(String key) {
		return (T) cacheService.remove(generator().key(key));
	}

	@Override
	public Object putNeverExpired(String key, Object object) {
		return cacheService.putNeverExpired(generator().key(key), object);
	}

	@Override
	public boolean contains(String key) {
		return cacheService.contains(generator().key(key));
	}

	@Override
	public void initResource() {
		
	}

}
