/**
 * 
 */
package com.kcfy.platform.server.kernal.service;

import com.kcfy.platform.server.kernal.model.JModel;
import com.kcfy.platform.server.kernal.model.SessionUser;

/**
 * @author Administrator
 *
 */
public class ServiceContext implements JModel {

	private SessionUser user;

	public SessionUser getUser() {
		return user;
	}

	public void setUser(SessionUser user) {
		this.user = user;
	}
	
	public static ServiceContext getSys(){
		ServiceContext serviceContext = new ServiceContext();
		SessionUser sessionUser = new SessionUser();
		sessionUser.setId("SYS");
		sessionUser.setNatureName("SYS");
		sessionUser.setUserName("SYS");
		serviceContext.setUser(sessionUser);
		return serviceContext;
	}
	public static ServiceContext getSys(String allName){
		ServiceContext serviceContext = new ServiceContext();
		SessionUser sessionUser = new SessionUser();
		sessionUser.setId(allName);
		sessionUser.setNatureName(allName);
		sessionUser.setUserName(allName);
		serviceContext.setUser(sessionUser);
		return serviceContext;
	}
	public static ServiceContext getSys(String id,String natureName,String userName){
		ServiceContext serviceContext = new ServiceContext();
		SessionUser sessionUser = new SessionUser();
		sessionUser.setId(id);
		sessionUser.setNatureName(natureName);
		sessionUser.setUserName(userName);
		serviceContext.setUser(sessionUser);
		return serviceContext;
	}
	
	
	
	
}
