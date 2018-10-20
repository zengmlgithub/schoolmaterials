package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.GoodsDao;
import com.nkl.page.dao.GoodsTypeDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.SaleDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsType;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sale;
import com.nkl.page.domain.User;

public class AdminManager {

	UserDao userDao = new UserDao();
	GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	GoodsDao goodsDao = new GoodsDao();
	OrdersDao ordersDao = new OrdersDao();
	SaleDao saleDao = new SaleDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		Logger logger = Logger.getLogger(AdminManager.class);
		logger.info("conn:" + conn);
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listGoodsTypes
	 * @Description: 商品类型查询
	 * @param goodsType
	 * @return List<GoodsType>
	 */
	public List<GoodsType> listGoodsTypes(GoodsType goodsType, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = goodsTypeDao.listGoodsTypesCount(goodsType, conn);
		}
		List<GoodsType> goodsTypes = goodsTypeDao.listGoodsTypes(goodsType, conn);

		BaseDao.closeDB(null, null, conn);
		return goodsTypes;
	}

	/**
	 * @Title: queryGoodsType
	 * @Description: 商品类型查询
	 * @param goodsType
	 * @return GoodsType
	 */
	public GoodsType queryGoodsType(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		GoodsType _goodsType = goodsTypeDao.getGoodsType(goodsType, conn);
		BaseDao.closeDB(null, null, conn);
		return _goodsType;
	}

	/**
	 * @Title: addGoodsType
	 * @Description: 添加商品类型
	 * @param goodsType
	 * @return void
	 */
	public void addGoodsType(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		goodsTypeDao.addGoodsType(goodsType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateGoodsType
	 * @Description: 更新商品类型信息
	 * @param goodsType
	 * @return void
	 */
	public void updateGoodsType(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		goodsTypeDao.updateGoodsType(goodsType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delGoodsType
	 * @Description: 删除商品类型信息
	 * @param goodsType
	 * @return void
	 */
	public void delGoodsTypes(GoodsType goodsType) {
		Connection conn = BaseDao.getConnection();
		goodsTypeDao.delGoodsTypes(goodsType.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 商品查询
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods> listGoodss(Goods goods, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = goodsDao.listGoodssCount(goods, conn);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods, conn);

		BaseDao.closeDB(null, null, conn);
		return goodss;
	}

	/**
	 * @Title: queryGoods
	 * @Description: 商品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		Goods _goods = goodsDao.getGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
		return _goods;
	}

	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @param goods
	 * @return void
	 */
	public void addGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		goodsDao.addGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateGoods
	 * @Description: 更新商品信息
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		goodsDao.updateGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delGoods
	 * @Description: 删除商品信息
	 * @param goods
	 * @return void
	 */
	public void delGoodss(Goods goods) {
		Connection conn = BaseDao.getConnection();
		goodsDao.delGoodss(goods.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 商品进货查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderss(Orders orders,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssCount(orders, conn);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders,conn);
		
		BaseDao.closeDB(null, null, conn);
		return orderss;
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 商品进货查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		Connection conn = BaseDao.getConnection();
		Orders _orders = ordersDao.getOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
		return _orders;
	}
	 
	/**
	 * @Title: addOrders
	 * @Description: 添加商品进货
	 * @param orders
	 * @return void
	 */
	public void addOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//添加商品销售
		ordersDao.addOrders(orders, conn);
		
		//更新商品实际库存数量
		Goods goods = new Goods();
		goods.setGoods_id(orders.getGoods_id());
		goods = goodsDao.getGoods(goods, conn);
		goods.setGoods_count(goods.getGoods_count()+orders.getOrders_count());
		goodsDao.updateGoods(goods, conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除商品进货信息
	 * @param orders
	 * @return void
	 */
	public void  delOrderss(Orders orders){
		Connection conn = BaseDao.getConnection();
		ordersDao.delOrderss(orders.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSales
	 * @Description: 商品销售查询
	 * @param sale
	 * @return List<Sale>
	 */
	public List<Sale> listSales(Sale sale, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = saleDao.listSalesCount(sale, conn);
		}
		List<Sale> sales = saleDao.listSales(sale, conn);

		BaseDao.closeDB(null, null, conn);
		return sales;
	}
	
	/**
	 * @Title: listSalesSum
	 * @Description: 商品销售汇总查询
	 * @param sale
	 * @return List<Sale>
	 */
	public List<Sale> listSalesSum(Sale sale, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = saleDao.listSalesCountSum(sale, conn);
		}
		List<Sale> sales = saleDao.listSalesSum(sale, conn);

		BaseDao.closeDB(null, null, conn);
		return sales;
	}

	/**
	 * @Title: querySale
	 * @Description: 商品销售查询
	 * @param sale
	 * @return Sale
	 */
	public Sale querySale(Sale sale) {
		Connection conn = BaseDao.getConnection();
		Sale _sale = saleDao.getSale(sale, conn);
		BaseDao.closeDB(null, null, conn);
		return _sale;
	}

	/**
	 * @Title: addSale
	 * @Description: 添加商品销售
	 * @param sale
	 * @return void
	 */
	public void addSale(Sale sale) {
		Connection conn = BaseDao.getConnection();
		//添加商品销售
		saleDao.addSale(sale, conn);
		
		//更新商品实际库存数量
		Goods goods = new Goods();
		goods.setGoods_id(sale.getGoods_id());
		goods = goodsDao.getGoods(goods, conn);
		goods.setGoods_count(goods.getGoods_count()-sale.getSale_count());
		goodsDao.updateGoods(goods, conn);
		
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delSale
	 * @Description: 删除商品销售信息
	 * @param sale
	 * @return void
	 */
	public void delSales(Sale sale) {
		Connection conn = BaseDao.getConnection();
		saleDao.delSales(sale.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	} 
	
	
}
