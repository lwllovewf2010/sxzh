package com.sanxian.sxzhuanhuan.function.sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SortCategoryActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-11 下午4:08:20
 * @version V1.0
 */
public class SortCategoryActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener, OnScrollListener {
	private CommonTitle conTitle = null;
	private ListView lvLeftSort = null;
	private ListView lvRightSort = null;
	
	private String categoryName = "" ;
	private String parent_id = "" ;
	private List<ICategory> sortParent = null ;
	private List<ICategory> sortChild = null ;
	private View postLeftView = null ;
	private View postRightView = null ;
	private ICategory curLeftSort = null ;
	private ICategory curRightSort = null ;
	
	private TestAPI api = null;
	private Map<String , String> input = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sort_category);
		
		Intent intent = getIntent() ;
		if(null != intent) {
			categoryName = intent.getStringExtra("category_name") ;
			parent_id = intent.getStringExtra("parent_id") ;
		}

		initWidget();
//		initData();
		
		getSortParent() ;
	}

	private void initWidget() {
		api = new TestAPI();
		input = new HashMap<String, String>();
		
		conTitle = (CommonTitle) findViewById(R.id.common_title);
		lvLeftSort = (ListView) findViewById(R.id.sort_category_ll_left_category_big_listview);
		lvRightSort = (ListView) findViewById(R.id.sort_category_ll_right_category_small_listview);

		conTitle.show(true, "返回", true, categoryName , false, "");
		conTitle.btnLeft.setOnClickListener(this);
		lvLeftSort.setOnItemClickListener(this);
		lvLeftSort.setOnScrollListener(this);
		lvRightSort.setOnItemClickListener(this);
		lvRightSort.setOnScrollListener(this);
	}

	private void initData() {

		lvLeftSort.setAdapter(new SimpleAdapter(this, TestData
				.initIndustry(getResources().getStringArray(
						R.array.array_industry_total)),
				R.layout.sort_category_item, new String[] { "CONTENT" },
				new int[] { R.id.sort_category_item_content }));
		lvRightSort.setAdapter(new SimpleAdapter(this, TestData
				.initIndustry(getResources().getStringArray(
						R.array.array_industry_keji)),
				R.layout.sort_category_item, new String[] { "CONTENT" },
				new int[] { R.id.sort_category_item_content }));
	}

	private TextView tvSelect = null ;
	private TextView tvPreSelect = null ;
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		tvSelect = ((TextView)((LinearLayout)view).getChildAt(1)) ;
		if(parent.getId() == lvLeftSort.getId() ) {
			if(null == tvPreSelect)
				tvPreSelect = tvSelect ;
			else 
				tvPreSelect.setText(categoryName) ;
			if(null == postLeftView && null != sortParent ) {
        		curLeftSort = sortParent.get(0) ;
        		view.setBackgroundResource(R.drawable.back_behind_list);
            } else {
            	postLeftView.setBackgroundColor(Color.TRANSPARENT);
            	curLeftSort = sortParent.get(position) ;
            }
            view.setBackgroundResource(R.drawable.back_behind_list);
            postLeftView = view ;
            getSortChild(curLeftSort.getId()) ;
            
//			curLeftSort = sortParent.get(position) ;
			categoryName = tvSelect.getText().toString() ;
			conTitle.tvTitle.setText(categoryName) ;
			tvSelect.setText(categoryName + "                    >") ;
			tvPreSelect = tvSelect ;
//			getSortChild(curLeftSort.getId()) ;
		}
		if(parent.getId() == lvRightSort.getId()) {
			UIHelper.showSortDetailActivity(this, categoryName, tvSelect.getText().toString()) ;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_btn_left:
			finish() ;
			break;
		}
	}

	/**
	 * 大分类列表:{"action":"get_common_data","type":"category","mcr":0}
	 */
	private void getSortParent() {
		api.getMCAData("category", input, SortCategoryActivity.this, Constant.REQUESTCODE.SORT_PARENT_REQUEST) ;
	}
	
	/**
	 * 小分类列表:{"action":"get_common_data","type":"category","mcr":0, "params":{"parent_id":"1"}}
	 */
	private void getSortChild(String parent_id) {
		input.put("parent_id" , parent_id) ;
		api.getMCAData("category", input, SortCategoryActivity.this, Constant.REQUESTCODE.SORT_CHILD_REQUEST) ;
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
			case Constant.REQUESTCODE.SORT_PARENT_REQUEST:
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
					sortParent = JSONParser.getCategory(jsondata) ;
					
					getSortChild(parent_id) ;
					
					lvLeftSort.setAdapter(new SimpleAdapter(this, TestData
							.initIndustry(sortParent),
							R.layout.sort_category_item, new String[] { "CONTENT" },
							new int[] { R.id.sort_category_item_content }));
				}
			case Constant.REQUESTCODE.SORT_CHILD_REQUEST:
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
					sortChild = JSONParser.getCategory(jsondata) ;
					
					lvRightSort.setAdapter(new SimpleAdapter(this, TestData
							.initIndustry(sortChild),
							R.layout.sort_category_item, new String[] { "CONTENT" },
							new int[] { R.id.sort_category_item_content }));
				}
		}
	}
	
}
