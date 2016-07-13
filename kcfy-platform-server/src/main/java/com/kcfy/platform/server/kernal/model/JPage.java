package com.kcfy.platform.server.kernal.model;

import java.util.List;

public interface JPage<T>  extends JModel{

	JPageable getPageable();
	
	List<T> getContent();
	
	int getTotalPageNumber();
	
	long getTotalRecordNumber();
	
	public void setStart(long start);
	
}
