package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Sale extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int sale_id; // 
	private int goods_id; // 
	private int sale_count;
	private String sale_admin; // 
	private String sale_date; //
	
	private String goods_no; // 
	private String goods_name; //
	private int goods_type_id; // 
	private String goods_type_name; // 
	private String sale_date_min;
	private String sale_date_max;
	
	private String ids;
	
	/**
	 * modified by zengmaolin
	 * 
	 * 2018-09-30
	 * 
	 *     添加领用联系方式
	 * 
	 */
	private String sale_receiver; //领用人姓名
	private String receiver_tel; //领用人电话
	private String use_desc;//使用描述
	
	
	/**
	 * modified by zengmaolin
	 * 2019-01-11
	 * @return
	 */
	
	private int goods_count; //当前库存量
	
	
	public int getGoods_count() {
		return goods_count;
	}
	
	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}
	
	public String getUse_desc() {
		return use_desc;
	}

	public void setUse_desc(String use_desc) {
		this.use_desc = use_desc;
	}

	public String getReceiver_tel() {
		return receiver_tel;
	}

	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}

	public int getSale_id() {
		return sale_id;
	}

	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	
	public String getSale_receiver() {
		return sale_receiver;
	}
	
	public void setSale_receiver(String sale_receiver) {
		this.sale_receiver = sale_receiver;
	}

	public String getSale_date() {
		return sale_date;
	}

	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}


	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSale_date_min() {
		return sale_date_min;
	}

	public void setSale_date_min(String sale_date_min) {
		this.sale_date_min = sale_date_min;
	}

	public String getSale_date_max() {
		return sale_date_max;
	}

	public void setSale_date_max(String sale_date_max) {
		this.sale_date_max = sale_date_max;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getSale_count() {
		return sale_count;
	}

	public void setSale_count(int sale_count) {
		this.sale_count = sale_count;
	}

	public String getSale_admin() {
		return sale_admin;
	}

	public void setSale_admin(String sale_admin) {
		this.sale_admin = sale_admin;
	}

	public String getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_type_id() {
		return goods_type_id;
	}

	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}

	public String getGoods_type_name() {
		return goods_type_name;
	}

	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}

	 

}
