package com.sanxian.sxzhuanhuan;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;

/**
 * @Title: TestAPIActivity.java
 * @Package com.sanxian.sxzhuanhuan
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-15 上午9:37:10
 * @version V1.0
 */
public class TestAPIActivity extends BaseActivity {

	private TestAPI api = null;
	private Map<String , String> input = null ;

	private Button btnMode = null;
	private Button btnCategory = null;
	private Button btnArea = null;
	private Button btnUname = null ;
	private Button btnUtelephone = null ;
	private Button btnSendVertifyCode = null ;
	private Button btnCheckVertifyCode = null ;
	private Button btnRegister = null ;
	private Button btnLogin = null ;
	private Button btnIndexImgs = null ;
	private Button btnCreativeList = null ;
	private Button btnProjectList = null ;
	private Button btnProductList = null ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		init();
		api = new TestAPI();
		input = new HashMap<String, String>() ;
	}

	private void init() {
		btnMode = (Button) findViewById(R.id.test_btn_mode);
		btnCategory = (Button) findViewById(R.id.test_btn_category);
		btnArea = (Button) findViewById(R.id.test_btn_area);
		btnUname = (Button) findViewById(R.id.test_btn_uname) ;
		btnUtelephone = (Button) findViewById(R.id.test_btn_utelephone) ;
		btnSendVertifyCode = (Button) findViewById(R.id.test_btn_send_vertify_code) ;
		btnCheckVertifyCode = (Button) findViewById(R.id.test_btn_check_vertify_code) ;
		btnRegister = (Button) findViewById(R.id.test_btn_register) ;
		btnLogin = (Button) findViewById(R.id.test_btn_login) ;
		btnIndexImgs = (Button) findViewById(R.id.test_btn_get_index_imgs) ;
		btnCreativeList = (Button) findViewById(R.id.test_btn_creative_list) ;
		btnProjectList = (Button) findViewById(R.id.test_btn_project_list) ;
		btnProductList = (Button) findViewById(R.id.test_btn_product_list) ;

		btnMode.setOnClickListener(this);
		btnCategory.setOnClickListener(this);
		btnArea.setOnClickListener(this);
		btnUname.setOnClickListener(this) ;
		btnUtelephone.setOnClickListener(this) ;
		btnSendVertifyCode.setOnClickListener(this) ;
		btnCheckVertifyCode.setOnClickListener(this) ;
		btnRegister.setOnClickListener(this) ;
		btnLogin.setOnClickListener(this) ;
		btnIndexImgs.setOnClickListener(this) ;
		btnCreativeList.setOnClickListener(this) ;
		btnProductList.setOnClickListener(this) ;
		btnProjectList.setOnClickListener(this) ;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.test_btn_mode:
			System.out.println("mode");
			input.put("start", "0") ;
			input.put("pagesize", "0") ;
			api.getMCAData("mode", input , this,
					Constant.REQUESTCODE.MODE_REQUEST);
			break;
		case R.id.test_btn_category:
			System.out.println("category");
			input.put("start", "0") ;
			input.put("pagesize", "0") ;
			api.getMCAData("category", input , this,
					Constant.REQUESTCODE.CATEGORY_REQUEST);
			break;
		case R.id.test_btn_area:
			System.out.println("area");
			input.put("start", "0") ;
			input.put("pagesize", "0") ;
			api.getMCAData("area", input, this,
					Constant.REQUESTCODE.AREA_REQUEST);
			break;
		case R.id.test_btn_uname:
			System.out.println("uname");
			input.put("user_name", "test") ;
			api.checkUnameMobile(input, this, Constant.REQUESTCODE.CHECK_UNAME_REQUEST) ;
			break;
		case R.id.test_btn_utelephone:
			System.out.println("utelephone");
			input.put("mobile", "12312312312") ;
			api.checkUnameMobile(input, this, Constant.REQUESTCODE.CHECK_UTELEPHONE_REQUEST) ;
			break;
		case R.id.test_btn_send_vertify_code:
			System.out.println("send_vertify_code");
			input.put("mobile", "18689221661") ;
			api.sendVertifyCode(input, this, Constant.REQUESTCODE.SEND_VERTIFY_CODE_REQUEST) ;
			break;
		case R.id.test_btn_check_vertify_code:
			System.out.println("check_vertify_code");
			//{"user_con":"13811689766","virify_code":"898635"}
			input.put("user_con", "13811689766") ;
			input.put("virify_code", "898635") ;
			api.checkVertifyCode(input, this, Constant.REQUESTCODE.CHECK_VERTIFY_CODE_REQUEST) ;
			break;
		case R.id.test_btn_register :
			System.out.println("register");
			//{"user_name":"106297632@qq.com","mobile":"13811689766","password":"abcd123"}}
			input.put("user_name", "106297632@qq.com") ;
			input.put("mobile", "13811689766") ;
			input.put("password", "abcd123") ;
			api.register(input, this, Constant.REQUESTCODE.REGISTER_REQUEST) ;
			break ;
		case R.id.test_btn_login :
			System.out.println("login");
			//{"uname":"10629762@qq.com","password":"abcd123"}
			input.put("uname", "10629762@qq.com") ;
			input.put("password", "abcd123") ;
			api.login(input, this, Constant.REQUESTCODE.LOGIN_REQUEST) ;
			break ;
		
		case R.id.test_btn_get_index_imgs :
			System.out.println("get_index_imgs");
			api.getIndexImgs(this, Constant.REQUESTCODE.INDEX_IMGS_REQUEST) ;
			break ;
		case R.id.test_btn_creative_list :
			System.out.println("creative_list");
			input.put("pmode", "2") ;
//			input.put("pagesize", "10") ;
			api.getCPPData(input, this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
			break ;
		case R.id.test_btn_project_list :
			System.out.println("project_list");
			input.put("pmode", "1") ;
			input.put("pagesize", "10") ;
			api.getCPPData(input, this, Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
			break ;
		case R.id.test_btn_product_list :
			System.out.println("product_list");
			input.put("pmode", "4") ;
			input.put("pagesize", "5") ;
			api.getCPPData(input, this, Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
			break ;
		}
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		System.out.println("----" + flag);
		switch (flag) {
		case Constant.REQUESTCODE.MODE_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getModes(jsondata);
			}
			break;
		case Constant.REQUESTCODE.CATEGORY_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getCategory(jsondata);
			}
		case Constant.REQUESTCODE.AREA_REQUEST:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getArea(jsondata);
			}
			break;
		case Constant.REQUESTCODE.CHECK_UNAME_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
			}
			break ;
		case Constant.REQUESTCODE.CHECK_UTELEPHONE_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
			}
			break ;
			
		case Constant.REQUESTCODE.SEND_VERTIFY_CODE_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
			}
			break ;
		case Constant.REQUESTCODE.CHECK_VERTIFY_CODE_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println("ret:" + JSONParser.getReturnFlag(jsondata));
				System.out.println(jsondata);
			}
			break ;
		case Constant.REQUESTCODE.REGISTER_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
			}
			break ;
		case Constant.REQUESTCODE.LOGIN_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				System.out.println(param.length + "----" + param[0].toString());
				String jsondata = param[1].toString();
				System.out.println(jsondata);
			}
			break ;
			
			
		case Constant.REQUESTCODE.INDEX_IMGS_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getIndexImgs(jsondata);
			}
			break ;
			
		case Constant.REQUESTCODE.CREATIVE_LIST_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getCreativeInfo(jsondata);
			}
			break ;
		case Constant.REQUESTCODE.PROJECT_LIST_REQUEST : 
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getProjectInfo(jsondata);
			}
			break ;
		case Constant.REQUESTCODE.PRODUCT_LIST_REQUEST :
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String jsondata = param[1].toString();
				System.out.println(jsondata);
				JSONParser.getProductInfo(jsondata);
			}
			break ;
		}
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		input.clear() ;
	}

}
