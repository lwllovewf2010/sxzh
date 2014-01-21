package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 我的账号绑定手机号码页面
 * @author joe
 *
 */
public class MyAccoBindPhoneActivity extends BaseActivity {
    private EditText input_oldphone_et;
    private EditText input_newphone_et;
    private EditText input_verify_code;
    private Button get_code_btn;
    private Button bind_phone_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_bind_phone);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("绑定手机号");
		input_oldphone_et = (EditText)findViewById(R.id.input_oldphone_et);
		input_newphone_et = (EditText)findViewById(R.id.input_newphone_et);
		input_verify_code = (EditText)findViewById(R.id.input_verify_code);
		get_code_btn = (Button)findViewById(R.id.get_code_btn);
		bind_phone_btn = (Button)findViewById(R.id.bind_phone_btn);
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
		case R.id.get_code_btn:
			
			break;
		case R.id.bind_phone_btn:
			
			break;
		default:
			break;
		}
	}

	public void checkData(){
		String oldphone = input_oldphone_et.getText().toString().trim();
		if(!"".equals(oldphone)){
			if(Util.checkMobile(oldphone)){
				
			}
		}else{
			
		}
	}
}
