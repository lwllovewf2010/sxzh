package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.MetaData;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.function.sort.slidingmenu.BaseSlidingFragmentActivity;
import com.sanxian.sxzhuanhuan.function.sort.slidingmenu.NavigationModel;
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
public class SortDetailActivity extends BaseSlidingFragmentActivity implements OnClickListener , OnItemClickListener , OnScrollListener , OnItemSelectedListener{
	private Context context = null ;
	/**分类详细列表 */
	private ListView lvSortDetail = null ;
	/**分类详情适配 */
	private SortDetailAdapter sortDetailAdapter = null ;
	/**创意数据集合 */
	private ArrayList<CreativeInfo> creativeInfos = null ;
	/**项目数据集合 */
	private ArrayList<ProjectInfo> projectInfos = null ;
	/**商品数据集合 */
	private ArrayList<ProductInfo> productInfos = null ;
	
	/**分类列表 */
	private Spinner sortSpinner = null ;
	/**创意 */
	private Button creativeButton = null ;
	/**项目 */
	private Button projectButton = null ;
	/**商品 */
	private Button productButton = null ;
	
	/**分类框，弹出slidingMenu */
	private ImageView ivSortMenu = null ;
	private SlidingMenu sm = null ;
	private String LIST_TEXT = "txt" ;
	private String LIST_IMAGEVIEW = "img" ;
	private SimpleAdapter lvAdapter = null ;
	private int mTag = 0;
	private ListView lvTitle = null ;
	private List<NavigationModel> navs = null ;
	private TextView mAboveTitle = null ;
	private String current_page = "" ;
	private ImageView imgQuery = null ;
	private EditText etFilter = null ;
	
	//底部按钮
	private ImageView ivFooterBack = null ;
	private ImageView ivFooterAdd = null ;
	private ImageView ivFooterMore = null ;
	private ImageView ivFooterNote = null ;
	
	/**当前的分类项 */
	private String curSortContent = "" ;
	/**当前的分类项对应的ID */
	private int curSortID = 0 ;
	/**当前查找的文本 */
	private String curSearchContent = "" ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		
		initSlidingMenu();
		setContentView(R.layout.sort_detail) ;
		
		initWidget() ;
		searchClick() ;
		initControl() ;
		initListView() ;
		initNav() ;
	}
	
	/**
	 * 初始化SlidingMenu
	 */
	private void initSlidingMenu() {
        setBehindContentView(R.layout.behind_slidingmenu);
        // customize the SlidingMenu
        sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
        //sm.setShadowWidth(20);
        sm.setBehindScrollScale(0);
    }
	
	private void initControl() {

		mAboveTitle = (TextView) findViewById(R.id.sort_detail_head_tvtitle);
        mAboveTitle.setText("分类");
        imgQuery = (ImageButton) findViewById(R.id.sort_detail_head_ivsearch);
        imgQuery.setOnClickListener(this);
        lvTitle = (ListView) findViewById(R.id.behind_list_show);
    }

	/**
	 * 设置SlidingMenu的显示方式及其对应的数据
	 */
	private void initListView() {

        lvAdapter = new SimpleAdapter(this, getData(),
                R.layout.behind_list_show, new String[]{LIST_TEXT,
                LIST_IMAGEVIEW},
                new int[]{R.id.textview_behind_title,
                        R.id.imageview_behind_icon}) {

			@Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub.
                View view = super.getView(position, convertView, parent);
                if (position == mTag) {
                    view.setBackgroundResource(R.drawable.back_behind_list);
                    lvTitle.setTag(view);
                } else {
                    view.setBackgroundColor(Color.TRANSPARENT);
                }
                return view;
            }
        };
        lvTitle.setAdapter(lvAdapter);
        lvTitle.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                NavigationModel navModel = navs.get(position);
                mAboveTitle.setText(navModel.getName());
                current_page = navModel.getTags();
                if (lvTitle.getTag() != null) {
                    if (lvTitle.getTag() == view) {
                        SortDetailActivity.this.showContent();
                        return;
                    }
                    ((View) lvTitle.getTag())
                            .setBackgroundColor(Color.TRANSPARENT);
                }
                lvTitle.setTag(view);
                view.setBackgroundResource(R.drawable.back_behind_list);
                
//                Util.toastInfo(context, navModel.getName()) ;
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });

    
	}
	
	/**
	 * 设置NavigationModel及点击Slidingmenu时对应的Title
	 */
	private void initNav() {
        navs = new ArrayList<NavigationModel>();
        NavigationModel nav1 = new NavigationModel("IT", "");
        NavigationModel nav2 = new NavigationModel("互联网", Constant.TAGS.CREATIVE_TAG);
        NavigationModel nav3 = new NavigationModel("Android" , Constant.TAGS.PROJECT_TAG);
        NavigationModel nav4 = new NavigationModel("Iphone", Constant.TAGS.PRODUCT_TAG);
        Collections.addAll(navs, nav1, nav2, nav3, nav4);
    }
	
	/**
	 * 设置SlidingMenu相关数据
	 * @return
	 */
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(LIST_TEXT, "AAAA");
		map.put(LIST_IMAGEVIEW, R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put(LIST_TEXT, "BBBB" ) ;
		map.put(LIST_IMAGEVIEW, R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put(LIST_TEXT, "CCCC");
		map.put(LIST_IMAGEVIEW, R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put(LIST_TEXT, "DDDD");
		map.put(LIST_IMAGEVIEW, R.drawable.ic_launcher);
		list.add(map);
		return list;
	}
	
	/**
	 * 初始化控件以及设置相关的监听器
	 */
	private void initWidget() {
		creativeButton = (Button) findViewById(R.id.sort_detail_btn_creative) ;
		projectButton = (Button) findViewById(R.id.sort_detail_btn_project) ;
		productButton = (Button) findViewById(R.id.sort_detail_btn_product) ;
		sortSpinner = (Spinner) findViewById(R.id.sort_head_spinner) ;
		etFilter = (EditText) findViewById(R.id.sort_head_etfilter) ;
		lvSortDetail = (ListView) findViewById(R.id.sort_lv_detail) ;
		ivSortMenu = (ImageView) findViewById(R.id.sort_detail_head_ivmenu); 
		ivFooterBack = (ImageView) findViewById(R.id.sort_detail_footer_iv_back) ;
		ivFooterAdd = (ImageView) findViewById(R.id.sort_detail_footer_iv_add) ;
		ivFooterMore = (ImageView) findViewById(R.id.sort_detail_footer_iv_more) ;
		ivFooterNote = (ImageView) findViewById(R.id.sort_detail_footer_iv_note) ;
		
		sortSpinner.setSelection(curSortID) ;
		etFilter.setText(curSearchContent) ;
		sortSpinner.setOnItemSelectedListener(this) ;
		creativeButton.setEnabled(false);
		creativeButton.setOnClickListener(sortsBtnClick(creativeButton , 1)) ; 
		projectButton.setOnClickListener(sortsBtnClick(projectButton , 2)) ;
		productButton.setOnClickListener(sortsBtnClick(productButton , 3)) ;
		ivSortMenu.setOnClickListener(this) ;
		ivFooterBack.setOnClickListener(this) ;
		ivFooterAdd.setOnClickListener(this) ;
		ivFooterMore.setOnClickListener(this) ;
		ivFooterNote.setOnClickListener(this) ;
		lvSortDetail.setOnScrollListener(this) ;
		lvSortDetail.setOnItemClickListener(this) ;
	}
	
	/**
	 * 响应分类按钮事件
	 * @param btn 事件源，及当前点击的按钮
	 * @param catalog 额外参数
	 * @return
	 */
	private View.OnClickListener sortsBtnClick(final Button btn,
			final int catalog) {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Util.toastInfo(context, btn.getText().toString().trim()) ;
				if(btn == creativeButton) {
					sortSpinner.setSelection(0) ;
					creativeButton.setEnabled(false) ;
					initData(Constant.Sort.SORT_CREATIVE) ;
				} else {
					creativeButton.setEnabled(true) ;
				}
				
				if(btn == projectButton) {
					sortSpinner.setSelection(1) ;
					projectButton.setEnabled(false) ;
					initData(Constant.Sort.SORT_PROJECT) ;
				} else {
					projectButton.setEnabled(true) ;
				}
				
				if(btn == productButton) {
					sortSpinner.setSelection(2) ;
					productButton.setEnabled(false) ;
					initData(Constant.Sort.SORT_PRODUCT) ;
				} else {
					productButton.setEnabled(true) ;
				}
			}
			
		} ;
	}
	
	/**
	 * 设置相关分类的数据信息
	 * @param flag  分类的类别ID
	 */
	private void initData(int flag) {
		switch (flag) {
			case Constant.Sort.SORT_CREATIVE:
				if(null == creativeInfos) {
					creativeInfos = TestData.initCreative() ;
				}
				sortDetailAdapter = new SortDetailAdapter(context, creativeInfos) ;
				lvSortDetail.setAdapter(sortDetailAdapter) ;
				break;
			case Constant.Sort.SORT_PROJECT :
				if(null == projectInfos) {
					projectInfos = TestData.initProject() ;
				}
				sortDetailAdapter = new SortDetailAdapter(context, projectInfos) ;
				lvSortDetail.setAdapter(sortDetailAdapter) ;
				break ;
			case Constant.Sort.SORT_PRODUCT :
				if(null == productInfos) {
					productInfos = TestData.initProduct() ;
				}
				sortDetailAdapter = new SortDetailAdapter(context, productInfos) ;
				lvSortDetail.setAdapter(sortDetailAdapter) ;
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
				creativeButton.setEnabled(false) ;
				projectButton.setEnabled(true) ;
				productButton.setEnabled(true) ;
				initData(Constant.Sort.SORT_CREATIVE) ;
				break;
			case 1:
				creativeButton.setEnabled(true) ;
				projectButton.setEnabled(false) ;
				productButton.setEnabled(true) ;
				initData(Constant.Sort.SORT_PROJECT) ;
				break;
			case 2 :
				creativeButton.setEnabled(true) ;
				projectButton.setEnabled(true) ;
				productButton.setEnabled(false) ;
				initData(Constant.Sort.SORT_PRODUCT) ;
				break ;
		}
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
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		MetaData info = null ;
		switch (curSortID) {
			case 0:
				info = creativeInfos.get(position) ;
				break;
			case 1 :
				info = projectInfos.get(position) ;
				break ;
			case 2 :
				info = productInfos.get(position) ;
				break ;
			default:
				break;
		}
		Util.toastInfo(context, info.getMetaName()) ;
		
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.sort_detail_head_ivmenu:
//				showMenu();  //show SlidingMenu
				UIHelper.showSortFilterActivity(context) ;
				break;
			case R.id.sort_detail_head_ivsearch :
//				curSearchContent = etFilter.getText().toString().trim() ;
//				searchClick() ;
				
//				UIHelper.showSortFilterActivity(context) ;
				UIHelper.showSearchHistoryActivity(context) ;
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
			default:
				break;
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
            int position, long id) {
		// TODO Auto-generated method stub
		curSortContent = parent.getItemAtPosition(position).toString();
		curSortID = position ;
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
	 */
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
