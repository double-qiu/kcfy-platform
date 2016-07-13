package com.kcfy.platform.client.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kcfy.platform.common.model.InvokeResultVO;
import com.kcfy.platform.common.model.RequestVO;

public class WSO2Client {

	private static final Logger LOGGER = LoggerFactory.getLogger(WSO2Client.class);

	/**  
	 * 获取默认wso2url
	 * 
	 *  @return  
	 */
	public static InvokeResultVO send(String endpoint, String formData, String paginationData, String serviceContext) {
		RequestVO reqeustVO = new RequestVO(endpoint, serviceContext, formData, paginationData, "");
		try {
			String result = HttpClient.doPost(WSO2Token.getWSO2Url(), JSON.toJSONString(reqeustVO));
			InvokeResultVO invokeResultVO = JSON.parseObject(result, InvokeResultVO.class);
			return invokeResultVO;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResultVO.failure(e.getMessage());
		}
	}
	
	/**  
	 * 指定wso2地址
	 * 
	 *  @return  
	 */
	public static InvokeResultVO send(String endpoint, String formData, String paginationData, String serviceContext, String wso2Url) {
		RequestVO reqeustVO = new RequestVO(endpoint, serviceContext, formData, paginationData, "");
		try {
			String result = HttpClient.doPost(wso2Url, JSON.toJSONString(reqeustVO));
			InvokeResultVO invokeResultVO = JSON.parseObject(result, InvokeResultVO.class);
			return invokeResultVO;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResultVO.failure(e.getMessage());
		}
	}

}
