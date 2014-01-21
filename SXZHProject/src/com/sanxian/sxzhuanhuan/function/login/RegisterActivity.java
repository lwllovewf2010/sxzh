package com.sanxian.sxzhuanhuan.function.login;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: RegisterActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-2 下午2:21:44 
 * @version V1.0   
 */
public class RegisterActivity extends BaseActivity implements OnClickListener , TextWatcher {
	
	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	private CommonTitle conTitle = null ;
	private EditText etUsername = null ;
	private EditText etTelephone = null ;
	private Button btnRegister = null ;
	private CheckBox cbAgree = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.register_first) ;
		
		init() ;
		conTitle.show(true, "取消", true, "注册", false, "") ;
	}
	
	@SuppressLint("NewApi")
	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>() ;
		
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		etUsername = (EditText) findViewById(R.id.register_et_username) ;
		etTelephone = (EditText) findViewById(R.id.register_et_telephone) ;
		btnRegister = (Button) findViewById(R.id.register_btn_vertify_code) ;
		cbAgree = (CheckBox) findViewById(R.id.register_cb_agree) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		cbAgree.setOnClickListener(this) ;
		etTelephone.addTextChangedListener(this) ;
		btnRegister.setOnClickListener(this) ;
		
		btnRegister.setClickable(false) ;
		btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.cancel_common_ok_button_normal) ) ;
//		btnRegister.setBackground(getResources().getDrawable(R.drawable.cancel_common_ok_button_normal)) ;
		
		UIHelper.setTextColor((TextView)findViewById(R.id.register_tv_step_first) , getResources().getString(R.string.reg_step_first), 
				getResources().getString(R.string.reg_step_second) ,
				getResources().getString(R.string.reg_step_third) ,"green" , Constant.RegisterStep.STEP1) ;
		
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
				UIHelper.showLoginActivity(RegisterActivity.this) ;
				break ;
				
			case R.id.register_btn_vertify_code :
				System.out.println("vertify code" );
				if (cbAgree.isChecked() ) {
					//"params":{"true_name":"\u5f20\u4e09","mobile":"13811689767"}}
					input.put("mobile", etTelephone.getText().toString().trim() ) ; // "18689221661") ;
//					api.sendVertifyCode(input, this, Constant.REQUESTCODE.SEND_VERTIFY_CODE_REQUEST) ;
//					UIHelper.showRegisterStep2Activity(RegisterActivity.this , etTelephone.getText().toString().trim()) ;
//					"params":{"true_name":"\u5f20\u4e09","mobile":"13811689767"}}
					input.put("true_name", etUsername.getText().toString().trim()) ;
					api.checkUserRegister(input, RegisterActivity.this, Constant.REQUESTCODE.CHECK_USER_REGISTER_REQUEST) ;
					
				} else {
					Util.toastInfo(RegisterActivity.this, getResources().getString(R.string.reg_xmapp_warning)) ;
				}
				
				break ;
				
			case R.id.register_cb_agree :
				System.out.println("cb_agree");
				break ;
			default:
				break;
		}
	}
	
	private String reference_open_id = null ;
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		System.out.println("flag----" + flag);
		switch (flag) {
		case Constant.REQUESTCODE.CHECK_USER_REGISTER_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				
				System.out.println(jsondata);
				//"content":{"reference_open_id":"3_1206_629938"}}
				JSONObject object = null ;
				try {
					object = new JSONObject(jsondata);
					JSONObject mJSONObject = object.getJSONObject("content");
					reference_open_id = mJSONObject.getString("reference_open_id") ;
					System.out.println("reference_open_id" + reference_open_id); 
					
					input.clear() ;
					input.put("mobile", etTelephone.getText().toString().trim() ) ; // "18689221661") ;
					api.sendVertifyCode(input, this, Constant.REQUESTCODE.SEND_VERTIFY_CODE_REQUEST) ;
					UIHelper.showRegisterStep2Activity(RegisterActivity.this , etTelephone.getText().toString() , etUsername.getText().toString() , reference_open_id) ;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
			
		case Constant.REQUESTCODE.SEND_VERTIFY_CODE_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();

				System.out.println(jsondata);
			}
			break ;
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if(UIHelper.isRealTelephone(etTelephone.getText().toString())) {
			btnRegister.setClickable(true) ;
			btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_btn)) ;
//			btnRegister.setBackground(getResources().getDrawable(R.drawable.login_btn)) ;
		} else {
			btnRegister.setClickable(false) ;
			btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.cancel_common_ok_button_normal)) ;
//			btnRegister.setBackground(getResources().getDrawable(R.drawable.cancel_common_ok_button_normal)) ;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		System.out.println(etTelephone.getText().toString());
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		input.clear() ;
	}
}
