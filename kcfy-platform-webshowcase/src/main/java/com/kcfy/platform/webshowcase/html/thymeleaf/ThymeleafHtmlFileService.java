package com.kcfy.platform.webshowcase.html.thymeleaf;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kcfy.platform.webshowcase.html.HtmlFileService;

public class ThymeleafHtmlFileService implements HtmlFileService {

	private static TemplateEngine templateEngine=ServletTemplateResolver.getTemplateEngine();
	
	
	@Override
	public byte[] getHtmlFile(String uri, Map<String, Object> attrs) {
		Context context=new Context(Locale.getDefault(), attrs);
		try {
			return templateEngine.process(uri, context).getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	@Override
	public byte[] getHtmlFileDef(String uri, Map<String, Object> attrs) {
		return null;
	}

	@Override
	public File getFile(String uri) {
		return null;
	}

	
}
