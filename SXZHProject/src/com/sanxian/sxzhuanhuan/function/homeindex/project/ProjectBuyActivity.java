package com.sanxian.sxzhuanhuan.function.homeindex.project;

import java.util.zip.Inflater;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
import com.sanxian.sxzhuanhuan.entity.GoodsBuyEntity;
import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsContentActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccoAddressIndexActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsPayActivity;
import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsPayFmsActivity;

/**
 * @ClassName: GoodsBuyActivity
 * @Description: TODO(商品购买步骤展示)
 * @author yuanqikai@sanxiantech.com
 * @date 2014-1-9 上午11:11:51
 * 
 */
public class ProjectBuyActivity extends BaseActivity {
	static final int GoodsPayActivity = 0;
	static final int GoodsPayFmsActivity = 1;
	static final int ACTIVITY_FINISH = 4;
	static final int REQUEST_ADDR = 2;

	TextView goods_buy_name;
	CommonTitle project_common_title;
	TextView goods_buy_phone;
	TextView goods_buy_addr;
	TextView goods_buy_company;
	TextView goods_buy_project_name;
	TextView goods_buy_price;
	TextView goods_buy_num;
	LinearLayout goods_fms;
	TextView goods_buy_total;
	TextView tv_reward_money;
	TextView tv_reward_return_days;
	TextView tv_reward_content;
	TextView tv_reward_limit;
	TextView project_buy_total;
	TextView goods_buy_color;
	TextView goods_buy_size;
	TextView goods_buy_fms;
	ImageView goods_buy_image;

	RelativeLayout goodsBuyLay;
	GoodsBuyEntity entity;
	String reward_money;
	String reward_return_days;
	String reward_content;
	String reward_post_free;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		goodsBuyLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.project_buy_activity, null);
		setContentView(goodsBuyLay);
		Intent intent = getIntent();
		entity = (GoodsBuyEntity)intent.getSerializableExtra("entity");
		reward_money = (String)intent.getStringExtra("reward_money");
		reward_return_days = (String)intent.getStringExtra("reward_return_days");
		reward_content = (String)intent.getStringExtra("reward_content");
		reward_post_free = (String)intent.getStringExtra("reward_post_free");
		
		//project_common_title
		
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		project_common_title = (CommonTitle) findViewById(R.id.project_common_title);
		project_common_title.show(true, "返回", true, "我要预购", false, null) ;
		project_common_title.btnLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		goods_buy_name = (TextView) findViewById(R.id.goods_buy_name);
		goods_buy_phone = (TextView) findViewById(R.id.goods_buy_phone);
		goods_buy_addr = (TextView) findViewById(R.id.goods_buy_addr);
		goods_buy_project_name = (TextView) findViewById(R.id.goods_buy_project_name);
		goods_buy_price = (TextView) findViewById(R.id.goods_buy_price);
		goods_buy_total = (TextView) findViewById(R.id.goods_buy_total);
		tv_reward_money = (TextView) findViewById(R.id.reward_money);
		tv_reward_return_days = (TextView) findViewById(R.id.reward_return_days);
		tv_reward_content = (TextView) findViewById(R.id.reward_content);
		tv_reward_limit = (TextView) findViewById(R.id.reward_limit);
		project_buy_total = (TextView) findViewById(R.id.project_buy_total);
		
		tv_reward_money.setText("￥  "+reward_money+" RMB × 1");
		tv_reward_return_days.setText("预计回报时间："+reward_return_days+"天");
		tv_reward_content.setText(reward_content);
		project_buy_total.setText("1件商品，￥ "+reward_money+"元");
		if((reward_content!=null) && (!"".equals(reward_content.trim()))){
			tv_reward_limit.setText("");
		}
		
		if(entity!=null){
			goods_buy_name.setText(entity.getPersonName());
			goods_buy_phone.setText(entity.getPhoneNum());
			goods_buy_addr.setText(entity.getAddress());
			ImageLoader loader = ImageLoader.getInstance();
			loader.displayImage(entity.getGoodsImage(), goods_buy_image);
			goods_buy_addr.setText(entity.getAddress());
		}
//		double total = Integer.valueOf(entity.getGoodsNum())*Double.valueOf(entity.getGoodsPice())*1.0;
//		goods_buy_total.setText(entity.getGoodsNum()+"件商品，￥ "+total+"元");
	}

	public void dellwithbuttons(View view) {
		int id = dellViewBackground(view);
		switch (id) {
		case R.id.project_buy_submit:
			dellProjectBuyConfirm(view);
			break;
		case R.id.project_buy_changeAddr:
			Intent intent = new Intent(this,MyAccoAddressIndexActivity.class);
			intent.putExtra("from", "goods_buy");
			startActivityForResult(intent, GoodsContentActivity.REQUEST_NEW_ADDR);
			break;

		default:
			break;
		}
	}

	public int dellViewBackground(View view) {

		return view.getId();
	}

	public void dellProjectBuyConfirm(View view) {
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
			
		}else if(requestCode == GoodsContentActivity.REQUEST_NEW_ADDR){
			Log.d("REQUEST_NEW_ADDR", "REQUEST_NEW_ADDR  111");
			if(resultCode == MyAccoAddressIndexActivity.CHOOSE_ADDRESS_CODE){
				AddressInfo addr = (AddressInfo)data.getSerializableExtra("addressInfo");
				goods_buy_name.setText(addr.getName());
				goods_buy_phone.setText(addr.getPhoneNum());
				goods_buy_addr.setText(addr.getAddress());
			}
		}
	}
}
