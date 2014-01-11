package com.sanxian.sxzhuanhuan.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.MainActivity;
import com.sanxian.sxzhuanhuan.WelViewPagerActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.function.login.RegisterActivity;
import com.sanxian.sxzhuanhuan.function.login.RegisterStep2Activity;
import com.sanxian.sxzhuanhuan.function.login.RegisterStep3Activity;
import com.sanxian.sxzhuanhuan.function.login.RegisterSuccessActivity;
import com.sanxian.sxzhuanhuan.function.login.SearchPwdActivity;
import com.sanxian.sxzhuanhuan.function.sort.SearchHistoryActivity;
import com.sanxian.sxzhuanhuan.function.sort.SearchResultActivity;
import com.sanxian.sxzhuanhuan.function.sort.SortDetailActivity;
import com.sanxian.sxzhuanhuan.function.sort.SortFilterActivity;

/**   
 * @Title: UIHelper.java 
 * @Package com.sanxian.sxzhuanhuan.common 
 * @Description: UI辅助类---一般用于显示其他的Activity
 * @author zhangfl@sanxian.com
 * @date 2014-1-7 下午1:47:15 
 * @version V1.0   
 */
public class UIHelper {
	/**
	 * 设置部分字体颜色：主要是用在注册页面
	 * @param view  载体
	 * @param step1 注册的第一步文字
	 * @param step2 注册的第二步文字
	 * @param step3 注册的第三步文字
	 * @param color 颜色
	 * @param flag 注册步骤标志
	 */
	public static void setTextColor(TextView view , String step1 , String step2 , String step3 , String color , int flag) {
		switch (flag) {
			case Constant.RegisterStep.STEP1:
				view.setText(Html.fromHtml("<font color='" + color + "'>" + step1 + "</font>" + step2 + step3));
				break;
			case Constant.RegisterStep.STEP2:
				view.setText(Html.fromHtml(step1 + "<font color='" + color + "'>" + step2 + "</font>" + step3));
				break;
			case Constant.RegisterStep.STEP3:
				view.setText(Html.fromHtml(step1 + step2 + "<font color='" + color + "'>" + step3 + "</font>"));
				break;
		}

	}
	
	/**
	 * 进入到欢迎页面
	 * @param activity
	 */
	public static void showWelcomingActivity(Activity activity) {
		Intent intent = new Intent(activity , WelViewPagerActivity.class) ;
		activity.startActivity(intent) ;
		activity.finish() ;
	}
	/**
	 * 进入到主页面
	 * @param activity
	 */
	public static void showMainActivity(Activity activity) {
		Intent intent = new Intent(activity , MainActivity.class) ;
		activity.startActivity(intent) ;
		activity.finish() ;
	}
	
	/**
	 * 进入登陆页
	 * @param context
	 */
	public static void showLoginActivity(Context context) {
		Intent intent = new Intent(context , LoginActivity.class) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		context.startActivity(intent) ;
	}
	
	/**
	 * 进入登陆页
	 * @param activity  当前的activity
	 */
	public static void showLoginActivity(Activity activity ) {
		Intent intent = new Intent(activity , LoginActivity.class) ;
		activity.startActivityForResult(intent, Constant.REQUEST_LOGIN_CODE) ;
		activity.finish() ;
	}
	
	/**
	 * 进入到注册页面
	 * @param activity
	 */
	public static void showRegisterActivity(Activity activity) {
		Intent intent = new Intent(activity , RegisterActivity.class) ;
		activity.startActivityForResult(intent, 1) ;
		activity.finish() ;  //去掉好些....
	}
	
	public static void showRegisterStep2Activity(Activity activity , String phone) {
		Intent intent = new Intent(activity , RegisterStep2Activity.class) ;
		intent.putExtra("phone_number", phone) ;
		activity.startActivity(intent) ;
		activity.finish() ;
	}
	
	public static void showRegisterStep3Activity(Activity activity , String phone) {
		Intent intent = new Intent(activity , RegisterStep3Activity.class) ;
		intent.putExtra("phone_number", phone) ;
		activity.startActivity(intent) ;
		activity.finish() ;
	}
	
	public static void showRegisterSuccessActivity(Activity activity) {
		Intent intent = new Intent(activity , RegisterSuccessActivity.class) ;
		activity.startActivity(intent) ;
		activity.finish() ;
	}
	
	/**
	 * 进入到找回密码界面
	 * @param activity
	 */
	public static void showSearchPwdActivity(Activity activity) {
		Intent intent = new Intent(activity , SearchPwdActivity.class) ;
		activity.startActivity(intent) ;
		activity.finish() ;
	}
	
	/**
	 * 进入到分类详情页
	 * @param context 上下文，及前一个Activity
	 * @param sortParent 大分类
	 * @param sortChild  大分类下的子分类
	 */
	public static void showSortDetailActivity(Context context , String sortParent , String sortChild){
		Intent intent = new Intent(context , SortDetailActivity.class) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		intent.putExtra("sort_parent", sortParent) ;
		intent.putExtra("sort_child", sortChild) ;
		context.startActivity(intent) ;
	}
	
	/**
	 * 进入搜索历史记录
	 * @param context
	 */
	public static void showSearchHistoryActivity(Context context){
		Intent intent = new Intent(context , SearchHistoryActivity.class) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		context.startActivity(intent) ;
	}
	
	/**
	 * 进入搜索结果页面
	 * @param context
	 * @param sortParent
	 * @param sortChild
	 */
	public static void showSearchResultActivity(Context context , String sortParent , String sortChild) {
		Intent intent = new Intent(context , SearchResultActivity.class) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		intent.putExtra("sort_parent", sortParent) ;
		intent.putExtra("sort_child", sortChild) ;
		context.startActivity(intent) ;
	}
	
	/**
	 * 筛选页面
	 * @param context
	 */
	public static void showSortFilterActivity(Context context ) {
		Intent intent = new Intent(context , SortFilterActivity.class) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		context.startActivity(intent) ;
	}
	
	/**
	 * 判断是否符合电话号码规则
	 * @param telephone
	 * @return
	 */
	public static boolean isRealTelephone(String telephone) {
		if(telephone.startsWith("1"))
			return true ;
		return false ;
	}
	
	/**
	 * 检查验证码是否有效
	 * @param code
	 * @return
	 */
	public static int checkVertifyCode(String code) {
		//TODO
		return Constant.VertifyCode.VC_OK ;
	}
	
	/**
	 * 点击注册的事件流程
	 * @param accountName
	 * @param password
	 * @param password2
	 * @return 
	 */
	public static int register(String accountName , String password , String password2) {
		if("".equals(password2) || null == password2) 
			return Constant.RegisterStatus.REGISTER_PWD_NULL ;
		else if(password.equals(password2)) 
			return Constant.RegisterStatus.REGISTER_OK ;
		else return Constant.RegisterStatus.REGISTER_PWD_WRONG ;
	}
}
