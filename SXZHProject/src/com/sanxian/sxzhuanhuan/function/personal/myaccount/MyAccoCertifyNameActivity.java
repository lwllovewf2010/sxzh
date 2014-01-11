package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
/**
 * 我的账号实名认证页面
 * @author joe
 *
 */
public class MyAccoCertifyNameActivity extends BaseActivity {
    
	private ImageView id_card_image1;
	private ImageView id_card_image2;
	private ImageView add_id_card_image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_certify_name);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setRight("确定");
		setTitle("实名认证");
		id_card_image1 = (ImageView)findViewById(R.id.id_card_image1);
		id_card_image2 = (ImageView)findViewById(R.id.id_card_image2);
		add_id_card_image = (ImageView)findViewById(R.id.add_id_card_image);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		add_id_card_image.setOnClickListener(this);
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
		case R.id.add_id_card_image:
			
		    break;
		default:
			break;
		}
	}
}
