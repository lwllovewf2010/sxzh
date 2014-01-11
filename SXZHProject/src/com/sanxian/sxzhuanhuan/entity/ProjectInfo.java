package com.sanxian.sxzhuanhuan.entity;

/**   
 * @Title: CreativeInfo.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 项目数据信息
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:44:28 
 * @version V1.0   
 */
public class ProjectInfo extends MetaData{
	private String logo ; //LOGO
	private int participate ; //参与人数
	private int discuss ; //讨论数
	private int days ; //已筹集的天数
	private float money ; //已筹集的金额
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
	
}
