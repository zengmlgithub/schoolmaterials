package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.Orders;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class OrdersDao {

	public int addOrders(Orders orders, Connection conn){
		String sql = "INSERT INTO orders(orders_id,goods_id,orders_count,orders_admin,orders_date) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			orders.getGoods_id(),
			orders.getOrders_count(),
			orders.getOrders_admin(),
			orders.getOrders_date()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delOrders(String orders_id, Connection conn){
		String sql = "DELETE FROM orders WHERE orders_id=?";

		Object[] params = new Object[] { new Integer(orders_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delOrderss(String[] orders_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <orders_ids.length; i++) {
			sBuilder.append("?");
			if (i !=orders_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM orders WHERE orders_id IN(" +sBuilder.toString()+")";

		Object[] params = orders_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateOrders(Orders orders, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE orders SET orders_id = " + orders.getOrders_id() +" ");
		
		sBuilder.append(" where orders_id = " + orders.getOrders_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public Orders getOrders(Orders orders, Connection conn){
		Orders _orders=new Orders();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT o.*,g.goods_no,g.goods_name,gt.goods_type_name From orders o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");
		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Orders.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _orders = (Orders)list.get(0);
		}
		return _orders;
	}

	public List<Orders>  listOrderss(Orders orders, Connection conn){
		List<Orders> orderss = new ArrayList<Orders>();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT o.*,g.goods_no,g.goods_name,gt.goods_type_name From orders o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");

		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getGoods_no())) {
			sBuilder.append(" and g.goods_no like '%" + orders.getGoods_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + orders.getGoods_name() +"%' ");
		}
		if (orders.getGoods_type_id()!=0) {
			sBuilder.append(" and gt.goods_type_id = " + orders.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_admin())) {
			sBuilder.append(" and o.orders_admin like '%" + orders.getOrders_admin() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_min())) {
			sBuilder.append(" and o.orders_date >= str_to_date('" + orders.getOrders_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_max())) {
			sBuilder.append(" and o.orders_date <= str_to_date('" + orders.getOrders_date_max() +"','%Y-%m-%d') ");
		}
		sBuilder.append(" order by orders_date desc,orders_id asc) t");

		if (orders.getStart() != -1) {
			sBuilder.append(" limit " + orders.getStart() + "," + orders.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Orders.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			orderss = new ArrayList<Orders>();
			for (Object object : list) {
				orderss.add((Orders)object);
			}
		}
		return orderss;
	}

	public int  listOrderssCount(Orders orders, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) From orders o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");

		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getGoods_no())) {
			sBuilder.append(" and g.goods_no like '%" + orders.getGoods_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + orders.getGoods_name() +"%' ");
		}
		if (orders.getGoods_type_id()!=0) {
			sBuilder.append(" and gt.goods_type_id = " + orders.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_admin())) {
			sBuilder.append(" and o.orders_admin like '%" + orders.getOrders_admin() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_min())) {
			sBuilder.append(" and o.orders_date >= str_to_date('" + orders.getOrders_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_date_max())) {
			sBuilder.append(" and o.orders_date <= str_to_date('" + orders.getOrders_date_max() +"','%Y-%m-%d') ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
