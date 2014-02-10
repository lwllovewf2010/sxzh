package com.sanxian.sxzhuanhuan.entity;

import java.util.ArrayList;

/**
 * @Title: MetaData.java
 * @Package com.sanxian.sxzhuanhuan.entity
 * @Description: 商品属性
 * @author yuanqk@sanxian.com
 * @date 2014-1-3 下午5:34:21
 * @version V1.0
 */
public class GoodsItemDetails {

	private String id; //
	private String goods_name; // 名称
	private String province_id; //
	private String province_name; // 原产地
	private String city_id; //
	private String goods_image; //
	private String project_describe; // 商品详情
	private String goods_post_min;// 订单满此数量即发货
	private String goods_status;// 商品状态（ 6:销售中 不等6就是已下架）
	private String goods_price;// 商品售价
	private String goods_cost_price;// 商品原价
	private String goods_post_type;// 发货方式 0=不邮寄 1=快递 2=平邮
	private String goods_post_free; // 是否包邮 1=大陆包邮 0=否
	private String goods_post_price; // 运费
	private String goods_sales_time; // 上架日期
	private String sales_num; // 总售出次数
	private String company_id;// 所属公司id
	private String company_name; // 公司名
	private String attention_num; // 关注商品人数
	private String is_attention;// 当前登记用户是否关注 true/false
	private String com_project;//项目ID
	private String project_name;//"项目名称",	//原项目名
	private String proj_purchase_user_num;//参与人数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public String getProject_describe() {
		return project_describe;
	}
	public void setProject_describe(String project_describe) {
		this.project_describe = project_describe;
	}
	public String getGoods_post_min() {
		return goods_post_min;
	}
	public void setGoods_post_min(String goods_post_min) {
		this.goods_post_min = goods_post_min;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_cost_price() {
		return goods_cost_price;
	}
	public void setGoods_cost_price(String goods_cost_price) {
		this.goods_cost_price = goods_cost_price;
	}
	public String getGoods_post_type() {
		return goods_post_type;
	}
	public void setGoods_post_type(String goods_post_type) {
		this.goods_post_type = goods_post_type;
	}
	public String getGoods_post_free() {
		return goods_post_free;
	}
	public void setGoods_post_free(String goods_post_free) {
		this.goods_post_free = goods_post_free;
	}
	public String getGoods_post_price() {
		return goods_post_price;
	}
	public void setGoods_post_price(String goods_post_price) {
		this.goods_post_price = goods_post_price;
	}
	public String getGoods_sales_time() {
		return goods_sales_time;
	}
	public void setGoods_sales_time(String goods_sales_time) {
		this.goods_sales_time = goods_sales_time;
	}
	public String getSales_num() {
		return sales_num;
	}
	public void setSales_num(String sales_num) {
		this.sales_num = sales_num;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getAttention_num() {
		return attention_num;
	}
	public void setAttention_num(String attention_num) {
		this.attention_num = attention_num;
	}
	public String isIs_attention() {
		return is_attention;
	}
	public void setIs_attention(String is_attention) {
		this.is_attention = is_attention;
	}
	public String getCom_project() {
		return com_project;
	}
	public void setCom_project(String com_project) {
		this.com_project = com_project;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProj_purchase_user_num() {
		return proj_purchase_user_num;
	}
	public void setProj_purchase_user_num(String proj_purchase_user_num) {
		this.proj_purchase_user_num = proj_purchase_user_num;
	}
	public String getIs_attention() {
		return is_attention;
	}
	
}
