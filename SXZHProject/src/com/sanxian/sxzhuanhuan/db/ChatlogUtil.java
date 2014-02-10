package com.sanxian.sxzhuanhuan.db;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.sanxian.sxzhuanhuan.message.ChatMsg;

public class ChatlogUtil {
	
	private static ChatlogUtil chatlog;
	
	private ChatlogUtil(){
		 
	}
	
	public static ChatlogUtil getChatlogUtil(){
		if(chatlog==null){
			return new ChatlogUtil();
		}
		return chatlog;
	}
	
	/**
	 * 根据jid找到聊天记录
	 * @param jid
	 * @return
	 */
	public List<ChatMsg> findMessageByJid(Activity context,String jid,String currJid){
		List<ChatMsg> list=new ArrayList<ChatMsg>();
		Cursor cursor=null;
		try {
			String projectAboutChatlog[] = { Chatlog.ID,
					Chatlog.CONTENT, Chatlog.JID,
					Chatlog.SENDER, Chatlog.FONT_FAMILY,
					Chatlog.FONT_SIZE, Chatlog.FONT_COLOR,
					Chatlog.SENT_TIME, Chatlog.NAME,
					Chatlog.IS_VOICE_MESSAGE,
					Chatlog.VOICE_MESSAGE_PATH ,
					Chatlog.HAS_BEEN_READ,Chatlog.LOGON_USER,Chatlog.VOICE_MESSAGE_LENGTH};		
			cursor=context.getContentResolver().query(Chatlog.CONTENT_URI,projectAboutChatlog,
			Chatlog.JID+ "=? and "+ Chatlog.LOGON_USER + "=?  ",new String[] { jid,currJid},null);
			boolean testtest=cursor.moveToFirst();
			if (cursor == null) {
				return list;
			}
			if (testtest) {         				
				do {
					Log.d("test", "几条数据呢?"+cursor.getString(8));
					// 获取字段的值      
					int id = cursor.getInt(cursor
							.getColumnIndex(Chatlog.ID));
					int sender = cursor.getInt(cursor
							.getColumnIndex(Chatlog.SENDER));
					String content = cursor.getString(cursor
							.getColumnIndex(Chatlog.CONTENT));			
					String sentTime = cursor.getString(cursor
							.getColumnIndex(Chatlog.SENT_TIME));
					int voiceType = cursor.getInt(cursor
							.getColumnIndex(Chatlog.IS_VOICE_MESSAGE));
					String voicePaht = cursor.getString(cursor
							.getColumnIndex(Chatlog.VOICE_MESSAGE_PATH));
					String username=cursor.getString(cursor.getColumnIndex(Chatlog.NAME));
					String userJid=cursor.getString(cursor.getColumnIndex(Chatlog.JID));			
					int hasBeenRead=cursor.getInt(cursor.getColumnIndex(Chatlog.HAS_BEEN_READ));
					int voiceMessageLength=cursor.getInt(cursor.getColumnIndex(Chatlog.VOICE_MESSAGE_LENGTH));
					
					String LogonUser=cursor.getString(cursor.getColumnIndex(Chatlog.LOGON_USER));			
					ChatMsg mes=new ChatMsg(id, content, userJid, username, sender, hasBeenRead, LogonUser, voiceType, voicePaht,sentTime,voiceMessageLength,null);
					list.add(mes);    
					} 
				while (cursor.moveToNext());    
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (cursor != null) {
				cursor.close();
			}
		}
		return list;
	}
	
	/**
	 * 添加聊天信息
	 * @param content
	 * @param message
	 */
	public void insertMessage(Context context,ChatMsg message){
		ContentValues values = new ContentValues();		
		values.put(Chatlog.CONTENT, message.getChatContent());
		values.put(Chatlog.JID, message.getJid());
		values.put(Chatlog.NAME, message.getUsername());
		values.put(Chatlog.SENDER, message.getSender());
		values.put(Chatlog.SENT_TIME, message.getSendTime());
		values.put(Chatlog.HAS_BEEN_READ, message.getHasBeenRead());
		values.put(Chatlog.LOGON_USER, message.getLogonUser());
		values.put(Chatlog.IS_VOICE_MESSAGE, message.getIsVoiceMessage());
		values.put(Chatlog.VOICE_MESSAGE_PATH, message.getVoiceMessagePath());
		context.getContentResolver().insert(Chatlog.CONTENT_URI, values);
		
	}

}
