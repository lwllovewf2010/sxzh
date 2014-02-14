package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
/**
 * 我的账号设置支付密码页面
 * @author joe
 *
 */
public class MyAccoSetPayPWActivity extends BaseActivity {

	private LinearLayout set_paypw_layout,modify_paypw_layout;//设置支付密码，修改支付密码
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_set_paypw);
		initView();
		initListener();
		
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		boolean is_set_paypw = getIntent().getBooleanExtra("is_set_paypw", false);
		set_paypw_layout = (LinearLayout)findViewById(R.id.set_paypw_layout);
		modify_paypw_layout = (LinearLayout)findViewById(R.id.modify_paypw_layout);
		if(!is_set_paypw){
			setTitle("设置支付密码");
			set_paypw_layout.setVisibility(View.VISIBLE);
			modify_paypw_layout.setVisibility(View.GONE);
		}else{
			setTitle("修改支付密码");
			set_paypw_layout.setVisibility(View.GONE);
			modify_paypw_layout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
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
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;

		default:
			break;
		}
	}

}
