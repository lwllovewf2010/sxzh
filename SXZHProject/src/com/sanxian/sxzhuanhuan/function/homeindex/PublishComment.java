package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommentAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.util.Util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;


/**
 * 发表评论
 * @author luozhiren
 *
 */
public class PublishComment extends BaseActivity implements OnClickListener{
	
	private static final int POSTCOMMENTCONTENT = 1;
	public CommentAPI api = null;
	private String creativeID;
	private EditText comment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_comment);
		Intent intent = getIntent();
		creativeID = intent.getStringExtra("creativeID");
		
		init();
	}

	
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case POSTCOMMENTCONTENT:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						Util.toastInfo(this, "发表成功！");
						this.finish();
					}else if (status == 1){
						Util.toastInfo(this, "发表失败，请重试！");
					}else if (status == 2){
						Util.toastInfo(this, "您已经对该创意发表过评论，不能再发表！");
						this.finish();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
	}	
	
	/**
	 * 初始化数据
	 */
	private void init(){
		initView();
		comment = (EditText)findViewById(R.id.topic_content_comment_content);
		setTitle("创意评论");
		setLeft("取消");
		displayRight();
		button_left.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.topic_content_comment_content_submit:
			String coments = comment.getText().toString();
			if("".endsWith(coments.trim())) {
				Util.toastInfo(this, "评论不能为空，请填写评论，然后再发表！");
				return;
			}
			postCommentContent("0",creativeID,coments,"3");
			break;

		default:
			break;
		}
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
	void postCommentContent(String yid,String objtid,String content,String ctype){
		api = CommentAPI.getInstance();
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		String[] userInfo = getOpen_idOrToken();
		if("".endsWith(userInfo[0].trim())) {
			Util.toastInfo(this, "未登录，或登录超时，请重新登录！");
			return;
		}
		paramsMap.put("open_id", userInfo[0]);
		paramsMap.put("token", userInfo[1]);
		paramsMap.put("yid", yid);
		paramsMap.put("objtid", objtid);
		paramsMap.put("content", content);
		paramsMap.put("ctype", ctype);
		api.postCommentContent(paramsMap, this, POSTCOMMENTCONTENT);
	}
}
