package com.kcfy.platform.client.protocol;

import org.apache.commons.lang3.StringUtils;

import com.kcfy.techservicemarket.core.generate.PropertyGen;

public class WSO2Token {
	
	private static final String APP_WSO2_URL = "app.wso2.url";
	private static final String APP_WSO2_TIMEOUT = "wso2.timeout";
	private static final int DEFAULT_TIMEOUT = 5000;

	public static String getWSO2Url() throws Exception {
		if (PropertyGen.prop.isEmpty()) {
			PropertyGen.loadProperties();
		}
		return PropertyGen.prop.getProperty(APP_WSO2_URL);
	}
	
	public static int getWSO2Timeout() throws Exception {
		if (PropertyGen.prop.isEmpty()) {
			PropertyGen.loadProperties();
		}
		String timeout = PropertyGen.prop.getProperty(APP_WSO2_TIMEOUT);
		if(StringUtils.isNotBlank(timeout)){
			try {
				return Integer.parseInt(timeout);
			} catch (Exception e) {
				return DEFAULT_TIMEOUT;
			}
		}else{
			return DEFAULT_TIMEOUT;
		}
	}

}
