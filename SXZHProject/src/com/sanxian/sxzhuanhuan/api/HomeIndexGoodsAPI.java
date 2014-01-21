package com.sanxian.sxzhuanhuan.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.RequestInputInfo;
import com.sanxian.sxzhuanhuan.util.Util;

public class HomeIndexGoodsAPI extends BaseAPI {

	/*
	 * 根据ID获取商品详细信息
	 */
	public void getGoodsItemInfo(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("goods");
		inputinfo.setType("get_row");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
		
	/*
	 * 根据ID获取创意详细信息
	 */
	public void getOriginalityItemInfo(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType("get_row");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}

}
