/**
 * 
 */
package com.kcfy.platform.server.kernal.parameter.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;

import com.kcfy.platform.server.kernal.parameter.validation.SingleViolationObject;

/**
 * @author zhengzw
 *
 */
public class ValidationHelper {
	
	/**
	 * 验证对象帮助类
	 * @param object
	 */
	public static SingleViolationObject validate(Object object) {
		if(null == object) {
			return SingleViolationObject.failures("参数不允许为空!");
		}
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> failurles = validator.validate(object);
		
		if(null != failurles && !failurles.isEmpty()){
			String message = StringUtils.EMPTY;
			
			for(ConstraintViolation<Object> cv : failurles) {
				message += StringUtils.isEmpty(message) ? "<" + cv.getMessage() + ">" : ",<" + cv.getMessage() + ">";
			}
			
			return SingleViolationObject.failures(message);
		}
		
		return SingleViolationObject.success();
	}
}
