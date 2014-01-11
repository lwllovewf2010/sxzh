package com.sanxian.sxzhuanhuan.common;

import android.content.Context;
import android.view.View;

public interface FragmentCallback {
   
	void initView(View view);// 初始化界面数据

	void initListener(); // / 初始化事件

	void refresh(Object... param);// 数据回调刷新
	

    void dialogControySure(Object... param);

	
	 void dialogControyCancel();
	
	Context getContext();//获取上下文
}
