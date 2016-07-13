package com.kcfy.platform.server.kernal.mapping;

import java.lang.annotation.Annotation;

import com.kcfy.platform.server.kernal.model.JModel;

public class JDefaultParamMeta implements JModel{

	private String name;
	
	private Class<?> type;
	
	private Annotation[] annotations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Annotation[] getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Annotation[] annotations) {
		this.annotations = annotations;
	}

	
}
