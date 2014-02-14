package com.sanxian.sxzhuanhuan.function.homeindex.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.function.homeindex.PublishComment;

/**
 * 
 * @Title: ProjectDetailActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex
 * @Description: 项目详情
 * @author zhangfl@sanxian.com
 * @date 2014-1-14 上午11:47:44
 * @version V1.0
 */
public class ProjectDetailActivity extends BaseActivity implements
		OnClickListener {

	private TextView tvProname = null ;
	private ImageView ivPrologo = null ;
	private TextView tvProDetail = null ;
//	private ImageView ivBack = null ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.project_detail);

		init() ;

	}
	
	private void init() {
		tvProname = (TextView) findViewById(R.id.project_detail_tv_proname) ;
		ivPrologo = (ImageView) findViewById(R.id.project_detail_iv_prologo) ;
		tvProDetail = (TextView) findViewById(R.id.project_detail_tv_pridetail) ;
//		ivBack = (ImageView) findViewById(R.id.project_content_footer_iv_back) ;
		
//		ivBack.setOnClickListener(this) ;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.topic_content_comment_publish_but:
				Intent intent = new Intent(this, PublishComment.class);
				startActivity(intent);
				break;
//			case R.id.project_content_footer_iv_back :
//				
//				break ;
		}
	}

	/**
	 * 初始化用户评论页面
	 * 
	 * @param view
	 */
	private void initCommnet(View view) {
		Button butCommnet = (Button) view
				.findViewById(R.id.topic_content_comment_publish_but);
		butCommnet.setOnClickListener(this);
	}

}
