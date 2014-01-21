package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.HashMap;
import java.util.Map;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 
 * 发布需求
 * @author joe
 *
 */
public class PublishOriginality extends BaseActivity implements OnClickListener{
	
	private EditText originalityTitle,originalityIntroduce,originalityNetaddress,originalityDetailedIntroduce;
	private TextView originalityType ,originalityEara,originalityProfession ;
	private ImageView originalityImage;
	private RelativeLayout originalityTypeRel,originalityAreaRel,originalityProfessionRel;
	
	
	private static final int SHOW_TYPE=1;
	private static final int SHOW_AREA=2;
	private static final int SHOW_PROFESSION=3;
	private static final int CREATEPROJECTONE = 4;
	
	private final int FROM_PUBLI_ROOT_CODE = 112;
	private final int FROM_PUBLI_SUB_CODE = 118;
	private final int REQUESTCODE = 113;
	private String mode_id = "";
	private String mode_name = "";
	private String category_id = "";
	private String category_name = "";
	private String area_root_id = "";
	private String area_root_name = "";
	private String area_sub_id = "";
	private String area_sub_name = "";
	
	private CharSequence[] testType={"创意","项目"};
	private CharSequence[] testArea={"北京","上海","深圳"};
	private CharSequence[] testProfession={"互联网","科技"}; 
	CommonAPI api=new CommonAPI();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_originality);		
		init();
		initListener();
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id, final Bundle bundle) {
		switch (id) {
		case SHOW_TYPE:
			
			return new AlertDialog.Builder(PublishOriginality.this).setItems(testType, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					originalityType.setText(testType[which].toString());
				}
				
			}).create();
					
		case SHOW_AREA:
			return new AlertDialog.Builder(PublishOriginality.this).setItems(testArea, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					originalityEara.setText(testArea[which]);
				}
				
			}).create();
		case SHOW_PROFESSION:
			return new AlertDialog.Builder(PublishOriginality.this).setItems(testProfession, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					originalityProfession.setText(testProfession[which]);
				}
				
			}).create();
			
		default:
			return null;
		}
		
	}
	
	/**
	 * 初始化数据
	 */
	private void init(){
		initView();
		setLeft("取消");
		setRight("下一步");
		setTitle("需求发布");
		TextView titleIndo=(TextView)findViewById(R.id.tvTitleIndo);
		titleIndo.setText("第一步/共三步");
		originalityTitle=(EditText)findViewById(R.id.publish_originality_originality_title); //需求标题
		originalityIntroduce=(EditText)findViewById(R.id.publish_originality_originality_introduce); //需求简介
		originalityNetaddress=(EditText)findViewById(R.id.publish_originality_originality_netaddress); //需求视频播放地址
		originalityDetailedIntroduce=(EditText)findViewById(R.id.publish_originality_originality_detailed_introduce); //需求详细介绍
		originalityType=(TextView)findViewById(R.id.publish_originality_type); //发布类型
		originalityEara=(TextView)findViewById(R.id.publish_originality_eara); //发布地区
		originalityProfession=(TextView)findViewById(R.id.publish_originality_profession); //发布行业
		originalityImage=(ImageView)findViewById(R.id.publish_originality_image_1); //添加需求图片
		originalityTypeRel=(RelativeLayout)findViewById(R.id.publish_originality_originality_type_rel); //类型
		originalityAreaRel=(RelativeLayout)findViewById(R.id.publish_originality_originality_area_rel); //地区
		originalityProfessionRel=(RelativeLayout)findViewById(R.id.publish_originality_originality_profession_rel); //行业
		
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		originalityTypeRel.setOnClickListener(this);
		originalityAreaRel.setOnClickListener(this);
		originalityProfessionRel.setOnClickListener(this);
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	   switch (v.getId()) {
	   //类型
	case R.id.publish_originality_originality_type_rel:
//		removeDialog(SHOW_TYPE);
//		showDialog(SHOW_TYPE, null);
		Intent typeintent = new Intent(PublishOriginality.this,GetRootCommonList.class);
		typeintent.putExtra("type", "mode");
		startActivityForResult(typeintent,REQUESTCODE);
		break;
		//地区
	case R.id.publish_originality_originality_area_rel:
//		removeDialog(SHOW_AREA);
//		showDialog(SHOW_AREA, null);
		Intent areaintent = new Intent(PublishOriginality.this,GetRootCommonList.class);
		areaintent.putExtra("type", "region_province");
		startActivityForResult(areaintent,REQUESTCODE);
		break;
		//行业
	case R.id.publish_originality_originality_profession_rel:
//		removeDialog(SHOW_PROFESSION);
//		showDialog(SHOW_PROFESSION, null);
		Intent categoryintent = new Intent(PublishOriginality.this,GetRootCommonList.class);
		categoryintent.putExtra("type", "category");
		startActivityForResult(categoryintent,REQUESTCODE);
		break;
	case R.id.title_right:
		api.manageProject("create", getMap(), this,CREATEPROJECTONE);
//		Intent intent=new Intent(this, PublishProjectReport.class);
//		startActivity(intent);
		break;
	case R.id.title_Left:
		this.finish();
		break;
	default:
		break;
	}
		
	}
	public Map<String,String> getMap(){
		PublishConstant.paramMap.clear();
		Map<String,String> tempMap=new HashMap<String, String>();
		tempMap.put("user_id", getOpen_idOrToken()[0]);
		
		
		PublishConstant.paramMap.putAll(tempMap);
		
		
		return PublishConstant.paramMap;
//		user_id true (string) 项目创建人openid 
//		project_name true (string) 项目名 
//		mode_id true (int) 1 1=项目 
//		category_id true (int) 行业id 科技/金融/影视的对应id 
//		province_id true (int) 省id 
//		city_id true (int) 城市id 
//		project_explain false (string) 需求简介 
//		project_logo false (string) Logo 
//		project_video false (string) 网络视频地址URL 
//		project_describe true (string) 详细介绍 
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
	
	@Override
	public void refresh(Object... param) {
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == FROM_PUBLI_ROOT_CODE){
			Bundle bundle = data.getExtras();
			String type = bundle.getString("type");
			if("mode".equals(type)){
				mode_id = bundle.getString("root_id");
				mode_name = bundle.getString("root_name");
				originalityType.setText(mode_name);
			}else if("category".equals(type)){
				category_id = bundle.getString("root_id");
				category_name = bundle.getString("root_name");
				originalityProfession.setText(category_name);
			}
			
		}else if(resultCode == FROM_PUBLI_SUB_CODE){
			Bundle bundle = data.getExtras();
			String type = bundle.getString("type");
			if("region_city".equals(type)){
				area_root_id = bundle.getString("root_id");
				area_root_name = bundle.getString("root_name");
				area_sub_id = bundle.getString("sub_id");
				area_sub_name = bundle.getString("sub_name");
				originalityEara.setText(area_root_name + " " + area_sub_name);
			}else if("category".equals(type)){
				category_id = bundle.getString("sub_id");
				category_name = bundle.getString("sub_name");
				originalityProfession.setText(bundle.getString("root_name") + " " + category_name);
			}
			
		}
	}
}

