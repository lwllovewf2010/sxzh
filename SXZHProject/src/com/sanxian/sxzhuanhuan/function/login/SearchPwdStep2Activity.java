package com.sanxian.sxzhuanhuan.function.login;

import java.util.HashMap;
import java.util.Map;

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
 * @Title: SearchPwdStep2Activity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-11 上午9:58:23 
 * @version V1.0   
 */
public class SearchPwdStep2Activity extends BaseActivity implements OnClickListener {
	private CommonHeader conHeader = null ;
	private EditText etPassword = null ;
	private EditText etPassword2 = null ;
	private Button btnCommit = null ;
	
	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.search_password_second) ;
		
		init() ;
		conHeader.show(true, true , "返回", true, "设置新密码", false , "" , false) ;
		
	}
	
	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>() ;

		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		etPassword = (EditText) findViewById(R.id.search_pwd_et_new_password) ;
		etPassword2 = (EditText) findViewById(R.id.search_pwd_et_new_password_commit) ;
		btnCommit = (Button) findViewById(R.id.search_pwd_btn_change_password) ;
		
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		btnCommit.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
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
//			UIHelper.showLoginActivity(SearchPwdStep2Activity.this) ;
			//"params":{"open_id":"5_1278_539947","token":"123456","old_password":"111222","new_password":"333333"}}
			input.clear() ;
			input.put("open_id", "") ;
			input.put("token", "") ;
			input.put("old_password", "") ;
			input.put("new_password", password1) ;
			api.changePassword(input, SearchPwdStep2Activity.this, Constant.REQUESTCODE.USER_CHANGE_PASSWORD_REQUEST); 
		}
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case Constant.REQUESTCODE.USER_CHANGE_PASSWORD_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();

				if (Constant.ResultStatus.RESULT_OK == JSONParser
						.getReturnFlag(jsondata)) {

					UIHelper.showLoginActivity(SearchPwdStep2Activity.this);

				} else if (1001 == JSONParser.getReturnFlag(jsondata)) {
					// token无效：{"ret":1001,"content":"The logon has become invalid!"}
				} else if (401 == JSONParser.getReturnFlag(jsondata)) {
					// 缺少参数：{"ret":401,"content":"params not exist!"}
				} else if (402 == JSONParser.getReturnFlag(jsondata)) {
					// 原密码不正确：{"ret":402,"content":"password error"}
				}
			}
			break;
		}
	}



}
 