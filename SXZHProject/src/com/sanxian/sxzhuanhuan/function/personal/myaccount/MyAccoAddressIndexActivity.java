package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter.MyAccoAddressIndexAdapter;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
* @ClassName: MyAccoAddressIndexActivity  
* @Description: 个人地址首页列表
* @author honaf
* @date 2014-1-11 下午1:38:17
 */
public class MyAccoAddressIndexActivity extends BaseActivity {
	private ListView listView;
	private MyAccoAddressIndexAdapter adapter;
	private Button add_address_btn;//添加地址
	private Button title_right;//确定按钮
	private CommonAPI api = null;
	private final int ADDRESS_LIST = 30;
	private final int ADD_REQUESTCODE = 35;
	private boolean is_edit = false;
	private final int SHOWMIDDIALOG = 12;
	private final int DEL_ADDRESS = 20;
	private final int SET_DEFAULT = 25;
	private int delete_position = 0;
	private int setdefault_position = 0;
	public static final int CHOOSE_ADDRESS_CODE = 40;
	private String from = "";
	List<AddressInfo> list = new ArrayList<AddressInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_address_index);
		initView();
		initListener();
		getData();
		init();
		
	}
	
	public void init(){
		from = getIntent().getStringExtra("from");
	}
	
	/**
	 * 初始化数据获取地址列表
	 * joe
	 */
	public void getData(){
		SharedPreferences spf = this.getSharedPreferences("login_user", 0) ;
		String open_id = spf.getString("open_id","");
		String token = spf.getString("token","");
		if("".equals(open_id)|| "".equals(token)){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
		}else{
		if(api == null){
			api = new CommonAPI();
		}
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",open_id);
		paramsmap.put("token",token);
		api.getVerifyCodeOrBind("get_address_list", paramsmap, this, ADDRESS_LIST);
		}
	}
	@Override
	public void initView() {
		super.initView();
		setTitle("个人地址管理");
		setRight("编辑");
		listView=(ListView)this.findViewById(R.id.listView);
		add_address_btn = (Button)this.findViewById(R.id.add_address_btn);
		title_right = (Button)this.findViewById(R.id.title_right);
		adapter = new MyAccoAddressIndexAdapter(this, list);
		listView.setAdapter(adapter);
	}

	@Override
	public void initListener() {
		super.initListener();
		button_left.setOnClickListener(this);
		add_address_btn.setOnClickListener(this);
		title_right.setOnClickListener(this);
		listView.setOnItemClickListener(new Itemlistener());
		listView.setOnItemLongClickListener(new onlongItemlistener());
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case ADDRESS_LIST:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.optInt("ret");
					if(status == 0){
						if(list.size() > 0){
							list.clear();
						}
						JSONArray array = json.getJSONArray("content");
						if(array.length() > 0){
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							String id = object.optString("id");
							String name = object.optString("rname");
							String mobile = object.optString("mobile");
							String zipcode = object.optString("zipcode");
							String province_id = object.optString("province_id");
							if("null".equals(province_id)){
								province_id = "";
							}
							String province_name = object.optString("province");
							if("null".equals(province_name)){
								province_name = "";
							}
							String city_id = object.optString("city_id");
							if("null".equals(city_id)){
								city_id = "";
							}
							String city_name = object.optString("city");
							if("null".equals(city_name)){
								city_name = "";
							}
							String area_id = object.optString("area_id");
							if("null".equals(area_id)){
								area_id = "";
							}
							String area_name = object.optString("area");
							if("null".equals(area_name)){
								area_name = "";
							}
							String address = object.optString("address");
							String isdefault = object.optString("isdefault");
							
							AddressInfo addressInfo = new AddressInfo(id,name,mobile,zipcode,province_id,province_name,city_id,city_name,area_id,area_name,address,isdefault);
							list.add(addressInfo);
						}
						}else{
							Util.toastInfo(this, "你还没有收货地址奥");
						}
						
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else{
						Util.toastInfo(this, "获取收货地址失败");
					}
					adapter.notifyDataSetChanged();
					if(list.size() < 20){
						add_address_btn.setVisibility(View.VISIBLE);	
					}else{
						add_address_btn.setVisibility(View.GONE);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case DEL_ADDRESS:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
					JSONObject json;
					try {
						json = new JSONObject(data);
						int status = json.optInt("ret");
						if(status == 0){
							list.remove(delete_position);
							adapter.notifyDataSetChanged();
							Util.toastInfo(this, "删除收货地址成功");
						}else if(status == 1001){
							Intent intent = new Intent(this, LoginActivity.class);
							startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
						}else{
							Util.toastInfo(this, "删除收货地址失败");
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			break;
		case SET_DEFAULT:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
					JSONObject json;
					try {
						json = new JSONObject(data);
						int status = json.optInt("ret");
						if(status == 0){
							if("accountinfo".equals(from)){
								getData();
								Util.toastInfo(this, "设置默认收货地址成功");
							}else if("goods_buy".equals(from)){
								Intent intent = new Intent();
								intent.putExtra("addressInfo", list.get(setdefault_position));
								setResult(CHOOSE_ADDRESS_CODE, intent);
								finish();
							}
						}else if(status == 1001){
							Intent intent = new Intent(this, LoginActivity.class);
							startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
						}else{
						if ("accountinfo".equals(from)) {
							Util.toastInfo(this, "设置默认收货地址失败");
							
						  } else if ("goods_buy".equals(from)) {
						}
						   Util.toastInfo(this, "选择收货地址失败");

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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.add_address_btn:
			Intent addressmanage = new Intent(MyAccoAddressIndexActivity.this,MyAccoAddressManageActivity.class);
			addressmanage.putExtra("type", "add");
			startActivityForResult(addressmanage, ADD_REQUESTCODE);
			break;
		case R.id.title_right:
		if(list.size() > 0){
			if(is_edit == false){
				setRight("完成");
				is_edit = true;
			}else{
				setRight("编辑");
				is_edit = false;
			}
		  }
			break;
		default:
			break;
		}
	}
	
	class Itemlistener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if(is_edit){
				Intent addressmanage = new Intent(MyAccoAddressIndexActivity.this,MyAccoAddressManageActivity.class);
				addressmanage.putExtra("addressInfo", list.get(arg2));
				addressmanage.putExtra("type","edit");
				startActivityForResult(addressmanage, ADD_REQUESTCODE);
			}else{
				setDefault(arg2);
			}
			
		}
		
	}
	
	/**
	 * 设置为默认收货地址
	 * joe
	 * @param position
	 */
	private void setDefault(int position){
		setdefault_position = position;
		String array[] = getOpen_idOrToken();
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",array[0]);
		paramsmap.put("token",array[1]);
		paramsmap.put("id",list.get(position).getId());
		api.getVerifyCodeOrBind("set_default_address", paramsmap, this, SET_DEFAULT);
	}
	/**
	 * 长安删除收货地址
	 * @author Administrator
	 *
	 */
	class onlongItemlistener implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			showMidDialog();
			delete_position = arg2;
			return false;
		}
	}
	private void showMidDialog() {
		Intent intent = new Intent(MyAccoAddressIndexActivity.this , MiddleDialog.class);
		MiddleDialogInfo info = new MiddleDialogInfo("提示", "是否删除该条收货地址", 
				false, "", "", "确定", "取消");
		intent.putExtra("info", info);
		startActivityForResult(intent, SHOWMIDDIALOG);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Constant.RESULT_LOGIN_CODE){
			setRight("编辑");
			is_edit = false;
			getData();	
		}else if (requestCode == SHOWMIDDIALOG) {

			if (resultCode == DialogConstant.MIDDLE_OK) {
				String array[] = getOpen_idOrToken();
				Map<String,String> paramsmap = new HashMap<String, String>();
				paramsmap.put("open_id",array[0]);
				paramsmap.put("token",array[1]);
				paramsmap.put("id",list.get(delete_position).getId());
				api.getVerifyCodeOrBind("del_address", paramsmap, this, DEL_ADDRESS);
				
			} else if (resultCode == DialogConstant.MIDDLE_CANCEL) {
			}
		}
	}
	
}
