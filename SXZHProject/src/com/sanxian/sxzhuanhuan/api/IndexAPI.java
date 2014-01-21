package com.sanxian.sxzhuanhuan.api;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.common.BaseFragment;

public class IndexAPI extends BaseAPI{
	
	/**
	 * 请求广告图片
	 * 
	 * @param type 请求类型
	 * @param lister 当前监听者
	 * @param requestCode 请求编码
	 */
	public void findAdvertisingImage(String action,String type,final BaseFragment listener,final int requestCode){
		RequestParams params = new RequestParams();
		params.put("action", action);
		params.put("type", type);		
		request("", params, HTTPMETHOD_GET, false, listener, requestCode);
	}
	
	
	/**
	 * 获取创意列表
	 * @param value
	 * @param listener
	 * @param requestCode
	 */
	public void findProjectList(String value,final BaseFragment listener,final int requestCode){
		RequestParams params = new RequestParams();
		params.put("input", value);
		request("", params, HTTPMETHOD_POST, false,listener, requestCode);
	}
}
