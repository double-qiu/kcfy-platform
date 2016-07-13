package com.kcfy.platform.common.model;

import java.io.Serializable;

/**
 * 
 * @author JIAZJ
 *
 */
public class FormIdentificationVO implements Serializable ,Cloneable{

	private String sessionId;
	
	private String formId;
	
	private String token;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
