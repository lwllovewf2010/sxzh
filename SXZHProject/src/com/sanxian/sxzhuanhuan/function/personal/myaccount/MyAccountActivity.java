package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
/**
 * 我的账户页面
 * @author joe
 * 
 */
public class MyAccountActivity extends BaseActivity implements OnClickListener{
	
	private RelativeLayout preson_data_layout;//个人信息
	private RelativeLayout invitation_layout;//用户邀请
	private RelativeLayout erweima_layout;//二维码名片
	private RelativeLayout my_account_layout;//我的账号
	private ImageView preson_img; //个人头像

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myaccount_index);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("我的账号");
		preson_data_layout = (RelativeLayout)findViewById(R.id.preson_data_layout);
		invitation_layout = (RelativeLayout)findViewById(R.id.invitation_layout);
		erweima_layout = (RelativeLayout)findViewById(R.id.erweima_layout);
		my_account_layout = (RelativeLayout)findViewById(R.id.my_account_layout);
		preson_img = (ImageView)findViewById(R.id.preson_img);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		preson_data_layout.setOnClickListener(this);
		invitation_layout.setOnClickListener(this);
		erweima_layout.setOnClickListener(this);
		my_account_layout.setOnClickListener(this);
		button_left.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
        case R.id.title_Left:
			finish();
			break;
		case R.id.preson_data_layout:
			Intent personintent = new Intent(this,PersonInfoActivity.class);
			startActivity(personintent);
			break;
		case R.id.invitation_layout:
			Intent invitation = new Intent(this,MyAccoInviteUserActivity.class);
			startActivity(invitation);
			break;
        case R.id.erweima_layout:
			Intent erintent = new Intent(this,ErWeiMaActivity.class);
			startActivity(erintent);
			break;
        case R.id.my_account_layout:
        	Intent intent = new Intent(this,MyAccountInfoActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
