package com.sanxian.sxzhuanhuan.api;

import java.util.Map;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.entity.RequestInputInfo;
import com.sanxian.sxzhuanhuan.util.Util;

public class CommonAPI extends BaseAPI{

	/**
	 * 获取分类、行业、城市等数据
	 * joe
	 * @param type
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void getCommonList(String type,Map<String,String> paramsmap, BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("get_common_data");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 创意的查找、创建操作
	 * joe
	 * @param type(创建=create, 查找列表=search, 查找单个=get_row )
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void manageCreative(String type,Map<String,String> paramsmap, BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 项目、查找、创建、删除操作
	 * joe
	 * @param type(get_row（单个），search（多个）,create,delete)
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void manageProject(String type,Map<String,String> paramsmap, BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("project");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 获取个人中心首页信息
	 * joe
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void getPersonIndex(Map<String,String> paramsmap,BaseFragment listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType("index");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 获取个人资料
	 * joe
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void getPersonInfo(Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType("get_my_info");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/**
	 * 邀请用户及邀请记录
	 * joe
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void inviteUserOrList(String type,Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	/**
	 * 修改绑定手机前获取验证码短信
	 * 修改绑定手机号
	 * joe
	 * @param type
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void getVerifyCodeOrBindPhone(String type,Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType(type);
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));		
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}  
}
