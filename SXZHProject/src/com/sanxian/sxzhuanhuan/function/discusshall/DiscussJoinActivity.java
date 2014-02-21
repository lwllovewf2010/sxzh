package com.sanxian.sxzhuanhuan.function.discusshall;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussData;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallCreaterAdapter;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallJoinerAdapter;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallManagerAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ScUtil;

/**
 * 
 * @Title: DiscussJoinActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshallx
 * @Description: 
 * @author zhangfl@sanxian.com
 * @date 2014-2-21 上午10:47:44
 * @version V1.0
 */
public class DiscussJoinActivity extends BaseActivity implements
		OnClickListener , OnItemClickListener{
	
	private String projectID = "" ;
	private LayoutInflater mInflater = null;
	private ScUtil sc = null ;
	private View viewLeft = null;
	private View viewRight = null;
	private List<View> listViews; 
	private DiscussHallManagerAdapter dhmaAdapter = null ;
	private DiscussHallCreaterAdapter dhcaAdapter = null ; 
	private DiscussHallJoinerAdapter dhjaAdapter = null ;
	private List<DiscussData> disData = null ;
	
	private CommonHeader conHeader = null ;
	/** 1.项目描述  2.项目详情 3.项目成员  4.部落大厅  5.讨论大厅*/  
	private static int PAGE_FALG = 0 ;  
	private ListView lvLeft = null ;
	private ListView lvRight = null ;
	
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
		
		initLeft();
	}

	/**
	 * 初始化项目内容页
	 */
	private void initLeft() {
		sc = new ScUtil(this);
		listViews = new ArrayList<View>();
		mInflater = getLayoutInflater();
		viewLeft = mInflater.inflate(R.layout.discuss_join_left, null);
		viewRight = mInflater.inflate(R.layout.discuss_join_right, null);

		initRight(viewRight);

		listViews.add(viewLeft);
		listViews.add(viewRight);

		PAGE_FALG = 1 ;
		initWidget();
		sc.showSc("进行中", "已结束", listViews , conHeader);
		initData() ;
	}

	private void initRight(View view) {
		lvRight = (ListView) view.findViewById(R.id.discuss_join_right_lv) ;
//		sc.showSc("进行中", "已结束", listViews , conHeader);
		getData() ;
		dhjaAdapter = new DiscussHallJoinerAdapter(DiscussJoinActivity.this, disData , 2) ;
		lvRight.setAdapter(dhjaAdapter) ;
	}

	private void initWidget() {
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		conHeader.show(true, true , "返回", true, "项目介绍", false , "" , false) ;
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		
 		if( 1 == PAGE_FALG ) {
			lvLeft = (ListView) viewLeft.findViewById(R.id.discuss_join_left_lv) ;
		} else if (2 == PAGE_FALG ) {

		} 

	}
	
	private void initData() {
		if( 1 == PAGE_FALG ) {
			getData() ;
			dhjaAdapter = new DiscussHallJoinerAdapter(DiscussJoinActivity.this, disData , 1) ;
			lvLeft.setAdapter(dhjaAdapter) ;
		} else if (2 == PAGE_FALG ) {
			
		} 

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
	protected void onStart() {
		super.onStart() ;
	}
	
	@Override
	protected void onPause() {
		super.onPause() ;
	}
	
	@Override
	protected void onResume() {
		super.onResume() ;
	}
	
	@Override
	protected void onRestart() {
		super.onRestart() ;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy() ;
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
			disData.add(data) ;
		}
		
	}
	
}
