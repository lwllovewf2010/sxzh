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
public class GoodsPay3rdPayActivity extends BaseActivity {
	RelativeLayout goodsPaySuccessedLay;
	static final int PAY_3rdPAY2_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		goodsPaySuccessedLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_pay_3rdpay_activity, null);
		setContentView(goodsPaySuccessedLay);
	}

	public void dellwithbuttons(View view) {
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.goods_pay_3rdpay_authorize:
			goodsPay3rdpayAuthorize(view);
			break;

		default:
			break;
		}
	}

	public int dellViewBackground(View view) {

		return view.getId();
	}

	public void goodsPay3rdpayAuthorize(View view) {
		Intent intent = new Intent(this, GoodsPay3rdPay2Activity.class);
		startActivityForResult(intent, PAY_3rdPAY2_REQUEST);
	}
}
