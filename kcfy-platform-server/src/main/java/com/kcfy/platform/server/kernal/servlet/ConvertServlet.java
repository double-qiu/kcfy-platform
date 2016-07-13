package com.kcfy.platform.server.kernal.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kcfy.platform.common.JJSON;
import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.common.model.RequestVO;
import com.kcfy.platform.server.cache.SimplePropertyExtendHandler;
import com.kcfy.platform.server.kernal.mapping.MappingHub;
import com.kcfy.platform.server.kernal.mapping.MappingMeta;
import com.kcfy.platform.server.kernal.model.InvokeResult;
import com.kcfy.platform.server.kernal.model.SimplePageRequestVO;
import com.kcfy.platform.server.kernal.service.ServiceContext;

/**
 * Created by yankm on 2016/3/14.
 */
public class ConvertServlet extends HttpServlet {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ConvertServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	InvokeResult invokeResult=null;
    	try{
    		LOGGER.info(" request from the url :  "+request.getRequestURL());
        	
            Map<String,String> map = new HashMap<String, String>();//map用于放header头
            request.setCharacterEncoding("UTF-8");
            Enumeration<String> enu = request.getHeaderNames();
            while(enu.hasMoreElements()){
                String headerName = enu.nextElement();
                map.put(headerName,request.getHeader(headerName));
            }
            String type = map.get("head-type");
            
            String requestData=request.getParameter(RequestVO.FORM_KEY);
            
            if(LOGGER.isDebugEnabled()){
            	LOGGER.debug("the request data-> "+requestData);
            }
            
            if(JStringUtils.isNotNullOrEmpty(requestData)){
            	
            	RequestVO requestVO=JJSON.get().parse(requestData, RequestVO.class);
            	String endpoint=requestVO.getEndpoint();
            	final String pathInfo = endpoint;
                Object controllerObjectByPath = MappingHub.getControllerObjectByPath(pathInfo);
                MappingMeta controllerMetaByPath = MappingHub.getControllerMetaByPath(pathInfo);
                Class<?>[] methodParamClasses = controllerMetaByPath.getMethodParamClasses();
                Object[] parameters = new Object[methodParamClasses.length];
                for(int i = 0;i<methodParamClasses.length;i++){
                    switch(i){
                    	case 0:{
                    		ServiceContext serviceContext=JJSON.get().parse(requestVO.getServiceContext(),ServiceContext.class);
                    		parameters[0]=serviceContext;
                    		break;
                    	}
                    	case 1:{
                    		Class<?> clazz=methodParamClasses[1];
                    		Object object=null;
                    		if(SimplePageRequestVO.class==clazz){
                    			object=JJSON.get().parse(requestVO.getPaginationData(),SimplePageRequestVO.class);
                    		}else{
                    			object=JJSON.get().parse(requestVO.getFormData(),clazz);
                    		}
                    		parameters[1]=object;
                    		break;
                    	}
                    	case 2:{
                    		SimplePageRequestVO simplePageRequestVO=JJSON.get().parse(requestVO.getPaginationData(),SimplePageRequestVO.class);
                    		parameters[2]=simplePageRequestVO;
                    		break;
                    	}
                    	default:{}
                    }
                }
                Object invoke = invoke(controllerObjectByPath, controllerMetaByPath.getMethodName(), methodParamClasses, parameters);
                new SimplePropertyExtendHandler().handle(invoke);
            	invokeResult=(InvokeResult)invoke;
            	if(LOGGER.isDebugEnabled()){
            		if(InvokeResult.class.isInstance(invoke)){
            			LOGGER.debug("the invoke["+requestVO.getEndpoint()+"] witht the data `"+requestData+"`  result-->"+JJSON.get().formatObject(invokeResult));
            		}
            	}
            }
            else{
            	invokeResult= InvokeResult.failure("the input data is missing.");
            }
            LOGGER.debug("request head of 'head-tye' :  "+ type);
    	}catch(Exception e){
    		invokeResult= InvokeResult.failure(e.getMessage());
    	}finally{
    		try{
    			String out=JJSON.get().formatObject(invokeResult);
    			writeBytesDirectly(request, response, out.getBytes("utf-8"));
    		}catch(Exception e){
    			writeBytesDirectly(request, response, "the server (json decode) error.".getBytes());
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
    
    private Object invoke(Object object, String methodName,Class<?>[] parameterTypes,Object[] parameters) throws Exception {
        Class<?> clazz=object.getClass();
        Method targetMethod=clazz.getMethod(methodName, parameterTypes);
        if(targetMethod==null) throw new RuntimeException("Method ["+methodName+"] not found.");
        targetMethod.setAccessible(true);
        return targetMethod.invoke(object, parameters);
    }
    
}
