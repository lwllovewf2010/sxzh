package com.sanxian.sxzhuanhuan.dialog;

import java.io.Serializable;

import android.R.integer;

/**
 * 
* @ClassName: TopDialogInfo  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author honaf
* @date 2014-1-8 下午3:51:47
 */
public class TopDialogInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int direction;    //出现的地方（目前支持左上角、右上角）
	private String []menu;    //item的数据
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String[] getMenu() {
		return menu;
	}
	public void setMenu(String[] menu) {
		this.menu = menu;
	}
	public TopDialogInfo(int direction, String[] menu) {
		super();
		this.direction = direction;
		this.menu = menu;
	}
	public TopDialogInfo() {
	}
	
	
}
