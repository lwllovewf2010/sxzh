package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.sanxian.sxzhuanhuan.MainActivity;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ILogin;
import com.sanxian.sxzhuanhuan.util.Util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PublishProjectLast extends BaseActivity implements OnClickListener {
	private TextView fullname;
	private TextView mobile_number;
	private EditText qq_number;
	private Button submit;
	private SharedPreferences spf;
	String open_id;
	String token;
	String fullnamesString;
	String mobile_numbersString;
	CommonAPI api = new CommonAPI();
	private static final int CREATEPROJECTONE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_project_last);
		spf = this.getSharedPreferences("login_user", 0);
		initView();
		initListener();
		init();

	}

	public void initView() {
		super.initView();
		setTitle("需求发布");
		setLeft("从设回报");
		findViewById(R.id.title_Left).setOnClickListener(this);
		TextView titleIndo = (TextView) findViewById(R.id.tvTitleIndo);
		titleIndo.setText("第三步/共三步");
		fullname = (TextView) this.findViewById(R.id.fullname);
		mobile_number = (TextView) this.findViewById(R.id.mobile_number);
		qq_number = (EditText) this.findViewById(R.id.qq_number);
		submit = (Button) this.findViewById(R.id.submit);

	}

	public void initListener() {
		super.initListener();
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.submit:
			getMap();
			api.manageProject("create", PublishConstant.paramMap, this, CREATEPROJECTONE);
			break;
		default:
			break;
		}
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		System.out.println("----" + flag);
		try {
			switch (flag) {
			case CREATEPROJECTONE:
				if (param.length > 0 && param[1] != null && param[1] instanceof String) {
					String jsondata = param[1].toString();
					//
					if (Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						JSONObject contentObject = new JSONObject(jsondata);
						Util.toastInfo(this, "创建成功！" + contentObject.optString("content"));
					
						
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						Util.toastInfo(this, "创建失败！");
					}
					Intent intent=new Intent(this,MainActivity.class);
					startActivity(intent);
				}
				break;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getMap() {

		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("project_qq", qq_number.getText().toString());
		tempMap.put("project_mobile", mobile_number.getText().toString());
		tempMap.put("project_realname", fullname.getText().toString());
		PublishConstant.paramMap.putAll(tempMap);
		return PublishConstant.paramMap;

		// project_qq false (string) 发起人QQ
		// project_mobile true (string) 发起人手机号
		// project_realname true (string) 发起人真实姓名
	}

	/**
	 * 初始化数据
	 */
	private void init() {

		open_id = spf.getString("open_id", "");
		token = spf.getString("token", "");
		fullnamesString = spf.getString("user_name", "");
		mobile_numbersString = spf.getString("mobile", "");
		// fullname.setText(fullnamesString);
		// mobile_number.setText(mobile_numbersString);
		fullname.setText("honaf");
		mobile_number.setText("13794484349");
	}

}
