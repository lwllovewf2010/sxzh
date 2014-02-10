package com.sanxian.sxzhuanhuan.message;

import java.util.Map;

import org.jivesoftware.smack.XMPPException;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppUtils;
import android.os.AsyncTask;
import android.util.Log;

public class MessageLogin extends AsyncTask<Map<String, String>,Integer,Boolean>{

	private static final String TAG = "MessageLogin";

	@Override
	protected Boolean doInBackground(Map<String, String>... map) {
		// TODO Auto-generated method stub
		Boolean loginCode = true;//成功
		Log.d(TAG, "登入后台"+map[0].get("username"));
		try {
			XmppUtils.getInstance().createConnection();
			XmppUtils.getInstance().getConnection().login(map[0].get("username"), map[0].get("password"), XmppUtils.RESOURCE);
			OpenfireUserInfo info=OpenfireUserInfo.getOpenFireUserInfo();
			info.setCurrentLoginUserJid(XmppUtils.getInstance().getConnection().getUser());
			Log.d(TAG, "登入成功之后的用户信息"+XmppUtils.getInstance().getConnection().getUser());
			
		} catch (XMPPException ex) {
			ex.printStackTrace();
			loginCode=false;
		}
		
		
		return loginCode;
	}

}
