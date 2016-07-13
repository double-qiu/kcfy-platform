package com.kcfy.platform.server.kernal.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcfy.platform.server.kernal.springjpa.query.JSingleEntityQuery;

@Service
public class SingleEntityQueryService extends ServiceSupport implements ISingleEntityQueryService{

	private static EntityManager em;
	
	@Override
	@Autowired
	public void setEm(EntityManager em) {
		SingleEntityQueryService.em = em;
	}
	
	public static JSingleEntityQuery getSingleEntityQuery(Class<?> entityClass){
		return new JSingleEntityQuery(entityClass, em);
	}
	
	
}
