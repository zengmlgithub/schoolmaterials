package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Goods extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int goods_id; // 
	private String goods_no; // 
	private String goods_name; // 
	private int goods_type_id; // 
	private double goods_price; // 
	private int goods_count; // 
	private String goods_desc; // 
	
	private String goods_type_name; // 
	private String ids;

	
	
	public Goods(int goods_id, String goods_no, String goods_name, int goods_type_id, double goods_price,
			int goods_count, String goods_desc, String goods_type_name, String ids) {
		super();
		this.goods_id = goods_id;
		this.goods_no = goods_no;
		this.goods_name = goods_name;
		this.goods_type_id = goods_type_id;
		this.goods_price = goods_price;
		this.goods_count = goods_count;
		this.goods_desc = goods_desc;
		this.goods_type_name = goods_type_name;
		this.ids = ids;
	}
	
	

	public Goods() {
		super();
	}



	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_type_id() {
		return goods_type_id;
	}

	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_desc() {
		return goods_desc;
	}

	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}

	public String getGoods_type_name() {
		return goods_type_name;
	}

	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

	public int getGoods_count() {
		return goods_count;
	}

	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}

}
