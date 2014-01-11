package com.sanxian.sxzhuanhuan.function.homeindex;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 发表话题
 * @author luozhiren
 *
 */
public class PublishTopic extends BaseActivity implements OnClickListener {
	
	private EditText topicTitle,topicDetailedIntroduce;
	private TextView topicType ,topicEara,topicProfession ;
	private RelativeLayout topicTypeRel,topicAreaRel,topicProfessionRel;
	private CheckBox topicXieyi;
	private Button topicSubmit;
	
	
	private static final int SHOW_TYPE=1;
	private static final int SHOW_AREA=2;
	private static final int SHOW_PROFESSION=3;
	
	
	private CharSequence[] testType={"创意","项目"};
	private CharSequence[] testArea={"北京","上海","深圳"};
	private CharSequence[] testProfession={"互联网","科技"}; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_topic);
		
		inti();
	}

	
	
	
	@Override
	public void refresh(Object... param) {
		
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id, final Bundle bundle) {
		switch (id) {
		case SHOW_TYPE:
			
			return new AlertDialog.Builder(PublishTopic.this).setItems(testType, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					topicType.setText(testType[which].toString());
				}
				
			}).create();
					
		case SHOW_AREA:
			return new AlertDialog.Builder(PublishTopic.this).setItems(testArea, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					topicEara.setText(testArea[which]);
				}
				
			}).create();
		case SHOW_PROFESSION:
			return new AlertDialog.Builder(PublishTopic.this).setItems(testProfession, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					topicProfession.setText(testProfession[which]);
				}
				
			}).create();
			
		default:
			return null;
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		   //类型
		case R.id.publish_topic_topic_type_rel:
			removeDialog(SHOW_TYPE);
			showDialog(SHOW_TYPE, null);
			break;
			//地区
		case R.id.publish_topic_topic_area_rel:
			removeDialog(SHOW_AREA);
			showDialog(SHOW_AREA, null);
			break;
			//行业
		case R.id.publish_topic_topic_profession_rel:
			removeDialog(SHOW_PROFESSION);
			showDialog(SHOW_PROFESSION, null);
			break;
			//协议
		case R.id.publish_topic_xieyi:
			//if( )
			break;
		default:
			break;
		}
	}
	
	/**
	 * 初始化数据
	 */
	private void inti(){
		initView();
		setTitle("话题发布");
		setLeft("取消");
		displayRight();
		topicTitle=(EditText)findViewById(R.id.publish_topic_topic_title);		
		topicDetailedIntroduce=(EditText)findViewById(R.id.publish_topic_topic_detailed_introduce);		
		topicType=(TextView)findViewById(R.id.publish_topic_type);
		topicEara=(TextView)findViewById(R.id.publish_topic_eara);
		topicProfession=(TextView)findViewById(R.id.publish_topic_profession);
		topicTypeRel=(RelativeLayout)findViewById(R.id.publish_topic_topic_type_rel);
		topicTypeRel.setOnClickListener(this);
		topicAreaRel=(RelativeLayout)findViewById(R.id.publish_topic_topic_area_rel);
		topicAreaRel.setOnClickListener(this);
		topicProfessionRel=(RelativeLayout)findViewById(R.id.publish_topic_topic_profession_rel);
		topicProfessionRel.setOnClickListener(this);
		topicXieyi=(CheckBox)findViewById(R.id.publish_topic_xieyi);
		topicXieyi.setOnClickListener(this);
		topicSubmit=(Button)findViewById(R.id.publish_topic_submit);
		topicSubmit.setOnClickListener(this);
		topicSubmit.setEnabled(false);
	}
	

}
