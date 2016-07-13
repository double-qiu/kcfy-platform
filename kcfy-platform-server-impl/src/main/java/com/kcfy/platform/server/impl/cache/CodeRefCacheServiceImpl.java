package com.kcfy.platform.server.impl.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcfy.platform.server.cache.CodeRefCacheModel;
import com.kcfy.platform.server.cache.CodeRefCacheModelService;
import com.kcfy.platform.server.cache.CodeRefCacheModelUtil;
import com.kcfy.platform.server.cache.CodeRefCacheService;
import com.kcfy.platform.server.cache.IdentifierGenerator;
import com.kcfy.platform.server.cache.ResourceCacheServiceSupport;
import com.kcfy.platform.server.cache.SimpleStringIdentifierGenerator;

@Service("com.kcfy.platform.server.impl.cache.CodeRefCacheServiceImpl")
public class CodeRefCacheServiceImpl extends ResourceCacheServiceSupport<CodeRefCacheModel, Object>
		implements CodeRefCacheService<CodeRefCacheModel> {

	@Autowired(required=false)
	private CodeRefCacheModelService codeRefCacheModelService;
	
	private static SimpleStringIdentifierGenerator simpleStringIdentifierGenerator=new SimpleStringIdentifierGenerator(){
		public String namespace() {
			return "/coderef/";
		};
	};
	
	@Override
	public IdentifierGenerator generator() {
		return simpleStringIdentifierGenerator;
	}

	@Override
	public void initResource() {
		if(codeRefCacheModelService==null) return;
		List<? extends CodeRefCacheModel> codeRefCacheModels= codeRefCacheModelService.getResourceCacheModels();
		if(codeRefCacheModels!=null&&!codeRefCacheModels.isEmpty()){
			for(CodeRefCacheModel codeRefCacheModel:codeRefCacheModels){
				putNeverExpired(codeRefCacheModel.getUri(), codeRefCacheModel);
			}
		}
	}

	@Override
	public String getName(String type, String code) {
		String name=null;
		CodeRefCacheModel codeRefCacheModel=super.get(CodeRefCacheModelUtil.key(type, code));
		if(codeRefCacheModel!=null){
			name=codeRefCacheModel.name();
		}
		return name;
	}
}
