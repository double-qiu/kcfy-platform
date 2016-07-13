/**
 * 
 */
package com.kcfy.platform.server.kernal.parameter.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcfy.platform.server.kernal.model.InvokeResult;
import com.kcfy.platform.server.kernal.parameter.util.ValidationHelper;
import com.kcfy.platform.server.kernal.parameter.validation.SingleViolationObject;
import com.kcfy.platform.server.kernal.parameter.validation.ValidationViolation;

/**
 * @author zhengzw
 *
 */
@Aspect
@Component
public class JSpringAspect {

	private static final Logger logger = LoggerFactory.getLogger(JSpringAspect.class);

	/**
	 * 定义切点
	 */
	@Pointcut("@within(com.kcfy.platform.server.kernal.parameter.annotation.ParamValidation4Controller)")
	public void validation() {

	}

	@Around(value = "validation()")
	public Object AroundLog(ProceedingJoinPoint pjd) {
		Object rtnObject = null;

		try {
			Object[] objects = pjd.getArgs();

			List<ValidationViolation> list = new ArrayList<ValidationViolation>();
			ValidationViolation vv = null;
			
			for (Object object : objects) {
				SingleViolationObject svo = ValidationHelper.validate(object);
				
				if(!svo.isSuccess()){
					vv = new ValidationViolation(object.getClass().getName());
					vv.setErrMsg(svo.getMessage());
					
					list.add(vv);
				}
			}
			
			if(!list.isEmpty()) {
				ObjectMapper om = new ObjectMapper();
				rtnObject = InvokeResult.failure(om.writeValueAsString(list));
				
				return rtnObject;
			}
			
			rtnObject = pjd.proceed();
			
			return rtnObject;
			
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}

		return rtnObject;
	}
}
