package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.SortInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;
import com.sanxian.sxzhuanhuan.function.homeindex.HomeIndex;
import com.sanxian.sxzhuanhuan.util.Util;
/**   
 * @Title: SortIndex.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: 分类首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-2 下午1:47:15 
 * @version V1.0   
 */
public class SortIndex extends BaseFragment implements OnItemClickListener , OnScrollListener , 
	OnItemSelectedListener , OnClickListener{
	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	private Context context = null ;
	/**分类列表 */
	private ListView lvSort = null ;
	/**分类适配器 */
	private SortAdapter sortAdapter = null ;
	
	/**分类下拉选项 */
	private Spinner spSort = null ;
	private ImageView ivSearch = null ;
	private String curSortItem = "创意" ;
	
	private List<ICategory> sortParent = null ;
	private List<ICategory> sortChild = null ;
	private View postLeftView = null ;
	private View postRightView = null ;
	
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
//		sorts = TestData.initSort() ;
//		sortAdapter = new SortAdapter(context, sorts) ;
//		lvSort.setAdapter(sortAdapter);
		
		api.getMCAData("category", input, SortIndex.this, Constant.REQUESTCODE.SORT_PARENT_REQUEST) ;
		
		return view;
	}
	
	/**
	 * 初始化视图控件
	 * @param view
	 */
	private void initWidget(View view) {
		api = new TestAPI();
		input = new HashMap<String, String>();
		
		spSort = (Spinner) view.findViewById(R.id.sort_index_head_spinner) ;
		ivSearch = (ImageView) view.findViewById(R.id.sort_index_head_search) ;
		lvSort = (ListView) view.findViewById(R.id.sort_index_lv) ;
	}
	/**
	 * 为相关控件添加事件监听
	 */
	private void setListener() {
		spSort.setOnItemSelectedListener(this) ;
		ivSearch.setOnClickListener(this) ;
		lvSort.setOnScrollListener(this);
		lvSort.setOnItemClickListener(this);
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
	}

//	private List<Map<String , List<ICategory>>> sortChildsss = null ;
//	private Map<String , List<ICategory>> mapSort = null ;
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
					
//					sortChildsss = new ArrayList<Map<String,List<ICategory>>>() ;
					for(int i = 0 ; i < sortParent.size() ; i++) {
						input.put("parent_id" , sortParent.get(i).getId()) ;
						api.getMCAData("category", input, SortIndex.this, Constant.REQUESTCODE.SORT_CHILD_REQUEST) ;
					}
					lvSort.setAdapter(new SortAdapter(getActivity(), (ArrayList<ICategory>) sortParent)) ;
					
				}
//			case Constant.REQUESTCODE.SORT_CHILD_REQUEST:
//				if (param.length > 0 && param[1] != null
//						&& param[1] instanceof String) {
//					String jsondata = param[1].toString();
//					sortChild = JSONParser.getCategory(jsondata) ;
//					
//					for(int i = 0 ; i < sortParent.size() ; i++) {
//						mapSort = new HashMap<String, List<ICategory>>() ;
//						mapSort.put(sortParent.get(i).getId() , sortChild) ;
//						System.out.println("parent_id----" + sortParent.get(i).getId()); 
//						System.out.println("------------" + sortChild);
//						sortChildsss.add(mapSort) ;
//					}
//				}
		}
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		ICategory sortInfo = (ICategory) sortParent.get(position) ;
		Util.toastInfo(context, sortInfo.getTitle()) ;
		
//		UIHelper.showLoginActivity(context) ;
//		UIHelper.showTestApiActivity(getActivity()) ;
//		UIHelper.showRealAuthActivity(context) ;
		
		UIHelper.showCategoryActivity(context , sortInfo.getTitle() , sortInfo.getId()) ;
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

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
            int position, long id) {
		// TODO Auto-generated method stub
		curSortItem = parent.getItemAtPosition(position).toString() ;
		Util.toastInfo(context, parent.getItemAtPosition(position).toString()) ;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.sort_index_head_search:
				System.out.println("search");
				UIHelper.showSortDetailActivity(getActivity(), curSortItem, "") ;
				break;
		}
	}

}
