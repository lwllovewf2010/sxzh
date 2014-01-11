package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
/**
 * 我的账号设置支付密码页面
 * @author joe
 *
 */
public class MyAccoInviteUserActivity extends BaseActivity implements OnClickListener {

	private TextView invition_hint;
	private LinearLayout invitation_layout;
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
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		invitation_layout.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
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

		default:
			break;
		}
	}
}
