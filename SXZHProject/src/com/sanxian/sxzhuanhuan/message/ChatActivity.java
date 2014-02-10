package com.sanxian.sxzhuanhuan.message;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.util.StringUtils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.db.ChatlogUtil;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppUtils;

import android.animation.IntEvaluator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint({ "SimpleDateFormat", "HandlerLeak" })
public class ChatActivity extends  BaseActivity implements OnClickListener,OnTouchListener{

	private static final String TAG = "ChatActivity";
	
	/**
	 * 新的消息
	 */
	private static final int NOTIF_MSG = 0;
	/**
	 * 是否显示键盘
	 */
	private static final int HANDLER_SHOW_CLOSE_KEYPAD = 1; // 是否显示键盘
	
	
	private static final int RECORD_STATE_ING=2;
	private static final int RECORD_STATE_ED=3;
	
	private static int MAX_TIME = 15;    //最长录制时间，单位秒，0为无时间限制
	private static int MIX_TIME = 1;     //最短录制时间，单位秒，0为无时间限制，建议设为1	
	private static int RECORD_NO = 0;  //不在录音
	private static int RECORD_ING = 1;   //正在录音
	private static int RECODE_ED = 2;   //完成录音	
	private static int RECODE_STATE = 0;      //录音的状态	
	private static float recodeTime=0.0f;    //录音的时间
	private static double voiceValue=0.0;    //麦克风获取的音量值
	private AudioRecorder mr;
	private Dialog dialog;
	private ImageView dialog_img;
	private Thread recordThread;
	private String voicePath="voice";
	private MediaPlayer mPlayer = null; //播放语音消息的播放器
//	private InputMethodManager imm;// 键盘输入
	
	public ImageLoader imageLoader;
	public DisplayImageOptions options;
	
	
	
	private CustomEmoticonEditText chatInput;  //编辑输入框
	private Button sendBut; //发送
	private ListView chatList; //消息记录列表	
	private ChatListAdapter adapter;
	private UserInfo friendInfo;
	private List<ChatMsg> chatMsgList = new ArrayList<ChatMsg>();
	private LayoutInflater inflater;	
	private MessageReceiver receiver;
	private Button voiceButton;  
	private ImageView sendVoiceMessage = null; // 发送语音消息的图片
	private String myAvatar;
	private String myuserName;
	
	
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case NOTIF_MSG:
				adapter.notifyDataSetChanged();
				break;
			case HANDLER_SHOW_CLOSE_KEYPAD:
				if (voiceButton.getVisibility() == View.GONE) {
					sendVoiceMessage
							.setBackgroundResource(R.drawable.aio_voice_nor);
				} else {
//					hideKeyBoard();
					sendVoiceMessage
							.setBackgroundResource(R.drawable.aio_keyboard_nor);
				}

				break;
			case RECORD_STATE_ING:
				//录音超过15秒自动停止
				if (RECODE_STATE == RECORD_ING) {
					RECODE_STATE=RECODE_ED;
					if (dialog.isShowing()) {
						dialog.dismiss();
					}
					try {
							mr.stop();
							voiceValue = 0.0;
							sendVocieMessage();
						} catch (IOException e) {
							e.printStackTrace();
						}
							
							if (recodeTime < 1.0) {
								//录音时间太短
//								showWarnToast();
//								record.setText("按住开始录音");
//								RECODE_STATE=RECORD_NO;
							}else{
								//录音完成
//							 record.setText("录音完成!点击重新录音");
//							 luyin_txt.setText("录音时间："+((int)recodeTime));
//							 luyin_path.setText("文件路径："+getAmrPath());
							}
				}
				
				break;
				
			case RECORD_STATE_ED:
				setDialogImage();
				
				break;
			
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat);
		
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
		myAvatar=app.getLoginUserInfo().getAvatar();
		myuserName=app.getLoginUserInfo().getUsername();
		//设置好友信息
		friendInfo=(UserInfo)getIntent().getExtras().getSerializable("userinfo");
		initView();
		init();
		//初始化接收信息广播
		receiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter(MessageConstant.RECEIVE_MESSAGE_ACTION);
		registerReceiver(receiver, filter);
		Log.d(TAG, "自己的头像"+myAvatar+"自己的姓名"+myuserName);
	}
	
	/**
	 * 初始化数据
	 */
	private void init(){
		displayLeft();
		displayRight();
		setTitle(friendInfo.getUsername());		
		chatInput = (CustomEmoticonEditText) findViewById(R.id.text_editor);
		sendBut = (Button) findViewById(R.id.SendContentButton);
		chatList = (ListView) findViewById(R.id.HistoryListView);		
		inflater = LayoutInflater.from(this);
		adapter = new ChatListAdapter();
		ChatlogUtil chatlogUtil=ChatlogUtil.getChatlogUtil();
		chatMsgList = chatlogUtil.findMessageByJid(this,friendInfo.getUid(),myuserName);
		chatList.setAdapter(adapter);
		sendBut.setOnClickListener(this);
		voiceButton = (Button) findViewById(R.id.voiceButton);
		voiceButton.setOnTouchListener(this);
		sendVoiceMessage = (ImageView) findViewById(R.id.sendVoiceMessage);
		sendVoiceMessage.setOnClickListener(this);
		mr = new AudioRecorder(voicePath);
		// 获取键盘
//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

	}
	
	
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//发送信息
		case R.id.SendContentButton:
			Log.d(TAG, "所有数据jid"+friendInfo.getJid()+"自己的"+friendInfo.getUsername());
			
			
			
			String sendmsg = chatInput.getText().toString().trim();			
			ChatMsg messageMe = new ChatMsg();
			messageMe.setJid(friendInfo.getUid());
			messageMe.setChatContent(sendmsg);
			messageMe.setHasBeenRead(ChatMsg.HASBEENREAD_ISREAD);
			messageMe.setSender(ChatMsg.SEND_MY);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			messageMe.setSendTime(format.format(new Date()));
			messageMe.setUsername(myuserName);
			messageMe.setLogonUser(myuserName);//先给个死的
			messageMe.setIsVoiceMessage(ChatMsg.ISVOICEMESSAGE_FALSE);
			messageMe.setContenType("TEXT");
			messageMe.setVoiceLength(0);
			sendMessage(messageMe);
			
			break;
			
		case R.id.sendVoiceMessage:
			if (voiceButton.getVisibility() == View.GONE) {
				voiceButton.setVisibility(View.VISIBLE);
				chatInput.setVisibility(View.GONE);
				sendBut.setVisibility(View.GONE);
			} else {
				voiceButton.setVisibility(View.GONE);
				chatInput.setVisibility(View.VISIBLE);
				sendBut.setVisibility(View.VISIBLE);
			}
			mHandler.removeMessages(HANDLER_SHOW_CLOSE_KEYPAD);
			mHandler.sendEmptyMessage(HANDLER_SHOW_CLOSE_KEYPAD);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			//按住的时候
			switch (v.getId()) {
			case R.id.voiceButton:
				if (RECODE_STATE != RECORD_ING) {						
					RECODE_STATE=RECORD_ING;
					showVoiceDialog();
					try {
						mr.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mythread();
				}
				break;

			default:
				break;
			}
			
		}else if (event.getAction() == MotionEvent.ACTION_UP) {
			//松开的时候
			switch (v.getId()) {
			case R.id.voiceButton:
				if (RECODE_STATE == RECORD_ING) {
					RECODE_STATE=RECODE_ED;
					if (dialog.isShowing()) {
						dialog.dismiss();
					}
					try {
							mr.stop();
							voiceValue = 0.0;
							sendVocieMessage();
						} catch (IOException e) {
							e.printStackTrace();
						}
//							录音时间太短      
//							if (recodeTime < MIX_TIME) {
//								showWarnToast();
//								record.setText("按住开始录音");
//								RECODE_STATE=RECORD_NO;
//							}else{
//							 record.setText("录音完成!点击重新录音");
//							 luyin_txt.setText("录音时间："+((int)recodeTime));
//							 luyin_path.setText("文件路径："+getAmrPath());
//							}
				}
				break;

			default:
				break;
			}
		}
		return true;
	}
	
	private void sendVocieMessage(){
		ChatMsg messageMe = new ChatMsg();
		messageMe.setJid(friendInfo.getUid());
		Log.d(TAG, "录音值地址"+mr.path);
		String vocieValue = StringUtils.encodeBase64(VoiceUtil
				.getBytes(mr.path));				
		Log.d(TAG, "录音值"+vocieValue);
		messageMe.setChatContent(vocieValue);
		messageMe.setHasBeenRead(ChatMsg.HASBEENREAD_ISREAD);
		messageMe.setSender(ChatMsg.SEND_MY);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		messageMe.setSendTime(format.format(new Date()));
		messageMe.setUsername(myuserName);
		messageMe.setLogonUser(myuserName);//先给个死的
		messageMe.setIsVoiceMessage(ChatMsg.ISVOICEMESSAGE_TRUE);
		messageMe.setContenType("VOICE");
		messageMe.setVoiceLength((int)recodeTime);
		sendMessage(messageMe);
	}
	
	
	/**
	 * 发送消息入口
	 */
	private void sendMessage(ChatMsg  message){
		try {			
			try {
				Message messagePacket = new Message(friendInfo.getJid(), Type.chat);
				
				messagePacket.setContentType(message.getContenType());			
				messagePacket.setBody(message.getChatContent());	
				messagePacket.setVocieLength(message.getVoiceLength()+"");
				messagePacket.setAvatar(myAvatar);
				messagePacket.setSendUserName(myuserName);
				if(XmppUtils.getInstance().getConnection()==null&&!XmppUtils.getInstance().getConnection().isAuthenticated()){
					Toast.makeText(ChatActivity.this, "已经掉线，后期将会改善连接", Toast.LENGTH_SHORT).show();
				}else{
					XmppUtils.getInstance().getConnection().sendPacket(messagePacket);
				}
				Log.d("test","---------------------------------"+messagePacket.toXML());
			} catch (Exception e1) {
				e1.printStackTrace();
				Toast.makeText(ChatActivity.this, "没有连网", Toast.LENGTH_SHORT).show();
			}
			ChatlogUtil chatlogUtil=ChatlogUtil.getChatlogUtil();
			chatlogUtil.insertMessage(ChatActivity.this, message);
			
			chatMsgList.add(message);
			mHandler.sendEmptyMessage(NOTIF_MSG);
			chatInput.setText("");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//录音时显示Dialog
	private	void showVoiceDialog(){
			dialog = new Dialog(ChatActivity.this,R.style.DialogStyle);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			dialog.setContentView(R.layout.my_dialog);
			dialog_img=(ImageView)dialog.findViewById(R.id.dialog_img);
			dialog.show();
		}
		
		
		//录音Dialog图片随声音大小切换
	private	void setDialogImage(){
			if (voiceValue < 200.0) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp1);
			}else if (voiceValue > 800.0 && voiceValue < 1600) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp2);
			}else if (voiceValue > 3200.0 && voiceValue < 5000) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp3);
			}else if (voiceValue > 7000.0 && voiceValue < 10000.0) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp4);
			}else if (voiceValue > 14000.0 && voiceValue < 17000.0) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp5);
			}else if (voiceValue > 20000.0 && voiceValue < 24000.0) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp6);
			}else if (voiceValue > 28000.0) {
				dialog_img.setImageResource(R.drawable.xiaoxi_amp7);
			}
		}
	
	//录音计时线程
	 private void mythread(){
			recordThread = new Thread(ImgThread);
			recordThread.start();
	 }
	 
	 
	//录音线程
		private Runnable ImgThread = new Runnable() {

			@Override
			public void run() {
				recodeTime = 0.0f;
				while (RECODE_STATE==RECORD_ING) {
					if (recodeTime >= MAX_TIME && MAX_TIME != 0) {
						mHandler.sendEmptyMessage(RECORD_STATE_ING);
					}else{
					try {
						Thread.sleep(200);
						recodeTime += 0.2;
						if (RECODE_STATE == RECORD_ING) {
							voiceValue = mr.getAmplitude();
							mHandler.sendEmptyMessage(RECORD_STATE_ED);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				}
			}
		};
	

	class ChatListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return chatMsgList.size();
		}

		@Override
		public ChatMsg getItem(int position) {
			return chatMsgList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			final ChatMsg chatMsg = chatMsgList.get(position);
			int type = chatMsg.getSender();			
				switch (type) {
				case ChatMsg.SEND_MY:
					convertView = inflater.inflate(R.layout.chat_item_myself , null);
					holder = new Holder();
					holder.messageName=(TextView)convertView.findViewById(R.id.visitor_nickName);
					holder.messageIoc=(ImageView)convertView.findViewById(R.id.imageView1);
					holder.messageContent=(TextView)convertView.findViewById(R.id.chat_content_textview_my);
					holder.messageTime=(TextView)convertView.findViewById(R.id.chatting_time_tv);
					switch (chatMsg.getIsVoiceMessage()) {
					//语音消息
					case ChatMsg.ISVOICEMESSAGE_TRUE:
						holder.messageContent.setText(chatMsg.getVoiceLength()+"'");
						imageAppendText(ChatActivity.this, holder.messageContent, R.drawable.record_me_normal);	
						holder.messageContent.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								startPlaying(VoiceUtil.getFile(StringUtils.decodeBase64(chatMsg.getChatContent().trim()), mr.path, ""));
							}
						});
						break;						
					default:
					//普通消息
						holder.messageContent.setText(chatMsg.getChatContent());
						break;
					}
					holder.messageName.setText(chatMsg.getUsername());
					holder.messageTime.setText(chatMsg.getSendTime());
					imageLoader.displayImage(myAvatar, holder.messageIoc, options);
					break;
				case ChatMsg.SEND_FRIEND:
					convertView = inflater.inflate(R.layout.chat_item_employee , null);
					holder = new Holder();
					holder.messageName=(TextView)convertView.findViewById(R.id.visitor_nickName);
					holder.messageIoc=(ImageView)convertView.findViewById(R.id.imageView1);
					holder.messageContent=(TextView)convertView.findViewById(R.id.chat_content_textview);
					holder.messageTime=(TextView)convertView.findViewById(R.id.chatting_time_tv);
					switch (chatMsg.getIsVoiceMessage()) {
					//语音消息
					case ChatMsg.ISVOICEMESSAGE_TRUE:
						imageAppendText(ChatActivity.this, holder.messageContent, R.drawable.record_other_normal);	
						holder.messageContent.append(chatMsg.getVoiceLength()+"'");						
						holder.messageContent.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startPlaying(VoiceUtil.getFile(StringUtils.decodeBase64(chatMsg.getChatContent().trim()), mr.path, ""));
						}
						});
						
						imageLoader.displayImage(friendInfo.getAvatar(), holder.messageIoc, options);
						break;						
					default:
					//普通消息
						holder.messageContent.setText(chatMsg.getChatContent());
						break;
					}
					holder.messageName.setText(chatMsg.getUsername());
					holder.messageTime.setText(chatMsg.getSendTime());
					break;
				}
			return convertView;
		}

		class Holder {
			TextView messageContent;
			ImageView messageIoc;
			TextView messageTime;
			TextView messageName;
		}
	}
	
	
	
	public static void imageAppendText(final Context context,
			final TextView view, final int drawableId) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				drawableId);
		// 根据Bitmap对象创建ImageSpan对象
		ImageSpan imageSpan = new ImageSpan(context, bitmap);
		// 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
		final SpannableString spannableString = new SpannableString("face");
		// 用ImageSpan对象替换face
		spannableString.setSpan(imageSpan, 0, 4,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 将随机获得的图像追加到EditText控件的最后
		view.append(spannableString);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}
	
	private class MessageReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(MessageConstant.RECEIVE_MESSAGE_ACTION.equals(intent.getAction())){
				ChatMsg chatMsg = (ChatMsg) intent.getExtras().getSerializable(MessageConstant.RECEIVE_MESSAGE_ACTION_INPUT_MESSAGE);
				Log.d(TAG, "收到广播");
				if(friendInfo.getUid().equals(chatMsg.getUid())){					
					chatMsgList.add(chatMsg);
					mHandler.sendEmptyMessage(NOTIF_MSG);
				}
				//先做个假的  只能对方发起聊天				
//				friendInfo=new FriendInfo();
//				friendInfo.setJid(chatMsg.getJid());				
			}
		}
		
	}

	
	
	private void startPlaying(String mFileName) {
		if(mPlayer==null){
			mPlayer = new MediaPlayer();
			try {
				mPlayer.setDataSource(mFileName);
				mPlayer.prepare();
				mPlayer.start();
			} catch (IOException e) {
				
			}
		}else if(!mPlayer.isPlaying()){
			mPlayer = new MediaPlayer();
			try {
				mPlayer.setDataSource(mFileName);
				mPlayer.prepare();
				mPlayer.start();
			} catch (IOException e) {
				
			}
		}else if(mPlayer.isPlaying()){
			mPlayer.stop();
		}
	}

	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
	}
	
//	 private void hideKeyBoard() {
//         if (ChatActivity.this.getCurrentFocus() != null) {
//                 if (ChatActivity.this.getCurrentFocus().getWindowToken() != null) {
//                         imm.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                 }
//         }
// }



}
