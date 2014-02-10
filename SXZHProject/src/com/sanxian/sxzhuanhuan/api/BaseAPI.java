package com.sanxian.sxzhuanhuan.api;
import java.util.HashMap;
import java.util.List;
import org.apache.http.cookie.Cookie;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.BaseFragmentActivity;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * API的基类，每个接口类都继承了此抽象类
 * 
 * @author mobile
 */
public abstract class BaseAPI {

	public static final String API_SERVER = "http://v2api.sxzhuanhuan.com/mobileapi/index.php";
//	public static final String API_SERVER = "http://192.168.1.9/mobileapi/index.php";
	// public static final String API_SERVER
	// ="http://192.168.0.107/index.php/v2/";
	// public static final String API_SERVER
	// ="http://192.168.1.125:67/index.php?g=api&ver=1_1";
	// public static final String API_SERVER =
	// "http://192.168.0.117:8080/TestLogin/servlet/LoginServlet?a=mobile";

	public static AsyncHttpClient client = null;
	public static PersistentCookieStore cookieStore;

	public static final String HTTPMETHOD_POST = "POST"; // post请求方式
	public static final String HTTPMETHOD_GET = "GET";// get请求方式
	private Dialog mDialog;
	private SharedPreferences pdf;
	private SharedPreferences cookiepdf;

	public BaseAPI() {

	}

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static String getAbsoluteUrl(String relativeUrl) {
		// Log.e("club====>",API_SERVER + relativeUrl+"");
		return API_SERVER + relativeUrl;
	}

	/**
	 * 请求无设置超时时间(默认值)
	 * 
	 * @param url
	 *            模块字段
	 * @param params
	 *            参数
	 * @param postMethod
	 *            Post或Get方法
	 * @param dealing
	 *            是否弹出等待处理框
	 * @param listener
	 *            回调的activity
	 * @param requestCode
	 *            回调的请求码
	 * @param timeout
	 *            连接超时时间,调用需要新建子类API来调用此方法
	 */

	// member/login/?
	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseActivity listener, final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
			cookieStore = new PersistentCookieStore(listener);
		}
		client.setCookieStore(cookieStore);
//			pdf = listener.getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token);
//		if ("member/login/?".equals(url) || "member/getBindUid/?".equals(url)) {
//			client.setCookieStore(cookieStore);
//		} else {
//			client.addHeader("Cookie", getCookie());
//		}

		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						if (params != null) {
							Log.d("params", params.toString());
						}

						Log.d("url", url);
						Log.d("response", response);
						JSONObject obj = new JSONObject(response);
						if ("member/login/?".equals(url)
								|| "member/getBindUid/?".equals(url)) {
							saveCookies(cookieStore, listener);
						}
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					Util.toastInfo(listener, "连接超时");
					listener.refresh(requestCode, BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	/**
	 * 请求无设置超时时间(默认值)
	 * 
	 * @param url
	 *            模块字段
	 * @param params
	 *            参数
	 * @param postMethod
	 *            Post或Get方法
	 * @param dealing
	 *            是否弹出等待处理框
	 * @param listener
	 *            回调的activity
	 * @param requestCode
	 *            回调的请求码
	 * @param timeout
	 *            连接超时时间,调用需要新建子类API来调用此方法
	 */

	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseActivity listener, final int requestCode,
			final HashMap<String, Object> map) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
		}
//			pdf = listener.getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token);
//		client.addHeader("Cookie", getCookie());
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						Log.d("params", params.toString());
						Log.d("url", url);
						Log.d("response", response);
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response, map);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					Util.toastInfo(listener, "连接超时");
					listener.refresh(requestCode, BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response, map);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	/**
	 * 请求设置超时时间
	 * 
	 * @param url
	 *            模块字段
	 * @param params
	 *            参数
	 * @param postMethod
	 *            Post或Get方法
	 * @param dealing
	 *            是否弹出等待处理框
	 * @param listener
	 *            回调的activity
	 * @param requestCode
	 *            回调的请求码
	 * @param timeout
	 *            连接超时时间,调用需要新建子类API来调用此方法
	 */

	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseActivity listener, final int requestCode, int timeout) {
		if (client == null) {
			client = new AsyncHttpClient();
		}

		if (timeout <= 0) {
			timeout = 45000;
		}
		client.setTimeout(timeout);
//			pdf = listener.getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token);
//		client.addHeader("Cookie", getCookie());
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						Log.d("params", params.toString());
						Log.d("url", url);
						Log.d("response", response);
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseFragment listener, final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
		}
		// client.getHttpClient().setCookieStore((CookieStore) getSaveCookie());
//			pdf = listener.getContext().getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token); 
//		client.addHeader("Cookie", getCookie());
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener
									.getContext()).create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						if (params != null) {
							Log.d("params", params.toString());
						}
						Log.d("url", url);
						Log.d("response", response);
						// Toast.makeText(listener.getContext(),"response"+response,
						// Toast.LENGTH_LONG).show();
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					Log.e("onFailure", "" + response);
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	/**
	 * 请求无监听
	 * 
	 * @param url
	 * @param params
	 * @param postMethod
	 * @param liste
	 */
	protected void requestNoLister(final String url,
			final RequestParams params, String postMethod,
			final MessageListener liste) {
		if (client == null) {
			client = new AsyncHttpClient();
		}
//			pdf = SApplication.getInstance().getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token);
		// client.getHttpClient().setCookieStore((CookieStore) getSaveCookie());
//		client.addHeader("Cookie", getCookie());
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {

				}

				public void onSuccess(String response) {
					Log.d("count post url", url);
					Log.d("count post params", params.toString());
					Log.d("count post Success", response);
					liste.OnMessageGet(response);
				}

				public void onFailure(Throwable e, String response) {

				}

				public void onFinish() {

				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {

				}

				public void onSuccess(String response) {
					liste.OnMessageGet(response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();

				}

				public void onFinish() {

				}
			});
		}
	}

	public interface MessageListener {
		public void OnMessageGet(String data);
	}

	protected void requestforframeactivity(final String url,
			final RequestParams params, String postMethod,
			final boolean showDealing, final BaseFragmentActivity listener,
			final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
		}
		// client.getHttpClient().setCookieStore((CookieStore) cookie);
//			pdf = listener.getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token);
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						Log.d("params", params.toString());
						Log.d("url", url);
						Log.d("response", response);
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	protected void request(final String url, final RequestParams params,
			String postMethod, final BaseActivity listener,
			final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();

		}
//			pdf = listener.getSharedPreferences("userinfo",
//					Context.MODE_PRIVATE);
//		String token = pdf.getString("token", "");
//		params.put("token", token);
		if (postMethod.equals(HTTPMETHOD_POST)) {

			client.post(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {

				}

				public void onSuccess(String response) {

					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {

				}

			});

		} else {
			client.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {

				}

				public void onSuccess(String response) {

					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {

				}
			});
		}
	}

	private void saveCookies(PersistentCookieStore cookieStore,
			BaseActivity activity) {
		String name = null;
		String value = null;
		List<Cookie> cookies = cookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			value = cookies.get(i).getValue();
			name = cookies.get(i).getName();
			cookiepdf = activity.getSharedPreferences("cookie", 0);
			Editor editor = cookiepdf.edit();
			editor.putString("name", name);
			editor.putString("value", value);
			editor.commit();
			cookieStore.clear();
		}
	}

	private String getCookie() {

		String cookie = null;
		cookiepdf = SApplication.getInstance().getSharedPreferences("cookie", 0);
		String name = cookiepdf.getString("name", null);
		String value = cookiepdf.getString("value", null);
		cookie = name + "=" + value;
		return cookie;

	}

}
