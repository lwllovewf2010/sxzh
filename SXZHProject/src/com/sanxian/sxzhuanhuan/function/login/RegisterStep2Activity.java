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
 * @Title: RegisterStep2Activity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-10 下午1:41:44 
 * @version V1.0   
 */
public class RegisterStep2Activity extends BaseActivity implements OnClickListener{
	
	private CommonTitle conTitle = null ;
	private TextView tvVertifyInfo = null ; 
	private EditText etVertifyCode = null ;
	private Button btnVertifyCode = null ;
	
	private String phone = "" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.register_second) ;
		Intent intent = getIntent() ;
		if(null != intent) {
			phone = intent.getStringExtra("phone_number") ;
		}
		
		init() ;
		conTitle.show(true, "返回", true, "注册", false, "") ;
	}
	
	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		tvVertifyInfo = (TextView) findViewById(R.id.register_tv_vertify_info) ;
		etVertifyCode = (EditText) findViewById(R.id.register_et_vertify_code) ;
		btnVertifyCode = (Button) findViewById(R.id.register_btn_cimmit_vertiry_code) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		btnVertifyCode.setOnClickListener(this) ;
		
		tvVertifyInfo.setText(tvVertifyInfo.getText().toString() + phone) ;
		UIHelper.setTextColor((TextView)findViewById(R.id.register_tv_step_second) , getResources().getString(R.string.reg_step_first), 
				getResources().getString(R.string.reg_step_second) ,
				getResources().getString(R.string.reg_step_third) ,"green" , Constant.RegisterStep.STEP2) ;
		
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
				UIHelper.showRegisterActivity(RegisterStep2Activity.this) ;
				break ;
				
			case R.id.register_btn_cimmit_vertiry_code :
				System.out.println("RegisterActivity" + "reg");
				String vertifyCode = etVertifyCode.getText().toString() ;
				int codeFlag = UIHelper.checkVertifyCode(vertifyCode) ;
				switch (codeFlag) {
					case Constant.VertifyCode.VC_OK:
						UIHelper.showRegisterStep3Activity(RegisterStep2Activity.this , phone) ;
						break;
					case Constant.VertifyCode.VC_EXPIRED :
						Util.toastInfo(RegisterStep2Activity.this, getResources().getString(R.string.warning_vertify_code_expired)) ;
						break ;
					case Constant.VertifyCode.VC_WRONG :
						Util.toastInfo(RegisterStep2Activity.this, getResources().getString(R.string.warning_vertify_code_wrong)) ;
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
