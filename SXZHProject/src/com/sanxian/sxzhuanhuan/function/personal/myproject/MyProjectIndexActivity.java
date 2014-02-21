package com.sanxian.sxzhuanhuan.function.personal.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.function.personal.myproject.adapter.MyProjectIndexAdapter;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 我的项目首页
 * @author joe
 *
 */
public class MyProjectIndexActivity extends BaseActivity {
    private ListView project_list;
    private LinearLayout filter_layout,shaixuan_layout;
    private TextView bg_text;
    private TestAPI api = null;
    private Map<String,String> input = null;
    private final int PROJECT_LIST_REQUEST = 25;
    private boolean isshow = false;
    private Animation showAction, hideAction;
    private TextView rootall,rootstart,rootjoin,suball,subpreheat,subongoing,subucce,subfail;
    private String rootcoosebg = "#efefef";
    private String rootnocoosebg = "#f7f7f7";
    private String subcoose = "#000000";
    private String subnocoose = "#444a4d";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myproject_index);
		initView();
		initListener();
		initData();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("我的项目");
		setRight("筛选");
		project_list = (ListView)findViewById(R.id.project_list);
		filter_layout = (LinearLayout)findViewById(R.id.filter_layout);
		shaixuan_layout = (LinearLayout)findViewById(R.id.shaixuan_layout);
		bg_text = (TextView)findViewById(R.id.bg_text);
		bg_text.setBackgroundColor(Color.argb(100, 0, 0, 0));
		
		rootall = (TextView)findViewById(R.id.rootall);
		rootstart = (TextView)findViewById(R.id.rootstart);
		rootjoin = (TextView)findViewById(R.id.rootjoin);
		suball = (TextView)findViewById(R.id.suball);
		subpreheat = (TextView)findViewById(R.id.subpreheat);
		subongoing = (TextView)findViewById(R.id.subongoing);
		subucce = (TextView)findViewById(R.id.subucce);
		subfail = (TextView)findViewById(R.id.subfail);
		
		showAction = AnimationUtils.loadAnimation(this, R.anim.push_top_in2);
		hideAction = AnimationUtils.loadAnimation(this, R.anim.push_top_out2);
		
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);
		bg_text.setOnClickListener(this);
		shaixuan_layout.setOnClickListener(this);
		
		rootall.setOnClickListener(this);
		rootstart.setOnClickListener(this);
		rootjoin.setOnClickListener(this);
		suball.setOnClickListener(this);
		subpreheat.setOnClickListener(this);
		subongoing.setOnClickListener(this);
		subucce.setOnClickListener(this);
		subfail.setOnClickListener(this);
		
	}
 
	private void initData(){
		api = new TestAPI();
		input = new HashMap<String, String>();
		input.put("pmode", "1") ;
		input.put("pagesize", "10") ;
		api.getCPPData(input, this,PROJECT_LIST_REQUEST) ;
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case PROJECT_LIST_REQUEST:
			if (param.length > 0 && param[1] != null
			&& param[1] instanceof String) {
		String jsondata = param[1].toString();
//		System.out.println(jsondata);
		List<ProjectInfo> projectInfos = (ArrayList<ProjectInfo>) JSONParser.getProjectInfo(jsondata);
		project_list.setAdapter( new MyProjectIndexAdapter(MyProjectIndexActivity.this, projectInfos)) ;
	}
			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.title_right:
			showorhide();
			break;
		case R.id.bg_text:
			showorhide();
			break;
		case R.id.rootall:
			rootall.setBackgroundColor(Color.parseColor(rootcoosebg));
			rootstart.setBackgroundColor(Color.parseColor(rootnocoosebg));
			rootjoin.setBackgroundColor(Color.parseColor(rootnocoosebg));
			rootall.setTextColor(Color.parseColor(subcoose));
			rootstart.setTextColor(Color.parseColor(subnocoose));
			rootjoin.setTextColor(Color.parseColor(subnocoose));
			break;
		case R.id.rootstart:
			rootall.setBackgroundColor(Color.parseColor(rootnocoosebg));
			rootstart.setBackgroundColor(Color.parseColor(rootcoosebg));
			rootjoin.setBackgroundColor(Color.parseColor(rootnocoosebg));
			rootall.setTextColor(Color.parseColor(subnocoose));
			rootstart.setTextColor(Color.parseColor(subcoose));
			rootjoin.setTextColor(Color.parseColor(subnocoose));
			break;
		case R.id.rootjoin:
			rootall.setBackgroundColor(Color.parseColor(rootnocoosebg));
			rootstart.setBackgroundColor(Color.parseColor(rootnocoosebg));
			rootjoin.setBackgroundColor(Color.parseColor(rootcoosebg));
			rootall.setTextColor(Color.parseColor(subnocoose));
			rootstart.setTextColor(Color.parseColor(subnocoose));
			rootjoin.setTextColor(Color.parseColor(subcoose));
			break;
		case R.id.suball:
			suball.setTextColor(Color.parseColor(subcoose));
			subpreheat.setTextColor(Color.parseColor(subnocoose));
			subongoing.setTextColor(Color.parseColor(subnocoose));
			subucce.setTextColor(Color.parseColor(subnocoose));
			subfail.setTextColor(Color.parseColor(subnocoose));
			showorhide();
			break;
		case R.id.subpreheat:
			suball.setTextColor(Color.parseColor(subnocoose));
			subpreheat.setTextColor(Color.parseColor(subcoose));
			subongoing.setTextColor(Color.parseColor(subnocoose));
			subucce.setTextColor(Color.parseColor(subnocoose));
			subfail.setTextColor(Color.parseColor(subnocoose));
			showorhide();
			break;
		case R.id.subongoing:
			suball.setTextColor(Color.parseColor(subnocoose));
			subpreheat.setTextColor(Color.parseColor(subnocoose));
			subongoing.setTextColor(Color.parseColor(subcoose));
			subucce.setTextColor(Color.parseColor(subnocoose));
			subfail.setTextColor(Color.parseColor(subnocoose));
			showorhide();
			break;
		case R.id.subucce:
			suball.setTextColor(Color.parseColor(subnocoose));
			subpreheat.setTextColor(Color.parseColor(subnocoose));
			subongoing.setTextColor(Color.parseColor(subnocoose));
			subucce.setTextColor(Color.parseColor(subcoose));
			subfail.setTextColor(Color.parseColor(subnocoose));
			showorhide();
			break;
		case R.id.subfail:
			suball.setTextColor(Color.parseColor(subnocoose));
			subpreheat.setTextColor(Color.parseColor(subnocoose));
			subongoing.setTextColor(Color.parseColor(subnocoose));
			subucce.setTextColor(Color.parseColor(subnocoose));
			subfail.setTextColor(Color.parseColor(subcoose));
			showorhide();
			break;
		default:
			break;
		}
	}
    
	
	/**
	 * 隐藏显示筛选框
	 * joe
	 */
	private void showorhide(){
		if(!isshow){
			filter_layout.setVisibility(View.VISIBLE);
			filter_layout.startAnimation(showAction);
			isshow = true;
		}else{
			filter_layout.setVisibility(View.INVISIBLE);
			filter_layout.startAnimation(hideAction);
			isshow = false;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}
}
