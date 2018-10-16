<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品销售信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listSales.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的商品销售！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delSales.action?paramsSale.ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listSales.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listSales.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品销售管理&gt;&gt;商品销售查询</span>
</div>
<form name="info" id="info" action="Admin_listSales.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">出库物资列表</td>
    <td width="" align="right">
            物资名称：
      <input type="text" id="paramsSale.goods_name" name="paramsSale.goods_name" value="${paramsSale.goods_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
            物资类型：
      <s:select id="paramsSale.goods_type_id" name="paramsSale.goods_type_id" value="%{#attr.paramsSale.goods_type_id}" 
      		list="#attr.goodsTypes" listKey="goods_type_id" listValue="goods_type_name" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;
           登记人：
      <input type="text" id="paramsSale.sale_admin" name="paramsSale.sale_admin" value="${paramsSale.sale_admin}" class="inputstyle" Style="width:100px;"/>&nbsp;
            出库日期：
      <s:textfield name="paramsSale.sale_date_min" id="paramsSale.sale_date_min"  cssStyle="width:100px" cssClass="inputstyle"
					 value="%{#attr.paramsSale.sale_date_min}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
	   -
      <s:textfield name="paramsSale.sale_date_max" id="paramsSale.sale_date_max"  cssStyle="width:100px" cssClass="inputstyle"
					 value="%{#attr.paramsSale.sale_date_max}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;&nbsp;			 
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">物资编号</td>
     <td width="" align="center">物资名称</td>
     <td width="" align="center">物资类型</td>
     <td width="" align="center">出库数量</td>
     <td width="" align="center">登记人</td>
     <td width="" align="center">领用人</td>
     <td width="" align="center">领用人电话</td>
     <td width="" align="center">出库日期</td>
     <td width="" align="center">使用说明</td>
   </tr>
   <s:if test="#attr.sales!=null && #attr.sales.size()>0">
   <s:iterator value="#attr.sales" id="sale" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#sale.sale_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#sale.goods_no"/></td>
     <td width="" align="center"><s:property value="#sale.goods_name"/></td>
     <td width="" align="center"><s:property value="#sale.goods_type_name"/></td>
     <td width="" align="center"><s:property value="#sale.sale_count"/></td>
     <td width="" align="center"><s:property value="#sale.sale_admin"/></td>
     <td width="" align="center"><s:property value="#sale.sale_receiver"/></td>
     <td width="" align="center"><s:property value="#sale.receiver_tel"/></td>
     <td width="" align="center"><s:property value="#sale.sale_date.substring(0,10)"/></td>
     <td width="" align="center"><s:property value="#sale.use_desc"/></td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="11" align="center"><b>&lt;不存在出库物资信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>