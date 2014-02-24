package com.sanxian.sxzhuanhuan.function.discusshall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;

/**
 * 
 * @Title: DiscussVoteActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshallx
 * @Description: 
 * @author zhangfl@sanxian.com
 * @date 2014-2-21 上午11:47:44
 * @version V1.0
 */
public class DiscussVoteActivity extends BaseActivity implements
		OnClickListener{
	
	private int choiceFlag = 0 ;
	private String projectID = "" ;
	private CommonHeader conHeader = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent() ;
		if(null != intent) {
			projectID = intent.getStringExtra("project_id") ;
			choiceFlag = intent.getIntExtra("choice_flag", 0) ;
			System.out.println("projectID---" + projectID);
		}
		
		if( 1 == choiceFlag) 
			setContentView(R.layout.vote_single);
		else 
			setContentView(R.layout.vote_muilt);
		
		initWidget() ;
	}


	private void initWidget() {
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		conHeader.show(true, true , "返回", true, "项目介绍", false , "" , false) ;
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		

	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		
		}
		
	}
	
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				finish();
				break ;
		}
	}

}
