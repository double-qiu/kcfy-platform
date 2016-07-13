package com.kcfy.platform.server.htmlMenu.service;

import java.util.List;

import com.kcfy.platform.server.htmlMenu.model.MenuModel;
import com.kcfy.platform.server.kernal.service.ServiceContext;
import com.kcfy.platform.server.tree.vo.JTreeTemplate;

public interface MenuService {
	/**
	 * 获取菜单Tree
	 * @param serviceContext
	 * @return
	 */
	public JTreeTemplate getHtmlMenu(ServiceContext serviceContext);
	
	/**
	 * 获取结构Tree
	 * @param serviceContext
	 * @return
	 */
	public JTreeTemplate getMenuTree(ServiceContext serviceContext);
	
	/**
	 * 将菜单绑定到TreeNode上
	 * @param serviceContext
	 * @param jTNodeId
	 * @param menuModel
	 * @return
	 */
	public Boolean relevanceMenuToTree(ServiceContext serviceContext,String jTNodeId,MenuModel menuModel);
	
	/**
	 * 获取全部未绑定的 菜单
	 * @param serviceContext
	 * @return
	 */
	public List<MenuModel> getAllNotRelevanceMenu(ServiceContext serviceContext);
	
	
}
