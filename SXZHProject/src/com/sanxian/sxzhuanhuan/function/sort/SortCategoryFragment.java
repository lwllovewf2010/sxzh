package com.sanxian.sxzhuanhuan.function.sort;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
/**   
 * @Title: SortCategoryFragment.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: 分类首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-11 下午3:47:15 
 * @version V1.0   
 */
public class SortCategoryFragment extends BaseFragment implements OnItemClickListener , OnScrollListener , OnClickListener {

	private Context context = null ;
	private CommonTitle conTitle = null ;
	private ListView lvLeft = null ;
	private ListView lvRight = null ;

	public SortCategoryFragment() {
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
		View view = inflater.inflate(R.layout.sort_category, container,false);
		
		initWidget(view) ;
		initData() ;
		
		return view;
	}
	
	private void initWidget(View view) {
		conTitle = (CommonTitle) view.findViewById(R.id.common_title) ;
		lvLeft = (ListView) view.findViewById(R.id.sort_category_ll_left_category_big_listview) ;
		lvRight = (ListView) view.findViewById(R.id.sort_category_ll_right_category_small_listview) ;
		
		conTitle.show(true, "返回", true, "科技", false, "") ;
		conTitle.btnLeft.setOnClickListener(this) ;
		lvLeft.setOnItemClickListener(this) ;
		lvLeft.setOnScrollListener(this) ;
		lvRight.setOnItemClickListener(this) ;
		lvRight.setOnScrollListener(this) ;
	}
	
	private void initData() {
		lvLeft.setAdapter(new ArrayAdapter<String>(context, R.layout.sort_filter_item ,getResources().getStringArray(R.array.array_industry_total))); 
		lvRight.setAdapter(new ArrayAdapter<String>(context , R.layout.sort_filter_item , getResources().getStringArray(R.array.array_industry_keji))) ;
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
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		
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

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.title_btn_left:
				break;
		}
	}

}
