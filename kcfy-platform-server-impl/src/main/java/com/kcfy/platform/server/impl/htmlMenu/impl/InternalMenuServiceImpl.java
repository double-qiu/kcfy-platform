package com.kcfy.platform.server.impl.htmlMenu.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.kcfy.platform.server.htmlMenu.model.MenuModel;
import com.kcfy.platform.server.impl.htmlMenu.repo.MenuModelRepo;
import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;
import com.kcfy.platform.server.kernal.service.InternalServiceSupport;

public class InternalMenuServiceImpl extends InternalServiceSupport<MenuModel> {
	@Autowired
	MenuModelRepo modelRepo;
	@Override
	public SingleEntityRepo<MenuModel, String> getRepo() {
		return modelRepo;
	}
}
