var CC = CACA = {
		
		// 编辑器参数
		kingEditorParams : {
			filePostName  : "uploadFile", //文件控件的名称，真正提交的名称
			uploadJson : '/service/pic/upload', //上传路径
			dir : "image" //类型
		},
		
		// 格式化连接
		formatUrl : function(val,row){
			if(val){
				return "<a href='"+val+"' target='_blank'>查看</a>";			
			}
			return "";
		},
		
	    
	    init : function(data){
	    	this.initPicUpload(data);
	    },
	    // 初始化图片上传组件
	    initPicUpload : function(data){
	    	$("#pickbutton_3122336930_975788").each(function(i,e){
	    		var _ele = $(e);
	    		//查找同级元素
	    		_ele.siblings("div.pics").remove();
	    		// 使用\方便拼接字符串
	    		_ele.after('\
	    			<div class="pics">\
	        			<ul></ul>\
	        		</div>');
	    		
	    		// 回显图片
	        	if(data && data.pics){
	        		var imgs = data.pics.split(",");
	        		for(var i in imgs){
	        			if($.trim(imgs[i]).length > 0){
	        				alert(imgs[i]);
	        				_ele.siblings(".pics").find("ul").append("<li><a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"' width='80' height='50' /></a></li>");
	        			}
	        		}
	        	}
	        	$(e).unbind('click').click(function(){
	        		//this：按钮
	        		//查找最近的一个form
	        		//parentsUntil查找返回，不包含目标元素
	        		//.parentsUntil("form")
	        		var form = $(this).parents("div img-list");
	        		
	        		KindEditor.editor(CACA.kingEditorParams).loadPlugin('multiimage',function(){
	        			var editor = this;
	        			editor.plugin.multiImageDialog({
	        				// clickFn: 点击“全部插入”执行
							clickFn : function(urlList) {
								var imgArray = [];
								KindEditor.each(urlList, function(i, data) {
									imgArray.push(data.url);
									form.find(".pics ul").append("<li><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' width='80' height='80' /></a></li>");
								});
								form.find("[name=image]").val(imgArray.join(","));
								editor.hideDialog();
							}
						});
	        		});
	        	});
	    	});
	    },
	    
	    
	    
	    createEditor : function(select){
	    	return KindEditor.create(select, CACA.kingEditorParams);
	    },
	    
	    /**
	     * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
	     * 
	     * 默认：<br/>
	     * width : 80% <br/>
	     * height : 80% <br/>
	     * title : (空字符串) <br/>
	     * 
	     * 参数：<br/>
	     * width : <br/>
	     * height : <br/>
	     * title : <br/>
	     * url : 必填参数 <br/>
	     * onLoad : function 加载完窗口内容后执行<br/>
	     * 
	     * 
	     */
	    createWindow : function(params){
	    	$("<div>").css({padding:"5px"}).window({
	    		width : params.width?params.width:"80%",
	    		height : params.height?params.height:"80%",
	    		modal:true,
	    		title : params.title?params.title:" ",
	    		href : params.url,
			    onClose : function(){
			    	$(this).window("destroy");
			    },
			    onLoad : function(){
			    	if(params.onLoad){
			    		params.onLoad.call(this);
			    	}
			    }
	    	}).window("open");
	    },
	    
	    closeCurrentWindow : function(){
	    	$(".panel-tool-close").click();
	    }
	};
