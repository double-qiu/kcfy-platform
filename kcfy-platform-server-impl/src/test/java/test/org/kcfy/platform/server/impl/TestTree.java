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
import com.kcfy.platform.server.tree.serivce.JTreeModelService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestTree {


	
	@Autowired
	private JTreeModelService jTreeModelService;
	
	
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
    	
    	jTreeModelService.SaveRootTreeModel(serviceContext, null);
    	
    }
    
    
    
    @Test
    public void testGetCar(){
    	
    	
    	
    }
    
    
    
    
    
    
    
}
