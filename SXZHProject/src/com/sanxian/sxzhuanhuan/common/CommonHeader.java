package com.sanxian.sxzhuanhuan.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;

/**   
 * @Title: CommonHeader.java 
 * @Package com.sanxian.sxzhuanhuan.common 
 * @Description: 公共标题栏
 * @author zhangfl@sanxian.com
 * @date 2014-2-12 上午10:01:31 
 * @version V1.0   
 */
public class CommonHeader extends LinearLayout {

	public ImageView ivPre = null ;
	public TextView tvLeft = null ;
	public TextView tvMid = null ;
	public TextView tvRight = null ;
	public ImageView ivNext = null ;
	
	public CommonHeader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CommonHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.common_header, this) ;
		
		initView() ;
	}

	private void initView() {
		ivPre = (ImageView)findViewById(R.id.header_pre_iv) ;
		tvLeft = (TextView)findViewById(R.id.header_left_tv) ;
		tvMid = (TextView) findViewById(R.id.header_mid_tv) ;
		tvRight = (TextView) findViewById(R.id.header_rigth_tv) ;
		ivNext = (ImageView) findViewById(R.id.header_next_iv) ;
		
	}
	
	/**
	 * 
	 * @param bivPre 是否显示←
	 * @param left 是否显示左边的文字
	 * @param leftTitle 左边的文字
	 * @param mid  是否显示中间的文字
	 * @param midTitle 中间的文字
	 * @param right是否显示右边的文字
	 * @param rightTitle 右边的文字
	 * @param bivNext 是否显示→
	 */
	public void show(boolean bivPre , boolean left , String leftTitle , boolean mid , String midTitle , boolean right , String rightTitle , boolean bivNext) {
		if(bivPre) {
			ivPre.setVisibility(View.VISIBLE) ;
		} else {
			ivPre.setVisibility(View.INVISIBLE) ;
		}
		
		if(left) {
			tvLeft.setVisibility(View.VISIBLE) ;
			tvLeft.setText(leftTitle) ;
		} else {
			tvLeft.setVisibility(View.INVISIBLE ) ;
		}
		
		if(mid) {
			tvMid.setVisibility(View.VISIBLE) ;
			tvMid.setText(midTitle) ;
		} else {
			tvMid.setVisibility(View.INVISIBLE) ;
		}
		
		if(right) {
			tvRight.setVisibility(View.VISIBLE) ;
			tvRight.setText(rightTitle) ;
		} else {
			tvRight.setVisibility(View.INVISIBLE) ;
		}
		
		if(bivNext) {
			ivNext.setVisibility(View.VISIBLE) ;
		} else {
			ivNext.setVisibility(View.INVISIBLE ) ;
		}
	}
	
}
