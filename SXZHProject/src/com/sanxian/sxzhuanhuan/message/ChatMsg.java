package com.sanxian.sxzhuanhuan.message;

import java.io.Serializable;

public class ChatMsg implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uid; //对应openId
	private int id;
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	private String chatContent;
	private String jid;
	private String username;
	private int sender;
	private String avatar;
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContenType() {
		return contentType;
	}

	public void setContenType(String contenType) {
		this.contentType = contenType;
	}
	private String contentType;
	
	
	
	public int getVoiceLength() {
		return voiceLength;
	}

	public void setVoiceLength(int voiceLength) {
		this.voiceLength = voiceLength;
	}
	private int voiceLength;
	/**
	 * 我发的信息
	 */
	public static final int SEND_MY=0;
	/**
	 * 对方发的信息
	 */
	public static final int SEND_FRIEND=1;
	
	private int hasBeenRead;
	/**
	 * 已经读了的信息
	 */
	public static final int HASBEENREAD_ISREAD=0;
	/**
	 * 未查看的信息
	 */
	public static final int HASBEENREAD_NOREAD=1;
	
	private String LogonUser;
	private int isVoiceMessage;
	/**
	 * 是语音消息
	 */
	public static final int ISVOICEMESSAGE_TRUE=0;
	/**
	 * 不是语音消息
	 */
	public static final int ISVOICEMESSAGE_FALSE=1;
	
	private String voiceMessagePath;
	private String sendTime;
	
	
	
	
	public ChatMsg(){
		
	}
	
	public ChatMsg(int id,String chatContent,String jid,String username,int sender,
			int hasBeenRead,String LogonUser,int isVoiceMessage,String voiceMessagePath,String sendTime,int voiceLength,String contentType){
		this.id=id;
		this.chatContent=chatContent;
		this.jid=jid;
		this.username=username;
		this.sender=sender;
		this.hasBeenRead=hasBeenRead;
		this.LogonUser=LogonUser;
		this.isVoiceMessage=isVoiceMessage;
		this.voiceMessagePath=voiceMessagePath;
		this.sendTime=sendTime;
		this.voiceLength=voiceLength;
		this.contentType=contentType;
	}
	
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getJid() {
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getHasBeenRead() {
		return hasBeenRead;
	}
	public void setHasBeenRead(int hasBeenRead) {
		this.hasBeenRead = hasBeenRead;
	}
	public String getLogonUser() {
		return LogonUser;
	}
	public void setLogonUser(String logonUser) {
		LogonUser = logonUser;
	}
	public int getIsVoiceMessage() {
		return isVoiceMessage;
	}
	public void setIsVoiceMessage(int isVoiceMessage) {
		this.isVoiceMessage = isVoiceMessage;
	}
	public String getVoiceMessagePath() {
		return voiceMessagePath;
	}
	public void setVoiceMessagePath(String voiceMessagePath) {
		this.voiceMessagePath = voiceMessagePath;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
