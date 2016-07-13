package com.kcfy.platform.client.webform;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kcfy.platform.common.model.FormIdentificationVO;

public class TokenStorageImpl implements TokenStorageService {

	public final static ThreadLocal<ServletRequest> threadLocal = new ThreadLocal<ServletRequest>();
	
	private final String tokenMap = "tokenMap";
	
	@Override
	public String getSessionId() {
		HttpServletRequest request = (HttpServletRequest)threadLocal.get();
		HttpSession session = request.getSession();
		String sessionId = "";
		synchronized (session) {
			sessionId = request.getSession().getId();
		}
		return sessionId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean store(FormIdentificationVO formIdentification) {
		try {
			HttpServletRequest request = (HttpServletRequest)threadLocal.get();
			HttpSession session = request.getSession();
			synchronized (session) {
				Map<String,FormIdentificationVO> map = (Map<String,FormIdentificationVO>)session.getAttribute(tokenMap);
				if(map == null){
					map = new HashMap<String,FormIdentificationVO>();
					session.setAttribute(tokenMap, map);
				}
				map.put(formIdentification.getFormId(), formIdentification);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormIdentificationVO getToken(String formId) {
		HttpServletRequest request = (HttpServletRequest)threadLocal.get();
		HttpSession session = request.getSession();
		FormIdentificationVO formIdentification = null;
		synchronized (session) {
			Map<String,FormIdentificationVO> map = (Map<String,FormIdentificationVO>)session.getAttribute(tokenMap);
			formIdentification = map.get(formId);
		}
		return formIdentification;
	}

	@Override
	public boolean removeBySessionId(String sessionId) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeByFormId(String formId) {
		try {
			HttpServletRequest request = (HttpServletRequest)threadLocal.get();
			HttpSession session = request.getSession();
			synchronized (session) {
				Map<String,FormIdentificationVO> map = (Map<String,FormIdentificationVO>)session.getAttribute(tokenMap);
				map.remove(formId);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
