
window.onload = function(){	

	var selectInputs = document.getElementsByName('checkItem'); // 所有勾选框
    var checkAllInputs = document.getElementsByClassName('check-all') // 全选框
    
    TTCart2.getTotal();
    
    //全选
    for(var i = 0; i < checkAllInputs.length; i++ ){
    	checkAllInputs[i].onclick = function(){
    		if(this.checked){
    			 for(var j = 0; j < selectInputs.length; j++ ){
    				 selectInputs[j].checked = true;
    			 }
    			 checkAllInputs[0].checked=true;
    			 checkAllInputs[1].checked=true;
    		}else{
    			for(var j = 0; j < selectInputs.length; j++ ){
   				 selectInputs[j].checked = false;
   			 	}
    			checkAllInputs[0].checked=false;
			 	checkAllInputs[1].checked=false;
    		}
    		TTCart2.getTotal();
    	}
    }
    
    
    // 点击选择框
    for(var i = 0; i < selectInputs.length; i++ ){
        selectInputs[i].onclick = function () {
            if (!this.checked) {
                for (var i = 0; i < checkAllInputs.length; i++) {
                    checkAllInputs[i].checked = false;
                }
            }
            
            var ids = TTCart2._getSelecteds();
            if(ids.length == selectInputs.length){
            	for (var i = 0; i < checkAllInputs.length; i++) {
                    checkAllInputs[i].checked = true;
                }
            }
 
            TTCart2.getTotal();
        }
    }
    
    
    
}
		
var TTCart2 = {
		
		_getSelecteds:function(){
   			
			var selectInputs = $("input[name=checkItem]");
						
			var ids = [];
			for (var i = 0; i < selectInputs.length; i++) {
				if(selectInputs[i].checked){
					var input=$(selectInputs[i]).parent(".p-checkbox").nextAll(".p-quantity").children("div").children("input");
					ids.push(input.attr("itemId"));
				}
			}
			return ids;
   		},
		
   		
		
		// 更新和总价格，已选
		getTotal:function(){
			
			var selectInputs = document.getElementsByName('checkItem'); // 所有勾选框

		    var seleted = 0;
			var price = 0;
			
			for (var i = 0; i < selectInputs.length; i++) {
                if(selectInputs[i].checked){
                	var input=$(selectInputs[i]).parent(".p-checkbox").nextAll(".p-quantity").children("div").children("input");
                	seleted += parseInt(input.val());
                	price += parseInt(input.attr("itemPrice"))*parseInt(input.val());
                	
                }
            }
			
			$("#selectedCount").html(seleted);
			
			$(".totalSkuPrice").html(new Number(price/1000).toFixed(2)).priceFormat({ //价格格式化插件
				 prefix: '￥',
				 thousandsSeparator: ',',
				 centsLimit: 2
			});
		},

		// 更新总数，已选
		getTotalNum:function(){
			var selectInputs = document.getElementsByName('checkItem'); // 所有勾选框
			//var selectedCount = document.getElementById('selectedCount'); //已选商品数目
			
			var seleted = 0;
			var price = 0;
			for (var i = 0; i < selectInputs.length; i++) {
				if(selectInputs[i].checked){
					var input=$(selectInputs[i]).parent(".p-checkbox").nextAll(".p-quantity").children("div").children("input");
					seleted += parseInt(input.val());
				}
			}
			$("#selectedCount").html(seleted);
			
		}
	
}
