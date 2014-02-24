package com.sanxian.sxzhuanhuan.function.discusshall;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussData;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallManagerAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ScUtil;

/**
 * 
 * @Title: DiscussManagerActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshallx
 * @Description: 
 * @author zhangfl@sanxian.com
 * @date 2014-2-24 上午10:47:44
 * @version V1.0
 */
public class DiscussManagerActivity extends BaseActivity implements
		OnClickListener , OnItemClickListener{
	
	private String projectID = "" ;
	private LayoutInflater mInflater = null;
	private ScUtil sc = null ;
	private View viewLeft = null;
	private View viewRight = null;
	private List<View> listViews; 
	private DiscussHallManagerAdapter dhmaAdapter = null ;
	private List<DiscussData> disData = null ;
	
	private CommonHeader conHeader = null ;
	private static int PAGE_FALG = 0 ;  
	private ListView lvLeft = null ;
	private ListView lvRight = null ;
	private LinearLayout llManagerLeft = null ;
	private TextView tvManagerLeftDisPubname = null ;
	private TextView tvManagerLeftDisCount = null ;
	private LinearLayout llManagerRight = null ;
	private TextView tvManagerRightCount = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.discuss_join_main);

		Intent intent = getIntent() ;
		if(null != intent) {
			projectID = intent.getStringExtra("project_id") ;
			System.out.println("projectID---" + projectID);
		}
		
		options = UIHelper.setOption() ;
		
		initContent();
	}

	private void initContent() {
		sc = new ScUtil(this);
		listViews = new ArrayList<View>();
		mInflater = getLayoutInflater();
		viewLeft = mInflater.inflate(R.layout.discuss_join_left, null);
		viewRight = mInflater.inflate(R.layout.discuss_join_right, null);

		initCommon() ;
		
		initRight(viewRight);

		listViews.add(viewLeft);
		listViews.add(viewRight);

		initLeft(viewLeft);
		sc.showSc("讨论中", "已结束", listViews , conHeader);
	}
	
	private void initCommon() {
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		conHeader.show(true, true , "返回", true, "主持中的讨论", false , "" , false) ;
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
	}
	
	private void initLeft(View view) {
		llManagerLeft = (LinearLayout) view.findViewById(R.id.discuss_manager_ll_left) ;
		tvManagerLeftDisPubname = (TextView) view.findViewById(R.id.discuss_manager_ll_tv_name) ;
		tvManagerLeftDisCount = (TextView) view.findViewById(R.id.discuss_manager_ll_tv_count) ;
		
		lvLeft = (ListView) view.findViewById(R.id.discuss_join_left_lv) ;
		getData() ;
		dhmaAdapter = new DiscussHallManagerAdapter(DiscussManagerActivity.this, disData , 1) ;
		lvLeft.setAdapter(dhmaAdapter) ;

	}

	private void initRight(View view) {
		llManagerRight = (LinearLayout) view.findViewById(R.id.discuss_manager_ll_right) ;
		tvManagerRightCount = (TextView) view.findViewById(R.id.discuss_manager_ll_tv_count);
		
		lvRight = (ListView) view.findViewById(R.id.discuss_join_right_lv) ;
		getData() ;
		dhmaAdapter = new DiscussHallManagerAdapter(DiscussManagerActivity.this, disData , 2) ;
		lvRight.setAdapter(dhmaAdapter) ;
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		System.out.println("flag----" + flag);
		switch (flag) {
		
		}
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				finish();
				break ;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	private void getData() {
		disData = new ArrayList<DiscussData>() ;
		DiscussData data = null ;
		for (int i = 0; i < 3; i++) {
			data = new DiscussData() ;
			data.setId("" + i ) ;
			data.setTitle("好呀！你妹"  + i ) ;
			data.setPubName("芳芳" + i) ;
			data.setPubTime("2014-02-2" + i) ;
			data.setSurplusTime("3小时" + i + "分") ;
			data.setEndTime("2014-03-2" + i) ; 
			data.setPubInfo("---") ;
			disData.add(data) ;
		}
		
	}

	
}
