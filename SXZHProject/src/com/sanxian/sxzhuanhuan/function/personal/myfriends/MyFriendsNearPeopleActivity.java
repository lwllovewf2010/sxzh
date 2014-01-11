package com.sanxian.sxzhuanhuan.function.personal.myfriends;


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
 * @ClassName: MyFriendsNearPeopleActivity
 * @Description:附近的人
 * @author honaf
 * @date 2014-1-2 下午5:43:30
 */
public class MyFriendsNearPeopleActivity extends BaseActivity implements IBaseActivity, OnClickListener {
	private WebView wv;
	private ProgressDialog pdialog;
	String path = "http://map.baidu.com/";
	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfriends_nearpeople);
		intImageUtil();
		this.initView();
		this.initListener();
		initWebView();

	}

	public void initWebView() {
		wv.getSettings().setDefaultTextEncodingName("UTF-8");
		// 设置不显示滚动条
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		// 开启javascript设置，否则WebView不执行js脚本
		wv.getSettings().setJavaScriptEnabled(true);
		Intent intent = this.getIntent();
		path = intent.getStringExtra("path");
		loadurl(path);
	}

	@Override
	public void initView() {
		super.initView();
		wv = (WebView) this.findViewById(R.id.webview_nearpeople);
		super.button_right.setVisibility(View.GONE);

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

	public void loadurl(String path) {
		wv.setWebViewClient(new MyWebViewClient());
		// wv.setWebChromeClient(new MyWebChromeClient());
		// 加载进度
		pdialog = new ProgressDialog(getApplicationContext());
		// 加载本地网页
		// wv.loadUrl("file:///android_asset/webview.html");
		// 加载Intent网页
		wv.loadUrl(path);

	}

	/*
	 * 
	 * 页面中的链接，如果希望点击链接继续在当前browser中响应， 而不是新开Android的系统browser中响应该链接， 必须覆盖
	 * webview的WebViewClient对象。
	 */
	final class MyWebViewClient extends WebViewClient {
		/* 页面加载时有loading提示 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			try {
				pdialog = ProgressDialog.show(MyFriendsNearPeopleActivity.this, null, "loading...", true, true);
				super.onPageStarted(view, url, favicon);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/* 页面加载完关闭loading提示 */
		@Override
		public void onPageFinished(WebView view, String url) {
			try {
				pdialog.dismiss();
				super.onPageFinished(view, url);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/* 页面加载失败后的错误提示 */
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			pdialog.dismiss();
			Toast.makeText(getApplicationContext(), "load fail .. " + failingUrl + description, Toast.LENGTH_SHORT);
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
	}

}
