package com.sanxian.sxzhuanhuan.function.personal;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.function.personal.microbank.MicroBankIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.microworld.MicroWorldIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccountActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccountInfoActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.PersonInfoActivity;
import com.sanxian.sxzhuanhuan.function.personal.mycollection.MyCollectionIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myfriends.MyFriendsIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myorder.MyOrderIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.myproject.MyProjectIndexActivity;
import com.sanxian.sxzhuanhuan.function.personal.setting.SetIndexActiVity;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 个人中心首页 joe
 * @author Administrator
 *
 */
public class PersonalIndex extends BaseFragment implements OnClickListener{
	
	private RelativeLayout preson_data_layout; //个人基本信息
	private RelativeLayout tiny_bank_layout;  //微银行
	private RelativeLayout my_project_layout;  //我的项目 
	private RelativeLayout my_tribe_layout;  //我的部落
	private RelativeLayout shopping_trolley_layout;  //我的购物车 
	private RelativeLayout my_order_layout;  //我的订单
	private RelativeLayout supermarket_layout; //微超市
	private RelativeLayout wxin_layout;  //微信
	private RelativeLayout collection_layout;  //我的收藏 
	private RelativeLayout friend_layout;  //我的好友
	private RelativeLayout blogger_layout;  //微博
	private RelativeLayout world_layout;  //微世界
	private RelativeLayout safety_instal_layout;  //安全设置
	private RelativeLayout exit_login_layout;  //退出登录
	
	private RelativeLayout already_login_layout,never_login_layout;//已登录,未登录
	private TextView person_login;//登录
	private boolean is_login = false;//是否登录
	private TextView is_certify;//是否认证
	
	private ImageView preson_avatar;
	private TextView username;//用户名
	private TextView phone;//手机号码
	private TextView company;//公司名称
	private TextView position;//职位
	private TextView project;//我的项目数
	private TextView tribe;//我的部落数
	private TextView article;//我的购物车数
	private TextView order;//我的订单数
	private TextView gold_coins_sum;//能量币
	
	private CommonAPI api = null;
    private final int GETPRESONINDEX = 22;
	private Context context;

	private DisplayImageOptions options;
	protected ImageLoader imageLoader;
	
	public PersonalIndex() {
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
		View view = inflater.inflate(R.layout.presonal_index, container,false);
		initView(view);
		initListener();
		intImageUtil();
		initData();
		return view;
	}
    
	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		 setTitle("个人中心");
	     displayLeft();
	     setRight("设置");
	     preson_data_layout = (RelativeLayout)view.findViewById(R.id.preson_data_layout);
	     tiny_bank_layout = (RelativeLayout)view.findViewById(R.id.tiny_bank_layout);
	     my_project_layout = (RelativeLayout)view.findViewById(R.id.my_project_layout);
	     my_tribe_layout = (RelativeLayout)view.findViewById(R.id.my_tribe_layout);
	     shopping_trolley_layout = (RelativeLayout)view.findViewById(R.id.shopping_trolley_layout);
	     my_order_layout = (RelativeLayout)view.findViewById(R.id.my_order_layout);
	     supermarket_layout = (RelativeLayout)view.findViewById(R.id.supermarket_layout);
	     wxin_layout = (RelativeLayout)view.findViewById(R.id.wxin_layout);
	     collection_layout = (RelativeLayout)view.findViewById(R.id.collection_layout);
	     friend_layout = (RelativeLayout)view.findViewById(R.id.friend_layout);
	     blogger_layout = (RelativeLayout)view.findViewById(R.id.blogger_layout);
	     world_layout = (RelativeLayout)view.findViewById(R.id.world_layout);
	     safety_instal_layout = (RelativeLayout)view.findViewById(R.id.safety_instal_layout);
	     exit_login_layout = (RelativeLayout)view.findViewById(R.id.exit_login_layout);
	     
	     already_login_layout = (RelativeLayout)view.findViewById(R.id.already_login_layout);
	     never_login_layout = (RelativeLayout)view.findViewById(R.id.never_login_layout);
	     person_login = (TextView)view.findViewById(R.id.person_login);
	     
	     username = (TextView)view.findViewById(R.id.preson_name);
	     preson_avatar = (ImageView)view.findViewById(R.id.avatar_img);
	     is_certify = (TextView)view.findViewById(R.id.is_certify);
	     
	     phone = (TextView)view.findViewById(R.id.phone_content_tv);
	     company = (TextView)view.findViewById(R.id.company_tv);
	     position = (TextView)view.findViewById(R.id.position_tv);
	     project = (TextView)view.findViewById(R.id.my_project_sum);
	     tribe = (TextView)view.findViewById(R.id.my_tribe_sum);
	     article = (TextView)view.findViewById(R.id.my_article_sum);
	     order = (TextView)view.findViewById(R.id.my_order_sum);
	     gold_coins_sum = (TextView)view.findViewById(R.id.gold_coins_sum);
	}
	
	/**
	 * 初始化页面数据
	 * joe
	 */
	public void initData(){
		SharedPreferences spf = context.getSharedPreferences("login_user", 0) ;
		String open_id = spf.getString("open_id","");
		String token = spf.getString("token","");
		if("".equals(open_id) || "".equals(token)){
			never_login_layout.setVisibility(View.VISIBLE);
			already_login_layout.setVisibility(View.GONE);
			is_login = false;
			project.setVisibility(View.INVISIBLE);
			tribe.setVisibility(View.INVISIBLE);
			article.setVisibility(View.INVISIBLE);
			order.setVisibility(View.INVISIBLE);
		}else{
			never_login_layout.setVisibility(View.GONE);
			already_login_layout.setVisibility(View.VISIBLE);
			is_login = true;
			project.setVisibility(View.VISIBLE);
			tribe.setVisibility(View.VISIBLE);
			article.setVisibility(View.VISIBLE);
			order.setVisibility(View.VISIBLE);
		if(api == null){
			api = new CommonAPI();
		}
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",open_id);
		paramsmap.put("token",token);
		api.getPersonIndex(paramsmap, PersonalIndex.this, GETPRESONINDEX);
	 }
	}
	
	/**
	 * 初始化图片加载方法
	 * joe
	 */
	private void intImageUtil() {
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				//.showStubImage(R.drawable.denglu_morentouxiang)
				// .showImageForEmptyUri(R.drawable.denglu_morentouxiang)//uri为空的时候
				// .showImageOnFail(R.drawable.denglu_morentouxiang)//加载失败的时候
				//.cacheOnDisc()
		        .displayer(new RoundedBitmapDisplayer(110)).build();
	}

	/* (non-Javadoc)
	 * @see com.sanxian.sxzhuanhuan.common.BaseFragment#refresh(java.lang.Object[])
	 */
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case GETPRESONINDEX:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if(status == 0){
						
						JSONObject object = json.getJSONObject("content");
						String company_name = object.optString("company_name");
						String position_name = object.optString("place_name");
						String order_num = object.optString("my_order_num");
						String project_num = object.optString("my_project_num");
						String shopping_cart_num = object.optString("my_shopping_cart_num");
						String tribe_num = object.optString("my_tribe_num");
						String money = object.optString("money");
						String photo = object.optString("photo");
						String true_name = object.optString("true_name");
						String user_name = object.optString("user_name");
						String mobile = object.optString("mobile");
						if("null".equals(user_name)){
							user_name = "";
						}
						username.setText(user_name);
						if("null".equals(mobile)){
							mobile = "";
						}
						if(mobile.length() > 0){
							phone.setText(mobile.replace(mobile.subSequence(3,7), "XXXX"));
						}
						company.setText(company_name);
						position.setText(position_name);
						project.setText(project_num);
						tribe.setText(tribe_num);
						article.setText(shopping_cart_num);
						order.setText(order_num);
						gold_coins_sum.setText(money);
						if(!"null".equals(photo) && photo.length() > 0){
						imageLoader.displayImage(photo, preson_avatar, options);
						}
					}else if(status == 1001){
						Intent intent = new Intent(context, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else {
						Util.toastInfo(context, "请求失败");
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

	@Override
	public Context getContext() {
		return super.getContext();
	}
	
	/**
	 * 注册监听事件方法
	 */
	public void initListener(){
//		preson_data_layout.setOnClickListener(this);
		preson_avatar.setOnClickListener(this);
		tiny_bank_layout.setOnClickListener(this);
		my_project_layout.setOnClickListener(this);
		my_tribe_layout.setOnClickListener(this);
		shopping_trolley_layout.setOnClickListener(this);
		my_order_layout.setOnClickListener(this);
		supermarket_layout.setOnClickListener(this);
		wxin_layout.setOnClickListener(this);
		collection_layout.setOnClickListener(this);
		friend_layout.setOnClickListener(this);
		blogger_layout.setOnClickListener(this);
		world_layout.setOnClickListener(this);
		button_right.setOnClickListener(this);
		safety_instal_layout.setOnClickListener(this);
		exit_login_layout.setOnClickListener(this);
		person_login.setOnClickListener(this);
	}
  
	/**
	 * 所有的单击事件处理方法
	 */
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.avatar_img:
			if(is_login == true){
				Intent presonintent = new Intent(context,PersonInfoActivity.class);
				startActivity(presonintent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.person_login:
			Intent loginintent = new Intent(context,LoginActivity.class);
			startActivityForResult(loginintent,Constant.REQUEST_LOGIN_CODE);
			break;
		case R.id.tiny_bank_layout:
			if(is_login == true){
				intent.setClass(context, MicroBankIndexActivity.class);
				startActivity(intent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.my_project_layout:
			if(is_login == true){
				intent.setClass(context, MyProjectIndexActivity.class);
				startActivity(intent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.my_tribe_layout:
			if(is_login == true){
				Util.toastInfo(context, "我的部落");
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.shopping_trolley_layout:
			if(is_login == true){
				Util.toastInfo(context, "我的购物车");
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.my_order_layout:
			if(is_login == true){
				intent.setClass(context, MyOrderIndexActivity.class);
				startActivity(intent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.supermarket_layout:
			if(is_login == true){
				Util.toastInfo(context, "微超市");
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.wxin_layout:
			Util.toastInfo(context, "微信");
			break;
		case R.id.collection_layout:
			if(is_login == true){
				intent.setClass(context, MyCollectionIndexActivity.class);
				startActivity(intent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.friend_layout:
			if(is_login == true){
				intent.setClass(context, MyFriendsIndexActivity.class);
				startActivity(intent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.blogger_layout:
			Util.toastInfo(context, "微博");
			break;
		case R.id.world_layout:
			Util.toastInfo(context, "微世界");
			intent.setClass(context, MicroWorldIndexActivity.class);
			startActivity(intent);
			break;
		case R.id.title_right:
			Intent setintent = new Intent(context,SetIndexActiVity.class);
			startActivity(setintent);
			break;
		case R.id.safety_instal_layout:
//			Util.toastInfo(context, "安全设置");
			if(is_login == true){
				Intent safetyintent = new Intent(context,MyAccountInfoActivity.class);
				startActivity(safetyintent);
			}else if(is_login == false){
				Util.toastInfo(context, "请先登录");
			}
			break;
		case R.id.exit_login_layout:
			Util.toastInfo(context, "退出登录");
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("----PersonalIndex     onStart ------");
		initData();
		}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Constant.RESULT_LOGIN_CODE){
			initData();
		}
	}

}
