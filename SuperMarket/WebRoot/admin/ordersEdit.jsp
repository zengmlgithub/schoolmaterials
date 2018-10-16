<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加物资进货信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsOrders\\.goods_id").val()=='0'){
			alert('进货物资不能为空');
			return;
		}
		if(!num.exec($("#paramsOrders\\.orders_count").val())){
			alert('进货数量必须为数字');
			return;
		}
		if($("#paramsOrders\\.orders_admin").val()==''){
			alert('进货人不能为空');
			return;
		}
		if($("#paramsOrders\\.orders_date").val()==''){
			alert('进货日期不能为空');
			return;
		}
		$("#paramsOrders\\.orders_id").val(0);
		$("#info").attr('action','Admin_addOrders.action').submit();
		 
	 });
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">物资进货管理&gt;&gt;添加物资进货</span>
</div>
<form id="info" name="info" action="Admin_addOrders.action" method="post">   
<s:hidden id="paramsOrders.orders_id" name="paramsOrders.orders_id" value="0" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">物资进货</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 进货物资：</td>
          <td width="65%">
          	<s:select list="#attr.goodss" name="paramsOrders.goods_id" id="paramsOrders.goods_id" listKey="goods_id" listValue="goods_name"
          			headerKey="0"  headerValue="请选择" cssStyle="width:155px">
          	</s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 进货数量：</td>
          <td width="65%">
          	<s:textfield name="paramsOrders.orders_count" id="paramsOrders.orders_count" value="%{#attr.orders.orders_count}"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 进货人：</td>
          <td width="65%">
          	<s:textfield name="paramsOrders.orders_admin" id="paramsOrders.orders_admin" value="%{#attr.orders==null?#attr.admin.real_name:#attr.orders.orders_admin}"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 进货日期：</td>
          <td width="65%">
          	<s:textfield name="paramsOrders.orders_date" id="paramsOrders.orders_date" 
					 value="%{#attr.paramsOrders.orders_date}"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<input type="button" id="addBtn" Class="btnstyle" value="进 货" />
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>