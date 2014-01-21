package com.sanxian.sxzhuanhuan.function.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;

/**
 * @Title: RealAuthActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.login
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-13 下午6:05:41
 * @version V1.0
 */
public class RealAuthActivity extends BaseActivity implements OnClickListener {
	private CommonTitle conTitle = null ;
	private EditText etRealName = null ;
	private EditText etRealID = null ;
	private ImageView ivIDFront = null ;
	private ImageView ivIDBack = null ;
	private ImageView ivMore = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.real_auth);

		init();
		conTitle.show(true, "返回", true, "实名认证", true, "确定");
	}
	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		etRealName = (EditText) findViewById(R.id.real_auth_tv_realname) ;
		etRealID = (EditText) findViewById(R.id.real_auth_tv_ID) ;
		ivIDFront = (ImageView) findViewById(R.id.real_auth_iv_ID_front) ;
		ivIDBack = (ImageView) findViewById(R.id.real_auth_iv_ID_back) ;
		ivMore = (ImageView) findViewById(R.id.real_auth_iv_ID_more) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		conTitle.btnRight.setOnClickListener(this) ;
		ivMore.setOnClickListener(this) ;
		
		ivIDFront.setVisibility(View.GONE ) ;
		ivIDBack.setVisibility(View.GONE ) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.title_btn_left:
				System.out.println("return");
				finish() ;
				break;
			case R.id.title_btn_right :
				System.out.println("ok");
				finish() ;
				break ;
				
			case R.id.real_auth_iv_ID_more :
				System.out.println("more");
				if(View.GONE == ivIDFront.getVisibility()) {
					ivIDFront.setVisibility(View.VISIBLE ) ;
				} else if (View.GONE == ivIDBack.getVisibility()) {
					ivIDBack.setVisibility(View.VISIBLE ) ;
				}
				break ;

		}
	}

}
