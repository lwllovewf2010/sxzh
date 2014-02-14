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
 * 我的账号修改登录密码页面
 * @author joe
 *
 */
public class MyAccoSetLoginPWActivity extends BaseActivity {
    private EditText old_password;
    private EditText new_password;
    private EditText confirm_new_password;
    private Button save_loginpw;
    private CommonAPI api = null;
    private final int CHANGE_PASSWORD = 25;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_set_loginpw);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("修改密码");
		displayRight();
		old_password = (EditText)findViewById(R.id.old_password);
		new_password = (EditText)findViewById(R.id.new_password);
		confirm_new_password = (EditText)findViewById(R.id.confirm_new_password);
		save_loginpw = (Button)findViewById(R.id.save_loginpw_button);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		save_loginpw.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.save_loginpw_button:
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
			String old_passw = old_password.getText().toString().trim();
			if(old_passw.length() > 0){
				if(old_passw.length() > 5){
					String new_passw = new_password.getText().toString().trim();
					if(new_passw.length() > 0){
						if(new_passw.length() > 5){
							String confirm_passw = confirm_new_password.getText().toString().trim();
							if(confirm_passw.length() > 0){
								if(confirm_passw.length() > 5){
									if(new_passw.equals(confirm_passw)){
										Map<String,String> paramsmap = new HashMap<String, String>();
					        			paramsmap.put("open_id",open_id);
					        			paramsmap.put("token",token);
					        			paramsmap.put("old_password",old_passw);
					        			paramsmap.put("new_password",confirm_passw);
										api.getVerifyCodeOrBind("change_password", paramsmap, this, CHANGE_PASSWORD);
									}else{
										Util.toastInfo(this,"新密码与确认密码不一致");
									}
								}else{
								  Util.toastInfo(this,"确认新密码长度不够");	
								}
							}else{
								Util.toastInfo(this,"确认密码不能为空");
							}
						}else{
						  Util.toastInfo(this,"新密码长度不够");	
						}
					}else{
						Util.toastInfo(this,"新密码不能为空");
					}
				}else{
				  Util.toastInfo(this,"当前密码长度不够");	
				}
			}else{
				Util.toastInfo(this,"当前密码不能为空");
			}
			}
			break;
		default:
			break;
		}
	}
    
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case CHANGE_PASSWORD:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.optInt("ret");
					if(status == 0){
						Util.toastInfo(this, "登录密码修改成功");
						finish();
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else if(status == 401){
						
					}else if(status == 402){
						Util.toastInfo(this, "原登录密码不正确");
					}else{
						Util.toastInfo(this, "登录密码修改失败");
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

}
