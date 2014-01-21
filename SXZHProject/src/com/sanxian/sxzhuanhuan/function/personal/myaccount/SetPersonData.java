package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

public class SetPersonData extends BaseActivity {
    
	private EditText input_content_et;
	private String set_type = "";
	private String content = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_personinfo);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		Intent intent = getIntent();
		if (intent != null) {
			set_type = intent.getStringExtra("set_type");
			content = intent.getStringExtra("content");
			if ("username".equals(set_type)) {
				setTitle("姓名");
			} else if ("signature".equals(set_type)) {
				setTitle("个性签名");
			}
		}
		setRight("保存");
		input_content_et = (EditText) findViewById(R.id.input_content_et);
		input_content_et.setText(content);
		input_content_et.setSelection(input_content_et.length());
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
