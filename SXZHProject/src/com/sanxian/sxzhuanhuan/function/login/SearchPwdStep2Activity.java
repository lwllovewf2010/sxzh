package com.sanxian.sxzhuanhuan.function.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: SearchPwdStep2Activity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-11 上午9:58:23 
 * @version V1.0   
 */
public class SearchPwdStep2Activity extends BaseActivity implements OnClickListener {
	private CommonTitle conTitle = null ;
	private EditText etPassword = null ;
	private EditText etPassword2 = null ;
	private Button btnCommit = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.search_password_second) ;
		
		init() ;
		conTitle.show(true, "返回", true, "找回密码", false, "") ;
		
	}
	
	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		etPassword = (EditText) findViewById(R.id.search_pwd_et_new_password) ;
		etPassword2 = (EditText) findViewById(R.id.search_pwd_et_new_password_commit) ;
		btnCommit = (Button) findViewById(R.id.search_pwd_btn_change_password) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		btnCommit.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
			case R.id.title_btn_left:
				System.out.println("-------------" + conTitle.btnLeft.getText().toString());
				UIHelper.showSearchPwdActivity(SearchPwdStep2Activity.this) ;
				break;
			case R.id.search_pwd_btn_change_password :
				System.out.println("change password");
				changePassword() ;
				break ;
		}
	}
	
	private void changePassword() {
		String password1 = etPassword.getText().toString() ;
		String password2 = etPassword2.getText().toString() ;
		if( !UIHelper.checkPasswordEffect(password1) ) {
			Util.toastInfo(SearchPwdStep2Activity.this, getResources().getString(R.string.search_pwd_warning_pwd_regular_wrong)) ;
		} else if ( !password1.equals(password2)) {
			Util.toastInfo(SearchPwdStep2Activity.this, getResources().getString(R.string.search_pwd_warning_pwd_not_same)) ;
		} else {
			UIHelper.showLoginActivity(SearchPwdStep2Activity.this) ;
		}
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}



}
 