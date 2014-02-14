package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.function.personal.PersonalIndex;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 邀请用户页面
 * @author joe
 *
 */
public class MyAccoInviteUserActivity extends BaseActivity implements OnClickListener {

	private TextView invition_hint;
	private LinearLayout invitation_layout;
    private EditText friend_name;
    private EditText friend_phone;
    private Button invitation_btn;
    private CommonAPI api = null;
    private final int INVITEUSER = 13;
    private final int SHOWMIDDIALOG = 12;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_invitation_user);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("用户邀请");
		invition_hint = (TextView)findViewById(R.id.invitation_hint);
		invitation_layout = (LinearLayout)findViewById(R.id.invitation_layout);
		invition_hint.setText(Html.fromHtml("<p>您可邀请好友加入三弦转换平台，并有义务对邀请的好友进行引导和监督。</p><p>当好友使用您的邀请码注册后在平台获得分红，您可获得该好友利润的1%；当好友严重违规时您也将承担连带责任。</p><p>请输入您要邀请的好友姓名和手机号码，将生成的邀请链接发送给好友，好友由此链接注册。此链接仅限该好友在24小时内使用。</p>"));
		friend_name = (EditText)findViewById(R.id.friend_name);
		friend_phone = (EditText)findViewById(R.id.friend_phone);
		invitation_btn = (Button)findViewById(R.id.invitation_btn);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		invitation_layout.setOnClickListener(this);
		invitation_btn.setOnClickListener(this);
//		friend_name.addTextChangedListener(new inputEditListener());
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case INVITEUSER:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				 String data = param[1].toString();
				 try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if(status == 0){
//						 Util.toastInfo(this, "邀请成功");	
						 showMidDialog();
					}else if(status == 1001){
						 Util.toastInfo(this, "登录过期");	
					}else if(status == 403){
						 Util.toastInfo(this, "该手机号已被邀请过");	
					}else if(status == 402){
						 Util.toastInfo(this, "该手机号已注册");		
					}else{
						 Util.toastInfo(this, "邀请失败");	
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.invitation_layout:
			Intent intent = new Intent(MyAccoInviteUserActivity.this,MyAccoInvitationNoteActivity.class);
			startActivity(intent);
			break;
		case R.id.invitation_btn:
			String name = friend_name.getText().toString();
			String phone = friend_phone.getText().toString().trim();
			if (name.length() > 0) {
				if(name.contains(" ")){
					Util.toastInfo(this, "好友姓名中不能含有空格");
				}else{
				Pattern pat = Pattern.compile("^[\u4e00-\u9fa5]*$");
				Matcher mat = pat.matcher(name);
				if(mat.find()){
					if(name.length()> 1){
				   if(phone != null && !"".equals(phone)){
					if(Util.checkMobile(phone)){
						SharedPreferences spf = this.getSharedPreferences(
								"login_user", 0);
						String open_id = spf.getString("open_id", null);
						String token = spf.getString("token", null);
						if (open_id == null || token == null) {
							UIHelper.showLoginActivity(MyAccoInviteUserActivity.this);
						} else {
							if (api == null) {
								api = new CommonAPI();
							}
							Map<String, String> paramsmap = new HashMap<String, String>();
							paramsmap.put("open_id", open_id);
							paramsmap.put("token", token);
							paramsmap.put("friend_name", name);
							paramsmap.put("friend_mobile", phone);
							api.inviteUserOrList("invite_users", paramsmap,this, INVITEUSER);
						}
					}else{
					  Util.toastInfo(this, "输入的手机号码格式不正确");	
					}
				 }else{
					 Util.toastInfo(this, "好友手机号码不能为空");
					}
				 }else{
					 Util.toastInfo(this, "好友姓名不能少于两个汉字");	
					}
				}else{
					Util.toastInfo(this, "好友姓名只能输入中文");	
				 }
				}
			}else{
				Util.toastInfo(this, "好友姓名不能为空");
			}
			break;
		default:
			break;
		}
	}
	
	private void showMidDialog() {
		Intent intent = new Intent(MyAccoInviteUserActivity.this , MiddleDialog.class);
		MiddleDialogInfo info = new MiddleDialogInfo("提示", "您好，邀请短信已发送至您好友的手机。邀请有效时间为24小时。您的好友可在邀请有效期内，填写与您邀请时所填写一致的姓名和手机号，成功注册三弦转换平台。", 
				false, "", "", "确定", "取消");
		intent.putExtra("info", info);
		startActivityForResult(intent, SHOWMIDDIALOG);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SHOWMIDDIALOG) {

			if (resultCode == DialogConstant.MIDDLE_OK) {
				this.finish();
			} else if (resultCode == DialogConstant.MIDDLE_CANCEL) {
				this.finish();
			}
		}
	}
	
	 class inputEditListener implements TextWatcher{

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				String content = friend_name.getText().toString().trim();
				if(content.length() > 10){
					Util.toastInfo(MyAccoInviteUserActivity.this, "姓名请输入10个汉字以内");
					friend_name.setText(content.subSequence(0, 10));
					friend_name.setSelection(10);
					
				}
				
			}
			 
		 }
}
