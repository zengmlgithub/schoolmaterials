
package com.nkl.admin.action;

import java.util.HashMap;
import java.util.List;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.Sale;

public class ExportExcelAction extends BaseAction{

	private static final long serialVersionUID = 8143993190677252109L;
	AdminManager adminManager =  new AdminManager();
	
	//导出参数
	Sale paramsSale;
	HashMap<String, Object> report = new HashMap<String, Object>();
	
	//goods导出参数
	Goods paramsGoods;
	HashMap<String,Object> goodsReport = new HashMap<String,Object>();
	
	
	public Goods getParamsGoods() {
		return paramsGoods;
	}
	
	public void setParamsGoods(Goods paramsGoods) {
		this.paramsGoods = paramsGoods;
	}
	
	public HashMap<String, Object> getGoodsReport() {
		return goodsReport;
	}
	
	public void setGoodsReport(HashMap<String, Object> goodsReport) {
		this.goodsReport = goodsReport;
	}
	
	/**
	 * @Title: exportSalesSum
	 * @Description: 导出销售汇总信息
	 * @return String
	 */
	public String exportSalesSum(){
		try{
			if (paramsSale==null) {
				paramsSale = new Sale();
			}
			//设置分页信息
			paramsSale.setStart(-1);
			//查询销售汇总
			List<Sale> sales = adminManager.listSalesSum(paramsSale,null); 
			
			report.put("sales", sales);
			
			//设置导出文件名
			setExportExcelName("销售汇总信息.xls");
		}
        catch(Exception e){
            setErrorReason("导出异常，请稍后再试", e);
            return ERROR;
        }
        return SUCCESS; 
	}
	
	/**
	 * 物资信息汇总导出excel
	 * @return
	 */
	public String exportGoodsSum(){
		try{
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			paramsGoods.setStart(-1);
			//查询销售汇总
			List<Goods> goods = adminManager.listGoodss(paramsGoods,null); 
			
			goodsReport.put("goodes", goods);
			
			//设置导出文件名
			setExportExcelName("物资库存信息汇总表.xls");
		}
        catch(Exception e){
            setErrorReason("导出异常，请稍后再试", e);
            return ERROR;
        }
        return SUCCESS; 
	}
	
	public HashMap<String, Object> getReport() {
		return report;
	}

	public void setReport(HashMap<String, Object> report) {
		this.report = report;
	}

	public Sale getParamsSale() {
		return paramsSale;
	}

	public void setParamsSale(Sale paramsSale) {
		this.paramsSale = paramsSale;
	}
	
	
	
}
