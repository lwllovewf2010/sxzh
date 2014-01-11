package com.sanxian.sxzhuanhuan;

import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**   
 * @Title: WelcomeActivity.java 
 * @Package com.sanxian.sxzhuanhuan 
 * @Description: 首次进入APP时展现的欢迎界面
 * @author zhangfl@sanxian.com 
 * @date 2014-1-2 上午11:16:31 
 * @version V1.0   
 */
public class WelcomeActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SharedPreferences spf = getSharedPreferences("run_app", 0) ;
		boolean firstRun = spf.getBoolean("first_run", true) ;
		
		if(firstRun) {
			SharedPreferences.Editor editor = spf.edit() ;
			editor.putBoolean("first_run", false) ;
			//延迟两秒后执行run方法中的页面跳转  
	        new Handler().postDelayed(new Runnable() {  
	            @Override  
	            public void run() {  
	            	UIHelper.showWelcomingActivity(WelcomeActivity.this) ;
	            }  
	        }, 2000);
	        editor.commit() ;
		} else {
			UIHelper.showMainActivity(WelcomeActivity.this) ;
		}
		
	}
}
