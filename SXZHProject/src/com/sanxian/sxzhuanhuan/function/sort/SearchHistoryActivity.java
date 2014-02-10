package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SearchHistoryActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-7 下午4:47:34
 * @version V1.0
 */
public class SearchHistoryActivity extends BaseActivity implements OnItemClickListener , OnItemSelectedListener{
	private Context context = null ;
	private ListView lvSearchHistory = null ;
	private Button btnClearHistory = null ;
	private Button btnCancel = null ;
	private SearchHistoryAdapter searchHistoryAdapter = null ;
	private ArrayList<String> sorts = null ;
	
	private Spinner spSort = null ;
	private EditText etFilter = null ;
	private String curSortItem = "" ;
	private int curSortID = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = SApplication.getInstance();
		
		setContentView(R.layout.search_history) ;
		
		Intent intent = getIntent() ;
		if(null != intent) {
			curSortItem = intent.getStringExtra("sortname") ;
			curSortID = Constant.Sort.getSortID(curSortItem);
			System.out.println("----search histroy activity:   " + curSortItem + "---" + curSortID ) ;
		}
		initWidget() ;
		
		sorts = getHistroyData() ;
		searchHistoryAdapter = new SearchHistoryAdapter(context, sorts ) ;
		lvSearchHistory.setAdapter(searchHistoryAdapter) ;
	}
	
	private void initWidget() {
		spSort = (Spinner) findViewById(R.id.search_histroy_head_spinner) ;
		etFilter = (EditText) findViewById(R.id.search_histroy_head_etfilter) ;
		lvSearchHistory = (ListView) findViewById(R.id.sort_search_history_listview) ;
		btnClearHistory = (Button) findViewById(R.id.sort_search_history_btn_clear) ;
		btnCancel = (Button) findViewById(R.id.search_histroy_head_cancle) ;
		
		
		spSort.setSelection(curSortID) ;
		lvSearchHistory.setOnItemClickListener(this) ;
		btnClearHistory.setOnClickListener(this) ;
		btnCancel.setOnClickListener(this) ;
		etFilter.setOnKeyListener(new OnKeyListener() {   
		    @Override
		    public boolean onKey(View v, int keyCode, KeyEvent event) {   
		        if (KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {   
		        	saveHistroyData() ;
		        	//搜索处理
		        	sorts = getHistroyData() ;
		        	searchHistoryAdapter = new SearchHistoryAdapter(context, sorts ) ;
					lvSearchHistory.setAdapter(searchHistoryAdapter) ;
		            return true;   
		        }
		        return false;   
		    }   
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
//		Util.toastInfo(context, sorts.get(position)) ;
		etFilter.setText(sorts.get(position)) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.sort_search_history_btn_clear:
				sorts = null ;
				//清除本地历史搜索的相关数据
				clearHistroyData() ;
				searchHistoryAdapter = new SearchHistoryAdapter(context, sorts ) ;
				lvSearchHistory.setAdapter(searchHistoryAdapter) ;
				break;
			case R.id.search_histroy_head_cancle :
				finish() ;
				break ;
				
		}
	}
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
            int position, long id) {
		// TODO Auto-generated method stub
		curSortItem = parent.getItemAtPosition(position).toString();
		curSortID = position;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 获取搜索的历史记录
	 * @return
	 */
	private ArrayList<String> getHistroyData() {
		SharedPreferences spf = getSharedPreferences(Constant.PRE_CONFIG_FILE, 0) ;
		String searchHistroy = spf.getString("search_histroy", "") ;
		ArrayList<String> results = new ArrayList<String>() ;
		if(!"".equals(searchHistroy)){
			searchHistroy = searchHistroy.substring(1) ;
			String[] datas = searchHistroy.split("/") ;
			for (String data : datas) {
				results.add(data) ;
			}
		}
		return results ;
	}
	/**
	 * 保存搜索的历史记录
	 */
	private void saveHistroyData() {
		SharedPreferences spf = getSharedPreferences(Constant.PRE_CONFIG_FILE, 0) ;
		SharedPreferences.Editor editor = spf.edit() ;
		String searchHistroy = spf.getString("search_histroy", "") ;
		if(!searchHistroy.endsWith("/")) {
			searchHistroy += "/" ;
		}
		if(searchHistroy.contains("/" + etFilter.getText().toString() + "/")){
			searchHistroy = searchHistroy.replace("/" + etFilter.getText().toString() + "/", "/") ;
		}
		editor.putString("search_histroy", "/" + etFilter.getText().toString() + searchHistroy ) ;
		editor.commit() ;
	}
	/**
	 * 清除搜索历史记录
	 */
	private void clearHistroyData() {
		SharedPreferences spf = getSharedPreferences(Constant.PRE_CONFIG_FILE, 0) ;
		SharedPreferences.Editor editor = spf.edit() ;
		editor.putString("search_histroy","") ;
		editor.commit() ;
	}
	
}
