package com.sanxian.sxzhuanhuan.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.RequestInputInfo;
import com.sanxian.sxzhuanhuan.util.Util;

public class CommentAPI extends BaseAPI {
	public static CommentAPI api = null;
	
	
	public static CommentAPI getInstance(){
		 if(api == null){
			 api = new CommentAPI();
		 }
		 return api;
	 }

	/*
	 * 发表看法
	 * {"action":"originality","type":"support","mcr":0,"params":
	 * {"open_id":"5_1278_539947","token":"b1fccbf52f67ca26","oid":"22","t":"1"}}
	 */
	public void postComments(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType("support");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/*
	 * 获取表态统计数
	 * {"action":"originality","type":"get_support_info","mcr":0,"params":{"oid":"22"}}
	 */
	public void getCommentNum(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("originality");
		inputinfo.setType("get_support_info");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	/*
	 * 获取评论信息
	 * {"action":"comment","type":"get_comment_info","mcr":0,"params":{"oid":"22","ctype":3,"total_count":1}}
	 */
	public void getCommentInfo(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("comment");
		inputinfo.setType("get_comment_info");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	/*
	 * 发表赞
	 * {"action":"comment","type":"support_comment","mcr":0,
         "params":{"open_id":"5_1278_539947","token":"b1fccbf52f67ca26","t":"2","comment_id":"1"}}
	 */
	public void postCommentZan(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("comment");
		inputinfo.setType("support_comment");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	/*
	 * 发表评论
	 * {"action":"comment","type":"comment_add","mcr":0,"params":{"open_id":"5_1278_539947",
           "token":"b1fccbf52f67ca26","yid":"0","objtid":"22","content":"i\u5730\u65b9'd'sdf","ctype":"3"}}
	 */
	public void postCommentContent(final Map<String,String> paramsmap, BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		RequestInputInfo inputinfo = new RequestInputInfo();
		inputinfo.setAction("comment");
		inputinfo.setType("comment_add");
		inputinfo.setMcr("0");
		inputinfo.setParams(paramsmap);
		params.put("input", Util.toJSONObject(inputinfo));
		request("", params, HTTPMETHOD_POST, false, listener, requestCode);
	}
	
	/** 
	* @Title: postCommentContent 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* {"open_id":"5_1278_539947",
           "token":"b1fccbf52f67ca26","yid":"0","objtid":"22","content":"i\u5730\u65b9'd'sdf","ctype":"3"}}
	* @return void    返回类型 
	* @throws 
	*/
	public void postCommentContent(String[] userInfo,String yid,String objtid,String content,String ctype,BaseActivity context,int POSTCOMMENTCONTENT){
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("open_id", userInfo[0]);
		paramsMap.put("token", userInfo[1]);
		paramsMap.put("yid", yid);
		paramsMap.put("objtid", objtid);
		paramsMap.put("content", content);
		paramsMap.put("ctype", ctype);
		CommentAPI.getInstance().postCommentContent(paramsMap, context, POSTCOMMENTCONTENT);
	}
	
	/** 
	* @Title: postCommentContent 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* {"action":"comment","type":"support_comment","mcr":0,
         "params":{"open_id":"5_1278_539947","token":"b1fccbf52f67ca26","t":"2","comment_id":"1"}}
	* @return void    返回类型 
	* @throws 
	*/
	public void postCommentZan(String[] userInfo,String comment_id,BaseActivity context,final int REQUESTCODE){
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("open_id", userInfo[0]);
		paramsMap.put("token", userInfo[1]);
		paramsMap.put("comment_id", comment_id);
		paramsMap.put("t", "1");// 1 表示支持
		CommentAPI.getInstance().postCommentZan(paramsMap, context, REQUESTCODE);
	}

}
