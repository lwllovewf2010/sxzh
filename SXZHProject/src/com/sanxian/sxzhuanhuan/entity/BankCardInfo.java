package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: BankCardInfo  
* @Description: 银行卡账户列表
* @author honaf
* @date 2014-1-18 上午11:56:05
 */
public class BankCardInfo implements Serializable {


	private static final long serialVersionUID = 1L;

	private String card_id;
	private String card_name;
	private String is_select;
	public BankCardInfo(String card_id, String card_name, String is_select) {
		super();
		this.card_id = card_id;
		this.card_name = card_name;
		this.is_select = is_select;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getIs_select() {
		return is_select;
	}
	public void setIs_select(String is_select) {
		this.is_select = is_select;
	}
	
	
	
}
