package com.sanxian.sxzhuanhuan.function.login;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
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
	
	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	private CommonHeader conHeader = null ;
	private EditText etAccount = null ;
	private String phone = "" ;
	private String username = "" ;
	private String reference_open_id = "" ;
	private EditText etPassword = null ;
//	private EditText etPasswordCommit = null ;
	private Button btnRegister = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.register_third) ;
		
		Intent intent = getIntent() ;
		if(null != intent) {
			phone = intent.getStringExtra("phone_number") ;
			username = intent.getStringExtra("username") ;
			reference_open_id = intent.getStringExtra("reference_open_id") ;
		}
		
		init() ;
		conHeader.show(true, true , "返回", true, "免费注册", false , "" , false) ;
	}
	
	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>() ;
		
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		etAccount = (EditText) findViewById(R.id.register_et_account) ;
		etPassword = (EditText) findViewById(R.id.register_et_set_password) ;
		btnRegister = (Button) findViewById(R.id.register_btn_register) ;
		
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		btnRegister.setOnClickListener(this) ;
		
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
//			case R.id.title_btn_left:
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				UIHelper.showRegisterStep2Activity(RegisterStep3Activity.this, phone, username, reference_open_id) ;
				break ;
				
			case R.id.register_btn_register :
				//{"user_name":"106297632@qq.com","mobile":"13811689766","password":"abcd123"}}
//			"params":{"user_name":"zhangsan","mobile":"12345678900","passwor":"123456","reference_open_id":"5_1278_539947"}}
				input.put("user_name", etAccount.getText().toString() ) ; //"106297632@qq.com") ;
				input.put("mobile", phone ) ; // "13811689766") ;
				input.put("password", etPassword.getText().toString() ) ; //"abcd123") ;
				input.put("reference_open_id", reference_open_id) ;
				
				int flag = UIHelper.register(etAccount.getText().toString(), etPassword.getText().toString()) ;
				switch (flag) {
					case Constant.RegisterStatus.REGISTER_OK :
						api.register(input, this, Constant.REQUESTCODE.REGISTER_REQUEST) ;
						break;
					case Constant.RegisterStatus.REGISTER_PWD_NULL :
						Util.toastInfo(RegisterStep3Activity.this, "密码不能为空") ;
						break ;
					case Constant.RegisterStatus.REGISTER_ACCOUNT_NULL :
						Util.toastInfo(RegisterStep3Activity.this, "用户名不能为空") ;
						break ;
				}
				break ;
				
			default:
				break;
		}
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		//{"action":"user_register","type":"submit_register","mcr":0,"params":{"user_name":"106297632@qq.com","mobile":"13811689766","password":"abcd123"}}
		switch (flag) {
			case Constant.REQUESTCODE.REGISTER_REQUEST:
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
					System.out.println(jsondata);
					if(Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						UIHelper.showRegisterSuccessActivity(RegisterStep3Activity.this) ;
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						//{"ret":1,"content":"info not find"}
						Util.toastInfo(RegisterStep3Activity.this, "传入的参数有问题") ;
					} else if(Constant.ResultStatus.RESULT_USER_EXIT == JSONParser.getReturnFlag(jsondata)) {
						//{"ret":2,"content":"user_name already exits"}
						Util.toastInfo(RegisterStep3Activity.this , etAccount.getText().toString() + " already exits") ;
					}
				}
				break;
				
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		input.clear() ;
	}
}
