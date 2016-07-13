package com.kcfy.platform.webshowcase.html.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kcfy.platform.common.JJSON;
import com.kcfy.platform.webshowcase.html.HtmlService;
import com.kcfy.platform.webshowcase.html.RequestParamNames;
import com.kcfy.platform.webshowcase.html.SyncHtmlModel;
import com.kcfy.platform.webshowcase.html.request.RequestHtml;
import com.kcfy.platform.webshowcase.html.request.RequestUrl;
import com.kcfy.platform.webshowcase.html.response.SyncHtmlResponse;
import com.kcfy.platform.webshowcase.html.response.SyncHtmlResponseService;
import com.kcfy.platform.webshowcase.html.thymeleaf.ServletTemplateResolver;

public class HtmlServlet extends HttpServlet{

	private static final Logger LOGGER=LoggerFactory.getLogger(HtmlServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletTemplateResolver.initializeTemplateEngine(config.getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	private HtmlService htmlService=HtmlService.get();
	
	private SyncHtmlResponseService syncHtmlResponseService=SyncHtmlResponseService.get();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SyncHtmlResponse syncHtmlResponse=null;
		try{
				String requestData=req.getParameter(RequestParamNames.REQUEST_DATA);
	        
	        if(LOGGER.isDebugEnabled()){
	        	LOGGER.debug("the request data-> "+requestData);
	        }
	        
	        if(requestData!=null&&requestData.length()>0){
	        	
	        	RequestUrl requestUrl=JJSON.get().parse(requestData, RequestUrl.class);
	        	
	        	RequestHtml requestHtml= requestUrl.getRequest();
	        	SyncHtmlModel syncHtmlModel= htmlService.getSyncHtmlModel(requestHtml);
	        	
	        	syncHtmlResponse= syncHtmlResponseService.getSyncHtmlResponse(requestUrl, syncHtmlModel);
	        }
	        else{
	        	throw new RuntimeException("request data is missing.");
	        }
	        
		}catch(Exception e){
			syncHtmlResponse=syncHtmlResponseService.miniError(e.getMessage());
		}finally{
			try{
    			String out=JJSON.get().formatObject(syncHtmlResponse);
    			writeBytesDirectly(req, resp, out.getBytes("utf-8"));
    		}catch(Exception e){
    			writeBytesDirectly(req, resp,"the server (json decode) error.".getBytes());
    		}
		}
		

	}
	
	
	public static final void writeBytesDirectly(HttpServletRequest request,
			HttpServletResponse response,byte[] bytes){
		OutputStream outputStream=null;
		try {
			outputStream=response.getOutputStream();
			response.getOutputStream().write(bytes);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
