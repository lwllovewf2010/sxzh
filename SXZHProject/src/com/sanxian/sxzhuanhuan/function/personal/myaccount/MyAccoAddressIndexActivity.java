package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
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
	private Button address_back_btn;
	private Button add_address_btn;//添加地址
	List<AddressInfo> list=new ArrayList<AddressInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_address_index);
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
		setRight("确定");
		listView=(ListView)this.findViewById(R.id.listView);
		address_back_btn = (Button)this.findViewById(R.id.address_back_btn);
		add_address_btn = (Button)this.findViewById(R.id.add_address_btn);
		
	}

	@Override
	public void initListener() {
		super.initListener();
		address_back_btn.setOnClickListener(this);
		add_address_btn.setOnClickListener(this);
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
		case R.id.address_back_btn:
			finish();
			break;
		case R.id.add_address_btn:
			Intent addressmanage = new Intent(MyAccoAddressIndexActivity.this,MyAccoAddressManageActivity.class);
			startActivity(addressmanage);
			break;
		default:
			break;
		}
	}
}
