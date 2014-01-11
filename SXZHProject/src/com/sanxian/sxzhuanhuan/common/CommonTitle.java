package com.sanxian.sxzhuanhuan.common;

import com.sanxian.sxzhuanhuan.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**   
 * @Title: CommonTitle.java 
 * @Package com.sanxian.sxzhuanhuan.common 
 * @Description: 公共标题栏(标题栏特征：左右为Button , 中间为TextView)
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 上午10:01:31 
 * @version V1.0   
 */
public class CommonTitle extends LinearLayout {

	public Button btnLeft = null ; 
	public TextView tvTitle = null ;
	public Button btnRight = null ;
	
	public CommonTitle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CommonTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.common_title, this) ;
		
		initView() ;
	}

	private void initView() {
		btnLeft = (Button) findViewById(R.id.title_btn_left) ;
		tvTitle = (TextView) findViewById(R.id.title_tv_mid) ;
		btnRight = (Button) findViewById(R.id.title_btn_right) ;
		
	}
	
	/**
	 * 
	 * @param left 左边Button是否显示
	 * @param leftTitle  左边Button显示的文字
	 * @param mid  中间的标题是否显示
	 * @param midTitle	中间标题显示的文字
	 * @param right  右边的Button是否显示
	 * @param rightTitle  右边Button显示的文字
	 */
	public void show(boolean left , String leftTitle , boolean mid , String midTitle , boolean right , String rightTitle) {
		if(left) {
			btnLeft.setVisibility(View.VISIBLE) ;
			btnLeft.setText(leftTitle) ;
		} else {
			btnLeft.setVisibility(View.INVISIBLE) ;
		}
		
		if(mid) {
			tvTitle.setVisibility(View.VISIBLE) ;
			tvTitle.setText(midTitle) ;
		} else {
			tvTitle.setVisibility(View.INVISIBLE) ;
		}
		
		if(right) {
			btnRight.setVisibility(View.VISIBLE) ;
			btnRight.setText(rightTitle) ;
		} else {
			btnRight.setVisibility(View.INVISIBLE) ;
		}
	}
	
}
