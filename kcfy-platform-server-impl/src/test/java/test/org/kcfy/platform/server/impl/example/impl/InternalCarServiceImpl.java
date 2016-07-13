package test.org.kcfy.platform.server.impl.example.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.org.kcfy.platform.server.impl.example.model.Car;
import test.org.kcfy.platform.server.impl.example.repo.CarRepo;

import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;
import com.kcfy.platform.server.kernal.service.InternalServiceSupport;

@Service
public class InternalCarServiceImpl extends InternalServiceSupport<Car>{

	@Autowired
	CarRepo carRepo;
	
	@Override
	public SingleEntityRepo<Car, String> getRepo() {
		return carRepo;
	}

}
