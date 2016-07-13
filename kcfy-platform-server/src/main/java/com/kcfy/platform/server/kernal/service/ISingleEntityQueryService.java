package com.kcfy.platform.server.kernal.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public interface ISingleEntityQueryService {

	public abstract void setEm(EntityManager em);

}