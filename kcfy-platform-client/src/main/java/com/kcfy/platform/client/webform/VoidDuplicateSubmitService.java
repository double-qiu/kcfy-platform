package com.kcfy.platform.client.webform;

import com.kcfy.platform.common.model.FormIdentificationVO;

public interface VoidDuplicateSubmitService {
	
	/**
	 * check if the form submit if valid.
	 * @param formId
	 * @param token
	 * @return
	 */
	boolean validate(String formId,String token);
	
	/**
	 * same as {@link #validate(String, String)}
	 * @param formIdentification
	 * @return
	 * @throws NullPointerException
	 */
	boolean validate(FormIdentificationVO formIdentification);
	
	/**
	 * cleanup all related form-tokens of any session context.
	 * @param sessionId
	 * @return
	 */
	boolean cleanup(String sessionId);

	/**
	 * get new form-token.
	 * @return
	 */
	FormIdentificationVO newFormIdentification();
	
}

