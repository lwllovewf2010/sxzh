package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;

/**
 * 用户的邀请记录实体类
 * 
 * @author joe
 * 
 */
public class InvitationUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String invite_name;// 姓名
	private String invite_phone;// 电话
	private String invite_time;// 时间
	private String invite_status;// 状态
	private String invite_userid;// 邀请的uid

	public String getInvite_name() {
		return invite_name;
	}

	public void setInvite_name(String invite_name) {
		this.invite_name = invite_name;
	}

	public String getInvite_phone() {
		return invite_phone;
	}

	public void setInvite_phone(String invite_phone) {
		this.invite_phone = invite_phone;
	}

	public String getInvite_time() {
		return invite_time;
	}

	public void setInvite_time(String invite_time) {
		this.invite_time = invite_time;
	}

	public String getInvite_status() {
		return invite_status;
	}

	public void setInvite_status(String invite_status) {
		this.invite_status = invite_status;
	}

	public String getInvite_userid() {
		return invite_userid;
	}

	public void setInvite_userid(String invite_userid) {
		this.invite_userid = invite_userid;
	}

}
