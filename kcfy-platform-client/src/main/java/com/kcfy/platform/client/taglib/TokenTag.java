package com.kcfy.platform.client.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcfy.platform.client.Constants;
import com.kcfy.platform.client.webform.DefaultVoidDuplicateSubmitService;
import com.kcfy.platform.client.webform.VoidDuplicateSubmitService;

public class TokenTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		HttpSession session = this.pageContext.getSession();
		synchronized (session) {
			try {
				VoidDuplicateSubmitService duplicateSubmitService = new DefaultVoidDuplicateSubmitService();
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(duplicateSubmitService.newFormIdentification());
				JspWriter out = pageContext.getOut();
				out.println("<input type=\"hidden\" token=\"token\" name=\""+Constants.TokenKey.KEY+"\" value="+json+"></input>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.doEndTag();
	}

}
