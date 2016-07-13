package com.kcfy.platform.server.cache.proext;

import java.lang.reflect.Field;

public interface Binder {

	
	void bind(Field field,Object object);
	
}
