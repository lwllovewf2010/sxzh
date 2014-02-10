package com.sanxian.sxzhuanhuan.api;

import java.util.Map;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.BaseFragmentActivity;
import com.sanxian.sxzhuanhuan.entity.RequestInputInfo;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: TestAPI.java
 * @Package com.sanxian.sxzhuanhuan.api
 * @Description: 服务接口
 * @author zhangfl@sanxian.com
 * @date 2014-1-15 上午9:36:13
 * @version V1.0
 */
public class TestAPI extends BaseAPI {
	
	/**
	 * 分类、行业、城市数据接口
	 * @param type 
	 * @param input 传入到服务器的params内容 
	 * @param listener 哪个extends BaseActivity进行调用的，在其中重载onRefresh(parm ...)进行相关解析
	 * @param requestCode 请求码----任意
	 * 大分类列表:{"action":"get_common_data","type":"category","mcr":0}
	 * 小分类列表:{"action":"get_common_data","type":"category","mcr":0, "params":{"parent_id":"1"}}
	 * 全国省列表:{"action":"get_common_data","type":"region_province"}
	 * 各省的城市列表:{"action":"get_common_data","type":"region_city","mcr":0,"params":{"province_id":6}} 
	 */
	public void getMCAData(String type , Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_common_data");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void getMCAData(String type , Map<String ,String> input, BaseFragment listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_common_data");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void getMCAData(String type , Map<String ,String> input, BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_common_data");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	//added by yuanqk
	/*
	 * {"action":"get_common_data","type":"region_province"} 
	 */
	public void getSortProvince(BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_common_data");
		inputinfo.setType("region_province");
		inputinfo.setMcr("0");
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	//added by yuanqk
	/*
	 * {"action":"get_common_data","type":"region_city","mcr":0,"params":{"province_id":6}}
	 */
	public void getSortCity(Map<String ,String> input, BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_common_data");
		inputinfo.setType("region_city");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 检查用户名、手机号是否已被使用过
	 * @param input
	 * @param listener
	 * @param requestCode
	 * 	 action : "user_register"
		 type : "check_uname"
		 mcr : 0  (可不传)
		 params 参数内容：(如果包含'user_name'即为判断用户名，包含'mobile'即为判断手机号，二者不能同时传 )
	 */
	public void checkUnameMobile(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_register");
		inputinfo.setType("check_uname");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 检查用户是否被邀请
	 * @param input
	 * @param listener
	 * @param requestCode
	 * input={"action":"user_register","type":"invite_check","mcr":0,"params":{"true_name":"\u5f20\u4e09","mobile":"13811689767"}}
	 * {"ret":0,"content":{"reference_open_id":"3_1206_629938"}}
	 */
	public void checkUserRegister(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_register");
		inputinfo.setType("invite_check");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 向指定手机发送验证码
	 * @param input
	 * @param listener
	 * @param requestCode
	 * {"action":"user_register","type":"get_captcha_message","mcr":0,"params":{"mobile":"18689221661"}}  
	 */
	public void sendVertifyCode(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_register");
		inputinfo.setType("get_captcha_message");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 修改密码
	 * @param input
	 * @param listener
	 * @param requestCode
	 * input={"action":"user_center","type":"change_password","mcr":0,
	 * "params":{"open_id":"5_1278_539947","token":"123456","old_password":"111222","new_password":"333333"}}
	 */
	public void changePassword(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType("change_password");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	/**
	 * 检查用户提交的短信验证码是否有效
	 * @param input
	 * @param listener
	 * @param requestCode
	 * {"action":"user_register","type":"check_virify","mcr":0,"params":{"user_con":"13811689766","virify_code":"898635"}}
	 */
	public void checkVertifyCode(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_register");
		inputinfo.setType("check_virify");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 提交注册
	 * @param input
	 * @param listener
	 * @param requestCode
	 * {"action":"user_register","type":"submit_register","mcr":0,
	 * 			"params":{"user_name":"106297632@qq.com","mobile":"13811689766","password":"abcd123"}}
	 * 
	 * {"action":"user_register","type":"submit_register","mcr":0,
	 * "params":{"user_name":"zhangsan","mobile":"12345678900","passwor":"123456","reference_open_id":"5_1278_539947"}}
	 */
	public void register(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_register");
		inputinfo.setType("submit_register");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 用户登录
	 * @param input
	 * @param listener
	 * @param requestCode
	 * {"action":"user_login","type":"submit_login","mcr":0,"params":{"uname":"10629762@qq.com","password":"abcd123"}}
	 */
	public void login(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_login");
		inputinfo.setType("submit_login");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 获取首页广告位图片
	 * @param listener
	 * @param requestCode
	 * 	 action : "get_homepage"
		 type : "ad"
		 mcr : 0  (可不传)
		 params 参数内容： 本接口params参数可不传
	 */
	public void getIndexImgs(BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_homepage");
		inputinfo.setType("ad");
		inputinfo.setMcr("0");
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void getIndexImgs(BaseFragment listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_homepage");
		inputinfo.setType("ad");
		inputinfo.setMcr("0");
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 获取创意、项目、商品列表
	 * @param input
	 * @param listener
	 * @param requestCode
	 *   action : "get_list"
		 type : "general"
		 mcr : 0  (可不传)
		 params 参数内容：pmode(1:项目；2：创意；4：产品) pagesize(每次返回结果条数：不传则默认为5条)
	 */
	public void getCPPData(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_list");
		inputinfo.setType("general");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void getCPPData(Map<String ,String> input, BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_list");
		inputinfo.setType("general");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void getCPPData(Map<String ,String> input, BaseFragment listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_list");
		inputinfo.setType("general");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 项目的查找、创建、删除操作
	 * @param  "search" 方法名（创建=create, 查找列表=search, 查找单个=get_row, 删除=delete）
	 * @param input
	 * @param listener
	 * @param requestCode
	 * 查找单个{"action":"project","type":"get_row","mcr":0,"params":{"proj_id": 100, "open_id": "1_1177_469954"}}
	 *     多个{"action":"project","type":"search","mcr":0,"params":{"start":0,"pagesize":10,"mode_id":1,"total_count":1 ....}}
	 * 
	 * 删除：
     *  POST传值内容1：input={"action":"project","type":"delete","mcr":0,"params":{"proj_ids": "100,200,300"}}
	 *	POST传值内容2：input={"action":"project","type":"delete","mcr":0,"params":{"proj_ids": "100"}}
	 */
	public void operaProjects(String type , Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("project");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	public void operaProjects(String type , Map<String ,String> input, BaseFragment listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("project");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	public void operaProjects(String type , Map<String ,String> input, BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("project");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 删除项目
	 * @param input
	 * @param listener
	 * @param requestCode
	 * {"action":"project","type":"delete","mcr":0,"params":{"proj_ids": "100"}}
	 * {"action":"project","type":"delete","mcr":0,"params":{"proj_ids": "100,200,300"}}
	 */
	public void deleteProjects(Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("project");
		inputinfo.setType("delete");
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 创意的查找
	 * @param type
	 * @param input
	 * @param listener
	 * @param requestCode
	 * 查找单个:input={"action":"originality","type":"get_row","mcr":0,"params":{"id": 100}}
	 * 查找多个：input={"action":"originality","type":"search","mcr":0,"params":{"start":0,"pagesize":10,"category_id":1,"total_count":1, "title":"关键词1,关键词2"...}}
	 * 创建一个创意:nput={"action":"originality","type":"create","mcr":0,"params":提交的各字段见接口文档
	 */
	public void operaCreativess(String type , Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	public void operaCreativess(String type , Map<String ,String> input, BaseFragment listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	public void operaCreativess(String type , Map<String ,String> input, BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 
	 * @param type
	 * @param input
	 * @param listener
	 * @param requestCode
	 * 查找单个商品:input={"action":"goods","type":"get_row","mcr":0,"params":{"goods_id": 489, "open_id": "1_1177_469954"}}
	 * 查找多个商品:input={"action":"goods","type":"search","mcr":0,"params":{"start":0,"pagesize":10,"mode_id":1,"total_count":1....}}
	 */
	public void operaProduct(String type , Map<String ,String> input, BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("goods");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void operaProduct(String type , Map<String ,String> input, BaseFragment listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("goods");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	public void operaProduct(String type , Map<String ,String> input, BaseFragmentActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("goods");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(input) ;
		params.put("input", Util.toJSONObject(inputinfo));
		requestforframeactivity("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
}
