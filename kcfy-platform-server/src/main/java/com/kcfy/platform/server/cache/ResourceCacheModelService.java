/**
 * 
 */
package com.kcfy.platform.server.cache;

import java.util.List;


/**
 * system resource interface 
 * @author J
 */
public interface ResourceCacheModelService{
	
	<T extends ResourceCacheModel> List<T> getResourceCacheModels();
	
}
