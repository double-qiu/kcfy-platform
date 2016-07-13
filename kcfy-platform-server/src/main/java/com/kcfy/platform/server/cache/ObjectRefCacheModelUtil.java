package com.kcfy.platform.server.cache;

import com.kcfy.platform.server.kernal.JStringUtils;

class ObjectRefCacheModelUtil {

	public static String key(ResourceCacheModel resourceCacheModel){
		return key(resourceCacheModel.getUri());
	}
	
	public static String key(String identifier){
		String key="";
		if(JStringUtils.isNotNullOrEmpty(identifier)){
			key=key+"-"+identifier;
		}
		return key;
	}
}
