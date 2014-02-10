package com.sanxian.sxzhuanhuan.entity;

/**
 * @Title: CreativeInfo.java
 * @Package com.sanxian.sxzhuanhuan.entity
 * @Description: 项目数据信息
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:44:28
 * @version V1.0
 */
public class ProjectInfo extends MetaData {
	private String user_id; // true (string) 项目创建人openid
	private String project_name; // true (string) 项目名
	private int mode_id; // true (int) 类型：1=项目、2=创意、3=发明专利、4=产品
	private int category_id; // true (int) 行业id
	private int province_id; // true (int) 省id
	private int city_id; // true (int) 城市id
	private String project_explain; // false (string) 需求简介
	private String project_logo; // false (string) Logo
	private String project_video; // false (string) 网络视频地址URL
	private String project_describe; // true (string) 详细介绍
	private int project_state; // true (int) 项目状态 -2:删除 -1:审核失败 0:创建 1:提交审核
								// 2:审核成功 默认1
	private int project_days; // true (int) 筹集天数
	private String project_money_refund; // false (string) 退款说明
	private int project_step; // true (int) 默认4, 项目（产品）进行的步骤（1:项目新建 2:项目回报
								// 3:项目提交 4:审核中 5:预热中 6:筹集中 7:招标中
								// 8:公司创建成功（产品：9：发布招标 10：招标成功 11：全部发货完成交易））
	private int reward_mode; // true (int)0~100 我占股份的百分比，100=100%占股, 90=90%占股,
								// 10=10%占股, 此处手机界面显示与网页显示相反,接口已经做了转换，可直接使用回报设置项
	private int project_money; // true (int) 项目总金额
	private int reward_money; // true (int) 集资限额
	private String reward_name; // true (string) 回报标题
	private int reward_return_days; // true (int) 回报时间
	private int reward_limit; // true (int) 是否限定入股人数 1=限 0=不限
	private int reward_person; // true (int) 限定入股人数
	private int reward_post; // true (int) 邮寄 0=不包邮 1=快递 2=平邮/EMS
	private int reward_post_free ; //是否包邮 0=不包邮 1=大陆包邮
	private String reward_patent_id; // false (string) 专利编号
	private String reward_patent_name; // false (string) 专利名
	private String project_qq; // false (string) 发起人QQ
	private String project_mobile; // true (string) 发起人手机号
	private String project_realname; // true (string) 发起人真实姓名
	
	private String id ; 
	private String project_gender ; //"project_gender": "1",
	private String project_createtime; // "project_createtime": "2013-10-27 14:48:28",
	private String project_shentime ; //   "project_shentime": "2013-10-27 14:51:14",
	private String project_updatetime; //"project_updatetime": "0000-00-00 00:00:00",
	private String manager_name ; //"manager_name": "三弦科技",
	private String manager_id ;//"manager_id": "2",
	private String manager_time; //"manager_time": "2013-10-27 14:52:36",
	private String company_id  ;//          "company_id": "",
	private String prod_type ; //"prod_type": "1",
	private String prod_brand ;//"prod_brand": "",
	private String prod_sendaddr ;//"prod_sendaddr": "",
	private String showtop ;//"showtop": "0",
	private String convert ; //"convert": "0",
	private String project_fj; //"project_fj": null,
	private String project_zlh ; //"project_zlh": null,
	private String project_zlname ;//"project_zlname": null,
	private String purchase_money ; //"purchase_money": 0,
	private String purchase_user_num ; //"purchase_user_num":11,  //参与预购人数
	
	private String attention_nums ; //"attention_nums":111, //项目成员人数
	private String comment_nums ; //"comment_nums":111, //评论数
	private String project_topic_count ; //"project_topic_count":111, //话题讨论数
    private boolean purchase_already ; // "purchase_already":true, //当前登录open_id是否参与预购 true or false
    private String province_name ;
    private String city_name ;
    private String category_name ;
    private String reward_content ;
    
    public int getReward_post_free() {
		return reward_post_free;
	}
	public void setReward_post_free(int reward_post_free) {
		this.reward_post_free = reward_post_free;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	/**
	 * @return the city_name
	 */
	public String getCity_name() {
		return city_name;
	}
	/**
	 * @param city_name the city_name to set
	 */
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	/**
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * @param category_name the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * @return the reward_content
	 */
	public String getReward_content() {
		return reward_content;
	}
	/**
	 * @param reward_content the reward_content to set
	 */
	public void setReward_content(String reward_content) {
		this.reward_content = reward_content;
	}
	/**
	 * @return the purchase_already
	 */
	public boolean isPurchase_already() {
		return purchase_already;
	}
	/**
	 * @param purchase_already the purchase_already to set
	 */
	public void setPurchase_already(boolean purchase_already) {
		this.purchase_already = purchase_already;
	}
	/**
	 * @return the attention_nums
	 */
	public String getAttention_nums() {
		return attention_nums;
	}
	/**
	 * @param attention_nums the attention_nums to set
	 */
	public void setAttention_nums(String attention_nums) {
		this.attention_nums = attention_nums;
	}
	/**
	 * @return the comment_nums
	 */
	public String getComment_nums() {
		return comment_nums;
	}
	/**
	 * @param comment_nums the comment_nums to set
	 */
	public void setComment_nums(String comment_nums) {
		this.comment_nums = comment_nums;
	}
	/**
	 * @return the project_topic_count
	 */
	public String getProject_topic_count() {
		return project_topic_count;
	}
	/**
	 * @param project_topic_count the project_topic_count to set
	 */
	public void setProject_topic_count(String project_topic_count) {
		this.project_topic_count = project_topic_count;
	}
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
	 * @return the project_gender
	 */
	public String getProject_gender() {
		return project_gender;
	}
	/**
	 * @param project_gender the project_gender to set
	 */
	public void setProject_gender(String project_gender) {
		this.project_gender = project_gender;
	}
	/**
	 * @return the project_createtime
	 */
	public String getProject_createtime() {
		return project_createtime;
	}
	/**
	 * @param project_createtime the project_createtime to set
	 */
	public void setProject_createtime(String project_createtime) {
		this.project_createtime = project_createtime;
	}
	/**
	 * @return the project_shentime
	 */
	public String getProject_shentime() {
		return project_shentime;
	}
	/**
	 * @param project_shentime the project_shentime to set
	 */
	public void setProject_shentime(String project_shentime) {
		this.project_shentime = project_shentime;
	}
	/**
	 * @return the project_updatetime
	 */
	public String getProject_updatetime() {
		return project_updatetime;
	}
	/**
	 * @param project_updatetime the project_updatetime to set
	 */
	public void setProject_updatetime(String project_updatetime) {
		this.project_updatetime = project_updatetime;
	}
	/**
	 * @return the manager_name
	 */
	public String getManager_name() {
		return manager_name;
	}
	/**
	 * @param manager_name the manager_name to set
	 */
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	/**
	 * @return the manager_id
	 */
	public String getManager_id() {
		return manager_id;
	}
	/**
	 * @param manager_id the manager_id to set
	 */
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	/**
	 * @return the manager_time
	 */
	public String getManager_time() {
		return manager_time;
	}
	/**
	 * @param manager_time the manager_time to set
	 */
	public void setManager_time(String manager_time) {
		this.manager_time = manager_time;
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
	 * @return the prod_type
	 */
	public String getProd_type() {
		return prod_type;
	}
	/**
	 * @param prod_type the prod_type to set
	 */
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	/**
	 * @return the prod_brand
	 */
	public String getProd_brand() {
		return prod_brand;
	}
	/**
	 * @param prod_brand the prod_brand to set
	 */
	public void setProd_brand(String prod_brand) {
		this.prod_brand = prod_brand;
	}
	/**
	 * @return the prod_sendaddr
	 */
	public String getProd_sendaddr() {
		return prod_sendaddr;
	}
	/**
	 * @param prod_sendaddr the prod_sendaddr to set
	 */
	public void setProd_sendaddr(String prod_sendaddr) {
		this.prod_sendaddr = prod_sendaddr;
	}
	/**
	 * @return the showtop
	 */
	public String getShowtop() {
		return showtop;
	}
	/**
	 * @param showtop the showtop to set
	 */
	public void setShowtop(String showtop) {
		this.showtop = showtop;
	}
	/**
	 * @return the convert
	 */
	public String getConvert() {
		return convert;
	}
	/**
	 * @param convert the convert to set
	 */
	public void setConvert(String convert) {
		this.convert = convert;
	}
	/**
	 * @return the project_fj
	 */
	public String getProject_fj() {
		return project_fj;
	}
	/**
	 * @param project_fj the project_fj to set
	 */
	public void setProject_fj(String project_fj) {
		this.project_fj = project_fj;
	}
	/**
	 * @return the project_zlh
	 */
	public String getProject_zlh() {
		return project_zlh;
	}
	/**
	 * @param project_zlh the project_zlh to set
	 */
	public void setProject_zlh(String project_zlh) {
		this.project_zlh = project_zlh;
	}
	/**
	 * @return the project_zlname
	 */
	public String getProject_zlname() {
		return project_zlname;
	}
	/**
	 * @param project_zlname the project_zlname to set
	 */
	public void setProject_zlname(String project_zlname) {
		this.project_zlname = project_zlname;
	}
	/**
	 * @return the purchase_money
	 */
	public String getPurchase_money() {
		return purchase_money;
	}
	/**
	 * @param purchase_money the purchase_money to set
	 */
	public void setPurchase_money(String purchase_money) {
		this.purchase_money = purchase_money;
	}
	/**
	 * @return the purchase_user_num
	 */
	public String getPurchase_user_num() {
		return purchase_user_num;
	}
	/**
	 * @param purchase_user_num the purchase_user_num to set
	 */
	public void setPurchase_user_num(String purchase_user_num) {
		this.purchase_user_num = purchase_user_num;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the project_name
	 */
	public String getProject_name() {
		return project_name;
	}
	/**
	 * @param project_name the project_name to set
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	/**
	 * @return the mode_id
	 */
	public int getMode_id() {
		return mode_id;
	}
	/**
	 * @param mode_id the mode_id to set
	 */
	public void setMode_id(int mode_id) {
		this.mode_id = mode_id;
	}
	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	/**
	 * @return the province_id
	 */
	public int getProvince_id() {
		return province_id;
	}
	/**
	 * @param province_id the province_id to set
	 */
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	/**
	 * @return the city_id
	 */
	public int getCity_id() {
		return city_id;
	}
	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	/**
	 * @return the project_explain
	 */
	public String getProject_explain() {
		return project_explain;
	}
	/**
	 * @param project_explain the project_explain to set
	 */
	public void setProject_explain(String project_explain) {
		this.project_explain = project_explain;
	}
	/**
	 * @return the project_logo
	 */
	public String getProject_logo() {
		return project_logo;
	}
	/**
	 * @param project_logo the project_logo to set
	 */
	public void setProject_logo(String project_logo) {
		this.project_logo = project_logo;
	}
	/**
	 * @return the project_video
	 */
	public String getProject_video() {
		return project_video;
	}
	/**
	 * @param project_video the project_video to set
	 */
	public void setProject_video(String project_video) {
		this.project_video = project_video;
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
	 * @return the project_state
	 */
	public int getProject_state() {
		return project_state;
	}
	/**
	 * @param project_state the project_state to set
	 */
	public void setProject_state(int project_state) {
		this.project_state = project_state;
	}
	/**
	 * @return the project_days
	 */
	public int getProject_days() {
		return project_days;
	}
	/**
	 * @param project_days the project_days to set
	 */
	public void setProject_days(int project_days) {
		this.project_days = project_days;
	}
	/**
	 * @return the project_money_refund
	 */
	public String getProject_money_refund() {
		return project_money_refund;
	}
	/**
	 * @param project_money_refund the project_money_refund to set
	 */
	public void setProject_money_refund(String project_money_refund) {
		this.project_money_refund = project_money_refund;
	}
	/**
	 * @return the project_step
	 */
	public int getProject_step() {
		return project_step;
	}
	/**
	 * @param project_step the project_step to set
	 */
	public void setProject_step(int project_step) {
		this.project_step = project_step;
	}
	/**
	 * @return the reward_mode
	 */
	public int getReward_mode() {
		return reward_mode;
	}
	/**
	 * @param reward_mode the reward_mode to set
	 */
	public void setReward_mode(int reward_mode) {
		this.reward_mode = reward_mode;
	}
	/**
	 * @return the project_money
	 */
	public int getProject_money() {
		return project_money;
	}
	/**
	 * @param project_money the project_money to set
	 */
	public void setProject_money(int project_money) {
		this.project_money = project_money;
	}
	/**
	 * @return the reward_money
	 */
	public int getReward_money() {
		return reward_money;
	}
	/**
	 * @param reward_money the reward_money to set
	 */
	public void setReward_money(int reward_money) {
		this.reward_money = reward_money;
	}
	/**
	 * @return the reward_name
	 */
	public String getReward_name() {
		return reward_name;
	}
	/**
	 * @param reward_name the reward_name to set
	 */
	public void setReward_name(String reward_name) {
		this.reward_name = reward_name;
	}
	/**
	 * @return the reward_return_days
	 */
	public int getReward_return_days() {
		return reward_return_days;
	}
	/**
	 * @param reward_return_days the reward_return_days to set
	 */
	public void setReward_return_days(int reward_return_days) {
		this.reward_return_days = reward_return_days;
	}
	/**
	 * @return the reward_limit
	 */
	public int getReward_limit() {
		return reward_limit;
	}
	/**
	 * @param reward_limit the reward_limit to set
	 */
	public void setReward_limit(int reward_limit) {
		this.reward_limit = reward_limit;
	}
	/**
	 * @return the reward_person
	 */
	public int getReward_person() {
		return reward_person;
	}
	/**
	 * @param reward_person the reward_person to set
	 */
	public void setReward_person(int reward_person) {
		this.reward_person = reward_person;
	}
	/**
	 * @return the reward_post
	 */
	public int getReward_post() {
		return reward_post;
	}
	/**
	 * @param reward_post the reward_post to set
	 */
	public void setReward_post(int reward_post) {
		this.reward_post = reward_post;
	}
	/**
	 * @return the reward_patent_id
	 */
	public String getReward_patent_id() {
		return reward_patent_id;
	}
	/**
	 * @param reward_patent_id the reward_patent_id to set
	 */
	public void setReward_patent_id(String reward_patent_id) {
		this.reward_patent_id = reward_patent_id;
	}
	/**
	 * @return the reward_patent_name
	 */
	public String getReward_patent_name() {
		return reward_patent_name;
	}
	/**
	 * @param reward_patent_name the reward_patent_name to set
	 */
	public void setReward_patent_name(String reward_patent_name) {
		this.reward_patent_name = reward_patent_name;
	}
	/**
	 * @return the project_qq
	 */
	public String getProject_qq() {
		return project_qq;
	}
	/**
	 * @param project_qq the project_qq to set
	 */
	public void setProject_qq(String project_qq) {
		this.project_qq = project_qq;
	}
	/**
	 * @return the project_mobile
	 */
	public String getProject_mobile() {
		return project_mobile;
	}
	/**
	 * @param project_mobile the project_mobile to set
	 */
	public void setProject_mobile(String project_mobile) {
		this.project_mobile = project_mobile;
	}
	/**
	 * @return the project_realname
	 */
	public String getProject_realname() {
		return project_realname;
	}
	/**
	 * @param project_realname the project_realname to set
	 */
	public void setProject_realname(String project_realname) {
		this.project_realname = project_realname;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectInfo [user_id=" + user_id + ", project_name="
				+ project_name + ", mode_id=" + mode_id + ", category_id="
				+ category_id + ", province_id=" + province_id + ", city_id="
				+ city_id + ", project_explain=" + project_explain
				+ ", project_logo=" + project_logo + ", project_video="
				+ project_video + ", project_describe=" + project_describe
				+ ", project_state=" + project_state + ", project_days="
				+ project_days + ", project_money_refund="
				+ project_money_refund + ", project_step=" + project_step
				+ ", reward_mode=" + reward_mode + ", project_money="
				+ project_money + ", reward_money=" + reward_money
				+ ", reward_name=" + reward_name + ", reward_return_days="
				+ reward_return_days + ", reward_limit=" + reward_limit
				+ ", reward_person=" + reward_person + ", reward_post="
				+ reward_post + ", reward_patent_id=" + reward_patent_id
				+ ", reward_patent_name=" + reward_patent_name
				+ ", project_qq=" + project_qq + ", project_mobile="
				+ project_mobile + ", project_realname=" + project_realname
				+ ", id=" + id + ", project_gender=" + project_gender
				+ ", project_createtime=" + project_createtime
				+ ", project_shentime=" + project_shentime
				+ ", project_updatetime=" + project_updatetime
				+ ", manager_name=" + manager_name + ", manager_id="
				+ manager_id + ", manager_time=" + manager_time
				+ ", company_id=" + company_id + ", prod_type=" + prod_type
				+ ", prod_brand=" + prod_brand + ", prod_sendaddr="
				+ prod_sendaddr + ", showtop=" + showtop + ", convert="
				+ convert + ", project_fj=" + project_fj + ", project_zlh="
				+ project_zlh + ", project_zlname=" + project_zlname
				+ ", purchase_money=" + purchase_money + ", purchase_user_num="
				+ purchase_user_num + "]";
	}
	
	
	
}
