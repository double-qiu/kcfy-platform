/**
 * 
 */
package com.kcfy.platform.server.kernal.model;


/**
 * @author J
 *
 */
public class SessionUser implements JModel {

	/**
	 * the primary key of the login user , uuid
	 */
	private String id;
	
	/**
	 * user name to loginin 
	 */
	private String userName;

	/**
	 * user name to be display 
	 */
	private String natureName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNatureName() {
		return natureName;
	}

	public void setNatureName(String natureName) {
		this.natureName = natureName;
	}
	
}
