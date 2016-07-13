package com.kcfy.platform.webshowcase.html;

import java.io.File;
import java.util.Map;

public interface HtmlFileService {

	public byte[] getHtmlFile(String uri,Map<String, Object> attrs);
	
	public byte[] getHtmlFileDef(String uri,Map<String, Object> attrs);
	
	public File getFile(String uri);
}
