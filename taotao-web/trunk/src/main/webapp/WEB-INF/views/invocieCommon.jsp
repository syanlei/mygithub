<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">	
<head>

</head>

<div style="padding: 10px 10px 10px 10px" id="invocieCommonId">
	<form id="invocieCommon" method="post">
		<table cellpadding="5">
				<tr>
					<td>发票抬头:</td>
					<td><input class="easyui-textbox" type="text" name="invocieHead" id="commonHeadId"
						data-options="required:true" style="width: 160px;" ></input>
						<input type="hidden" id="invocieModeId" value="普通发票" />
						</td>
				</tr>
			  
				<tr>
					<td>发票内容:</td>
					<td>
						<input type="radio" name="invocieContent" value="明细"  />明细
						<input type="radio" name="invocieContent" value="办公用品" />办公用品
						<input type="radio" name="invocieContent" value="电脑配件"/>电脑配件
						<input type="radio" name="invocieContent" value="耗材" />耗材
				</tr>
				
				
		</table>
	</form>

	<div style="padding: 5px">
		<br />
		<br />
		<input type="button" onclick="submitForm()" value="保存发票信息" />
		<input type="button" onclick="clearForm()" value="取消" />
	</div>
	
	<br />
	<div>
		<p>温馨提示：发票的开票金额不包括京东卡/京东E卡、优惠券和京豆支付部分  </p>
	</div>
</div>
<script type="text/javascript">

		function submitForm(){
			//clearForm();
			/*获取抬头  */
			var invocieHead=$("#commonHeadId").val()
			$("#hiddenInvocieHead").val(invocieHead);
			
			/*获取发票内容  */
			var invocieContentValue =$("input[name='invocieContent']:checked").val();
			$("#hiddeninvocieContent").val(invocieContentValue);
			
			/*获取开票方式  */
			var invocieModeIdValue= $("#invocieModeId").val();
			$("#hiddeninvocieMode").val(invocieModeIdValue);
			
			$('#invocieCommon').window('close');
			
		}

	function clearForm() {
		$('#invocieCommon').form('reset');
	}
</script>