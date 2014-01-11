package com.sanxian.sxzhuanhuan.function.sort.slidingmenu;

import android.os.Bundle;
import android.view.Window;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseFragmentActivity;

/**   
 * @Title: BaseFragmentActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort.slidingmenu
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-6 下午1:47:15 
 * @version V1.0   
 */
public class BFragmentActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
//		MobclickAgent.onError(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		MobclickAgent.onResume(this);
	}
	
	public void finish()
	{
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	public void defaultFinish()
	{
		super.finish();
	}
}
