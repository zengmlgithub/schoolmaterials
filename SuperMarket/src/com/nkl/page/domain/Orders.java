package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Orders extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int orders_id; // 
	private int goods_id; // 
	private int orders_count; // 
	private String orders_admin;
	private String orders_date;
	
	private String goods_no; // 
	private String goods_name; //
	private int goods_type_id; // 
	private String goods_type_name; // 
	private String orders_date_min;
	private String orders_date_max;
	
	private String ids;
	
	public int getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrders_date_min() {
		return orders_date_min;
	}

	public void setOrders_date_min(String orders_date_min) {
		this.orders_date_min = orders_date_min;
	}

	public String getOrders_date_max() {
		return orders_date_max;
	}

	public void setOrders_date_max(String orders_date_max) {
		this.orders_date_max = orders_date_max;
	}
	
	public int getOrders_count() {
		return orders_count;
	}
	public void setOrders_count(int orders_count) {
		this.orders_count = orders_count;
	}
	public String getOrders_admin() {
		return orders_admin;
	}
	public void setOrders_admin(String orders_admin) {
		this.orders_admin = orders_admin;
	}
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	public String getGoods_type_name() {
		return goods_type_name;
	}
	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}

	public int getGoods_type_id() {
		return goods_type_id;
	}

	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}

	 

}
