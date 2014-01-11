package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: OrderBean  
* @Description: 我的订单实体类 
* * @author honaf
* @date 2014-1-6 下午3:03:52
 */
public class CollectionInfo implements Serializable {


	private static final long serialVersionUID = 1L;

	private String order_id;// 订单号
	private String avatar;// 商品图片
	private String unit_price;// 单价
	private String total_price;// 总价
	private String number;// 数量
	private String goods_name;// 商品名称
	private String transaction_status;// 交易状态
	public CollectionInfo(){
		super();
	}
	public CollectionInfo(String order_id, String avatar, String unit_price, String total_price, String number, String goods_name, String transaction_status) {
		super();
		this.order_id = order_id;
		this.avatar = avatar;
		this.unit_price = unit_price;
		this.total_price = total_price;
		this.number = number;
		this.goods_name = goods_name;
		this.transaction_status = transaction_status;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getTransaction_status() {
		return transaction_status;
	}
	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}
	
}
