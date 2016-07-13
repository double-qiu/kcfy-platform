package com.kcfy.platform.server.impl.tree.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.server.BusinessExceptionUtil;
import com.kcfy.platform.server.kernal.service.ServiceContext;
import com.kcfy.platform.server.kernal.service.ServiceSupport;
import com.kcfy.platform.server.tree.hepler.TreeTypeEnum;
import com.kcfy.platform.server.tree.model.JTreeModel;
import com.kcfy.platform.server.tree.serivce.JTreeModelService;

@Service
public class JTreeModelServiceImpl extends ServiceSupport implements JTreeModelService{

	@Autowired
	InternalJTreeModelServiceImpl internalJTreeModelServiceImpl;

	@Override
	public List<JTreeModel> getTreeModelsByJTreeType(ServiceContext serviceContext, TreeTypeEnum type) {
		List<JTreeModel> models = internalJTreeModelServiceImpl.singleEntityQuery2().condition().equals("treetype",type.getValue()).ready().models();
		return models;
	}


	@Override
	public JTreeModel SaveNodeTreeModel(ServiceContext serviceContext, JTreeModel jTreeModel) {
	
		if( JStringUtils.isNullOrEmpty(jTreeModel.getPid())){
			BusinessExceptionUtil.throwException(new Exception("数据模型错误  pid 不可以同时为 null"));
		}
			
			if(!checkTreeModelByTreeType(jTreeModel.getTreeType(),jTreeModel.getCode(),jTreeModel.getId())){
				BusinessExceptionUtil.throwException(new Exception("父节点不存在"));	
		}
		internalJTreeModelServiceImpl.saveOnly(serviceContext, jTreeModel);
		
		return jTreeModel;
	}
	
	
	@Override
	public JTreeModel SaveRootTreeModel(ServiceContext serviceContext, JTreeModel jTreeModel) {
		try {
			
			if(checkTreeModelByTreeType(jTreeModel.getTreeType(),null,null)){
				internalJTreeModelServiceImpl.saveOnly(serviceContext, jTreeModel);
				return jTreeModel;
			}else{
				BusinessExceptionUtil.throwException(new Exception(jTreeModel.getTreeType().getValue()+" root JsonTree is there in db"));
			}
			return jTreeModel;
		} catch (Exception e) {
			BusinessExceptionUtil.throwException(e);
		}
		return jTreeModel;
		}
		
	@Override
	public Boolean RelevanceTreeModel(ServiceContext serviceContext, JTreeModel jTreeModel) {
		try {
			if(JStringUtils.isNullOrEmpty(jTreeModel.getId())||JStringUtils.isNullOrEmpty(jTreeModel.getEntityId())){
				return false;
			}
			JTreeModel dbSource = internalJTreeModelServiceImpl.getById(serviceContext, jTreeModel.getId(), JTreeModel.class);
			dbSource.setEntityId(jTreeModel.getEntityId());
			internalJTreeModelServiceImpl.updateOnly(serviceContext, dbSource);
			return true;
		} catch (Exception e) {
			BusinessExceptionUtil.throwException(e);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param treeType
	 * @param code
	 * @param pid
	 * @return
	 */
	private boolean checkTreeModelByTreeType(TreeTypeEnum treeType,String code,String pid){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select count(0)  from  t_jTreeModel t where 1=1");
		if(JStringUtils.isNotNullOrEmpty(treeType.getValue())){
			sb.append(" t.treetype ='"+treeType.getValue()+"'");
		}
		if(JStringUtils.isNotNullOrEmpty(code)){
			sb.append(" t.code = '"+code+"'");
		}
		else if(JStringUtils.isNotNullOrEmpty(pid)){
			sb.append(" t.id = '"+pid+"'");
		}
		
		Integer model = nativeQuery().setSql(sb.toString()).setSingle(true).model();
		if(model>0){
			return false;
		}
		return true;
	}






	
	
}
