if (!window.kjcs) {
	var kjcs = {};

};

kjcs.testPaginCode = (function(win) {
	
	var _this = null;
	var paginCode = function(options) {
	this.options = options;
	this.DefaultsGrid = {
			htmlBox:null,                           // dom元素id
	        url: false,                             //ajax url
	        data: null,                            //初始化数据
	        page: 1,                                //默认当前页 
	        pageSize: 2,                           //每页默认的结果数
	        params: {},                              //提交到服务器的参数 
	        columns: [],                          //数据源
			rowId : 'id',                     //唯一标识
			Topn :5,                        // 分页导航条数
			searchParam :[],                     //搜索参数
			pageSizeOptions:[2, 4, 6, 8, 10],  //可选择设定的每页结果数
			myfunction :{},
			render:null,
			checkbox:false,
			
			
			
	    };
		/*this.operate = {
						    name: "operate",
						    display: "操作",
						    width: 120,
						    minWidth: 80,
						  render :function(rowdata){
						  var _rowdata = rowdata;
						  var _button = $("<button class ='add'>添加</button >&nbsp;&nbsp;<button class ='view'>查看</button >&nbsp;&nbsp;<button class ='edit'>修改</button >&nbsp;&nbsp;<button class ='del'>删除</button>")
						  _button.on('click', function(){
								switch($(this).attr("class")){
									case "add":
									alert("--------add");
									break;
									case "view":
									alert(rowdata.id +"--------view");
									break;
									case "edit":
									alert(rowdata.id +"--------edit");
									break;
									case "del":
									alert(rowdata.id +"--------del");
									break;
								}
						     });
							return _button;
							},
						};
		*/
		
	this.DefaultsGrid_columns = {
	        name: null,
	        display: null,
	        type: null,
	        width: 120,
	        minWidth: 80,
	        align: 'left',
			render:null,
	    };
		this.totalPage = null;
		_this = this;
		init(true);
		this.choicerowdata={};                    //选择后数据
		
	};
	paginCode.prototype = {
			
		callback : function(back){
			this.callback =back;
		},
		setParam : function(pageparam){
			this.pageparam = pageparam;
			
		},
		/*外部初始化*/
		_init:function( params){
			if(!!params){
				_this.DefaultsGrid.params = params;
			}	
			_this.DefaultsGrid.page = _this.options.page;
			loadServerData(null,null,null,false);
			
			$("#pagination2").jqPaginator('option', {
		    currentPage: _this.DefaultsGrid.page
		});
		}
	};
	
	/**
	 * 1  调去后台数据
	 * 2、 生成html代码；
	 * 
	 * 
	 * 
	 * **/
	function init(flag){
		if(flag){
			
			setColumns();
			$.extend(_this.DefaultsGrid,_this.options);	
		}    
		 var _data = [{id:"001",name:"aa",avg:"19",sex:"男",hobby:"篮球"},{id:"002",name:"bb",avg:"20",sex:"女",hobby:"篮球"},{id:"003",name:"aa",avg:"19",sex:"男",hobby:"篮球"},{id:"004",name:"bb",avg:"20",sex:"女",hobby:"篮球"}];
		createSearchHtml(_this.DefaultsGrid.searchParam);
		
		 loadServerData(null,null,null,true);
	//	createHtml(_data);
		
	}

	/*设置Columns*/
	function setColumns(){
		var columns =[];
		/*var flag = true;
			for(var i = 0, l = _this.options.columns.length; i < l; i++){
				if("operate"==_this.options.columns[i].name){
					flag = false;
				}
			}
			if(flag){
				_this.options.columns.push(_this.operate);
			}*/
		for (var i in _this.options.columns){
			var t ={};
			 for(var j in _this.DefaultsGrid_columns){
				 if(!_this.options.columns[i][j]){
					 _this.options.columns[i][j] =_this.DefaultsGrid_columns[j] ;
				 }
						
				 }
			
		}
		return columns;
	}
	/*搜索form*/
	function createSearchHtml(searchParam){
		if(searchParam.length>0){
			var _html_Search = "<form>";
			for (var i in searchParam){
				_html_Search +=searchParam[i].display+"<input id = '"+searchParam[i].name+"'type ='text' value =''/>";
			}
			_html_Search  +="<input type = 'button' value ='搜索' class ='button'/>";
			_html_Search +="</form>"
		}
		$("#searchBox").html(_html_Search);
		
		$("#searchBox").on('click','[type =button]',function(){
			$("#searchBox form [type =text]").each(function(){
				var _key =$(this).attr("id");
				var _value =$(this).val();
				_this.DefaultsGrid.params[_key]= _value;
				init(false);
				
			});
		});
	}
	/*后台加载数据*/
	  function  loadServerData(params,callback,url,flag){
		 /* var backFunction = callback,_data = params||{};
			url += (url.indexOf('?') == -1 ? '?' : '&') +"timestamp="+new Date().getTime();
			
			var data ={"data":Tool.JsonToString(_data)};
				 $.ajax({
						   type: "GET",
						   url: url,
						   data: data||{},
						   async:true,
						   dataType:'json',
						   success: function(msg){						
								backFunction(msg);					   
							},
						   
						   error :function(textStatus){
								
							failure(textStatus);
						   }
						}); */
			_this.totalPage =10;
			var _data = [{id:"001",name:"aa",avg:"19",sex:"男",hobby:"篮球"},
		             {id:"002",name:"bb",avg:"20",sex:"女",hobby:"篮球002"},
		             {id:"003",name:"aa",avg:"19",sex:"男",hobby:"篮球dfsdf"},
		             {id:"004",name:"bb",avg:"20",sex:"女",hobby:"篮球sdf"},
		             {id:"005",name:"bb",avg:"20",sex:"女",hobby:"篮球sdf"},
		             {id:"006",name:"bb",avg:"20",sex:"女",hobby:"篮球h"},
		             {id:"007",name:"bb",avg:"20",sex:"女",hobby:"篮球k"},
		             {id:"008",name:"bb",avg:"20",sex:"女",hobby:"篮球l"},
		             {id:"009",name:"bb",avg:"20",sex:"女",hobby:"篮球o"},
		             {id:"010",name:"bb",avg:"20",sex:"女",hobby:"篮球i"},
		             {id:"011",name:"bb",avg:"20",sex:"女",hobby:"篮球u"},
		             {id:"012",name:"bb",avg:"20",sex:"女",hobby:"篮球y"},
		             {id:"013",name:"bb",avg:"20",sex:"女",hobby:"篮球r"},
		             {id:"014",name:"bb",avg:"20",sex:"女",hobby:"篮球r"},
		             {id:"015",name:"bb",avg:"20",sex:"女",hobby:"篮球we"},
		             {id:"016",name:"bb",avg:"20",sex:"女",hobby:"篮球dfs"},
		             {id:"017",name:"bb",avg:"20",sex:"女",hobby:"篮球kk"},
		             {id:"018",name:"bb",avg:"20",sex:"女",hobby:"篮球fg"},
		             {id:"019",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"020",name:"bb",avg:"20",sex:"女",hobby:"篮球gfd"},
		             {id:"021",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"022",name:"bb",avg:"20",sex:"女",hobby:"篮球g"},
		             {id:"023",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"024",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"025",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"026",name:"bb",avg:"20",sex:"女",hobby:"篮球g"},
		             {id:"027",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"028",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"029",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"030",name:"bb",avg:"20",sex:"女",hobby:"篮球dfg"},
		             {id:"031",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"032",name:"bb",avg:"20",sex:"女",hobby:"篮球er"},
		             {id:"033",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"034",name:"bb",avg:"20",sex:"女",hobby:"篮球we"},
		             {id:"035",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"036",name:"bb",avg:"20",sex:"女",hobby:"篮球wer"},
		             {id:"037",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"038",name:"bb",avg:"20",sex:"女",hobby:"篮球ty"},
		             {id:"039",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"040",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"041",name:"bb",avg:"20",sex:"女",hobby:"篮球x"},
		             {id:"042",name:"bb",avg:"20",sex:"女",hobby:"篮球xcv"},
		             {id:"043",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"044",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"045",name:"bb",avg:"20",sex:"女",hobby:"篮球;l"},
		             {id:"046",name:"bb",avg:"20",sex:"女",hobby:"篮球ii"},
		             {id:"047",name:"bb",avg:"20",sex:"女",hobby:"篮球"},
		             {id:"048",name:"bb",avg:"20",sex:"女",hobby:"篮球yt"},
		             {id:"049",name:"bb",avg:"20",sex:"女",hobby:"篮球'';"},
		             ];	
            _this.DefaultsGrid.data = _data.slice((_this.DefaultsGrid.page-1)*_this.DefaultsGrid.pageSize, _this.DefaultsGrid.page*_this.DefaultsGrid.pageSize);					 
			var $_html_tbody = createHtml_tbody(_this.DefaultsGrid.data);
         if(!flag){
			 $("#testBox tbody").replaceWith($_html_tbody);
		 }else{
			
			  createHtml(_this.DefaultsGrid.data);
		 }			
			};
			
	/*表单中表头*/
	function  createHtml(data){
	
		
		var $_html_table = $("<table  border='1' class='table table-striped table-bordered table-hover table-condensed  '></table>");
		var columns = _this.DefaultsGrid.columns;
		
		var $_html_thead = createHtml_thead(columns);
		$_html_table.append($_html_thead);
		
		var $_html_tbody = 	createHtml_tbody(data);
		$_html_table.append($_html_tbody);
		
		
		$("#"+_this.DefaultsGrid.htmlBox).append($_html_table).append("<div><ul id ='pageSizeOptions' >每页显示：<select ></select> </ul><ul id = 'checkData'>显示："+((_this.DefaultsGrid.page-1)*_this.DefaultsGrid.pageSize+1)+"至"+_this.DefaultsGrid.page*_this.DefaultsGrid.pageSize+"共"+ 40+"条记录。</ul><ul class='pagination' id='pagination2'></ul></div>");
		
		getpagination();
		setSelectData ();
		
		/*选择select框后重新初始化数据*/
		$('#pageSizeOptions').on("change","select",function(){
			_this.DefaultsGrid.pageSize = $(this).children('option:selected').val();
			 _this.choicerowdata = {};
			 $('table tr > th:first-child input:checkbox')[0].checked = false;
			loadServerData(null,null,null,false);
			
		});
		
		/*checkbox的全选 和全取消* 和点击"td"中checkbox  取消"th"中的checkbox */
		$('body').on('click', 'table  input:checkbox', function () {
        var that = this;
        if($($(this).parent()).is("th")){
		        	$(this).closest('table').find('tr > td:first-child input:checkbox').each(function () {	
		        		this.checked = that.checked;
						if(that.checked){
							_this.choicerowdata[$(this).attr("rowindex")]=_this.DefaultsGrid.data[$(this).attr("rowindex")];
						}else{
							delete _this.choicerowdata[$(this).attr("rowindex")];
						}	
		        	});
		 }else{
		     var _that = this;
			if(!that.checked){
				 $(this).closest('table').find('tr > th:first-child input:checkbox')[0].checked = _that.checked;
				 
					delete _this.choicerowdata[$(this).attr("rowindex")];
				}else{
					_this.choicerowdata[$(this).attr("rowindex")]=_this.DefaultsGrid.data[$(this).attr("rowindex")];
				}
						
		}
		        
    });
		 
	}
   
	/*表中thead数据*/
	function createHtml_thead(columns){
		var $_html_thead = $("<thead><tr></tr></thead>");
		
		if(_this.DefaultsGrid.checkbox){
			var $_tmp_checkBox = $("<th style = 'width:1%'><input type='checkbox' ></input></th>");
			$_html_thead.children("tr").append($_tmp_checkBox);
		}
		for(var i in columns){
			var $_tmp_thead =$("<th style='text-align:"+columns[i].align+";width:"+columns[i].width+"px'></th>");
			$_tmp_thead.html(columns[i].display)
			$_html_thead.children("tr").append($_tmp_thead);
		}
		
		return $_html_thead;
	}
	
	/*设置select框的默认值*/
	function setSelectData (){
		var _html_select = "";
		for(var i in _this.DefaultsGrid.pageSizeOptions){
			_html_select += "<option value="+_this.DefaultsGrid.pageSizeOptions[i]+">"+_this.DefaultsGrid.pageSizeOptions[i]+"条</option>"
		}
		$("#pageSizeOptions select").html(_html_select);
		$("#pageSizeOptions option[value="+_this.DefaultsGrid.pageSize+"]").attr("selected",true);
	}
	
	
	
	/*表单中_tbody*/
	
	function createHtml_tbody(data){
		var $_html_tbody = $("<tbody></tbody>");	
		var columns = _this.DefaultsGrid.columns;
		var _data=data;
		//var index ;
		for(var i in _data){
			var $_tmp_tbody_tr = $("<tr></tr>");
			
			if(_this.DefaultsGrid.checkbox){
			var $_tmp_checkBox = $("<td ><input type='checkbox' rowindex ='"+i+"'></input></td>");
			$_tmp_tbody_tr.append($_tmp_checkBox);
		}
			for(var j in columns){
				var $_tmp_tbody_td = $("<td></td>");
				if(!!columns[j].render){
					var content =_getCellContent(_data[i],columns[j].render );
					$_tmp_tbody_td.css("text-align",columns[j].align).css("width",columns[j].width+"px").html(content);
				//    index = j;
				}else{
				  $_tmp_tbody_td.css("text-align",columns[j].align).css("width",columns[j].width+"px").html(_data[i][columns[j].name]);
				//   index = j;
				}
				$_tmp_tbody_tr.append($_tmp_tbody_td);	
			}
			$_html_tbody.append($_tmp_tbody_tr);
		}
		return $_html_tbody;
	}
	
	/**/
	 function _getCellContent(rowdata, column){
		 
         if (!rowdata || !column) return "";
		 var content = "";
         content = column.call(this, rowdata);
		return content || "";
	 }

	/*分页事件*/	
	function dofunction(pn){
		
		var param = eval('('+'{page:"'+pn+'"}'+')');
	//	$.extend(param ,_this.pageparam);
	//	loadServerData(_this.pagePath,param);
		$("#checkData").html("显示："+pn+"至"+(_this.DefaultsGrid.page+5)+"共"+_this.totalPage+"条记录。");
		_this.DefaultsGrid.page = pn;
		
		loadServerData(null,null,null,false);
		
		$("#pagination2").jqPaginator('option', {
		    currentPage: pn
		});
	}
	/*分页组件*/
	function getpagination(){
		var _type =false;
		 $.jqPaginator('#pagination2', {
		    totalPages: _this.totalPage,
		    visiblePages: _this.DefaultsGrid.Topn,
		    currentPage: _this.DefaultsGrid.page,
		    prev: '<li class="prev"><a href="javascript:;"><</a></li>',
		    next: '<li class="next"><a href="javascript:;">></a></li>',
			first: '<li class="first"><a href="javascript:;"><<</a></li>',
			last: '<li class="last"><a href="javascript:;">>></a></li>',
		    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		    onPageChange: function (num, type) {
		    	if(_type){
		    	dofunction(num);
		    	}
		    	_type =true;
		    }
		});
	}
	
	
	
	return paginCode;
})(window);