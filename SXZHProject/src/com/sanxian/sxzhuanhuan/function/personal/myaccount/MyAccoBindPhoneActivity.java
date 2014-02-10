package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 我的账号绑定手机号码页面
 * @author joe
 *
 */
public class MyAccoBindPhoneActivity extends BaseActivity {
    private EditText input_oldphone_et;
    private EditText input_newphone_et;
    private EditText input_verify_code;
    private Button get_code_btn;
    private Button bind_phone_btn;
    private CommonAPI api = null;
    private final int GET_VERIFY_CODE = 15;
    private final int RESULTCODE = 15;
    private String type = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_bind_phone);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("绑定手机号");
		input_oldphone_et = (EditText)findViewById(R.id.input_oldphone_et);
		input_newphone_et = (EditText)findViewById(R.id.input_newphone_et);
		input_verify_code = (EditText)findViewById(R.id.input_verify_code);
		get_code_btn = (Button)findViewById(R.id.get_code_btn);
		bind_phone_btn = (Button)findViewById(R.id.bind_phone_btn);
//		String mobile = getIntent().getStringExtra("mobile");
//		input_oldphone_et.setText(mobile);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		get_code_btn.setOnClickListener(this);
		bind_phone_btn.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case GET_VERIFY_CODE:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.optInt("ret");
					if(status == 0){
						if("get".equals(type)){
							Util.toastInfo(this, "验证码已发送请等待");
						}else if("virify".equals(type)){
							Util.toastInfo(this, "绑定手机号码成功");
							SharedPreferences spf = getSharedPreferences("login_user", 0) ;
							SharedPreferences.Editor editor = spf.edit() ;
							editor.putString("mobile",input_newphone_et.getText().toString().trim() ) ;
							editor.commit() ;
							setResult(RESULTCODE);
							finish();
						}
					}else if(status == 408){
						Util.toastInfo(this, "验证码有误");
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.get_code_btn:
			type = "get";
			tackle(type);
			break;
		case R.id.bind_phone_btn:
			type = "virify";
			tackle(type);
			break;
		default:
			break;
		}
	}
    
    /**
     * 检查信息是否合法
     * joe
     * @return
     */
	public boolean checkData(){
		String oldphone = input_oldphone_et.getText().toString().trim();
		if(!"".equals(oldphone)){
			if(Util.checkMobile(oldphone)){
				String newphone = input_newphone_et.getText().toString().trim();
				if(!"".equals(newphone)){
					if(Util.checkMobile(newphone)){
						return true;
					}else{
						Util.toastInfo(this, "新手机号码格式不正确");
						return false;
					}
				}else{
					Util.toastInfo(this, "新手机号码不能为空");	
					return false;
				}
			}else{
				Util.toastInfo(this, "原手机号码格式不正确");
				return false;
			}
		}else{
			Util.toastInfo(this, "原手机号码不能为空");
			return false;
		}
	}
	
	private void tackle(String type){
		if(checkData()){
		String oldphone = input_oldphone_et.getText().toString().trim();
		String newphone = input_newphone_et.getText().toString().trim();
		SharedPreferences spf = this.getSharedPreferences("login_user", 0) ;
		String open_id = spf.getString("open_id","");
		String token = spf.getString("token","");
		if("".equals(open_id)|| "".equals(token)){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
		}else{
		if(api == null){
			api = new CommonAPI();
		}
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",open_id);
		paramsmap.put("token",token);
		paramsmap.put("old_mobile",oldphone);
		paramsmap.put("new_mobile",newphone);
		if("virify".equals(type)){
			String verify_code = input_verify_code.getText().toString().trim();
			if(verify_code.length() > 0){
				paramsmap.put("virify_code",verify_code);
				api.getVerifyCodeOrBind("change_mobie", paramsmap, this, GET_VERIFY_CODE);
			}else{
				Util.toastInfo(this, "请输入验证码");
			}
		 }else if("get".equals(type)){
		 api.getVerifyCodeOrBind("get_captcha_changemobile",paramsmap,this, GET_VERIFY_CODE);
		}
	   }
	  }
	}
}
