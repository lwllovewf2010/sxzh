/*
 * Copyright (C) Winkee Technologies Co., Ltd. 2005-2012. 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information 
 * of Winkee Technologies Co., Ltd. ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Winkee. 
 */
package com.sanxian.sxzhuanhuan.db;


import android.net.Uri;

/**
 * 点对点聊天记录表
 * 
 * @author 罗治任
 * @date 2012-9-4
 * @version 1.0
 */
public class Chatlog {
	public static final String TABLE_NAME_EMPLOYEECHATLOG = "chatlog";

	public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
	public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
	public static final String MIME_ITEM = "vnd.sanxian.chatlog";

	// 表示单一记录
	public static final String MIME_TYPE_SINGLE = MIME_ITEM_PREFIX + "/"
			+ MIME_ITEM;

	// 所有记录
	public static final String MEME_TYPE_MULTIPLE = "MIME_DIR_PREFIX" + "/"
			+ MIME_ITEM;

	// 授权者
	public static final String AUTHORITY = "cn.sanxian.provider.customprovider";

	public static final String PATH_SINGLE = "chatlog/#";
	public static final String PATH_MULTIPLE = "chatlog";

	public static final String STR = "content://" + AUTHORITY + "/"
			+ PATH_MULTIPLE;
	public static final Uri CONTENT_URI = Uri.parse(STR);

	public static final String ID = "_id"; // 唯一标识主键
	public static final String CONTENT = "Content"; // 聊天内容
	public static final String JID = "Jid";// jid,标识和哪个好友聊天
	public static final String NAME = "Name";// 和哪个客服聊天(属于哪个employee)
	public static final String SENDER = "Sender";// 辨别聊天记录属于哪一方(我0,对方1)
	public static final String FONT_FAMILY = "FontFamily";// 字体
	public static final String FONT_SIZE = "FontSize";// 字体大小
	public static final String FONT_COLOR = "FontColor";// 字体颜色
	public static final String SENT_TIME = "SentTime";// 留言时间

	public static final String HAS_BEEN_READ = "HasBeenRead";// 标识为是否只读(0表示未读，1表示已读)
	public static final String LOGON_USER = "LogonUser";//标识当前登录用户
	
	public static final String IS_VOICE_MESSAGE="IsVoiceMessage";//标识为是否为语音消息
	public static final String VOICE_MESSAGE_PATH="VoiceMessagePath";//语音消息的地址
	public static final String VOICE_MESSAGE_LENGTH="VoiceMessageLength";
	
	
}
