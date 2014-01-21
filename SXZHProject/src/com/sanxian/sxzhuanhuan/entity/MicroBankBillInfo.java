package com.sanxian.sxzhuanhuan.entity;


import java.io.Serializable;

/**
 * 
* @ClassName: MicroBankBillInfo  
* @Description: 微银行账单实体
* @author honaf
* @date 2014-1-16 下午4:21:01
 */
public class MicroBankBillInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1839391886503818909L;
	private String bill_id;
	private String trade_time;
	private String trade_type;
	private String goods;
	private String amount;
	private String balance;
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public String getTrade_time() {
		return trade_time;
	}
	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public MicroBankBillInfo(String bill_id, String trade_time, String trade_type, String goods, String amount, String balance) {
		super();
		this.bill_id = bill_id;
		this.trade_time = trade_time;
		this.trade_type = trade_type;
		this.goods = goods;
		this.amount = amount;
		this.balance = balance;
	}
	public MicroBankBillInfo() {
		super();
	}
	
	
}
