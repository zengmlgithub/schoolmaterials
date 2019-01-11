package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.Sale;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class SaleDao {

	public int addSale(Sale sale, Connection conn){
		String sql = "INSERT INTO sale(sale_id,goods_id,sale_count,sale_admin,sale_date,sale_receiver,receiver_tel,use_desc,goods_count) values(null,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			sale.getGoods_id(),
			sale.getSale_count(),
			sale.getSale_admin(),
			sale.getSale_date(),
			sale.getSale_receiver(),
			sale.getReceiver_tel(),
			sale.getUse_desc(),
			sale.getGoods_count()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSale(String sale_id, Connection conn){
		String sql = "DELETE FROM sale WHERE sale_id=?";

		Object[] params = new Object[] { new Integer(sale_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSales(String[] sale_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <sale_ids.length; i++) {
			sBuilder.append("?");
			if (i !=sale_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sale WHERE sale_id IN(" +sBuilder.toString()+")";

		Object[] params = sale_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSale(Sale sale, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sale SET sale_id = " + sale.getSale_id() +" ");
		
		sBuilder.append(" where sale_id = " + sale.getSale_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public Sale getSale(Sale sale, Connection conn){
		Sale _sale=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT o.*,g.goods_no,g.goods_name,gt.goods_type_name From sale o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");
		if (sale.getSale_id()!=0) {
			sBuilder.append(" and sale_id = " + sale.getSale_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Sale.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sale = (Sale)list.get(0);
		}
		return _sale;
	}

	public List<Sale>  listSales(Sale sale, Connection conn){
		List<Sale> sales = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT o.*,g.goods_no,g.goods_name,gt.goods_type_name From sale o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");

		if (sale.getSale_id()!=0) {
			sBuilder.append(" and sale_id = " + sale.getSale_id() +" ");
		}
		if (!StringUtil.isEmptyString(sale.getGoods_no())) {
			sBuilder.append(" and g.goods_no like '%" + sale.getGoods_no() +"%' ");
		}
		
		if (!StringUtil.isEmptyString(sale.getSale_receiver())) {
			sBuilder.append(" and g.sale_receiver like '%" + sale.getGoods_no() +"%' ");
		}
		
		if (!StringUtil.isEmptyString(sale.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + sale.getGoods_name() +"%' ");
		}
		if (sale.getGoods_type_id()!=0) {
			sBuilder.append(" and gt.goods_type_id = " + sale.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_admin())) {
			sBuilder.append(" and o.sale_admin like '%" + sale.getSale_admin() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_min())) {
			sBuilder.append(" and o.sale_date >= str_to_date('" + sale.getSale_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_max())) {
			sBuilder.append(" and o.sale_date <= str_to_date('" + sale.getSale_date_max() +"','%Y-%m-%d') ");
		}
		sBuilder.append(" order by sale_date desc,sale_id asc) t");

		if (sale.getStart() != -1) {
			sBuilder.append(" limit " + sale.getStart() + "," + sale.getLimit());
		}
		
		List<Object> list = BaseDao.executeQuery(Sale.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sales = new ArrayList<Sale>();
			for (Object object : list) {
				sales.add((Sale)object);
			}
		}
		return sales;
	}

	public int  listSalesCount(Sale sale, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) From sale o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");

		if (sale.getSale_id()!=0) {
			sBuilder.append(" and sale_id = " + sale.getSale_id() +" ");
		}
		if (!StringUtil.isEmptyString(sale.getGoods_no())) {
			sBuilder.append(" and g.goods_no like '%" + sale.getGoods_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + sale.getGoods_name() +"%' ");
		}
		if (sale.getGoods_type_id()!=0) {
			sBuilder.append(" and gt.goods_type_id = " + sale.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_admin())) {
			sBuilder.append(" and o.sale_admin like '%" + sale.getSale_admin() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_min())) {
			sBuilder.append(" and o.sale_date >= str_to_date('" + sale.getSale_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_max())) {
			sBuilder.append(" and o.sale_date <= str_to_date('" + sale.getSale_date_max() +"','%Y-%m-%d') ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public List<Sale>  listSalesSum(Sale sale, Connection conn){
		List<Sale> sales = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT g.goods_no,g.goods_name,gt.goods_type_name,sum(o.sale_count) sale_count From sale o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");

		if (!StringUtil.isEmptyString(sale.getGoods_no())) {
			sBuilder.append(" and g.goods_no like '%" + sale.getGoods_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + sale.getGoods_name() +"%' ");
		}
		if (sale.getGoods_type_id()!=0) {
			sBuilder.append(" and gt.goods_type_id = " + sale.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_admin())) {
			sBuilder.append(" and o.sale_admin like '%" + sale.getSale_admin() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_min())) {
			sBuilder.append(" and o.sale_date >= str_to_date('" + sale.getSale_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_max())) {
			sBuilder.append(" and o.sale_date <= str_to_date('" + sale.getSale_date_max() +"','%Y-%m-%d') ");
		}
		sBuilder.append(" group by g.goods_no,g.goods_name,gt.goods_type_name ");
		sBuilder.append(" order by g.goods_no,g.goods_name,gt.goods_type_name asc) t");

		if (sale.getStart() != -1) {
			sBuilder.append(" limit " + sale.getStart() + "," + sale.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Sale.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sales = new ArrayList<Sale>();
			for (Object object : list) {
				sales.add((Sale)object);
			}
		}
		return sales;
	}

	public int  listSalesCountSum(Sale sale, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) From sale o ");
		sBuilder.append("  join goods g on g.goods_id=o.goods_id join goods_type gt on g.goods_type_id=gt.goods_type_id WHERE 1=1");

		if (!StringUtil.isEmptyString(sale.getGoods_no())) {
			sBuilder.append(" and g.goods_no like '%" + sale.getGoods_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + sale.getGoods_name() +"%' ");
		}
		if (sale.getGoods_type_id()!=0) {
			sBuilder.append(" and gt.goods_type_id = " + sale.getGoods_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_admin())) {
			sBuilder.append(" and o.sale_admin like '%" + sale.getSale_admin() +"%' ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_min())) {
			sBuilder.append(" and o.sale_date >= str_to_date('" + sale.getSale_date_min() +"','%Y-%m-%d') ");
		}
		if (!StringUtil.isEmptyString(sale.getSale_date_max())) {
			sBuilder.append(" and o.sale_date <= str_to_date('" + sale.getSale_date_max() +"','%Y-%m-%d') ");
		}
		sBuilder.append(" group by g.goods_no,g.goods_name,gt.goods_type_name ");

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
