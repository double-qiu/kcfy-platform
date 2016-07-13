/**
 * 
 */
package com.kcfy.platform.server.kernal.model;

/**
 * 树结构接口定义
 * @author xiongp
 */
public interface ITree extends JModel{

	String getId();

	String getName();

	String getParentId();

	Integer getSequence();
}
