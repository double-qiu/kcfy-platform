/**
 * 
 */
package com.kcfy.platform.server.kernal.mapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author J
 */
public class MappingHub{
	
	public static final Map<String, MappingMeta> CONTROLLERS=new ConcurrentHashMap<String, MappingMeta>();
	
	public static final Map<String, Object> CONTROLLER_OBJECTS=new ConcurrentHashMap<String, Object>();
	
	public static MappingMeta getControllerMetaByPath(String path){
		return CONTROLLERS.get(path);
	}
	
	public static Object getControllerObjectByPath(String path){
		return CONTROLLER_OBJECTS.get(path);
	}
	
	public static void putMappingMeta(String path,MappingMeta mappingMeta){
		CONTROLLERS.put(path, mappingMeta);
	}
	
	public static void putControllerObject(String path,Object controllerObject){
		CONTROLLER_OBJECTS.put(path, controllerObject);
	}
	
	public static Collection<String> getAllPaths(){
		return CONTROLLERS.keySet();
	}
	
}
