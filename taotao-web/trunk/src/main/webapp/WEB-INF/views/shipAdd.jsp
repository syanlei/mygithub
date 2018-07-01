<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">	
<head>
<style type="text/css">
a {
	color: #C0C0C0;
	text-decoration: none;
}

a:hover {
	color: #990000;
}
.bottom {
	text-align: left;
	font-size: 12px;
	color: #FF0000;
	width: 1000px;
}
</style>
<script type="text/javascript">
	var s=["s_province1","s_city1","s_county1"];
	_init_area(s);
</script>
	


</head>

<div style="padding: 10px 10px 10px 10px" id="shipId">
	<form id="shipContent" method="post">
		<table cellpadding="5">
				<tr>
					<td>收货人:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						data-options="required:true" style="width: 60px;" ></input>
						</td>
				</tr>
				
				<tr>
					<td>收货地址:</td>
					<td>
						<select id="s_province1" name="provinceName" ></select>
						<select id="s_city1" name="cityName" ></select>
						<select id="s_county1" name="countyName" ></select>
						<div id="show"></div>
					</td>
			  </tr>
			  
			  <tr>
					<td>详细地址：</td>
					<td><input class="easyui-textbox" type="text" name="townName" 
					           data-options="required:true" style="width: 230px;"></input>
					</td>
				</tr>
			  
				<tr>
					<td>联系电话:</td>
					<td><input class="easyui-textbox" type="text" name="phone"
						data-options="required:true" ,validType:'phone', style="width: 100px;"></input></td>
				</tr>
		</table>
	</form>

	<div style="padding: 5px">
		<br />
		<br />
		<input type="button" onclick="submitForm()" value="提交" />
		<input type="button" onclick="clearForm()" value="重置" />
		
		<!-- <a href="javascript:void(0)"  onclick="submitForm()">提交</a>  -->
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a> -->
	</div>
	<div>
		<p>温馨提示：用来接收送货详细地址，便于及时送货  </p>
	</div>
</div>
<script type="text/javascript">

	var s=["s_province1","s_city1","s_county1"];
	var Gid1 = document.getElementById;
	var showArea1 = function() {
		Gid1('show').innerHTML = "<h3>省" + Gid1('provinceName').value + " - 市"
				+ Gid1('cityName').value + " - 县/区" + Gid1('countyName').value
				+ "</h3>"
	}
	Gid1('s_county').setAttribute('onchange', 'showArea()');
	
	$.extend($.fn.validatebox.defaults.rules, {
		phone : {    /* 属性值 */
			validator : function(value, param) {
				var regexp=/^1(3|5|7|8)\d{9}$/;
				
				return regexp.test(value);
			},
			message : '手机号输入不正确  1(3|5|7|8)\d{9}'
		}
	});
	
	
	
	function submitForm() {
		if (!$('#shipContent').form('validate')) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}

		$.ajax({
			type : "POST",
			url : "/ship/saveTake.html",
			data : $("#shipContent").serialize(),
			statusCode : {
				201 : function() {
					$('#shipAdd').window('close');
					queryTaskList();
					clearForm();
				},
				500 : function() {
					$.messager.alert('提示', '新增收货地址失败!');
				}
			}
		
		});
		
	}
	function clearForm() {
		$('#shipContent').form('reset');
	}
	_init_area();
</script>