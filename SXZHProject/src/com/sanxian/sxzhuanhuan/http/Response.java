package com.sanxian.sxzhuanhuan.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * A data class representing HTTP Response
 * 
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class Response {
	public final static String NO_LOGIN_USER = "No_Login";
	private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal<DocumentBuilder>() {
		@Override
		protected DocumentBuilder initialValue() {
			try {
				return DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
			} catch (ParserConfigurationException ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}
	};

	private int statusCode;
	private Document responseAsDocument = null;
	private String responseAsString = null;
	private InputStream is;

	// //////默认登录次数 ,登录次数限制为3次
	private int count = 1;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	private HashMap<String, File[]> fileMap;
	public HashMap<String, File[]> getFileMap() {
		return fileMap;
	}

	public void setFileMap(HashMap<String, File[]> fileMap) {
		this.fileMap = fileMap;
	}
//	private String fileParamName;
//
//	public String getFileParamName() {
//		return fileParamName;
//	}
//
//	public void setFileParamName(String fileParamName) {
//		this.fileParamName = fileParamName;
//	}

//	private File files[] = null;

//	public File[] getFiles() {
//		return files;
//	}
//
//	public void setFiles(File[] files) {
//		this.files = files;
//	}


	private PostParameter postParams;

	public PostParameter getPostParams() {
		return postParams;
	}

	public void setPostParams(PostParameter postParams) {
		this.postParams = postParams;
	}

	private HttpURLConnection con;
	private boolean streamConsumed = false;

	public Response() {

	}

	public Response(HttpURLConnection con) throws IOException {
		this.con = con;
		this.statusCode = con.getResponseCode();
		if (null == (is = con.getErrorStream())) {
			is = con.getInputStream();
		}
		if (null != is && "gzip".equals(con.getContentEncoding())) {
			// the response is gzipped
			is = new GZIPInputStream(is);
		}
	}

	// for test purpose
	/* package */Response(String content) {
		this.responseAsString = content;
	}

	public void reset(Response res) {
		this.con = res.getCon();
		this.statusCode = res.getStatusCode();
		this.is = res.getIs();
		this.responseAsString = res.getResponseAsString();
	}

	public InputStream getIs() {
		return is;
	}

	public HttpURLConnection getCon() {
		return con;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getResponseHeader(String name) {
		if (con != null)
			return con.getHeaderField(name);
		else
			return null;
	}

	/**
	 * Returns the response stream.<br>
	 * This method cannot be called after calling asString() or asDcoument()<br>
	 * It is suggested to call disconnect() after consuming the stream.
	 * 
	 * Disconnects the internal HttpURLConnection silently.
	 * 
	 * @return response body stream
	 * @throws MeeetException
	 * @see #disconnect()
	 */
	public InputStream asStream() {
		if (streamConsumed) {
			throw new IllegalStateException("Stream has already been consumed.");
		}
		return is;
	}

	/**
	 * Returns the response body as string.<br>
	 * Disconnects the internal HttpURLConnection silently.
	 * 
	 * @return response body
	 * @throws MeeetException
	 */
	public String asString() throws PostException {
		if (null == responseAsString) {
			BufferedReader br;
			try {
				InputStream stream = asStream();
				if (null == stream) {
					return "-1";
				}
				br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
				StringBuffer buf = new StringBuffer();
				String line;
				while (null != (line = br.readLine())) {
					buf.append(line).append("\n");
				}
				this.responseAsString = buf.toString();
//				if (Configuration.isDalvik()) {
//					this.responseAsString = unescape(responseAsString);
//				}
//				log(responseAsString);
				stream.close();
				con.disconnect();
				streamConsumed = true;
			} catch (NullPointerException npe) {
				// don't remember in which case npe can be thrown
				throw new PostException(npe.getMessage(), npe);
				
			} catch (IOException ioe) {
				throw new PostException(ioe.getMessage(), ioe);
			}
		}
		return responseAsString;
	}

	/**
	 * Returns the response body as org.w3c.dom.Document.<br>
	 * Disconnects the internal HttpURLConnection silently.
	 * 
	 * @return response body as org.w3c.dom.Document
	 * @throws MeeetException
	 */
	public Document asDocument() throws PostException {
		if (null == responseAsDocument) {
			try {
				// it should be faster to read the inputstream directly.
				// but makes it difficult to troubleshoot
				this.responseAsDocument = builders.get().parse(
						new ByteArrayInputStream(asString().getBytes("UTF-8")));
			} catch (SAXException saxe) {
				throw new PostException(
						"The response body was not well-formed:\n"
								+ responseAsString, saxe);
			} catch (IOException ioe) {
				throw new PostException(
						"There's something with the connection.", ioe);
			}
		}
		return responseAsDocument;
	}

	/**
	 * Returns the response body as sinat4j.org.json.JSONObject.<br>
	 * Disconnects the internal HttpURLConnection silently.
	 * 
	 * @return response body as sinat4j.org.json.JSONObject
	 * @throws WeiboException
	 */
	// public JSONObject asJSONObject() throws WeiboException {
	// try {
	// return new JSONObject(asString());
	// } catch (JSONException jsone) {
	// throw new WeiboException(jsone.getMessage() + ":" +
	// this.responseAsString, jsone);
	// }
	// }

	/**
	 * Returns the response body as sinat4j.org.json.JSONArray.<br>
	 * Disconnects the internal HttpURLConnection silently.
	 * 
	 * @return response body as sinat4j.org.json.JSONArray
	 * @throws MeeetException
	 */
	// public JSONArray asJSONArray() throws WeiboException {
	// try {
	// return new JSONArray(asString());
	// } catch (Exception jsone) {
	// throw new WeiboException(jsone.getMessage() + ":" +
	// this.responseAsString, jsone);
	// }
	// }

	public InputStreamReader asReader() {
		try {
			return new InputStreamReader(is, "UTF-8");
		} catch (java.io.UnsupportedEncodingException uee) {
			return new InputStreamReader(is);
		}
	}

	public void disconnect() {
		con.disconnect();
	}

	// // huangying
	public JsonNode asjsonNode() {
		// ////////登录次数限制为3次
//		if (count <= 3) {
			ObjectMapper om = new ObjectMapper();
			try {
				JsonNode rootNode = null;
				//rootNode.withArray(propertyName);
				if (is != null && this.statusCode == HttpURLConnection.HTTP_OK) {
					rootNode = om.readTree(is);
				} else if (responseAsString != null && this.statusCode != HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
					if(!Util.isEmpty(responseAsString))
					{
						rootNode = om.readTree(responseAsString);
					}
					else
					{
						return null;
					}
				} else {
					return null;
				}
				
				/*
				boolean ok = rootNode.path("ok").asBoolean();
				String err = rootNode.path("err").asText();

				if (!ok && NO_LOGIN_USER.equals(err)) {
					MtActivity mt = (MtActivity) MainService.getActivityByName("Mt");
					MeeetApplication applicaton = (MeeetApplication) mt.getApplicationContext();
					String pho = String.valueOf(applicaton.getNowuser().getPho());
					String pwd = MainService.nowpw;
					if (!MeeetUtil.isEmpty(pho) && !MeeetUtil.isEmpty(pwd)) {
						MeeetDataIF meeetData = new MeeetDataIF();
						Response res = meeetData.userLoginRes(pho, pwd,
								applicaton.getNowuser().getUid(), 0, 0,1);
						JsonNode jsonNode = res.asjsonNode();
						boolean okk = jsonNode.path("ok").asBoolean();
						count++;
						if (okk) {
							if (MeeetUtil.isEmpty(url) && this.con != null) {
								url = this.con.getURL().toString();
							}
							if (url != null) {
								Response newResponse = null;
								if (MeeetUtil.isEmpty(this.fileMap)) {
									newResponse = meeetData.post(url,
											this.postParams);
								} else {
									newResponse = meeetData.multPartURL(
											 url,
											this.postParams, this.fileMap);
								}

								this.reset(newResponse);
								rootNode = this.asjsonNode();
							} 
						}
					}
				}
				*/
				return rootNode;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
//		} else {
//			return null;
//		}
	}

	private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

	/**
	 * Unescape UTF-8 escaped characters to string.
	 * 
	 * @author pengjianq...@gmail.com
	 * 
	 * @param original
	 *            The string to be unescaped.
	 * @return The unescaped string
	 */
	public static String unescape(String original) {
		Matcher mm = escaped.matcher(original);
		StringBuffer unescaped = new StringBuffer();
		while (mm.find()) {
			mm.appendReplacement(unescaped, Character.toString((char) Integer
					.parseInt(mm.group(1), 10)));
		}
		mm.appendTail(unescaped);
		return unescaped.toString();
	}

	@Override
	public String toString() {
		if (null != responseAsString) {
			return responseAsString;
		}
		return "Response{" + "statusCode=" + statusCode + ", response="
				+ responseAsDocument + ", responseString='" + responseAsString
				+ '\'' + ", is=" + is + ", con=" + con + '}';
	}


	public String getResponseAsString() {
		return responseAsString;
	}

	public void setResponseAsString(String responseAsString) {
		this.responseAsString = responseAsString;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
