package com.kcfy.platform.server.impl.tree.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcfy.platform.server.impl.tree.repo.JTreeModelRepo;
import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;
import com.kcfy.platform.server.kernal.service.InternalServiceSupport;
import com.kcfy.platform.server.tree.model.JTreeModel;

@Service
public  class InternalJTreeModelServiceImpl extends InternalServiceSupport<JTreeModel>{

	@Autowired
	JTreeModelRepo jTreeModelRepo;
	
	@Override
	public SingleEntityRepo<JTreeModel, String> getRepo() {
		return jTreeModelRepo;
	}

}
