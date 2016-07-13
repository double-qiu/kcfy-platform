/**
 * 
 */
package com.kcfy.platform.server.kernal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J
 */
@SuppressWarnings("serial")
public class JImpl<T> implements JPage<T> {
	
	private long totalRecordNumber=Long.MAX_VALUE;

	private int totalPageNumber;
	
	private JPageable pageable;
	
	private List<T> content=new ArrayList<T>();
	
	private long start;
	
	public static int caculateTotalPageNumber(long totalRecordNumber,int pageSize){
		if(totalRecordNumber < pageSize) {
			return 0;
		} else {
			if ((totalRecordNumber % pageSize) > 0) {
				return (int) (totalRecordNumber/pageSize+1);
			} else {
				return (int) (totalRecordNumber/pageSize);
			}
		}
	}
	
	public void caculatePageNumber() {
		setTotalPageNumber(caculateTotalPageNumber(totalRecordNumber, pageable.getPageSize()));
		if (pageable.getPageNumber() > totalPageNumber) {
			JPageRequest pageRequest=(JPageRequest)pageable;
			pageRequest.setPageNumber(totalPageNumber);
		}
		
	}

	public long getTotalRecordNumber() {
		return totalRecordNumber;
	}



	public void setTotalRecordNumber(long totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}



	public int getTotalPageNumber() {
		return totalPageNumber;
	}


	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}


	public JPageable getPageable() {
		return pageable;
	}


	public void setPageable(JPageable pageable) {
		this.pageable = pageable;
	}


	public List<T> getContent() {
		return content;
	}


	public void setContent(List<T> content) {
		this.content = content;
	}
	
	 public long getStart() {
	        return start;
	    }
	
	public void setStart(long start) {
		this.start = start;
	}
}
