package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: AddressInfo  
* @Description: 收货地址试题类（有需要自己完善）
* @author honaf
* @date 2014-1-11 上午11:08:49
 */
public class AddressInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name="";
	private String district="";
	private String street="";
	private boolean isDefault=false;  //是否为默认地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public AddressInfo(String name, String district, String street, boolean isDefault) {
		super();
		this.name = name;
		this.district = district;
		this.street = street;
		this.isDefault = isDefault;
	}
	
	
}
