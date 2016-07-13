package com.kcfy.platform.server.cache.proext;

import java.lang.reflect.Field;

import com.kcfy.platform.server.cache.CodeRefCacheModel;
import com.kcfy.platform.server.cache.CodeRefCacheService;
import com.kcfy.platform.server.kernal.service.JServiceLazyProxy;


public class CodeExtendBinder implements Binder {

	private CodeRefCacheService<CodeRefCacheModel> codeRefCacheService=
			JServiceLazyProxy.proxy(CodeRefCacheService.class);
	
	@Override
	public void bind(Field field, Object object) {
		try{
			CodeExtend codeExtend= field.getAnnotation(CodeExtend.class);
			if(codeExtend!=null&&codeExtend.active()){
				field.setAccessible(true);
				Field propertyField = field.getDeclaringClass().getDeclaredField(codeExtend.property());
				propertyField.setAccessible(true);
				Object code=propertyField.get(object);
				String codeType=codeExtend.codeType();
				Object value=codeRefCacheService.getName(codeType, (String) code);
				field.set(object, value);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	

}
