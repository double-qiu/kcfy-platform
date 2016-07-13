package com.kcfy.platform.server.tree.treeview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kcfy.platform.server.tree.model.JEntity;
import com.kcfy.platform.server.tree.model.JTree;
import com.kcfy.platform.server.tree.model.JTreeNode;

public class JTreeNodeInterfaceImpl implements JTreeNodeInterface {

	@Override
	public List<JTreeNode> generateTreeNode(List<? extends JTree> dataList, List<? extends JEntity> entitys) {
		return generateLimitTreeNode(dataList, entitys, false);
	}

	@Override
	public List<JTreeNode> generateLimitTreeNode(List<? extends JTree> dataList, List<? extends JEntity> entitys) {
		return generateLimitTreeNode(dataList, entitys, true);
	}

	List<JTreeNode> generateLimitTreeNode(List<? extends JTree> dataList, List<? extends JEntity> entitys, boolean limitType) {
		List<JTreeNode> jTreeNodes = new ArrayList<JTreeNode>();
		Map<String, ? extends JEntity> entityMaps = getEntityMaps(entitys);
		for (JTree jTree : dataList) {
			jTreeNodes.add(simpleCopy(jTree, entityMaps, limitType));
		}
		return jTreeNodes;
	}

	/**
	 * 不做 children's 赋值
	 * 
	 * @param jTree
	 * @param entityMaps
	 * @param limitType
	 * @return
	 */
	private JTreeNode simpleCopy(JTree jTree, Map<String, ? extends JEntity> entityMaps, boolean limitType) {
		JTreeNode jnode = new JTreeNode();
		JEntity jEntity = entityMaps.get(jTree.getEntityId());
		jnode.setEntity(jEntity);

		if (limitType || null == jEntity) {
			jnode.setChildrens(null);
		}
		jnode.setExpand(jTree.isExpand());
		jnode.setSequence(jTree.getSequence());
		jnode.setParentId(jTree.getPid());
		jnode.setId(jTree.getId());

		entityMaps.remove(jTree.getEntityId());
		return jnode;
	}

	private Map<String, ? extends JEntity> getEntityMaps(List<? extends JEntity> entitys) {
		Map<String, JEntity> backSource = new HashMap<String, JEntity>();
		for (JEntity jEntity : entitys) {
			backSource.put(jEntity.getKey(), jEntity);
		}
		return backSource;
	}

}
