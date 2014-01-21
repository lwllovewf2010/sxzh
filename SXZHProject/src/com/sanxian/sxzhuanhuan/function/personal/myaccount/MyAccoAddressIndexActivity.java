package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsBuyActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter.MyAccoAddressIndexAdapter;
/**
 * 
* @ClassName: MyAccoAddressIndexActivity  
* @Description: 个人地址首页列表
* @author honaf
* @date 2014-1-11 下午1:38:17
 */
/**
 * 
* @ClassName: MyAccoAddressIndexActivity  
* @Description: 个人地址首页列表
* @author honaf
* @date 2014-1-11 下午1:38:17
 */
public class MyAccoAddressIndexActivity extends BaseActivity {
	private ListView listView;
	private MyAccoAddressIndexAdapter adapter;
	private Button add_address_btn;//添加地址
	private Button title_right;//确定按钮
	private String cls;
	List<AddressInfo> list=new ArrayList<AddressInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_address_index);
		//add by yuanqk
		Intent intent = getIntent();
		//获取即将启动的类
		cls = intent.getStringExtra("startClass");
		Log.d("yuanqikai", "yuanqikai cls = "+cls);
		
		initView();
		initListener();
		getData();
		adapter=new MyAccoAddressIndexAdapter(this, list);
		listView.setAdapter(adapter);
	}
	public void getData(){
		for (int i = 0; i < 20; i++) {
			AddressInfo addressInfo=new AddressInfo("乔布斯"+i, "深圳", "坪山路", true);
			list.add(addressInfo);
		}
	}
	@Override
	public void initView() {
		super.initView();
		setTitle("个人地址管理");
		setRight("编辑");
		listView=(ListView)this.findViewById(R.id.listView);
		add_address_btn = (Button)this.findViewById(R.id.add_address_btn);
		title_right = (Button)this.findViewById(R.id.title_right);
		
	}

	@Override
	public void initListener() {
		super.initListener();
		button_left.setOnClickListener(this);
		add_address_btn.setOnClickListener(this);
		title_right.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.add_address_btn:
			Intent addressmanage = new Intent(MyAccoAddressIndexActivity.this,MyAccoAddressManageActivity.class);
			startActivity(addressmanage);
			break;
		case R.id.title_right:
			if ("GoodsBuyActivity".equals(cls)) {
				startActivityByActiviyName(this);
				finish();
			}
			break;
		default:
			break;
		}
	}
	
	//added by yuanqk
	public void startActivityByActiviyName(Context context){
		Intent intent = new Intent(context,GoodsBuyActivity.class);
		AddressInfo info = new AddressInfo("奥巴马", "美国", "白宫911", "911",true);
		Bundle bundle = new Bundle();
		bundle.putString("name", "赵钱孙");
		bundle.putString("district", "北京朝阳区");
		bundle.putString("street", "天安门广场01号");
		bundle.putString("phoneNum", "13899998888");
		intent.putExtra("bundle", bundle);
		context.startActivity(intent);
	}
}
