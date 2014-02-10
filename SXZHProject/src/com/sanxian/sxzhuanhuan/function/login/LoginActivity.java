package com.sanxian.sxzhuanhuan.function.login;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ILogin;
import com.sanxian.sxzhuanhuan.message.MessageLogin;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppService;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppUtils;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: LoginActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login
 * @Description: 用户登录
 * @author zhangfl@sanxian.com 
 * @date 2014-1-2 上午11:36:31 
 * @version V1.0   
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	//布局中的相关控件
	private CommonTitle conTitle = null ;
	private EditText etAccount = null ;
	private EditText etPassword = null ;
	private TextView tvRegister = null ;
	private ImageView ivForgetPwd = null ;
	
	//相关变量
	private String strAccount = "" ;
	private String strPassword = "" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login) ;
		
		init() ;
		conTitle.show(true, "取消", true, "登录", true, "登录") ;
	}
	
	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>() ;
		
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		etAccount = (EditText) findViewById(R.id.login_et_account) ;
		etPassword = (EditText) findViewById(R.id.login_et_password) ;
		ivForgetPwd = (ImageView) findViewById(R.id.login_iv_forget_pwd) ;
		tvRegister = (TextView) findViewById(R.id.login_tv_register) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		conTitle.btnRight.setOnClickListener(this) ;
		tvRegister.setOnClickListener(this) ;
		ivForgetPwd.setOnClickListener(this) ;
		
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int tag = v.getId() ;
		switch(tag) {
			case R.id.title_btn_left:
				setResult(Constant.LOGIN_CANCLE) ;
				finish() ;
				break ;
				
			case R.id.title_btn_right :
				strAccount = etAccount.getText().toString().trim() ;
				strPassword = etPassword.getText().toString().trim() ;
				input.put("uname", strAccount) ;
				input.put("password", strPassword) ;
				api.login(input, this, Constant.REQUESTCODE.LOGIN_REQUEST) ;
				break ;
				
			case R.id.login_tv_register :
				System.out.println("------" + "register");
				UIHelper.showRegisterActivity(LoginActivity.this) ;
				break ;
				
			case R.id.login_iv_forget_pwd :
				System.out.println("-------" + "forget password");
				UIHelper.showSearchPwdActivity(LoginActivity.this) ;
				break ;
				
			default :
				
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		System.out.println("----" + flag);
		switch (flag) {
			case Constant.REQUESTCODE.LOGIN_REQUEST:
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
					System.out.println(jsondata);
					if(Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						ILogin logindata = JSONParser.getLoginData(jsondata) ;
						//"open_id": "5_1278_539947",
//				        "user_name": "10629762@qq.com",
//				        "photo": "",
//				        "dname": null,
//				        "mobile": "13811689766",
//				        "email": "10629762@qq.com",
//				        "token": "f9a4745d0a1c8ba7"
						SharedPreferences spf = getSharedPreferences("login_user", 0) ;
						SharedPreferences.Editor editor = spf.edit() ;
						editor.putString("open_id", logindata.getOpen_id()) ;
						editor.putString("token", logindata.getToken()) ;
						editor.putString("user_name", logindata.getUser_name()) ;
						editor.putString("photo", logindata.getPhoto()) ;
						editor.putString("dname", logindata.getTrue_name()) ;
						editor.putString("mobile", logindata.getMobile()) ;
						editor.putString("email", logindata.getEmail()) ;
						
						editor.commit() ;
						
						
						MessageLogin login=new MessageLogin(){

							@Override
							protected void onPostExecute(Boolean result) {
								// TODO Auto-generated method stub
								if(result){
									Log.d("loginactivity", "登入成功了启动服务");
									Toast toast=new Toast(LoginActivity.this);
									toast.makeText(LoginActivity.this, "openfire登入成功", Toast.LENGTH_LONG).show();
									//登入成功
									Intent intentService = new Intent(LoginActivity.this,XmppService.class);
									startService(intentService);									
									//状态设置为上线
									XmppUtils.getInstance().sendOnLine();
								}
							}
							
						};
						Map<String, String> map=new HashMap<String, String>();
//						map.put("username", etAccount.getText().toString());
//						map.put("username", etPassword.getText().toString());
						map.put("username",logindata.getOpen_id());
						map.put("password","666666");						
						login.execute(map);
						
						
						setResult(Constant.RESULT_LOGIN_CODE) ;
						finish() ;
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						//{"ret":1,"content":"info not find"}
						//{"ret":1,"content":"password error"}
						if("info not find" .equals(JSONParser.getFailString(jsondata))) {
							Util.toastInfo(LoginActivity.this, "帐号不存在，请重新输入") ;
						} else if ("password error" .equals(JSONParser.getFailString(jsondata))  ) {
							Util.toastInfo(LoginActivity.this, "密码错误，请重新输入") ;
						}
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
