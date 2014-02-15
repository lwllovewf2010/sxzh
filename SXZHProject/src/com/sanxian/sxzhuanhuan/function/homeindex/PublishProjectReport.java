package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.HashMap;
import java.util.Map;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.util.Util;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 发布项目的第二步
 * 
 * @author luozhiren
 * 
 */
public class PublishProjectReport extends BaseActivity implements OnClickListener {
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
	// 步骤流程图
	public ImageView img_one;
	public ImageView img_two;
	public TextView txt_one;
	public TextView txt_two;
	private Button submit;
	private Button cancel_submit;
	// 切换限定按钮（checkbox）
	private Button btn_limit_partner;
	private Button btn_patent_limit;
	private Button btn_stock_limit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_project_report);
		init();
		initListener();
		
	}

	public void initView() {
		super.initView();
		publish_project_report_projectcount = (EditText) this.findViewById(R.id.publish_project_report_projectcount);
		publish_project_report_projectcountxiane = (EditText) this.findViewById(R.id.publish_project_report_projectcountxiane);
		publish_project_report_day = (EditText) this.findViewById(R.id.publish_project_report_day);
		publish_project_report_tuikuanshoum_rel = (EditText) this.findViewById(R.id.publish_project_report_tuikuanshoum_rel);
		publish_project_report_huibaotitle = (EditText) this.findViewById(R.id.publish_project_report_huibaotitle);
		publish_project_report_huibaotime = (EditText) this.findViewById(R.id.publish_project_report_huibaotime);
		publish_project_report_huinbaocontent_rel = (EditText) this.findViewById(R.id.publish_project_report_huinbaocontent_rel);
		publish_project_renchu = (EditText) this.findViewById(R.id.publish_project_renchu);
		publish_project_report_zhuanlibianhao = (EditText) this.findViewById(R.id.publish_project_report_zhuanlibianhao);
		publish_project_report_zhuanliname = (EditText) this.findViewById(R.id.publish_project_report_zhuanliname);
		publish_project_report_rate = (EditText) this.findViewById(R.id.publish_project_report_rate);

		img_one = (ImageView) this.findViewById(R.id.img_one);
		img_two = (ImageView) this.findViewById(R.id.img_two);
		img_one.setBackgroundResource(R.drawable.finish);
		img_two.setBackgroundResource(R.drawable.step_two_on);
		txt_one = (TextView) this.findViewById(R.id.txt_one);
		txt_two = (TextView) this.findViewById(R.id.txt_two);
		txt_two.setTextColor(getResources().getColor((R.color.common_font_color)));
		submit = (Button) this.findViewById(R.id.submit);
		cancel_submit = (Button) this.findViewById(R.id.cancel_submit);
		// 切换限定按钮（checkbox）
		btn_limit_partner = (Button) this.findViewById(R.id.btn_limit_partner);
		btn_patent_limit = (Button) this.findViewById(R.id.btn_patent_limit);
		btn_stock_limit = (Button) this.findViewById(R.id.btn_stock_limit);

	}

	public void initListener() {
		super.initListener();
		displayRight();
		submit.setOnClickListener(this);
		cancel_submit.setOnClickListener(this);
		// 切换限定按钮（checkbox）
		btn_limit_partner.setOnClickListener(this);
		btn_patent_limit.setOnClickListener(this);
		btn_stock_limit.setOnClickListener(this);

	}

	@Override
	public void refresh(Object... param) {

	}

	// 验证非空
	public boolean checkInput() {
		String tempRate=publish_project_report_rate.getText().toString().trim();
		if ("".equals(publish_project_report_projectcount.getText().toString().trim())) {
			Util.toastInfo(this, "项目总金额必填！");
			return false;
		} else if ("".equals(publish_project_report_day.getText().toString().trim())) {
			Util.toastInfo(this, "筹集天数必填");
			return false;
		} else if ("".equals(publish_project_report_tuikuanshoum_rel.getText().toString().trim())) {
			Util.toastInfo(this, "退款说明必填");
			return false;
		} else if ("".equals(publish_project_report_huibaotitle.getText().toString().trim())) {
			Util.toastInfo(this, "回报标题必填");
			return false;// 回报标题
		} else if ("".equals(publish_project_report_projectcountxiane.getText().toString().trim())) {
			Util.toastInfo(this, "预购金额必填");
			return false;// 预购金额
		} else if ("".equals(publish_project_report_huibaotime.getText().toString().trim())) {
			Util.toastInfo(this, "回报天数必填");
			return false;// 回报天数
		}else if("".equals(tempRate)){
			Util.toastInfo(this, "筹集回报按百分比均分必须是10的整数倍，并且不超过100");
			return false;
		} else if(!(Integer.parseInt(tempRate)%10==0&&Integer.parseInt(tempRate)<=100)){
			Util.toastInfo(this, "筹集回报按百分比均分必须是10的整数倍，并且不超过100");
			return false;
		}else {
			return true;

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submit:
			// 此处需判断为空（略）
			if (checkInput()) {
				getMap();
				Intent intent = new Intent(this, PublishProjectLast.class);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.cancel_submit:
			this.finish();
			break;
		case R.id.title_Left:
//			this.finish();
			Util.toastInfo(this, "清空");
			break;
		case R.id.btn_limit_partner://股东人数
			if("1".equals(btn_limit_partner.getTag())){
				btn_limit_partner.setBackgroundResource(R.drawable.uncheck_box);
				btn_limit_partner.setTag("0");
			}else{
				btn_limit_partner.setBackgroundResource(R.drawable.check_box);
				btn_limit_partner.setTag("1");
			}

			break;
		case R.id.btn_patent_limit://专利
			if("1".equals(btn_patent_limit.getTag())){
				btn_patent_limit.setBackgroundResource(R.drawable.uncheck_box);
				btn_patent_limit.setTag("0");
			}else{
				btn_patent_limit.setBackgroundResource(R.drawable.check_box);
				btn_patent_limit.setTag("1");
			}
			break;
		case R.id.btn_stock_limit://股票限制
			if("1".equals(btn_stock_limit.getTag())){
				btn_stock_limit.setBackgroundResource(R.drawable.uncheck_box);
				btn_stock_limit.setTag("0");
			}else{
				btn_stock_limit.setBackgroundResource(R.drawable.check_box);
				btn_stock_limit.setTag("1");
			}
			break;
		default:
			break;
		}
	}

	Boolean isLimit = true;// 默认限制人数

	public Map<String, String> getMap() {

		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("project_money", publish_project_report_projectcount.getText().toString());
		tempMap.put("reward_money", publish_project_report_projectcountxiane.getText().toString());
		tempMap.put("project_days", publish_project_report_day.getText().toString());
		tempMap.put("project_money_refund", publish_project_report_tuikuanshoum_rel.getText().toString());
		tempMap.put("reward_name", publish_project_report_huibaotitle.getText().toString());
		tempMap.put("reward_return_days", publish_project_report_huibaotime.getText().toString());
		tempMap.put("reward_content", publish_project_report_huinbaocontent_rel.getText().toString());
		tempMap.put("reward_limit", btn_limit_partner.getTag().toString()+"");// 默认限制人数，待定
		tempMap.put("reward_person", publish_project_renchu.getText().toString());
		tempMap.put("reward_post", "1");// 默认快递，待定
		tempMap.put("reward_patent_id", publish_project_report_zhuanlibianhao.getText().toString());
		tempMap.put("reward_patent_name", publish_project_report_zhuanliname.getText().toString());
		tempMap.put("reward_mode", publish_project_report_rate.getText().toString());
		PublishConstant.paramMap.putAll(tempMap);
		return PublishConstant.paramMap;

		// project_days true (int) 筹集天数
		// project_money_refund false (string) 退款说明
		// reward_mode true (int) 筹集回报按百分比均分 10的整数倍,例如：100 90 80 70 10

		// project_money true (int) 项目总筹资金额
		// reward_money true (int) 单笔筹资限额

		// reward_name true (string) 回报标题
		// reward_content true (string) 回报内容描述
		// reward_return_days true (int) 回报时间
		// reward_limit true (int) 是否限定入股人数 1=限 0=不限
		// reward_person true (int) 限定入股人数
		// reward_post true (int) 邮寄 0=不包邮 1=快递 2=平邮/EMS
		// reward_patent_id false (string) 专利编号
		// reward_patent_name false (string) 专利名
		// project_qq false (string) 发起人QQ
		// project_mobile true (string) 发起人手机号
		// project_realname true (string) 发起人真实姓名

	}

	/**
	 * 初始化数据
	 */
	private void init() {
		initView();
		setTitle("需求发布");
		setLeft("重填信息");
		setRight("提交审核");
		findViewById(R.id.title_Left).setOnClickListener(this);
		findViewById(R.id.title_right).setOnClickListener(this);
		TextView titleIndo = (TextView) findViewById(R.id.tvTitleIndo);
		titleIndo.setText("第二步/共三步");
	}

}
