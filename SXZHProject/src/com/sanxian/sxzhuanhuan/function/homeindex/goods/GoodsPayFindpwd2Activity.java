package com.sanxian.sxzhuanhuan.function.homeindex.goods;

import java.util.zip.Inflater;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

/**
 * @ClassName: GoodsBuyActivity
 * @Description: TODO(商品购买步骤展示)
 * @author yuanqikai@sanxiantech.com
 * @date 2014-1-9 上午11:11:51
 * 
 */
public class GoodsPayFindpwd2Activity extends BaseActivity {
	static final int PAY_SEND_SMS_REQUEST = 0;
	static final int PAY_CALL_REQUEST = 1;
	static final int PAY_3rdPAY_REQUEST = 2;

	RelativeLayout findPWDLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		findPWDLayout = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_pay_findpwd2_activity, null);
		setContentView(findPWDLayout);
		
	}

	public void dellwithbuttons(View view) {
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.goods_pay_findpwd2_next:
			goodsPayFindpwd2Next(view);
			break;
			
		default:
			break;
		}
	}

	public int dellViewBackground(View view) {

		return view.getId();
	}

	public void goodsPayFindpwd2Next(View view) {
		Intent intent = new Intent(this, GoodsPayFindpwd3Activity.class);
		startActivityForResult(intent, PAY_SEND_SMS_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PAY_SEND_SMS_REQUEST) {
			// 如果支付成功提示界面返回了，则条跳转到GoodsBuyActivity
			setResult(GoodsBuyActivity.ACTIVITY_FINISH);
			finish();
		}
	}
}
