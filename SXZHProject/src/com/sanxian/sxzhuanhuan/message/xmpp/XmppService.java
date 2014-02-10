package com.sanxian.sxzhuanhuan.message.xmpp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.db.ChatlogUtil;
import com.sanxian.sxzhuanhuan.message.ChatActivity;
import com.sanxian.sxzhuanhuan.message.ChatMsg;
import com.sanxian.sxzhuanhuan.message.MessageConstant;
import com.sanxian.sxzhuanhuan.message.OpenfireUserInfo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class XmppService extends Service {

	protected static final String TAG = "XmppService";
	XMPPConnection connection;
	private SApplication app;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		app=SApplication.getInstance();
		
		try {
			connection = XmppUtils.getInstance().getConnection();
			connection.addPacketListener(new PacketListener() {
				
				@Override
				public void processPacket(Packet packet) {
					if(packet instanceof Message){//信息监听
						Message msg = (Message) packet;
						if(Message.Type.chat.equals(msg.getType())){//是一对一聊天信息
							if(!TextUtils.isEmpty(msg.getBody())){	
								ChatMsg chatMsg = new ChatMsg();
								chatMsg.setJid(msg.getFrom().substring(0,msg.getFrom().indexOf("@")));
								chatMsg.setUid(msg.getFrom().substring(0,msg.getFrom().indexOf("@")));
								chatMsg.setChatContent(msg.getBody().trim());
								chatMsg.setHasBeenRead(ChatMsg.HASBEENREAD_NOREAD);
								chatMsg.setSender(ChatMsg.SEND_FRIEND);
								SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								chatMsg.setSendTime(format.format(new Date()));
								chatMsg.setUsername(msg.getSendUserName());
								chatMsg.setAvatar(msg.getAvatar());
								Log.d(TAG, "我得到的值用户名"+app.getLoginUserInfo().getUsername());	
								chatMsg.setLogonUser(app.getLoginUserInfo().getUsername());//先给个死的								
								if(msg.getContentType().equals("VOICE")){										
									chatMsg.setIsVoiceMessage(ChatMsg.ISVOICEMESSAGE_TRUE);
									chatMsg.setVoiceLength(Integer.valueOf(msg.getVocieLength()));
								}else{
									chatMsg.setIsVoiceMessage(ChatMsg.ISVOICEMESSAGE_FALSE);
								}
								ChatlogUtil chatlogUtil=ChatlogUtil.getChatlogUtil();
								chatlogUtil.insertMessage(XmppService.this, chatMsg);	
								Log.d(TAG, "我得到的值"+msg.getAvatar()+"--发送姓名"+msg.getSendUserName()+"--发送类型"+msg.getContentType()+"－－发送长度"+msg.getVocieLength());	
								Intent intent = new Intent();
								intent.setAction(MessageConstant.RECEIVE_MESSAGE_ACTION);
								intent.putExtra(MessageConstant.RECEIVE_MESSAGE_ACTION_INPUT_MESSAGE, chatMsg);								
								sendBroadcast(intent);
								
							}
							
						}else if(Message.Type.groupchat.equals(msg.getType())){//多人聊天
							
						}
					}else if(packet instanceof Presence){
						Presence presence = (Presence) packet;
							Log.d(TAG, "收到一条状态 xml = " + presence.toXML());							
					}
				}
			}, null);
		} catch (XMPPException e) {
			
			e.printStackTrace();
		}
		
		
		connection.addConnectionListener(new ConnectionListener() {
			
			@Override
			public void reconnectionSuccessful() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reconnectionFailed(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reconnectingIn(int seconds) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionClosedOnError(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionClosed() {
				
			}
		});
		
	}
	
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}
	
}
