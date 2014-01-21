package com.sanxian.sxzhuanhuan.function.homeindex;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * 发布项目的第二步
 * @author luozhiren
 *
 */
public class PublishProjectReport extends BaseActivity implements OnClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_project_report);
		init();
	}

	@Override
	public void refresh(Object... param) {
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.title_right:
			Intent intent=new Intent(this, PublishProjectLast.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 初始化数据
	 */
	private void init(){
		initView();
		setTitle("需求发布");
		setLeft("重填信息");
		setRight("提交审核");
		findViewById(R.id.title_Left).setOnClickListener(this);
		findViewById(R.id.title_right).setOnClickListener(this);
		TextView titleIndo=(TextView)findViewById(R.id.tvTitleIndo);
		titleIndo.setText("第二步/共三步");
	}
	
}
