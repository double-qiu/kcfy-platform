package test.org.kcfy.platform.server.impl.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.org.kcfy.platform.server.impl.example.model.Car;
import test.org.kcfy.platform.server.impl.example.model.Park;
import test.org.kcfy.platform.server.impl.example.service.ExampleService;
import test.org.kcfy.platform.server.impl.example.vo.CarAddInVO;
import test.org.kcfy.platform.server.impl.example.vo.CarCriteriaInVO;
import test.org.kcfy.platform.server.impl.example.vo.CarDetailOutVO;
import test.org.kcfy.platform.server.impl.example.vo.CarRecordOutVO;
import test.org.kcfy.platform.server.impl.example.vo.ParkAddInVO;
import test.org.kcfy.platform.server.impl.example.vo.ParkCriteriaInVO;
import test.org.kcfy.platform.server.impl.example.vo.ParkRecordOutVO;

import com.kcfy.platform.server.kernal.Copy;
import com.kcfy.platform.server.kernal.mapping.ControllerSupport;
import com.kcfy.platform.server.kernal.model.InvokeResult;
import com.kcfy.platform.server.kernal.model.JPage;
import com.kcfy.platform.server.kernal.model.SimplePageRequest;
import com.kcfy.platform.server.kernal.model.SimplePageRequestVO;
import com.kcfy.platform.server.kernal.service.JServiceLazyProxy;
import com.kcfy.platform.server.kernal.service.ServiceContext;

/**
 * any methods in the controller should not catch/hide any exception. 
 * instead throw any expected/unexpected exception to the caller.
 * <p>1. any method name should be same as the path value , see {@link RequestMapping} 
 * <p>2. all methods should have two method arguments or three only for pagination.
 * @author JIAZJ
 */
@Controller
@RequestMapping("/example")
public class ExampleController extends ControllerSupport {

	private ExampleService exampleService = JServiceLazyProxy.proxy(ExampleService.class);
	
	@ResponseBody
	@RequestMapping("/saveCar")
	public InvokeResult saveCar(ServiceContext serviceContext, CarAddInVO carAddInVO) throws Exception{
		// do something validation on the car or nothing.
		Car car=Copy.simpleCopy(carAddInVO, Car.class);
		exampleService.saveCar(serviceContext, car);
		return InvokeResult.success(car.getId());
	}
	
	@ResponseBody
	@RequestMapping("/getCarById")
	public InvokeResult getCarById(ServiceContext serviceContext, String id) throws Exception{
		// do something validation on the car or nothing.
		Car car=exampleService.getCarById(serviceContext, id);
		CarDetailOutVO carDetailOutVO=null;
		if(car!=null){
			carDetailOutVO=Copy.simpleCopy(car, CarDetailOutVO.class);
		}
		return InvokeResult.success(carDetailOutVO);
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteCarById")
	public InvokeResult deleteCarById(ServiceContext serviceContext, String id) throws Exception{
		// do something validation on the car or nothing.
		exampleService.deleteCarById(serviceContext, id);
		return InvokeResult.success(true);
	}
	
	
	@ResponseBody
	@RequestMapping("/getCarsByPage")
	public InvokeResult getCarsByPage(ServiceContext serviceContext, CarCriteriaInVO carCriteriaInVO,SimplePageRequestVO simplePageRequestVO ) throws Exception{
		// do something validation on the car or nothing.
		JPage<CarRecordOutVO> page=exampleService.getCars(serviceContext, carCriteriaInVO, 
				new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
		return InvokeResult.success(page);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/savePark")
	public InvokeResult savePark(ServiceContext serviceContext, ParkAddInVO parkAddInVO) throws Exception{
		// do something validation on the car or nothing.
		exampleService.savePark(serviceContext, Copy.simpleCopy(parkAddInVO, Park.class));
		return InvokeResult.success(true);
	}
	
	@ResponseBody
	@RequestMapping("/getParksByPage")
	public InvokeResult getParksByPage(ServiceContext serviceContext, ParkCriteriaInVO parkCriteriaInVO,SimplePageRequestVO simplePageRequestVO ) throws Exception{
		// do something validation on the car or nothing.
		JPage<ParkRecordOutVO> page=exampleService.getParks(serviceContext, 
				new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
		return InvokeResult.success(page);
	}
	
	
	
	
	
}
