package com.kcfy.platform.server.kernal.springjpa.query2;

import com.kcfy.platform.server.kernal.springjpa.spi.JGenericQueryService;
import com.kcfy.platform.server.kernal.springjpa.spi.JHibernateQueryService;


public class JSPIQueryServiceUtil {

	protected static JSPIFoundService spiFoundService=new JSPIFoundService();
	
	protected static JSPIQueryService spiQueryService;
	static {
		JJPASPI jpaSpi=spiFoundService.getSpi();
		switch (jpaSpi) {
		case HIBERNATE:
			spiQueryService=new JHibernateQueryService();
			break;
		case EMPTY:
			spiQueryService=new JGenericQueryService();
			break;
		default:
			spiQueryService=new JGenericQueryService();
		}
	}
	
	public static JSPIQueryService getSPIQueryService(){
		return spiQueryService;
	}
	
}
