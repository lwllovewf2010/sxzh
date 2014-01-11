package com.sanxian.sxzhuanhuan.dialog;

import java.io.Serializable;

import android.R.integer;

/**
 * 
* @ClassName: FootDialogInfo  
* @Description: 底部弹出框
* @author honaf
* @date 2014-1-8 下午3:59:24
 */
public class FootDialogInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String []menu;     //底部item的数据
	public String[] getMenu() {
		return menu;
	}
	public void setMenu(String[] menu) {
		this.menu = menu;
	}
	public FootDialogInfo(String[] menu) {
		super();
		this.menu = menu;
	}
	public FootDialogInfo() {
	}
	
	
	
}
