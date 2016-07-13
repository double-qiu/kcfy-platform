/**
 * 
 */
package com.kcfy.platform.server.kernal.parameter.validation;

/**
 * @author zhengzw
 *
 */
public class ValidationViolation {
	
	private String className;
	
	private String errMsg;
	
	public ValidationViolation(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
