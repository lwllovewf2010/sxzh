package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.api.TestAPI;
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
    private TestAPI testapi = null;
    private final int GET_VERIFY_CODE = 15;
    private String type = "";
    private String bindtype = "";
    private LinearLayout modify_phone_layout;
    private SharedPreferences spf = this.getSharedPreferences("login_user", 0) ;
    
    private Timer timer;
    private int time = 60;
    private boolean is_usable = true; //是否可用
	 private Handler handler = new Handler()
	    {
	    	public void handleMessage(Message msg) {
	    		switch (msg.what) {
				case 1:
		            if(time == 0){
		            	if(timer != null){
		            		timer.cancel();
		            		timer = null;
		            	}
		            	if(task != null){
		            		task.cancel();
		            		task = null;
		            	}
		            	get_code_btn.setText(getResources().getString(R.string.get_verify_code_btn));
		            	is_usable = true;
					}else{
						get_code_btn.setText(time + "s后再发送");
						time = time - 1 ;
						is_usable = false;
					}
					break;

				default:
					break;
				}
	    	};
	    };
	    private TimerTask task;
	    public void runTask(){
	    time = 60;
	    task = new TimerTask(){  
	    	    public void run() {  
	    		Message message = new Message();      
	    		message.what = 1;      
	    		handler.sendMessage(message);    
	    		}  
	     };  
	    }
	
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
		bindtype = getIntent().getStringExtra("bindtype");
		modify_phone_layout = (LinearLayout)findViewById(R.id.modify_phone_layout);
		if("bind".equals(bindtype)){
			modify_phone_layout.setVisibility(View.GONE);
		}
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
							 timer = null;
							 timer = new Timer(true);
							 runTask();
							 timer.schedule(task,0, 1000); //延时1000ms后执行，1000ms执行一次
						}else if("virify".equals(type)){
							if("bind".equals(bindtype)){
								Util.toastInfo(this, "绑定手机号码成功");
							}else if("modify".equals(bindtype)){
								Util.toastInfo(this, "修改绑定手机号码成功");
							}
							SharedPreferences spf = getSharedPreferences("login_user", 0) ;
							SharedPreferences.Editor editor = spf.edit() ;
							editor.putString("mobile",input_newphone_et.getText().toString().trim() ) ;
							editor.commit() ;
							finish();
						}
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else if(status == 406){
						Util.toastInfo(this, "你输入的旧手机号码有误");
					}else if(status == 407){
						Util.toastInfo(this, "新手机号码已被绑定，请更换其他号码");
					}else if(status == 408){
						Util.toastInfo(this, "验证码有误");
					}else if(status == 401){
						Util.toastInfo(this, "你输入的旧手机号码有误");
					}else{
						
						if("bind".equals(bindtype)){
							if("get".equals(type)){
								if(status == 1){
									Util.toastInfo(this, "该号码已被绑定，请更换其他号码");
								}else{
									Util.toastInfo(this, "验证码发送失败");
								}
							}else if("virify".equals(type)){
								Util.toastInfo(this, "绑定手机号码失败");
							}
							
						}else if("modify".equals(bindtype)){
							if("get".equals(type)){
								Util.toastInfo(this, "验证码发送失败");
							}else if("virify".equals(type)){
								Util.toastInfo(this, "绑定手机号码失败");
							}	
						}
						
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
			if(is_usable){
				type = "get";
				if("bind".equals(bindtype)){
					bindMobile();
				}else if("modify".equals(bindtype)){
					tackle(type);	
				}
			}
			break;
		case R.id.bind_phone_btn:
			type = "virify";
			if("bind".equals(bindtype)){
				bindMobile();
			}else if("modify".equals(bindtype)){
				tackle(type);	
			}
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
	
	/**
	 * 修改绑定手机号码操作
	 * joe
	 * @param type
	 */
	private void tackle(String type){
		if(checkData()){
		String oldphone = input_oldphone_et.getText().toString().trim();
		String newphone = input_newphone_et.getText().toString().trim();
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
	

/**
 * 绑定手机号码
 * joe
 */
	private void bindMobile(){
		String mobile = input_newphone_et.getText().toString().trim();
		if(mobile.length() > 0){
			if(Util.checkMobile(mobile)){
				
				if("get".equals(type)){
					if(testapi == null){
						testapi = new TestAPI();
					}
					Map<String,String> paramsmap = new HashMap<String, String>();
					paramsmap.put("mobile",mobile);
					testapi.sendVertifyCode(paramsmap, this, GET_VERIFY_CODE);
				    }else if("virify".equals(type)){
				    	String open_id = spf.getString("open_id","");
						String token = spf.getString("token","");
						if("".equals(open_id)|| "".equals(token)){
							Intent intent = new Intent(this, LoginActivity.class);
							startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
						}else {
				    	if(api == null){
							api = new CommonAPI();
						}
				    	String verify_code = input_verify_code.getText().toString().trim();
						if(verify_code.length() > 0){
							Map<String,String> paramsmap = new HashMap<String, String>();
							paramsmap.put("open_id",open_id);
							paramsmap.put("token",token);
							paramsmap.put("mobile",mobile);
							paramsmap.put("virify_code",verify_code);
							api.getVerifyCodeOrBind("get_invite_list", paramsmap, this, GET_VERIFY_CODE);
						}else{
							Util.toastInfo(this, "请输入验证码");
						}
				  }
				}
			}else{
				Util.toastInfo(this, "手机号码格式不正确");	
			}
		}else{
			Util.toastInfo(this, "手机号码不能为空");
		}
	}
}
