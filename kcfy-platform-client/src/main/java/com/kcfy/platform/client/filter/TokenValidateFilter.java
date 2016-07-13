package com.kcfy.platform.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.kcfy.platform.client.webform.CheckToken;
import com.kcfy.platform.client.webform.TokenStorageService;

public class TokenValidateFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.setProperty(TokenStorageService.tokenStorageImplKey, "com.kcfy.platform.client.webform.TokenStorageImpl");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (CheckToken.checkToken(request, response)) {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
