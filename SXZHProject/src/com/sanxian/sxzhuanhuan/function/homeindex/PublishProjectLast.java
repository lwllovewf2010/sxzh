package com.sanxian.sxzhuanhuan.function.homeindex;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PublishProjectLast extends BaseActivity implements OnClickListener {
	private TextView fullname;
	private TextView mobile_number;
	private EditText qq_number;
	private SharedPreferences spf;
	String open_id;
	String token;
	String fullnamesString;
	String mobile_numbersString;
	CommonAPI api = new CommonAPI();
	private static final int CREATEPROJECTONE = 100;
	private String tempImages = "";// 临时组装图片地址
	private int tempSize = 0;// 用来标志图片是否上传完成
	private static final int UPLOADAVATAR = 9;

	// 步骤流程图
	public ImageView img_one;
	public ImageView img_two;
	public ImageView img_three;
	public TextView txt_two;
	public TextView txt_three;

	private Button submit;
	private Button cancel_submit;

	private RelativeLayout charge_self;
	private RelativeLayout charge_vote;

	private ImageView tick_self;
	private ImageView tick_vote;

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
		// 步骤流程图
		img_one = (ImageView) this.findViewById(R.id.img_one);
		img_two = (ImageView) this.findViewById(R.id.img_two);
		img_three = (ImageView) this.findViewById(R.id.img_three);
		img_one.setBackgroundResource(R.drawable.finish);
		img_two.setBackgroundResource(R.drawable.finish);
		img_three.setBackgroundResource(R.drawable.step_three_on);

		txt_two = (TextView) this.findViewById(R.id.txt_two);
		txt_two.setTextColor(getResources().getColor(R.color.common_font_color));
		txt_three = (TextView) this.findViewById(R.id.txt_three);
		txt_three.setTextColor(getResources().getColor(R.color.common_font_color));

		submit = (Button) this.findViewById(R.id.submit);
		submit.setText("提交审核");
		cancel_submit = (Button) this.findViewById(R.id.cancel_submit);

		charge_self = (RelativeLayout) this.findViewById(R.id.charge_self);
		charge_vote = (RelativeLayout) this.findViewById(R.id.charge_vote);

		tick_self = (ImageView) this.findViewById(R.id.tick_self);
		tick_vote = (ImageView) this.findViewById(R.id.tick_vote);
	}

	public void initListener() {
		super.initListener();
		submit.setOnClickListener(this);
		cancel_submit.setOnClickListener(this);

		charge_self.setOnClickListener(this);
		charge_vote.setOnClickListener(this);
	}

	private boolean checkInput() {
		return true;
	}

	boolean charge = true;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.charge_self:
			charge = true;
			tick_self.setVisibility(View.VISIBLE);
			tick_vote.setVisibility(View.INVISIBLE);
			break;
		case R.id.charge_vote:
			charge = false;
			tick_self.setVisibility(View.INVISIBLE);
			tick_vote.setVisibility(View.VISIBLE);
			break;
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.cancel_submit:
			this.finish();
			break;
		case R.id.submit:
			// 项目图片（获取）
			if (app.getImagelist().size() == 0) {
				getMap();
				api.manageProject("create", PublishConstant.paramMap, this, CREATEPROJECTONE);
			} else {

				for (int i = 0; i < app.getImagelist().size(); i++) {

					// tempImages+=app.getImagelist().get(i)+",";
					api.uploadAvatarForProject(new HashMap<String, String>(), new File(app.getImagelist().get(i)), this, UPLOADAVATAR);
				}
				// tempMap.put("project_logo", tempImages);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
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
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
					finish();
				}
				break;

			case UPLOADAVATAR:
				if (param.length > 0 && param[1] != null && param[1] instanceof String) {
					String jsondata = param[1].toString();
					if (Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						JSONObject dataObject = new JSONObject(jsondata);
						tempImages = dataObject.optString("content");
						tempSize++;
						if (app.getImagelist().size() == tempSize) {
							Util.toastInfo(this, "上传成功");
							getMap();
							api.manageProject("create", PublishConstant.paramMap, this, CREATEPROJECTONE);
						}
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						Util.toastInfo(this, "上传失败");
					}
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
		tempMap.put("project_logo", tempImages);

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
		fullname.setText(fullnamesString);
		mobile_number.setText(mobile_numbersString);
		// fullname.setText("honaf");
		// mobile_number.setText("13794484349");
	}

}
