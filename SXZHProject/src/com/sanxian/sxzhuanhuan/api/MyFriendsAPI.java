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

public class MyFriendsAPI extends BaseAPI{

		
	/**
	 * 
	* @Description: 获取好友列表
	* @param @param paramsmap
	* @param @param listener
	* @param @param requestCode    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void getFriendList(Map<String,String> paramsmap,BaseActivity listener,final int requestCode){
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("user_center");
		inputinfo.setType("get_friend_list");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo,(SApplication)listener.getApplicationContext()));		
		request("", params, HTTPMETHOD_POST, true, listener, requestCode);
//		input={"action":"user_center","type":"get_friend_list","mcr":0,"params":{"open_id":"5_1278_539947","token":"b1fccbf52f67ca26

	}
	
	
}
