/**
 * 
 */
package com.kcfy.platform.server.kernal.parameter.validation;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhengzw
 *
 */
public class SingleViolationObject {
	
	private boolean success = true;
	
	private String message = StringUtils.EMPTY;

	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static SingleViolationObject success() {
		SingleViolationObject singleViolationObject = new SingleViolationObject();
		singleViolationObject.success = true;
		
		return singleViolationObject;
	}
	
	public static SingleViolationObject failures(String message) {
		SingleViolationObject singleViolationObject = new SingleViolationObject();
		singleViolationObject.success = false;
		singleViolationObject.message = message;
		
		return singleViolationObject;
	}
}
