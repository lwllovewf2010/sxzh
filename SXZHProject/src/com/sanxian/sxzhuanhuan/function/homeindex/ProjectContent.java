package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.ArrayList;
import java.util.List;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class ProjectContent extends BaseActivity implements OnClickListener{

private List<View> listViews; // Tab页面列表		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_content);
		ScUtil sc=new ScUtil(this);
		
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		View topicDescribe=mInflater.inflate(R.layout.topic_content_describe, null);
		View topicComment=mInflater.inflate(R.layout.topic_content_comment, null);
		
		initCommnet(topicComment);
		
		
		listViews.add(topicDescribe);
		listViews.add(topicComment);
		
		sc.showSc("创意描述", "讨论大厅", listViews);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topic_content_comment_publish_but:
			Intent intent=new Intent(this, PublishComment.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	
	/**
	 * 初始化用户评论页面
	 * @param view
	 */
	private void initCommnet(View view){
		Button butCommnet=(Button)view.findViewById(R.id.topic_content_comment_publish_but);
		butCommnet.setOnClickListener(this);
	}
	

}
