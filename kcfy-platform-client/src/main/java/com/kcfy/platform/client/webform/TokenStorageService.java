package com.kcfy.platform.client.webform;

import com.kcfy.platform.common.model.FormIdentificationVO;

public interface TokenStorageService {

	public static final String tokenStorageImplKey="com.kcfy.token.storage.impl";
	
	String getSessionId();
	
	boolean store(FormIdentificationVO formIdentification);
	
	FormIdentificationVO getToken(String formId);
	
	boolean removeBySessionId(String sessionId);
	
	boolean removeByFormId(String formId);
	
}
