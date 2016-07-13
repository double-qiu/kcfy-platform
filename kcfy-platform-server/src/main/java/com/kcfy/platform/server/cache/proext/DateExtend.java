package com.kcfy.platform.server.cache.proext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateExtend {

	/**
	 * reference to another property, they must be included in the same class.
	 * i.e. if the referenced property appears in the super class, then an exception should be thrown to
	 * the caller. 
	 * @return
	 */
	String property();
	
	/**
	 * the format of date default "yyyy-MM-dd HH:mm:ss" 
	 * @return
	 */
	String format() default "";
	
	boolean active() default true;
}
