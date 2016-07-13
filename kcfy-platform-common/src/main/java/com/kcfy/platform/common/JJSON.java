package com.kcfy.platform.common;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JJSON {

	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	{
		//always default
//		mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
	}
	
	private static final JJSON json=new JJSON();
	
	public static JJSON get(){
		return json;
	}
	
	public String formatObject(Object object){
		try {
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			mapper.writeValue(out, object);
			return out.toString("UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public <T> T parse(String string, Class<T> t){
		try {
			return mapper.readValue(string, t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}  
	
	public Map<String, String> parse(String string){
		try {
			return mapper.readValue(string, new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	
	
	
}
