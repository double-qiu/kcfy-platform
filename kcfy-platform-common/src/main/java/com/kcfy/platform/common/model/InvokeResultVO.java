package com.kcfy.platform.common.model;

import java.io.Serializable;


/**
 * Created by J on 2016/3/9.
 */
public class InvokeResultVO implements Serializable,Cloneable {

    private Object data;

    private String errorMessage;

    private boolean success=true;
    
    private FormIdentificationVO token;

    public static InvokeResultVO success(Object data) {
        InvokeResultVO result = new InvokeResultVO();
        result.data = data;
        return result;
    }

    public static InvokeResultVO success() {
        InvokeResultVO result = new InvokeResultVO();
        result.success = true;
        return result;
    }

    public static InvokeResultVO failure(String message) {
        InvokeResultVO result = new InvokeResultVO();
        result.success = false;
        result.errorMessage = message;
        return result;
    }

    public Object getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
   
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the token
	 */
	public FormIdentificationVO getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(FormIdentificationVO token) {
		this.token = token;
	}
    
    
}
