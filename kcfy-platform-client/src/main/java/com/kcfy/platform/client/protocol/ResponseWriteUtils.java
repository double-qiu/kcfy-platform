package com.kcfy.platform.client.protocol;

import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcfy.platform.client.servlet.ClientServlet;
import com.kcfy.platform.common.model.InvokeResultVO;
import com.kcfy.techservicemarket.core.json.util.IOUtils;

public class ResponseWriteUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServlet.class);
	
	public static void writeJson(ServletResponse response,InvokeResultVO invokeResult){
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter printWriter = null;
		try {
			String invokeResultString = objectMapper.writeValueAsString(invokeResult);
			HttpServletResponse servletResponse = (HttpServletResponse)response;
			servletResponse.reset();//清除首部的空白行
			response.setContentType("application/json;charset=utf-8");
			printWriter = response.getWriter();
			printWriter.write(invokeResultString);
		} catch (Exception e) {
			IOUtils.close(printWriter);
			LOGGER.error(e.getMessage(), e);
		}finally {
			IOUtils.close(printWriter);
		}
	}
	
	public static void writeJson(ServletResponse response,Object object){
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter printWriter = null;
		try {
			String invokeResultString = objectMapper.writeValueAsString(object);
			HttpServletResponse servletResponse = (HttpServletResponse)response;
			servletResponse.reset();//清除首部的空白行
			response.setContentType("application/json;charset=utf-8");
			printWriter = response.getWriter();
			printWriter.write(invokeResultString);
		} catch (Exception e) {
			IOUtils.close(printWriter);
			LOGGER.error(e.getMessage(), e);
		}finally {
			IOUtils.close(printWriter);
		}
	}

}
