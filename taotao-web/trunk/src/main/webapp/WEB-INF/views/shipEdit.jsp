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
	var jjson=${queryTake};
	var s = [ "s_province", "s_city", "s_county" ];
	_init_area(s);
	var jjson=${queryTake};
	
	$('#Edit_id').val(jjson.id);
	$('#Edit_name').val(jjson.name);
	$('#s_province').change(jjson.provinceName);
	$('#Edit_townName').val(jjson.townName);
	$('#Edit_phone').val(jjson.phone);
	$('#s_province').val(jjson.provinceName);
	change(1);
	$('#s_city').val(jjson.cityName);
	change(2);
	$('#s_county').val(jjson.countyName);
	change(3);
	
	
	function submitForm() {
		
			$.ajax({
				type : "PUT",
				url : "/ship/editTake.html",
				data : $("#shipContent1").serialize(),
				statusCode : {
					204 : function() {
						$('#shipEdit').window('close');
						queryTaskList();
						clearForm();
					},
					500 : function() {
						$.messager.alert('提示', '修改收货地址失败!');
					}
				}
			});
		}
	

	
	function clearForm() {
		$('#shipContent1').form('reset');
	}
</script>


<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>

</head>

<div style="padding: 10px 10px 10px 10px" id="shipId">
	<form id="shipContent1" >
		<table cellpadding="5">
			<tr>
			
				<td>收货人:</td>
				<td><input class="easyui-textbox" type="text" name="name" id="Edit_name"
					data-options="required:true" style="width: 60px;"></input>
					<input type="hidden" id="Edit_id" name="id"></input></td>
			</tr>

			<tr>
				<td>收货地址:</td>
				<td><select id="s_province" name="provinceName"></select> <select
					id="s_city" name="cityName"></select> <select id="s_county"
					name="countyName"></select>
				</td>
			</tr>

			<tr>
				<td>详细地址：</td>
				<td><input class="easyui-textbox" type="text" name="townName" id="Edit_townName"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>

			<tr>
				<td>联系电话:</td>
				<td><input class="easyui-textbox" id="Edit_phone" type="text" name="phone"
					data-options="required:true"
					,validType:'phone', style="width: 100px;"></input></td>
			</tr>
		</table>
	</form>

	<div style="padding: 5px">
		<br /> <br /> <input type="button" onclick="submitForm()" value="提交" />
		<input type="button" onclick="clearForm()" value="重置" />
	</div>
</div>
