<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加出库物资信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
	$(document).ready(function() {

		var goods_count = $("#goods_count");
		var num = /^\d+$/;
		$("#addBtn").bind('click', function() {
			if ($("#paramsSale\\.goods_id").val() == '0') {
				alert('出库物资不能为空');
				return;
			}
			if (!num.exec($("#paramsSale\\.sale_count").val())) {
				alert('物资数量必须为数字');
				return;
			}
			if (Number($("#paramsSale\\.sale_count").val()) > Number(goods_count.val())) {
				alert('库存数量不足，无法销售');
				return;
			}
			if ($("#paramsSale\\.sale_admin").val() == '') {
				alert('出库登记人不能为空');
				return;
			}
			if ($("#paramsSale\\.sale_date").val() == '') {
				alert('出库日期不能为空');
				return;
			}
			$("#paramsSale\\.sale_id").val(0);
			$("#info").attr('action', 'Admin_addSale.action').submit();

		});

		var goodss = {};
		<s:if test="#attr.goodss!=null&&#attr.goodss.size()>0">
	 <s:iterator value="#attr.goodss" id="goods">
	 	var goods_id = "<s:property value='#goods.goods_id'/>";
	 	goodss[goods_id] = "<s:property value='#goods.goods_count'/>";
	 </s:iterator>
	 </s:if>

		$("#goods_id").change(function() {
			var goods_id = $(this).val();
			$("#paramsSale\\.goods_id").val(goods_id);
			$("#goods_count").val(goodss[goods_id]);
			$("#goods_count_show").html(goodss[goods_id]);
		});

	});
</script>
<style type="text/css">
</style>
</head>
<body>
	<div class="pageTitle">
		&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span
			id="MainTitle" style="color:white">物资出库管理&gt;&gt;添加物资</span>
	</div>
	<form id="info" name="info" action="Admin_addSale.action" method="post">
		<s:hidden id="paramsSale.sale_id" name="paramsSale.sale_id" value="0" />
		<s:hidden id="paramsSale.goods_id" name="paramsSale.goods_id"
			value="0" />
		<s:hidden id="goods_count" name="goods_count" value="0" />
		<table width="800" align="center" cellpadding="0" cellspacing="0"
			style="margin-top:10px;margin-bottom:10px;">
			<tr>
				<td height="24">
					<Table border="0" cellspacing="0" cellpadding="0" align="center"
						width="100%">
						<TR>
							<TD height="24" class="edittitleleft">&nbsp;</TD>
							<TD class="edittitle">出库登记</TD>
							<TD class="edittitleright">&nbsp;</TD>
						</TR>
					</TABLE>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#8f8f8f"></td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" cellpadding="1" cellspacing="1"
						class="editbody">
						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font> 出库物资：</td>
							<td width="65%">
								<s:select list="#attr.goodss"
									name="goods_id" id="goods_id" listKey="goods_id"
									listValue="goods_name" headerKey="0" headerValue="请选择"
									cssStyle="width:155px">
								</s:select></td>
						</tr>
						<tr>
							<td width="35%" align="right" style="padding-right:5px">现存数量：</td>
							<td width="65%" id="goods_count_show">0</td>
						</tr>
						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font> 出库数量：</td>
							<td width="65%">
								<s:textfield name="paramsSale.sale_count"
									id="paramsSale.sale_count" 
									value="%{#attr.sale.sale_count}" 
								/>
							</td>
						</tr>
						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font> 出库登记人：</td>
							<td width="65%">
								<s:textfield 
									name="paramsSale.sale_admin"
									id="paramsSale.sale_admin"
									value="%{#attr.sale==null?#attr.admin.real_name:#attr.sale.sale_admin}" 
								/>
							</td>
						</tr>
						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font> 领用人：</td>
							<td width="65%"><s:textfield name="paramsSale.sale_receiver" id="paramsSale.sale_receiver" value="%{#attr.sale.sale_receiver}" /></td>
						</tr>
						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font> 领用人联系方式：</td>
							<td width="65%"><s:textfield name="paramsSale.receiver_tel" id="paramsSale.receiver_tel" value="%{#attr.sale.receiver_tel}" /></td>
						</tr>

						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font> 出库日期：</td>
							<td width="65%">
								<s:textfield name="paramsSale.sale_date"
									id="paramsSale.sale_date" value="%{#attr.paramsSale.sale_date}"
									onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" 
								/>
							</td>
						</tr>
						<tr>
							<td width="35%" align="right" style="padding-right:5px"><font
								color="red">*</font>物资使用描述：</td>
							<td width="65%">
								<textarea style="width:350px;height:100px"
									name="paramsSale.use_desc" id="paramsSale.use_desc">
          							<s:property value="%{#attr.sale.receiver_tel}" />
          						</textarea>
          					</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" cellpadding="0" cellspacing="0"
						class="editbody">
						<tr class="editbody">
							<td align="center" height="30"><input type="button"
								id="addBtn" Class="btnstyle" value="确认出库" /> &nbsp;<label
								style="color:red">${tip}</label></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>