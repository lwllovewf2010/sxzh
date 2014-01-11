package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;


/**
 * 项目实体类
 * @author luozhiren
 *
 */
public class ProjectBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String projecTitle; //项目标题
	
	private String content;  //项目详细
	
	private String imageURL; //项目图片
	
	private int personCount; //项目参与人数
	
	private int discussCount; //讨论次数
	
	private int starDay; //开始天数
	
	private double money; //金额
	
	public String getProjecTitle() {
		return projecTitle;
	}

	public void setProjecTitle(String projecTitle) {
		this.projecTitle = projecTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public int getDiscussCount() {
		return discussCount;
	}

	public void setDiscussCount(int discussCount) {
		this.discussCount = discussCount;
	}

	public int getStarDay() {
		return starDay;
	}

	public void setStarDay(int starDay) {
		this.starDay = starDay;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	
	

}
