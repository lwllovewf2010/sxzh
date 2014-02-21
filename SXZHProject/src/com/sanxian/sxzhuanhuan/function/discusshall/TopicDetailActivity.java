package com.sanxian.sxzhuanhuan.function.discusshall;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: TopicDetailActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshall
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-2-20 下午4:48:14
 * @version V1.0
 */
public class TopicDetailActivity extends BaseActivity implements
		OnClickListener {
	private String topicID = "" ;
	private TestAPI api = null;
	private Map<String, String> input = null;
	private CommonHeader conHeader = null;

	private TextView topicTitle = null ;
	private TextView pubInfo = null ;
	private ImageView topicGoing = null ;
	private TextView topicStatus = null ;
	private TextView topicContent = null ;
	private Button btnTopicClose = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.topic_detail);

		Intent intent = getIntent() ;
		if(null != intent) {
			topicID = intent.getStringExtra("topic_id") ;
			System.out.println("topic_id:" + topicID);
		}
		
		init();
		conHeader.show(true, true , "返回", true, "话题详情", false , "" , false) ;
	}

	private void init() {
		api = new TestAPI();
		input = new HashMap<String, String>();

		conHeader = (CommonHeader) findViewById(R.id.common_header);
		topicTitle = (TextView) findViewById(R.id.topic_detail_tv_topicname) ;
		pubInfo = (TextView) findViewById(R.id.topic_detail_tv_topic_pubinfo) ;
		topicGoing = (ImageView) findViewById(R.id.topic_detail_iv_ongoing) ;
		topicStatus = (TextView) findViewById(R.id.topic_detail_tv_topic_status) ;
		topicContent = (TextView) findViewById(R.id.topic_detail_tv_topic_content) ;
		btnTopicClose = (Button) findViewById(R.id.topic_detail_btn_close) ;

		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this);
		btnTopicClose.setOnClickListener(this) ;
		
		topicStatus.setVisibility(View.GONE) ;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.header_left_tv:
			case R.id.header_pre_iv:
				finish();
				break;
				
			case R.id.topic_detail_btn_close :
				Util.toastInfo(TopicDetailActivity.this, "关闭此会话") ;
				topicGoing.setVisibility(View.GONE) ;
				topicStatus.setVisibility(View.VISIBLE) ;
				btnTopicClose.setVisibility(View.INVISIBLE) ;
				break ; 
		}
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
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		input.clear() ;
	}
	
}
