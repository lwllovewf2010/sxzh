package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: CollectionInfo  
* @Description: 我的收藏实体
* @author honaf
* @date 2014-1-20 下午4:58:14
 */
public class CollectionInfo implements Serializable {


	private static final long serialVersionUID = 1L;
	private String id;//产品或项目id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String avatar;  //图片
	private String name;   //名称
	private String type;  //类型    产品和项目
	private String energy_money;//能量币
	private String popularity;//人气
	private String introduction;
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEnergy_money() {
		return energy_money;
	}
	public void setEnergy_money(String energy_money) {
		this.energy_money = energy_money;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public CollectionInfo(String id, String avatar, String name, String type, String energy_money, String popularity, String introduction) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.name = name;
		this.type = type;
		this.energy_money = energy_money;
		this.popularity = popularity;
		this.introduction = introduction;
	}
	public CollectionInfo() {
		super();
	}
	
	
}
