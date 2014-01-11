package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
/**
 * 我的账号详细页面
 * @author joe
 *
 */
public class MyAccountInfoActivity extends BaseActivity implements OnClickListener{
	/*修改密码，实名验证，手机验证，邮箱验证，个人地址管理，设置支付密码*/
	private RelativeLayout set_passw_layout,certify_name_layout,certify_phone_layout,certify_email_layout,preson_address_layout,set_pay_passw_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_info);
		initView();
		initListener();
	};

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("我的账号");
		
		set_passw_layout = (RelativeLayout)findViewById(R.id.set_passw_layout);
		certify_name_layout = (RelativeLayout)findViewById(R.id.certify_name_layout);
		certify_phone_layout = (RelativeLayout)findViewById(R.id.certify_phone_layout);
		certify_email_layout = (RelativeLayout)findViewById(R.id.certify_email_layout);
		preson_address_layout = (RelativeLayout)findViewById(R.id.preson_address_layout);
		set_pay_passw_layout = (RelativeLayout)findViewById(R.id.set_pay_passw_layout);
		
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		set_passw_layout.setOnClickListener(this);
		certify_name_layout.setOnClickListener(this);
		certify_phone_layout.setOnClickListener(this);
		certify_email_layout.setOnClickListener(this);
		preson_address_layout.setOnClickListener(this);
		set_pay_passw_layout.setOnClickListener(this);
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
		case R.id.set_passw_layout:
			Intent setpw = new Intent(MyAccountInfoActivity.this,MyAccoSetLoginPWActivity.class);
			startActivity(setpw);
			break;
		case R.id.certify_name_layout:
			Intent certifyname = new Intent(MyAccountInfoActivity.this,MyAccoCertifyNameActivity.class);
			startActivity(certifyname);
			break;
		case R.id.certify_phone_layout:
			Intent bindphone = new Intent(MyAccountInfoActivity.this,MyAccoBindPhoneActivity.class);
			startActivity(bindphone);
			break;
		case R.id.certify_email_layout:
			Intent bindemail = new Intent(MyAccountInfoActivity.this,MyAccoBindEmailActivity.class);
			startActivity(bindemail);
			break;
		case R.id.preson_address_layout:
			Intent address = new Intent(MyAccountInfoActivity.this,MyAccoAddressIndexActivity.class);
			startActivity(address);
			break;
		case R.id.set_pay_passw_layout:
			Intent setpaypw = new Intent(MyAccountInfoActivity.this,MyAccoSetPayPWActivity.class);
			startActivity(setpaypw);
			break;
		default:
			break;
		}
	}

}
