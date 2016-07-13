/**
 * 
 */
package com.kcfy.platform.server.cache;




/**
 * system resource interface.
 * @author J
 */
public interface ResourceCacheService<T> extends InitialResource,JCacheService{
	
	IdentifierGenerator generator();
	
}
