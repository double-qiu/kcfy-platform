package com.kcfy.platform.server.tree.model;

import com.kcfy.platform.server.kernal.model.JModel;

public interface JTree  extends JModel{

	/**
	 *  id
	 * @return
	 */
	String getId();
	/**
	 * 应用实体Id
	 * @return
	 */
	String getEntityId();
	/**
	 * 父类Id
	 * @return
	 */
	String getPid();
	/**
	 * 节点内排序
	 * @return
	 */
	Integer getSequence();
	
	/**
	 * 是否展开
	 * @return
	 */
	boolean isExpand();
	
	
	
}
