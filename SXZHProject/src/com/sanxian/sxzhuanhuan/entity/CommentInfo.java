package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: CommentInfo
 * @Description: 包括创意、商品、项目的评论相关字段
 * @author yuanqk
 * @date 2014-2-19 上午11:08:49
 */
public class CommentInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String id = "";

	String userImage = "";
	String userName = "";
	String date = "";
	String content = "";

	public CommentInfo(String userImage, String userName, String date,
			String content) {
		super();
		this.userImage = userImage;
		this.userName = userName;
		this.date = date;
		this.content = content;
	}

	// added by yuanqk
	public CommentInfo() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
