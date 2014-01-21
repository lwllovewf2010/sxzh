package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.function.homeindex.adapter.GetRootCommonAdapter;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 获取分类、行业、省份等数据
 * 
 * @author joe
 */
public class GetRootCommonList extends BaseActivity {

	private ListView common_list;
	private String type = "";
	private CommonAPI api = null;
	private final int REQUESTCODE = 12;
	private List<Map<String, String>> list;
	private GetRootCommonAdapter adapter;
	private String root_id;
	private String root_name;
	private final int RESULT_CODE = 112;
	private final int SUB_REQUESTCODE = 109;
	private final int SUB_RESULTCODE = 118;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getcommontdata_list);
		initView();
		initData();
		initListener();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		type = getIntent().getStringExtra("type");
		if("mode".equals(type)){
			setTitle("分类");
		}else if("category".equals(type)){
			setTitle("行业");
		}else if("region_province".equals(type)){
			setTitle("省份");
		}
		common_list = (ListView) findViewById(R.id.common_list);
		list = new ArrayList<Map<String,String>>();
		adapter = new GetRootCommonAdapter(this,list,type);
		common_list.setAdapter(adapter);
	}
	private void initData(){
		
		if(api == null){
			api = new CommonAPI();
		}
		Map<String,String> paramsMap = new HashMap<String,String>();
		if("mode".equals(type)){
			paramsMap.put("start","0");
			paramsMap.put("pagesize","0");
		}else{
			paramsMap.put("parent_id","0");
		}
		api.getCommonList(type,paramsMap, this, REQUESTCODE);
	} 
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
	    button_left.setOnClickListener(this);
	    common_list.setOnItemClickListener(new ItemClick());
	    
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
		 Log.e("joe", "请求数据" + data);
		 try {
			JSONObject json = new JSONObject(data);
			int status = json.getInt("ret");
			if(status == 0){
				JSONArray array = json.getJSONArray("content");
				if(list.size() > 0){
				   list.clear();
				}
				if(array != null && array.length() > 0){
					if("mode".equals(type)){
						for (int i = 0; i < array.length(); i++) {
							Map<String,String> modeMap = new HashMap<String, String>();
							JSONObject jsonmode = array.getJSONObject(i);
							modeMap.put("id", jsonmode.getString("id"));
							modeMap.put("type_name", jsonmode.getString("type_name"));
							list.add(modeMap);
						}
					}else if("category".equals(type)){
						for (int i = 0; i < array.length(); i++) {
							Map<String,String> categoryMap = new HashMap<String, String>();
							JSONObject jsonmode = array.getJSONObject(i);
							categoryMap.put("id", jsonmode.getString("id"));
							categoryMap.put("type_name", jsonmode.getString("title"));
							categoryMap.put("parent_id", jsonmode.getString("parent_id"));
							categoryMap.put("sub_count", jsonmode.getString("sub_count"));
							list.add(categoryMap);
						}
					}else if("region_province".equals(type)){
						for (int i = 0; i < array.length(); i++) {
							Map<String,String> areaMap = new HashMap<String, String>();
							JSONObject jsonmode = array.getJSONObject(i);
							areaMap.put("id", jsonmode.getString("id"));
							areaMap.put("type_name", jsonmode.getString("region_name"));
							list.add(areaMap);
						}
					}
					
				}
			}else{
				Util.toastInfo(this, "请求失败");
			}
			adapter.notifyDataSetChanged();
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
	
	class ItemClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if(!"".equals(type)){
				root_id = list.get(arg2).get("id");
				root_name = list.get(arg2).get("type_name");
				if ("region_province".equals(type)) {
					Intent intent = new Intent(GetRootCommonList.this,GetSubCommonList.class);
					intent.putExtra("type", "region_city");
					intent.putExtra("root_id", root_id);
					startActivityForResult(intent, SUB_REQUESTCODE);
				}else if("category".equals(type)){
					String sub_count = list.get(arg2).get("sub_count");
					if(!"0".equals(sub_count)){
						Intent intent = new Intent(GetRootCommonList.this,GetSubCommonList.class);
						intent.putExtra("type",type);
						intent.putExtra("root_id", root_id);
						startActivityForResult(intent, SUB_REQUESTCODE);
					}else{
						Bundle bundle = new Bundle();
						bundle.putString("root_id", root_id);
						bundle.putString("root_name", root_name);
						setResult(RESULT_CODE, getIntent().putExtras(bundle));
						finish();	
					}
				}else {
					Bundle bundle = new Bundle();
					bundle.putString("root_id", root_id);
					bundle.putString("root_name", root_name);
					setResult(RESULT_CODE, getIntent().putExtras(bundle));
					finish();
				}
		   }
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == SUB_RESULTCODE){
			Bundle bundle = data.getExtras();
			bundle.putString("root_id", root_id);
			bundle.putString("root_name", root_name);
			setResult(SUB_RESULTCODE, getIntent().putExtras(bundle));
			finish();
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

		default:
			break;
		}
	}

}
