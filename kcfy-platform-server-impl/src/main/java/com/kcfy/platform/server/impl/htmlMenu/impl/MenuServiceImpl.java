package com.kcfy.platform.server.impl.htmlMenu.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import test.org.kcfy.platform.server.impl.example.service.ExampleService;

import com.kcfy.platform.server.htmlMenu.helper.MenuJTree;
import com.kcfy.platform.server.htmlMenu.helper.MenuUtil;
import com.kcfy.platform.server.htmlMenu.model.MenuModel;
import com.kcfy.platform.server.htmlMenu.service.MenuService;
import com.kcfy.platform.server.kernal.service.JServiceLazyProxy;
import com.kcfy.platform.server.kernal.service.ServiceContext;
import com.kcfy.platform.server.kernal.service.ServiceSupport;
import com.kcfy.platform.server.tree.hepler.TreeTypeEnum;
import com.kcfy.platform.server.tree.model.JTreeModel;
import com.kcfy.platform.server.tree.model.JTreeNode;
import com.kcfy.platform.server.tree.serivce.JTreeModelService;
import com.kcfy.platform.server.tree.vo.JTreeTemplate;

@Service
public class MenuServiceImpl extends ServiceSupport implements MenuService {
	
	private JTreeModelService jTreeModelService = JServiceLazyProxy.proxy(JTreeModelService.class);
	
	@Override
	public JTreeTemplate getHtmlMenu(ServiceContext serviceContext) {
		List<JTreeModel> treeModels = jTreeModelService.getTreeModelsByJTreeType(serviceContext, TreeTypeEnum.Html_Menu);
		List<MenuJTree> jTreeNodes = MenuUtil.buildJTreeNode(treeModels);
		
		
		return null;
	}

	@Override
	public JTreeTemplate getMenuTree(ServiceContext serviceContext) {
		return null;
	}

	@Override
	public Boolean relevanceMenuToTree(ServiceContext serviceContext, String jTNodeId, MenuModel menuModel) {
		return null;
	}

	@Override
	public List<MenuModel> getAllNotRelevanceMenu(ServiceContext serviceContext) {
		return null;
	}

}
