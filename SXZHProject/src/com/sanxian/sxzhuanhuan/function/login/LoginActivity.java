package com.sanxian.sxzhuanhuan.function.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;

/**   
 * @Title: LoginActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login
 * @Description: 用户登录
 * @author zhangfl@sanxian.com 
 * @date 2014-1-2 上午11:36:31 
 * @version V1.0   
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
	
	//布局中的相关控件
	private CommonTitle conTitle = null ;
	private EditText etAccount = null ;
	private EditText etPassword = null ;
	private TextView tvRegister = null ;
	private ImageView ivForgetPwd = null ;
	
	//相关变量
	private String strAccount = "" ;
	private String strPassword = "" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.login) ;
		
		init() ;
		conTitle.show(true, "取消", true, "登录", true, "登录") ;
	}
	
	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		etAccount = (EditText) findViewById(R.id.login_et_account) ;
		etPassword = (EditText) findViewById(R.id.login_et_password) ;
		ivForgetPwd = (ImageView) findViewById(R.id.login_iv_forget_pwd) ;
		tvRegister = (TextView) findViewById(R.id.login_tv_register) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		conTitle.btnRight.setOnClickListener(this) ;
		tvRegister.setOnClickListener(this) ;
		ivForgetPwd.setOnClickListener(this) ;
		
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int tag = v.getId() ;
		switch(tag) {
			case R.id.title_btn_left:
				setResult(Constant.LOGIN_CANCLE) ;
				finish() ;
				break ;
				
			case R.id.title_btn_right :
				strAccount = etAccount.getText().toString() ;
				strPassword = etPassword.getText().toString() ;
				Intent intent = new Intent() ;
				intent.putExtra("account", strAccount) ;
				intent.putExtra("password", strPassword) ;
//				setResult(Constant.LOGIN_OK, intent ) ;
				setResult(Constant.LOGIN_OK) ;
				finish() ;
				break ;
				
			case R.id.login_tv_register :
				System.out.println("------" + "register");
				UIHelper.showRegisterActivity(LoginActivity.this) ;
				break ;
				
			case R.id.login_iv_forget_pwd :
				System.out.println("-------" + "forget password");
				UIHelper.showSearchPwdActivity(LoginActivity.this) ;
				break ;
				
			default :
				
		}
		// Choreographer Skipped 44 frames!  The application may be doing too much work on its main thread.

	}
	
	/* (non-Javadoc)
	 * @see com.sanxian.sxzhuanhuan.common.BaseActivity#refresh(java.lang.Object[])
	 */
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}
	
}
