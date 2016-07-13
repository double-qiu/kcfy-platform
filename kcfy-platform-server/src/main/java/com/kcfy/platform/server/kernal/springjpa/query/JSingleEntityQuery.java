package com.kcfy.platform.server.kernal.springjpa.query;


import java.util.List;

import javax.persistence.EntityManager;

import com.kcfy.platform.server.kernal.model.JPage;
import com.kcfy.platform.server.kernal.model.JPageable;

public class JSingleEntityQuery {

	private JSingleEntityQueryMeta singleEntityQueryMeta;
	
	private EntityManager entityManager;

	public JSingleEntityQuery(Class<?> entityClass,
			EntityManager entityManager) {
		this.singleEntityQueryMeta = new JSingleEntityQueryMeta(entityClass,this);
		this.entityManager = entityManager;
	}
	
	public JCondition condition(){
		return singleEntityQueryMeta.condition();
	}
	
	/**
	 * default set  <strong>isAvailable=1</strong>
	 * @return
	 */
	public JCondition conditionDefault(){
		return singleEntityQueryMeta.conditionDefault();
	}
	
	public JOrder order() {
		return singleEntityQueryMeta.order();
	}
	
	public <T> List<T> executeList(){
		return QueryBuilder.get(entityManager)
		.setJpql(singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.build().execute();
	}
	
	public <T> JPage<T> executePageable(JPageable pageable){
		return QueryBuilder.get(entityManager)
		.setJpql(singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.setPageable(pageable)
		.build().execute();
	}
	
}
