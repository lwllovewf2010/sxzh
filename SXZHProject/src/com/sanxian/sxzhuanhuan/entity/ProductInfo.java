package com.sanxian.sxzhuanhuan.entity;

/**   
 * @Title: CreativeInfo.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 商品数据信息
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:44:28 
 * @version V1.0   
 */
public class ProductInfo extends MetaData{
	
	private String id ; // "id":"444",
	private String goods_name ; // "goods_name":"Cookoo\u667a\u80fd\u84dd\u7259\u624b\u8868",
	private String province_id ; // "province_id":"1",
	private String province_name ; // "province_name":"广东省",//原产地
	private String city_id ; // "city_id":"102",
	private String goods_image ; // "goods_image":".\/uploadfile\/project\/\u79d1\u62801-1382856455.jpg",
	private String project_describe ; // "project_describe":"\r\n html describe \r\n<\/h2>", //商品详情
	private String goods_post_min ; // "goods_post_min":"15",  //订单满此数量即发货
	private String goods_status ; // "goods_status":"6",  //商品状态（ 6:销售中 不等6就是已下架）
	private String goods_price ; // "goods_price":"126", //商品售价
	private String goods_cost_price ; // "goods_cost_price":210, //商品原价
	private String goods_post_type ; // "goods_post_type":"1",  //发货方式 0=不邮寄 1=快递 2=平邮
	private String goods_post_free ; // "goods_post_free":"1", //是否包邮 1=大陆包邮 0=否
	private String goods_post_price ; // "goods_post_price":"126", //运费
	private String goods_sales_time ; // "goods_sales_time":"2014-1-1 10:10:01", //上架日期
	private String sales_num ; // "sales_num":"126", //总售出次数
	private String company_id ; // "company_id":"126", //所属公司id
	private String company_name ; // "company_name":"126",  //公司名
	private String attention_num ; // "attention_num":100  //关注商品人数
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the goods_name
	 */
	public String getGoods_name() {
		return goods_name;
	}
	/**
	 * @param goods_name the goods_name to set
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	/**
	 * @return the province_id
	 */
	public String getProvince_id() {
		return province_id;
	}
	/**
	 * @param province_id the province_id to set
	 */
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	/**
	 * @return the province_name
	 */
	public String getProvince_name() {
		return province_name;
	}
	/**
	 * @param province_name the province_name to set
	 */
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	/**
	 * @return the city_id
	 */
	public String getCity_id() {
		return city_id;
	}
	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	/**
	 * @return the goods_image
	 */
	public String getGoods_image() {
		return goods_image;
	}
	/**
	 * @param goods_image the goods_image to set
	 */
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	/**
	 * @return the project_describe
	 */
	public String getProject_describe() {
		return project_describe;
	}
	/**
	 * @param project_describe the project_describe to set
	 */
	public void setProject_describe(String project_describe) {
		this.project_describe = project_describe;
	}
	/**
	 * @return the goods_post_min
	 */
	public String getGoods_post_min() {
		return goods_post_min;
	}
	/**
	 * @param goods_post_min the goods_post_min to set
	 */
	public void setGoods_post_min(String goods_post_min) {
		this.goods_post_min = goods_post_min;
	}
	/**
	 * @return the goods_status
	 */
	public String getGoods_status() {
		return goods_status;
	}
	/**
	 * @param goods_status the goods_status to set
	 */
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	/**
	 * @return the goods_price
	 */
	public String getGoods_price() {
		return goods_price;
	}
	/**
	 * @param goods_price the goods_price to set
	 */
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	/**
	 * @return the goods_cost_price
	 */
	public String getGoods_cost_price() {
		return goods_cost_price;
	}
	/**
	 * @param goods_cost_price the goods_cost_price to set
	 */
	public void setGoods_cost_price(String goods_cost_price) {
		this.goods_cost_price = goods_cost_price;
	}
	/**
	 * @return the goods_post_type
	 */
	public String getGoods_post_type() {
		return goods_post_type;
	}
	/**
	 * @param goods_post_type the goods_post_type to set
	 */
	public void setGoods_post_type(String goods_post_type) {
		this.goods_post_type = goods_post_type;
	}
	/**
	 * @return the goods_post_free
	 */
	public String getGoods_post_free() {
		return goods_post_free;
	}
	/**
	 * @param goods_post_free the goods_post_free to set
	 */
	public void setGoods_post_free(String goods_post_free) {
		this.goods_post_free = goods_post_free;
	}
	/**
	 * @return the goods_post_price
	 */
	public String getGoods_post_price() {
		return goods_post_price;
	}
	/**
	 * @param goods_post_price the goods_post_price to set
	 */
	public void setGoods_post_price(String goods_post_price) {
		this.goods_post_price = goods_post_price;
	}
	/**
	 * @return the goods_sales_time
	 */
	public String getGoods_sales_time() {
		return goods_sales_time;
	}
	/**
	 * @param goods_sales_time the goods_sales_time to set
	 */
	public void setGoods_sales_time(String goods_sales_time) {
		this.goods_sales_time = goods_sales_time;
	}
	/**
	 * @return the sales_num
	 */
	public String getSales_num() {
		return sales_num;
	}
	/**
	 * @param sales_num the sales_num to set
	 */
	public void setSales_num(String sales_num) {
		this.sales_num = sales_num;
	}
	/**
	 * @return the company_id
	 */
	public String getCompany_id() {
		return company_id;
	}
	/**
	 * @param company_id the company_id to set
	 */
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return company_name;
	}
	/**
	 * @param company_name the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	/**
	 * @return the attention_num
	 */
	public String getAttention_num() {
		return attention_num;
	}
	/**
	 * @param attention_num the attention_num to set
	 */
	public void setAttention_num(String attention_num) {
		this.attention_num = attention_num;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", goods_name=" + goods_name
				+ ", province_id=" + province_id + ", province_name="
				+ province_name + ", city_id=" + city_id + ", goods_image="
				+ goods_image + ", project_describe=" + project_describe
				+ ", goods_post_min=" + goods_post_min + ", goods_status="
				+ goods_status + ", goods_price=" + goods_price
				+ ", goods_cost_price=" + goods_cost_price
				+ ", goods_post_type=" + goods_post_type + ", goods_post_free="
				+ goods_post_free + ", goods_post_price=" + goods_post_price
				+ ", goods_sales_time=" + goods_sales_time + ", sales_num="
				+ sales_num + ", company_id=" + company_id + ", company_name="
				+ company_name + ", attention_num=" + attention_num + "]";
	}
	
	
	
	
	
	//test -----
//	private String logo ; //LOGO
//	private int participate ; //参与人数
//	private int discuss ; //讨论数
//	private int days ; //已筹集的天数
//	private float money ; //已筹集的金额
//	public int getParticipate() {
//		return participate;
//	}
//	public void setParticipate(int participate) {
//		this.participate = participate;
//	}
//	public int getDiscuss() {
//		return discuss;
//	}
//	public void setDiscuss(int discuss) {
//		this.discuss = discuss;
//	}
//	public int getDays() {
//		return days;
//	}
//	public void setDays(int days) {
//		this.days = days;
//	}
//	public float getMoney() {
//		return money;
//	}
//	public void setMoney(float money) {
//		this.money = money;
//	}
//	public String getLogo() {
//		return logo;
//	}
//	public void setLogo(String logo) {
//		this.logo = logo;
//	}
	
}
