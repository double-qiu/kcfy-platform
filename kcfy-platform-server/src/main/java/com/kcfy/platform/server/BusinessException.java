package com.kcfy.platform.server;

public class BusinessException extends RuntimeException {

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Exception e){
		super(e);
	}
	
}
