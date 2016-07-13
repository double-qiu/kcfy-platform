if (!window.kjcs) {
	var kjcs = {};

};
kjcs.prompt = {
		showtype:false,
		message:""
};

var Tool = {
		isArray : function(obj) {
			return this.type(obj) == "array";
		},
		isFun : function(obj) {
			return this.type(obj) == "function";
		},
		isStr : function(obj) {
			return this.type(obj) == "string";
		},
		log : function(msg) {
			this.info(msg);
		},
		info : function(msg) {
			if (window.console && window.console.info) {
				window.console.info(msg);
			}
		},
		error : function(msg) {
			if (window.console && window.console.error) {
				window.console.error(msg);
			}
		},
		infoMessage : function(msg){
			if($(".messagepopup").length==0){
				$("body").append("<div class=\"messagepopup\" id=\"messagepopup\" style=\"display:none\"><span></span></div>");
			}
			this.info(msg);
			if(!kjcs.prompt.showtype){
				kjcs.prompt.showtype = true;
				kjcs.prompt.message=msg;
				$(".messagepopup").css("display","block");
				$(".messagepopup span").html(msg);
				setTimeout('Tool.closePrompt()',2500);
			}else{
				
			}
		},
		errorMessage : function(msg){
			if($(".messagepopup").length==0){
				$("body").append("<div class=\"messagepopup\" id=\"messagepopup\" style=\"display:none\"><span></span></div>");
			}
			this.error(msg);
			if(!kjcs.prompt.showtype){
				kjcs.prompt.showtype = true;
				kjcs.prompt.message=msg;
				$(".messagepopup").css("display","block");
				$(".messagepopup span").html(msg);
				setTimeout('Tool.closePrompt()',4000);
			}else{
				
			}
		},
		closePrompt : function(){
			kjcs.prompt.showtype = false;
			$(".messagepopup").css("display","none");
			$(".messagepopup span").html("");
		},
		setCookie:function(name,value, time){
			var _exp=new Date();
			time = time||60000;
			_exp.setTime(_exp.getTime()+time);
			document.cookie=name+"="+escape(value)+";expires="+_exp.toGMTString();
		},
		/**
		 * 读cookies
		 */
		getCookie:function(name)
		{
		    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		    if(arr=document.cookie.match(reg))
		        return (arr[2]);
		    else
		        return null;
		},
		
		
		
		
		getUrlParam : function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = decodeURI(window.location.search.substr(1)).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		},
		setUrlParam : function(name,value){
			var href = window.location.href;
			var  _newParam = null ;// "&"+name+"=" ;
			if(null !=Tool.getUrlParam(name)){
				  _newParam = name+"="+value ;
				href = href.replace(name+"="+Tool.getUrlParam(name),_newParam)
				return href;
			}
			if(window.location.search.substr(1).length ==0){
				_newParam = "?"+name+"="+value;
			}else{
				_newParam = "&"+name+"="+value;
			}
			return  href +_newParam;	
		},
		
		/**
		 * 收索title分段
		 * 
		 * @param _title
		 */
		subsection: function(_title,keyWord){
			var front_title = "";
			var back_title = "";
			var index = _title.indexOf(keyWord);
			front_title = _title.substr(0,index);
			back_title = _title.substr(index+keyWord.length);
			var result_title ={
					'front_title':front_title,
					'back_title':back_title
			}
			return result_title;
		},
		
		checkisRarFile : function(photoExt){
			if(photoExt!='.rar'&&photoExt!='.zip'){
				return true;
			}else{
				return false;
			}
		},
		
		checkRarFile :function (target,callback){ 
			photoExt=target.value.substr(target.value.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
		    if(Tool.checkisRarFile(photoExt)){
		    	callback.call({"code":"401"});
		        return ;
		    }
			  var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		    var fileSize = 0;          
		    if (isIE && !target.files) {
		      var filePath = target.value;      
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");         
		      var file = fileSystem.GetFile (filePath);      
		      fileSize = file.Size;     
		    } else {     
		     fileSize = target.files[0].size;      
		     }    
		     var size = fileSize / 1024;     
		     if(size>3*1000){   
		    	 callback.call({"code":"402"});
			        return ;
		     }else{
		    	 callback.call({"code":"200"});
			        return ;
		     }   
		},
		checkImage : function(target,callback){ 
			photoExt=target.value.substr(target.value.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
		    if(Tool.checkisImg(photoExt)){
		    	callback.call({"code":"401"});
		        return ;
		    }
			
			  var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		    var fileSize = 0;          
		    if (isIE && !target.files) {      
		      var filePath = target.value;      
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");         
		      var file = fileSystem.GetFile (filePath);      
		      fileSize = file.Size;     
		    } else {     
		     fileSize = target.files[0].size;      
		     }    
		     var size = fileSize / 1024;     
		     if(size>1*1000){   
		    	 callback.call({"code":"402"});
			        return ;
		     }else{
		    	 callback.call({"code":"200"});
			        return ;
		     }   
		},
		
		/**
		 * 检查是否是图片
		 * 
		 * @param photoExt
		 * @returns {Boolean}
		 */
		checkisImg : function(photoExt){
			if(photoExt!='.jpg'&&photoExt!='.png'&&photoExt!='.jpeg'&&photoExt!='.bmp'&&photoExt!='.gif'){
				return true;
			}else{
				return false;
			}
		},
		
		/**
		 * 检测文件上传大小 上传类型不是图片 401 文件过大 402
		 * 
		 * @param target
		 *            input this
		 * @param _size
		 *            大小单位M
		 * @param callback
		 *            回调函数
		 */
		checkFileSize: function (target,_size,callback) { 
			photoExt=target.value.substr(target.value.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
		    if(Tool.checkisImg(photoExt)){
		    	callback.call({"code":"401"});
		        return ;
		    }
			
			  var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		    var fileSize = 0;          
		    if (isIE && !target.files) {      
		      var filePath = target.value;      
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");         
		      var file = fileSystem.GetFile (filePath);      
		      fileSize = file.Size;     
		    } else {     
		     fileSize = target.files[0].size;      
		     }    
		     var size = fileSize / 1024;     
		     if(size>_size*1000){   
		    	 callback.call({"code":"402"});
			        return ;
		     }else{
		    	 callback.call({"code":"200"});
			        return ;
		     }   
		},

		/**
		 * 页面打印传人需要打印的元素的id
		 */
			pagePrint: function(param){
				$("#"+param).jqprint();
			},
			

			/**
			 * ajax 实现文件上传form 提交
			 * 
			 * @param obj
			 *            form表单id
			 * @param _url
			 *            提交的url
			 * @param callback
			 *            回掉函数
			 */
			submitFunc : function(obj,_url,callback){
			    	var form =$(obj);
			    	var ajax_option ={
			    			url : _url,
			    			success : function(res){
			    				callback.call(res);
			    			},
			    			error : function(res){
			    				callback.call(res);
			    			}
			    	};
			    	form.ajaxSubmit(ajax_option);
			},
			/**
			 * 用户登陆 返回ture
			 * 
			 * @param tologin
			 * @returns {Boolean}
			 */
			checkIsLogin : function(tologin){
				var userName=Liferay.ThemeDisplay.getUserName();
			if(userName.length ==0){
				if(tologin){
					window.location.href="/c/portal/login";
					return false;
				}else{
					return false;
				}
				
			}else{
				return true;
			}
			},
			
			/**
			 * 登陆用户才能跳转
			 * 
			 * @param tologin
			 *            {Boolean}
			 * @param jumpUrl
			 *            {String}
			 * @returns {Boolean}
			 */
			loginUserRequset : function(tologin,jumpUrl){
				if(Tool.checkIsLogin(tologin)){
					window.location.href=jumpUrl;
				}else{
					return false;
				}
				
			},
			/**
			 * 
			 */
			isIE :function() { // ie?
			      if (!!window.ActiveXObject || "ActiveXObject" in window)
			        return true;
			      else
			        return false;
			    },
			    
			    serializeObject: function (obj) {
			        var resultObj = new Object();
			        $.each(obj.serializeArray(), function (index, param) {
			            if (!(param.name in resultObj)) {
			                if(param.value!=undefined && param.value!=''){
			                    param.value=$.trim(param.value);
			                }
			                resultObj[param.name] = param.value;
			            }
			        });
			        return resultObj;
			    },
			    
			    jsonToParam : function(obj) {
					var param = "";
					for ( var key in obj) {
						// if(Tool.isStr(obj[key])){
						param += "&" + key + "=" + obj[key];
						// }
					}
					if (param.length) {
						param = param.substr(1);
					}
					return param;
				},
				stringify : function(obj) {
					var JSON = window.JSON;
					if (JSON) {
						return JSON.stringify(obj);
					}
					return Tool.jsonToParam(obj);
				},
			    
				parseJSON : function(json) {
					if (typeof json != 'string')
						return json = json.replace(/^\s+|\s+$/g, '');
					var isValid = /^[\],:{}\s]*$/
							.test(json
									.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@")
									.replace(
											/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
											"]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""));
					if (!isValid)
						throw "Invalid JSON";
					var JSON = window.JSON;
					return JSON && JSON.parse ? JSON.parse(json) : (new Function("return "+ json))();
				},
				
			    JsonToString :function(String){
			    return 	JSON.stringify(String);
			    }
			    
};	

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery); 


var Ajax = function(){
	function defSuccess() {
	};
	function defFailure(code) {
		Tool.infoMessage(code);
//		Tool.log("requset failure: status" + code);
	};
	/**
	 * 默认get，not timely 请求
	 *  对 fileRespectSource 进行解析
	 * @param url
	 * @param opt
	 */
	function request(url,opt){
		var async = opt.async !== false,timely = opt.timely !== false, method = opt.method || 'GET',data = opt.data||null,
			success = opt.success||defSuccess,failure = opt.failure
		|| defFailure, header = opt.header || {}, method = method.toUpperCase(),timestamp ="";
		
			   var data ={};
		 
		   data.formData = Tool.JsonToString(data);
		   data.paginationData = Tool.JsonToString({page:page,size:size});
		
		if(method =='GET'&&timely){
			url += (url.indexOf('?') == -1 ? '?' : '&') +"timestamp="+new Date().getTime();
		}
	/*	if(method =='GET' && data){
			url += (url.indexOf('?') == -1 ? '?' : '&') + data;
		}*/
		$.ajax({
			   type: method,
			   url: url,
			   data: data||{},
			   async:async,
			   dataType:'json',
			   success: function(msg){
				   if(msg.success){
					   success(msg.data)
					   }else{
					   failure(msg.errorMessage);
				   }
				   
			   },
			   error :function(textStatus){
				   failure(textStatus);
			   }
			});
	};
	
	function get(url,opt){
		var  _opt = {};	
		_opt.async = opt.async !== false,_opt.timely = true, _opt.method =  'GET',_opt.data = opt.data||null,
		_opt.success = opt.success||defSuccess,_opt.failure = opt.failure || defFailure,page = opt.page||0,
		size=opt.size||0;
		request(url,_opt);
	
	};
	function post(url,opt){
		var  _opt = {};	
		_opt.async = opt.async !== false,_opt.timely = true, _opt.method =  'POST',_opt.data = opt.data||null,
		_opt.success = opt.success||defSuccess,_opt.failure = opt.failure || defFailure,page = opt.page||0,
		size=opt.size||0;
		request(url,_opt);
	};
		
	/**
	*opt ={formObj,failurl,success}
	*/
	function submitFile(url,opt){
		var async = opt.async !== false,
		method ='POST',
		success = opt.success||defSuccess,
		failure = opt.failure || defFailure,
		formObj =opt.formObj,
		page = opt.page||0,
		size=opt.size||0,token ="";
			var _url = url;	
					
		var form =formObj;
		   if(form.children("[token=token]").length ==1){
			token =form.children("[token=token]").val();
		   }
		   
		   var data ={};
		   data.token =token;
		   data.formData = Tool.JsonToString(form.serializeJson());
		   data.paginationData = Tool.JsonToString({page:page,size:size});
		   
			$.ajax({
			   type: method,
			   url: _url,
			   data: data||{},
			   async:async,
			   dataType:'json',
			   success: function(msg){
				 if(msg.success){
				  			 success(msg);
				  			 }else{
				  				 if(!!msg.token){
				  				   if(form.children("[token=token]").length ==1){
									   form.children("[token=token]").val(Tool.JsonToString(msg.token));
								   } 
				  				 }
				  			   failure(msg.errorMessage); 
				  			 }
				   
			   },
			   error :function(textStatus){
				   failure(textStatus);
			   }
			});
	}
	
	
	return {
		"getRequest":get,
		"post":post,
			"search":get,
		"submitForm":submitFile
			};
	
}();

kjcs.html = (function(data){
	var donum = 0;
	 var _this = null;
	 var createHtml = function(obj){
		  this.obj = obj;
		  this.url = !!obj.url?obj.url:"";
		 _this = this;
		 this.init();
	 };
	 createHtml.prototype = {
		 
		 init : function(){
			 //获取根节点html
			 var rootDoms = $("[data-layoutBody]");
			 
		 var datas =  getRequstParams(rootDoms);
		 console.log(rootDoms.length+"这是root节点数量");
			this.doMethod(datas);
		 
			},
			doview:function(htmlUrl,layoutId){
				
				
				this.doMethod(datas);
			},
			
			
		doMethod : function(datas){
			donum ++;
			if(donum >50){
				alert("嵌套逻辑超过50");
				return ;
			}
				for(var i in datas){
					var data = datas[i];
					doget(data,doGetCallBack,data.layoutId);
				}
	
		},
		
		loadHtml: function(obj){
			$(obj).parent().addClass("active")
			$(obj).parent().siblings().removeClass("active");
			var data = getRequstParamsByParam($(obj));
			
			doget(data,loadHtmlCallBack,data.request.layoutId)
		},

	 };
		/**
		 * 根据dom元素获取传入参数
		 */
		function getRequstParams(doms){
			var data =[];
			for(var i in doms){
			var dom  = doms.eq(i);
			if(!!dom.attr("data-htmlUrl")){
				var params ={url:_this.url};
				var request = {htmlUrl:dom.attr("data-htmlUrl"),layoutId:dom.attr("data-layoutId")};
				params.request =request;
				data.push(params);
				}
			}
			return data;
		};
			/**
			 * 根据入参获取传入参数
			 */
		function getRequstParamsByParam(doms){
			
			if(!!dom.attr("htmlUrl")){
				var params ={url:_this.url};
				var request = {htmlUrl:dom.attr("htmlUrl"),layoutId:dom.attr("layoutId")};
				params.request =request;
				
				}
			
			return params;
		};
		function doget(params,callback,domId){
		var backFunction = callback,_data = params||{},url = _data.url;
		url += (url.indexOf('?') == -1 ? '?' : '&') +"timestamp="+new Date().getTime();
		
		var data ={"data":Tool.JsonToString(_data)};
//		data.data = _data;
			$.ajax({
					   type: "GET",
					   url: url,
					   data: data||{},
					   async:true,
					   dataType:'json',
					   success: function(msg){						
							backFunction(msg,domId);					   
						},
					   
					   error :function(textStatus){
							
						failure(textStatus);
					   }
					});
				
		};
		
		function  loadHtmlCallBack(data,layoutId){
			$("[data-layoutId='"+layoutId+"']").eq(0).html(data.html);
			
		}
		
		//获取html 成功后回调
		function doGetCallBack(data,layoutId){
			if($("[data-layoutId='"+data.htmlDef.layoutId+"']").length!=1){
				callErrData(data);
				$("[data-layoutId='"+layoutId+"']").eq(0).html(data.html);
			}else{
					$("[data-layoutId='"+data.htmlDef.layoutId+"']").html(data.html);
				if(data.htmlDef.type =="html"){					
						if($("[data-layoutId='"+data.htmlDef.layoutId+"'] [token='token']").length==1){
							$("[data-layoutId='"+data.htmlDef.layoutId+"'] [token='token']").eq(0).val(data.token);
						}						
				}
					var doms = 	$("[data-layoutId='"+data.htmlDef.layoutId+"'] [data-layoutId]");
					var params = getRequstParams(doms);
					_this.doMethod(params);
						
				
			}			
			
		};
		
		
			function failure(data){
			Tool.info("[doGet error error ]",data);
			
		};
		function callErrData(data){
			Tool.info("[doGet  html layoutId:]",Tool.JsonToString(data));

		};
	 
	return createHtml;
	 
})(window);



