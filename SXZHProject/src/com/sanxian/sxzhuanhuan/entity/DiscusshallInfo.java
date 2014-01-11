package com.sanxian.sxzhuanhuan.entity;


import java.io.Serializable;

/**
 * 
* @ClassName: DiscussShallInfo  
* @Description: 讨论大厅首页实体
* @author honaf
* @date 2014-1-9 上午11:47:48
 */
public class DiscusshallInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1839391886503818909L;
	private String avatar;
	private String title;
	private String content;
	private String time;
	public DiscusshallInfo(String avatar, String title, String content, String time) {
		super();
		this.avatar = avatar;
		this.title = title;
		this.content = content;
		this.time = time;
	}
	public DiscusshallInfo() {
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
