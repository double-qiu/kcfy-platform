package com.kcfy.platform.server.htmlMenu.helper;

import java.util.ArrayList;
import java.util.List;

import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.server.tree.model.JTreeModel;

public class MenuUtil {

		public static List<MenuJTree> buildJTreeNode(List<JTreeModel> treeModel){
			List<MenuJTree> backSource = new ArrayList<MenuJTree>();
			for (JTreeModel jTreeModel : treeModel) {
				backSource.add(buildJTreeNode(jTreeModel));
			}
			return backSource;
		}
		
		public static MenuJTree buildJTreeNode(JTreeModel treeModel){
				try {
					MenuJTree jTreeNode = MenuJTree.class.newInstance();
					
					jTreeNode.setParentId(treeModel.getPid());
					jTreeNode.setSequence(treeModel.getSequence());
					jTreeNode.setExpand(treeModel.isExpand());
					if(JStringUtils.isNullOrEmpty(treeModel.getCode())){
						jTreeNode.setId(treeModel.getId());
					}else{
						jTreeNode.setId(treeModel.getCode());	
					}
					
					return jTreeNode;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	return null;
		}
		
	
}
