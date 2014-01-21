package com.sanxian.sxzhuanhuan.entity;

import java.util.ArrayList;

/**
 * @Title: MetaData.java
 * @Package com.sanxian.sxzhuanhuan.entity
 * @Description: 创意属性
 * @author yuanqk@sanxian.com
 * @date 2014-1-3 下午5:34:21
 * @version V1.0
 */
public class OriginalityItemDetails {
	private String id;
	private String user_id;
	private String org_name;  //创意名称
	private String org_desc;//创意详情
	private String province_id;
	private String org_explain;
	private String org_video;
	private String city_id;
	private String category_id;//分类id
	private String scancount; //浏览次数
	private String addtime;//创建日期
	private String updatetime;//
	private String org_state;  //0：正常-1：被举报拉黑1：成功转为项目2：项目转换失败
	private String pid;  //转变为项目后的项目id 没转项目此值为 null或0或空
	private String gzcount;//  关注人数
	private String comment_nums;  //评论数
	private String category_name;  //
	private String province_name;  //
	private String city_name;  //
	private String user_realname;  //
	private String favorite_nums;  //收藏数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_desc() {
		return org_desc;
	}
	public void setOrg_desc(String org_desc) {
		this.org_desc = org_desc;
	}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getScancount() {
		return scancount;
	}
	public void setScancount(String scancount) {
		this.scancount = scancount;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getOrg_state() {
		return org_state;
	}
	public void setOrg_state(String org_state) {
		this.org_state = org_state;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getGzcount() {
		return gzcount;
	}
	public void setGzcount(String gzcount) {
		this.gzcount = gzcount;
	}
	public String getComment_nums() {
		return comment_nums;
	}
	public void setComment_nums(String comment_nums) {
		this.comment_nums = comment_nums;
	}
	public String getFavorite_nums() {
		return favorite_nums;
	}
	public void setFavorite_nums(String favorite_nums) {
		this.favorite_nums = favorite_nums;
	}
	public String getOrg_explain() {
		return org_explain;
	}
	public void setOrg_explain(String org_explain) {
		this.org_explain = org_explain;
	}
	public String getOrg_video() {
		return org_video;
	}
	public void setOrg_video(String org_video) {
		this.org_video = org_video;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getUser_realname() {
		return user_realname;
	}
	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}
	
}
