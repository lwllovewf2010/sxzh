package com.sanxian.sxzhuanhuan.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sanxian.sxzhuanhuan.MainActivity;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.TestAPIActivity;
import com.sanxian.sxzhuanhuan.WelViewPagerActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.discusshall.DiscussJoinActivity;
import com.sanxian.sxzhuanhuan.function.discusshall.DiscussManagerActivity;
import com.sanxian.sxzhuanhuan.function.discusshall.DiscussVoteActivity;
import com.sanxian.sxzhuanhuan.function.discusshall.DiscussVoteResultActivity;
import com.sanxian.sxzhuanhuan.function.discusshall.PubDiscussActivity;
import com.sanxian.sxzhuanhuan.function.discusshall.PubVoteActivity;
import com.sanxian.sxzhuanhuan.function.discusshall.TopicDetailActivity;
import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsContentActivity;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ProjectContentActivity;
import com.sanxian.sxzhuanhuan.function.login.InviteInfoActivity;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.function.login.RealAuthActivity;
import com.sanxian.sxzhuanhuan.function.login.RegisterActivity;
import com.sanxian.sxzhuanhuan.function.login.RegisterStep2Activity;
import com.sanxian.sxzhuanhuan.function.login.RegisterStep3Activity;
import com.sanxian.sxzhuanhuan.function.login.RegisterSuccessActivity;
import com.sanxian.sxzhuanhuan.function.login.SearchPwdActivity;
import com.sanxian.sxzhuanhuan.function.login.SearchPwdStep2Activity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.MyAccoCertifyNameActivity;
import com.sanxian.sxzhuanhuan.function.sort.SearchHistoryActivity;
import com.sanxian.sxzhuanhuan.function.sort.SearchResultActivity;
import com.sanxian.sxzhuanhuan.function.sort.SortCategoryActivity;
import com.sanxian.sxzhuanhuan.function.sort.SortDetailActivity;

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
	 * 
	 * @param view
	 *            载体
	 * @param step1
	 *            注册的第一步文字
	 * @param step2
	 *            注册的第二步文字
	 * @param step3
	 *            注册的第三步文字
	 * @param color
	 *            颜色
	 * @param flag
	 *            注册步骤标志
	 */
	public static void setTextColor(TextView view, String step1, String step2,
			String step3, String color, int flag) {
		switch (flag) {
		case Constant.RegisterStep.STEP1:
			view.setText(Html.fromHtml("<font color='" + color + "'>" + step1
					+ "</font>" + step2 + step3));
			break;
		case Constant.RegisterStep.STEP2:
			view.setText(Html.fromHtml(step1 + "<font color='" + color + "'>"
					+ step2 + "</font>" + step3));
			break;
		case Constant.RegisterStep.STEP3:
			view.setText(Html.fromHtml(step1 + step2 + "<font color='" + color
					+ "'>" + step3 + "</font>"));
			break;
		}

	}

	/**
	 * 进入到欢迎页面
	 * 
	 * @param activity
	 */
	public static void showWelcomingActivity(Activity activity) {
		Intent intent = new Intent(activity, WelViewPagerActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 进入到主页面
	 * 
	 * @param activity
	 */
	public static void showMainActivity(Activity activity) {
		Intent intent = new Intent(activity, MainActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 进入登陆页
	 * 
	 * @param context
	 */
	public static void showLoginActivity(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 进入登陆页
	 * 
	 * @param activity
	 *            当前的activity
	 */
	public static void showLoginActivity(Activity activity) {
		Intent intent = new Intent(activity, LoginActivity.class);
		activity.startActivityForResult(intent, Constant.REQUEST_LOGIN_CODE);
		activity.finish();
	}

	/**
	 * 进入到如何被邀请信息页
	 * @param activity
	 */
	public static void showInviteInfoActivity(Activity activity) {
		Intent intent = new Intent(activity, InviteInfoActivity.class);
		activity.startActivity(intent) ;
	}
	
	/**
	 * 进入到注册页面
	 * 
	 * @param activity
	 */
	public static void showRegisterActivity(Activity activity) {
		Intent intent = new Intent(activity, RegisterActivity.class);
		activity.startActivityForResult(intent, 1);
		activity.finish(); // 去掉好些....
	}

	public static void showRegisterStep2Activity(Activity activity, String phone) {
		Intent intent = new Intent(activity, RegisterStep2Activity.class);
		intent.putExtra("phone_number", phone);
		activity.startActivity(intent);
		activity.finish();
	}
	
	public static void showRegisterStep2Activity(Activity activity, String phone , String username , String reference_open_id) {
		Intent intent = new Intent(activity, RegisterStep2Activity.class);
		intent.putExtra("phone_number", phone);
		intent.putExtra("username", username) ;
		intent.putExtra("reference_open_id", reference_open_id) ;
		activity.startActivity(intent);
		activity.finish();
	}

	public static void showRegisterStep3Activity(Activity activity, String phone) {
		Intent intent = new Intent(activity, RegisterStep3Activity.class);
		intent.putExtra("phone_number", phone);
		activity.startActivity(intent);
		activity.finish();
	}
	
	public static void showRegisterStep3Activity(Activity activity, String phone , String username , String reference_open_id) {
		Intent intent = new Intent(activity, RegisterStep3Activity.class);
		intent.putExtra("phone_number", phone);
		intent.putExtra("username", username) ;
		intent.putExtra("reference_open_id", reference_open_id) ;
		activity.startActivity(intent);
		activity.finish();
	}

	public static void showRegisterSuccessActivity(Activity activity) {
		Intent intent = new Intent(activity, RegisterSuccessActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 进入到实名认证页面
	 * 
	 * @param activity
	 */
	public static void showRealAuthActivity(Activity activity) {
		Intent intent = new Intent(activity, MyAccoCertifyNameActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}
	
	public static void showRealAuthActivity(Context activity) {
		Intent intent = new Intent(activity, MyAccoCertifyNameActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(intent);
	}

	/**
	 * 进入到找回密码界面
	 * 
	 * @param activity
	 */
	public static void showSearchPwdActivity(Activity activity) {
		Intent intent = new Intent(activity, SearchPwdActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	public static void showSearchPwdStep2Activity(Activity activity) {
		Intent intent = new Intent(activity, SearchPwdStep2Activity.class);
		activity.startActivity(intent);
		activity.finish();
	}
	
	/**
	 * 显示商品内容页
	 * @param context
	 */
	public static void showProductActivity(Context context) {
		Intent intent=new Intent(context, GoodsContentActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/**
	 * 显示分类页面
	 * 
	 * @param context
	 */
	public static void showCategoryActivity(Context context, String sortName ,String category , String id) {
		Intent intent = new Intent(context, SortCategoryActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("sort_name", sortName);
		intent.putExtra("parent_id", id) ;
		intent.putExtra("category_name", category);
		context.startActivity(intent);
	}

	/**
	 * 根据左边的行业分类，得到具体的分类
	 * 
	 * @param leftCategory
	 * @return
	 */
	public static List<Map<String, String>> showRightCategory(
			String leftCategory) {
		List<Map<String, String>> contents = new ArrayList<Map<String, String>>();
		return contents;
	}

	/**
	 * 进入到分类详情页
	 * 
	 * @param context
	 *            上下文，及前一个Activity
	 * @param sortParent
	 *            大分类
	 * @param sortChild
	 *            大分类下的子分类
	 */
	public static void showSortDetailActivity(Context context,
			String sortParent, String sortChild , String sortChildID) {
		Intent intent = new Intent(context, SortDetailActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("sort_parent", sortParent);
		intent.putExtra("sort_child", sortChild);
		intent.putExtra("sort_child_id", sortChildID) ;
		context.startActivity(intent);
	}

	/**
	 * 进入搜索历史记录
	 * 
	 * @param context
	 */
	public static void showSearchHistoryActivity(Context context ,String sortname ) {
		Intent intent = new Intent(context, SearchHistoryActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("sortname", sortname) ;
		context.startActivity(intent);
	}
	public static void showSearchHistoryActivity(Activity activity,
			String sortname) {
		Intent intent = new Intent(activity, SearchHistoryActivity.class);
		intent.putExtra("sortname", sortname) ;
		activity.startActivity(intent);
//		activity.finish() ;
	}

	/**
	 * 进入搜索结果页面
	 * 
	 * @param context
	 * @param sortParent
	 * @param sortChild
	 */
	public static void showSearchResultActivity(Context context,
			String sortParent, String sortChild) {
		Intent intent = new Intent(context, SearchResultActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("sort_parent", sortParent);
		intent.putExtra("sort_child", sortChild);
		context.startActivity(intent);
	}
	
	public static void showSearchResultActivity(Activity activity,
			String sortParent, String sortChild) {
		Intent intent = new Intent(activity, SearchResultActivity.class);
		intent.putExtra("sort_parent", sortParent);
		intent.putExtra("sort_child", sortChild);
		activity.startActivity(intent);
		activity.finish() ;
	}

	/**
	 * 进入到项目详情页面
	 * 
	 * @param context
	 */
	public static void showProjectDetailActivity(Context context) {
		Intent intent = new Intent(context, ProjectContentActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	public static void showProjectDetailActivity(Context context , String proID) {
		Intent intent = new Intent(context, ProjectContentActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("project_id", proID) ;
		context.startActivity(intent);
	}

	/**
	 * 判断是否符合电话号码规则
	 * 
	 * @param telephone
	 * @return true or false
	 */
	public static boolean isRealTelephone(String telephone) {
		if (telephone.startsWith("1") && telephone.length() == 11)
			return true;
		return false;
	}

	/**
	 * 判断该号码是否已经注册了
	 * 
	 * @param telephone
	 *            电话号码
	 * @return true or false
	 */
	public static boolean phoneAreRegisted(String telephone) {
		// TODO
		return true;
	}

	/**
	 * 判断该验证码是否已经失效
	 * 
	 * @param vertify_code
	 *            手机验证码
	 * @return false or true
	 */
	public static boolean vertifyCodeIsExpired(String vertify_code) {
		// TODO
		return false;
	}

	/**
	 * 检查验证码在规定时间内是否有效
	 * 
	 * @param code
	 * @return
	 */
	public static int checkVertifyCode(String code) {
		// TODO
		return Constant.VertifyCode.VC_OK;
	}

	/**
	 * 检查密码是否符合特定的规则
	 * 
	 * @param password
	 * @return
	 */
	public static boolean checkPasswordEffect(String password) {
		// TODO
		return true;
	}

	/**
	 * 点击注册的事件流程
	 * 
	 * @param accountName
	 * @param password
	 * @param password2
	 * @return
	 */
	public static int register(String accountName, String password,
			String password2) {
		if ("".equals(password2) || null == password2)
			return Constant.RegisterStatus.REGISTER_PWD_NULL;
		else if (password.equals(password2))
			return Constant.RegisterStatus.REGISTER_OK;
		else
			return Constant.RegisterStatus.REGISTER_PWD_WRONG;
	}

	public static int register(String accountName, String password) {
		if ("".equals(accountName) || null == accountName)
			return Constant.RegisterStatus.REGISTER_ACCOUNT_NULL;
		else if ("".equals(password) || null == password)
			return Constant.RegisterStatus.REGISTER_PWD_NULL;
		else
			return Constant.RegisterStatus.REGISTER_OK;
	}
	
	
	
	
	
	public static DisplayImageOptions setOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.index)
		.showImageForEmptyUri(R.drawable.index)
		.showImageOnFail(R.drawable.index)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
//		.displayer(new RoundedBitmapDisplayer(20))  
		.build();
		
//		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.index)// 加载失败的时候
//				.cacheOnDisc().build();
		
		return options ;
	}
	
	
	public static String getOpenID(Context context) {
		SharedPreferences spf = context.getSharedPreferences("login_user", 0) ;
		System.out.println("UIHElper:-----" + spf.getString("open_id", "") );
		return spf.getString("open_id", "") ;
	}
	
	/**
	 * 进入到会话详情页
	 * @param activity
	 * @param topicID
	 */
	public static void showTopicDetailActivity(Activity activity , String topicID) {
		Intent intent = new Intent(activity, TopicDetailActivity.class);
		intent.putExtra("topic_id", topicID) ;
		activity.startActivity(intent);
	}
	
	public static void showTopicDetailActivity(Context context , String topicID) {
		Intent intent = new Intent(context, TopicDetailActivity.class);
		intent.putExtra("topic_id", topicID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	
	/**
	 * 进入到待参与信息
	 * @param activity
	 * @param topicID
	 */
	public static void showDiscussJoinActivity(Activity activity , String projectID) {
		Intent intent = new Intent(activity, DiscussJoinActivity.class);
		intent.putExtra("project_id", projectID) ;
		activity.startActivity(intent);
	}
	
	public static void showDiscussJoinActivity(Context context , String projectID) {
		Intent intent = new Intent(context, DiscussJoinActivity.class);
		intent.putExtra("project_id", projectID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/**
	 * 进入到投票页
	 * @param activity
	 * @param topicID
	 */
	public static void showDiscussVoteActivity(Activity activity , String topicID , int flag) {
		Intent intent = new Intent(activity, DiscussVoteActivity.class);
		intent.putExtra("topic_id", topicID) ;
		intent.putExtra("choice_flag", flag) ;
		activity.startActivity(intent);
	}
	public static void showDiscussVoteActivity(Context context , String topicID , int flag) {
		Intent intent = new Intent(context, DiscussVoteActivity.class);
		intent.putExtra("topic_id", topicID) ;
		intent.putExtra("choice_flag", flag) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/**
	 * 进入到投票结果页
	 * @param activity
	 * @param topicID
	 */
	public static void showDiscussVoteResultActivity(Activity activity , String topicID , int flag) {
		Intent intent = new Intent(activity, DiscussVoteResultActivity.class);
		intent.putExtra("topic_id", topicID) ;
		intent.putExtra("choice_flag", flag) ;
		activity.startActivity(intent);
	}
	public static void showDiscussVoteResultActivity(Context context , String topicID , int flag) {
		Intent intent = new Intent(context, DiscussVoteResultActivity.class);
		intent.putExtra("topic_id", topicID) ;
		intent.putExtra("choice_flag", flag) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/**
	 * 进入到主持讨论页
	 * @param activity
	 * @param topicID
	 */
	public static void showDiscussManagerActivity(Activity activity , String projectID) {
		Intent intent = new Intent(activity, DiscussManagerActivity.class);
		intent.putExtra("project_id", projectID) ;
		activity.startActivity(intent);
	}
	
	public static void showDiscussManagerActivity(Context context , String projectID) {
		Intent intent = new Intent(context, DiscussManagerActivity.class);
		intent.putExtra("project_id", projectID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/**
	 * 进入讨论发布页
	 * @param activity
	 * @param topicID
	 */
	public static void showDiscussPubActivity(Activity activity , String projectID) {
		Intent intent = new Intent(activity, PubDiscussActivity.class);
		intent.putExtra("project_id", projectID) ;
		activity.startActivity(intent);
	}
	
	public static void showDiscussPubActivity(Context context , String projectID) {
		Intent intent = new Intent(context, PubDiscussActivity.class);
		intent.putExtra("project_id", projectID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 进入投票发布页
	 * @param activity
	 * @param topicID
	 */
	public static void showPubVoteActivity(Activity activity , String projectID) {
		Intent intent = new Intent(activity, PubVoteActivity.class);
		intent.putExtra("project_id", projectID) ;
		activity.startActivity(intent);
	}
	
	public static void showPubVoteActivity(Context context , String projectID) {
		Intent intent = new Intent(context, PubVoteActivity.class);
		intent.putExtra("project_id", projectID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	
	
	
	
	
	
	
	
	
	public static void showTestApiActivity(Activity activity) {
		Intent intent = new Intent(activity, TestAPIActivity.class);
		activity.startActivityForResult(intent, Constant.REQUEST_LOGIN_CODE);
		activity.finish();
	}
	
	
	/**
	 * 根据图片url得到相关图片
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static Bitmap getBitmap(String url) throws ClientProtocolException,
			IOException {
		InputStream in = HttpTool.getInputStream(url, null, HttpTool.GET);
		if (in != null) {
			Bitmap bm = BitmapFactory.decodeStream(in);
			return bm;
		}
		return null;
	}

	public static class HttpTool {
		public static final int GET = 1;
		public static final int POST = 2;

		public static InputStream getInputStream(String url,
				ArrayList<BasicNameValuePair> params, int method)
				throws ClientProtocolException, IOException {
			InputStream in = null;
			HttpEntity entity = getEntity(url, params, method);
			if (entity != null) {
				in = entity.getContent();
			}
			return in;
		}

		public static HttpEntity getEntity(String url,
				ArrayList<BasicNameValuePair> params, int method)
				throws ClientProtocolException, IOException {
			HttpEntity entity = null;
			HttpClient client = new DefaultHttpClient();
			HttpUriRequest request = null;
			StringBuffer sb = new StringBuffer(url);
			switch (method) {
			case GET:
				if (params != null && !params.isEmpty()) {
					for (BasicNameValuePair pair : params) {
						sb.append("?").append(pair.getName()).append("=")
								.append(pair.getValue()).append("&");
					}
					sb.deleteCharAt(sb.length() - 1);
				}
				request = new HttpGet(sb.toString());
				break;
			case POST:
				request = new HttpPost(sb.toString());
				((HttpPost) request).setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
				break;
			}
			request.addHeader("Pragma", "no-cache");
			request.addHeader("Cache-Control", "no-cache");
			request.addHeader("Cookie", "");
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				System.out.println("result-->" + result);
			}
			return entity;
		}

	}

}
