package com.kcfy.platform.server.kernal.springjpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kcfy.platform.server.kernal.model.AbstractEntity;
import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;

import java.io.Serializable;

@NoRepositoryBean
public interface JSpringJpaRepository<T extends AbstractEntity,ID extends Serializable>
	extends PagingAndSortingRepository<T, ID>,SingleEntityRepo<T, ID>{
	
//	@Override
//	@Query(value="select t from #{#entityName} t")
//	public List<T> getModelsByPage(JPagination pagination);
	
}
