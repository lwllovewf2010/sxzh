package com.sanxian.sxzhuanhuan.entity;

import java.util.ArrayList;

/**   
 * @Title: SortInfo.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 分类信息
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午3:44:17 
 * @version V1.0   
 */
public class SortInfo {
	private String sortLogoUrl ;
	private String sortName ;
	private ArrayList<String> sortChild ;
	
	/**
	 * @return the sortLogoUrl
	 */
	public String getSortLogoUrl() {
		return sortLogoUrl;
	}
	/**
	 * @param sortLogoUrl the sortLogoUrl to set
	 */
	public void setSortLogoUrl(String sortLogoUrl) {
		this.sortLogoUrl = sortLogoUrl;
	}
	/**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}
	/**
	 * @param sortName the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	/**
	 * @return the sortChild
	 */
	public ArrayList<String> getSortChild() {
		return sortChild;
	}
	/**
	 * @param sortChild the sortChild to set
	 */
	public void setSortChild(ArrayList<String> sortChild) {
		this.sortChild = sortChild;
	}

}
