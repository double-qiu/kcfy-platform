package com.kcfy.platform.client.webform;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcfy.platform.client.protocol.ResponseWriteUtils;
import com.kcfy.platform.client.servlet.ClientServlet;
import com.kcfy.platform.common.model.FormIdentificationVO;
import com.kcfy.platform.common.model.InvokeResultVO;

public class CheckToken {

	private static final String KEY = "headToken";
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServlet.class);

	public static boolean checkToken(ServletRequest request, ServletResponse response) {
		TokenStorageImpl.threadLocal.set(request);
		try {
			VoidDuplicateSubmitService voidDuplicateSubmitService = new DefaultVoidDuplicateSubmitService();
			FormIdentificationVO formIdentification = getToken(request);
			if (formIdentification != null) {
				boolean approve = false;
				try {
					approve = voidDuplicateSubmitService.validate(formIdentification);
				} catch (Exception e) {
					approve = false;
				}
				if (!approve) {
					String message = "Token失效，请重新提交";
					InvokeResultVO invokeResult = InvokeResultVO.failure(message);
					invokeResult.setToken(voidDuplicateSubmitService.newFormIdentification());
					ResponseWriteUtils.writeJson(response, invokeResult);
				}
				return approve;
			} else {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	private static FormIdentificationVO getToken(ServletRequest request) {
		String tokenKey = request.getParameter(KEY);
		if (tokenKey == null || "".equals(tokenKey)) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			FormIdentificationVO formIdentification = mapper.readValue(tokenKey, FormIdentificationVO.class);
			return formIdentification;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("token parse error");
		}
	}

}
