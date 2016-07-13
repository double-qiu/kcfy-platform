package test.org.kcfy.platform.server.impl.example.jpa;

import test.org.kcfy.platform.server.impl.example.model.Car;
import test.org.kcfy.platform.server.impl.example.repo.CarRepo;

import com.kcfy.platform.server.kernal.springjpa.JSpringJpaRepository;

public interface CarJPARepo extends CarRepo,
	JSpringJpaRepository<Car,String> {

}
