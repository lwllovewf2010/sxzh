package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.function.personal.PersonalIndex;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 我的账号详细页面
 * @author joe
 *
 */
public class MyAccountInfoActivity extends BaseActivity implements OnClickListener{
	/*修改密码，实名验证，手机验证，邮箱验证，个人地址管理，设置支付密码*/
	private RelativeLayout set_passw_layout,certify_name_layout,certify_phone_layout,certify_email_layout,preson_address_layout,set_pay_passw_layout;
	private TextView phone_number,email_number,certify_number,certify_phone_table,certify_email_table,certify_table;//手机号码，邮箱,实名认证;已绑定（手机号码，邮箱,实名认证）标签
	private String email = "";
	private final int REQUESTCODE = 10;
	private final int RESULTCODE = 15;
	private CommonAPI api = null;
	private final int GETACCOUNTINFO = 22;
	private boolean is_set_paypw = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_info);
		initView();
		initListener();
		initData();
	};

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("账号设置");
		
		set_passw_layout = (RelativeLayout)findViewById(R.id.set_passw_layout);
		certify_name_layout = (RelativeLayout)findViewById(R.id.certify_name_layout);
		certify_phone_layout = (RelativeLayout)findViewById(R.id.certify_phone_layout);
		certify_email_layout = (RelativeLayout)findViewById(R.id.certify_email_layout);
		preson_address_layout = (RelativeLayout)findViewById(R.id.preson_address_layout);
		set_pay_passw_layout = (RelativeLayout)findViewById(R.id.set_pay_passw_layout);
		certify_number = (TextView)findViewById(R.id.certify_number);
		phone_number = (TextView)findViewById(R.id.phone_number);
		email_number = (TextView)findViewById(R.id.email_number);
		certify_phone_table = (TextView)findViewById(R.id.certify_phone_table);
		certify_email_table = (TextView)findViewById(R.id.certify_email_table);
		certify_table = (TextView)findViewById(R.id.certify_table);
		
	}
	
	/**
	 * 初始化页面数据
	 * joe
	 */
	private void initData(){
		String array[] = getOpen_idOrToken();
		if(api == null){
			api = new CommonAPI();
		}
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",array[0]);
		paramsmap.put("token",array[1]);
		api.getPersonInfo("index",paramsmap,this,GETACCOUNTINFO);
//	 }
	}
	
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		set_passw_layout.setOnClickListener(this);
		certify_name_layout.setOnClickListener(this);
		certify_phone_layout.setOnClickListener(this);
		certify_email_layout.setOnClickListener(this);
		preson_address_layout.setOnClickListener(this);
		set_pay_passw_layout.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case GETACCOUNTINFO:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if(status == 0){
						JSONObject object = json.getJSONObject("content");
						
//						String company_name = object.optString("company_name");
//						String position_name = object.optString("place_name");
//						String order_num = object.optString("my_order_num");
//						String project_num = object.optString("my_project_num");
//						String shopping_cart_num = object.optString("my_shopping_cart_num");
//						String tribe_num = object.optString("my_tribe_num");
//						String money = object.optString("money");
//						String photo = object.optString("photo");
//						String true_name = object.optString("true_name");
//						String user_name = object.optString("user_name");
						String id_card = object.optString("id_card");
						email = object.optString("email");
						String mobile = object.optString("mobile");
						String isset_paypwd = object.optString("isset_paypwd");
						if("0".equals(isset_paypwd)){
							is_set_paypw = false;
						}else if("1".equals(isset_paypwd)){
							is_set_paypw = true;
						}
						
						if("null".equals(id_card)){
							id_card = "";
						}
						if("null".equals(email)){
							email = "";
						}
						if("null".equals(mobile)){
							mobile = "";
						}
						if(mobile.length() > 0){
							phone_number.setText(mobile.replace(mobile.subSequence(3,7), "XXXX"));
							certify_phone_table.setText("已绑定手机号    ");
						}
						if(email.length() > 0){
							email_number.setText(email.replace(email.subSequence(3,7), "XXXX"));
							certify_email_table.setText("已绑定邮箱        ");
						}
					    if(id_card.length() > 0){
					    	certify_number.setText(id_card.replace(id_card.subSequence(3,14), "XXXXXXXXXXX"));
					    	certify_table.setText("已认证身份证号");
					    }
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else {
						Util.toastInfo(this, "请求失败");
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
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.set_passw_layout:
			Intent setpw = new Intent(MyAccountInfoActivity.this,MyAccoSetLoginPWActivity.class);
			startActivity(setpw);
			break;
		case R.id.certify_name_layout:
			Intent certifyname = new Intent(MyAccountInfoActivity.this,MyAccoCertifyNameActivity.class);
			startActivity(certifyname);
			break;
		case R.id.certify_phone_layout:
			Intent bindphone = new Intent(MyAccountInfoActivity.this,MyAccoBindPhoneActivity.class);
			startActivityForResult(bindphone, REQUESTCODE);
			break;
		case R.id.certify_email_layout:
			Intent bindemail = new Intent(MyAccountInfoActivity.this,MyAccoBindEmailActivity.class);
			if(email.length() == 0){
			bindemail.putExtra("type","bind");
			}else{
			bindemail.putExtra("type","modify");
			}
			startActivity(bindemail);	
			break;
		case R.id.preson_address_layout:
			Intent address = new Intent(MyAccountInfoActivity.this,MyAccoAddressIndexActivity.class);
			address.putExtra("from","accountinfo");
			startActivity(address);
			break;
		case R.id.set_pay_passw_layout:
			Intent setpaypw = new Intent(MyAccountInfoActivity.this,MyAccoSetPayPWActivity.class);
			setpaypw.putExtra("is_set_paypw",is_set_paypw);
			startActivity(setpaypw);
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULTCODE){
			initData();
			SharedPreferences spf = getSharedPreferences("login_user", 0) ;
			String mobile = spf.getString("mobile","");
			phone_number.setText(mobile.replace(mobile.subSequence(3,7), "XXXX"));
			
		}else if(resultCode == Constant.RESULT_LOGIN_CODE){
			initData();
		}
	}

}
