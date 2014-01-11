package com.sanxian.sxzhuanhuan.function.homeindex;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * 发表评论
 * @author luozhiren
 *
 */
public class PublishComment extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_comment);
		init();
	}

	
	
	@Override
	public void refresh(Object... param) {
		
	}	
	
	/**
	 * 初始化数据
	 */
	private void init(){
		initView();
		setTitle("创意评论");
		setLeft("取消");
		displayRight();
		button_left.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.top_left_linear:
			this.finish();
			break;

		default:
			break;
		}
	}
}
