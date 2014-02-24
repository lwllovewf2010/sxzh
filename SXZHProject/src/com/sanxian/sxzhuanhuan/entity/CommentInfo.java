package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	String userImage = "";// 用户头像
	String userName = "";// 用户名
	String content = "";// 评论内容
	String id = "";// 评论id
	String dnum = "";// 用户顶的总数
	String fdnum = "";// 用户倒的数量
	String ctype = "";// 评论的类型产品为1,项目2,创意3
	String userid = "";// 用户账号
	String ip = "";// 用户发布的ip
	String addtime = "";// 添加时间
	String replyid = "";// 父ID
	String objtid = "";// 项目或者产品id，创意id
	String sid = "";// 根id
	String r_user_name ="";//被回复者 用户名
	ArrayList<CommentInfo> comment_groups = null;// 子评论

	// added by yuanqk
	public CommentInfo() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDnum() {
		return dnum;
	}

	public void setDnum(String dnum) {
		this.dnum = dnum;
	}

	public String getFdnum() {
		return fdnum;
	}

	public void setFdnum(String fdnum) {
		this.fdnum = fdnum;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getReplyid() {
		return replyid;
	}

	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}

	public String getObjtid() {
		return objtid;
	}

	public void setObjtid(String objtid) {
		this.objtid = objtid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public ArrayList<CommentInfo> getComment_groups() {
		if(comment_groups==null) comment_groups = new ArrayList<CommentInfo>();
		return comment_groups;
	}

	public void setComment_groups(ArrayList<CommentInfo> comment_groups) {
		this.comment_groups = comment_groups;
	}

	public String getR_user_name() {
		return r_user_name;
	}

	public void setR_user_name(String r_user_name) {
		this.r_user_name = r_user_name;
	}

}
