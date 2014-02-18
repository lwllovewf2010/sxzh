package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
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
import com.sanxian.sxzhuanhuan.entity.City;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.entity.Province;
import com.sanxian.sxzhuanhuan.entity.SortListItemEntity;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.function.homeindex.CreativeAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.ProductAdapter;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ProjectAdapter;
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
	private CreativeInfo creinfo = null;
	private ProjectInfo projinfo = null;
	private ProductInfo prodinfo = null;

	private Button sortSpinner = null;
	private Button creativeButton = null;
	private Button projectButton = null;
	private Button productButton = null;

	/** 分类框，弹出slidingMenu */
	private TextView ivSortMenu = null;
	private SlidingMenu sm = null;
	private ListView lvSortLeft = null;
	private ListView lvSortRigth = null;
	private TextView mAboveTitle = null;
	private Button sortFilterZone = null;
	private Button sortFilterSort = null;
	private LinearLayout sortFilterAreaLL = null;
	private LinearLayout sortFilterSequenceLL = null;
	private ListView provinceList = null;
	private ListView citylist = null;
	// private EditText etFilter = null;

	// 底部按钮
	private ImageView ivFooterBack = null;
	private ImageView ivFooterAdd = null;
	private ImageView ivFooterMore = null;
	private ImageView ivFooterNote = null;

	// 左边滑动相关
	private static List<ICategory> sortParent = null;
	private static List<ICategory> sortChild = null;
	private static View postLeftView = null;
	private static View postRightView = null;
	private static ICategory curLeftSort = null;
	private static ICategory curRightSort = null;

	// yuanqk added
	private ArrayList<SortListItemEntity> sortList;
	private SortListAdapter sortListCityAdapt;
	private SortListAdapter sortListProvinceAdapt;
	private ArrayList<Province> provincesInfo = null;
	private ArrayList<City> citysInfo = null;
	private SortListAdapter.ViewHolder holder;
	public static SortListAdapter.ViewHolder cityHolder;
	public LinearLayout layout;

	/** 当前的分类项 */
	private String curSortContent = "";
	/** 当前的分类项对应的ID */
	private int curSortID = 0;
	private int provinceID = 0;
	private int cityID = 0;
	/** 当前查找的文本 */
	private String curSearchContent = "";
	/** 当前的分类ID---子项 */
	private String curSortChildID = "";

	private int ORDERBY = 1; // 1=发布日期倒序(默认), 2=价格倒序, 3=价格升序, 4=点击量, 5=参与人数最多

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		context = SApplication.getInstance();
		Intent intent = getIntent();
		if (null != intent) {
			curSortContent = intent.getStringExtra("sort_parent");
			curSearchContent = intent.getStringExtra("sort_child");
			curSortChildID = intent.getStringExtra("sort_child_id");
			curSortID = Constant.Sort.getSortID(curSortContent);
			System.out.println("----sort-detail-activity:" + curSortContent
					+ "---" + curSearchContent + "---" + curSortID + "---"
					+ curSortChildID);
		}

		initSlidingMenu();
		setContentView(R.layout.sort_detail);

		initWidget();
		searchClick();
		initControl();

		initListView();

		// yuanqk added
		initRightListView();
		initRightData();
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
				postLeftView = view;
				curLeftSort = sortParent.get(position);
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
				// etFilter.setText(curRightSort.getTitle());
				postRightView = view;

				System.out.println("curRightSort.getId()----->"
						+ curRightSort.getId());
				curSortChildID = curRightSort.getId();
				getData(curSortID, curRightSort.getId());
				sm.toggle();
			}
		});

	}

	private void getData(int curSortID, String curChildID) {
		if (null == creativeInfos)
			creativeInfos = new ArrayList<CreativeInfo>();
		if (null == projectInfos)
			projectInfos = new ArrayList<ProjectInfo>();
		if (null == productInfos)
			productInfos = new ArrayList<ProductInfo>();
		switch (curSortID) {
		case 0:
			// getCreative(curChildID) ;
			System.out.println("---" + curChildID);
			getCreativeMore(curChildID);
			break;
		case 1:
			// getProject(curChildID , "" ) ;
			getProjectMore("", curChildID);
			break;
		case 2:
			// getProduct(curChildID, "") ;
			getProductMore();
			break;
		}
	}

	// 初始化筛选界面
	void initRightListView() {
		sortFilterZone = (Button) findViewById(R.id.sort_filter_btn_zone);
		sortFilterSort = (Button) findViewById(R.id.sort_filter_btn_sort);
		sortFilterAreaLL = (LinearLayout) findViewById(R.id.sort_filter_area);
		sortFilterSequenceLL = (LinearLayout) findViewById(R.id.sort_filter_sequence);
		provinceList = (ListView) findViewById(R.id.sort_listView1);
		provinceList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("yuanqikai11", "yuanqikai11 arg2 = " + position);
				provinceList.setItemChecked(position, true);
				SortListAdapter.ViewHolder hold = (SortListAdapter.ViewHolder) view
						.getTag();
				initCityData(hold.provinceId);
			}

		});
		citylist = (ListView) findViewById(R.id.sort_listView2);
		citylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cityHolder = (SortListAdapter.ViewHolder) view.getTag();
				searchByCity(cityHolder);
				// 关闭第二菜单
				getSlidingMenu().toggle();
			}
		});
	}

	// 通过城市搜索
	public void searchByCity(SortListAdapter.ViewHolder holder) {
		if (api == null)
			api = new TestAPI();
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "0");
		map.put("category_id", curSortChildID);
		map.put("start", curSearchContent);
		map.put("province_id", holder.provinceId);
		Log.d("", "yuanqikai province_id = " + holder.provinceId);
		map.put("city_id", holder.cityId);

		Log.d("", "yuanqikai curSortIDcurSortID= " + curSortID);

		if (curSortID == 0) {
			api.operaCreativess("search", map, this,
					Constant.REQUESTCODE.CREATIVE_LIST_REQUEST);
		} else if (curSortID == 1) {
			api.operaProjects("search", map, this,
					Constant.REQUESTCODE.PROJECT_GET_ROW_REQUEST);
		} else if (curSortID == 2) {
			api.operaProduct("search", map, this,
					Constant.REQUESTCODE.PRODUCT_GET_ROW_REQUEST);
		}

	}

	public void searchByCase(SortListAdapter.ViewHolder holder, String orderby) {
		if (api == null)
			api = new TestAPI();
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "0");
		map.put("category_id", curSortChildID);
		map.put("start", curSearchContent);
		if (null != holder) {
			map.put("province_id", holder.provinceId);
			map.put("city_id", holder.cityId);
		}
		map.put("orderby", orderby);

		if (curSortID == 0) {
			api.operaCreativess("search", map, this,
					Constant.REQUESTCODE.CREATIVE_LIST_REQUEST);
		} else if (curSortID == 1) {
			api.operaProjects("search", map, this,
					Constant.REQUESTCODE.PROJECT_GET_ROW_REQUEST);
		} else if (curSortID == 2) {
			api.operaProduct("search", map, this,
					Constant.REQUESTCODE.PRODUCT_GET_ROW_REQUEST);
		}

	}

	// added by yuanqk
	// 获取城市列表
	void initRightData() {
		if (api == null)
			api = new TestAPI();
		api.getSortProvince(this, Constant.REQUESTCODE.SORT_PROVINCE_REQUEST);
	}

	void initCityData(String province_id) {
		if (api == null)
			api = new TestAPI();
		Map<String, String> map = new HashMap<String, String>();
		map.put("province_id", province_id);
		api.getSortCity(map, this, Constant.REQUESTCODE.SORT_CITY_REQUEST);
	}

	// yuanqk added
	public ArrayList<SortListItemEntity> provinceConvertToList(
			ArrayList<Province> province) {
		ArrayList<SortListItemEntity> sortlist = new ArrayList<SortListItemEntity>();
		SortListItemEntity item = null;

		for (int i = 0; i < province.size(); i++) {
			item = new SortListItemEntity();
			item.setName(((Province) province.get(i)).getRegion_name());
			item.setProvinceId(((Province) province.get(i)).getId());
			sortlist.add(item);
		}
		return sortlist;
	}

	// yuanqk added
	public ArrayList<SortListItemEntity> cityConvertToList(ArrayList<City> city) {
		ArrayList<SortListItemEntity> sortlist = new ArrayList<SortListItemEntity>();
		SortListItemEntity item = null;

		for (int i = 0; i < city.size(); i++) {
			item = new SortListItemEntity();
			item.setCityId(((City) city.get(i)).getId());
			item.setProvinceId(((City) city.get(i)).getProvince_id());
			item.setName(((City) city.get(i)).getRegion_name());
			sortlist.add(item);
		}
		return sortlist;
	}

	// added by yuanqk
	public void rightSlippingMenuEvent(View view) {
		switch (view.getId()) {
		case R.id.sort_filter_btn_zone:
			Log.d("sort_filter_btn_zone", "sort_filter_btn_zone");
			sortFilterZone.setTextColor(getResources().getColor(
					R.color.orangered));
			sortFilterSort.setTextColor(getResources().getColor(R.color.black));
			sortFilterAreaLL.setVisibility(View.VISIBLE);
			sortFilterSequenceLL.setVisibility(View.GONE);
			break;
		case R.id.sort_filter_btn_sort:
			Log.d("sort_filter_btn_sort", "sort_filter_btn_sort");
			sortFilterZone.setTextColor(getResources().getColor(R.color.black));
			sortFilterSort.setTextColor(getResources().getColor(
					R.color.orangered));
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
		sortSpinner = (Button) findViewById(R.id.sort_head_spinner);
		// etFilter = (EditText) findViewById(R.id.sort_head_etfilter);
		lvSortDetail = (ListView) findViewById(R.id.sort_lv_detail);
		ivSortMenu = (TextView) findViewById(R.id.sort_cat);
		ivFooterBack = (ImageView) findViewById(R.id.sort_detail_footer_iv_back);
		ivFooterAdd = (ImageView) findViewById(R.id.sort_detail_footer_iv_add);
		ivFooterMore = (ImageView) findViewById(R.id.sort_detail_footer_iv_more);
		ivFooterNote = (ImageView) findViewById(R.id.sort_detail_footer_iv_note);

		// etFilter.setText(curSearchContent);
		// etFilter.setOnClickListener(this) ; //跳到搜索页面
		creativeButton.setEnabled(false);
		// creativeButton.setOnClickListener(sortsBtnClick(creativeButton, 1));
		// projectButton.setOnClickListener(sortsBtnClick(projectButton, 2));
		// productButton.setOnClickListener(sortsBtnClick(productButton, 3));
		ivSortMenu.setOnClickListener(this);
		ivFooterBack.setOnClickListener(this);
		ivFooterAdd.setOnClickListener(this);
		ivFooterAdd.setVisibility(View.GONE);
		ivFooterMore.setOnClickListener(this);
		ivFooterMore.setVisibility(View.GONE);
		ivFooterNote.setOnClickListener(this);
		lvSortDetail.setOnScrollListener(this);
		lvSortDetail.setOnItemClickListener(this);

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
		// R.layout.simple_spinner_item);
		// String[] dataArr = getResources().getStringArray(R.array.sorts);
		// for (int i = 0; i < dataArr.length; i++) {
		// adapter.add(dataArr[i]);
		// }
		// adapter.setDropDownViewResource(R.layout.simple_spinner_item);
		// sortSpinner.setAdapter(adapter);
		sortSpinner.setText(sortString(curSortID));
		getData(curSortID, curSortChildID);
		hideWidget();
	}

	public String sortString(int sortID) {
		String temp = "创意";
		switch (sortID) {
		case 0:
			temp = "创意";
			break;
		case 1:
			temp = "项目";
			break;
		case 2:
			temp = "商品";
			break;

		default:
			break;
		}
		return temp;
	}

	public int sortStringToID(String sortString) {
		int ID = 0;
		if ("创意".equals(sortString.trim())) {
			ID = 0;
		} else if ("项目".equals(sortString.trim())) {
			ID = 1;
		} else if ("商品".equals(sortString.trim())) {
			ID = 2;
		}

		return ID;
	}

	/**
	 * 隐藏不该显示的控件
	 */
	private void hideWidget() {
		switch (curSortID + Constant.Sort.SORT_BASE) {
		case Constant.Sort.SORT_CREATIVE:
			productButton.setVisibility(View.GONE);
			projectButton.setVisibility(View.GONE);
			break;
		case Constant.Sort.SORT_PROJECT:
			creativeButton.setVisibility(View.GONE);
			productButton.setVisibility(View.GONE);
			break;
		case Constant.Sort.SORT_PRODUCT:
			creativeButton.setVisibility(View.GONE);
			projectButton.setVisibility(View.GONE);
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
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sort_head_etfilter:
			// UIHelper.showSearchResultActivity(SortDetailActivity.this,
			// curSortContent, curSearchContent) ;
			UIHelper.showSearchHistoryActivity(SortDetailActivity.this,
					curSortContent);
			break;

		case R.id.sort_cat:
			// if (null == sortParent || sortParent.size() < 1)
			getSortParent();
			showMenu(); // show SlidingMenu

			break;
		case R.id.sort_choose:
			// yuanqk
			getSlidingMenu().showSecondaryMenu();

			break;

		case R.id.sort_detail_footer_iv_back:
			finish();
			break;
		case R.id.sort_detail_footer_iv_note:
			showBottomMenuDialog();
			break;
		case R.id.sort_filter_sort_join_num:
			getSlidingMenu().toggle();
			searchByCase(cityHolder, "5");
			ORDERBY = 5;
			break;
		case R.id.sort_filter_sort_clicknum:
			getSlidingMenu().toggle();
			searchByCase(cityHolder, "4");
			ORDERBY = 4;
			break;
		case R.id.sort_filter_sort_pub_time:
			getSlidingMenu().toggle();
			searchByCase(cityHolder, "1");
			ORDERBY = 1;
			break;
		case R.id.sort_filter_sort_default:
			getSlidingMenu().toggle();
			searchByCase(cityHolder, "1");
			ORDERBY = 1;
			break;
		case R.id.sort_filter_ll_sequence_price_up:
			getSlidingMenu().toggle();
			searchByCase(cityHolder, "3");
			ORDERBY = 3;
			break;
		case R.id.sort_filter_ll_sequence_price_down:
			getSlidingMenu().toggle();
			searchByCase(cityHolder, "2");
			ORDERBY = 2;
			break;
		case R.id.sort_head_spinner:
			onPopupButtonClick(v);
			break;
		default:
			break;
		}
	}

	// R.layout.slidingmenu_sort_right
	public void onPopupSortChoose(View button) {
		layout = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.slidingmenu_sort_right, null);
		PopupWindow popupWindow = new PopupWindow(this);
		popupWindow.setWidth(this.getWindowManager().getDefaultDisplay()
				.getWidth());
		popupWindow.setHeight(this.getWindowManager().getDefaultDisplay()
				.getHeight());
		popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setContentView(layout);
		// showAsDropDown会把里面的view作为参照物，所以要那满屏幕parent
		popupWindow.showAsDropDown(layout.findViewById(R.id.sort_choose), 0, 0);
		// popupWindow.showAtLocation(findViewById(R.id.main),
		// Gravity.CENTER_HORIZONTAL, x, y);// 需要指定Gravity，默认情况是center.
	}

	@SuppressLint("NewApi")
	public void onPopupButtonClick(View button) {
		PopupMenu popup = new PopupMenu(this, button);
		popup.getMenuInflater().inflate(R.menu.popup_sort, popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				sortSpinner.setText(item.getTitle());
				curSortID = sortStringToID(item.getTitle() + "");
				return true;
			}
		});
		popup.show();
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
		input.clear(); // note
		api.getMCAData("category", input, SortDetailActivity.this,
				Constant.REQUESTCODE.SORT_PARENT_REQUEST);
	}

	/**
	 * 小分类列表:{"action":"get_common_data","type":"category","mcr":0,
	 * "params":{"parent_id":"1"}}
	 */
	private void getSortChild(String parent_id) {
		input.clear();
		input.put("parent_id", parent_id);
		api.getMCAData("category", input, SortDetailActivity.this,
				Constant.REQUESTCODE.SORT_CHILD_REQUEST);
	}

	/**
	 * 查找单个创意
	 * input={"action":"originality","type":"get_row","mcr":0,"params":{"id":
	 * 100}}
	 * 
	 * @param creative_id
	 */
	private void getCreative(String creative_id) {
		input.clear();
		input.put("id", creative_id);
		api.operaCreativess("get_row", input, SortDetailActivity.this,
				Constant.REQUESTCODE.CREATIVE_FETCH_ONE);
	}

	/**
	 * 查找多个创意input={"action":"originality","type":"search","mcr":0,"params":{
	 * "start":0,"pagesize":10,"category_id":1,"total_count":1,
	 * "title":"关键词1,关键词2"...}}
	 * 
	 * @param creative_id
	 */
	private void getCreativeMore(String category_id) {
		input.clear();
		input.put("start", "0");
		// input.put("pagesize", "100") ;
		input.put("category_id", category_id);
		input.put("orderby", "" + ORDERBY); // 1=发布日期倒序(默认), 4=点击量, 5=关注人数
		api.operaCreativess("search", input, SortDetailActivity.this,
				Constant.REQUESTCODE.CREATIVE_LIST_REQUEST);
	}

	/**
	 * 查找单个项目 {"action":"project","type":"get_row","mcr":0,"params":{"proj_id":
	 * 100, "open_id": "6_1210_309970"}}
	 */
	private void getProject(String proj_id, String open_id) {
		input.clear();
		input.put("proj_id", proj_id);
		input.put("open_id", UIHelper.getOpenID(context)); // "6_1210_309970" )
															// ; //获取open_id
		api.operaProjects("get_row", input, SortDetailActivity.this,
				Constant.REQUESTCODE.PROJECT_GET_ROW_REQUEST);
	}

	/**
	 * 查找多个项目 {"action":"project","type":"search","mcr":0,"params":{"start":0,
	 * "pagesize":10,"mode_id":1,"total_count":1 ....}}
	 */
	private void getProjectMore(String open_id, String category_id) {
		input.clear();
		input.put("start", "0");
		// input.put("pagesize", "100") ;
		input.put("category_id", category_id);
		input.put("orderby", "" + ORDERBY); // 1=发布日期倒序(默认), 4=点击量, 5=关注人数
		input.put("open_id", "6_1210_309970"); // 获取open_id
												// UIHelper.getOpenID(context)
		api.operaProjects("search", input, SortDetailActivity.this,
				Constant.REQUESTCODE.PROJECT_LIST_REQUEST);

	}

	/**
	 * 查找单个商品：input={"action":"goods","type":"get_row","mcr":0,"params":{
	 * "goods_id": 489, "open_id": "1_1177_469954"}}
	 */
	private void getProduct(String goods_id, String open_id) {
		input.clear();
		input.put("goods_id", goods_id);
		input.put("open_id", UIHelper.getOpenID(context)); // "6_1210_309970" )
															// ; //获取open_id
		api.operaProduct("get_row", input, SortDetailActivity.this,
				Constant.REQUESTCODE.PRODUCT_GET_ROW_REQUEST);
	}

	/**
	 * 查找多个商品：input={"action":"goods","type":"search","mcr":0,"params":{"start":
	 * 0,"pagesize":10,"mode_id":1,"total_count":1....}}
	 */
	private void getProductMore() {
		input.clear();
		input.put("start", "0");
		// input.put("pagesize", "100") ;
		input.put("open_id", "6_1210_309970"); // 获取open_id
												// UIHelper.getOpenID(context)
		api.operaProduct("get_row", input, SortDetailActivity.this,
				Constant.REQUESTCODE.PRODUCT_LIST_REQUEST);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case Constant.REQUESTCODE.SORT_PARENT_REQUEST: // 行业一级分类
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				sortParent = JSONParser.getCategory(jsondata);

				// System.out.println("---here:---" +
				// sortParent.get(0).toString());
				getSortChild(sortParent.get(0).getId()); // ///
				if (null == curLeftSort) {
					curLeftSort = sortParent.get(0);
				}
				lvSortLeft.setAdapter(new SimpleAdapter(this, TestData
						.initIndustryParent(sortParent),
						R.layout.behind_list_show, new String[] { "CONTENT",
								"COUNT" }, new int[] {
								R.id.textview_behind_title,
								R.id.textview_behind_count }));

			}
			break;
		case Constant.REQUESTCODE.SORT_CHILD_REQUEST: // 行业子分类
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
			break;

		case Constant.REQUESTCODE.CREATIVE_FETCH_ONE: // 查找单个创意
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				creinfo = JSONParser.parseCreativeInfo(jsondata);
				if (null != creativeInfos)
					creativeInfos.clear();
				if (null != creinfo && null != creinfo.getId())
					creativeInfos.add(creinfo);
				lvSortDetail.setAdapter(new CreativeAdapter(
						SortDetailActivity.this, creativeInfos));
			}
			break;

		case Constant.REQUESTCODE.PROJECT_GET_ROW_REQUEST: // 查找单个项目
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				projinfo = JSONParser.parseProjectInfo(jsondata);
				if (null != projectInfos)
					projectInfos.clear();
				if (null != projinfo.getId())
					projectInfos.add(projinfo);
				lvSortDetail.setAdapter(new ProjectAdapter(
						SortDetailActivity.this, projectInfos));
			}
			break;

		case Constant.REQUESTCODE.PRODUCT_GET_ROW_REQUEST: // 查找单个商品
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				prodinfo = JSONParser.parseProductInfo(jsondata);
				if (null != productInfos)
					productInfos.clear();
				if (null != prodinfo.getId())
					productInfos.add(prodinfo);
				lvSortDetail.setAdapter(new ProductAdapter(
						SortDetailActivity.this, productInfos));
			}
			break;

		case Constant.REQUESTCODE.CREATIVE_LIST_REQUEST: // 获取所有的创意
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				if (null != creativeInfos)
					creativeInfos.clear();
				creativeInfos = (ArrayList<CreativeInfo>) JSONParser
						.getCreativeInfo(jsondata);
				lvSortDetail.setAdapter(new CreativeAdapter(
						SortDetailActivity.this, creativeInfos));
			}
			break;
		case Constant.REQUESTCODE.PROJECT_LIST_REQUEST: // 获取所有的项目
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				if (null != projectInfos)
					projectInfos.clear();
				projectInfos = (ArrayList<ProjectInfo>) JSONParser
						.getProjectInfo(jsondata);
				lvSortDetail.setAdapter(new ProjectAdapter(
						SortDetailActivity.this, projectInfos));
			}
			break;
		case Constant.REQUESTCODE.PRODUCT_LIST_REQUEST: // 获取所有的商品
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				if (null != productInfos)
					productInfos.clear();
				productInfos = (ArrayList<ProductInfo>) JSONParser
						.getProductInfo(jsondata);
				lvSortDetail.setAdapter(new ProductAdapter(
						SortDetailActivity.this, productInfos));
			}
			break;
		case Constant.REQUESTCODE.SORT_PROVINCE_REQUEST: // 获取省份
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				provincesInfo = (ArrayList<Province>) JSONParser
						.getProvinceInfo(jsondata);
				sortListProvinceAdapt = new SortListAdapter(this,
						provinceConvertToList(provincesInfo));
				provinceList.setAdapter(sortListProvinceAdapt);
			}
			break;
		case Constant.REQUESTCODE.SORT_CITY_REQUEST: // 获取城市
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				citysInfo = (ArrayList<City>) JSONParser.getCityInfo(jsondata);
				sortListCityAdapt = new SortListAdapter(this,
						cityConvertToList(citysInfo));
				citylist.setAdapter(sortListCityAdapt);
			}
			break;
		default:
			break;

		}

	}

	/**
	 * 显示底部快捷菜单
	 */
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

	/**
	 * 响应分类按钮事件
	 * 
	 * @param btn
	 *            事件源，及当前点击的按钮
	 * @param catalog
	 *            额外参数
	 * @return
	 */
	// private View.OnClickListener sortsBtnClick(final Button btn,
	// final int catalog) {
	// return new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// if (btn == creativeButton) {
	// sortSpinner.setSelection(0);
	// creativeButton.setEnabled(false);
	// initData(Constant.Sort.SORT_CREATIVE) ;
	// } else {
	// creativeButton.setEnabled(true);
	// }
	//
	// if (btn == projectButton) {
	// sortSpinner.setSelection(1);
	// projectButton.setEnabled(false);
	// initData(Constant.Sort.SORT_PROJECT) ;
	// } else {
	// projectButton.setEnabled(true);
	// }
	//
	// if (btn == productButton) {
	// sortSpinner.setSelection(2);
	// productButton.setEnabled(false);
	// initData(Constant.Sort.SORT_PRODUCT) ;
	// } else {
	// productButton.setEnabled(true);
	// }
	// }
	//
	// };
	// }

	/**
	 * 设置相关分类的数据信息
	 * 
	 * @param flag
	 *            分类的类别ID
	 */
	// private void initData(int flag) {
	// switch (flag) {
	// case Constant.Sort.SORT_CREATIVE:
	// if(null == creativeInfos) {
	// input.put("pmode", "2") ;
	// input.put("pagesize", "10") ;
	// api.getCPPData(input, SortDetailActivity.this,
	// Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
	// } else {
	// lvSortDetail.setAdapter(new CreativeAdapter(SortDetailActivity.this,
	// creativeInfos)) ;
	// }
	// break;
	// case Constant.Sort.SORT_PROJECT :
	// if(null == projectInfos) {
	// input.put("pmode", "1") ;
	// input.put("pagesize", "10") ;
	// api.getCPPData(input, this, Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
	// } else {
	// lvSortDetail.setAdapter(new ProjectAdapter(SortDetailActivity.this,
	// projectInfos) ) ;
	// }
	// break ;
	// case Constant.Sort.SORT_PRODUCT :
	// if(null == productInfos) {
	// input.put("pmode", "4") ;
	// input.put("pagesize", "5") ;
	// api.getCPPData(input, this, Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
	// } else {
	// lvSortDetail.setAdapter( new ProductAdapter(SortDetailActivity.this,
	// productInfos)) ;
	// }
	// break ;
	// default:
	// break;
	// }
	// }

}
