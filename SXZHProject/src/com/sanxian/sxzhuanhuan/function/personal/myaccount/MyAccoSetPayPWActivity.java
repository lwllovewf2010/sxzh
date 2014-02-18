package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 我的账号设置支付密码页面
 * @author joe
 *
 */
public class MyAccoSetPayPWActivity extends BaseActivity {

	private LinearLayout set_paypw_layout,modify_paypw_layout;//设置支付密码，修改支付密码
	boolean is_set_paypw = false;
	private Button save_button;//提交按钮
	private EditText input_pay_pw,inpunt_confirm_paypw,current_pay_password,
	                 new_pay_password,confirm_pay_password;//输入支付密码，确认支付密码， 当前密码，新支付密码，确认新支付密码
	private CommonAPI api = null;
	private final int SET_OR_MODIFY_PAY_PASSWORD = 10;
	private Pattern pat = Pattern.compile("^([a-zA-Z0-9]|[._!@#$%^&*~/,|]){6,15}$");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_set_paypw);
		initView();
		initListener();
		
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		is_set_paypw = getIntent().getBooleanExtra("is_set_paypw", false);
		set_paypw_layout = (LinearLayout)findViewById(R.id.set_paypw_layout);
		modify_paypw_layout = (LinearLayout)findViewById(R.id.modify_paypw_layout);
		
		input_pay_pw = (EditText)findViewById(R.id.input_pay_pw);
		inpunt_confirm_paypw = (EditText)findViewById(R.id.inpunt_confirm_paypw);
		
		current_pay_password = (EditText)findViewById(R.id.current_pay_password);
		new_pay_password = (EditText)findViewById(R.id.new_pay_password);
		confirm_pay_password = (EditText)findViewById(R.id.modify_confirm_pay_password);
		save_button = (Button)findViewById(R.id.save_paypw_button);
		if(!is_set_paypw){
			setTitle("设置支付密码");
			set_paypw_layout.setVisibility(View.VISIBLE);
			modify_paypw_layout.setVisibility(View.GONE);
		}else{
			setTitle("修改支付密码");
			set_paypw_layout.setVisibility(View.GONE);
			modify_paypw_layout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		save_button.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case SET_OR_MODIFY_PAY_PASSWORD:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				JSONObject json;
				try {
					json = new JSONObject(data);
					int status = json.getInt("ret");
					if(status == 0){
						if(!is_set_paypw){
							Util.toastInfo(this, "设置支付密码成功");
						}else{
							Util.toastInfo(this, "修改支付密码成功");
						}
						finish();
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else if(status == 2){
						if(!is_set_paypw){
							Util.toastInfo(this, "您已设置过支付密码");
						}else{
							Util.toastInfo(this, "原支付密码错误");
						}
					}else{
						if(!is_set_paypw){
							Util.toastInfo(this, "设置过支付密码失败");
						}else{
							Util.toastInfo(this, "修改支付密码失败");
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
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
		case R.id.save_paypw_button:
			if(api == null){
				api = new CommonAPI();
			}
			if(!is_set_paypw){//设置支付密码
			String set_paypw = input_pay_pw.getText().toString();
			if(set_paypw.length() > 0){
				if(set_paypw.length() > 5){
					Matcher mat = pat.matcher(set_paypw);
					if(mat.find()){
							String set_confirm_paypw = inpunt_confirm_paypw.getText().toString();
							if(set_confirm_paypw.length() > 0){
								if(set_paypw.equals(set_confirm_paypw)){
									String array[] = getOpen_idOrToken();
									Map<String,String> paramsmap = new HashMap<String, String>();
									paramsmap.put("open_id",array[0]);
									paramsmap.put("token", array[1]);
									paramsmap.put("pay_pwd", set_paypw);
									api.getVerifyCodeOrBind("set_pay_pwd", paramsmap, this, SET_OR_MODIFY_PAY_PASSWORD);	
								}else{
									Util.toastInfo(this, "两次输入的支付密码不一致");	
								}
							}else{
								Util.toastInfo(this, "确认支付密码不能为空");
							}
					}else{
						Util.toastInfo(this,"支付密码只允许输入字母数字或字符");	
					}
				}else{
					Util.toastInfo(this,"支付密码长度不符合要求");
				}
			}else{
				Util.toastInfo(this, "输入支付密码不能为空");
			}
			}else{//修改支付密码
				String current_passw = current_pay_password.getText().toString();
				if(current_passw.length() > 0){
					if(current_passw.length() > 5){
						Matcher mat = pat.matcher(current_passw);
						if(mat.find()){
							String new_passw = new_pay_password.getText().toString();
							if(new_passw.length() > 0){
								if(new_passw.length() > 5){
									Matcher mat1 = pat.matcher(new_passw);
									if(mat1.find()){
											String confirm_paypw = confirm_pay_password.getText().toString();
											if(confirm_paypw.length() > 0){
												if(new_passw.equals(confirm_paypw)){
													String array[] = getOpen_idOrToken();
													Map<String,String> paramsmap = new HashMap<String, String>();
													paramsmap.put("open_id",array[0]);
													paramsmap.put("token", array[1]);
													paramsmap.put("old_pay_pwd", current_passw);
													paramsmap.put("new_pay_pwd", new_passw);
													api.getVerifyCodeOrBind("change_pay_pwd", paramsmap, this, SET_OR_MODIFY_PAY_PASSWORD);	
												}else{
													Util.toastInfo(this, "两次输入的支付密码不一致");	
												}
											}else{
												Util.toastInfo(this, "确认支付密码不能为空");
											}
									}else{
										Util.toastInfo(this,"新支付密码只允许输入字母数字或字符");	
									}
								}else{
									Util.toastInfo(this,"新支付密码长度不符合要求");
								}
							}else{
								Util.toastInfo(this, "新支付密码不能为空");
							}
							
						}else{
							Util.toastInfo(this,"当前支付密码只允许输入字母数字或字符");	
						}
					}else{
						Util.toastInfo(this, "当前支付密码长度符合要求");
					}
				}else{
					Util.toastInfo(this, "当前支付密码不能为空");
				}
			}
			KeyBoardCancle();
			break;
		default:
			break;
		}
	}
	// 关闭键盘
	public void KeyBoardCancle() {
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(LoginActivity.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}
