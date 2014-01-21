package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.dialog.BottomRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.function.homeindex.CreativeAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.ProductAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ProjectAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ProjectContentActivity;
import com.sanxian.sxzhuanhuan.function.sort.slidingmenu.BaseSlidingFragmentActivity;
import com.sanxian.sxzhuanhuan.function.sort.slidingmenu.SlidingMenu;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SortDetailActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: 分类详情
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:29:09
 * @version V1.0
 */
public class SortDetailActivity extends BaseSlidingFragmentActivity implements
		OnClickListener, OnItemClickListener, OnScrollListener,
		OnItemSelectedListener {
	private Context context = null;

	private TestAPI api = null;
	private Map<String, String> input = null;

	private ListView lvSortDetail = null;
	private SortDetailAdapter sortDetailAdapter = null;
	private ArrayList<CreativeInfo> creativeInfos = null;
	private ArrayList<ProjectInfo> projectInfos = null;
	private ArrayList<ProductInfo> productInfos = null;

	private Spinner sortSpinner = null;
	private Button creativeButton = null;
	private Button projectButton = null;
	private Button productButton = null;

	/** 分类框，弹出slidingMenu */
	private ImageView ivSortMenu = null;
	private SlidingMenu sm = null;
	private ListView lvSortLeft = null;
	private ListView lvSortRigth = null;
	private TextView mAboveTitle = null;
	private Button sortFilterZone = null;
	private Button sortFilterSort = null;
	private LinearLayout sortFilterAreaLL = null;
	private LinearLayout sortFilterSequenceLL = null;
	private ImageView imgQuery = null;
	private EditText etFilter = null;

	// 底部按钮
	private ImageView ivFooterBack = null;
	private ImageView ivFooterAdd = null;
	private ImageView ivFooterMore = null;
	private ImageView ivFooterNote = null;

	//左边滑动相关
	private static List<ICategory> sortParent = null;
	private static List<ICategory> sortChild = null;
	private static View postLeftView = null;
	private static View postRightView = null;
	private static ICategory curLeftSort = null;
	private static ICategory curRightSort = null;
	/** 当前的分类项 */
	private String curSortContent = "";
	/** 当前的分类项对应的ID */
	private int curSortID = 0;
	/** 当前查找的文本 */
	private String curSearchContent = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		context = SApplication.getInstance();
		Intent intent = getIntent();
		if (null != intent) {
			curSortContent = intent.getStringExtra("sort_parent");
			curSearchContent = intent.getStringExtra("sort_child");
			curSortID = Constant.Sort.getSortID(curSortContent);
		}

		initSlidingMenu();
		setContentView(R.layout.sort_detail);

		initWidget();
		searchClick();
		initControl();

		// initData() ;
		initListView();
		initRightListView();
		// initNav() ;
	}

	/**
	 * 初始化SlidingMenu
	 */
	private void initSlidingMenu() {
		// setBehindContentView(R.layout.behind_slidingmenu);
		sm = getSlidingMenu();

		// 设置滑动菜单的属性值
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		setBehindContentView(R.layout.behind_slidingmenu_sort);
		// 第右边菜单
		sm.setSecondaryMenu(R.layout.slidingmenu_sort_right);
		sm.setSecondaryShadowDrawable(R.drawable.slidingmenu_shadow);
	}

	private void initControl() {

		mAboveTitle = (TextView) findViewById(R.id.sort_detail_head_tvtitle);
		mAboveTitle.setText("分类");
		imgQuery = (ImageButton) findViewById(R.id.sort_detail_head_ivsearch);
		imgQuery.setOnClickListener(this);
		// lvTitle = (ListView) findViewById(R.id.behind_list_show);

		lvSortLeft = (ListView) findViewById(R.id.sort_category_ll_left_category_big_listview);
		lvSortRigth = (ListView) findViewById(R.id.sort_category_ll_right_category_small_listview);
	}

	/**
	 * 设置SlidingMenu的显示方式及其对应的数据
	 */
	private void initListView() {
		lvSortLeft.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (null == postLeftView && null != sortParent) {
					curLeftSort = sortParent.get(0);
					view.setBackgroundResource(R.drawable.back_behind_list);
					System.out.println("----------0000");
				} else {
					postLeftView.setBackgroundColor(Color.TRANSPARENT);
					System.out.println("--------" + position);
					curLeftSort = sortParent.get(position);
				}

				view.setBackgroundResource(R.drawable.back_behind_list);
				postLeftView = view;
				
				System.out.println(curLeftSort);
				getSortChild(curLeftSort.getId());
			}
		});

		lvSortRigth.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (null != postRightView) {
					postRightView.setBackgroundColor(Color.TRANSPARENT);
				}
				view.setBackgroundResource(R.drawable.back_behind_list);

				curRightSort = sortChild.get(position);

				Util.toastInfo(SortDetailActivity.this, curLeftSort.getTitle()
						+ "---" + curRightSort.getTitle());
				etFilter.setText(curRightSort.getTitle());
				postRightView = view;

				sm.toggle();
			}
		});

	}

	// added by yuanqk
	void initRightListView() {
		sortFilterZone = (Button) findViewById(R.id.sort_filter_btn_zone);
		sortFilterSort = (Button) findViewById(R.id.sort_filter_btn_sort);
		sortFilterAreaLL = (LinearLayout) findViewById(R.id.sort_filter_area);
		sortFilterSequenceLL = (LinearLayout) findViewById(R.id.sort_filter_sequence);
	}
	// added by yuanqk
	public void rightSlippingMenuEvent(View view) {
		switch (view.getId()) {
		case R.id.sort_filter_btn_zone:
			Log.d("sort_filter_btn_zone", "sort_filter_btn_zone");
			sortFilterZone.setBackgroundColor(getResources().getColor(R.color.lightcyan));
			sortFilterSort.setBackgroundResource(R.drawable.frame_button_n);
			sortFilterAreaLL.setVisibility(View.VISIBLE);
			sortFilterSequenceLL.setVisibility(View.GONE);
			break;
		case R.id.sort_filter_btn_sort:
			Log.d("sort_filter_btn_sort", "sort_filter_btn_sort");
			sortFilterSort.setBackgroundColor(getResources().getColor(R.color.lightcyan));
			sortFilterZone.setBackgroundResource(R.drawable.frame_button_n);
			sortFilterAreaLL.setVisibility(View.GONE);
			sortFilterSequenceLL.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}

	/**
	 * 初始化控件以及设置相关的监听器
	 */
	private void initWidget() {
		api = new TestAPI();
		input = new HashMap<String, String>();

		creativeButton = (Button) findViewById(R.id.sort_detail_btn_creative);
		projectButton = (Button) findViewById(R.id.sort_detail_btn_project);
		productButton = (Button) findViewById(R.id.sort_detail_btn_product);
		sortSpinner = (Spinner) findViewById(R.id.sort_head_spinner);
		etFilter = (EditText) findViewById(R.id.sort_head_etfilter);
		lvSortDetail = (ListView) findViewById(R.id.sort_lv_detail);
		ivSortMenu = (ImageView) findViewById(R.id.sort_detail_head_ivmenu);
		ivFooterBack = (ImageView) findViewById(R.id.sort_detail_footer_iv_back);
		ivFooterAdd = (ImageView) findViewById(R.id.sort_detail_footer_iv_add);
		ivFooterMore = (ImageView) findViewById(R.id.sort_detail_footer_iv_more);
		ivFooterNote = (ImageView) findViewById(R.id.sort_detail_footer_iv_note);

//		sortSpinner.setSelection(curSortID);
		etFilter.setText(curSearchContent);
//		sortSpinner.setOnItemSelectedListener(this);
		creativeButton.setEnabled(false);
		creativeButton.setOnClickListener(sortsBtnClick(creativeButton, 1));
		projectButton.setOnClickListener(sortsBtnClick(projectButton, 2));
		productButton.setOnClickListener(sortsBtnClick(productButton, 3));
		ivSortMenu.setOnClickListener(this);
		ivFooterBack.setOnClickListener(this);
		ivFooterAdd.setOnClickListener(this);
		ivFooterAdd.setVisibility(View.GONE);
		ivFooterMore.setOnClickListener(this);
		ivFooterMore.setVisibility(View.GONE);
		ivFooterNote.setOnClickListener(this);
		lvSortDetail.setOnScrollListener(this);
		lvSortDetail.setOnItemClickListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				R.layout.simple_spinner_item);
		String[] dataArr = getResources().getStringArray(R.array.sorts);
		for (int i = 0; i < dataArr.length; i++) {
			adapter.add(dataArr[i]);
		}
		adapter.setDropDownViewResource(R.layout.simple_spinner_item);
		sortSpinner.setAdapter(adapter);
		sortSpinner.setSelection(curSortID) ;
//		initData(Constant.Sort.SORT_CREATIVE);
		initData(curSortID + Constant.Sort.SORT_BASE) ;
		hideWidget() ;
	}
	/**
	 * 隐藏不该显示的控件
	 */
	private void hideWidget() {
		switch (curSortID + Constant.Sort.SORT_BASE) {
			case Constant.Sort.SORT_CREATIVE:
				productButton.setVisibility(View.GONE) ;
				projectButton.setVisibility(View.GONE) ;
				break;
			case Constant.Sort.SORT_PROJECT:
				creativeButton.setVisibility(View.GONE) ;
				productButton.setVisibility(View.GONE) ;
				break;
			case Constant.Sort.SORT_PRODUCT :
				creativeButton.setVisibility(View.GONE) ;
				projectButton.setVisibility(View.GONE) ;
				break ;
		}
	}

	/**
	 * 响应分类按钮事件
	 * 
	 * @param btn
	 *            事件源，及当前点击的按钮
	 * @param catalog
	 *            额外参数
	 * @return
	 */
	private View.OnClickListener sortsBtnClick(final Button btn,
			final int catalog) {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (btn == creativeButton) {
					sortSpinner.setSelection(0);
					creativeButton.setEnabled(false);
					initData(Constant.Sort.SORT_CREATIVE) ;
				} else {
					creativeButton.setEnabled(true);
				}

				if (btn == projectButton) {
					sortSpinner.setSelection(1);
					projectButton.setEnabled(false);
					initData(Constant.Sort.SORT_PROJECT) ;
				} else {
					projectButton.setEnabled(true);
				}

				if (btn == productButton) {
					sortSpinner.setSelection(2);
					productButton.setEnabled(false);
					initData(Constant.Sort.SORT_PRODUCT) ;
				} else {
					productButton.setEnabled(true);
				}
			}

		};
	}

	/**
	 * 设置相关分类的数据信息
	 * 
	 * @param flag
	 *            分类的类别ID
	 */
	private void initData(int flag) {
		switch (flag) {
			case Constant.Sort.SORT_CREATIVE:
				if(null == creativeInfos) {
					input.put("pmode", "2") ;
					input.put("pagesize", "10") ;
					api.getCPPData(input, SortDetailActivity.this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
				} else {
					lvSortDetail.setAdapter(new CreativeAdapter(SortDetailActivity.this, creativeInfos)) ;
				}
				break;
			case Constant.Sort.SORT_PROJECT :
				if(null == projectInfos) {
					input.put("pmode", "1") ;
					input.put("pagesize", "10") ;
					api.getCPPData(input, this, Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
				} else {
					lvSortDetail.setAdapter(new ProjectAdapter(SortDetailActivity.this, projectInfos) ) ;
				}
				break ;
			case Constant.Sort.SORT_PRODUCT :
				if(null == productInfos) {
					input.put("pmode", "4") ;
					input.put("pagesize", "5") ;
					api.getCPPData(input, this, Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
				} else {
					lvSortDetail.setAdapter( new ProductAdapter(SortDetailActivity.this, productInfos)) ;
				}
				break ;
			default:
				break;
		}
	}

	/**
	 * 提取公共方法，为任一界面进入分类详情页抑或是在分类详情页点击筛选时的界面、数据地显示
	 */
	private void searchClick() {
		switch (curSortID) {
		case 0:
			creativeButton.setEnabled(false);
			projectButton.setEnabled(true);
			productButton.setEnabled(true);
			// initData(Constant.Sort.SORT_CREATIVE) ;
			break;
		case 1:
			creativeButton.setEnabled(true);
			projectButton.setEnabled(false);
			productButton.setEnabled(true);
			// initData(Constant.Sort.SORT_PROJECT) ;
			break;
		case 2:
			creativeButton.setEnabled(true);
			projectButton.setEnabled(true);
			productButton.setEnabled(false);
			// initData(Constant.Sort.SORT_PRODUCT) ;
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.
	 * AbsListView, int, int, int)
	 */
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (curSortID) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		default:
			break;
		}
		// Util.toastInfo(context, info.getMetaName()) ;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sort_detail_head_ivmenu:
//			if (null == sortParent || sortParent.size() < 1)
			getSortParent();

			showMenu(); // show SlidingMenu

			break;
		case R.id.sort_detail_head_ivsearch:
			// yuanqk
			break;

		case R.id.sort_detail_footer_iv_back:
			finish();
			break;
		case R.id.sort_detail_footer_iv_note:
			showBottomMenuDialog();
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		curSortContent = parent.getItemAtPosition(position).toString();
		curSortID = position;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 大分类列表:{"action":"get_common_data","type":"category","mcr":0}
	 */
	private void getSortParent() {
		api.getMCAData("category", input, SortDetailActivity.this,
				Constant.REQUESTCODE.SORT_PARENT_REQUEST);
	}

	/**
	 * 小分类列表:{"action":"get_common_data","type":"category","mcr":0,
	 * "params":{"parent_id":"1"}}
	 */
	private void getSortChild(String parent_id) {
		input.put("parent_id", parent_id);
		api.getMCAData("category", input, SortDetailActivity.this,
				Constant.REQUESTCODE.SORT_CHILD_REQUEST);
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

				getSortChild(sortParent.get(0).getId()) ;   /////
				if (null == curLeftSort) {
					curLeftSort = sortParent.get(0) ;
				}
				lvSortLeft.setAdapter(new SimpleAdapter(this, TestData
						.initIndustryParent(sortParent),
						R.layout.behind_list_show, new String[] { "CONTENT",
								"COUNT" }, new int[] {
								R.id.textview_behind_title,
								R.id.textview_behind_count }));
				
			}
			break ;
		case Constant.REQUESTCODE.SORT_CHILD_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				sortChild = JSONParser.getCategory(jsondata);

				lvSortRigth.setAdapter(new SimpleAdapter(this, TestData
						.initIndustryParent(sortChild),
						R.layout.behind_list_show, new String[] { "CONTENT",
								"COUNT" }, new int[] {
								R.id.textview_behind_title,
								R.id.textview_behind_count }));
			}
			break ;
			
		case Constant.REQUESTCODE.CREATIVE_LIST_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				creativeInfos = (ArrayList<CreativeInfo>) JSONParser.getCreativeInfo(jsondata);
				lvSortDetail.setAdapter( new CreativeAdapter(SortDetailActivity.this, creativeInfos)) ;
			}
			break ;
		case Constant.REQUESTCODE.PROJECT_LIST_REQUEST : 
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				projectInfos = (ArrayList<ProjectInfo>) JSONParser.getProjectInfo(jsondata);
				
				lvSortDetail.setAdapter( new ProjectAdapter(SortDetailActivity.this, projectInfos)) ;
			}
			break ;
		case Constant.REQUESTCODE.PRODUCT_LIST_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				productInfos = (ArrayList<ProductInfo>) JSONParser.getProductInfo(jsondata);
				
				lvSortDetail.setAdapter( new ProductAdapter(SortDetailActivity.this, productInfos)) ;
			}
			break ;
			
			
		}
		
	}

	
	
	
	
	
	
	
	private void showBottomMenuDialog() {
		Intent intent = new Intent(SortDetailActivity.this,
				BottomRightOrLeftDialog.class);
		TopDialogInfo info = new TopDialogInfo();
		info.setDirection(DialogConstant.BRIGHT);
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.FOOTER_FAST_MENU);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == DialogConstant.FOOTER_FAST_MENU) {

			if (resultCode == DialogConstant.TOPDIALOG_ONE) {
				Util.toastInfo(SortDetailActivity.this, "首页");
			} else if (resultCode == DialogConstant.TOPDIALOG_TWO) {
				Util.toastInfo(SortDetailActivity.this, "需求大厅");
			} else if (resultCode == DialogConstant.TOPDIALOG_THREE) {
				Util.toastInfo(SortDetailActivity.this, "分类搜索");
			} else if (resultCode == DialogConstant.TOPDIALOG_FOUR) {
				Util.toastInfo(SortDetailActivity.this, "讨论大厅");
			} else if (resultCode == DialogConstant.TOPDIALOG_FIVE) {
				Util.toastInfo(SortDetailActivity.this, "个人中心");
			}

		}
	}


}
