package com.sanxian.sxzhuanhuan.function.personal.microworld;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;

/**
 * 
* @ClassName: MicroWorldIndexActivity  
* @Description: 微世界 
* @author honaf
* @date 2014-1-3 下午5:38:54
 */
public class MicroWorldIndexActivity extends BaseActivity implements IBaseActivity, OnClickListener {
	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfriends_searchpeople);
		intImageUtil();
		this.initView();
		this.initListener();

	}


	@Override
	public void initView() {
		super.initView();
		
		super.button_right.setVisibility(View.GONE);
		super.title_txt.setText("搜用户");
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);

	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.title_Left:
			this.finish();
			break;

		default:
			break;
		}
	}


}
