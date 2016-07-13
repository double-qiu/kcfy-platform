/**
 * 
 */
package com.kcfy.platform.server.cache;

import com.kcfy.platform.server.kernal.model.JModel;


/**
 * ANY resource that need be cached in any form.
 * @author J
 */
public interface ResourceCacheModel extends JModel{

	/**
	 * the resource uri.
	 * @return
	 */
	public String getUri();
	
}
