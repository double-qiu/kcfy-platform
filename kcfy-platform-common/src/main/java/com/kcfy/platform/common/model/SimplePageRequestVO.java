package com.kcfy.platform.common.model;

import java.io.Serializable;


public class SimplePageRequestVO implements Serializable ,Cloneable {

	private int page;
	
	private int size;
	
	SimplePageRequestVO(){}

	public SimplePageRequestVO(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
