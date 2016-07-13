package com.kcfy.platform.client.protocol;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.kcfy.platform.common.model.RequestVO;
import com.kcfy.techservicemarket.core.generate.TokenGen;

public class HttpClient {
	
	private static final String charset = "utf-8";

	public static String doPost(String url, String params) throws Exception {
		String result;
		CloseableHttpClient hc = HttpClients.createDefault();
		// Post请求
		HttpPost httppost = new HttpPost(url);
		// 设置参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair(RequestVO.FORM_KEY, params));
		httppost.setEntity(new UrlEncodedFormEntity(nvps,charset));
		int timeout = WSO2Token.getWSO2Timeout();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
		httppost.setConfig(requestConfig);
		httppost.setHeaders(createHeaders());
		// 发送请求
		HttpResponse httpresponse = hc.execute(httppost);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		result = EntityUtils.toString(entity, charset);
		return result;
	}

	private static Header[] createHeaders() {
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Authorization", "Bearer " + TokenGen.getToken()));
		headers.add(new BasicHeader("accept", "*/*"));
		headers.add(new BasicHeader("connection", "Keep-Alive"));
		headers.add(new BasicHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8"));
		Header[] headerArray = new Header[headers.size()];
		return headers.toArray(headerArray);
	}

}
