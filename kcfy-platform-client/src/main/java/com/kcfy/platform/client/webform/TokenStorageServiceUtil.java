package com.kcfy.platform.client.webform;

import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.common.model.FormIdentificationVO;



public class TokenStorageServiceUtil implements TokenStorageService {
	
	private TokenStorageService tokenStorageService;
	
	private final Object sync=new Object();
	
	private TokenStorageService getTokenStorage(){
		if(tokenStorageService==null){
			synchronized (sync) {
				if(tokenStorageService==null){
					try{
						String tokenStorageImpl=System.getProperty(TokenStorageService.tokenStorageImplKey);
						if(JStringUtils.isNotNullOrEmpty(tokenStorageImpl)){
							tokenStorageService=(TokenStorageService) Class.forName(tokenStorageImpl).newInstance();
						}
						else{
							log("USER DEFAULT : "+TokenStorageImpl.class.getName());
							tokenStorageService=new TokenStorageImpl();
						}
					}catch(Exception e){
						log(e.getMessage());
						throw new RuntimeException(e);
					}
				}
			}
		}
		return tokenStorageService;
	}
	
	private static void log(String message){
		System.out.println(TokenStorageServiceUtil.class.getName()+"---------------->"+message);
	}
	
	private static final TokenStorageServiceUtil INSTANCE=new TokenStorageServiceUtil();
	
	public static TokenStorageService get(){
		return INSTANCE;
	}

	@Override
	public String getSessionId() {
		return getTokenStorage().getSessionId();
	}

	@Override
	public boolean store(FormIdentificationVO formIdentification) {
		return getTokenStorage().store(formIdentification);
	}

	@Override
	public FormIdentificationVO getToken(String formId) {
		return getTokenStorage().getToken(formId);
	}

	@Override
	public boolean removeBySessionId(String formId) {
		return getTokenStorage().removeBySessionId(formId);
	}

	@Override
	public boolean removeByFormId(String formId) {
		return getTokenStorage().removeByFormId(formId);
	}

}
