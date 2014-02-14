package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;

/**
 * 查看已注册用户的个人资料页面
 * 
 * @author joe
 * 
 */
public class RegisteredPersonInfoActivity extends BaseActivity implements OnClickListener {
	private ImageView myacc_avatar;// 头像
	private TextView username_tv;// 用户名
	private TextView area_tv; // 地区

	private TextView signature_tv;// 个性签名
	private CommonAPI api = null;
	private final int GETPERSONINFO = 15;
	private final int MALE = 1;// 男
	private final int FEMALE = 2;// 女
	private TextView sex_tv;
	private String u_open_id = "";//要查询的open_id
	UserInfo userInfo=new UserInfo();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_registered_personinfo);
		initView();
		initListener();
		initData();

	}

	@Override
	public void initView() {
		super.initView();
		displayRight();
		setTitle("个人信息");
		myacc_avatar = (ImageView) findViewById(R.id.reg_myacc_avatar);
		area_tv = (TextView) findViewById(R.id.reg_area_tv);
		username_tv = (TextView) findViewById(R.id.reg_username_tv);
		signature_tv = (TextView) findViewById(R.id.reg_signature_tv);
		sex_tv = (TextView) this.findViewById(R.id.reg_sex_tv);
		u_open_id = getIntent().getStringExtra("see_uid");
	}

	@Override
	public void initListener() {
		super.initListener();
		button_left.setOnClickListener(this);
	}

	private void initData() {
		
		SharedPreferences spf = this.getSharedPreferences("login_user", 0);
		String open_id = spf.getString("open_id", "");
		String token = spf.getString("token", "");
		if ("".equals(open_id) || "".equals(token)) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, Constant.REQUEST_LOGIN_CODE);
		} else {
			if (api == null) {
				api = new CommonAPI();
			}
			Map<String, String> paramsmap = new HashMap<String, String>();
			paramsmap.put("open_id", open_id);
			paramsmap.put("token", token);
			paramsmap.put("u_open_id", u_open_id);
			api.getPersonInfo("get_my_info",paramsmap, this, GETPERSONINFO);
		}
	}
	public void bindData(){
		imageLoader.displayImage(userInfo.getAvatar(), myacc_avatar, options);
		username_tv.setText(userInfo.getRealName());
		if(FEMALE==Integer.parseInt(userInfo.getGender())){
			sex_tv.setText("女");
		}else{
			sex_tv.setText("男");
		}
		
		area_tv.setText(userInfo.getAddress());
		signature_tv.setText(userInfo.getSignature());
	}
	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		try {
			// toast提示暂时为简单调试信息，后续去掉或调整
			switch (flag) {
			case GETPERSONINFO:
				if (param.length > 0 && param[1] != null && param[1] instanceof String) {
					String jsondata = param[1].toString();
					if (Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						JSONObject jsonObject=new JSONObject(jsondata);
						JSONObject contentObject=jsonObject.optJSONObject("content");
						if(contentObject!=null){
							userInfo.setRealName(contentObject.optString("user_name"));
							userInfo.setAvatar(contentObject.optString("photo"));
							userInfo.setGender(contentObject.optString("gender"));
							userInfo.setAddress(contentObject.optString("address"));
							userInfo.setSignature(contentObject.optString("description"));
//							{"ret":0,"content":{"user_name":"houhui123","photo":"","dname":"houhui123","gender":"","address":"","description":"\u4eca\u65e5\u4e8b\u4eca\u65e5\u6bd5"}}
							bindData();
							
						}
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						
					}
					
				}
				break;
		
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Constant.RESULT_LOGIN_CODE){
			initData();
		}
	}


}
