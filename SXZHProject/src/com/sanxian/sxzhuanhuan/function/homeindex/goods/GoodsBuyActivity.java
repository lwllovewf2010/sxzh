package com.sanxian.sxzhuanhuan.function.homeindex.goods;

import java.util.zip.Inflater;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.GoodsBuyEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @ClassName: GoodsBuyActivity
 * @Description: TODO(商品购买步骤展示)
 * @author yuanqikai@sanxiantech.com
 * @date 2014-1-9 上午11:11:51
 * 
 */
public class GoodsBuyActivity extends BaseActivity {
	static final int GoodsPayActivity = 0;
	static final int GoodsPayFmsActivity = 1;
	static final int ACTIVITY_FINISH = 4;

	TextView goods_buy_name;
	TextView goods_buy_phone;
	TextView goods_buy_addr;
	TextView goods_buy_company;
	TextView goods_buy_project_name;
	TextView goods_buy_price;
	TextView goods_buy_num;
	LinearLayout goods_fms;
	TextView goods_buy_total;
	TextView goods_buy_color;
	TextView goods_buy_size;
	TextView goods_buy_fms;
	ImageView goods_buy_image;

	RelativeLayout goodsBuyLay;
	GoodsBuyEntity entity;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		goodsBuyLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_buy_activity, null);
		setContentView(goodsBuyLay);
		Intent intent = getIntent();
		entity = (GoodsBuyEntity)intent.getSerializableExtra("entity");
		
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		goods_buy_name = (TextView) findViewById(R.id.goods_buy_name);
		goods_buy_phone = (TextView) findViewById(R.id.goods_buy_phone);
		goods_buy_addr = (TextView) findViewById(R.id.goods_buy_addr);
		goods_buy_company = (TextView) findViewById(R.id.goods_buy_company);
		goods_buy_project_name = (TextView) findViewById(R.id.goods_buy_project_name);
		goods_buy_price = (TextView) findViewById(R.id.goods_buy_price);
		goods_buy_num = (TextView) findViewById(R.id.goods_buy_num);
		goods_fms = (LinearLayout) findViewById(R.id.goods_fms);
		goods_buy_total = (TextView) findViewById(R.id.goods_buy_total);
		goods_buy_color = (TextView) findViewById(R.id.goods_buy_color);
		goods_buy_size = (TextView) findViewById(R.id.goods_buy_size);
		goods_buy_image = (ImageView) findViewById(R.id.goods_buy_image);
		goods_buy_fms = (TextView) findViewById(R.id.goods_buy_fms);
		if(entity!=null){
			goods_buy_name.setText(entity.getPersonName());
			goods_buy_phone.setText(entity.getPhoneNum());
			goods_buy_addr.setText(entity.getAddress());
			goods_buy_company.setText(entity.getEnterprise());
			goods_buy_project_name.setText(entity.getGoodsName());
			goods_buy_num.setText("×"+entity.getGoodsNum());
			goods_buy_price.setText("￥  "+entity.getGoodsPice());
			goods_buy_color.setText(entity.getGoodsColor());
			goods_buy_size.setText(entity.getGoodsSize());
			ImageLoader loader = ImageLoader.getInstance();
			loader.displayImage(entity.getGoodsImage(), goods_buy_image);
			goods_buy_addr.setText(entity.getAddress());
		}
		double total = Integer.valueOf(entity.getGoodsNum())*Double.valueOf(entity.getGoodsPice())*1.0;
		goods_buy_total.setText(entity.getGoodsNum()+"件商品，￥ "+total+"元");
	}

	public void dellwithbuttons(View view) {
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.goods_buy_confirm:
			dellgoodsBuyConfirm(view);
			break;
		case R.id.goods_fms:
			dellgoodsBuyFMS(view);
			break;
		case R.id.goods_buy_back:
			finish();
			break;

		default:
			break;
		}
	}

	public int dellViewBackground(View view) {

		return view.getId();
	}

	public void dellgoodsBuyConfirm(View view) {
		Intent intent = new Intent(this, GoodsPayActivity.class);
		startActivityForResult(intent, GoodsPayActivity);
	}

	public void dellgoodsBuyFMS(View view) {
		Intent intent = new Intent(this, GoodsPayFmsActivity.class);
		startActivityForResult(intent, GoodsPayFmsActivity);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == GoodsPayActivity) {
			// 如果设置了ACTIVITY_FINISH，则直接销毁该activity
			if (resultCode == ACTIVITY_FINISH) {
				finish();
			}
		}else if(GoodsPayFmsActivity == requestCode){
			if(data!=null){
				String text = data.getStringExtra("result");
				goods_buy_fms.setText(text);
			}
			
		}
	}
}
