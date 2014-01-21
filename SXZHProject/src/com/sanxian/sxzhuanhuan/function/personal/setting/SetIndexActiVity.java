package com.sanxian.sxzhuanhuan.function.personal.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 设置页面 joe
 * @author Administrator
 *
 */
public class SetIndexActiVity extends BaseActivity implements OnClickListener{
	
	private RelativeLayout image_layout;//分享
    private RelativeLayout privacy_layout;//意见反馈
    private RelativeLayout new_project_layout;//关于
	private ImageView set_image; //设置仅wifi下显示图片
	private ImageView set_privacy;//设置公开个人信息
	private ImageView set_new_project;//新项目每日提醒
    private RelativeLayout share_layout;//分享
    private RelativeLayout feedback_layout;//意见反馈
    private RelativeLayout about_layout;//关于
    private RelativeLayout help_layout;//帮助信息
    private Button exit_btn;//退出
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_index);
		initView();
		initListener();
	}
	
	/**
	 * 初始化控件
	 */
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		super.displayRight();
		super.setTitle("设置");
		image_layout = (RelativeLayout)findViewById(R.id.image_layout);
		privacy_layout = (RelativeLayout)findViewById(R.id.privacy_layout);
		new_project_layout = (RelativeLayout)findViewById(R.id.new_project_layout);
		set_image = (ImageView)findViewById(R.id.set_image);
		set_privacy = (ImageView)findViewById(R.id.set_privacy);
		set_new_project = (ImageView)findViewById(R.id.set_new_project);
		share_layout = (RelativeLayout)findViewById(R.id.share_layout);
		feedback_layout = (RelativeLayout)findViewById(R.id.feedback_layout);
		about_layout = (RelativeLayout)findViewById(R.id.about_layout);
		help_layout = (RelativeLayout)findViewById(R.id.help_layout);
		exit_btn = (Button)findViewById(R.id.exit_btn);
		
	}

	/**
	 * 注册监听
	 */
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		image_layout.setOnClickListener(this);
		privacy_layout.setOnClickListener(this);
		new_project_layout.setOnClickListener(this);
		set_image.setOnClickListener(this);
		set_privacy.setOnClickListener(this);
		set_new_project.setOnClickListener(this);
		share_layout.setOnClickListener(this);
		feedback_layout.setOnClickListener(this);
		about_layout.setOnClickListener(this);
		help_layout.setOnClickListener(this);
		exit_btn.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_layout:
			break;
		case R.id.privacy_layout:
			break;
		case R.id.new_project_layout:
			break;
		case R.id.title_Left:
			finish();
			break;
		case R.id.set_image:
         Util.toastInfo(this, "仅wifi下显示图片");
			break;
		case R.id.set_privacy:
			Util.toastInfo(this, "个人信息");
			break;
		case R.id.set_new_project:
			Util.toastInfo(this, "项目提醒");
			break;
		case R.id.share_layout:
			Util.toastInfo(this, "分享");
			break;
		case R.id.feedback_layout:
			Util.toastInfo(this, "意见反馈");
	        break;
		case R.id.about_layout:
			Util.toastInfo(this, "关于");
			break;
		case R.id.help_layout:
			Util.toastInfo(this, "帮助");
			break;
		case R.id.exit_btn:
			Util.toastInfo(this, "退出");
			clearUserData() ;
			
//			SApplication.getInstance().exit() ; //
			break;
		default:
			break;
		}
	}
	
	/**
	 * 退出应用之前清除掉用户登录的相关信息
	 */
	private void clearUserData() {

		System.out.println("-------clear user data !!!!----------");
		SharedPreferences spf = getSharedPreferences("login_user", 0) ;
		SharedPreferences.Editor editor = spf.edit() ;
		editor.putString("open_id", "") ;
		editor.putString("token", "" );
		editor.commit() ;
	}

}
