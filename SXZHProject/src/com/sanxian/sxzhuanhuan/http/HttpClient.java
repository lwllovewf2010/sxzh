package com.sanxian.sxzhuanhuan.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.params.HttpConnectionParams;

import android.util.Log;

//import weibo4j.Weibo;

/**
 * A utility class to handle HTTP request/response.
 * 
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class HttpClient implements java.io.Serializable {
	private static final int OK = 200;// OK: Success!
	private static final int NOT_MODIFIED = 304;// Not Modified: There was no
												// new data to return.
	private static final int BAD_REQUEST = 400;// Bad Request: The request was
												// invalid. An accompanying
												// error message will explain
												// why. This is the status code
												// will be returned during rate
												// limiting.
	private static final int NOT_AUTHORIZED = 401;// Not Authorized:
													// Authentication
													// credentials were missing
													// or incorrect.
	private static final int FORBIDDEN = 403;// Forbidden: The request is
												// understood, but it has been
												// refused. An accompanying
												// error message will explain
												// why.
	private static final int NOT_FOUND = 404;// Not Found: The URI requested is
												// invalid or the resource
												// requested, such as a user,
												// does not exists.
	private static final int NOT_ACCEPTABLE = 406;// Not Acceptable: Returned by
													// the Search API when an
													// invalid format is
													// specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server
															// Error: Something
															// is broken. Please
															// post to the group
															// so the Weibo team
															// can investigate.
	private static final int BAD_GATEWAY = 502;// Bad Gateway: Weibo is down or
												// being upgraded.
	private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable:
														// The Weibo servers are
														// up, but overloaded
														// with requests. Try
														// again later. The
														// search and trend
														// methods use this to
														// indicate when you are
														// being rate limited.

	private final static boolean DEBUG = true;

	private String basic;
	private int retryCount = 1;
	private int retryIntervalMillis = 2000;

	// private String uid = Configuration.getUid();
	// private String phone = Configuration.getPhone();
	// private String password = Configuration.getPassword();

	private int connectionTimeout = 10000;
	private int readTimeout = 10000;
	private static final long serialVersionUID = 808018030183407996L;
	private static boolean isJDK14orEarlier = false;
	private Map<String, String> requestHeaders = new HashMap<String, String>();
//	private static String sessionid = null;

	// static {
	// try {
	// String versionStr = System
	// .getProperty("java.specification.version");
	// if (null != versionStr) {
	// isJDK14orEarlier = 1.5d > Double.parseDouble(versionStr);
	// }
	// } catch (AccessControlException ace) {
	// isJDK14orEarlier = true;
	// }
	// }

	public HttpClient(String userId, String password) {
		this();
		// setUserId(userId);
		// setPassword(password);
	}

	public HttpClient() {
		this.basic = null;
		// setUserAgent(null);
		// setOAuthConsumer(null, null);
		setRequestHeader("Accept-Encoding", "gzip");
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public Response post(String url, PostParameter postParameters,
			boolean authenticated) throws PostException {
		PostParameter newPostParameters = new PostParameter();
		newPostParameters.addAll(postParameters);
		return httpRequest(url, newPostParameters, authenticated);
	}

	private final <T> T[] copy(T[] source) {
		Class type = source.getClass().getComponentType();
		T[] target = (T[]) Array.newInstance(type, source.length);
		// T[] target = (T[])Array.newInstance(type, source.length+1);

		System.arraycopy(source, 0, target, 0, source.length);
		return target;
	}

	public Response delete(String url, boolean authenticated)
			throws PostException {
		return httpRequest(url, null, authenticated, "DELETE");
	}

	// public Response multPartURL(String url, PostParameter[] params,
	// ImageItem item, boolean authenticated) throws PostException {
	// try {
	// org.apache.http.client.HttpClient client = new
	// org.apache.http.impl.client.DefaultHttpClient();
	// HttpPost httppost = new HttpPost(url);
	// MultipartEntity reqEntity = new MultipartEntity();
	// if (params != null) {
	// int i = 0;
	// for (PostParameter entry : params) {
	// reqEntity.addPart("entry.getName()",new StringBody(entry.getValue()));
	// }
	// parts[parts.length - 1] = new ByteArrayPart(item.getContent(),
	// item.getName(), item.getContentType());
	// }
	// post.setRequestEntity(new MultipartRequestEntity(parts, post
	// .getParams()));
	// client.executeMethod(post);
	//
	// Response response = new Response();
	// response.setResponseAsString(post.getResponseBodyAsString());
	// response.setStatusCode(post.getStatusCode());
	//
	// // log("multPartURL URL:" + url + ", result:" + response + ", time:"
	// // + (System.currentTimeMillis() - t));
	// return response;
	// } catch (Exception ex) {
	// throw new PostException(ex.getMessage(), ex, -1);
	// } finally {
	// post.releaseConnection();
	// }
	// }

	public Response multPartURL(/* String fileParamName, */String url,
			PostParameter params, /*
									 * File[] files, String videoParamName,
									 * File[] videos,
									 */HashMap<String, File[]> fileMap)
			throws PostException {
		org.apache.http.client.HttpClient client = new org.apache.http.impl.client.DefaultHttpClient();

		HttpPost httppost = new HttpPost(url);
		try {

			MultipartEntity reqEntity = new MultipartEntity();
			if (params != null) {
				int i = 0;
				for (; i< params.size(); i++) {
					
					StringBody sb = new StringBody(params.getValue(i),
							Charset.forName("UTF-8"));
					reqEntity.addPart(params.getKey(i), sb);
				}
			}

			Set<String> keySet = fileMap.keySet();
			String[] keys = new String[keySet.size()];
			keySet.toArray(keys);
			for (int i = 0; i < keys.length; i++) {
				File[] files = fileMap.get(keys[i]);
				for (int j = 0; j < files.length; j++) {
					File file = files[j];
					FileBody fb = new FileBody(file);
					reqEntity.addPart(keys[i], fb);
				}
			}

			// if (files != null) {
			// for (int i = 0; i < files.length; i++) {
			// File file = files[i];
			// FileBody fb = new FileBody(file);
			// reqEntity.addPart(fileParamName, fb);
			// }
			// }
			// if(videos != null)
			// {
			// for(int i = 0; i < videos.length; i++)
			// {
			// File file = videos[i];
			// FileBody fb = new FileBody(file);
			// reqEntity.addPart(videoParamName, fb);
			// }
			// }
			httppost.setEntity(reqEntity);
//			if (!Util.isEmpty(HttpClient.sessionid)) {
//				httppost.setHeader("Cookie", HttpClient.sessionid);
//			}
			// socket read数据的超时时间
			client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
					5000);
			// 上传连接超时，因要上传图片、、音频等信息，所以事件较大
			client.getParams().setIntParameter(
					HttpConnectionParams.CONNECTION_TIMEOUT, 120000);

			HttpResponse rp = client.execute(httppost);
			BufferedReader reader = new BufferedReader(new InputStreamReader(rp
					.getEntity().getContent(), "UTF-8"));
			StringBuilder builder = new StringBuilder();
			for (String line = null; (line = reader.readLine()) != null;) {
				builder.append(line).append("\n");
			}
			Response response = new Response();
			response.setResponseAsString(builder.toString());
			response.setPostParams(params);
			response.setUrl(url);
			response.setFileMap(fileMap);
			// response.setFiles(files);
			// response.setFileParamName(fileParamName);
			// response.setStatusCode();
			return response;
		} catch (Exception ex) {
			throw new PostException(ex.getMessage(), ex, -1);
		} finally {
			// post.releaseConnection();
			client = null;
		}
	}

	// private static class ByteArrayPart extends PartBase {
	// private byte[] mData;
	// private String mName;
	//
	// public ByteArrayPart(byte[] data, String name, String type)
	// throws IOException {
	// super(name, type, "UTF-8", "binary");
	// mName = name;
	// mData = data;
	// }
	//
	// @Override
	// protected void sendData(OutputStream out) throws IOException {
	// out.write(mData);
	// }
	//
	// @Override
	// protected long lengthOfData() throws IOException {
	// return mData.length;
	// }
	//
	// @Override
	// protected void sendDispositionHeader(OutputStream out)
	// throws IOException {
	// super.sendDispositionHeader(out);
	// StringBuilder buf = new StringBuilder();
	// buf.append("; filename=\"").append(mName).append("\"");
	// out.write(buf.toString().getBytes());
	// }
	// }

	public Response post(String url, boolean authenticated)
			throws PostException {
		return httpRequest(url, new PostParameter(), authenticated);
	}

	public Response post(String url, PostParameter PostParameters)
			throws PostException {
		return httpRequest(url, PostParameters, false);
	}

	public Response post(String url) throws PostException {
		return httpRequest(url, new PostParameter(), false);
	}

	public Response get(String url, boolean authenticated) throws PostException {
		return httpRequest(url, null, authenticated);
	}

	public Response get(String url) throws PostException {
		return httpRequest(url, null, false);
	}

	protected Response httpRequest(String url, PostParameter postParams,
			boolean authenticated) throws PostException {
		// 统一增加source 参数
		// int len = 1;
		// PostParameter[] newPostParameters = postParams;
		String method = "GET";
		if (postParams != null) {
			method = "POST";
			// len = postParams.length + 1;
			// newPostParameters = copy(postParams);
			// newPostParameters[postParams.length] = new
			// PostParameter("source",Weibo.CONSUMER_KEY);
		}
		return httpRequest(url, postParams, authenticated, method);
	}

	public Response httpRequest(String url, PostParameter postParams,
			boolean authenticated, String httpMethod) throws PostException {
		int retriedCount;
		int retry = retryCount;
		Response res = null;
		for (retriedCount = 0; retriedCount < retry; retriedCount++) {
			int responseCode = -1;
			// try {
			HttpURLConnection con = null;
			OutputStream osw = null;
			try {
				con = getConnection(url);
				con.setDoInput(true);
				//保留sessonid来登陆
				/*
				if (!Util.isEmpty(HttpClient.sessionid)) {
					con.setRequestProperty("Cookie", HttpClient.sessionid);
				}
				*/
				if (null != postParams || "POST".equals(httpMethod)) {
					con.setRequestMethod("POST");
					con.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
					con.setDoOutput(true);
					con.setUseCaches(false);
					String postParam = "";
					if (postParams != null) {
						postParam = encodeParameters(postParams);
					}
					// log("Post Params: ", postParam);
					byte[] bytes = postParam.getBytes("UTF-8");
//					con.setRequestProperty("Content-Length",
//							Integer.toString(bytes.length));
					con.connect();
					osw = con.getOutputStream();
					osw.write(bytes);
					osw.flush();
					osw.close();
				} else if ("DELETE".equals(httpMethod)) {
					con.setRequestMethod("DELETE");
				} else {
					con.setRequestMethod("GET");
				}
				res = new Response(con);
				res.setPostParams(postParams);
				responseCode = con.getResponseCode();

				/*
				String session_value = con.getHeaderField("Set-Cookie");
				if (!Util.isEmpty(session_value)) {
					String[] session = session_value.split(";");
					if (session.length > 0 && session[0].contains("JSESSIONID"))
						HttpClient.sessionid = session[0];
				}
				*/
				if (DEBUG) {
					Map<String, List<String>> responseHeaders = con
							.getHeaderFields();
					for (String key : responseHeaders.keySet()) {
						List<String> values = responseHeaders.get(key);
						for (String value : values) {
							if (null != key) {
								log(key + ": " + value);
							} else {
								log(value);
							}
						}
					}
				}
				if (responseCode != OK) {
					if (responseCode < INTERNAL_SERVER_ERROR
							|| retriedCount == retryCount) {
						throw new PostException(getCause(responseCode) + "\n"
								+ res.asString(), responseCode);
					}
					// will retry if the status code is
					// INTERNAL_SERVER_ERROR
				} else {
					break;
				}
			} catch (IOException io) {
				Log.e("IOException","IOException");
			} catch (Exception ex) {
				if (retriedCount == retryCount) {
					// throw new PostException(ex.getMessage(), ex,
					// responseCode);
				}

				// con.getErrorStream();
				// ex.printStackTrace();
			} finally {
				try {
					if (osw != null) {
						osw.close();
					}
					// if(con != null)
					// {
					// con.disconnect();
					// }
				} catch (Exception ignore) {
				}
			}
			// } catch (IOException ioe) {
			// // connection timeout or read timeout
			// if (retriedCount == retryCount) {
			// throw new ß(ioe.getMessage(), ioe,
			// responseCode);
			// }
			// }
			try {
				if (DEBUG && null != res) {
					res.asString();
				}
				// log("Sleeping " + retryIntervalMillis
				// + " millisecs for next retry.");
				Thread.sleep(retryIntervalMillis);
			} catch (InterruptedException ignore) {
				// nothing to do
			}
		}
		if (res == null) {
			res = new Response();
		}
		return res;
	}

	public static String encodeParameters(PostParameter postParams) {
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < postParams.size(); j++) {
			if (j != 0) {
				buf.append("&");
			}
			try {
				buf.append(URLEncoder.encode(postParams.getKey(j), "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(postParams.getValue(j), "UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
			}
		}
		return buf.toString();

	}

	/**
	 * sets HTTP headers
	 * 
	 * @param connection
	 *            HttpURLConnection
	 * @param authenticated
	 *            boolean
	 */
	private void setHeaders(String url, PostParameter params,
			HttpURLConnection connection, boolean authenticated,
			String httpMethod) {
		log("Request: ");
		log(httpMethod + " ", url);

		// if (authenticated) {
		// if (basic == null && oauth == null) {
		// }
		// String authorization = null;
		// if (null != oauth) {
		// // use OAuth
		// authorization = oauth.generateAuthorizationHeader(httpMethod,
		// url, params, oauthToken);
		// } else if (null != basic) {
		// // use Basic Auth
		// authorization = this.basic;
		// } else {
		// throw new IllegalStateException(
		// "Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
		// }
		// connection.addRequestProperty("Authorization", authorization);
		// log("Authorization: " + authorization);
		// }
		for (String key : requestHeaders.keySet()) {
			connection.addRequestProperty(key, requestHeaders.get(key));
			log(key + ": " + requestHeaders.get(key));
		}
	}

	public void setRequestHeader(String name, String value) {
		requestHeaders.put(name, value);
	}

	public String getRequestHeader(String name) {
		return requestHeaders.get(name);
	}

	private HttpURLConnection getConnection(String url) throws IOException {
		HttpURLConnection con = null;
		con = (HttpURLConnection) new URL(url).openConnection();
		if (connectionTimeout > 0 && !isJDK14orEarlier) {
			con.setConnectTimeout(connectionTimeout);
		}
		if (readTimeout > 0 && !isJDK14orEarlier) {
			con.setReadTimeout(readTimeout);
		}
		return con;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof HttpClient))
			return false;

		HttpClient that = (HttpClient) o;

		if (connectionTimeout != that.connectionTimeout)
			return false;

		if (readTimeout != that.readTimeout)
			return false;
		if (retryCount != that.retryCount)
			return false;
		if (retryIntervalMillis != that.retryIntervalMillis)
			return false;

		if (basic != null ? !basic.equals(that.basic) : that.basic != null)
			return false;

		if (!requestHeaders.equals(that.requestHeaders))
			return false;

		// if (uid != null ? ! uid.equals(that.uid) : that.uid != null)
		// return false;

		return true;
	}

	/*
	 * @Override public int hashCode() { int result = basic != null ?
	 * basic.hashCode() : 0; result = 31 * result + retryCount; result = 31 *
	 * result + retryIntervalMillis; // result = 31 * result + (uid != null ?
	 * uid.hashCode() : 0); // result = 31 * result + (password != null ?
	 * password.hashCode() : 0); result = 31 * result + (proxyHost != null ?
	 * proxyHost.hashCode() : 0); result = 31 * result + proxyPort; result = 31
	 * * result + (proxyAuthUser != null ? proxyAuthUser.hashCode() : 0); result
	 * = 31 result + (proxyAuthPassword != null ? proxyAuthPassword.hashCode() :
	 * 0); result = 31 * result + connectionTimeout; result = 31 * result +
	 * readTimeout; result = 31 * result + requestHeaders.hashCode(); result =
	 * 31 * result + (oauth != null ? oauth.hashCode() : 0); result = 31 *
	 * result + requestTokenURL.hashCode(); result = 31 * result +
	 * authorizationURL.hashCode(); result = 31 * result +
	 * authenticationURL.hashCode(); result = 31 * result + (accessTokenURL !=
	 * null ? accessTokenURL.hashCode() : 0); // result = 31 * result +
	 * (oauthToken != null ? oauthToken.hashCode() : // 0); return result; }
	 */
	private static void log(String message) {
		if (DEBUG) {
			System.out.println("[" + new java.util.Date() + "]" + message);
		}
	}

	private static void log(String message, String message2) {
		if (DEBUG) {
			log(message + message2);
		}
	}

	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
		case NOT_MODIFIED:
			break;
		case BAD_REQUEST:
			cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
			break;
		case NOT_AUTHORIZED:
			cause = "Authentication credentials were missing or incorrect.";
			break;
		case FORBIDDEN:
			cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
			break;
		case NOT_ACCEPTABLE:
			cause = "Returned by the Search API when an invalid format is specified in the request.";
			break;
		case INTERNAL_SERVER_ERROR:
			cause = "Something is broken.  Please post to the group so the Weibo team can investigate.";
			break;
		case BAD_GATEWAY:
			cause = "Weibo is down or being upgraded.";
			break;
		case SERVICE_UNAVAILABLE:
			cause = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
			break;
		default:
			cause = "";
		}
		return statusCode + ":" + cause;
	}
}
