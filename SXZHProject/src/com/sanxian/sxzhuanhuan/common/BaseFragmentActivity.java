package com.sanxian.sxzhuanhuan.common;


import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseFragmentActivity extends FragmentActivity implements FragmentCallback {

	public TextView title_txt;
	public Button button_left;
	public Button button_right;
	public SApplication app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (SApplication) this.getApplicationContext();
//		String classname = this.getClass().getName();
//		Activity act = app.getActivityByName(classname);
//		if (act == null) {
//			app.allActivity.add(this);
//		}
	}
	@Override
	public void initView(View view) {
		title_txt = (TextView) view.findViewById(R.id.tvTitle);
		button_left = (Button) view.findViewById(R.id.title_Left);
		button_right = (Button) view.findViewById(R.id.title_right);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub

	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置标题
	 * 
	 * @param text
	 */
	public void setTitle(String text) {
		title_txt.setText(text);
	}

	public void displayLeft() {
		button_left.setVisibility(View.INVISIBLE);
	}

	public void displayRight() {
		button_right.setVisibility(View.INVISIBLE);
	}
	@Override
	public void dialogControySure(Object... param) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dialogControyCancel() {
		// TODO Auto-generated method stub
		
	}

}
