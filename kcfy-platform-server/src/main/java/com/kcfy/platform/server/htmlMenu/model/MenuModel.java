package com.kcfy.platform.server.htmlMenu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kcfy.platform.server.kernal.model.AbstractEntity;

/**
 *  
 * @author 张军
 *
 */
@Entity
@Table(name = "t_menumodel")
public class MenuModel  extends AbstractEntity{

	private static final long serialVersionUID = 2876476718612397022L;

	/**
	 * 菜单类型 MENU_RESOURCE PAGE_ELEMENT_RESOURCE
	 */
	@Column(name = "category")
	private String cateGory;
	/**
	 * 树节点ID
	 */
	@Column(name = "tid")
	private String tid;
	/**
	 *菜单描述 
	 *@example 通用查询配置、数据源查询配置
	 */
	@Column(name ="description")
	private String description;
	
	/**
	 *菜单 名称  
	 *@example 用户管理-添加
	 */
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "menu_icon")
	private  String menuIcon;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "layoutId")
	private String layoutId;

	@Column(name ="relevance")
	private boolean relevance = false;
	public String getCateGory() {
		return cateGory;
	}

	public void setCateGory(String cateGory) {
		this.cateGory = cateGory;
	}


	public boolean isRelevance() {
		return relevance;
	}

	public void setRelevance(boolean relevance) {
		this.relevance = relevance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	
	
}
