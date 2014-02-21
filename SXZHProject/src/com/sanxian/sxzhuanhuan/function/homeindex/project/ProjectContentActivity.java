package com.sanxian.sxzhuanhuan.function.homeindex.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.HomeIndexGoodsAPI;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.common.CustomProgress;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.dialog.BottomRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.GoodsBuyEntity;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.entity.TestData;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallCreaterAdapter;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallJoinerAdapter;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussHallManagerAdapter;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccoAddressIndexActivity;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
 * @Title: ProjectDetailActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex
 * @Description: 项目详情
 * @author zhangfl@sanxian.com
 * @date 2014-1-14 上午10:47:44
 * @version V1.0
 */
public class ProjectContentActivity extends BaseActivity implements
		OnClickListener , OnItemSelectedListener , OnItemClickListener{
	private LayoutInflater mInflater = null;
	private ScUtil sc = null ;
	private View viewProjectContent = null;
	private View viewTopicContent = null;
	private List<View> listViews; // Tab页面列表
	
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null ;
	
	// common
	private CommonHeader conHeader = null ;
	/** 1.项目描述  2.项目详情 3.项目成员  4.部落大厅  5.讨论大厅*/  
	private static int PAGE_FALG = 0 ;  
//	private ImageView ivBack = null;
//	private ImageView ivCollect = null;
//	private ImageView ivMenu = null;

	// tab1 ---项目描述
	private TextView tvGOTOHall = null ;
	private TextView tvGOTODetail = null ;
	private TextView tvGOTOVideos = null ;
	private TextView tvGOTOMembers = null ;
	private TextView tvGOTOProduct = null ;
	
	private ImageView ivProjectContentLogo = null ;
	private TextView tvProjectContentTitle = null ;
//	private TextView tvProjectContentJoin = null ;
	private TextView tvProjectContentCreateName = null ;
	private TextView tvProjectContentHangye= null ;
	private TextView tvProjectContentZone= null ;
	private TextView tvProjectContentProfile= null ;
//	private TextView tvProjectContentTotalMoney= null ;
//	private TextView tvProjectContentPrePerson= null ;
//	private TextView tvProjectContentCurMoney= null ;
//	private TextView tvProjectContentShenTime= null ;
//	private ProgressBar pbProjectContentBar = null ;
	private CustomProgress pbProjectContentBar = null ;
	private TextView tvProjectContentHBMoney= null ;
	private TextView tvProjectContentHBTime= null ;
	private TextView tvProjectContentHBContent= null ;
	private TextView tvProjectContentHBPreBuy= null ;
//	private TextView tvProjectContentHBBaoyou= null ;
	private Button btnProjectContentCangu = null ;
	
	private RelativeLayout rlProjectContentHall = null ;
	private RelativeLayout rlProjectContentDetail = null ;
	private RelativeLayout rlProjectContentVideo = null ;
	private RelativeLayout rlProjectContentParter = null ;
	private RelativeLayout rlProjectContentProduct = null ;
	
	//项目详情
	private TextView tvProjectDetailName = null ;
	private ImageView ivProjectDetailLogo = null ;
	private TextView tvProjectDetailContent = null ;
	
	//项目成员
	private Spinner spFilter = null ;
	private EditText etFilter = null ;
	private ImageView ivSearch = null ;
	private ListView lvMembers = null ;
	private ArrayList<UserInfo> userinfos = null ;
	private ProjectMembersAdapter pmAdapter = null ;
	private String filter_level_1 = "" ;
	private String filter_level_2 = "" ;
	
	//讨论大厅
//	private ListView lvTopicHallControl = null ;
//	private ListView lvTopicHallJoinfo = null ;
//	private ListView lvTopicHallMyinfo = null ;
//	private DiscussHallManagerAdapter dhmaTopicHallControl = null ;
//	private DiscussHallJoinerAdapter dhmaTopicHallJoinfo = null ;
//	private DiscussHallCreaterAdapter dhmaTopicHallMyinfo = null ;
	private RelativeLayout rlTopicHallManager = null ;
	private RelativeLayout rlTopicHallJoin = null ;
	private RelativeLayout rlTopicHallCreate = null ;
	private TextView tvManagerCount = null ; 
	private TextView tvManagerMember = null ; 
	private TextView tvJoinCount = null ; 
	private TextView tvCreateCount = null ; 
	
	
	private static String proID = "" ;
	private static String open_id = "1_1177_469954" ;
	private TestAPI api = null;
	private HomeIndexGoodsAPI goodsAPI = null;
	private Map<String , String> input = null ;
	private static ProjectInfo projectInfoDetail = null ;
	//added by yuanqk
	private final int REQUEST_DEFAULT_ADDR = 2;
	public final static int REQUEST_NEW_ADDR = 3;
	private AddressInfo address = new AddressInfo();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.project_content);

		Intent intent = getIntent() ;
		if(null == intent) {
//			Util.toastInfo(context, string) ;
		} else {
			proID = intent.getStringExtra("project_id") ;
			System.out.println("-------" + proID);
		}
		
		options = UIHelper.setOption() ;
		
		api = new TestAPI();
		input = new HashMap<String, String>();
		input.put("proj_id", proID ) ; //Integer.parseInt(proID)) ;
		input.put("open_id", UIHelper.getOpenID(ProjectContentActivity.this) ); //open_id) ; // UIHelper.getOpenID(ProjectContentActivity.this) ;
		api.operaProjects("get_row", input, ProjectContentActivity.this, Constant.REQUESTCODE.PROJECT_GET_ROW_REQUEST) ; 
		
		initContent();
	}

	/**
	 * 初始化项目内容页
	 */
	private void initContent() {
		sc = new ScUtil(this);
		listViews = new ArrayList<View>();
		mInflater = getLayoutInflater();
		viewProjectContent = mInflater.inflate(R.layout.project_content_tab1,
				null);
		viewTopicContent = mInflater.inflate(R.layout.topic_hall,
				null);

		initCommnet(viewTopicContent);

		listViews.add(viewProjectContent);
		listViews.add(viewTopicContent);

		PAGE_FALG = 1 ;
		initWidget();
		sc.showSc("项目描述", "讨论大厅", listViews , conHeader);
		initData() ;
	}

	/**
	 * 初始化项目详情页
	 */
	private void initDetail() {
		listViews.clear() ;
		viewProjectContent = mInflater.inflate(R.layout.project_detail, null);
		viewTopicContent = mInflater.inflate(R.layout.topic_content_comment, null);
		initCommnet(viewTopicContent);

		listViews.add(viewProjectContent);
		listViews.add(viewTopicContent);

		PAGE_FALG = 2 ;
		initWidget();
		sc.showSc("项目描述", "讨论大厅", listViews , conHeader);
		initData() ;
	}
	
	/**
	 * 初始化项目成员
	 */
	private void initMembers() {
		listViews.clear() ;
		viewProjectContent = mInflater.inflate(R.layout.project_members, null);
		viewTopicContent = mInflater.inflate(R.layout.topic_content_comment, null);
		initCommnet(viewTopicContent);

		listViews.add(viewProjectContent);
		listViews.add(viewTopicContent);
		
		PAGE_FALG = 3 ;
		initWidget();
		sc.showSc("项目描述", "讨论大厅", listViews , conHeader);
		initData() ;
	}
	
	/**
	 * 初始化部落大厅
	 */
	private void initHall() {
		listViews.clear() ;
		viewProjectContent = mInflater.inflate(R.layout.project_members, null);
		viewTopicContent = mInflater.inflate(R.layout.topic_content_comment, null);
		initCommnet(viewTopicContent);

		listViews.add(viewProjectContent);
		listViews.add(viewTopicContent);

		PAGE_FALG = 4 ;
		initWidget();
		sc.showSc("部落描述", "讨论大厅", listViews , conHeader);
		initData() ;
	}

	/**
	 * 初始化用户评论页面
	 * 
	 * @param view
	 */
	private void initCommnet(View view) {
//		PAGE_FALG = 5 ;
//		initWidget();
		rlTopicHallManager = (RelativeLayout) view.findViewById(R.id.topic_hall_rl_manager_info) ;
		rlTopicHallJoin = (RelativeLayout) view.findViewById(R.id.topic_hall_rl_join_info) ;
		rlTopicHallCreate = (RelativeLayout) view.findViewById(R.id.topic_hall_rl_create_info) ;
		tvManagerCount = (TextView) view.findViewById(R.id.topic_hall_rl_manager_info_count) ;
		tvManagerMember = (TextView) view.findViewById(R.id.topic_hall_rl_manager_member_count) ;
		tvJoinCount = (TextView) view.findViewById(R.id.topic_hall_rl_join_info_count) ;
		tvCreateCount = (TextView) view.findViewById(R.id.topic_hall_rl_create_info_count) ;
		
		rlTopicHallManager.setOnClickListener(this) ;
		rlTopicHallJoin.setOnClickListener(this) ;
		rlTopicHallCreate.setOnClickListener(this) ;
		
		sc.showSc("项目描述", "讨论大厅", listViews , conHeader);
//		initData() ;
	}

	private TextView tvCurMoney = null ;
	private TextView tvJoinPerson = null ;
	private TextView tvAllMoney = null ;
	private TextView tvShenTime = null;
	private void initWidget() {
		//common
//		ivBack = (ImageView) findViewById(R.id.project_content_footer_iv_back);
//		ivCollect = (ImageView) findViewById(R.id.project_content_footer_iv_collect);
//		ivMenu = (ImageView) findViewById(R.id.project_content_footer_iv_menu);
//		ivBack.setOnClickListener(this);
//		ivMenu.setOnClickListener(this);
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		conHeader.show(true, true , "返回", true, "项目介绍", false , "" , false) ;
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		
		//项目描述
		if( 1 == PAGE_FALG ) {
			ivProjectContentLogo = (ImageView) viewProjectContent.findViewById(R.id.project_content_tab1__pro_logo ) ;
			tvProjectContentTitle = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_title);
//			tvProjectContentJoin = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_sale) ;
			tvProjectContentCreateName = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_name) ;
			tvProjectContentHangye = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_hangye); 
			tvProjectContentZone = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_zone); 
			tvProjectContentProfile = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_text);
			
//			tvProjectContentTotalMoney = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_total_money);
//			tvProjectContentPrePerson = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_buy_count);
//			tvProjectContentCurMoney = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_cur_money);
//			tvProjectContentShenTime = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_shen_time);
			pbProjectContentBar = (CustomProgress) viewProjectContent.findViewById(R.id.project_content_tab1_progressBar);
			tvCurMoney = (TextView) viewProjectContent.findViewById(R.id.sort_detail_item_cur_money);
			tvJoinPerson = (TextView) viewProjectContent.findViewById(R.id.sort_detail_item_person_join);
			tvAllMoney = (TextView) viewProjectContent.findViewById(R.id.sort_detail_item_all_money);
			tvShenTime = (TextView) viewProjectContent.findViewById(R.id.sort_detail_item_shen_time);
			
			tvProjectContentHBMoney = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_huibao_lin1_money);
			tvProjectContentHBTime = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_huibao_lin1_time);
			tvProjectContentHBContent = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_huibao_lin1_content);
			tvProjectContentHBPreBuy = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_huibao_lin1_pre_buy);
//			tvProjectContentHBBaoyou = (TextView) viewProjectContent.findViewById(R.id.project_content_tab1_huibao_baoyou) ;
			btnProjectContentCangu = (Button)  viewProjectContent.findViewById(R.id.project_content_button_no_join);
			btnProjectContentCangu.setOnClickListener(this) ;
			
			tvGOTOHall = (TextView) viewProjectContent.findViewById(R.id.project_content_goto_hall) ;
			tvGOTODetail = (TextView) viewProjectContent.findViewById(R.id.project_content_goto_pro_detail) ;
			tvGOTOVideos = (TextView) viewProjectContent.findViewById(R.id.project_content_goto_videos) ;
			tvGOTOMembers = (TextView) viewProjectContent.findViewById(R.id.project_content_goto_xiangmu_chengyuan) ;
			tvGOTOProduct = (TextView) viewProjectContent.findViewById(R.id.project_content_goto_pro_product) ;
//			tvGOTOHall.setOnClickListener(this) ;
//			tvGOTODetail.setOnClickListener(this) ;
//			tvGOTOVideos.setOnClickListener(this) ;
//			tvGOTOMembers.setOnClickListener(this) ;
//			tvGOTOProduct.setOnClickListener(this) ;
			rlProjectContentHall = (RelativeLayout) viewProjectContent.findViewById(R.id.project_content_type_rel_hall) ;
			rlProjectContentDetail = (RelativeLayout) viewProjectContent.findViewById(R.id.project_content_type_rel) ;
			rlProjectContentVideo = (RelativeLayout) viewProjectContent.findViewById(R.id.project_content_renshuss_rel) ;
			rlProjectContentParter = (RelativeLayout) viewProjectContent.findViewById(R.id.project_content_xiangmu_chengyuan_rel) ;
			rlProjectContentProduct = (RelativeLayout) viewProjectContent.findViewById(R.id.project_content_type_rel_pro_product) ;
			rlProjectContentHall.setOnClickListener(this) ;
			rlProjectContentDetail.setOnClickListener(this) ;
			rlProjectContentVideo.setOnClickListener(this) ;
			rlProjectContentParter.setOnClickListener(this) ;
			rlProjectContentProduct.setOnClickListener(this) ;
			
//			ivCollect.setVisibility(View.VISIBLE) ;
//			ivCollect.setOnClickListener(this);
		} else if (2 == PAGE_FALG ) { // 项目详情
//			ivCollect.setVisibility(View.INVISIBLE) ;
			
			tvProjectDetailName = (TextView) viewProjectContent.findViewById(R.id.project_detail_tv_proname) ;
			ivProjectDetailLogo = (ImageView) viewProjectContent.findViewById(R.id.project_detail_iv_prologo) ;
			tvProjectDetailContent = (TextView) viewProjectContent.findViewById(R.id.project_detail_tv_pridetail) ;
		} else if (3 == PAGE_FALG ) {  // 项目成员
//			ivCollect.setVisibility(View.INVISIBLE) ;
			spFilter = (Spinner) findViewById(R.id.project_members_spinner_filter) ;
			etFilter = (EditText) findViewById(R.id.project_members_et_filter ) ;
			ivSearch = (ImageView) findViewById(R.id.project_members_iv_search ) ;
			lvMembers = (ListView) findViewById(R.id.project_members_lv) ;
			
			ivSearch.setOnClickListener(this) ;
			spFilter.setOnItemSelectedListener(this) ;
			lvMembers.setOnItemClickListener(this) ;
			
			userinfos = TestData.initUserinfos() ;
			pmAdapter = new ProjectMembersAdapter(this, userinfos) ;
			lvMembers.setAdapter(pmAdapter) ;
		} else if ( 4 == PAGE_FALG) {  //部落大厅
			
		} 
		

	}
	
	private void initData() {
		if( 1 == PAGE_FALG ) {
			
			if(null != projectInfoDetail ) {
				imageLoader.displayImage(projectInfoDetail.getProject_logo(), ivProjectContentLogo, options, null);
				tvProjectContentTitle.setText( projectInfoDetail.getProject_name()) ;
				tvProjectContentCreateName.setText("    " + projectInfoDetail.getProject_realname()) ;
				tvProjectContentHangye.setText("" + projectInfoDetail.getCategory_name()) ;
				tvProjectContentZone.setText("    " + projectInfoDetail.getProvince_name() + "." + projectInfoDetail.getCity_name()) ;
				tvProjectContentProfile.setText((null==projectInfoDetail.getProject_explain()) ? "" : Html.fromHtml(projectInfoDetail.getProject_explain())) ;  //Html.fromHtml(projectInfoDetail.getProject_explain())
//				tvProjectContentTotalMoney.setText("" + projectInfoDetail.getProject_money()) ;
//				tvProjectContentPrePerson.setText(projectInfoDetail.getPurchase_user_num()) ;
//				tvProjectContentCurMoney.setText(projectInfoDetail.getPurchase_money()) ;
//				tvProjectContentShenTime.setText(projectInfoDetail.getProject_shentime()) ;  //  substring(0, 10)
				//test
//				projectInfoDetail.setPurchase_money("100") ;
				if(null == projectInfoDetail.getPurchase_money() || (0 == projectInfoDetail.getProject_money()) )
					pbProjectContentBar.setProgress(0) ;
				else pbProjectContentBar.setProgress(Integer.parseInt(projectInfoDetail.getPurchase_money()) * 100 / projectInfoDetail.getProject_money() ) ;
				tvCurMoney.setText("￥" + projectInfoDetail.getPurchase_money() );
				tvJoinPerson.setText("" + projectInfoDetail.getAttention_nums() );
				tvAllMoney.setText("￥" + projectInfoDetail.getProject_money());
				tvShenTime.setText("" + projectInfoDetail.getProject_end_time() );
				
				tvProjectContentHBMoney.setText("￥" + projectInfoDetail.getReward_money() + " RMB") ;
				tvProjectContentHBTime.setText("" + projectInfoDetail.getReward_return_days() + "天") ;
				tvProjectContentHBContent.setText(projectInfoDetail.getReward_content()) ;  //回报的内容
				if(1 == projectInfoDetail.getReward_limit()) {//是否限定入股人数 1=限 0=不限
					tvProjectContentHBPreBuy.setText( Html.fromHtml("已参与" + "<font color='" + getResources().getColor(R.color.color_18af8e) + "'>" +
							projectInfoDetail.getAttention_nums() + "</font>" + "人/人数上限" + "<font color='" + getResources().getColor(R.color.color_18af8e) + "'>" + 
							projectInfoDetail.getReward_person() + "</font>" + "人") ) ;
				} else {
					tvProjectContentHBPreBuy.setText( Html.fromHtml("已参与" +"<font color='" + getResources().getColor(R.color.color_18af8e) + "'>" +
							projectInfoDetail.getAttention_nums() + "</font>" + "人/不限定名额" )) ;
				}
				
//				if(1 == projectInfoDetail.getReward_post_free()) {//是否包邮 0=不包邮 1=大陆包邮
//					tvProjectContentHBBaoyou.setText("包快递（大陆地区）") ;
//				}else {
//					tvProjectContentHBBaoyou.setText("不包邮") ;
//				}
//				if(true == projectInfoDetail.isPurchase_already()) {
//					btnProjectContentCangu.setClickable(false) ;
//					btnProjectContentCangu.setText("已经投资") ;
//					btnProjectContentCangu.setBackgroundColor(ProjectContentActivity.this.getResources().getColor(R.color.dimgray)) ;
//				} else {
//					btnProjectContentCangu.setClickable(true) ;
//					tvProjectContentJoin.setText("项目预售中(我还未加入)") ;
//				}
			}
			
		} else if (2 == PAGE_FALG ) {
			if(null != projectInfoDetail ) {
				tvProjectDetailName.setText(projectInfoDetail.getProject_name()) ;
				imageLoader.displayImage(projectInfoDetail.getProject_logo(), ivProjectDetailLogo, options, null);
				tvProjectDetailContent.setText((null == projectInfoDetail.getProject_describe()) ? "" : Html.fromHtml(projectInfoDetail.getProject_describe())) ;
			}
			
		} else if (3 == PAGE_FALG ) {
			
		} else if (4 == PAGE_FALG) {
			
		} 

	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		System.out.println("flag----" + flag);
		switch (flag) {
		case Constant.REQUESTCODE.PROJECT_GET_ROW_REQUEST:
			if (param.length > 0 && param[1] != null
			&& param[1] instanceof String) {
				String jsondata = param[1].toString();
//				System.out.println(jsondata);
				projectInfoDetail = JSONParser.parseProjectInfo(jsondata) ;
				initData() ;
//				System.out.println("---" + projectInfoDetail);
			}
			break;
			//yuanqk added
		case REQUEST_DEFAULT_ADDR:
			if (param.length > 0 && param[1] != null
			&& param[1] instanceof String) {
		String data = param[1].toString();
		Log.d("yuanqikai", "yuanqikai 请求数据 = " + data);
		try {
			JSONObject json = new JSONObject(data);
			int status = json.getInt("ret");
			if (status == 0) {
				JSONObject jsonmode = json.getJSONObject("content");
				Log.d("yuanqikai", "yuanqikai jsonmode" + jsonmode.toString());
				if(jsonmode.has("default_address")){
					//没有默认地址
					startActivityByContent(this, MyAccoAddressIndexActivity.class);
				}else {
					if (jsonmode != null && jsonmode.length() > 0) {
						address.setId(jsonmode.getString("id"));
						address.setName(jsonmode.getString("rname"));
						address.setPhoneNum(jsonmode.getString("mobile"));
						address.setPostcode(jsonmode.getString("zipcode"));
						address.setProvince_id(jsonmode
								.getString("province_id"));
						address.setCity_id(jsonmode.getString("city_id"));
						address.setArea_id(jsonmode.getString("area_id"));
						address.setProvince_name(jsonmode.getString("province"));
						address.setCity_name(jsonmode.getString("city"));
						address.setArea_name(jsonmode.getString("area"));
						address.setAddress(jsonmode.getString("address"));
						//跳到购买页面
						dellgoodsBuybutton(address);
					}
				}
			} else {
				//自己写地址
				startActivityByContent(this, MyAccoAddressIndexActivity.class);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
			break;
			
		default:
			break;
		}
		
	}
	
	public void dellgoodsBuybutton(AddressInfo address) {
		GoodsBuyEntity entity = new GoodsBuyEntity();
		entity.setAddress(address.getProvince_name()+address.getCity_name()+address.getArea_name()+address.getAddress());// 从地址栏返回
		entity.setPersonName(address.getName());// 从地址栏返回
		entity.setPhoneNum(address.getPhoneNum());// 从地址栏返回

		Intent intent = new Intent(this, ProjectBuyActivity.class);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("entity", entity);
		bundle.putString("reward_money", ""+projectInfoDetail.getReward_money());
		bundle.putString("reward_return_days", ""+projectInfoDetail.getReward_return_days());
		bundle.putString("reward_content", projectInfoDetail.getReward_content());
		bundle.putString("reward_post_free", ""+projectInfoDetail.getReward_post_free());
		intent.putExtras(bundle);
		startActivity(intent);
		return;
	}
	
	public void startActivityByContent(Context context, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		intent.putExtra("from", "goods_buy");
		startActivityForResult(intent, REQUEST_NEW_ADDR);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.topic_content_comment_publish_but:
//			Intent intent = new Intent(this, PublishComment.class);
//			startActivity(intent);
//			break;
			
		case R.id.header_left_tv :
		case R.id.header_pre_iv :
			if (1 == PAGE_FALG) {
				finish();
			} else if (2 == PAGE_FALG) {
				initContent();
			} else if (3 == PAGE_FALG) {
				initContent();
			}
			break ;
			
		case R.id.project_content_button_no_join:
			getDefaultAddr();
			break ;
//		case R.id.project_content_footer_iv_back:  	
//			System.out.println("back");
//			
//			if(1 == PAGE_FALG ) {
//				finish() ;
//			} else if( 2 == PAGE_FALG ) {
//				initContent() ;
//			} else if( 3 == PAGE_FALG ) {
//				initContent() ;
//			}
			
//			break;
//		case R.id.project_content_footer_iv_collect:	//我的收藏
//			System.out.println("collect");
//			break;
//		case R.id.project_content_footer_iv_menu:   //通用的快捷方式
//			System.out.println("menu");
//			showBottomMenuDialog() ;
//			break;
			
		case R.id.project_content_type_rel_hall : //case R.id.project_content_goto_hall :	//部落大厅
			System.out.println("hall");
//			initHall() ;
			//TODO
			break ;
		case R.id.project_content_type_rel : //case R.id.project_content_goto_pro_detail:     //项目详情
			System.out.println("detail");
			initDetail() ;
			break;
		case R.id.project_content_renshuss_rel : //case R.id.project_content_goto_videos:    // 宣传视频
			System.out.println("videos");
			//宣传视频：创建者提交关于该项目的相关视频链接，点击后跳转至手机浏览器打开相关视频地址
			break;
		case R.id.project_content_xiangmu_chengyuan_rel : //case R.id.project_content_goto_xiangmu_chengyuan:   //项目成员
			System.out.println("members");
			initMembers() ;
			//TODO
			break;
		case R.id.project_content_type_rel_pro_product : //case R.id.project_content_goto_pro_product : 	//项目产品
			System.out.println("product"); 
			//TODO
			break ;

		case R.id.project_members_iv_search :     
			filter_level_2 = etFilter.getText().toString() ;
			System.out.println("fileter_level_1--->" + filter_level_1 + "---filter_level_2--->" + filter_level_2); 
			break ;
			
		case R.id.topic_hall_rl_manager_info :
			Util.toastInfo(ProjectContentActivity.this, "主持中的讨论") ;
			break ;
		case R.id.topic_hall_rl_join_info :
//			Util.toastInfo(ProjectContentActivity.this, "待参与信息") ;
			UIHelper.showDiscussJoinActivity(ProjectContentActivity.this, proID) ;
			break ;
		case R.id.topic_hall_rl_create_info :
			Util.toastInfo(ProjectContentActivity.this, "我的信息") ;
			break ;
		}
	}
	//added by yuanqk
public void getDefaultAddr() {
		
		if (goodsAPI == null) {
			goodsAPI = new HomeIndexGoodsAPI();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();
		SharedPreferences spf = getSharedPreferences("login_user", 0);
		String openID = spf.getString("open_id", "0");
		String token = spf.getString("token", "0");
		paramsMap.put("open_id", openID);
		paramsMap.put("token", token);
		Log.d("open_id", "open_id = " + openID);
		Log.d("token", "token = " + token);
		goodsAPI.getDefaultAddr(paramsMap, this, REQUEST_DEFAULT_ADDR);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		UserInfo userinfo = userinfos.get(position) ;
		Util.toastInfo(this, userinfo.getUsername()) ;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
            int position, long id) {
		// TODO Auto-generated method stub
		filter_level_1 = parent.getItemAtPosition(position).toString(); 
		Util.toastInfo(this, filter_level_1) ;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void showBottomMenuDialog(){
		Intent intent=new Intent(ProjectContentActivity.this,BottomRightOrLeftDialog.class);
		TopDialogInfo info=new TopDialogInfo();
		info.setDirection(DialogConstant.BRIGHT);
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.FOOTER_FAST_MENU);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == DialogConstant.FOOTER_FAST_MENU) {

			if (resultCode == DialogConstant.TOPDIALOG_ONE) {
				Util.toastInfo(ProjectContentActivity.this, "首页") ;
			} else if (resultCode == DialogConstant.TOPDIALOG_TWO) {
				Util.toastInfo(ProjectContentActivity.this, "需求大厅") ;
			} else if (resultCode == DialogConstant.TOPDIALOG_THREE) {
				Util.toastInfo(ProjectContentActivity.this, "分类搜索") ;
			} else if (resultCode == DialogConstant.TOPDIALOG_FOUR) {
				Util.toastInfo(ProjectContentActivity.this, "讨论大厅") ;
			} else if (resultCode == DialogConstant.TOPDIALOG_FIVE) {
				Util.toastInfo(ProjectContentActivity.this, "个人中心") ;
			}

		}
	}

	@Override
	protected void onStart() {
		super.onStart() ;
		System.out.println("onstart");
	}
	
	@Override
	protected void onPause() {
		super.onPause() ;
		System.out.println("onPause");
	}
	
	@Override
	protected void onResume() {
		super.onResume() ;
		System.out.println("onResume");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart() ;
		System.out.println("onRestart");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy() ;
		System.out.println("onDestroy");
	}

}
