package com.kcfy.platform.server.kernal;

import java.io.InputStream;
import java.util.Properties;

public class Version {
	
	public static String getVersion(){
		
		try{
			InputStream inputStream= Version.class.getResourceAsStream("version.properties");
			Properties properties=new Properties();
			properties.load(inputStream);
			return properties.getProperty("platform.version");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
