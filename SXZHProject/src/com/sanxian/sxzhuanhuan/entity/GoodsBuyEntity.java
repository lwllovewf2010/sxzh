package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Title: goodsbuyVo
 * @Package com.sanxian.sxzhuanhuan.entity
 * @Description: 商品属性
 * @author yuanqk@sanxian.com
 * @date 2014-1-3 下午5:34:21
 * @version V1.0
 */
public class GoodsBuyEntity implements Serializable{
	private String personName;
	private String phoneNum;
	private String address;
	private String enterprise;
	private String goodsName;
	private String goodsColor;
	private String goodsSize;
	private String goodsPice;
	private String goodsNum;
	private String goodsImage;
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsColor() {
		return goodsColor;
	}
	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}
	public String getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}
	public String getGoodsPice() {
		return goodsPice;
	}
	public void setGoodsPice(String goodsPice) {
		this.goodsPice = goodsPice;
	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
}
