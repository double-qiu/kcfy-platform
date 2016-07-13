package test.org.kcfy.platform.server.impl.example.service;

import java.util.List;

import test.org.kcfy.platform.server.impl.example.model.Car;
import test.org.kcfy.platform.server.impl.example.model.Park;
import test.org.kcfy.platform.server.impl.example.vo.CarCriteriaInVO;
import test.org.kcfy.platform.server.impl.example.vo.CarRecordOutVO;
import test.org.kcfy.platform.server.impl.example.vo.ParkRecordOutVO;

import com.kcfy.platform.server.kernal.model.JPage;
import com.kcfy.platform.server.kernal.model.SimplePageRequest;
import com.kcfy.platform.server.kernal.service.ServiceContext;
import com.kcfy.platform.server.kernal.springjpa.query.JSingleEntityQuery;

public interface ExampleService {

	void saveCar(ServiceContext serviceContext,Car car);
	
	void updateCar(ServiceContext serviceContext,Car car);
	
	void deleteCar(ServiceContext serviceContext,Car car);
	
	void deleteCarById(ServiceContext serviceContext,String id);
	
	Car getCarById(ServiceContext serviceContext,String id);
	
	JPage<CarRecordOutVO> getCars(ServiceContext serviceContext,CarCriteriaInVO carCriteriaInVO, SimplePageRequest simplePageRequest);
	
	void savePark(ServiceContext serviceContext,Park park);
	
	void updatePark(ServiceContext serviceContext,Park park);
	
	void deletePark(ServiceContext serviceContext,Park park);
	
	void deleteParkById(ServiceContext serviceContext,String id);
	
	Park getParkById(ServiceContext serviceContext,String id);

	JPage<ParkRecordOutVO> getParks(ServiceContext serviceContext,SimplePageRequest simplePageRequest);
	
	List<Car> getAllCarBySomeProperties(ServiceContext serviceContext);

	JPage<Car> getAllCarBySomeProperties(ServiceContext serviceContext,SimplePageRequest simplePageRequest);

	int updateCars(ServiceContext serviceContext,String name);

	void sqlExample(ServiceContext serviceContext);
	
	
}
