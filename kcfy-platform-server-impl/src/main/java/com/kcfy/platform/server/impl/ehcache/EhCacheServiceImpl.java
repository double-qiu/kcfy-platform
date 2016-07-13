package com.kcfy.platform.server.impl.ehcache;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcfy.platform.server.cache.EhCacheService;
import com.kcfy.platform.server.kernal.service.ServiceSupport;


@Service
public class EhCacheServiceImpl extends ServiceSupport implements EhCacheService {
	Logger logger = LoggerFactory.getLogger(EhCacheServiceImpl.class);
	private Cache cache ;
	
	public EhCacheServiceImpl() {
		InputStream resourceAsStream = EhCacheServiceImpl.class.getResourceAsStream("/ehcache.xml");
		CacheManager cacheManager = CacheManager.create(resourceAsStream);
		  cache = cacheManager.getCache("ehcache");
		 
	}

	@Override
	public Object putNeverExpired(String key, Object object) {
		Element element = new  Element(key, object);
		cache.put(element);
		logger.debug("putNeverExpired --> key : [],object : []",key,object);
		return null;
	}

	@Override
	public Object get(String key) {
		Element object=cache.get(key);
		return object==null?null:object.getObjectValue();
	}

	@Override
	public Object remove(String key) {
		System.out.println("remove");
		return cache.remove(key);
	}

	@Override
	public boolean contains(String key) {
		return false;
	}

}
