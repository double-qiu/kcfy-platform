package test.org.kcfy.platform.server.impl.example.jpa;

import test.org.kcfy.platform.server.impl.example.model.Park;
import test.org.kcfy.platform.server.impl.example.repo.ParkRepo;

import com.kcfy.platform.server.kernal.springjpa.JSpringJpaRepository;

public interface ParkJPARepo extends ParkRepo,
	JSpringJpaRepository<Park,String> {

}
