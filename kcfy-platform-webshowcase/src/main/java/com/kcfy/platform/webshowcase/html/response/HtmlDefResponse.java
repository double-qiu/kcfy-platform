package com.kcfy.platform.webshowcase.html.response;

import com.kcfy.platform.webshowcase.html.HtmlDefNames;

public class HtmlDefResponse {
	
	/**
	 * @see HtmlDefNames#LAYOUT
	 * @see HtmlDefNames#HTML
	 */
	private String type;
	
	
	private String layoutId;
	
	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	/**
	 * 
	 * @return {@link #type}
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type {@link #type}
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
