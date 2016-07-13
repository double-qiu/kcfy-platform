package com.kcfy.platform.server.kernal.springjpa.query;



import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Parameter;
import javax.persistence.Query;

import com.kcfy.platform.server.kernal.JAssert;
import com.kcfy.platform.server.kernal.model.JImpl;
import com.kcfy.platform.server.kernal.model.JPage;
import com.kcfy.platform.server.kernal.model.JPageable;
import com.kcfy.platform.server.kernal.model.JSimplePageImpl;
import com.kcfy.platform.server.kernal.model.SimplePageRequest;

public abstract class QueryExecution {
	
	protected QueryMeta queryMeta;
	
	public QueryExecution(QueryMeta queryMeta) {
		this.queryMeta = queryMeta;
	}


	public <T> T execute(){
		T result=null;
		try {
			result = doExecute();
		} catch (NoResultException e) {
			return null;
		}
		return result;
	}
	
	
	protected abstract <T> T doExecute();
	
	
	public static void setQueryParameters(Query query, Map<?, Object> params) {
		
		Set<Parameter<?>> sqlParams=query.getParameters();
		
		for (Parameter<?> param : sqlParams){
			String paramName=param.getName();
			Object value=params.get(paramName);
			
			if(JpaDateParam.class.isInstance(value)){
				JpaDateParam jpaDateParam= (JpaDateParam) value;
				if(String.class.isInstance(paramName)){
					query.setParameter((String)paramName, jpaDateParam.getDate(), jpaDateParam.getTemporalType());
				}
				else if(Integer.class.isInstance(paramName)){
//					query.setParameter((Integer)paramName, jpaDateParam.getDate(), jpaDateParam.getTemporalType());
				}
			}
			else if(JpaCalendarParam.class.isInstance(value)){
				JpaCalendarParam jpaCalendarParam= (JpaCalendarParam) value;
				if(String.class.isInstance(paramName)){
					query.setParameter((String)paramName, jpaCalendarParam.getCalendar(), jpaCalendarParam.getTemporalType());
					
				}
				else if(Integer.class.isInstance(paramName)){
//					query.setParameter((Integer)paramName, jpaCalendarParam.getCalendar(), jpaCalendarParam.getTemporalType());
				}
			}
			else{
				if(String.class.isInstance(paramName)){
					query.setParameter((String)paramName, value);
					
				}
				else if(Integer.class.isInstance(paramName)){
//					query.setParameter((Integer)paramName, value);
				}
			}
		}
	}
	
	static class SingleExecution extends QueryExecution{
		public SingleExecution(QueryMeta queryMeta) {
			super(queryMeta);
		}

		@Override
		protected Object doExecute() {
			Query query=queryMeta.getQuery();
			QueryExecution.setQueryParameters(query, queryMeta.getParams());
			Object object= query.getSingleResult();
			Class<?> result=queryMeta.getResult();
			return result==null?object:result.cast(object);
		}
	}
	
	static class ListExecution extends QueryExecution{
		public ListExecution(QueryMeta queryMeta) {
			super(queryMeta);
		}

		@Override
		protected List<?> doExecute() {
			Query query=queryMeta.getQuery();
			QueryExecution.setQueryParameters(query, queryMeta.getParams());
			return query.getResultList();
		}
	}
	
	
	static class PagedExecution extends QueryExecution {
		
		public PagedExecution(QueryMeta queryMeta) {
			super(queryMeta);
		}

		@Override
		protected JPage doExecute() {
			JPageable pageable=queryMeta.getPageable();
			Query countQuery=queryMeta.getCountQuery();
			JAssert.isNotNull(countQuery, "count query not found.");
			QueryExecution.setQueryParameters(countQuery, queryMeta.getParams());
			long count=0;
			Object obj=countQuery.getSingleResult();
			if(BigInteger.class.isInstance(obj)){
				((BigInteger)obj).longValue();
			}
			else{
				count=Long.valueOf(obj.toString());
			}
			int pageNumber=pageable.getPageNumber();
			int pageSize=pageable.getPageSize();
			int tempTotalPageNumber=JImpl.caculateTotalPageNumber(count, pageSize);
			pageNumber=pageNumber>tempTotalPageNumber?tempTotalPageNumber:pageNumber;
			
			Query query=queryMeta.getQuery();
			QueryExecution.setQueryParameters(query, queryMeta.getParams());
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
			List list= query.getResultList();
			JImpl page=new JImpl();
			page.setContent(list);
			page.setTotalRecordNumber(count);
			page.setTotalPageNumber(tempTotalPageNumber);
			SimplePageRequest pageRequest=new SimplePageRequest(pageNumber, pageable.getPageSize());
			page.setPageable(pageRequest);
			
			JSimplePageImpl simplePageImpl=new JSimplePageImpl();
			simplePageImpl.setStart(queryMeta.getPageable().getPageNumber()*page.getPageable().getPageSize());
			simplePageImpl.setPageSize(page.getPageable().getPageSize());
			simplePageImpl.setResultCount(page.getTotalRecordNumber());
			simplePageImpl.setPageIndex(page.getPageable().getPageNumber());
			simplePageImpl.setData(page.getContent());
			return simplePageImpl;
		}
	} 
	
	static class UpdateExecution extends QueryExecution{
		public UpdateExecution(QueryMeta queryMeta) {
			super(queryMeta);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Object doExecute() {
			Query query=queryMeta.getQuery();
			setQueryParameters(query, queryMeta.getParams());
			return query.executeUpdate();
		}
	}
	
}
