package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.PopupMenu;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SortIndex.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: 分类首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-2 下午1:47:15
 * @version V1.0
 */
public class SortIndex extends BaseFragment implements OnItemClickListener,
		OnScrollListener, OnItemSelectedListener, OnClickListener {
	private TestAPI api = null;
	private Map<String, String> input = null;

	private Context context = null;
	/** 分类列表 */
	private ListView lvSort = null;
	/** 分类适配器 */
	private SortAdapter sortAdapter = null;

	/** 分类下拉选项 */
	private Button spSort = null;
	private ImageView ivSearch = null;
	private String curSortItem = "创意";

	private List<ICategory> sortParent = null;
	private List<ICategory> sortChild = null;
	private View postLeftView = null;
	private View postRightView = null;

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
		View view = inflater.inflate(R.layout.sort_index, container, false);

		initWidget(view);
		setListener();

		api.getMCAData("category", input, SortIndex.this,
				Constant.REQUESTCODE.SORT_PARENT_REQUEST);

		return view;
	}

	/**
	 * 初始化视图控件
	 * 
	 * @param view
	 */
	private void initWidget(View view) {
		api = new TestAPI();
		input = new HashMap<String, String>();

		spSort = (Button) view.findViewById(R.id.sort_index_head_spinner);
		ivSearch = (ImageView) view.findViewById(R.id.sort_index_head_search);
		lvSort = (ListView) view.findViewById(R.id.sort_index_lv);
	}

	/**
	 * 为相关控件添加事件监听
	 */
	private void setListener() {
		spSort.setOnClickListener(this);
		ivSearch.setOnClickListener(this);
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

		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case Constant.REQUESTCODE.SORT_PARENT_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				sortParent = JSONParser.getCategory(jsondata);

				for (int i = 0; i < sortParent.size(); i++) {
					input.put("parent_id", sortParent.get(i).getId());
					api.getMCAData("category", input, SortIndex.this,
							Constant.REQUESTCODE.SORT_CHILD_REQUEST);
				}
				lvSort.setAdapter(new SortAdapter(getActivity(),
						(ArrayList<ICategory>) sortParent));

			}
		}
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ICategory sortInfo = (ICategory) sortParent.get(position);

		UIHelper.showCategoryActivity(context, curSortItem,
				sortInfo.getTitle(), sortInfo.getId());
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
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		curSortItem = parent.getItemAtPosition(position).toString();
		Util.toastInfo(context, parent.getItemAtPosition(position).toString());
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
			UIHelper.showSearchHistoryActivity(context, curSortItem);
			break;
		case R.id.sort_index_head_spinner:
			onPopupButtonClick(v);
			break;
		}
	}
	
	@SuppressLint("NewApi")
	public void onPopupButtonClick(View button) {
        PopupMenu popup = new PopupMenu(getActivity(), button);
        popup.getMenuInflater().inflate(R.menu.popup_sort, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
            	curSortItem=(String) item.getTitle();
            	spSort.setText(curSortItem);
                return true;
            }
        });
        popup.show();
    }

}
