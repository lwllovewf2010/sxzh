package com.sanxian.sxzhuanhuan.function.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonTitle;

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
	private TextView tvGotoNow = null ;
	private Button btnRelnameAuth = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.register_success);

		init();
		conTitle.show(false, "", true, "注册", false, "");
	}

	private void init() {
		conTitle = (CommonTitle) findViewById(R.id.common_title) ;
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
				break ;

		}
	}
}
