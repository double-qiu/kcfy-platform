package test.org.kcfy.platform.server.impl.example.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.org.kcfy.platform.server.impl.example.model.Car;
import test.org.kcfy.platform.server.impl.example.model.Park;
import test.org.kcfy.platform.server.impl.example.service.ExampleService;
import test.org.kcfy.platform.server.impl.example.vo.CarCriteriaInVO;
import test.org.kcfy.platform.server.impl.example.vo.CarRecordOutVO;
import test.org.kcfy.platform.server.impl.example.vo.ParkRecordOutVO;

import com.kcfy.platform.common.JStringUtils;
import com.kcfy.platform.server.BusinessException;
import com.kcfy.platform.server.BusinessExceptionUtil;
import com.kcfy.platform.server.kernal.JAssert;
import com.kcfy.platform.server.kernal.model.JPage;
import com.kcfy.platform.server.kernal.model.SimplePageRequest;
import com.kcfy.platform.server.kernal.service.ServiceContext;
import com.kcfy.platform.server.kernal.service.ServiceSupport;
import com.kcfy.platform.server.kernal.service.SingleEntityQueryService;


/**
 * all validation/business message should be thrown via {@link BusinessException},
 * other exceptions exclude the mention above all should be wrapped in the form of "BusinessExceptionUtil.throwException(e);"
 * see {@link BusinessExceptionUtil}.
 * <p> <h1>note: </h1>
 * <p>1.any method should be wrapped in the try part (try{  ...  }) see {@link #saveCar(ServiceContext, Car)}
 * <p>2. any method should not declare any exception in the method signature. 
 * i.e. 'throws' statement NOT exists in the method.
 * @author JIAZJ
 * @see BusinessExceptionUtil
 */
@Service
public class ExampleServiceImpl extends ServiceSupport implements ExampleService{

	@Autowired
	private InternalCarServiceImpl internalCarServiceImpl;
	
	@Autowired
	private InternalParkServiceImpl internalParkServiceImpl;

	@Override
	public void saveCar(ServiceContext serviceContext, Car car) {
		// do something validation on the car or nothing.
		try{
			
			String name=car.getName();
			if(JStringUtils.isNullOrEmpty(name)){
				throw new BusinessException("the car name is missing, invalid data.");
			}
			internalCarServiceImpl.saveOnly(serviceContext, car);
			
			if("_name".equals(name)){
				throw new IllegalStateException("unexpected exception.");
			}
			
		}catch(Exception e){
			BusinessExceptionUtil.throwException(e);
		}
	}

	@Override
	public void updateCar(ServiceContext serviceContext, Car car) {
		// do something validation on the car or nothing.
		internalCarServiceImpl.updateOnly(serviceContext, car);
	}

	@Override
	public void deleteCar(ServiceContext serviceContext, Car car) {
		// do something validation on the car or nothing.
		internalCarServiceImpl.delete(serviceContext, car);
	}

	@Override
	public void deleteCarById(ServiceContext serviceContext, String id) {
		// do something validation on the car or nothing.
		internalCarServiceImpl.delete(serviceContext, id, Car.class);
	}
	
	@Override
	public Car getCarById(ServiceContext serviceContext, String id) {
		return internalCarServiceImpl.getById(serviceContext, id, Car.class);
	}

	@Override
	public JPage<CarRecordOutVO> getCars(ServiceContext serviceContext,CarCriteriaInVO carCriteriaInVO,
			SimplePageRequest simplePageRequest) {
		
		//native sql example
		StringBuilder nativeStringBuilder =new StringBuilder(
				"select a._name as _name , a.id as _id  from t_car a where 1=1 ");
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String name=carCriteriaInVO.getName();
		if(JStringUtils.isNotNullOrEmpty(name)){
			nativeStringBuilder.append(" and a._name like :name");
			parameters.put("name", name);
		}
		
		String brand=carCriteriaInVO.getBrand();
		if(JStringUtils.isNotNullOrEmpty(brand)){
			nativeStringBuilder.append(" and a._brand like :brand");
			parameters.put("brand", brand);
		}
		
		String minPrice=carCriteriaInVO.getMinPrice();
		if(JStringUtils.isNotNullOrEmpty(minPrice)){
			nativeStringBuilder.append(" and a._price > :minPrice");
			parameters.put("minPrice", minPrice);
		}
		
		String maxPrice=carCriteriaInVO.getMaxPrice();
		if(JStringUtils.isNotNullOrEmpty(maxPrice)){
			nativeStringBuilder.append(" and a._price < :maxPrice");
			parameters.put("maxPrice", maxPrice);
		}
		
		JPage<CarRecordOutVO> page = queryBuilder()
			.setNativeSql(nativeStringBuilder.toString())
			.setResultSetMapping("CarRecordOutVOMapping")
			.setParams(parameters)
			.setPageable(simplePageRequest)
			.build()
			.execute();
		return page;
	}

	@Override
	public void savePark(ServiceContext serviceContext, Park park) {
		// do something validation on the car or nothing.
		internalParkServiceImpl.saveOnly(serviceContext, park);
	}

	@Override
	public void updatePark(ServiceContext serviceContext, Park park) {
		// do something validation on the car or nothing.
		internalParkServiceImpl.saveOnly(serviceContext, park);
	}

	@Override
	public void deletePark(ServiceContext serviceContext, Park park) {
		// do something validation on the car or nothing.
		internalParkServiceImpl.delete(serviceContext, park);
	}

	@Override
	public void deleteParkById(ServiceContext serviceContext, String id) {
		// do something validation on the car or nothing.
		internalParkServiceImpl.delete(serviceContext, id, Park.class);
	}
	
	@Override
	public Park getParkById(ServiceContext serviceContext, String id) {
		return internalParkServiceImpl.getById(serviceContext, id, Park.class);
	}

	@Override
	public JPage<ParkRecordOutVO> getParks(ServiceContext serviceContext,
			SimplePageRequest simplePageRequest) {
		
		//JPQL example.
		StringBuilder jpql =new StringBuilder(
				" select new  test.org.kcfy.platform.server.example.vo.ParkRecordOutVO"
						+ "(name,capacity,address) from Park"
				);
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		JPage<ParkRecordOutVO> page = queryBuilder()
			.setJpql(jpql.toString())
			.setParams(parameters)
			.setPageable(simplePageRequest)
			.build()
			.execute();
		return page;
	}
	
	@Override
	public List<Car> getAllCarBySomeProperties(ServiceContext serviceContext) {
		return SingleEntityQueryService.getSingleEntityQuery(Car.class)
		.condition().startLikes("name", "宝马")
		.larger("price", 0.88f)
		.ready().order()
		.asc("createDate").ready()
		.executeList();
	}
	
	@Override
	public JPage<Car> getAllCarBySomeProperties(ServiceContext serviceContext,
			SimplePageRequest simplePageRequest) {
		return internalCarServiceImpl.singleEntityQuery()
				.condition().startLikes("name", "宝马")
				.larger("price", 0.88f)
				.ready().order()
				.asc("createDate").ready()
				.executePageable(simplePageRequest);
	}
	
	@Override
	public int updateCars(ServiceContext serviceContext, String name) {
		return queryBuilder().setJpql("update Car set price=2.0 where name like '%"+name+"%'")
				.setUpdate(true).build().execute();
	}
	
	@Override
	public void sqlExample(ServiceContext serviceContext) {
		try{
			
			List<Car> cars=  internalCarServiceImpl.singleEntityQuery2().condition()
			.startLikes("name", "")
			// .startLikes("name", null) // null is not accepted , use empty string instead of 
			// and if the value is null, the condition be ignored
			.ready().models();
			
			JAssert.isNotNull(cars);
			
			List<Car>  paramCodes= internalCarServiceImpl.singleEntityQuery2()
			.condition().equals("name","宝马").ready().models();
			JAssert.isNotNull(paramCodes);
			
			List<Car> paramCodes1=jpqlQuery()
					.setJpql("from Car s where s.name='宝马'")
			.models();
			JAssert.isNotNull(paramCodes1);
			
			
			List<Car> paramCodes3= jpqlQuery()
					.setJpql("select name as name , brand as brand from Car s where s.name='宝马'")
					.models(Car.class);
			
			JAssert.isNotNull(paramCodes3);
			
			
			List<Map<String, Object>> paramCodes5=jpqlQuery()
					.setJpql("select name as name , brand as brand from Car s where s.name='宝马'")
					.maps();
			
			JAssert.isNotNull(paramCodes5);
			
			
			Map<String, Object> paramCodes6=jpqlQuery()
					.setJpql("select name as name , brand as brand from Car s where s.name='宝马'")
					.setSingle(true)
					.map();
			
			JAssert.isNotNull(paramCodes6);
			
			List<Car> paramCodes4= nativeQuery()
					.setSql("select  a._name as name , a._brand as brand "
							+ " from t_car a where a._name='宝马' ")
					.models(Car.class);
			JAssert.isNotNull(paramCodes4);
			
			Map<String, Object> object=nativeQuery()
					.setSql("select  a._name as name , a._brand as brand "
							+ " from t_car a where a._name='宝马' ")
			.map();
			
			JAssert.isNotNull(object);
			
			List<Map<String, Object>>  object1= nativeQuery()
					.setSql("select  a._name as name , a._brand as brand "
							+ " from t_car a where a._name='宝马' ")
			.maps();
			
			JAssert.isNotNull(object1);
			
			
			SimplePageRequest simplePageRequest=new SimplePageRequest(0,12);
			JPage<Car> paramCodePage= internalCarServiceImpl.singleEntityQuery2()
					.conditionDefault().equals("name","宝马").ready().modelPage(simplePageRequest);
			
			JAssert.isNotNull(paramCodes);
			JAssert.isNotNull(paramCodePage);
			
			Object afc=jpqlQuery()
			.setJpql("update Car s  set s.price ='88' where s.name='宝马' ")
			.setUpdate(true)
			.model();
			
			JAssert.isNotNull(afc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
}
