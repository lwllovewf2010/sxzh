package com.sanxian.sxzhuanhuan.function.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.util.Util;

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
	private CommonHeader conHeader = null ;
	private TextView tvTimeLimit = null ;
	private TextView tvGotoNow = null ;
	private TextView tvTextColor = null ;
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

		setContentView(R.layout.register_success);

		init();
		conHeader.show(true, true , "返回", true, "注册完成", false , "" , false) ;
		
		timeThread.start() ;
	}

	private void init() {
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		tvTimeLimit = (TextView) findViewById(R.id.register_suc_tv_time_limit) ;
		tvGotoNow = (TextView) findViewById(R.id.register_suc_tv_goto_now) ;
		tvTextColor = (TextView) findViewById(R.id.reg_suc_tv_color) ;
		btnRelnameAuth = (Button) findViewById(R.id.register_suc_btn_relname_auth) ;
		
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		tvGotoNow.setOnClickListener(this) ;
		//如果您有许多想法或者想要投资
		tvTextColor.setText(Html.fromHtml("如果您有" + "<font color='" + getResources().getColor(R.color.color_f39700) + "'>" + "许多想法"
					+ "</font>" + "或者" + "<font color='" + getResources().getColor(R.color.color_00a0e9) + "'>" + "想要投资"
					+ "</font>")) ;
		btnRelnameAuth.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				Util.toastInfo(RegisterSuccessActivity.this, "去哪") ;
				break;
			case R.id.register_suc_tv_goto_now:
				finish() ;
				break;
			case R.id.register_suc_btn_relname_auth :
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
