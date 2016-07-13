package com.kcfy.platform.common.model;

import java.io.Serializable;

public class RequestVO implements Serializable ,Cloneable {
	
	public static final String FORM_KEY="_reqeust_entity";
	
	/**
	 * the target URL
	 */
	private String endpoint;
	
	/**
	 * THE METHOD EXECUTING CONTEXT (JSON FORMAT)
	 * @see ServiceContextVO
	 * @see SessionUserVO
	 */
	private String serviceContext;
	
	/**
	 * THR FORM DATA(JSON FORMAT)
	 */
	private String formData;
	
	/**
	 * PAGINATION DATA IF PAGEABLE (JSON FORMAT)
	 * @see SimplePageRequestVO
	 */
	private String paginationData;
	
	/**
	 * TOKEN INFO RELATED TO FORM(JSON FORMAT)
	 * @see FormIdentificationVO 
	 */
	private String token;
	
	public RequestVO(){
		
	}

	public RequestVO(String endpoint, String serviceContext, String formData, String paginationData, String token) {
		this.endpoint = endpoint;
		this.serviceContext = serviceContext;
		this.formData = formData;
		this.paginationData = paginationData;
		this.token = token;
	}

	public String getServiceContext() {
		return serviceContext;
	}

	public void setServiceContext(String serviceContext) {
		this.serviceContext = serviceContext;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public String getPaginationData() {
		return paginationData;
	}

	/**
	 * 
	 * @param paginationData
	 * @see FormIdentificationVO
	 */
	public void setPaginationData(String paginationData) {
		this.paginationData = paginationData;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	
	
}
