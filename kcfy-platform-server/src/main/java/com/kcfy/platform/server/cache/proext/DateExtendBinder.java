package com.kcfy.platform.server.cache.proext;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExtendBinder implements Binder{

	@Override
	public void bind(Field field, Object object) {
		try {
			DateExtend dateExtend = field.getAnnotation(DateExtend.class);
			if(dateExtend != null && dateExtend.active()){
				field.setAccessible(true);
				Field propertyField = field.getDeclaringClass().getDeclaredField(dateExtend.property());
				propertyField.setAccessible(true);
				Object date = propertyField.get(object);
				String format = dateExtend.format();
				if(date!=null){
					Object value = new SimpleDateFormat(format).format((Date)date);
					field.set(object, value);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
