package test.org.kcfy.platform.server.impl.example.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.org.kcfy.platform.server.impl.example.model.Park;
import test.org.kcfy.platform.server.impl.example.repo.ParkRepo;

import com.kcfy.platform.server.kernal.repo.SingleEntityRepo;
import com.kcfy.platform.server.kernal.service.InternalServiceSupport;

@Service
public class InternalParkServiceImpl extends InternalServiceSupport<Park>{

	@Autowired
	ParkRepo parkRepo;
	
	@Override
	public SingleEntityRepo<Park, String> getRepo() {
		return parkRepo;
	}

}
