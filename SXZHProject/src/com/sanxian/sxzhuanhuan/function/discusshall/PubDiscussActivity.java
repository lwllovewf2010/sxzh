package com.sanxian.sxzhuanhuan.function.discusshall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: PubDiscussActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshall
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-2-24 下午2:48:48
 * @version V1.0
 */
public class PubDiscussActivity extends BaseActivity implements OnClickListener {
	private CommonHeader conHeader = null ;
	private EditText etPubTitle = null ;
	private EditText etPubContent = null ;
	private Button btnPub = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.pub_discuss);

		Intent intent = getIntent() ;
		if(null != intent) {
		}
		
		initWidget() ;
	}
	
	private void initWidget() {
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		conHeader.show(true, true , "返回", true, "发布讨论", false , "" , false) ;
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		
		etPubTitle = (EditText) findViewById(R.id.pub_discuss_title) ;
		etPubContent = (EditText) findViewById(R.id.pub_discuss_detail) ;
		btnPub = (Button) findViewById(R.id.pub_discuss_btn) ;
		
		btnPub.setOnClickListener(this) ;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_left_tv:
		case R.id.header_pre_iv:
			finish();
			break;
			
		case R.id.pub_discuss_btn :
			Util.toastInfo(PubDiscussActivity.this, "" + etPubTitle.getText().toString() + "-----> " +  etPubContent.getText().toString()) ;
			break;
		}
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		System.out.println("flag----" + flag);
		switch (flag) {

		}

	}
}
