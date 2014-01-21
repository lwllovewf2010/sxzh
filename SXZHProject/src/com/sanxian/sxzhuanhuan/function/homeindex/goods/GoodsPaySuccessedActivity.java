package com.sanxian.sxzhuanhuan.function.homeindex.goods;

import java.util.zip.Inflater;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/** 
* @ClassName: GoodsBuyActivity 
* @Description: TODO(商品购买步骤展示) 
* @author yuanqikai@sanxiantech.com
* @date 2014-1-9 上午11:11:51 
*  
*/
public class GoodsPaySuccessedActivity extends BaseActivity {
	RelativeLayout goodsPaySuccessedLay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		goodsPaySuccessedLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_pay_successed_activity, null);
		setContentView(goodsPaySuccessedLay);
	}
	
	public void dellwithbuttons(View view){
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.goods_pay_confirm:
			goodsPayConfirm(view);
			break;
		case R.id.goods_pay_back2:
			finish();
			break;

		default:
			break;
		}
	}
	
	public int dellViewBackground(View view){
		
		return view.getId();
	}
	
public void goodsPayConfirm(View view){
		finish();
}
}
