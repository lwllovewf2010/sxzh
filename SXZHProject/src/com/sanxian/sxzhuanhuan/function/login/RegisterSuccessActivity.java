package com.sanxian.sxzhuanhuan.function.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;
import com.sanxian.sxzhuanhuan.common.UIHelper;

/**
 * @Title: RegisterSuccessActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.login
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-10 下午3:20:01
 * @version V1.0
 */
public class RegisterSuccessActivity extends BaseActivity implements
		OnClickListener {
	private CommonTitle conTitle = null ;
	private TextView tvTimeLimit = null ;
	private TextView tvGotoNow = null ;
	private Button btnRelnameAuth = null ;
	
	private final static int MSG_TIME = 0x01 ;
	private static int timeLimit = 15 ;
	private Handler timeHandle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_TIME :
					tvTimeLimit.setText(String.format(getResources().getString(R.string.reg_suc_timelimit), timeLimit--)) ;
					System.out.println("timelimit---" + timeLimit );
					if(timeLimit == 0) 
						finish() ;
					break ;
			}
		};
	} ;
	
	private Thread timeThread = new Thread() {
		@Override
		public void run() {
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

		setContentView(R.layout.register_success);

		init();
		conTitle.show(false, "", true, "注册", false, "");
		
		timeThread.start() ;
	}

	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
		tvTimeLimit = (TextView) findViewById(R.id.register_suc_tv_time_limit) ;
		tvGotoNow = (TextView) findViewById(R.id.register_suc_tv_goto_now) ;
		btnRelnameAuth = (Button) findViewById(R.id.register_suc_btn_relname_auth) ;
		
		tvGotoNow.setOnClickListener(this) ;
		btnRelnameAuth.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.register_suc_tv_goto_now:
				System.out.println("goto now");
				finish() ;
				break;
			case R.id.register_suc_btn_relname_auth :
				System.out.println("relname_auth");
				UIHelper.showRealAuthActivity(RegisterSuccessActivity.this) ;
				break ;

		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timeLimit = 15 ;
		timeThread.interrupt() ;
	}
}
