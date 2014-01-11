package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
/**
 * 二维码名片页面
 * @author joe
 *
 */
public class ErWeiMaActivity extends BaseActivity implements OnClickListener{
  
	private Animation showAction, hideAction;
	private boolean is_show = true;
	private RelativeLayout erweima_layout;
	private LinearLayout bottom_layout;
	private Button take_photo_btn,album_btn,cancel_btn;//分享，保存，取消

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_erweima);
		initView();
		initListener();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setRight("更多");
		setTitle("二维码名片");
		
		erweima_layout = (RelativeLayout)findViewById(R.id.erweima_layout);
		bottom_layout = (LinearLayout)findViewById(R.id.bottom_select_layout);
		showAction = AnimationUtils.loadAnimation(this,R.anim.slide_up_in);
     	hideAction = AnimationUtils.loadAnimation(this, R.anim.slide_down_out);
     	take_photo_btn = (Button)findViewById(R.id.take_photo_btn);
     	album_btn = (Button)findViewById(R.id.album_btn);
     	cancel_btn = (Button)findViewById(R.id.cancel_btn);
     	
     	take_photo_btn.setText("分享我的二维码");
     	album_btn.setText("保存二维码到手机");
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);
		erweima_layout.setOnClickListener(this);
		take_photo_btn.setOnClickListener(this);
		album_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.title_right:
			if(is_show){
				showBottomLayout();
			}else{
				hideBottomLayout();
			}
			break;
		case R.id.erweima_layout:
			if(!is_show){
				hideBottomLayout();
			}
			break;
		case R.id.take_photo_btn:// 分享二维码
			break;
		case R.id.album_btn://保存到手机
			break;
		case R.id.cancel_btn:
			hideBottomLayout();
			break;
		default:
			break;
		}
	}
 
	/**
	 * 显示底部菜单
	 * joe
	 */
	
	private void showBottomLayout(){
		bottom_layout.setVisibility(View.VISIBLE);	
		bottom_layout.startAnimation(showAction);
		is_show = false;
	};
	
	/**
	 * 隐藏底部菜单
	 * joe
	 */
	private void hideBottomLayout(){
		bottom_layout.setVisibility(View.GONE);	
		bottom_layout.startAnimation(hideAction);
		is_show = true;
	};
}
