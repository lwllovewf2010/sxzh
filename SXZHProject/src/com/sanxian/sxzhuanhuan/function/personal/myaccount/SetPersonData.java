package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 
* @ClassName: SetPersonData  
* @Description: 修改个人资料中的一些共用属性（如：签名）
* @author honaf
* @date 2014-1-23 下午2:28:14
 */
public class SetPersonData extends BaseActivity {
    
	private EditText input_content_et;
	private String set_type = "";
	private String content = "";
	CommonAPI api=new CommonAPI();
	private final int UPDATESIGNATURE=100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_personinfo);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		super.initView();
		Intent intent = getIntent();
		if (intent != null) {
			set_type = intent.getStringExtra("set_type");
			content = intent.getStringExtra("content");
			if ("username".equals(set_type)) {
				setTitle("姓名");
			} else if ("signature".equals(set_type)) {
				setTitle("个性签名");
			}
		}
		setRight("保存");
		input_content_et = (EditText) findViewById(R.id.input_content_et);
		input_content_et.setText(content);
		input_content_et.setSelection(input_content_et.length());
	}

	@Override
	public void initListener() {
		super.initListener();
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		try {
			switch (flag) {
			case UPDATESIGNATURE:
				if (param.length > 0 && param[1] != null && param[1] instanceof String) {
					String jsondata = param[1].toString();
					if(Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						Util.toastInfo(this, "修改成功");
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						Util.toastInfo(this, "修改失败");
					}
					Intent intent=new Intent();
					intent.putExtra("returnValue",input_content_et.getText().toString());
					setResult(1,intent);
					finish();
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
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.title_right:
			if("".equals(input_content_et.getText().toString().trim())){
				return;
			}
			if ("signature".equals(set_type)) {
				Map<String,String> map=new HashMap<String, String>();
				map.put("description", input_content_et.getText().toString());
				api.updatePersonInfomation(map, this, UPDATESIGNATURE);
			}
			break;
		default:
			break;
		}
	}

}
