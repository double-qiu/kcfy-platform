package com.kcfy.platform.client.webform;

import com.kcfy.platform.common.JAssert;
import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.common.JUniqueUtils;
import com.kcfy.platform.common.model.FormIdentificationVO;


public class DefaultVoidDuplicateSubmitService implements VoidDuplicateSubmitService {
	
	private TokenStorageService tokenStorageService=TokenStorageServiceUtil.get();
	
	@Override
	public synchronized boolean validate(String formId, String token) {
		FormIdentificationVO formIdentification=tokenStorageService.getToken(formId);
		if(formIdentification==null){
			throw new RuntimeException("the form ["+formId+"] is invlaid, check if the form need validate.");
		}
		String storageToken=formIdentification.getToken();
		if(JStringUtils.isNotNullOrEmpty(storageToken)){
			boolean valid= storageToken.equals(token);
			tokenStorageService.removeByFormId(formId);
			return valid;
		}
		else{
			throw new RuntimeException("the form ["+formId+"] is invlaid, "+
					(token==null?"token is missing.":("token : "+token))
					);
		}
	}
	
	@Override
	public boolean validate(FormIdentificationVO formIdentification) {
		return validate(formIdentification.getFormId(),formIdentification.getToken());
	}
	
	@Override
	public boolean cleanup(String sessionId) {
		return tokenStorageService.removeBySessionId(sessionId);
	}
	
	
	@Override
	public FormIdentificationVO newFormIdentification() {
		FormIdentificationVO formIdentification=new FormIdentificationVO();
		String sessionId=tokenStorageService.getSessionId();
		JAssert.isNotNull(sessionId,"");
		JAssert.isNotEmpty(sessionId);
		formIdentification.setSessionId(sessionId);
		String appendUnique=JUniqueUtils.unique();
		String formIId=sessionId+"_"+appendUnique;
		formIdentification.setFormId(formIId);
		formIdentification.setToken(JUniqueUtils.unique());
		
		//store ...
		tokenStorageService.store(formIdentification);
		
		return formIdentification;
	}
	
}
