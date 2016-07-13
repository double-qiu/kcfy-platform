package com.kcfy.platform.server.kernal.service;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kcfy.platform.server.kernal.model.AbstractEntity;
import com.kcfy.platform.server.kernal.model.Availability;
import com.kcfy.platform.server.kernal.model.JPageable;
import com.kcfy.platform.server.kernal.model.SessionUser;
import com.kcfy.platform.server.kernal.model.SimplePageRequest;
import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;
import com.kcfy.platform.server.kernal.springjpa.query.JSingleEntityQuery;

/**
 * delegate service operation of a certain table, 
 * <p>include insert, update, delete(default set "DELETE" as "Y" ), get(one record according)
 * <p>sub-class should implements method of {@code getRepo()} .
 * @author J
 *
 * @param <T>
 */
public class InternalServiceSupport<T extends AbstractEntity> extends SpringServiceFactorySupport implements IServiceSupport<T,String> {
	
	protected final Logger LOGGER= LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager em;
	
	protected Class<?> entityClass=null;
	
	public InternalServiceSupport() {
		ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass=(Class<T>) type.getActualTypeArguments()[0];
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOnly(ServiceContext context, T object) {
		LOGGER.info("context",context);
		LOGGER.info("object",object);
		proxyOnSave(getRepo(), context.getUser(), object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateOnly(ServiceContext context, T object){
		LOGGER.info("context",context);
		LOGGER.info("object",object);
		proxyOnUpdate(getRepo(), context.getUser(), object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ServiceContext context, String id,Class<?>... entryClass) {
		LOGGER.info("context",context);
		LOGGER.info("id",id);
		T abstractEntity= getRepo().getModel(id, entryClass);
		abstractEntity.setIsAvailable(Availability.unavailable);
		updateOnly(context, abstractEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getById(ServiceContext context, String id,Class<?>... entryClass) {
		LOGGER.info("context",context);
		LOGGER.info("id",id);
		return getRepo().getModel(id, entryClass);
	}


	/**
	 * fill in common info.  to execute 
	 * @param authorizer    generally its login user
	 */
	private T proxyOnSave(SingleEntityRepo<T , ?> repo, SessionUser authorizer, T baseModel){
		LOGGER.info("repo",repo);
		LOGGER.info("authorizer",authorizer);
		LOGGER.info("baseModel",baseModel);
		baseModel.setVersion(0);
		baseModel.setCreatorId(authorizer.getId());
		baseModel.setCreateDate(new Date());
		baseModel.setModifierId(authorizer.getId());
		baseModel.setModifyDate(new Date());
		baseModel.setIsAvailable(Availability.available);
		repo.saveModel(baseModel);
		return baseModel;
	}
	
	/**
	 * fill in common info.
	 * also validate whether the version changes, then to execute 
	 */
	private T proxyOnUpdate(SingleEntityRepo<T, ?> repo, SessionUser authorizer, T baseModel){
		LOGGER.info("repo",repo);
		LOGGER.info("authorizer",authorizer);
		LOGGER.info("baseModel",baseModel);
		baseModel.setModifierId(authorizer.getId());
		baseModel.setModifyDate(new Date());
		repo.saveModel(baseModel);
		return baseModel;
	}
	


	protected SimplePageRequest toPageRequest(JPageable pageable){
		LOGGER.info("pageable",pageable);
		return new SimplePageRequest(pageable.getPageNumber(), pageable.getPageSize());
	}
	
	public void deleteAllByIds(ServiceContext context, List<String> ids,Class<?>... entryClass) {
		LOGGER.info("context",context);
		LOGGER.info("ids",ids);
		for(String id : ids){
			delete(context, id,entryClass);
		}
	}
	
	@Override
	public void deleteAllByModels(ServiceContext context, List<T> models,Class<?>... entryClass) {
		LOGGER.info("context",context);
		LOGGER.info("models",models);
		for(T model:models){
			delete(context, model.getId());
		}
	}
	
	@Override
	public void delete(ServiceContext context, T model) {
		LOGGER.info("context",context);
		LOGGER.info("model",model);
		delete(context, model.getId());
	}
	
	
	public List<T> getAllModes(ServiceContext serviceContext,Class<?>... entryClass){
		LOGGER.info("serviceContext",serviceContext);
		return (List<T>) getRepo().getAllModels(entryClass);
	}
	
	/**
	 * 物理删除
	 * @param serviceContext
	 * @param model
	 */
	public void deletePermanently(ServiceContext serviceContext,T model){
		getRepo().deleteModel(model);
	}
	
	/**
	 * 批量物理删
	 * @param serviceContext
	 * @param models
	 */
	public void deletePermanentlyByModels(ServiceContext serviceContext,List<T> models,Class<?>... entryClass){
		for(T model : models){
			deletePermanently(serviceContext, model);
		}
	}
	
	@Override
	public void saveAllOnly(ServiceContext context, Iterable<T> objects,Class<?>... entryClass) {
		getRepo().saveAllModels(objects, entryClass);
	}
	
	/**
	 * override the method to provide the real repository.
	 */
	@Override
	public SingleEntityRepo<T, String> getRepo() {
		return null;
	}
	
	@Override
	protected boolean isCanRegister() {
		return false;
	}
	
	/**
	 * use {@link #singleEntityQuery2()} instead of this.
	 * @return
	 */
	@Deprecated
	public JSingleEntityQuery singleEntityQuery(){
		return new JSingleEntityQuery(entityClass, em);
	}
	
	public com.kcfy.platform.server.kernal.springjpa.query2.JSingleEntityQuery singleEntityQuery2(){
		return new com.kcfy.platform.server.kernal.springjpa.query2.JSingleEntityQuery(entityClass, em);
	}
	
}
