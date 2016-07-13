package com.kcfy.platform.webshowcase.html.response;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kcfy.platform.client.webform.DefaultVoidDuplicateSubmitService;
import com.kcfy.platform.client.webform.VoidDuplicateSubmitService;
import com.kcfy.platform.common.JJSON;
import com.kcfy.platform.webshowcase.html.HtmlDefNames;
import com.kcfy.platform.webshowcase.html.HtmlService;
import com.kcfy.platform.webshowcase.html.SyncHtmlModel;
import com.kcfy.platform.webshowcase.html.request.RequestUrl;


public class SyncHtmlResponseService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(SyncHtmlResponseService.class);
	
	private HtmlService htmlService=HtmlService.get();
	
	private SyncHtmlResponseService(){}
	
	private static SyncHtmlResponseService INSTANCE=new SyncHtmlResponseService();

	private VoidDuplicateSubmitService voidDuplicateSubmitService=new DefaultVoidDuplicateSubmitService();
	
	public static SyncHtmlResponseService get(){
		return INSTANCE;
	}
	
	public SyncHtmlResponse error(RequestUrl requestUrl){
		
		SyncHtmlResponse syncHtmlResponse=new SyncHtmlResponse();
		
		HtmlDefResponse htmlDefResponse=new HtmlDefResponse();
		htmlDefResponse.setType(HtmlDefNames.HTML);
		htmlDefResponse.setLayoutId(requestUrl.getRequest().getLayoutId());
		syncHtmlResponse.setHtmlDef(htmlDefResponse);
		
		try {
			syncHtmlResponse.setHtml(new String(htmlService.get404Page().getHtml(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return syncHtmlResponse;
	}
	
	public SyncHtmlResponse getSyncHtmlResponse(RequestUrl requestUrl,SyncHtmlModel syncHtmlModel){
		try{
			SyncHtmlResponse syncHtmlResponse=new SyncHtmlResponse();
			syncHtmlResponse.setHtml(new String(syncHtmlModel.getHtml(),"utf-8"));
			
			HtmlDefResponse htmlDefResponse=new HtmlDefResponse();
			htmlDefResponse.setType(syncHtmlModel.getHtmlDef().getType());
			htmlDefResponse.setLayoutId(requestUrl.getRequest().getLayoutId());
			syncHtmlResponse.setHtmlDef(htmlDefResponse);
			
//			syncHtmlResponse.setToken(JJSON.get().formatObject(voidDuplicateSubmitService.newFormIdentification()));
			
			return syncHtmlResponse;
			
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return  error(requestUrl);
		}
		
	}
	
	
	public SyncHtmlResponse miniError(String message){
		
		SyncHtmlResponse syncHtmlResponse=new SyncHtmlResponse();
		
		HtmlDefResponse htmlDefResponse=new HtmlDefResponse();
		htmlDefResponse.setType(HtmlDefNames.HTML);
		htmlDefResponse.setLayoutId("global-mini-error");
		syncHtmlResponse.setHtmlDef(htmlDefResponse);
		syncHtmlResponse.setHtml("error occurs,please contact administrator. cause by "+message);
		return syncHtmlResponse;
	}
	
}
