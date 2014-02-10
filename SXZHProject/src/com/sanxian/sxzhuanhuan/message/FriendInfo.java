package com.sanxian.sxzhuanhuan.message;

import java.io.Serializable;

import android.text.TextUtils;


public class FriendInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String nickname;
	private String jid;
	
	
	public String getJid() {
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		if(TextUtils.isEmpty(nickname))
			return username;
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
