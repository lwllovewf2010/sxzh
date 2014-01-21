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
public class GoodsPayActivity extends BaseActivity {
	static final int PAY_SEND_SMS_REQUEST = 0;
	static final int PAY_CALL_REQUEST = 1;
	static final int PAY_3rdPAY_REQUEST = 2;
	CheckBox otherBank;

	RelativeLayout goodsPayLay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		goodsPayLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_pay_activity, null);
		setContentView(goodsPayLay);
		
		

		otherBank = (CheckBox) this.findViewById(R.id.goods_pay_other_bank);
		RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup1);
		// 绑定一个匿名监听器
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				// 获取变更后的选中项的ID
				int radioButtonId = arg0.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) findViewById(radioButtonId);
				// 更新文本内容，以符合选中项
				Log.d("yuanqikai",
						"yuanqikai onCheckedChanged radioButtonId = "
								+ radioButtonId);
			}
		});
	}

	public void dellwithbuttons(View view) {
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.goods_pay_back:
			goodsPayBack(view);
			break;
		case R.id.goods_pay_confirm:
			goodsPayConfirm(view);
			break;
		case R.id.goods_pay_other_bank:
			goodsPayOtherBank(view);
			break;
		case R.id.goods_pay_findpwd:
			goodsPayFindPWD(view);
			break;

		default:
			break;
		}
	}

	public int dellViewBackground(View view) {

		return view.getId();
	}

	public void goodsPayBack(View view) {
		finish();
	}

	public void goodsPayConfirm(View view) {
		if(otherBank.isChecked()){
			Intent intent = new Intent(this, GoodsPay3rdPayActivity.class);
			startActivityForResult(intent, PAY_3rdPAY_REQUEST);
		}else{
			Intent intent = new Intent(this, GoodsPaySuccessedActivity.class);
			startActivityForResult(intent, PAY_SEND_SMS_REQUEST);
		}
	}
	
	public void goodsPayOtherBank(View view) {
	}
	public void goodsPayFindPWD(View view) {
		Intent intent = new Intent(this, GoodsPayFindpwdActivity.class);
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
