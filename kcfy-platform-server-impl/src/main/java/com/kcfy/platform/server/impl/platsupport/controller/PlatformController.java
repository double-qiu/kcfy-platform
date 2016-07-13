package com.kcfy.platform.server.impl.platsupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcfy.platform.server.kernal.Version;
import com.kcfy.platform.server.kernal.mapping.ControllerSupport;
import com.kcfy.platform.server.kernal.mapping.SkipMappingCheck;
import com.kcfy.platform.server.kernal.model.InvokeResult;

@Controller
@RequestMapping("/platformsupport")
public class PlatformController extends ControllerSupport {
	
	@ResponseBody
	@RequestMapping("/getVersion")
	@SkipMappingCheck
	public InvokeResult getVersion(){
		return InvokeResult.success(Version.getVersion());
	}
	
}
