package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
 * 我的账号绑定邮箱页面
 * @author joe
 *
 */
public class MyAccoBindEmailActivity extends BaseActivity {
    private EditText input_email;
    private Button send_link_btn;
    private CommonAPI api = null;
    private final int BIND_EMAIL = 20;
    private final int RESULTCODE = 15;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_bind_email);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("绑定邮箱");
		input_email = (EditText)findViewById(R.id.input_email);
		send_link_btn = (Button)findViewById(R.id.send_link_btn);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		send_link_btn.setOnClickListener(this);
		input_email.addTextChangedListener(new input());
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case BIND_EMAIL:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.optInt("ret");
					if(status == 1){
						Util.toastInfo(this, "邮箱验证链接已发送，赶快去验证吧");
						finish();
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else{
						Util.toastInfo(this, "绑定邮箱失败");
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
        case R.id.send_link_btn:
        	String email = input_email.getText().toString().trim();
        	if(email.length() > 0){
        		if(Util.isEmail(email)){
        			SharedPreferences spf = this.getSharedPreferences("login_user", 0) ;
        			String open_id = spf.getString("open_id","");
        			String token = spf.getString("token","");
        			String user_name = spf.getString("user_name","");
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
        			paramsmap.put("email",email);
        			paramsmap.put("user_name",user_name);
        			api.getVerifyCodeOrBind("binding_email", paramsmap, this, BIND_EMAIL);
        			}
        		}else{
        			Util.toastInfo(this, "邮箱格式不正确");
        		}
        	}else{
        		Util.toastInfo(this, "请输入要绑定的邮箱");
        	}
			break;
		default:
			break;
		}
	}
	
	class input implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
		}
		
	}
}
