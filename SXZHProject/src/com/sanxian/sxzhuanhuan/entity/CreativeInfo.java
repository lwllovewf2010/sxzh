package com.sanxian.sxzhuanhuan.entity;

/**   
 * @Title: CreativeInfo.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 创意数据信息
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:44:28 
 * @version V1.0   
 */
public class CreativeInfo extends MetaData{
	private String id ; // "id":"444",
	private String user_id ; // "user_id":"6_1177_469954",
	private String org_name ; // "org_name":"2",  //创意名称
	private String org_desc ; // "org_desc":"创意详情", //创意详情
	private String province_id ; // "province_id":"102",
	private String city_id ; // "city_id":"1",
	private String category_id ; // "category_id":"1", //分类id
	private String scancount ; // "scancount":111, //浏览次数
	private String addtime ; // "addtime":"2013-10-27 14:48:01",  //创建日期
	private String org_state ; // "org_state":"2",  //0：正常-1：被举报拉黑1：成功转为项目2：项目转换失败
	private String pid ; // "pid":"",  //转变为项目后的项目id 没转项目此值为 null或0或空
	private String gzcount ; // "gzcount":11  关注人数
	
	private String org_explain ; // "org_explain": "-",
	private String org_video ; //"org_video": "-",
	private String updatetime ; //"updatetime": null,
	
	private String comment_nums ; //"comment_nums":11,  //评论数
	private String favorite_nums ; // "favorite_nums":11  //收藏数

	
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
	 * @return the favorite_nums
	 */
	public String getFavorite_nums() {
		return favorite_nums;
	}
	/**
	 * @param favorite_nums the favorite_nums to set
	 */
	public void setFavorite_nums(String favorite_nums) {
		this.favorite_nums = favorite_nums;
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
	 * @return the org_name
	 */
	public String getOrg_name() {
		return org_name;
	}
	/**
	 * @param org_name the org_name to set
	 */
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	/**
	 * @return the org_desc
	 */
	public String getOrg_desc() {
		return org_desc;
	}
	/**
	 * @param org_desc the org_desc to set
	 */
	public void setOrg_desc(String org_desc) {
		this.org_desc = org_desc;
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
	 * @return the category_id
	 */
	public String getCategory_id() {
		return category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	/**
	 * @return the scancount
	 */
	public String getScancount() {
		return scancount;
	}
	/**
	 * @param scancount the scancount to set
	 */
	public void setScancount(String scancount) {
		this.scancount = scancount;
	}
	/**
	 * @return the addtime
	 */
	public String getAddtime() {
		return addtime;
	}
	/**
	 * @param addtime the addtime to set
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	/**
	 * @return the org_state
	 */
	public String getOrg_state() {
		return org_state;
	}
	/**
	 * @param org_state the org_state to set
	 */
	public void setOrg_state(String org_state) {
		this.org_state = org_state;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the gzcount
	 */
	public String getGzcount() {
		return gzcount;
	}
	/**
	 * @param gzcount the gzcount to set
	 */
	public void setGzcount(String gzcount) {
		this.gzcount = gzcount;
	}
	/**
	 * @return the org_explain
	 */
	public String getOrg_explain() {
		return org_explain;
	}
	/**
	 * @param org_explain the org_explain to set
	 */
	public void setOrg_explain(String org_explain) {
		this.org_explain = org_explain;
	}
	/**
	 * @return the org_video
	 */
	public String getOrg_video() {
		return org_video;
	}
	/**
	 * @param org_video the org_video to set
	 */
	public void setOrg_video(String org_video) {
		this.org_video = org_video;
	}
	/**
	 * @return the updatetime
	 */
	public String getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreativeInfo [id=" + id + ", user_id=" + user_id
				+ ", org_name=" + org_name + ", org_desc=" + org_desc
				+ ", province_id=" + province_id + ", city_id=" + city_id
				+ ", category_id=" + category_id + ", scancount=" + scancount
				+ ", addtime=" + addtime + ", org_state=" + org_state
				+ ", pid=" + pid + ", gzcount=" + gzcount + ", org_explain="
				+ org_explain + ", org_video=" + org_video + ", updatetime="
				+ updatetime + ", comment_nums=" + comment_nums
				+ ", favorite_nums=" + favorite_nums + "]";
	}
	
	
	
	//test--------
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
