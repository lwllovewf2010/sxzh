package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.HashMap;
import java.util.Map;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * 发布项目的第二步
 * @author luozhiren
 *
 */
public class PublishProjectReport extends BaseActivity implements OnClickListener{
	private EditText publish_project_report_projectcount;
	private EditText publish_project_report_projectcountxiane;
	private EditText publish_project_report_day;
	private EditText publish_project_report_tuikuanshoum_rel;
	private EditText publish_project_report_huibaotitle;
	private EditText publish_project_report_huibaotime;
	private EditText publish_project_report_huinbaocontent_rel;
	private EditText publish_project_renchu;
	private EditText publish_project_report_zhuanlibianhao;
	private EditText publish_project_report_zhuanliname;
	private EditText publish_project_report_rate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_project_report);
		init();
	}
	public void initView(){
		super.initView();
		publish_project_report_projectcount=(EditText)this.findViewById(R.id.publish_project_report_projectcount);
		publish_project_report_projectcountxiane=(EditText)this.findViewById(R.id.publish_project_report_projectcountxiane);
		publish_project_report_day=(EditText)this.findViewById(R.id.publish_project_report_day);
		publish_project_report_tuikuanshoum_rel=(EditText)this.findViewById(R.id.publish_project_report_tuikuanshoum_rel);
		publish_project_report_huibaotitle=(EditText)this.findViewById(R.id.publish_project_report_huibaotitle);
		publish_project_report_huibaotime=(EditText)this.findViewById(R.id.publish_project_report_huibaotime);
		publish_project_report_huinbaocontent_rel=(EditText)this.findViewById(R.id.publish_project_report_huinbaocontent_rel);
		publish_project_renchu=(EditText)this.findViewById(R.id.publish_project_renchu);
		publish_project_report_zhuanlibianhao=(EditText)this.findViewById(R.id.publish_project_report_zhuanlibianhao);
		publish_project_report_zhuanliname=(EditText)this.findViewById(R.id.publish_project_report_zhuanliname);
		publish_project_report_rate=(EditText)this.findViewById(R.id.publish_project_report_rate);
	}
	public void initListener(){
		super.initListener();
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
			//此处需判断为空（略）
			getMap();
			Intent intent=new Intent(this, PublishProjectLast.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	Boolean  isLimit=true;//默认限制人数
	public Map<String,String> getMap(){
		
		Map<String,String> tempMap=new HashMap<String, String>();
		tempMap.put("project_money", publish_project_report_projectcount.getText().toString());
		tempMap.put("reward_money", publish_project_report_projectcountxiane.getText().toString());
		tempMap.put("project_days", publish_project_report_day.getText().toString());
		tempMap.put("project_money_refund", publish_project_report_tuikuanshoum_rel.getText().toString());
		tempMap.put("reward_name", publish_project_report_huibaotitle.getText().toString());
		tempMap.put("reward_return_days", publish_project_report_huibaotime.getText().toString());
		tempMap.put("reward_content", publish_project_report_huinbaocontent_rel.getText().toString());
		tempMap.put("reward_limit", isLimit?"1":"0");//默认限制人数，待定
		tempMap.put("reward_person", publish_project_renchu.getText().toString());
		tempMap.put("reward_post", "1");//默认快递，待定
		tempMap.put("reward_patent_id", publish_project_report_zhuanlibianhao.getText().toString());
		tempMap.put("reward_patent_name", publish_project_report_zhuanliname.getText().toString());
		tempMap.put("reward_mode", publish_project_report_rate.getText().toString());
		PublishConstant.paramMap.putAll(tempMap);
		return PublishConstant.paramMap;

		
		
//		project_days true (int) 筹集天数 
//		project_money_refund false (string) 退款说明 
//		reward_mode true (int) 筹集回报按百分比均分 10的整数倍,例如：100 90 80 70 10 
		
//		project_money true (int) 项目总筹资金额 
//		reward_money true (int) 单笔筹资限额 
		
//		reward_name true (string) 回报标题 
//		reward_content true (string) 回报内容描述 
//		reward_return_days true (int) 回报时间 
//		reward_limit true (int) 是否限定入股人数 1=限 0=不限 
//		reward_person true (int) 限定入股人数 
//		reward_post true (int) 邮寄 0=不包邮 1=快递 2=平邮/EMS 
//		reward_patent_id false (string) 专利编号 
//		reward_patent_name false (string) 专利名 
//		project_qq false (string) 发起人QQ 
//		project_mobile true (string) 发起人手机号 
//		project_realname true (string) 发起人真实姓名 

		
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
