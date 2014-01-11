package com.sanxian.sxzhuanhuan.function.homeindex;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
 * @author luozhiren
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
	
	
	private CharSequence[] testType={"创意","项目"};
	private CharSequence[] testArea={"北京","上海","深圳"};
	private CharSequence[] testProfession={"互联网","科技"}; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_originality);
		
		init();
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
		
		Button right=(Button)findViewById(R.id.title_right);
		right.setOnClickListener(this);
//		findViewById(R.id.title_btn_left).setOnClickListener(this);
		originalityTitle=(EditText)findViewById(R.id.publish_originality_originality_title); //需求标题
		originalityIntroduce=(EditText)findViewById(R.id.publish_originality_originality_introduce); //需求简介
		originalityNetaddress=(EditText)findViewById(R.id.publish_originality_originality_netaddress); //需求视频播放地址
		originalityDetailedIntroduce=(EditText)findViewById(R.id.publish_originality_originality_detailed_introduce); //需求详细介绍
		originalityType=(TextView)findViewById(R.id.publish_originality_type); //发布类型
		originalityEara=(TextView)findViewById(R.id.publish_originality_eara); //发布地区
		originalityProfession=(TextView)findViewById(R.id.publish_originality_profession); //发布行业
		originalityImage=(ImageView)findViewById(R.id.publish_originality_image_1); //添加需求图片
		originalityTypeRel=(RelativeLayout)findViewById(R.id.publish_originality_originality_type_rel); //类型
		originalityTypeRel.setOnClickListener(this);
		originalityAreaRel=(RelativeLayout)findViewById(R.id.publish_originality_originality_area_rel); //地区
		originalityAreaRel.setOnClickListener(this);
		originalityProfessionRel=(RelativeLayout)findViewById(R.id.publish_originality_originality_profession_rel); //行业
		originalityProfessionRel.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	   switch (v.getId()) {
	   //类型
	case R.id.publish_originality_originality_type_rel:
		removeDialog(SHOW_TYPE);
		showDialog(SHOW_TYPE, null);
		break;
		//地区
	case R.id.publish_originality_originality_area_rel:
		removeDialog(SHOW_AREA);
		showDialog(SHOW_AREA, null);
		break;
		//行业
	case R.id.publish_originality_originality_profession_rel:
		removeDialog(SHOW_PROFESSION);
		showDialog(SHOW_PROFESSION, null);
		break;
	case R.id.title_right:
		Intent intent=new Intent(this, PublishProjectReport.class);
		startActivity(intent);
		break;
	case R.id.title_btn_left:
		this.finish();
		break;
	default:
		break;
	}
		
	}
	
	@Override
	public void refresh(Object... param) {
		
	}
	
}

