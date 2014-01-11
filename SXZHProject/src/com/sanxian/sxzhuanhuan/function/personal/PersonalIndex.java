package com.sanxian.sxzhuanhuan.function.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.function.personal.microworld.MicroWorldIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccountActivity;
import com.sanxian.sxzhuanhuan.function.personal.mycollection.MyCollectionIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myfriends.MyFriendsIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myorder.MyOrderIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.setting.SetIndexActiVity;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 个人中心首页 joe
 * @author Administrator
 *
 */
public class PersonalIndex extends BaseFragment implements OnClickListener{
	
	private RelativeLayout preson_data_layout; //个人基本信息
	private RelativeLayout tiny_bank_layout;  //微银行
	private LinearLayout my_project_layout;  //我的项目 
	private LinearLayout my_tribe_layout;  //我的部落
	private LinearLayout shopping_trolley_layout;  //我的购物车 
	private LinearLayout my_order_layout;  //我的订单
	private RelativeLayout supermarket_layout; //微超市
	private RelativeLayout wxin_layout;  //微信
	private RelativeLayout collection_layout;  //我的收藏 
	private RelativeLayout friend_layout;  //我的好友
	private RelativeLayout blogger_layout;  //微博
	private RelativeLayout world_layout;  //微世界
	

	private Context context;

	public PersonalIndex() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.presonal_index, container,false);
		initView(view);
		initListener();
		return view;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		 setTitle("个人中心");
	     displayLeft();
	     setRight("设置");
	     preson_data_layout = (RelativeLayout)view.findViewById(R.id.preson_data_layout);
	     tiny_bank_layout = (RelativeLayout)view.findViewById(R.id.tiny_bank_layout);
	     my_project_layout = (LinearLayout)view.findViewById(R.id.my_project_layout);
	     my_tribe_layout = (LinearLayout)view.findViewById(R.id.my_tribe_layout);
	     shopping_trolley_layout = (LinearLayout)view.findViewById(R.id.shopping_trolley_layout);
	     my_order_layout = (LinearLayout)view.findViewById(R.id.my_order_layout);
	     supermarket_layout = (RelativeLayout)view.findViewById(R.id.supermarket_layout);
	     wxin_layout = (RelativeLayout)view.findViewById(R.id.wxin_layout);
	     collection_layout = (RelativeLayout)view.findViewById(R.id.collection_layout);
	     friend_layout = (RelativeLayout)view.findViewById(R.id.friend_layout);
	     blogger_layout = (RelativeLayout)view.findViewById(R.id.blogger_layout);
	     world_layout = (RelativeLayout)view.findViewById(R.id.world_layout);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	public Context getContext() {
		return super.getContext();
	}
	
	/**
	 * 注册监听事件方法
	 */
	public void initListener(){
		preson_data_layout.setOnClickListener(this);
		tiny_bank_layout.setOnClickListener(this);
		my_project_layout.setOnClickListener(this);
		my_tribe_layout.setOnClickListener(this);
		shopping_trolley_layout.setOnClickListener(this);
		my_order_layout.setOnClickListener(this);
		supermarket_layout.setOnClickListener(this);
		wxin_layout.setOnClickListener(this);
		collection_layout.setOnClickListener(this);
		friend_layout.setOnClickListener(this);
		blogger_layout.setOnClickListener(this);
		world_layout.setOnClickListener(this);
		button_right.setOnClickListener(this);
		
	}
  
	/**
	 * 所有的单击事件处理方法
	 */
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.preson_data_layout:
			Intent presonintent = new Intent(context,MyAccountActivity.class);
			startActivity(presonintent);
			break;
		case R.id.tiny_bank_layout:
			Util.toastInfo(context, "微银行");
			break;
		case R.id.my_project_layout:
			Util.toastInfo(context, "我的项目");
			break;
		case R.id.my_tribe_layout:
			Util.toastInfo(context, "我的部落");
			break;
		case R.id.shopping_trolley_layout:
			Util.toastInfo(context, "我的购物车");
			break;
		case R.id.my_order_layout:
			Util.toastInfo(context, "我的订单");
			intent.setClass(context, MyOrderIndexActivity.class);
			startActivity(intent);
			break;
		case R.id.supermarket_layout:
			Util.toastInfo(context, "微超市");
			break;
		case R.id.wxin_layout:
			Util.toastInfo(context, "微信");
			break;
		case R.id.collection_layout:
			Util.toastInfo(context, "我的收藏");
			intent.setClass(context, MyCollectionIndexActivity.class);
			startActivity(intent);
			break;
		case R.id.friend_layout:
			Util.toastInfo(context, "我的好友");
			intent.setClass(context, MyFriendsIndexActivity.class);
			startActivity(intent);
			break;
		case R.id.blogger_layout:
			Util.toastInfo(context, "微博");
			break;
		case R.id.world_layout:
			Util.toastInfo(context, "微世界");
			intent.setClass(context, MicroWorldIndexActivity.class);
			startActivity(intent);
			break;
		case R.id.title_right:
			Intent setintent = new Intent(context,SetIndexActiVity.class);
			startActivity(setintent);
			break;
		default:
			break;
		}
	}

}
