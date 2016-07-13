package com.kcfy.platform.client.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kcfy.platform.client.Constants;
import com.kcfy.platform.client.protocol.ResponseWriteUtils;
import com.kcfy.platform.client.protocol.WSO2Client;
import com.kcfy.platform.client.webform.CheckToken;
import com.kcfy.platform.client.webform.DefaultVoidDuplicateSubmitService;
import com.kcfy.platform.client.webform.VoidDuplicateSubmitService;
import com.kcfy.platform.common.model.InvokeResultVO;
import com.kcfy.platform.common.model.ServiceContextVO;
import com.kcfy.platform.common.model.SessionUserVO;
import com.kcfy.platform.common.model.SimplePageRequestVO;

public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServlet.class);
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		work(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		work(request, response);
	}

	private void work(HttpServletRequest request, HttpServletResponse response) {
		VoidDuplicateSubmitService voidDuplicateSubmitService = new DefaultVoidDuplicateSubmitService();
		String endpoint = request.getParameter("endpoint");
		String formData = request.getParameter("formData");
		String isPage = request.getParameter("isPage");
		String paginationData = null;
		if(StringUtils.isNotBlank(isPage) && Boolean.parseBoolean(isPage)){
			String page_start = request.getParameter("start");
			String page_length = request.getParameter("length");
			paginationData = JSON.toJSONString(new SimplePageRequestVO(Integer.parseInt(page_start),Integer.parseInt(page_length)));
		}else{
			paginationData = request.getParameter("paginationData");
		}
		
		Object sessionUser = request.getSession().getAttribute(Constants.CLIENT_SESSION_NAME);
		ServiceContextVO serviceContext = null;
		if(sessionUser == null){
			serviceContext = ServiceContextVO.getSys();
		}else{
			serviceContext = new ServiceContextVO();
			serviceContext.setUser((SessionUserVO)sessionUser);
		}
		
		InvokeResultVO invokeResultVO = WSO2Client.send(endpoint, formData, paginationData, JSON.toJSONString(serviceContext));
		if(!invokeResultVO.isSuccess()){
			invokeResultVO.setToken(voidDuplicateSubmitService.newFormIdentification());
		}
		if(StringUtils.isNotBlank(isPage) && Boolean.parseBoolean(isPage)){
			ResponseWriteUtils.writeJson(response, invokeResultVO.getData());
		}else{
			ResponseWriteUtils.writeJson(response, invokeResultVO);
		}
		
	}

}
