package com.kcfy.platform.server.kernal.springjpa.query;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.kcfy.platform.server.kernal.model.JPageable;

import java.util.HashMap;
import java.util.Map;

abstract class QueryMeta {

	protected Map<?, Object> params=new HashMap<Object, Object>();
	
	protected final EntityManager em;
	
	protected JPageable pageable;
	
	protected Class<?> result;
	
	protected boolean single;
	
	protected String resultSetMapping;

	/**
	 * set true if the sql is update of insert 
	 * like 'update table set ... ; insert table ...'
	 */
	protected boolean update=false;
	
	
	public QueryMeta(EntityManager em) {
		super();
		this.em = em;
	}
	
	public JPageable getPageable() {
		return pageable;
	}
	public void setPageable(JPageable pageable) {
		this.pageable = pageable;
	}
	public boolean isPageable(){
		return pageable!=null;
	}
	
	public Map<?, Object> getParams() {
		return params;
	}
	
	public void setParams(Map<?, Object> params) {
		this.params = params;
	}
	
	public abstract Query getQuery();
	
	public abstract String getQueryString();
	
	public abstract String getCountQueryString();
	
	public abstract Query getCountQuery();

	public Class<?> getResult() {
		return result;
	}

	public void setResult(Class<?> result) {
		this.result = result;
	}

	public boolean isSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}
	
	public void setResultSetMapping(String resultSetMapping) {
		this.resultSetMapping = resultSetMapping;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
}
