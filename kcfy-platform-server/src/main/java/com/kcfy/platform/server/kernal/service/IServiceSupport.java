package com.kcfy.platform.server.kernal.service;

import java.io.Serializable;
import java.util.List;

import com.kcfy.platform.server.kernal.model.AbstractEntity;
import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;
import com.kcfy.platform.server.kernal.springjpa.JSpringJpaRepository;

/**
 * Created by J on 2016/3/9.
 */
public interface IServiceSupport<T extends AbstractEntity,ID extends Serializable> {
	void saveOnly(ServiceContext context, T object);

	void updateOnly(ServiceContext context, T object);

	void delete(ServiceContext context, String id,Class<?>... entryClass);
	
	void delete(ServiceContext context, T model);

	T getById(ServiceContext context, String id,Class<?>... entryClass);

	public void deleteAllByIds(ServiceContext context, List<String> ids,Class<?>... entryClass);
	
	public void deleteAllByModels(ServiceContext context, List<T> models,Class<?>... entryClass);
	
	public void deletePermanentlyByModels(ServiceContext serviceContext,List<T> models,Class<?>... entryClass);
	
	public List<T> getAllModes(ServiceContext serviceContext,Class<?>... entryClass);
	
	public void deletePermanently(ServiceContext serviceContext,T model);
	
	/**
	 * {@inheritDoc}
	 */
//	@Override
//	public JPage<T> getsByPage(ServiceContext context, JPageable pagination) {
//		JPageImpl<T> page=new JPageImpl<T>();
//		List<T> records=getRepo().getModelsByPage(pagination);
//		page.setPageable(pagination);
//		page.setTotalRecordNumber(records.size());
//		page.setContent(records);
//		page.setTotalPageNumber(JPageImpl.caculateTotalPageNumber(records.size(), pagination.getPageSize()));
//		return page;
//	}

	SingleEntityRepo<T,ID> getRepo();
	
	void saveAllOnly(ServiceContext context, Iterable<T> objects,Class<?>... entryClass);

}
