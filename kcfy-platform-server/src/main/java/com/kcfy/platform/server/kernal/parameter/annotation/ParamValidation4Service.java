/**
 * 
 */
package com.kcfy.platform.server.kernal.parameter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhengzw
 * 此注解仅用在controller上,
 * 要求所有的controller均返回InvokeResult
 * 
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValidation4Service {
	
}
