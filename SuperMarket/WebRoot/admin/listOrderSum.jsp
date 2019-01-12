<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品进货信息</title>
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
   document.info.action="Admin_listOrderss.action";
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
       alert("请至少选择一个要删除的商品进货！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delOrderss.action?paramsOrders.ids="+ids;
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
  document.info.action="Admin_listOrderss.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listOrderss.action";
  document.info.submit();
}

$(document).ready(function(){
	$("#export").bind('click',function(){
		var aQuery = $("#info").serialize();  
		$("#info").attr('action','exportOrderSum.action').submit();;
	});
});

</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品进货管理&gt;&gt;商品进货查询</span>
</div>
<form name="info" id="info" action="Admin_listOrderss.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">商品进货列表</td>
    <td width="" align="right">
            <%-- 商品名称：
      <input type="text" id="paramsOrders.goods_name" name="paramsOrders.goods_name" value="${paramsOrders.goods_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
            商品类型：
      <s:select id="paramsOrders.goods_type_id" name="paramsOrders.goods_type_id" value="%{#attr.paramsOrders.goods_type_id}" 
      		list="#attr.goodsTypes" listKey="goods_type_id" listValue="goods_type_name" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;
            进货人：
      <input type="text" id="paramsOrders.orders_admin" name="paramsOrders.orders_admin" value="${paramsOrders.orders_admin}" class="inputstyle" Style="width:100px;"/>&nbsp;
            进货日期：
      <s:textfield name="paramsOrders.orders_date_min" id="paramsOrders.orders_date_min"  cssStyle="width:100px" cssClass="inputstyle"
					 value="%{#attr.paramsOrders.orders_date_min}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
	   -
      <s:textfield name="paramsOrders.orders_date_max" id="paramsOrders.orders_date_max"  cssStyle="width:100px" cssClass="inputstyle"
					 value="%{#attr.paramsOrders.orders_date_max}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;&nbsp;			 
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/> --%>
      
      <input type="button" value="导出" id="export" class="btnstyle"/>
      
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">商品编号</td>
     <td width="" align="center">商品名称</td>
     <td width="" align="center">商品类型</td>
     <td width="" align="center">进货前库存</td>
     
     <td width="" align="center">进货数量</td>
     <td width="" align="center">进货人</td>
     <td width="" align="center">进货日期</td>
   </tr>
   <s:if test="#attr.orderss!=null && #attr.orderss.size()>0">
   <s:iterator value="#attr.orderss" id="orders" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#orders.orders_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#orders.goods_no"/></td>
     <td width="" align="center"><s:property value="#orders.goods_name"/></td>
     <td width="" align="center"><s:property value="#orders.goods_type_name"/></td>
      <td width="" align="center"><s:property value="#orders.goods_count"/></td>
     <td width="" align="center"><s:property value="#orders.orders_count"/></td>
     <td width="" align="center"><s:property value="#orders.orders_admin"/></td>
     <td width="" align="center"><s:property value="#orders.orders_date.substring(0,10)"/></td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="8" align="center"><b>&lt;不存在商品进货信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>