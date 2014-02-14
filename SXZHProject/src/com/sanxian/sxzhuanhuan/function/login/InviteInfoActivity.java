package com.sanxian.sxzhuanhuan.function.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.entity.Constant;

/**   
 * @Title: InviteInfoActivity.java 
 * @Package com.sanxian.sxzhuanhuan.function.login
 * @Description: 如何获得邀请
 * @author zhangfl@sanxian.com 
 * @date 2014-2-12 上午11:36:31 
 * @version V1.0   
 */
public class InviteInfoActivity extends BaseActivity implements OnClickListener{
	
	private CommonHeader conHeader = null ;
	private Button btnLogin = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.invite_info) ;
		
		init() ;
		conHeader.show(true, true , "返回", true, "如何获得邀请", false , "" , false) ;
	}
	
	private void init() {
		
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		btnLogin = (Button) findViewById(R.id.invite_phone_call) ;
		
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		btnLogin.setOnClickListener(this) ;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int tag = v.getId() ;
		switch(tag) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				finish() ;
				break ;
			case R.id.invite_phone_call :
//				Util.toastInfo(InviteInfoActivity.this, "拨打三弦的客服电话") ;
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+Constant.PHONE_NUMBER));  
                startActivity(intent);
				break ;
				
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
