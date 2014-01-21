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
 * 获取城市等数据
 * 
 * @author joe
 */
public class GetSubCommonList extends BaseActivity {

	private ListView common_list;
	private String type = "";
	private CommonAPI api = null;
	private final int REQUESTCODE = 12;
	private List<Map<String, String>> list;
	private GetRootCommonAdapter adapter;
	private String root_id = "";
	private  String paramsname = "";
	private final int RESULT_CODE = 118;
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
		Intent intent = getIntent();
		if(intent != null){
			type = getIntent().getStringExtra("type");
			root_id = getIntent().getStringExtra("root_id");
		}
		if("region_city".equals(type)){
			setTitle("城市");
			paramsname = "province_id";
		}else if("category".equals(type)){
			setTitle("行业");
			paramsname = "parent_id";
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
		paramsMap.put(paramsname,root_id);
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
		 try {
			JSONObject json = new JSONObject(data);
			int status = json.getInt("ret");
			if(status == 0){
				JSONArray array = json.getJSONArray("content");
				if(list.size() > 0){
				   list.clear();
				}
				if("region_city".equals(type)){
					if(array != null && array.length() > 0){
						for (int i = 0; i < array.length(); i++) {
							Map<String,String> areaMap = new HashMap<String, String>();
							JSONObject jsonmode = array.getJSONObject(i);
							areaMap.put("id", jsonmode.getString("id"));
							areaMap.put("type_name", jsonmode.getString("region_name"));
							list.add(areaMap);
						}
				 }
				}else if("category".equals(type)){
					if(array != null && array.length() > 0){
						for (int i = 0; i < array.length(); i++) {
							Map<String,String> categoryMap = new HashMap<String, String>();
							JSONObject jsonmode = array.getJSONObject(i);
							categoryMap.put("id", jsonmode.getString("id"));
							categoryMap.put("type_name", jsonmode.getString("title"));
							list.add(categoryMap);
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
				Bundle bundle = new Bundle();
				bundle.putString("sub_id", list.get(arg2).get("id"));
				bundle.putString("sub_name", list.get(arg2).get("type_name"));
                bundle.putString("type",type);
				setResult(RESULT_CODE, getIntent().putExtras(bundle));
				finish();
			}
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
