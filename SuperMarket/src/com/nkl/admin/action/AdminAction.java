package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsType;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sale;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

	//抓取页面参数
	User paramsUser;
	Goods paramsGoods;
	GoodsType paramsGoodsType;
	Sale paramsSale;
	Orders paramsOrders;
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		setSuccessTip("编辑成功", "modifyInfo.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		setSuccessTip("修改成功", "modifyPwd.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("添加用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("删除用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listGoodsTypes
	 * @Description: 查询商品类型
	 * @return String
	 */
	public String listGoodsTypes(){
		try {
			if (paramsGoodsType==null) {
				paramsGoodsType = new GoodsType();
			}
			
			//设置分页信息
			setPagination(paramsGoodsType);
			//总的条数
			int[] sum={0};
			//查询商品类型列表
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(paramsGoodsType,sum); 
			Param.setAttribute("goodsTypes", goodsTypes);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询商品类型异常", "main.jsp");
			return "infoTip";
		}
		
		return "goodsTypeShow";
	}
	
	/**
	 * @Title: addGoodsTypeShow
	 * @Description: 显示添加商品类型页面
	 * @return String
	 */
	public String addGoodsTypeShow(){
		return "goodsTypeEdit";
	}
	
	/**
	 * @Title: addGoodsType
	 * @Description: 添加商品类型
	 * @return String
	 */
	public String addGoodsType(){
		try {
			//检查商品类型是否存在
			GoodsType goodsType = new GoodsType();
			goodsType.setGoods_type_name(paramsGoodsType.getGoods_type_name());
			goodsType = adminManager.queryGoodsType(goodsType);
			if (goodsType!=null) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("goodsType", paramsGoodsType);
				return "goodsTypeEdit";
			}
			
			 //添加商品类型
			adminManager.addGoodsType(paramsGoodsType);
			
			setSuccessTip("添加成功", "Admin_listGoodsTypes.action");
		} catch (Exception e) {
			setErrorTip("添加商品类型异常", "Admin_listGoodsTypes.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editGoodsType
	 * @Description: 编辑商品类型
	 * @return String
	 */
	public String editGoodsType(){
		try {
			 //得到商品类型
			GoodsType goodsType = adminManager.queryGoodsType(paramsGoodsType);
			Param.setAttribute("goodsType", goodsType);
			
		} catch (Exception e) {
			setErrorTip("查询商品类型异常", "Admin_listGoodsTypes.action");
			return "infoTip";
		}
		
		return "goodsTypeEdit";
	}
	
	/**
	 * @Title: saveGoodsType
	 * @Description: 保存编辑商品类型
	 * @return String
	 */
	public String saveGoodsType(){
		try {
			//检查商品类型是否存在
			GoodsType goodsType = new GoodsType();
			goodsType.setGoods_type_name(paramsGoodsType.getGoods_type_name());
			goodsType = adminManager.queryGoodsType(goodsType);
			if (goodsType!=null && goodsType.getGoods_type_id()!=paramsGoodsType.getGoods_type_id()) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("goodsType", paramsGoodsType);
				return "goodsTypeEdit";
			}
			
			 //保存编辑商品类型
			adminManager.updateGoodsType(paramsGoodsType);
			
			setSuccessTip("编辑成功", "Admin_listGoodsTypes.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("goodsType", paramsGoodsType);
			return "goodsTypeEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delGoodsTypes
	 * @Description: 删除商品类型
	 * @return String
	 */
	public String delGoodsTypes(){
		try {
			 //删除商品类型
			adminManager.delGoodsTypes(paramsGoodsType);
			
			setSuccessTip("删除商品类型成功", "Admin_listGoodsTypes.action");
		} catch (Exception e) {
			setErrorTip("删除商品类型异常", "Admin_listGoodsTypes.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询商品
	 * @return String
	 */
	public String listGoodss(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "main.jsp");
			return "infoTip";
		}
		
		return "goodsShow";
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询库存
	 * @return String
	 */
	public String listGoodsSum(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "main.jsp");
			return "infoTip";
		}
		
		return "listGoodsSum";
	}
	
	/**
	 * 出库物资汇总
	 * @return
	 */
	public String listOrdersSum(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品进货列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品进货异常", "main.jsp");
			return "infoTip";
		}
		
		return "listOrdersSum";
	}
	
	
	/**
	 * @Title: addGoodsShow
	 * @Description: 显示添加商品页面
	 * @return String
	 */
	public String addGoodsShow(){
		//查询商品类型
		GoodsType goodsType = new GoodsType();
		goodsType.setStart(-1);
		
		List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
//		Param.setAttribute("goodsTypes", goodsTypes);
		Param.setSession("goodsTypes", goodsTypes);
		return "goodsEdit";
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @return String
	 */
	public String addGoods(){
		try {
			//检查商品编号是否存在
			Goods goods = new Goods();
			goods.setGoods_no(paramsGoods.getGoods_no());
			goods = adminManager.queryGoods(goods);
			if (goods!=null) {
				tip="失败，该商品编号已经存在！";
				Param.setAttribute("goods", paramsGoods);
				return "goodsEdit";
			}
			
			 //添加商品
			adminManager.addGoods(paramsGoods);
			
		} catch (Exception e) {
			setErrorTip("添加商品异常", "Admin_listGoodss.action");
		}
		setSuccessTip("添加商品成功", "Admin_listGoodss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询商品详情
	 * @return String
	 */
	public String queryGoods(){
		try {
			 //得到商品
			Goods goods = adminManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "Admin_listGoodss.action");
			return "infoTip";
		}
		
		return "goodsDetail";
	}
	 
	/**
	 * @Title: editGoods
	 * @Description: 编辑商品
	 * @return String
	 */
	public String editGoods(){
		try {
			 //得到商品
			Goods goods = adminManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品异常", "Admin_listGoodss.action");
			return "infoTip";
		}
		
		return "goodsEdit";
	}
	
	/**
	 * @Title: saveGoods
	 * @Description: 保存编辑商品
	 * @return String
	 */
	public String saveGoods(){
		try {
			 //保存编辑商品
			adminManager.updateGoods(paramsGoods);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("goods", paramsGoods);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
			return "goodsEdit";
		}
		setSuccessTip("编辑商品成功", "Admin_listGoodss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delGoodss
	 * @Description: 删除商品
	 * @return String
	 */
	public String delGoodss(){
		try {
			 //删除商品
			adminManager.delGoodss(paramsGoods);
			
		} catch (Exception e) {
			setErrorTip("删除商品异常", "Admin_listGoodss.action");
		}
		setSuccessTip("删除商品成功", "Admin_listGoodss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询商品进货
	 * @return String
	 */
	public String listOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品进货列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品进货异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: addOrdersShow
	 * @Description: 显示添加商品进货页面
	 * @return String
	 */
	public String addOrdersShow(){
		//查询进货的商品
		Goods goods = new Goods();
		goods.setStart(-1);
		List<Goods> goodss = adminManager.listGoodss(goods, null);
		if (goodss==null) {
			goodss = new ArrayList<Goods>();
		}
		Param.setAttribute("goodss", goodss);
		
		//查询商品类型
		GoodsType goodsType = new GoodsType();
		goodsType.setStart(-1);
		List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
		Param.setAttribute("goodsTypes", goodsTypes);
		
		return "ordersEdit";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加商品进货
	 * @return String
	 */
	public String addOrders(){
		try {
			//添加商品进货
			adminManager.addOrders(paramsOrders);
			
			setSuccessTip("商品进货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("商品进货异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	} 
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除商品进货
	 * @return String
	 */
	public String delOrderss(){
		try {
			 //删除商品进货
			adminManager.delOrderss(paramsOrders);
			
			setSuccessTip("删除商品进货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("删除商品进货异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listSales
	 * @Description: 查询商品销售
	 * @return String
	 */
	public String listSales(){
		try {
			if (paramsSale==null) {
				paramsSale = new Sale();
			}
			//设置分页信息
			setPagination(paramsSale);
			//总的条数
			int[] sum={0};
			//查询商品销售列表
			List<Sale> sales = adminManager.listSales(paramsSale,sum); 
			
			Param.setAttribute("sales", sales);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品销售异常", "main.jsp");
			return "infoTip";
		}
		
		return "saleShow";
	}
	
	/**
	 * 查询库存所有商品
	 * 
	 * @author zengmaolin 2019-01-12
	 *     查询所有商品
	 * @return
	 */
//	public String listGoodsSum(){
//		try {
//			if (paramsGoods==null) {
//				paramsGoods = new Goods();
//			}
//			//设置分页信息
//			setPagination(paramsGoods);
//			//总的条数
//			int[] sum={0};
//			//查询商品销售列表
//			List<Goods> sales = adminManager.listGoodss(paramsGoods, sum); 
//			
//			Param.setAttribute("goodss", sales);
//			setTotalCount(sum[0]);
//			
//			//查询商品类型
//			GoodsType goodsType = new GoodsType();
//			goodsType.setStart(-1);
//			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
//			Param.setAttribute("goodsTypes", goodsTypes);
//			
//		} catch (Exception e) {
//			setErrorTip("查询商品销售异常", "main.jsp");
//			return "infoTip";
//		}
//		
//		return "goodsShow";
//	}
	
	/**
	 * @Title: listSalesSum
	 * @Description: 查询商品销售汇总
	 * @return String
	 */
	public String listSalesSum(){
		try {
			if (paramsSale==null) {
				paramsSale = new Sale();
			}
			//设置分页信息
			setPagination(paramsSale);
			//总的条数
			int[] sum={0};
			//查询商品销售列表
			List<Sale> sales = adminManager.listSalesSum(paramsSale,sum); 
			
			Param.setAttribute("sales", sales);
			setTotalCount(sum[0]);
			
			//查询商品类型
			GoodsType goodsType = new GoodsType();
			goodsType.setStart(-1);
			List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
			Param.setAttribute("goodsTypes", goodsTypes);
			
		} catch (Exception e) {
			setErrorTip("查询商品销售汇总异常", "main.jsp");
			return "infoTip";
		}
		
		return "saleSumShow";
	}
	
	/**
	 * @Title: addSaleShow
	 * @Description: 显示添加商品销售页面
	 * @return String
	 */
	public String addSaleShow(){
		//查询销售的商品
		Goods goods = new Goods();
		goods.setStart(-1);
		List<Goods> goodss = adminManager.listGoodss(goods, null);
		if (goodss==null) {
			goodss = new ArrayList<Goods>();
		}
		Param.setAttribute("goodss", goodss);
		
		//查询商品类型
		GoodsType goodsType = new GoodsType();
		goodsType.setStart(-1);
		List<GoodsType> goodsTypes = adminManager.listGoodsTypes(goodsType, null);
		Param.setAttribute("goodsTypes", goodsTypes);
		
		return "saleEdit";
	}
	
	/**
	 * @Title: addSale
	 * @Description: 添加商品销售
	 * @return String
	 */
	public String addSale(){
		try {
			//添加商品销售
			adminManager.addSale(paramsSale);
			setSuccessTip("商品出库成功", "Admin_listSales.action");
		} catch (Exception e) {
			setErrorTip("商品出库异常", "Admin_listSales.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delSales
	 * @Description: 删除商品销售
	 * @return String
	 */
	public String delSales(){
		try {
			 //删除商品销售
			adminManager.delSales(paramsSale);
			
			setSuccessTip("删除商品销售成功", "Admin_listSales.action");
		} catch (Exception e) {
			setErrorTip("删除商品销售异常", "Admin_listSales.action");
		}
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Goods getParamsGoods() {
		return paramsGoods;
	}

	public void setParamsGoods(Goods paramsGoods) {
		this.paramsGoods = paramsGoods;
	}

	public GoodsType getParamsGoodsType() {
		return paramsGoodsType;
	}

	public void setParamsGoodsType(GoodsType paramsGoodsType) {
		this.paramsGoodsType = paramsGoodsType;
	}

	public Sale getParamsSale() {
		return paramsSale;
	}

	public void setParamsSale(Sale paramsSale) {
		this.paramsSale = paramsSale;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}


}
