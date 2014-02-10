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
	
	String id = "";
	String name = ""; //姓名
	String province_id = ""; //省份id
	String province_name = "";//省份name
	String city_id = "";//城市id
	String city_name = "";//城市name
	String area_id = "";//区id
	String area_name = "";//区name
	String address = "";//地址
	String phoneNum = "";//手机
	String postcode = "";//邮编
	String isDefault = "0";  //1是0不是
	
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
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public AddressInfo(String id,String name,String phoneNum,String postcode, String province_id,String province_name,String city_id,String city_name,String area_id,String area_name,String address,String isDefault) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.postcode = postcode;
		this.province_id = province_id;
		this.province_name = province_name;
		this.city_id = city_id;
		this.city_name = city_name;
		this.area_id = area_id;
		this.area_name = area_name;
		this.address = address;
		this.isDefault = isDefault;
	}
	//added by yuanqk
	public AddressInfo() {
		
	}
	
}
