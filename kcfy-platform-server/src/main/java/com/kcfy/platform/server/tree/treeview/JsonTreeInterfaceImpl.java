package com.kcfy.platform.server.tree.treeview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;

import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.server.BusinessExceptionUtil;
import com.kcfy.platform.server.kernal.Copy;
import com.kcfy.platform.server.tree.model.JTreeNode;
import com.kcfy.platform.server.tree.vo.JTreeTemplate;

public class JsonTreeInterfaceImpl implements JsonTreeInterface<JTreeTemplate> {

	@Override
	public List<JTreeTemplate> generateJTreeTemplates(List<JTreeNode> treeNodes) {
		return null;
	}

	@Override
	public JTreeTemplate generateJTreeTemplate(List<JTreeNode> treeNodes) {
		return myGenerateJTreeTemplate(treeNodes, false);
	}


	private JTreeTemplate myGenerateJTreeTemplate(List<JTreeNode> treeNodes, boolean sort) {
		boolean doOneRootVo = true;
		List<JTreeNode> treeSouceNotRoot = new ArrayList<JTreeNode>();
		/**
		 * 第一次循环 check rootTree & make rootTree away other trees
		 */
		JTreeTemplate rootTree = null;
		for (JTreeNode treeSource : treeNodes) {

			if (JStringUtils.isNullOrEmpty(treeSource.getParentId())) {
				if (doOneRootVo) {
					doOneRootVo = false;
					rootTree = simpleCopy(treeSource);

				} else {
					BusinessExceptionUtil.throwException(new Exception("Tree have many RootTree" + treeSource.getId()));
				}

			} else {
				treeSouceNotRoot.add(treeSource);
			}
		}
		List<JTreeNode> treeSourceByPid = getchildrenNode(treeSouceNotRoot, rootTree.getId());
		createJsonTree(rootTree, treeSourceByPid, treeSouceNotRoot,sort);
		return rootTree;
	}

	/**
	 * JtreeTemlate 的构建 是通过 {@link JsonTreeInterfaceImpl#simpleCopy(JTreeNode)}
	 * 
	 * @param rootTree
	 * @param treeSourceByPid
	 * @param treeSouceNotRoot
	 */
	private void createJsonTree(JTreeTemplate treeNode, List<JTreeNode> childrenNodes, List<JTreeNode> treeSouceNotRoot,boolean sort) {

		if (null == childrenNodes || childrenNodes.size() <= 0) {
			return;
		} else {
			
			List<JTreeTemplate> backList  = new ArrayList<JTreeTemplate>();
		
			if(sort){
				Collections.sort(childrenNodes, new ListSnDescComparator() );
			}
			
			for (JTreeNode jTreeNode : childrenNodes) {
				JTreeTemplate simpleCopy = simpleCopy(jTreeNode);
				backList.add(simpleCopy);
				
				if(jTreeNode.isSubclass()){
					
					createJsonTree(simpleCopy, getchildrenNode(treeSouceNotRoot, jTreeNode.getId()), treeSouceNotRoot, sort);
					
				}else{
					//当前root 不符合有子类条件
				}
			}
			
			treeNode.setChildrens(backList);

		}
	}

	private List<JTreeNode> getchildrenNode(List<JTreeNode> treeSouceNotRoot, String id) {
		List<JTreeNode> backSouce = new ArrayList<JTreeNode>();
		for (JTreeNode Source : treeSouceNotRoot) {
			if (JStringUtils.isNotNullOrEmpty(Source.getParentId()) && Source.getParentId().equals(id))
				backSouce.add(Source);
		}
		treeSouceNotRoot.remove(backSouce);
		return backSouce;
	}
	

	

	private JTreeTemplate simpleCopy(JTreeNode treeSource) {
		JTreeTemplate newInstance;
		try {
			newInstance = JTreeTemplate.class.newInstance();
			newInstance.setId(treeSource.getId());
			newInstance.setEntity(treeSource.getEntity());
			newInstance.setExpand(treeSource.isExpand());
			return newInstance;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	};

}
