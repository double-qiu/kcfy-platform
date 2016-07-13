package test.org.kcfy.platform.server.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.org.kcfy.platform.server.impl.example.controller.ExampleController;
import test.org.kcfy.platform.server.impl.example.model.Car;
import test.org.kcfy.platform.server.impl.example.service.ExampleService;
import test.org.kcfy.platform.server.impl.example.vo.CarAddInVO;
import test.org.kcfy.platform.server.impl.example.vo.CarCriteriaInVO;
import test.org.kcfy.platform.server.impl.example.vo.CarDetailOutVO;
import test.org.kcfy.platform.server.impl.example.vo.CarRecordOutVO;

import com.kcfy.platform.common.JJSON;
import com.kcfy.platform.server.kernal.JAssert;
import com.kcfy.platform.server.kernal.model.InvokeResult;
import com.kcfy.platform.server.kernal.model.JPage;
import com.kcfy.platform.server.kernal.model.SessionUser;
import com.kcfy.platform.server.kernal.model.SimplePageRequest;
import com.kcfy.platform.server.kernal.model.SimplePageRequestVO;
import com.kcfy.platform.server.kernal.service.ServiceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestExample {

	@Autowired
	private ExampleController exampleController;
	
	@Autowired
	private ExampleService exampleService;
	
	
	ServiceContext serviceContext=null;
    @Before
    public void before(){
        serviceContext=new ServiceContext();
        SessionUser sessionUser=new SessionUser();
        sessionUser.setId("SYS-TEST");
        sessionUser.setUserName("SYS-TEST");
        serviceContext.setUser(sessionUser);
    }
    
    @Test
    public void testCar(){
    	try{
    		CarAddInVO carAddInVO=new CarAddInVO();
        	carAddInVO.setName("宝马");
        	carAddInVO.setBrand("宝马");
        	carAddInVO.setPrice(0.99f);
        	exampleController.saveCar(serviceContext, carAddInVO);
        	
        	CarCriteriaInVO carCriteriaInVO=new CarCriteriaInVO();
        	SimplePageRequestVO simplePageRequestVO=new SimplePageRequestVO();
        	simplePageRequestVO.setPage(0);
        	simplePageRequestVO.setSize(10);
        	InvokeResult invokeResult=  exampleController.getCarsByPage(serviceContext, carCriteriaInVO, simplePageRequestVO);
        	String carId=null;
        	if(invokeResult.isSuccess()){
        		JPage<CarRecordOutVO> page=getObject(invokeResult, JPage.class) ;
        		List<CarRecordOutVO>  carRecordOutVOs=page.getContent();
        		for(CarRecordOutVO carRecordOutVO:carRecordOutVOs){
        			System.out.println(JJSON.get().formatObject(carRecordOutVO));
        			carId=carRecordOutVO.getId();
        		}
        	}
        	
        	InvokeResult invokeResult2= exampleController.getCarById(serviceContext, carId);
        	CarDetailOutVO carDetailOutVO= getObject(invokeResult2, CarDetailOutVO.class);
        	Assert.assertNotNull(carDetailOutVO);
        	
        	exampleController.deleteCarById(serviceContext, carId);
        	
        	invokeResult2= exampleController.getCarById(serviceContext, carId);
        	carDetailOutVO= getObject(invokeResult2, CarDetailOutVO.class);
        	Assert.assertNotNull(carDetailOutVO);
        	
        	Assert.assertTrue(true);
        	
        	List<Car> cars=exampleService.getAllCarBySomeProperties(serviceContext);
        	
        	JPage<Car> carPage=exampleService.getAllCarBySomeProperties(serviceContext, new SimplePageRequest(0, 10));
        	
        	Assert.assertNotNull(cars);
        	Assert.assertNotNull(carPage);
        	
        	
        	int afc=exampleService.updateCars(serviceContext, "宝马");
        	
        	exampleService.sqlExample(serviceContext);
        	
        	JAssert.isTrue(afc>0);
        	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    private <T> T getObject(InvokeResult invokeResult,Class<T> clazz){
    	if(invokeResult.isSuccess()){
    		return (T) invokeResult.getData();
    	}
    	return null;
    }
    
    
    @Test
    public void testGetCar(){
    	
    	try{
    		String carId="2fa3295f-6186-47f1-a9cc-0b7bec3c2f3a";
    		InvokeResult invokeResult2= exampleController.getCarById(serviceContext, carId);
        	CarDetailOutVO carDetailOutVO= getObject(invokeResult2, CarDetailOutVO.class);
        	Assert.assertNotNull(carDetailOutVO);
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    	}
    	
    }
    
    
    
    
    
    
    
}
