package com.sanxian.sxzhuanhuan.entity;


import java.io.Serializable;

import com.sanxian.sxzhuanhuan.message.xmpp.XmppUtils;

/**
 * 登录的用户信息
 *
 */
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1839391886503818909L;
	////编号
	String uid ="";
	///用户名
	String username ="";
	///密码
	String password="";
	
	///邮箱
	String email="";
	//手机号码
	String mobile="";
	//头像
	String avatar="";
	//公司
	String company = "";
	String address = "";
	String name_letter="";
	public String getName_letter() {
		return name_letter;
	}
	public void setName_letter(String name_letter) {
		this.name_letter = name_letter;
	}
	//好友模块
	//距离
	String distance = "";
	public String getJid() {
		return getUid()+"@"+XmppUtils.SERVER_NAME;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	//职位
	String position = "";
	//是否在线
	String isonline = "";
	String gender="";//性别
	String signature="";//个性签名
	
	private String jid;
	
	

	String token="";
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	// zhangfl
	/**加入时间 */
	String jointime = "" ;
	/**加入的项目数 */
	int joinProCount = 0 ;
	/**入股的项目数 */
	int shareProCount = 0 ;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//时间(fan加)
	String status="";
	String privatestr=""; 
	public String getPrivatestr() {
		return privatestr;
	}
	public void setPrivatestr(String privatestr) {
		this.privatestr = privatestr;
	}
	String city="";
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	String dateline="";
	private String realName;//真实姓名
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public String getIsonline() {
		return isonline;
	}
	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return the jointime
	 */
	public String getJointime() {
		return jointime;
	}
	/**
	 * @param jointime the jointime to set
	 */
	public void setJointime(String jointime) {
		this.jointime = jointime;
	}
	/**
	 * @return the joinProCount
	 */
	public int getJoinProCount() {
		return joinProCount;
	}
	/**
	 * @param joinProCount the joinProCount to set
	 */
	public void setJoinProCount(int joinProCount) {
		this.joinProCount = joinProCount;
	}
	/**
	 * @return the shareProCount
	 */
	public int getShareProCount() {
		return shareProCount;
	}
	/**
	 * @param shareProCount the shareProCount to set
	 */
	public void setShareProCount(int shareProCount) {
		this.shareProCount = shareProCount;
	}
	
}
