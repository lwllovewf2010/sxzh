package com.sanxian.sxzhuanhuan.function.login;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: RegisterStep2Activity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-10 下午1:41:44 
 * @version V1.0   
 */
public class RegisterStep2Activity extends BaseActivity implements OnClickListener{

	private TestAPI api = null;
	private Map<String , String> input = null ;
	
	private CommonTitle conTitle = null ;
	private TextView tvVertifyInfo = null ; 
	private EditText etVertifyCode = null ;
	private Button btnVertifyCode = null ;
	
	private String phone = "" ;
	private String username = "" ;
	private String reference_open_id = "" ;
	private String vertifyCode = "" ;
	
	private final static int MSG_TIME = 0x01 ;
	private static int timeLimit = 0 ;
	private Handler timeHandle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_TIME :
					++timeLimit ;
					System.out.println("timelimit---" + timeLimit );
					break ;
			}
		};
	} ;
	
	private Thread timeThread = new Thread() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//while(Thread.interrupted()==false);/* 当系统发出中断信息时停止本循环*/
			try {
				do {
					Thread.sleep(1000);
					
					Message msg=new Message() ;
		            msg.what = MSG_TIME ;
		            timeHandle.sendMessage(msg) ;
				} while (Thread.interrupted()==false  && timeLimit < 120) ;
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
		
		setContentView(R.layout.register_second) ;
		Intent intent = getIntent() ;
		if(null != intent) {
			phone = intent.getStringExtra("phone_number") ;
			username = intent.getStringExtra("username") ;
			reference_open_id = intent.getStringExtra("reference_open_id") ;
		}
		
		init() ;
		conTitle.show(true, "返回", true, "注册", false, "") ;
		
		timeThread.start() ;
	}
	
	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>() ;
		
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		tvVertifyInfo = (TextView) findViewById(R.id.register_tv_vertify_info) ;
		etVertifyCode = (EditText) findViewById(R.id.register_et_vertify_code) ;
		btnVertifyCode = (Button) findViewById(R.id.register_btn_cimmit_vertiry_code) ;
		
		conTitle.btnLeft.setOnClickListener(this) ;
		btnVertifyCode.setOnClickListener(this) ;
		
		tvVertifyInfo.setText(tvVertifyInfo.getText().toString() + formatPhone(phone)) ;
		UIHelper.setTextColor((TextView)findViewById(R.id.register_tv_step_second) , getResources().getString(R.string.reg_step_first), 
				getResources().getString(R.string.reg_step_second) ,
				getResources().getString(R.string.reg_step_third) ,"green" , Constant.RegisterStep.STEP2) ;
		
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
				UIHelper.showRegisterActivity(RegisterStep2Activity.this) ;
				break ;
				
			case R.id.register_btn_cimmit_vertiry_code :
				System.out.println("RegisterActivity" + "reg");
				vertifyCode = etVertifyCode.getText().toString() ;
				input.put("user_con", phone ) ; //"13811689766") ;
				input.put("virify_code", vertifyCode ) ; //"898635") ;
				api.checkVertifyCode(input, this, Constant.REQUESTCODE.CHECK_VERTIFY_CODE_REQUEST) ;
				
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
		System.out.println("----" + flag);
		switch (flag) {
			case Constant.REQUESTCODE.CHECK_VERTIFY_CODE_REQUEST:
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
					System.out.println(jsondata);
					
//					UIHelper.showRegisterStep3Activity(RegisterStep2Activity.this , phone) ;
					
					if(Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						//{"user_name":"zhangsan","mobile":"12345678900","passwor":"123456","reference_open_id":"5_1278_539947"}}
						UIHelper.showRegisterStep3Activity(RegisterStep2Activity.this , phone , username , reference_open_id ) ;
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						//{"ret":1,"content":"failed"}
						if(timeLimit >= 120) {
							Util.toastInfo(RegisterStep2Activity.this, getResources().getString(R.string.warning_vertify_code_expired)) ;
						} else {
							Util.toastInfo(RegisterStep2Activity.this, getResources().getString(R.string.warning_vertify_code_wrong)) ;
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
		timeLimit = 0 ;
		timeThread.interrupt() ;
	}
	
	private String formatPhone(String phone) {
		return phone.substring(0, 3) + "****" + phone.substring(7, 11) ;
	}
}
