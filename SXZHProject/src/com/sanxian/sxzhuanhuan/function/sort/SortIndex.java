package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.SortInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.util.Util;
/**   
 * @Title: SortIndex.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: 分类首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-2 下午1:47:15 
 * @version V1.0   
 */
public class SortIndex extends BaseFragment implements OnClickListener , OnItemClickListener , OnScrollListener , 
	OnItemSelectedListener , OnFocusChangeListener{

	private Context context = null ;
	/**分类列表 */
	private ListView lvSort = null ;
	/**分类适配器 */
	private SortAdapter sortAdapter = null ;
	/**分类数据信息集合 */
	private ArrayList<SortInfo> sorts = null ;
	
	/**分类下拉选项 */
	private Spinner spSort = null ;
    /**过滤器 */
	private EditText etFilter = null ;
	/**搜索按钮 */
	private ImageButton ibSearch = null ;
	/**取消搜索 */
	private Button btnSearchCancle = null ;
	/**当前的分类项 */
	private String curSortContent = "" ;
	/**当前查找的文本 */
	private String curSearchContent = "" ;

	public SortIndex() {
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
		View view = inflater.inflate(R.layout.sort_index, container,false);
		
		initWidget(view) ;
		setListener() ;
		sorts = TestData.initSort() ;
		sortAdapter = new SortAdapter(context, sorts) ;
		lvSort.setAdapter(sortAdapter);
		
		return view;
	}
	
	/**
	 * 初始化视图控件
	 * @param view
	 */
	private void initWidget(View view) {
		spSort = (Spinner) view.findViewById(R.id.sort_head_spinner) ;
		etFilter = (EditText) view.findViewById(R.id.sort_head_etfilter) ;
		ibSearch = (ImageButton) view.findViewById(R.id.sort_head_ivsearch) ;
		btnSearchCancle = (Button) view.findViewById(R.id.sort_head_btn_search_cancle) ;
		lvSort = (ListView) view.findViewById(R.id.sort_lv) ;
	}
	/**
	 * 为相关控件添加事件监听
	 */
	private void setListener() {
		spSort.setOnItemSelectedListener(this) ;
		etFilter.setOnFocusChangeListener(this) ;
		ibSearch.setOnClickListener(this) ;
		btnSearchCancle.setOnClickListener(this) ;
		lvSort.setOnScrollListener(this);
		lvSort.setOnItemClickListener(this);
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.sort_head_ivsearch:
//				etFilter.clearFocus() ;
//				curSearchContent = etFilter.getText().toString().trim() ;
//				if("".equals(curSearchContent)) {
//					UIHelper.showSearchHistoryActivity(context) ;
//				} else {
//					Util.toastInfo(context, "search " + curSearchContent) ;
//					UIHelper.showSearchResultActivity(context, curSortContent, curSearchContent) ;
//				}
				
				UIHelper.showLoginActivity(context) ;
				break;
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		SortInfo sortInfo = (SortInfo) sorts.get(position) ;
		UIHelper.showSortDetailActivity(context, curSortContent, sortInfo.getSortName()) ;
	}

	/* (non-Javadoc)
	 * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.AbsListView, int, int, int)
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android.widget.AbsListView, int)
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
            int position, long id) {
		// TODO Auto-generated method stub
		curSortContent = parent.getItemAtPosition(position).toString();
//		Util.toastInfo(context, curSortContent) ;
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
	 */
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnFocusChangeListener#onFocusChange(android.view.View, boolean)
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
	}

}
