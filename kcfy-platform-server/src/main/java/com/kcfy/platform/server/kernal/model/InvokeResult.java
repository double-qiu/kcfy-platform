package com.kcfy.platform.server.kernal.model;




/**
 * Created by J on 2016/3/9.
 */
public class InvokeResult implements JModel {

    private Object data;

    private String errorMessage;

    private boolean success=true;
    
    private FormIdentification token;

    public static InvokeResult success(Object data) {
        InvokeResult result = new InvokeResult();
        result.data = data;
        return result;
    }

    /**
     * use {@link #success(Object)} instead of 
     * @return
     */
    @Deprecated
    public static InvokeResult success() {
        InvokeResult result = new InvokeResult();
        result.success = true;
        return result;
    }

    public static InvokeResult failure(String message) {
        InvokeResult result = new InvokeResult();
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

	public FormIdentification getToken() {
		return token;
	}

	public void setToken(FormIdentification token) {
		this.token = token;
	}

	
}
