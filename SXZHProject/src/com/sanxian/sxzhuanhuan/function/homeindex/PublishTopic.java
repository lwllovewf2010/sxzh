package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.homeindex.originality.OriginalityActivity;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.util.Util;


/**
 * 发表话题
 * @author joe
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
	
	private final int FROM_TOPIC_ROOT_CODE = 112;
	private final int FROM_TOPIC_SUB_CODE = 118;
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
	
	private CommonAPI api = null;
	private final int CREATIVE = 102;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_topic);
		initView();
		initListener();
	}

	
	
	
	@Override
	public void refresh(Object... param) {
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case CREATIVE:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if(status == 0){
						Util.toastInfo(this, "创意发布成功");
						String creativeID = json.optString("content");
						Intent intent = new Intent(this,OriginalityActivity.class);
						intent.putExtra("creativeID", creativeID);
						startActivity(intent);
						finish();
					}else{
						Util.toastInfo(this, "创意发布失败");	
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
		
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
//		removeDialog(SHOW_TYPE);
//		showDialog(SHOW_TYPE, null);
		Intent typeintent = new Intent(PublishTopic.this,GetRootCommonList.class);
		typeintent.putExtra("type", "mode");
		typeintent.putExtra("is_get_last","0");
		startActivityForResult(typeintent, REQUESTCODE);
		break;
		//地区
	case R.id.publish_topic_topic_area_rel:
//		removeDialog(SHOW_AREA);
//		showDialog(SHOW_AREA, null);
		Intent areaintent = new Intent(PublishTopic.this,GetRootCommonList.class);
		areaintent.putExtra("type", "region_province");
		areaintent.putExtra("is_get_last","0");
		startActivityForResult(areaintent, REQUESTCODE);
		break;
		//行业
	case R.id.publish_topic_topic_profession_rel:
//		removeDialog(SHOW_PROFESSION);
//		showDialog(SHOW_PROFESSION, null);
		Intent categoryintent = new Intent(PublishTopic.this,GetRootCommonList.class);
		categoryintent.putExtra("type", "category");
		categoryintent.putExtra("is_get_last","0");
		startActivityForResult(categoryintent, REQUESTCODE);
		break;
	case R.id.title_Left:
		this.finish();
		break;
	case R.id.publish_topic_submit:
		String title = topicTitle.getText().toString().trim();
		if(!"".equals(title)){
			if(!"".equals(area_root_id)){
				if(!"".equals(category_id)){
					String content = topicDetailedIntroduce.getText().toString().trim();
					if(!"".equals(content)){
						if(topicXieyi.isChecked()){
							if(api == null){
								api = new CommonAPI();
							}
							SharedPreferences spf = this.getSharedPreferences("login_user", 0) ;
							String open_id = spf.getString("open_id", "");
							String token = spf.getString("token", "");
							if("".equals(open_id) || "".equals(token)){
//								UIHelper.showLoginActivity(PublishTopic.this);
								Intent intent = new Intent(this, LoginActivity.class);
								startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
							}else{
								Map<String,String> paramsmap = new HashMap<String, String>();
								paramsmap.put("open_id",open_id);
//								paramsmap.put("token",token);
								paramsmap.put("org_name",title);
								paramsmap.put("province_id",area_root_id);
								paramsmap.put("city_id",area_sub_id);
								paramsmap.put("category_id",category_id);
								paramsmap.put("org_desc",content);
								api.manageCreative("create", paramsmap, this, CREATIVE);
							}
							
						}else{
							Util.toastInfo(this, "你还没有同意三弦项目发布要求");
						}
						
					}else{
						Util.toastInfo(this, "话题内容不能为空");
					}
				}else{
					Util.toastInfo(this, "行业未选择");
				}
			}else{
				Util.toastInfo(this, "地区未选择");
			}
		}else{
			Util.toastInfo(this, "话题名称不能为空");
		}
		break;
	default:
		break;
	}
	}
	
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("话题发布");
		setLeft("取消");
		button_left.setOnClickListener(this);
		displayRight();
		topicTitle=(EditText)findViewById(R.id.publish_topic_topic_title);		
		topicDetailedIntroduce=(EditText)findViewById(R.id.publish_topic_topic_detailed_introduce);		
		topicType=(TextView)findViewById(R.id.publish_topic_type);
		topicEara=(TextView)findViewById(R.id.publish_topic_eara);
		topicProfession=(TextView)findViewById(R.id.publish_topic_profession);
		topicTypeRel=(RelativeLayout)findViewById(R.id.publish_topic_topic_type_rel);
		topicAreaRel=(RelativeLayout)findViewById(R.id.publish_topic_topic_area_rel);
		topicProfessionRel=(RelativeLayout)findViewById(R.id.publish_topic_topic_profession_rel);
		topicXieyi=(CheckBox)findViewById(R.id.publish_topic_xieyi);
		topicSubmit=(Button)findViewById(R.id.publish_topic_submit);
	}




	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		topicTypeRel.setOnClickListener(this);
		topicAreaRel.setOnClickListener(this);
		topicProfessionRel.setOnClickListener(this);
		topicXieyi.setOnClickListener(this);
		topicSubmit.setOnClickListener(this);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == FROM_TOPIC_ROOT_CODE){
			Bundle bundle = data.getExtras();
			String type = bundle.getString("type");
			if("mode".equals(type)){
				mode_id = bundle.getString("root_id");
				mode_name = bundle.getString("root_name");
				topicType.setText(mode_name);
			}else if("category".equals(type)){
				category_id = bundle.getString("root_id");
				category_name = bundle.getString("root_name");
				topicProfession.setText(category_name);
			}
			
		}else if(resultCode == FROM_TOPIC_SUB_CODE){
			Bundle bundle = data.getExtras();
			String type = bundle.getString("type");
			if("region_city".equals(type)){
				area_root_id = bundle.getString("root_id");
				area_root_name = bundle.getString("root_name");
				area_sub_id = bundle.getString("sub_id");
				area_sub_name = bundle.getString("sub_name");
				topicEara.setText(area_root_name + " " + area_sub_name);
			}else if("category".equals(type)){
				category_id = bundle.getString("sub_id");
				category_name = bundle.getString("sub_name");
				topicProfession.setText(bundle.getString("root_name") + " " +category_name);
			}
			
		}
	}

}
