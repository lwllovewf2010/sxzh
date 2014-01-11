package com.sanxian.sxzhuanhuan.function.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: RegisterStep3Activity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-10 下午1:41:44 
 * @version V1.0   
 */
public class RegisterStep3Activity extends BaseActivity implements OnClickListener{
	
	private CommonTitle conTitle = null ;
	private EditText etAccount = null ;
	private EditText etPassword = null ;
	private EditText etPasswordCommit = null ;
	private Button btnRegister = null ;
	
	private String phone = "" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.register_third) ;
		
		Intent intent = getIntent() ;
		if(null != intent) {
			phone = intent.getStringExtra("phone_number") ;
		}
		
		init() ;
		conTitle.show(true, "返回", true, "注册", false, "") ;
	}
	
	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		etAccount = (EditText) findViewById(R.id.register_et_account) ;
		etPassword = (EditText) findViewById(R.id.register_et_set_password) ;
		etPasswordCommit = (EditText) findViewById(R.id.register_et_commit_password) ;
		btnRegister = (Button) findViewById(R.id.register_btn_register) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		btnRegister.setOnClickListener(this) ;
		
		UIHelper.setTextColor((TextView)findViewById(R.id.register_tv_step_third) , getResources().getString(R.string.reg_step_first), 
				getResources().getString(R.string.reg_step_second) ,
				getResources().getString(R.string.reg_step_third) ,"green" , Constant.RegisterStep.STEP3) ;
		
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
			case R.id.title_btn_left:
				UIHelper.showRegisterStep2Activity(RegisterStep3Activity.this, phone) ;
				break ;
				
			case R.id.register_btn_register :
				int flag = UIHelper.register(etAccount.getText().toString(), etPassword.getText().toString(), etPasswordCommit.getText().toString()) ;
				switch (flag) {
					case Constant.RegisterStatus.REGISTER_OK :
						UIHelper.showRegisterSuccessActivity(RegisterStep3Activity.this) ;
						break;
					case Constant.RegisterStatus.REGISTER_PWD_NULL :
						Util.toastInfo(RegisterStep3Activity.this, getResources().getString(R.string.warning_commit_pwd_is_null)) ;
						break ;
					case Constant.RegisterStatus.REGISTER_PWD_WRONG :
						Util.toastInfo(RegisterStep3Activity.this, getResources().getString(R.string.warning_pwd_not_same)) ;
						break ;
				}
				break ;
				
			default:
				break;
		}
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
