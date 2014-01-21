package com.sanxian.sxzhuanhuan.function.homeindex.goods;

import java.util.zip.Inflater;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * @ClassName: GoodsBuyActivity
 * @Description: TODO(商品支付快递)
 * @author yuanqikai@sanxiantech.com
 * @date 2014-1-9 上午11:11:51
 * 
 */
public class GoodsPayFmsActivity extends BaseActivity {
	RelativeLayout goodsPayFmsLay;
	RadioGroup fms_radioGroup;
	RadioButton fms_radio0;
	RadioButton fms_radio1;
	public final static int FMSSettingOK = 5; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		goodsPayFmsLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_pay_fms_activity, null);
		setContentView(goodsPayFmsLay);
		
		initView();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		fms_radioGroup = (RadioGroup)findViewById(R.id.fms_radioGroup);
		fms_radio0 = (RadioButton)findViewById(R.id.fms_radio0);
		fms_radio1 = (RadioButton)findViewById(R.id.fms_radio1);
	}

	public void dellwithbuttons(View view) {
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.goods_pay_fms_back:
			goodsPayFmsBack(view);
			break;

		default:
			break;
		}
	}

	public int dellViewBackground(View view) {

		return view.getId();
	}

	public void goodsPayFmsBack(View view) {
		int id = fms_radioGroup.getCheckedRadioButtonId();
		String text = "";
		switch (id) {
		case R.id.fms_radio0:
			text = fms_radio0.getText().toString();
			break;
		case R.id.fms_radio1:
			text = fms_radio1.getText().toString();
			break;

		default:
			break;
		}
		Intent intent =new Intent();  
        intent.putExtra("result", text); 
        setResult(FMSSettingOK, intent);//设置返回数据  
		finish();
	}
}
