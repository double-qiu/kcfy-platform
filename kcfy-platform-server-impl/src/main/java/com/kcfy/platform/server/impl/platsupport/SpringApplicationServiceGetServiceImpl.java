package com.kcfy.platform.server.impl.platsupport;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kcfy.platform.server.kernal.service.ServiceSupport;
import com.kcfy.platform.server.kernal.service.SpringApplicationServiceGetService;

@Service
public class SpringApplicationServiceGetServiceImpl extends ServiceSupport implements
		SpringApplicationServiceGetService {

	
	@Override
	public Object getService(Class<?> clazz) {
		Service service=clazz.getAnnotation(Service.class);
		String name=clazz.getSimpleName();
		String serviceName=name.substring(0, 1).toLowerCase()
				+name.substring(1);
		if(service!=null&&!service.value().equals("")){
			serviceName=service.value();
		}
		
		Component component=clazz.getAnnotation(Component.class);
		if(component!=null&&component.value()!=""){
			serviceName=service.value();
		}
		return applicationContext.getBean(serviceName);
	}

	@Override
	public Object getService(String serviceName) {
		return applicationContext.getBean(serviceName);
	}
	
}
