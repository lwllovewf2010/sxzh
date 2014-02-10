package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.homeindex.GetRootCommonList;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 个人地址管理
 * @author joe
 *
 */
public class MyAccoAddressManageActivity extends BaseActivity {
    private EditText name_et; //收货人名
    private EditText phone_et;//电话
    private EditText postcode_et;//邮编
    private TextView district_et;//地区
    private EditText address_et;//详细地址
    private ImageView set_default;//是否设置为默认 0不是1是
    private CommonAPI api = null;
    private final int ADD_ADDRESS = 30;
    private final int AREA_RESULTCODE = 118;
    private String area_root_id = ""; //省份id
	private String area_root_name = "";//省份名称
	private String area_sub_id = "";//城市id
	private String area_sub_name = "";//城市名称
	private String area_subsub_id = "";//区id
	private String area_subsub_name = "";//区名称
	private final int REQUESTCODE = 35;
	private String type = "";//编辑/添加
	private AddressInfo addressInfo = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_address);
		initView();
		initListener();
		getData();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setRight("保存");
		name_et = (EditText)findViewById(R.id.name_et);
		phone_et = (EditText)findViewById(R.id.phone_et);
		postcode_et = (EditText)findViewById(R.id.postcode_et);
		district_et = (TextView)findViewById(R.id.district_et);
		address_et = (EditText)findViewById(R.id.address_et);
		set_default = (ImageView)findViewById(R.id.set_default);
	}

	public void getData(){
		Intent intent = getIntent();
		if(intent != null){
			type = intent.getStringExtra("type");
			if("edit".equals(type)){
			addressInfo = (AddressInfo)intent.getSerializableExtra("addressInfo");
			name_et.setText(addressInfo.getName());
			phone_et.setText(addressInfo.getPhoneNum());
			postcode_et.setText(addressInfo.getPostcode());
			district_et.setText(addressInfo.getProvince_name() + " " + addressInfo.getCity_name() + " " +addressInfo.getArea_name());
			address_et.setText(addressInfo.getAddress());
			area_root_id = addressInfo.getProvince_id();
			area_sub_id = addressInfo.getCity_id();
			area_subsub_id = addressInfo.getArea_id();
			if("1".equals(addressInfo.getIsDefault())){
				set_default.setTag("1");
				set_default.setBackgroundResource(R.drawable.anniu_off);
			}else if("0".equals(addressInfo.getIsDefault())){
				set_default.setTag("0");
				set_default.setBackgroundResource(R.drawable.anniu_on);
			}
			setTitle("查看修改收货地址");
			}else if("add".equals(type)){
			setTitle("新建收货地址");
			}
		}
	}
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);
		set_default.setOnClickListener(this);
		district_et.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.title_right:
			addAddress();
			break;
		case R.id.district_et:
			Intent areaintent = new Intent(MyAccoAddressManageActivity.this,GetRootCommonList.class);
			areaintent.putExtra("type", "region_province");
			areaintent.putExtra("is_get_last", "1");
			startActivityForResult(areaintent, REQUESTCODE);
			break;
        case R.id.set_default:
			String tag = set_default.getTag().toString().trim();
			if("1".equals(tag)){
				set_default.setTag("0");
				set_default.setBackgroundResource(R.drawable.anniu_on);
			}else if("0".equals(tag)){
				set_default.setTag("1");
				set_default.setBackgroundResource(R.drawable.anniu_off);
			}
			break;
		default:
			break;
		}
	}
    
	private void addAddress(){
		String name = name_et.getText().toString().trim();
		if(name.length() > 0){
		String phone = phone_et.getText().toString().trim();
		if(phone.length() > 0){
			if(Util.checkMobile(phone)){
			String postcode = postcode_et.getText().toString().trim();
			if(postcode.length() > 0){
				if(postcode.length() == 6){
					if(!"".equals(area_root_id)){
						String address = address_et.getText().toString().trim();
						if(address.length() > 0){
							String is_default = set_default.getTag().toString().trim();
							String array[] = getOpen_idOrToken();
							if(api == null){
								api = new CommonAPI();
							}
							Map<String,String> paramsmap = new HashMap<String, String>();
							paramsmap.put("open_id",array[0]);
							paramsmap.put("token",array[1]);
							paramsmap.put("rname",name);
							paramsmap.put("mobile",phone);
							paramsmap.put("zipcode",postcode);
							paramsmap.put("province",area_root_id);
							paramsmap.put("city",area_sub_id);
							paramsmap.put("area",area_subsub_id);
							paramsmap.put("address", address);
							paramsmap.put("isdefault",is_default);
							if("add".equals(type)){
								api.getVerifyCodeOrBind("add_address",paramsmap, this, ADD_ADDRESS);
							}else if("edit".equals(type)){
								paramsmap.put("id",addressInfo.getId());
								api.getVerifyCodeOrBind("edit_address",paramsmap, this, ADD_ADDRESS);
							}
							
						}else{
							Util.toastInfo(this, "详细地址不能为空");
						}
					}else{
						Util.toastInfo(this, "地区未选择");
					}
				}else{
					Util.toastInfo(this, "邮编格式不正确");
				}
			}else{
				Util.toastInfo(this, "邮编不能为空");
			}
			}else{
				Util.toastInfo(this, "手机号码格式不正确");
			}
		}else{
			Util.toastInfo(this, "手机号码不能为空");
		}
		}else{
			Util.toastInfo(this, "收货人姓名不能为空");
		}
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case ADD_ADDRESS:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.optInt("ret");
					if(status == 0){
						if("add".equals(type)){
							Util.toastInfo(this, "添加收货地址成功");
						}else if("edit".equals(type)){
							Util.toastInfo(this, "编辑收货地址成功");
						}
						setResult(Constant.RESULT_LOGIN_CODE);
						finish();
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else{
						if("add".equals(type)){
							Util.toastInfo(this, "添加收货地址失败");
						}else if("edit".equals(type)){
							Util.toastInfo(this, "编辑收货地址失败");
						}
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
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == AREA_RESULTCODE){
		Bundle bundle = data.getExtras();
		area_root_id = bundle.getString("root_id");
		area_root_name = bundle.getString("root_name");
		area_sub_id = bundle.getString("sub_id");
		area_sub_name = bundle.getString("sub_name");
		area_subsub_id = bundle.getString("subsub_id");
		area_subsub_name = bundle.getString("subsub_name");
		district_et.setText(area_root_name + " " + area_sub_name + " " + area_subsub_name);
	}
}
}
