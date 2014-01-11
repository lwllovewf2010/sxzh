package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.SortInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SearchHistoryActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-7 下午4:47:34
 * @version V1.0
 */
public class SearchHistoryActivity extends BaseActivity implements OnItemClickListener{
	private Context context = null ;
	private ListView lvSearchHistory = null ;
	private Button btnClearHistory = null ;
	private Button btnCancel = null ;
	private ImageButton ivbtnSearch = null ;
	private SearchHistoryAdapter searchHistoryAdapter = null ;
	private ArrayList<SortInfo> sorts = null ;
	
	
	//底部按钮
	private ImageView ivFooterBack = null ;
	private ImageView ivFooterAdd = null ;
	private ImageView ivFooterMore = null ;
	private ImageView ivFooterNote = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = SApplication.getInstance();
		
		setContentView(R.layout.search_history) ;
		
		initWidget() ;
		
		sorts = TestData.initSort() ;
		searchHistoryAdapter = new SearchHistoryAdapter(context, sorts ) ;
		lvSearchHistory.setAdapter(searchHistoryAdapter) ;
	}
	
	private void initWidget() {
		lvSearchHistory = (ListView) findViewById(R.id.sort_search_history_listview) ;
		btnClearHistory = (Button) findViewById(R.id.sort_search_history_btn_clear) ;
		btnCancel = (Button) findViewById(R.id.sort_head_btn_search_cancle) ;
		ivbtnSearch = (ImageButton) findViewById(R.id.sort_head_ivsearch) ;
		ivFooterBack = (ImageView) findViewById(R.id.sort_detail_footer_iv_back) ;
		ivFooterAdd = (ImageView) findViewById(R.id.sort_detail_footer_iv_add) ;
		ivFooterMore = (ImageView) findViewById(R.id.sort_detail_footer_iv_more) ;
		ivFooterNote = (ImageView) findViewById(R.id.sort_detail_footer_iv_note) ;
		
		btnCancel.setVisibility(View.VISIBLE) ;
		ivbtnSearch.setVisibility(View.GONE) ;
		
		lvSearchHistory.setOnItemClickListener(this) ;
		btnClearHistory.setOnClickListener(this) ;
		btnCancel.setOnClickListener(this) ;
		ivFooterBack.setOnClickListener(this) ;
		ivFooterAdd.setOnClickListener(this) ;
		ivFooterMore.setOnClickListener(this) ;
		ivFooterNote.setOnClickListener(this) ;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		Util.toastInfo(context, sorts.get(position).getSortName()) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.sort_search_history_btn_clear:
				sorts = null ;
				searchHistoryAdapter = new SearchHistoryAdapter(context, sorts ) ;
				lvSearchHistory.setAdapter(searchHistoryAdapter) ;
				break;
			case R.id.sort_head_btn_search_cancle :
				finish() ;
				break ;
				
			case R.id.sort_detail_footer_iv_back :
				Util.toastInfo(context, "back") ;
				finish() ;
				break ;
			case R.id.sort_detail_footer_iv_add :
				Util.toastInfo(context, "add") ;
				break ;
			case R.id.sort_detail_footer_iv_more :
				Util.toastInfo(context, "more") ;
				break ;
			case R.id.sort_detail_footer_iv_note :
				Util.toastInfo(context, "note") ;
				break ;
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (resultCode) {
			case 1:
				Util.toastInfo(context, "SearchHistoryActivity取消") ;
				break;
			case 2 :
				Util.toastInfo(context, "SearchHistoryActivity确定") ;
				break ;
			default:
				break;
		}
	}
}
