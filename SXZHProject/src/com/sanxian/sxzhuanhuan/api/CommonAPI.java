package com.sanxian.sxzhuanhuan.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.SApplication;
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
	public void getPersonInfo(String type,Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
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
	 * 邀请用户及邀请记录(获取，清除)
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
	 * 绑定邮箱
	 * 修改登录密码
	 * 获取个人收货地址列表
	 * 增加收货地址
	 * joe
	 * @param type
	 * @param paramsmap
	 * @param listener
	 * @param requestCode
	 */
	public void getVerifyCodeOrBind(String type,Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
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
	 * 
	* @Description: 修改个人信息
	* @param @param type
	* @param @param paramsmap
	* @param @param listener
	* @param @param requestCode    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void updatePersonInfomation(Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType("edit_my_info");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo,(SApplication)listener.getApplicationContext()));		
		request("", params, HTTPMETHOD_POST, true, listener, requestCode);
//			  input={"action":"user_center","type":"edit_my_info","mcr":0,
//			                        "params":{"open_id":"5_1278_539947","token":"b1fccbf52f67ca26","photo":"/img/1234.jpg"}
//		注意：photo、gender、area、description参数均非必需，可以只传一个，也可以同时传多个，请根据具体修改的内容来决定参数。

	}
	/**
	 * 
	* @Description: 上传图片  
	* @param @param paramsmap
	* @param @param listener
	* @param @param requestCode    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void uploadAvatar(Map<String,String> paramsmap,File file,BaseActivity listener,final int requestCode){
		try {
			RequestParams params = new RequestParams();
			RequestInputInfo inputinfo = new RequestInputInfo();
			inputinfo.setAction("upload");
			inputinfo.setType("up_avatar");
			inputinfo.setMcr("0");
			inputinfo.setParams(paramsmap);
			params.put("input", Util.toJSONObject(inputinfo,(SApplication)listener.getApplicationContext()));	
			params.put("user_photo", file);
			request("", params, HTTPMETHOD_POST, false, listener, requestCode);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		action : "upload"
//			 type : "up_avatar"
//			 mcr : 0  (可不传)
//			 params 参数内容：user_photo

	}
	
}
