package com.kcfy.platform.server.tree.serivce;

import java.util.List;

import com.kcfy.platform.server.kernal.service.ServiceContext;
import com.kcfy.platform.server.tree.hepler.TreeTypeEnum;
import com.kcfy.platform.server.tree.model.JTreeModel;

public interface JTreeModelService {

	
	
	
	List<JTreeModel> getTreeModelsByJTreeType(ServiceContext serviceContext,TreeTypeEnum type);
	
	/**
	 *  root 节点目录添加
	 * @param type
	 * @param jTreeModel
	 * @return
	 */
	JTreeModel SaveRootTreeModel(ServiceContext serviceContext,JTreeModel jTreeModel);
	
	/**
	 * 树节点的添加
	 * 有二个添加策略 {@code JTreeModel#getCode()} 如果不为null, 则校验他父节点是否存在是以code 字段
	 * 否则以 id 作为 检查父类节点是否存在
	 * @param serviceContext
	 * @param jTreeModel {@link JTreeModel#getPid() } is not null 
	 * @return
	 */
	JTreeModel SaveNodeTreeModel(ServiceContext serviceContext,JTreeModel jTreeModel);
	
	
	/**
	 * 节点绑定
	 * @param serviceContext
	 * @param jTreeModel
	 * @return
	 */
	Boolean RelevanceTreeModel(ServiceContext serviceContext,JTreeModel jTreeModel);
	
}
