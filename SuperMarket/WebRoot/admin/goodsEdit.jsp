<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>添加</s:else>物资信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num1 = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsGoods\\.goods_no").val()==''){
			alert('物资编号不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_name").val()==''){
			alert('物资名称不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_type_id").val()=='0'){
			alert('物资类型不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_price").val()!=''){
			if(!num1.exec($("#paramsGoods\\.goods_price").val())){
				alert('物资价格必须为数字');
				return;
			}
		}else{
			$("#paramsGoods\\.goods_price").val(0);
		}
		if($("#paramsGoods\\.goods_count").val()!=''){
			if(!num2.exec($("#paramsGoods\\.goods_count").val())){
				alert('库存数量必须为数字');
				return;
			}
		}else{
			$("#paramsGoods\\.goods_count").val(0);
		}
		$("#paramsGoods\\.goods_id").val(0);
		$("#info").attr('action','Admin_addGoods.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
	    if($("#paramsGoods\\.goods_name").val()==''){
			alert('物资名称不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_type_id").val()=='0'){
			alert('物资类型不能为空');
			return;
		}
		if($("#paramsGoods\\.goods_price").val()!=''){
			if(!num1.exec($("#paramsGoods\\.goods_price").val())){
				alert('物资价格必须为数字');
				return;
			}
		}else{
			$("#paramsGoods\\.goods_price").val(0);
		}
		if($("#paramsGoods\\.goods_count").val()!=''){
			if(!num2.exec($("#paramsGoods\\.goods_count").val())){
				alert('库存数量必须为数字');
				return;
			}
		}else{
			$("#paramsGoods\\.goods_count").val(0);
		}
		$("#info").attr('action','Admin_saveGoods.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">物资管理&gt;&gt;<s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>添加</s:else>物资</span>
</div>
<form id="info" name="info" action="Admin_addGoods.action" method="post">   
<s:hidden id="paramsGoods.goods_id" name="paramsGoods.goods_id" value="%{#attr.goods.goods_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>添加</s:else>商品</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 物资编号：</td>
          <td width="65%">
          	<s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">
          		<s:property value="#attr.goods.goods_no"/>
          	</s:if>
          	<s:else>
          		<s:textfield name="paramsGoods.goods_no" id="paramsGoods.goods_no" value="%{#attr.goods.goods_no}"/>
          	</s:else>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 物资名称：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_name" id="paramsGoods.goods_name" value="%{#attr.goods.goods_name}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 物资类型：</td>
          <td width="65%">
          	<s:select id="paramsGoods.goods_type_id" name="paramsGoods.goods_type_id" value="%{#attr.goods.goods_type_id}" 
	      		list="#attr.goodsTypes" listKey="goods_type_id" listValue="goods_type_name" 
	      		class="selectstyle" cssStyle="width:150px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 物资价格：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_price" id="paramsGoods.goods_price" value="%{#attr.goods.goods_price}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 库存数量：</td>
          <td width="65%">
          	<s:textfield name="paramsGoods.goods_count" id="paramsGoods.goods_count" value="%{#attr.goods.goods_count}"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>物资描述：</td>
          <td width="65%">
          	<textarea style="width:350px;height:100px" name="paramsGoods.goods_desc" id="noticeContent">
          	<s:property value="#attr.goods.goods_desc" />
          	</textarea>
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
          	<s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
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