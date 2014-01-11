package com.sanxian.sxzhuanhuan.function.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;

/**   
 * @Title: SearchPwdActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-2 下午2:35:23 
 * @version V1.0   
 */
public class SearchPwdActivity extends BaseActivity implements OnClickListener{
	private CommonTitle conTitle = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.search_password) ;
		
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		conTitle.show(true, "取消", true, "找回密码", false, "") ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
			case R.id.title_btn_left:
				System.out.println("-------------" + conTitle.btnLeft.getText().toString());
				break;
	
			default:
				break;
		}
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}
}
 