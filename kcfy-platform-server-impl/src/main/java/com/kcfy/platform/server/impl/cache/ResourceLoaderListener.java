package com.kcfy.platform.server.impl.cache;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.kcfy.platform.server.cache.InitialResource;
import com.kcfy.platform.server.cache.ResourceCacheServiceSupport;

/**
 * @author J
 */
@Component
public class ResourceLoaderListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ResourceLoaderListener.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try{
			Set<InitialResource> initialResources=  ResourceCacheServiceSupport.getInitialResources();
			for(InitialResource initialResource:initialResources){
				initialResource.initResource();
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
