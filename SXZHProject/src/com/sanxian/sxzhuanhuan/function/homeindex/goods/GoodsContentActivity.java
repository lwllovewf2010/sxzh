package com.sanxian.sxzhuanhuan.function.homeindex.goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.api.HomeIndexGoodsAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.GoodsBuyEntity;
import com.sanxian.sxzhuanhuan.entity.GoodsItemDetails;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccoAddressIndexActivity;
import com.sanxian.sxzhuanhuan.util.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ClassName: GoodsContent
 * @Description: TODO(显示当前ID的商品信息)
 * @author yuanqikai@sanxiantech.com
 * @date 2014-1-8 下午3:43:03
 * 
 */
public class GoodsContentActivity extends BaseActivity {
	RelativeLayout goodsLay;

	ImageView project_pic1;
	ImageView project_pic2;
	TextView project_name;
	ImageView btn_share;
	TextView cash;
	TextView original_cost;
	TextView express;
	TextView express_addr;
	TextView collection_num;
	TextView sale_num;
	TextView sale_info;
	public TextView goods_num;
	Button goods_add_shopping;
	RelativeLayout goods_detail_relative;
	RelativeLayout goods_param_relative;
	TextView project_describe;
	TextView join_num;
	ImageView adver1_pic;
	TextView adver1_price;
	TextView adver1_name;
	ImageView adver2_pic;
	TextView adver2_price;
	TextView adver2_name;
	ImageView adver3_pic;
	TextView adver3_price;
	TextView adver3_name;
	Button goods_buy;
	ImageView home_menu;
	RadioGroup radioGroup1;
	RadioGroup radioGroup2;

	// 有无默认地址
	private boolean defaultAddress = true;
	private String goodsID;

	private HomeIndexGoodsAPI api = null;
	private final int REQUESTCODE = 12;
	private GoodsItemDetails goodsItem = new GoodsItemDetails();
	//图片参数
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null ;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub MyAccoAddressIndexActivity
		super.onCreate(savedInstanceState);
		goodsLay = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.goods_activity, null);
		setContentView(goodsLay);

		initView();
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.logo)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();
		Intent intent = getIntent();
		goodsID = intent.getStringExtra("goodsID");
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		project_pic1 = (ImageView) findViewById(R.id.project_pic1);
		project_name = (TextView) findViewById(R.id.project_name);
		btn_share = (ImageView) findViewById(R.id.btn_share);
		cash = (TextView) findViewById(R.id.cash);
		original_cost = (TextView) findViewById(R.id.original_cost);
		express = (TextView) findViewById(R.id.express);
		express_addr = (TextView) findViewById(R.id.express_addr);
		collection_num = (TextView) findViewById(R.id.collection_num);
		sale_num = (TextView) findViewById(R.id.sale_num);
		sale_info = (TextView) findViewById(R.id.sale_info);
		LinearLayout goods_num_ll = (LinearLayout) findViewById(R.id.goods_num_ll);
		goods_num = (TextView) goods_num_ll.findViewById(R.id.goods_num);
		radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
		
		goods_add_shopping = (Button) findViewById(R.id.goods_add_shopping);
		goods_detail_relative = (RelativeLayout) findViewById(R.id.goods_detail_relative);
		goods_param_relative = (RelativeLayout) findViewById(R.id.goods_param_relative);
		project_describe = (TextView) findViewById(R.id.project_describe);
		join_num = (TextView) findViewById(R.id.join_num);
		adver1_pic = (ImageView) findViewById(R.id.adver1_pic);
		adver1_price = (TextView) findViewById(R.id.adver1_price);
		adver1_name = (TextView) findViewById(R.id.adver1_name);
		adver2_pic = (ImageView) findViewById(R.id.adver2_pic);
		adver2_price = (TextView) findViewById(R.id.adver2_price);
		adver2_name = (TextView) findViewById(R.id.adver2_name);
		adver3_pic = (ImageView) findViewById(R.id.adver3_pic);
		adver3_price = (TextView) findViewById(R.id.adver3_price);
		adver3_name = (TextView) findViewById(R.id.adver3_name);
		goods_buy = (Button) findViewById(R.id.goods_buy);
		home_menu = (ImageView) findViewById(R.id.home_menu);

	}
	
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {  
        
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());  
  
        @Override  
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {  
            if (loadedImage != null) {  
                ImageView imageView = (ImageView) view;  
                // 是否第一次显示  
                boolean firstDisplay = !displayedImages.contains(imageUri);  
                if (firstDisplay) {  
                    // 图片淡入效果  
                    FadeInBitmapDisplayer.animate(imageView, 500);  
                    displayedImages.add(imageUri);  
                }  
            }  
        }  
    }  

	/*
	 * 现在只有489一条商品有数据 {"goods_id": 489, "open_id": "1_1177_469954"}
	 */
	public void initData() {
		if (api == null) {
			api = new HomeIndexGoodsAPI();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();
		
		paramsMap.put("goods_id", goodsID);
		paramsMap.put("open_id", "1_1177_469954");
		api.getGoodsItemInfo(paramsMap, this, REQUESTCODE);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case REQUESTCODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				Log.d("yuanqikai", "yuanqikai 请求数据 = " + data);
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						JSONObject jsonmode = json.getJSONObject("content");

						if (jsonmode != null && jsonmode.length() > 0) {
								goodsItem.setId(jsonmode.getString("id"));
								goodsItem.setGoods_name(jsonmode
										.getString("goods_name"));
								goodsItem.setGoods_image(jsonmode
										.getString("goods_image"));
//								goodsItem.setProject_describe(jsonmode
//										.getString("project_describe"));
								goodsItem.setGoods_post_min(jsonmode
										.getString("goods_post_min"));
								goodsItem.setGoods_status(jsonmode
										.getString("goods_status"));
								goodsItem.setGoods_price(jsonmode
										.getString("goods_price"));
								goodsItem.setGoods_cost_price(jsonmode
										.getString("goods_cost_price"));
								goodsItem.setGoods_post_type(jsonmode
										.getString("goods_post_type"));
								goodsItem.setGoods_post_free(jsonmode
										.getString("goods_post_free"));
								goodsItem.setGoods_post_price(jsonmode
										.getString("goods_post_price"));
								goodsItem.setGoods_sales_time(jsonmode
										.getString("goods_sales_time"));
								goodsItem.setSales_num(jsonmode
										.getString("sales_num"));
								goodsItem.setCompany_id(jsonmode
										.getString("company_id"));
								goodsItem.setCompany_name(jsonmode
										.getString("company_name"));
								goodsItem.setAttention_num(jsonmode
										.getString("attention_num"));
								goodsItem.setIs_attention(jsonmode
										.getString("is_attention"));
						}
					} else {
						Util.toastInfo(this, "请求失败");
					}
					// adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//更新界面
			refreshUI(goodsItem);
			break;

		default:
			break;
		}
	}
	
	public void refreshUI(GoodsItemDetails goodsItem){
		project_name.setText(goodsItem.getGoods_name());
		cash.setText(goodsItem.getGoods_price());
		original_cost.setText(goodsItem.getGoods_cost_price());
		express.setText(goodsItem.getGoods_post_price());
		collection_num.setText(goodsItem.getAttention_num());
		sale_num.setText(goodsItem.getSales_num());
		project_describe.setText(goodsItem.getProject_describe());
		//加载图片
		imageLoader.displayImage(goodsItem.getGoods_image(), project_pic1, options,animateFirstListener);
	}

	// 处理button事件
	public void dellwithbuttons(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.goods_buy:
			dellgoodsBuybutton(view);
			break;
		case R.id.goods_add_shopping:
			dellgoodsAddShoppingButton(view);
			break;
		case R.id.goods_back:
			finish();
			break;
		case R.id.goods_add_num:
			int num1 = Integer.valueOf((String)(goods_num.getText().toString()));
			goods_num.setText(""+(num1+1));
			break;
		case R.id.goods_desc_num:
			int num2 = Integer.valueOf((String)(goods_num.getText().toString()));
			if(num2 > 0)
				goods_num.setText(""+(num2-1));
			break;
		case R.id.goods_show_project:
			Log.d("", "");//goodsID
			break;

		default:
			break;
		}
	}

	public void dellgoodsAddShoppingButton(View view) {
		// 占位
		((Button) view).setText("加入购物车成功");
		((Button) view).setClickable(false);
		((Button) view).setBackgroundColor(getResources().getColor(
				android.R.color.holo_blue_dark));
		((Button) view).invalidate();
		return;
	}

	public void dellgoodsBuybutton(View view) {
		if(goods_add_shopping.isClickable()){
			Toast.makeText(this, "请先加入购物车，然后再购买", Toast.LENGTH_SHORT).show();
			return;
		}
		// 测试使用
		if (!defaultAddress) {
			startActivityByContent(this, MyAccoAddressIndexActivity.class);
		}
		GoodsBuyEntity entity = new GoodsBuyEntity();
		entity.setAddress("火星");//从地址栏返回
		entity.setEnterprise(goodsItem.getCompany_name());
		entity.setGoodsColor(checkedRadioText(radioGroup1.getCheckedRadioButtonId()));
		entity.setGoodsSize(checkedRadioText(radioGroup2.getCheckedRadioButtonId()));
		entity.setGoodsImage(goodsItem.getGoods_image());
		entity.setGoodsName(goodsItem.getGoods_name());
		entity.setGoodsNum(goods_num.getText().toString());
		entity.setGoodsPice(goodsItem.getGoods_price());
		entity.setPersonName("外星人");//从地址栏返回
		entity.setPhoneNum("88888");//从地址栏返回
		
		Intent intent = new Intent(this, GoodsBuyActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("entity", entity);
		intent.putExtras(bundle);
		startActivity(intent);
		return;
	}
	//单选框
	public String checkedRadioText(final int id){
		String text = "";
		switch (id) {
		case R.id.radio10:
			text = "白色";
			break;
		case R.id.radio11:
			text = "蓝色";
			break;
		case R.id.radio12:
			text = "灰色";
			break;
		case R.id.radio20:
			text = "小号";
			break;
		case R.id.radio21:
			text = "中号";
			break;
		case R.id.radio22:
			text = "大号";
			break;

		default:
			break;
		}
		return text;
	}
	

	public void startActivityByContent(Context context, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		intent.putExtra("startClass", "GoodsBuyActivity");
		startActivity(intent);
	}

}
