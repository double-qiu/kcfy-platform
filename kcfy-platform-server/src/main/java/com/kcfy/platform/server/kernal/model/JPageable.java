/**
 * 
 */
package com.kcfy.platform.server.kernal.model;


/**
 * @author J
 */
public interface JPageable extends JModel{

	/**
	 * Returns the page to be returned.
	 * 
	 * @return the page to be returned.
	 */
	int getPageNumber();

	/**
	 * Returns the number of items to be returned.
	 * 
	 * @return the number of items of that page
	 */
	int getPageSize();
	

}
