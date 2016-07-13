package com.kcfy.platform.server.kernal.springjpa.query2;


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
	
	public JCondition conditionDefault(){
		return singleEntityQueryMeta.conditionDefault();
	}
	
	public JOrder order() {
		return singleEntityQueryMeta.order();
	}
	
	public <T> List<T> models(){
		return JQueryBuilder.get(entityManager).jpqlQuery()
		.setJpql(singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.models();
	}
	
	public <T> T model(){
		return JQueryBuilder.get(entityManager).jpqlQuery()
		.setJpql(singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.model();
	}
	
	public <T> JPage<T> modelPage(JPageable pageable){
		return JQueryBuilder.get(entityManager).jpqlQuery()
		.setJpql(singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.setPageable(pageable)
		.modelPage();
	}
	
}
