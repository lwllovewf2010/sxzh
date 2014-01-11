package com.sanxian.sxzhuanhuan.common;


import java.util.List;

import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.R;


import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseFragment extends Fragment implements FragmentCallback {

	public TextView title_txt;
	public Button button_left;
	public Button button_right;
	public SApplication app;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=(SApplication) SApplication.getInstance().getApplicationContext();
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
	
	public void displayTitle(){
		title_txt.setVisibility(View.INVISIBLE);
	}
   
	public void setRight(String text){
		button_right.setText(text);
	}
	
	public void setLeft(String text){
		button_left.setText(text);
	}
	@Override
	public void dialogControySure(Object... param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dialogControyCancel() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		 if (!app.isActive) {
		 // 程序 从后台唤醒，进入前台
			 app.setActive(true);
		 }
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if (!isAppOnForeground()) {
			// 程序进入后台			
			app.setActive(false);
		}
	}
	
	/**
	 * 程序是否在前台运行
	 * 
	 * @return
	 */
	public boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device
		String packageName = app.getPackageName();

		List<RunningAppProcessInfo> appProcesses = SApplication.activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

}
