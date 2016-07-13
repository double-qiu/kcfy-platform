package com.kcfy.platform.server.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.kcfy.platform.server.cache.proext.Binder;
import com.kcfy.platform.server.cache.proext.CodeExtendBinder;
import com.kcfy.platform.server.cache.proext.DateExtendBinder;
import com.kcfy.platform.server.cache.proext.PropertyExtendable;

public class SimplePropertyExtendHandler {

	private int potentialCount;
	private int maxCaculCount=Integer.MAX_VALUE;
	
	public final void handle(Object object) {
		getHandler(object).doHandle(object);
	}
	
	protected void doHandle(Object obj){
		potentialCount++;
		if(potentialCount>maxCaculCount){
			throw new RuntimeException("the object is larger...,the data bind occurs this<-");
		}
	}
	
	private static final MapPropertyHandler DO_MAP_PROPERTY_HANDLER=new MapPropertyHandler();
	
	static class MapPropertyHandler extends SimplePropertyExtendHandler{
		
		@SuppressWarnings("rawtypes")
		@Override
		protected void doHandle(Object object) {
			super.doHandle(object);
			Map map=(Map) object;
			for(Object obj: map.values()){
				getHandler(obj).doHandle(obj);
			}
		}
	}
	
	private static final CollectionPropertyHandler DO_COLLECTION_PROPERTY_HANDLER=new CollectionPropertyHandler();
	static class CollectionPropertyHandler extends SimplePropertyExtendHandler{
		@SuppressWarnings("rawtypes")
		@Override
		protected void doHandle(Object object) {
			super.doHandle(object);
			Collection collection=(Collection) object;
			for(Object obj: collection){
				getHandler(obj).doHandle(obj);
			}
		}
	}
	
	private static final SimplePropertyExtendHandler DO_NOTHING_PROPERTY_EXTEND_HANDLER=
			new SimplePropertyExtendHandler(){
		protected void doHandle(Object obj) {};
	};
	
	private static final List<Binder> BINDERS=new ArrayList<Binder>(){
		{
			add(new CodeExtendBinder());
			add(new DateExtendBinder());
		}
	};
	
	private static final SimplePropertyExtendHandler DO_BIND_PROPERTY_EXTEND_HANDLER=
			new SimplePropertyExtendHandler(){
		protected void doHandle(Object obj) {
			try{
				super.doHandle(obj);
				
				Class<?> clazz=obj.getClass();
				
				while(clazz!=null){
					Field[] fields=clazz.getDeclaredFields();
					if(fields.length>0){
						for(Field field:fields){
							field.setAccessible(true);
							for(Binder binder:BINDERS){
								binder.bind(field, obj);
							}
							Object inObj=field.get(obj);
							if(inObj==null){
								if(field.getType()==String.class){
									field.set(obj, "");
								}
							}
							getHandler(inObj).doHandle(inObj);
						}
					}
					clazz=clazz.getSuperclass();
				}
				
				
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	};
	
	static SimplePropertyExtendHandler getHandler(Object object){
		
		if(PropertyExtendable.class.isInstance(object)){
			return DO_BIND_PROPERTY_EXTEND_HANDLER;
		}
		if(Map.class.isInstance(object)){
			return DO_MAP_PROPERTY_HANDLER;
		}
		
		if(Collection.class.isInstance(object)){
			return DO_COLLECTION_PROPERTY_HANDLER;
		}
		return DO_NOTHING_PROPERTY_EXTEND_HANDLER;
	}
	
	
	
	
}
