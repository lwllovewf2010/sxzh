package com.sanxian.sxzhuanhuan.function.homeindex;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;



public class PublishProjectLast extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_project_last);
		init();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_Left:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void refresh(Object... param) {
		
	}
	
	/**
	 * 初始化数据
	 */
	private void init(){
		initView();
		setTitle("需求发布");
		setLeft("从设回报");	
		findViewById(R.id.title_Left).setOnClickListener(this);
		
	}

}
