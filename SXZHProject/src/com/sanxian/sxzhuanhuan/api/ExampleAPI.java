package com.sanxian.sxzhuanhuan.api;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.common.BaseActivity;

/**
 * 个人中心 自定义api样本
 * 
 * @author hudabing
 * 
 */
public class ExampleAPI extends BaseAPI {

	/**
	 * 
	 * @Description: 不显示进度条
	 * @param @param uid
	 * @param @param listener
	 * @param @param requestCode 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void example1(String uid, final BaseActivity listener,
			final int requestCode) {
		// http://bse.rjbank.cn/api.php?app=usercore&m=Mobile&a=getUserInfo
		RequestParams params = new RequestParams();
		params.put("uid", uid);
		// request( "&m=myspace&a=myDetailInfo", params, HTTPMETHOD_POST,listener, requestCode);
		request("&m=myspace&a=myDetailInfo", params, HTTPMETHOD_POST, false,listener, requestCode);
	}

	/**
	 * 
	 * @Description: 显示进度条
	 * @param @param uid
	 * @param @param listener
	 * @param @param requestCode 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void example2(String uid, final BaseActivity listener,
			final int requestCode) {
		// http://bse.rjbank.cn/api.php?app=usercore&m=Mobile&a=getUserInfo
		RequestParams params = new RequestParams();
		params.put("uid", uid);
		request("&m=myspace&a=myDetailInfo", params, HTTPMETHOD_POST, true,	listener, requestCode);
	}

	/**
	 * 
	 * @Description: 显示进度条
	 * @param @param uid
	 * @param @param listener
	 * @param @param requestCode 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void example3(String uid, final BaseActivity listener,
			final int requestCode, final int timeout) {
		// http://bse.rjbank.cn/api.php?app=usercore&m=Mobile&a=getUserInfo
		RequestParams params = new RequestParams();
		params.put("uid", uid);
		request("&m=myspace&a=myDetailInfo", params, HTTPMETHOD_POST, true,	listener, requestCode, timeout);
	}

	/**
	 * 
	 * @Description: 根据boolean判断是否显示进度条
	 * @param @param name
	 * @param @param pass
	 * @param @param listener
	 * @param @param requestCode 请求码
	 * @return void 返回类型
	 * @throws
	 */
	public void exampleAll(String name, String pass,
			final BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("uname", name);
		params.put("upass", pass);
		request("&m=my", params, HTTPMETHOD_POST, true, listener, requestCode);
	}

	/**
	 * 
	 * @Description: 根据boolean判断是否显示进度条,并且设置超时时间
	 * @param @param name
	 * @param @param pass
	 * @param @param listener
	 * @param @param requestCode 请求码
	 * @return void 返回类型
	 * @throws
	 */
	public void exampleAll2(String name, String pass,
			final BaseActivity listener, final int requestCode,
			final int timeout) {
		RequestParams params = new RequestParams();
		params.put("uname", name);
		params.put("upass", pass);
		request("&m=my", params, HTTPMETHOD_POST, true, listener, requestCode,	timeout);
	}

}
