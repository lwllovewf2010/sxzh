package com.sanxian.sxzhuanhuan.function.login;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: SearchPwdActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-2 下午2:35:23 
 * @version V1.0   
 */
public class SearchPwdActivity extends BaseActivity implements OnClickListener , TextWatcher{
	
	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	private CommonHeader conHeader = null ;
	private EditText etTelephone = null ;
	private TextView tvShowInfo = null ;
	private EditText etVertifyCode = null ;
	private Button btnVertifyCode = null ;
	private Button btnNext = null ;
	
	private final static int MSG_TIME = 0x01 ;
	private static int timeLimit = 60 ;
	private Handler timeHandle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_TIME :
					btnVertifyCode.setText((timeLimit--) + "秒") ;
					btnVertifyCode.setBackgroundColor(SearchPwdActivity.this.getResources().getColor(R.color.crimson)) ;
					System.out.println("timelimit---" + timeLimit );
					break ;
			}
		};
	} ;
	
	private Thread tempThread = null ; // 临时变量，避免出现Thread already start异常
	private Thread timeThread = new Thread() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				do {
					Thread.sleep(1000);
					
					Message msg=new Message() ;
		            msg.what = MSG_TIME ;
		            timeHandle.sendMessage(msg) ;
				} while (Thread.interrupted()==false  && timeLimit > 0) ;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.search_password_first) ;
		
		init() ;
		conHeader.show(true, true , "返回", true, "找回密码", false , "" , false) ;
		
	}
	
	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>() ;
		
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		etTelephone = (EditText) findViewById(R.id.search_pwd_et_telephone) ;
		tvShowInfo = (TextView) findViewById(R.id.search_pwd_tv_show_info) ;
		etVertifyCode = (EditText) findViewById(R.id.search_pwd_et_vertify_code) ;
		btnVertifyCode = (Button) findViewById(R.id.search_pwd_btn_get_vertify_code) ;
		btnNext = (Button) findViewById(R.id.search_pwd_btn_next) ;
		
		conHeader.ivPre.setOnClickListener(this) ;
		conHeader.tvLeft.setOnClickListener(this) ;
		btnVertifyCode.setOnClickListener(this) ;
		btnNext.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				UIHelper.showLoginActivity(SearchPwdActivity.this) ;
				break;
			case R.id.search_pwd_btn_get_vertify_code :
				getVertifyCode() ;
				
				break ;
			case R.id.search_pwd_btn_next :
				input.put("user_con",  etTelephone.getText().toString() ) ;
				input.put("virify_code", etVertifyCode.getText().toString() ) ; //"898635") ;
				api.checkVertifyCode(input, this, Constant.REQUESTCODE.CHECK_VERTIFY_CODE_REQUEST) ;
				break ;
		}
	}
	
	private void getVertifyCode() {
		if(UIHelper.isRealTelephone(etTelephone.getText().toString())) {
			etTelephone.setFocusable(false) ;
			btnVertifyCode.setClickable(false) ;
			tvShowInfo.setTextSize(12.0f) ;
			tvShowInfo.setText(Html.fromHtml("<font color='red'>" + getResources().getString(R.string.search_pwd_tv_vertify_info) + "</font>")) ;
			
			input.put("mobile",etTelephone.getText().toString().trim() ) ;
			api.sendVertifyCode(input, this, Constant.REQUESTCODE.SEND_VERTIFY_CODE_REQUEST) ;
			
			tempThread = new Thread(timeThread) ;
			tempThread.start() ;
		} else {
			Util.toastInfo(SearchPwdActivity.this, "号码无效，请重新输入!") ;
		}
	}
	
	private void reInit() {
		input.clear() ;
		timeLimit = 60 ;
		tempThread.interrupt() ;
		
		etTelephone.setText("") ;
		etTelephone.setFocusable(true) ;
		etTelephone.setFocusableInTouchMode(true) ;
		etTelephone.requestFocus() ;
		etTelephone.setClickable(true) ;
		etVertifyCode.setHint("请输入短信验证码") ;
		btnVertifyCode.setClickable(true) ;
		btnVertifyCode.setText("获取验证码") ;
		btnVertifyCode.setBackgroundDrawable(SearchPwdActivity.this.getResources().getDrawable(R.drawable.login_btn)) ;
		tvShowInfo.setText("请输入短信验证码") ;
		tvShowInfo.setTextSize(23.0f) ;
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
			case Constant.REQUESTCODE.CHECK_VERTIFY_CODE_REQUEST:
				if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();

				if (Constant.ResultStatus.RESULT_OK == JSONParser
						.getReturnFlag(jsondata)) {
					
					UIHelper.showSearchPwdStep2Activity(SearchPwdActivity.this) ;
					
				} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser
						.getReturnFlag(jsondata)) {
					// {"ret":1,"content":"failed"}
					if (timeLimit <= 0) {
						Util.toastInfo(SearchPwdActivity.this, getResources().getString(R.string.search_pwd_warning_vertify_expired)) ;
						reInit() ;
					} else {
						Util.toastInfo(SearchPwdActivity.this, getResources()
								.getString(R.string.warning_vertify_code_wrong));
						etVertifyCode.setText("") ;
						etVertifyCode.requestFocus() ;
					}
				}
			}
				break ;
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
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if(UIHelper.isRealTelephone(etTelephone.getText().toString())) {
			
		} else {
			
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		input.clear() ;
		timeLimit = 60 ;
		if(null != tempThread) 
			tempThread.interrupt() ;
	}

}
 