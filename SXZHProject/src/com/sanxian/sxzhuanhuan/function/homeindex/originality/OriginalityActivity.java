package com.sanxian.sxzhuanhuan.function.homeindex.originality;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.HomeIndexGoodsAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.OriginalityItemDetails;
import com.sanxian.sxzhuanhuan.function.homeindex.PublishComment;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ScUtil;
import com.sanxian.sxzhuanhuan.util.Util;

import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;

public class OriginalityActivity extends BaseActivity implements OnClickListener,OnItemSelectedListener{
	
	
	private List<View> listViews; // Tab页面列表
	
	View topicDescribe; //创意描述
	View topicComment;//创意内容
	TextView topic_content_describe_title;
	TextView topic_content_describe_name;
	TextView topic_content_describe_hangye;
	TextView topic_content_describe_region;
	TextView topic_content_describe_text;
	Button topic_content_button;
	
	private HomeIndexGoodsAPI api = null;
	private final int REQUESTCODE = 12;
	private OriginalityItemDetails originalityItem = new OriginalityItemDetails();
	String creativeID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_content);
		Intent intent = getIntent();
		creativeID = intent.getStringExtra("creativeID");
		
		ScUtil sc=new ScUtil(this);
		
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		topicDescribe=mInflater.inflate(R.layout.topic_content_describe, null);
		topicComment=mInflater.inflate(R.layout.topic_content_comment, null);
		
		initView();
		
		listViews.add(topicDescribe);
		listViews.add(topicComment);
		
		sc.showSc("创意描述", "用户评论", listViews);
		
		initDate();
		
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		topic_content_describe_title = (TextView)topicDescribe.findViewById(R.id.topic_content_describe_title);
		topic_content_describe_name = (TextView)topicDescribe.findViewById(R.id.topic_content_describe_name);
		topic_content_describe_hangye = (TextView)topicDescribe.findViewById(R.id.topic_content_describe_hangye);
		topic_content_describe_region = (TextView)topicDescribe.findViewById(R.id.topic_content_describe_region);
		topic_content_describe_text = (TextView)topicDescribe.findViewById(R.id.topic_content_describe_text);
		topic_content_button = (Button)topicDescribe.findViewById(R.id.topic_content_button);
		//初始化发表评论按钮
		Button butCommnet=(Button)topicComment.findViewById(R.id.topic_content_comment_publish_but);
		Spinner spinner=(Spinner)topicComment.findViewById(R.id.topic_content_commnent_publish_order_type);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.planets_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		butCommnet.setOnClickListener(this);
	}
	
	public void initDate() {
		// 这里进行http请求
		if (api == null) {
			api = new HomeIndexGoodsAPI();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("id", creativeID);
		api.getOriginalityItemInfo(paramsMap, this, REQUESTCODE);
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case REQUESTCODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						JSONObject jsonmode = json.getJSONObject("content");

						if (jsonmode != null && jsonmode.length() > 0) {
							originalityItem.setOrg_name(jsonmode.getString("org_name"));
							originalityItem.setOrg_desc(jsonmode.getString("org_desc"));
							originalityItem.setId(jsonmode.getString("id"));
							originalityItem.setUser_id(jsonmode.getString("user_id"));
							originalityItem.setOrg_explain(jsonmode.getString("org_explain"));
							originalityItem.setOrg_video(jsonmode.getString("org_video"));
							originalityItem.setProvince_id(jsonmode.getString("province_id"));
							originalityItem.setCity_id(jsonmode.getString("city_id"));
							originalityItem.setCategory_id(jsonmode.getString("category_id"));
							originalityItem.setScancount(jsonmode.getString("scancount"));
							originalityItem.setAddtime(jsonmode.getString("addtime"));
							originalityItem.setUpdatetime(jsonmode.getString("updatetime"));
							originalityItem.setOrg_state(jsonmode.getString("org_state"));
							originalityItem.setGzcount(jsonmode.getString("gzcount"));
							originalityItem.setComment_nums(jsonmode.getString("comment_nums"));
							originalityItem.setFavorite_nums(jsonmode.getString("favorite_nums"));
							originalityItem.setCategory_name(jsonmode.getString("category_name"));
							originalityItem.setProvince_name(jsonmode.getString("province_name"));
							originalityItem.setCity_name(jsonmode.getString("city_name"));
							originalityItem.setUser_realname(jsonmode.getString("user_realname"));
						}
					} else {
						Util.toastInfo(this, "请求失败");
					}
					// adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//更新界面
			refreshUI(originalityItem);
			break;

		default:
			break;
		}
	}
	
	private void refreshUI(OriginalityItemDetails item){
		topic_content_describe_title.setText(item.getOrg_name());
		topic_content_describe_text.setText(item.getOrg_desc());
		topic_content_describe_region.setText("所属地域："+item.getProvince_name()+"·"+item.getCity_name());
		topic_content_describe_hangye.setText("所属行业："+item.getCategory_name());
		//判断useid
		SharedPreferences spf = getSharedPreferences("login_user", 0) ;
		String openid = spf.getString("open_id", "0");
		if((item.getPid()!= null)&&(item.getPid().trim()!="0")&&(item.getPid().trim()!="")){
			topic_content_button.setText(getResources().getString(R.string.topic_content_button2));
			topic_content_button.setVisibility(View.VISIBLE);
			topic_content_button.setTag(1);
		}else if(openid.trim().equals(item.getUser_id())){
			topic_content_button.setVisibility(View.VISIBLE);
			topic_content_button.setTag(0);
		}
		Log.d("openid",	"openid = "+openid);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topic_content_comment_publish_but:
			Intent intent=new Intent(this, PublishComment.class);
			startActivity(intent);
			break;
		case R.id.topic_content_button:
			String tag = (String)v.getTag();
			if(tag!=null){
				if(tag.trim().equals("0")){
					Toast.makeText(this, "发布项目", Toast.LENGTH_SHORT).show();
				}else if(tag.trim().equals("1")){
					Toast.makeText(this, "项目详情", Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		// TODO Auto-generated method stub
		//spinner选择触发事件
		Log.d("yuanqikai", "yuanqikai pos = "+pos);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		//spinner未选择触发事件
		Log.d("yuanqikai", "yuanqikai onNothingSelected arg0 = "+arg0);
	}

}
