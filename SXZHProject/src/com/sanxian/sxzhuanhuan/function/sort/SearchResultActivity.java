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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.SortInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SearchResultActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-7 下午5:47:34
 * @version V1.0
 */
public class SearchResultActivity extends BaseActivity implements OnItemClickListener{
	private Context context = null ;
	private ListView lvSearchResult = null ;
	private Button btnCancel = null ;
	private ImageButton ivbtnSearch = null ;
	private SearchResultAdapter searchResultAdapter = null ;
	private ArrayList<SortInfo> sorts = null ;
	private Spinner sortSpinner = null ;
	private EditText etFilter = null ;
	/**当前的分类项 */
	private String curSortContent = "" ;
	/**当前的分类项对应的ID */
	private int curSortID = 0 ;
	/**当前查找的文本 */
	private String curSearchContent = "" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = SApplication.getInstance();
		Intent intent = getIntent() ;
		if(null != intent ) {
			curSortContent = intent.getStringExtra("sort_parent") ;
			curSearchContent = intent.getStringExtra("sort_child") ;
			curSortID = Constant.Sort.getSortID(curSortContent) ;
		}
		
		setContentView(R.layout.search_result) ;
		
		initWidget() ;
		
		sorts = TestData.initSort() ;
		searchResultAdapter = new SearchResultAdapter(context, sorts ) ;
		lvSearchResult.setAdapter(searchResultAdapter) ;
	}
	
	private void initWidget() {
		lvSearchResult = (ListView) findViewById(R.id.sort_search_result_listview) ;
		btnCancel = (Button) findViewById(R.id.sort_head_btn_search_cancle) ;
		ivbtnSearch = (ImageButton) findViewById(R.id.sort_head_ivsearch) ;
		sortSpinner = (Spinner) findViewById(R.id.sort_head_spinner) ;
		etFilter = (EditText) findViewById(R.id.sort_head_etfilter) ;
		
		btnCancel.setVisibility(View.VISIBLE) ;
		ivbtnSearch.setVisibility(View.GONE) ;
		sortSpinner.setSelection(curSortID) ;
		etFilter.setText(curSearchContent) ;
		
		lvSearchResult.setOnItemClickListener(this) ;
		btnCancel.setOnClickListener(this) ;
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
			case R.id.sort_head_btn_search_cancle :
				finish() ;
				break ;
		}
	}
}
