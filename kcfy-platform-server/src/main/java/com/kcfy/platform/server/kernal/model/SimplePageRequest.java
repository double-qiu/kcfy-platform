package com.kcfy.platform.server.kernal.model;

import org.springframework.data.domain.PageRequest;

public class SimplePageRequest extends PageRequest implements JPageable {

	private long count=-1;
	
	public SimplePageRequest(int page, int size) {
		super(page, size);
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	public boolean counted(){
		return count!=-1;
	}
	
	
}
