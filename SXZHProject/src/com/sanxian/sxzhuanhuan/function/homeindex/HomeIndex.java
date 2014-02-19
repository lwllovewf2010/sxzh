package com.sanxian.sxzhuanhuan.function.homeindex;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.IImgs;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ProjectAdapter;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.util.Util;
import com.sanxian.sxzhuanhuan.view.xlistview.XListView;
import com.sanxian.sxzhuanhuan.view.xlistview.XListView.IXListViewListener;

/**
 * @Title: HomeIndex.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex
 * @Description: 首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-15 下午3:27:42
 * @version V1.0
 */
public class HomeIndex extends BaseFragment implements OnClickListener, IXListViewListener,OnPageChangeListener {

	private XListView mListView = null;
	private boolean isUpRefresh = true;
	private boolean isDownRefresh = false;
	private String[] refreshTime = new String[3];
	private Handler mHandler = null;

	private static int SORTID = 0;
	// 布局控件
	private String[] publishType = { "创意话题", "集资项目" };
	private Button btnPublish = null;
	private EditText etSearch = null;
	private ImageView ivSearch = null;
	private ImageView ivCreative = null;
	private TextView tvCreative = null;
	private ImageView ivProject = null;
	private TextView tvProject = null;
	private ImageView ivGoods = null;
	private TextView tvGoods = null;
	private ArrayList<CreativeInfo> creativeInfos = null;
	private ArrayList<ProjectInfo> projectInfos = null;
	private ArrayList<ProductInfo> productInfos = null;
	private ArrayList<CreativeInfo> creativeSearchInfos = null;
	private ArrayList<ProjectInfo> projectSearchInfos = null;
	private ArrayList<ProductInfo> productSearchInfos = null;
	private Context context;

	private TopDialogInfo dialogInfo = null;

	private TestAPI api = null;
	private Map<String, String> input = null;
	private Map<String, String> searchInput = null;
	private List<IImgs> indexImgs = null;
	private int[] PAGE = new int[3];
	private final static int PAGESIZE = 5;

	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	View mainView=null;
	public HomeIndex() {
		super();
	}
	private void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = UIHelper.setOption();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
		intImageUtil();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.home_index, container, false);
		mainView=view;
		init(view);

		api.getIndexImgs(HomeIndex.this, Constant.REQUESTCODE.INDEX_IMGS_REQUEST) ; //首页轮播图----

		return view;
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case Constant.REQUESTCODE.INDEX_IMGS_REQUEST: // 首页轮播图
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				indexImgs = JSONParser.getIndexImgs(jsondata);

				initViewPager();
				initDot();
				taggletHandler.sleep(5000);

			}
			break;

		case Constant.REQUESTCODE.CREATIVE_LIST_REQUEST: // 首页创意列表
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println("首页创意列表 :" + jsondata);
				ArrayList<CreativeInfo> tempCreativeInfo = null;
				tempCreativeInfo = (ArrayList<CreativeInfo>) JSONParser.getCreativeInfo(jsondata);

				if (isUpRefresh) {
					creativeInfos = tempCreativeInfo;
				} else {
					PAGE[0]++;
					if (null != creativeInfos)
						creativeInfos.addAll(tempCreativeInfo);
					else
						creativeInfos = tempCreativeInfo;
				}

				mListView.setAdapter(new CreativeAdapter(getActivity(), creativeInfos));
				setXlistviewPos();
			}
			break;
		case Constant.REQUESTCODE.PROJECT_LIST_REQUEST: // 首页项目列表
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				ArrayList<ProjectInfo> tempProjectInfo = null;
				tempProjectInfo = (ArrayList<ProjectInfo>) JSONParser.getProjectInfo(jsondata);
				if (isUpRefresh) {
					projectInfos = tempProjectInfo;
				} else {
					PAGE[1]++;
					if (null != projectInfos)
						projectInfos.addAll(tempProjectInfo);
					else
						projectInfos = tempProjectInfo;
				}
				mListView.setAdapter(new ProjectAdapter(getActivity(), projectInfos));
				setXlistviewPos();
			}
			break;
		case Constant.REQUESTCODE.PRODUCT_LIST_REQUEST: // 首页商品列表
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				ArrayList<ProductInfo> tempProductInfo = null;
				tempProductInfo = (ArrayList<ProductInfo>) JSONParser.getProductInfo(jsondata);
				if (isUpRefresh) {
					productInfos = tempProductInfo;
				} else {
					PAGE[2]++;
					if (null != productInfos)
						productInfos.addAll(tempProductInfo);
					else
						productInfos = tempProductInfo;
				}
				mListView.setAdapter(new ProductAdapter(getActivity(), productInfos));
				setXlistviewPos();
			}
			break;

		case Constant.REQUESTCODE.HOME_INDEX_SEARCH_CREATIVE_REQUEST: // 首页创意查找
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				creativeSearchInfos = (ArrayList<CreativeInfo>) JSONParser.getCreativeInfo(jsondata);
				mListView.setAdapter(new CreativeAdapter(getActivity(), creativeSearchInfos)); // +++
			}
			break;
		case Constant.REQUESTCODE.HOME_INDEX_SEARCH_PROJECT_REQUEST: // 首页项目查找
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				projectSearchInfos = (ArrayList<ProjectInfo>) JSONParser.getProjectInfo(jsondata);
				mListView.setAdapter(new ProjectAdapter(getActivity(), projectSearchInfos)); // +++
			}
			break;
		case Constant.REQUESTCODE.HOME_INDEX_SEARCH_PRODUCT_REQUEST: // 首页商品查找
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				productSearchInfos = (ArrayList<ProductInfo>) JSONParser.getProductInfo(jsondata);
				mListView.setAdapter(new ProductAdapter(getActivity(), productSearchInfos)); // +++
			}
			break;

		}

	}

	/**
	 * 设置XListView中滚动的位置 上拉为第一条， 下拉为最后一条
	 */
	private void setXlistviewPos() {
		if (isUpRefresh) {
			mListView.setSelection(0);
		}
		if (isDownRefresh) {
			mListView.setSelection(creativeInfos.size() - 1);
			isDownRefresh = false;
		}
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
		case R.id.home_index_title_right_btn:
			if (isOffLine()) {
				Util.toastInfo(context, "请登录再操作！");
				Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);
			} else {
				showRightDialog();
			}
			break;
		case R.id.home_index_title_search_img:
			isUpRefresh = true;
			initSearchData(SORTID);
			break;
		}

	}

	/**
	 * 右上角对话框
	 */
	private void showRightDialog() {
		dialogInfo = new TopDialogInfo(DialogConstant.TRIGHT, publishType);
		Intent intent = new Intent(getActivity(), TopRightOrLeftDialog.class);
		intent.putExtra("info", dialogInfo);
		startActivityForResult(intent, DialogConstant.REQUEST_TOP);
	}

	/**
	 * 实名验证对话框
	 */
	private void showMidDialog() {
		Intent intent = new Intent(getActivity(), MiddleDialog.class);
		MiddleDialogInfo info = new MiddleDialogInfo("提示", "请您先进行实名认证再继续发布项目，以便于客服在您需要的时候提供及时的帮助。", false, "", "", "立即去验证", "取消发布");
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_MIDDLE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == DialogConstant.REQUEST_TOP) {
			if (resultCode != DialogConstant.DIALOG_RETURN) {
				System.out.println("resultCode=" + resultCode + "    " + dialogInfo.getMenu()[resultCode]);
				Intent publishIntent = null;
				if (resultCode == Constant.PUBLISH_TOPIC) {
					publishIntent = new Intent(getActivity(), PublishTopic.class);
					getActivity().startActivity(publishIntent);
				} else if (resultCode == Constant.PUBLISH_ORIGINALITY) {
					publishIntent = new Intent(getActivity(), PublishOriginality.class);
					getActivity().startActivity(publishIntent);
				}
			}

		} else if (resultCode == DialogConstant.MIDDLE_OK) {
			System.out.println("---------ok -------------");
			UIHelper.showRealAuthActivity(context);
		} else if (resultCode == DialogConstant.MIDDLE_CANCEL) {
			System.out.println("----cancle-----");
		} else if (resultCode == Constant.RESULT_LOGIN_CODE) {
			System.out.println("-------login ok ---------");
		}
	}

	/**
	 * 初始化数据
	 */
	private void init(View view) {
		api = new TestAPI();
		input = new HashMap<String, String>();
		searchInput = new HashMap<String, String>();

		for (int i = 0; i < PAGE.length; i++) {
			PAGE[i] = 1;		//点击类别之后已经加载了一次
		}

		// 初始刷新相关
		mHandler = new Handler();
		mListView = (XListView) view.findViewById(R.id.home_index_content_xlist);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);

		btnPublish = (Button) view.findViewById(R.id.home_index_title_right_btn);
		btnPublish.setOnClickListener(this);
		etSearch = (EditText) view.findViewById(R.id.home_index_title_search_text);
		ivSearch = (ImageView) view.findViewById(R.id.home_index_title_search_img);
		ivSearch.setOnClickListener(this);

		ivCreative = (ImageView) view.findViewById(R.id.home_index_content_nav_creative_iv);
		tvCreative = (TextView) view.findViewById(R.id.home_index_content_nav_creative_tv);
		ivProject = (ImageView) view.findViewById(R.id.home_index_content_nav_project_iv);
		tvProject = (TextView) view.findViewById(R.id.home_index_content_nav_project_tv);
		ivGoods = (ImageView) view.findViewById(R.id.home_index_content_nav_goods_iv);
		tvGoods = (TextView) view.findViewById(R.id.home_index_content_nav_goods_tv);

		ivCreative.setOnClickListener(sortsClick(ivCreative, 1));
		tvCreative.setOnClickListener(sortsClick(tvCreative, 1));
		ivProject.setOnClickListener(sortsClick(ivProject, 2));
		tvProject.setOnClickListener(sortsClick(tvProject, 2));
		ivGoods.setOnClickListener(sortsClick(ivGoods, 3));
		tvGoods.setOnClickListener(sortsClick(tvGoods, 3));

		ivCreative.setPressed(true);
		tvCreative.setTextColor(getActivity().getResources().getColor(R.color.color_444a4d));
		initData(Constant.Sort.SORT_CREATIVE);
	}

	private View.OnClickListener sortsClick(final View view, final int catalog) {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (catalog == 1) {
					SORTID = 0;
					ivCreative.setPressed(true);
					tvCreative.setTextColor(getActivity().getResources().getColor(R.color.color_444a4d));
					initData(Constant.Sort.SORT_CREATIVE);
				} else {
					ivCreative.setPressed(false);
					tvCreative.setTextColor(getActivity().getResources().getColor(R.color.color_6d6d72));
				}

				if (catalog == 2) {
					SORTID = 1;
					ivProject.setPressed(true);
					tvProject.setTextColor(getActivity().getResources().getColor(R.color.color_444a4d));
					initData(Constant.Sort.SORT_PROJECT);
				} else {
					ivProject.setPressed(false);
					tvProject.setTextColor(getActivity().getResources().getColor(R.color.color_6d6d72));
				}

				if (catalog == 3) {
					SORTID = 2;
					ivGoods.setPressed(true);
					tvGoods.setTextColor(getActivity().getResources().getColor(R.color.color_444a4d));
					initData(Constant.Sort.SORT_PRODUCT);
				} else {
					ivGoods.setPressed(false);
					tvGoods.setTextColor(getActivity().getResources().getColor(R.color.color_6d6d72));
				}
			}

		};
	}

	/**
	 * 首页公共的获取数据接口
	 * @param start
	 * @param pmode
	 * @param pagesize
	 * @param requestCode
	 */
	private void getIndexData(String start , String pmode , String pagesize , int requestCode) {
		input.put("start", start);
		input.put("pmode", pmode);
		input.put("pagesize", pagesize);
		api.getCPPData(input, this, requestCode);
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
			if(null == creativeInfos ) {
//				input.put("start", "0");
//				input.put("pmode", "2");
//				input.put("pagesize", "" + PAGESIZE);
//				api.getCPPData(input, this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST);
				getIndexData("0", "2", "" + PAGESIZE, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
			} else {
				mListView.setAdapter(new CreativeAdapter(getActivity(), creativeInfos));
			}
			break;
		case Constant.Sort.SORT_PROJECT:
			if (null == projectInfos) {
				getIndexData("0", "1", "" + PAGESIZE, Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
			} else {
				mListView.setAdapter(new ProjectAdapter(getActivity(), projectInfos));
			}
			break;
		case Constant.Sort.SORT_PRODUCT:
			if (null == productInfos) {
				getIndexData("0", "4", "" + PAGESIZE, Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
			} else {
				mListView.setAdapter(new ProductAdapter(getActivity(), productInfos));
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 首页搜索
	 * 
	 * @param flag
	 */
	private void initSearchData(int flag) {
		switch (flag) {
		case 0:
			//"params":{"start":0,"pagesize":10,"category_id":1,"total_count":1, "title":"关键词1,关键词2"...}}
//			参数名	必选	类型及值	说明
//			start	true	(int)	limit分页起始行
//			pagesize	false	(int)	分页显示数量，不带=10
//			total_count	false	(int)1或0	1=结果中带有总记录数 0=不带
//			open_id	false	(string)	创意创建人openid
//			category_id	false	(int)	行业id
//			province_id	false	(int)	省id
//			city_id	false	(int)	城市id
//			title	false	(string)	搜索创意标题 模糊搜索 空格或逗号可最多同时搜索5个关键词 模糊搜索
//			orderby	false	(int)1 4 5	排序方式，取值： 1=发布日期倒序(默认), 4=点击量, 5=关注人数
			searchInput.clear();
			searchInput.put("start", "");
			searchInput.put("pagesize", "");
			searchInput.put("total_count", "0");
			searchInput.put("open_id", "");
			searchInput.put("category_id", "");
			searchInput.put("province_id", "");
			searchInput.put("city_id", "");
			searchInput.put("title", etSearch.getText().toString());
			searchInput.put("orderby", "");
			api.operaCreativess("search", searchInput, this, Constant.REQUESTCODE.HOME_INDEX_SEARCH_CREATIVE_REQUEST);
			break;
		case 1:
//			参数名	必选	类型及值	说明
//			start	true	(int)	limit分页起始行
//			pagesize	false	(int)	分页显示数量
//			total_count	false	(int)1或0	1=结果中带有总记录数 0=不带
//			project_state	false	(string)不带则查找 2	项目状态 -2:删除 -1:审核失败 0:创建 1:提交审核 2:审核成功
//			user_id	false	(string)	项目创建人openid
//			project_step	false	(string) 多个用逗号分隔	默认查找6, 项目（产品）进行的步骤（-1:筹集失败 1:项目新建 2:项目回报 3:项目提交 4:审核中 5:预热中 6:筹集中 7:招标中 8:公司创建成功（产品：9：发布招标 10：招标成功 11：全部发货完成交易））
//			orderby	false	(int)1 2 3 4 5	排序方式，取值： 1=发布日期倒序(默认), 2=价格倒序, 3=价格升序, 4=点击量, 5=参与人数最多
//			category_id	false	(int)	行业id
//			province_id	false	(int)	省id
//			city_id	false	(int)	城市id
//			title	false	(string)	搜索项目名称关键词 空格或逗号可最多同时搜索5个关键词 模糊搜索
			searchInput.clear();
			searchInput.put("start", "");
			searchInput.put("pagesize", "");
			searchInput.put("total_count", "0");
			searchInput.put("project_state", "");
			searchInput.put("user_id", "");
			searchInput.put("project_step", "");
			searchInput.put("orderby", "");
			searchInput.put("category_id", "");
			searchInput.put("province_id", "");
			searchInput.put("city_id", "");
			searchInput.put("title", etSearch.getText().toString());
			api.operaProjects("search", searchInput, this, Constant.REQUESTCODE.HOME_INDEX_SEARCH_PROJECT_REQUEST);
			break;
		case 2:
//			参数名	必选	类型及值	说明
//			start	true	(int)	limit分页起始行
//			pagesize	false	(int)	分页显示数量
//			total_count	false	(int)1或0	1=结果中带有总记录数 0=不带
//			title	false	(string)	商品名称 空格或逗号可最多同时搜索5个关键词 模糊搜索
//			province_id	false	(int)	省id
//			city_id	false	(int)	城市id
			searchInput.clear();
			searchInput.put("start", "");
			searchInput.put("pagesize", "");
			searchInput.put("total_count", "0");
			searchInput.put("title", etSearch.getText().toString());
			searchInput.put("province_id", "");
			searchInput.put("city_id", "");
			api.operaProduct("search", searchInput, this, Constant.REQUESTCODE.HOME_INDEX_SEARCH_PRODUCT_REQUEST);
			break;
		}
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences spf = getActivity().getSharedPreferences(Constant.PRE_CONFIG_FILE, 0);
		SharedPreferences.Editor editor = spf.edit();
		for (int i = 0; i < 3; i++) {
			editor.putString("refresh_time_" + i, refreshTime[i]);
		}
		editor.commit();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences spf = getActivity().getSharedPreferences(Constant.PRE_CONFIG_FILE, 0);
		for (int i = 0; i < 3; i++) {
			refreshTime[i] = spf.getString("refresh_time_" + i, "");
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isUpRefresh = true;
				isDownRefresh = false;
				switch (SORTID) {
				case 0:
//					input.put("start", "0");
//					input.put("pmode", "2");
//					input.put("pagesize", "" + ( PAGE[SORTID] * PAGESIZE));
//					api.getCPPData(input, HomeIndex.this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST);
					getIndexData("0", "2", "" + ( PAGE[SORTID] * PAGESIZE) , Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
					break;
				case 1:
					getIndexData("0", "1", "" + ( PAGE[SORTID] * PAGESIZE) , Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
					break;
				case 2:
					getIndexData("0", "4", "" + ( PAGE[SORTID] * PAGESIZE) , Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
					break;
				}

				onLoad(refreshTime[SORTID]);
				refreshTime[SORTID] = refTimeFormat();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isDownRefresh = true;
				isUpRefresh = false;
				switch (SORTID) {
				case 0:
//					input.put("start", "" + PAGE[SORTID] * PAGESIZE);
//					input.put("pmode", "2");
//					input.put("pagesize", "" + PAGESIZE);
//					api.getCPPData(input, HomeIndex.this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST);
					getIndexData("" + PAGE[SORTID] * PAGESIZE, "2", "" + PAGESIZE , Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
					break;
				case 1:
					getIndexData("" + PAGE[SORTID] * PAGESIZE, "1", "" + PAGESIZE , Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
					break;
				case 2:
					getIndexData("" + PAGE[SORTID] * PAGESIZE, "4", "" + PAGESIZE , Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
					break;

				}
				onLoad("");
			}
		}, 2000);
	}

	private void onLoad(String time) {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(time);
	}

	private String refTimeFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		return formatter.format(curDate);
	}

	// 广告
	private ViewPager mPager;
	private ArrayList<View> views;
	private View view1;
	private LinearLayout dot;
	private List<ImageView> dotList;
	private int currentItem = 0;

	private void initViewPager() {
		mPager = (ViewPager) mainView.findViewById(R.id.viewPager);
		views = new ArrayList<View>();
		LayoutInflater mInflater = LayoutInflater.from(context);
	
		for (int i = 0; i < indexImgs.size(); i++) {
			view1 = mInflater.inflate(R.layout.view1, null);
			views.add(view1);
		}

		mPager.setAdapter(new myPagerAdapter());
		mPager.setOnPageChangeListener(this);
	}

	private void initDot() {
		dot = (LinearLayout) mainView.findViewById(R.id.dot);
		dotList = new ArrayList<ImageView>();
		for (int i = 0; i < views.size(); i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(12, 0, 12, 0);
			ImageView m = new ImageView(context);
			if (i == 0) {
				m.setBackgroundResource(R.drawable.dot_red);
			} else {
				m.setBackgroundResource(R.drawable.dot_white);
			}
			m.setLayoutParams(params);
			dot.addView(m);
			dotList.add(m);
		}
	}

	class myPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(View container, final int position) {

			((ViewPager) container).addView(views.get(position));
			if (views.get(position) != null) {
				// views.get(position).setBackgroundResource(list.get(position).getImagesrc());
//				ansey.showportAnsy((ImageView) views.get(position), indexImgs.get(position).getImagesrc());
				imageLoader.displayImage( indexImgs.get(position).getImgpath(), (ImageView) views.get(position), options);
				views.get(position).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {	//点击广告图
//						Util.toastInfo(context, "hello" + position);
					}
				});
			}

			return views.get(position);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		for (int i = 0; i < dotList.size(); i++) {
			if (i == arg0) {
				dotList.get(i).setBackgroundResource(R.drawable.dot_red);
			} else {
				dotList.get(i).setBackgroundResource(R.drawable.dot_white);
			}
		}
		currentItem = arg0;
	}

	TaggleHandler taggletHandler = new TaggleHandler();

	class TaggleHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
		
			// Animation animation = AnimationUtils.loadAnimation(
			// MainActivity.this, R.anim.enter);
			// mPager.setAnimationCacheEnabled(false);
			// mPager.setAnimation(animation);
			mPager.setCurrentItem(currentItem);
			// mDuration += 100;
			// scroller.setmDuration(mDuration);
			taggletHandler.sleep(3000);
			if (currentItem >= views.size()) {
				currentItem = 0;
				// mDuration = 100;
			} else {
				currentItem++;
			}
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			this.sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
}
