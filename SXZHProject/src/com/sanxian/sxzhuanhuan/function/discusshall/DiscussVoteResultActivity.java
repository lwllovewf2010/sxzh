package com.sanxian.sxzhuanhuan.function.discusshall;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.CommonHeader;
import com.sanxian.sxzhuanhuan.entity.DiscussVoteResultInfo;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscussVoteResultAdapter;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscusshallIndexAdapter;

/**
 * 
 * @Title: DiscussVoteResultActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshallx
 * @Description: 
 * @author zhangfl@sanxian.com
 * @date 2014-2-21 上午11:47:44
 * @version V1.0
 */
public class DiscussVoteResultActivity extends BaseActivity implements
		OnClickListener{
	
	private CommonHeader conHeader = null ;
	private TextView tvVoteType = null ;
	private TextView tvVoteTitle= null ;
	private TextView tvVotePubname = null ;
	private TextView tvVotePubtime = null ;
	private TextView tvVoteSurplusTime = null ;
	private TextView tvVoteCount = null ;
	private ListView lvResult = null ;
	private TextView tvVoteResultInfo = null ;
	
	private DiscussVoteResultAdapter adapter = null ;
	private List<DiscussVoteResultInfo> infos = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent() ;
		if(null != intent) {
		}
		
		setContentView(R.layout.vote_result);
		
		initWidget() ;
		
		adapter = new DiscussVoteResultAdapter(DiscussVoteResultActivity.this, infos);
		lvResult.setAdapter(adapter);
	}


	private void initWidget() {
		conHeader = (CommonHeader) findViewById(R.id.common_header) ;
		conHeader.show(true, true , "返回", true, "投票结果", false , "" , false) ;
		conHeader.ivPre.setOnClickListener(this);
		conHeader.tvLeft.setOnClickListener(this) ;
		
		tvVoteType = (TextView) findViewById(R.id.vote_type) ;
		tvVoteTitle = (TextView) findViewById(R.id.vote_title) ;
		tvVotePubname = (TextView) findViewById(R.id.vote_pubname) ;
		tvVotePubtime = (TextView) findViewById(R.id.vote_pubtime) ;
		tvVoteSurplusTime = (TextView) findViewById(R.id.vote_surplustime) ;
		tvVoteCount = (TextView) findViewById(R.id.vote_count) ;
		lvResult = (ListView) findViewById(R.id.vote_result_lv) ;
		tvVoteResultInfo = (TextView) findViewById(R.id.vote_result_info) ;

		initData() ;
	}
	
	private void initData() {
		infos = new ArrayList<DiscussVoteResultInfo>() ;
		DiscussVoteResultInfo info = new DiscussVoteResultInfo() ;
		for (int i = 0; i < 3; i++) {
			info.setId("" + i ) ;
			info.setVotePubname("张三" + i + i) ;
			info.setVoteResult("" + i%3 + "%(" + 12 + i + "票)") ; 
			infos.add(info) ;
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
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.header_left_tv :
			case R.id.header_pre_iv :
				finish();
				break ;
		}
	}

}
