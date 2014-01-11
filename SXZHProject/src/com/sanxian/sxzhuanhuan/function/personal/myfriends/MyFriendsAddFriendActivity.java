package com.sanxian.sxzhuanhuan.function.personal.myfriends;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;

/**
 * 
* @ClassName: MyFriendsAddFriendActivity  
* @Description: 各种添加好友入口
* @author honaf
* @date 2014-1-2 下午6:16:58
 */
public class MyFriendsAddFriendActivity extends BaseActivity implements IBaseActivity, OnClickListener {
	private RelativeLayout addfriend_fromcontacts_rl;
	private RelativeLayout scan_rl;
	private RelativeLayout search_rl;
	
	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfriends_addfriend);
		intImageUtil();
		this.initView();
		this.initListener();

	}



	@Override
	public void initView() {
		super.initView();
		super.button_right.setVisibility(View.GONE);
		super.title_txt.setText("添加好友");
		addfriend_fromcontacts_rl=(RelativeLayout)this.findViewById(R.id.addfriend_fromcontacts_rl);
		scan_rl=(RelativeLayout)this.findViewById(R.id.scan_rl);
		search_rl=(RelativeLayout)this.findViewById(R.id.search_rl);
		
				
		
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);
		addfriend_fromcontacts_rl.setOnClickListener(this);
		scan_rl.setOnClickListener(this);
		search_rl.setOnClickListener(this);

	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {

		case R.id.title_Left:
			this.finish();
			break;
		case R.id.addfriend_fromcontacts_rl:
			break;
		case R.id.scan_rl:
			break;
		case R.id.search_rl:
			intent=new Intent(this, MyFriendsSearchActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	

}
