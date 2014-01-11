package com.sanxian.sxzhuanhuan.entity;

import java.util.ArrayList;

/**   
 * @Title: MetaData.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 创意、项目、商品三者的元数据
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:34:21 
 * @version V1.0   
 */
public class MetaData {
	private String metaName ; //名称
	private String metaSort ; //种类
	private String metaZone ; //地区
	private String metaProfession ; //行业
	private String metaProfile ; //简介
	private ArrayList<String> metaImgs ; //图片地址
	private String metaVideo ; //视频地址
	private String metaDetail ;  //详细信息
	private float metaMoney ; //总金额
	private int metaDays ; //筹集总天数
	private String metaRefund ; //退款说明
	
	private String logo ; //LOGO
	private int participate ; //参与人数
	private int discuss ; //讨论数
	private int days ; //已筹集的天数
	private float money ; //已筹集的金额
	/**
	 * @return the metaName
	 */
	public String getMetaName() {
		return metaName;
	}
	/**
	 * @param metaName the metaName to set
	 */
	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}
	/**
	 * @return the metaSort
	 */
	public String getMetaSort() {
		return metaSort;
	}
	/**
	 * @param metaSort the metaSort to set
	 */
	public void setMetaSort(String metaSort) {
		this.metaSort = metaSort;
	}
	/**
	 * @return the metaZone
	 */
	public String getMetaZone() {
		return metaZone;
	}
	/**
	 * @param metaZone the metaZone to set
	 */
	public void setMetaZone(String metaZone) {
		this.metaZone = metaZone;
	}
	/**
	 * @return the metaProfession
	 */
	public String getMetaProfession() {
		return metaProfession;
	}
	/**
	 * @param metaProfession the metaProfession to set
	 */
	public void setMetaProfession(String metaProfession) {
		this.metaProfession = metaProfession;
	}
	/**
	 * @return the metaProfile
	 */
	public String getMetaProfile() {
		return metaProfile;
	}
	/**
	 * @param metaProfile the metaProfile to set
	 */
	public void setMetaProfile(String metaProfile) {
		this.metaProfile = metaProfile;
	}
	/**
	 * @return the metaImgs
	 */
	public ArrayList<String> getMetaImgs() {
		return metaImgs;
	}
	/**
	 * @param metaImgs the metaImgs to set
	 */
	public void setMetaImgs(ArrayList<String> metaImgs) {
		this.metaImgs = metaImgs;
	}
	/**
	 * @return the metaVideo
	 */
	public String getMetaVideo() {
		return metaVideo;
	}
	/**
	 * @param metaVideo the metaVideo to set
	 */
	public void setMetaVideo(String metaVideo) {
		this.metaVideo = metaVideo;
	}
	/**
	 * @return the metaDetail
	 */
	public String getMetaDetail() {
		return metaDetail;
	}
	/**
	 * @param metaDetail the metaDetail to set
	 */
	public void setMetaDetail(String metaDetail) {
		this.metaDetail = metaDetail;
	}
	/**
	 * @return the metaMoney
	 */
	public float getMetaMoney() {
		return metaMoney;
	}
	/**
	 * @param metaMoney the metaMoney to set
	 */
	public void setMetaMoney(float metaMoney) {
		this.metaMoney = metaMoney;
	}
	/**
	 * @return the metaDays
	 */
	public int getMetaDays() {
		return metaDays;
	}
	/**
	 * @param metaDays the metaDays to set
	 */
	public void setMetaDays(int metaDays) {
		this.metaDays = metaDays;
	}
	/**
	 * @return the metaRefund
	 */
	public String getMetaRefund() {
		return metaRefund;
	}
	/**
	 * @param metaRefund the metaRefund to set
	 */
	public void setMetaRefund(String metaRefund) {
		this.metaRefund = metaRefund;
	}
	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the participate
	 */
	public int getParticipate() {
		return participate;
	}
	/**
	 * @param participate the participate to set
	 */
	public void setParticipate(int participate) {
		this.participate = participate;
	}
	/**
	 * @return the discuss
	 */
	public int getDiscuss() {
		return discuss;
	}
	/**
	 * @param discuss the discuss to set
	 */
	public void setDiscuss(int discuss) {
		this.discuss = discuss;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the money
	 */
	public float getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(float money) {
		this.money = money;
	}
	
	
}
