package com.sanxian.sxzhuanhuan.function.homeindex.originality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommentAPI;
import com.sanxian.sxzhuanhuan.api.HomeIndexGoodsAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.CommentInfo;
import com.sanxian.sxzhuanhuan.entity.OriginalityItemDetails;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.function.homeindex.PublishComment;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ScUtil;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.util.Util;
import com.sanxian.sxzhuanhuan.view.xlistview.XListView.IXListViewListener;

import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

public class OriginalityActivity extends BaseActivity implements
		OnClickListener, OnItemSelectedListener, IXListViewListener {

	private final int REQUESTCODE = 12;
	// 发表表态
	private final int POSTCOMMENTCODE = 1;
	// 获取表态数据
	private final int GETCOMMENT_NUM_CODE = 2;
	// 发表评论
	private final int POSTCOMMENTCONCENTCODE = 3;
	// 获取评论
	private final int GETCOMMENT_INFO_CODE = 4;

	private List<View> listViews; // Tab页面列表

	View topicDescribe; // 创意描述
	View topicComment;// 创意内容
	TextView topic_content_describe_title;
	TextView topic_content_describe_name;
	TextView topic_content_describe_hangye;
	TextView topic_content_describe_region;
	WebView topic_content_describe_text;
	Button topic_content_button;
	Button spinner;
	ListView lv_topic_comment_list;
	ProgressBar progressBarSupport;
	ProgressBar progressBarDisSupport;
	TextView tv_progressBarSupport;
	TextView tv_progressBarDisSupport;
	EditText et_replay;
	ListView listView1;
	String replyid = "0";
	String replyName = "";

	private HomeIndexGoodsAPI api = null;

	private OriginalityItemDetails originalityItem = new OriginalityItemDetails();
	String creativeID;// 创意的ID
	final String mimeType = "text/html";

	private ArrayList<CommentInfo> commentInfo = new ArrayList<CommentInfo>();
	OrgCommentAdapter commentAdapter;
	private CommentAPI orgAPI = null;
	// 创意表态数据:看好，不看好
	private String agreecount = "";
	private String disagreecount = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_content);
		Intent intent = getIntent();
		creativeID = intent.getStringExtra("creativeID");
		Log.d("", "yuanqikai creativeID = " + creativeID);
		ScUtil sc = new ScUtil(this);

		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		topicDescribe = mInflater
				.inflate(R.layout.topic_content_describe, null);
		topicComment = mInflater.inflate(R.layout.topic_content_comment, null);

		initView();

		listViews.add(topicDescribe);
		listViews.add(topicComment);

		sc.showSc("创意描述", "用户评论", listViews);

		initDate();

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		topic_content_describe_title = (TextView) topicDescribe
				.findViewById(R.id.topic_content_describe_title);
		topic_content_describe_name = (TextView) topicDescribe
				.findViewById(R.id.topic_content_describe_name);
		topic_content_describe_hangye = (TextView) topicDescribe
				.findViewById(R.id.topic_content_describe_hangye);
		topic_content_describe_region = (TextView) topicDescribe
				.findViewById(R.id.topic_content_describe_region);
		topic_content_describe_text = (WebView) topicDescribe
				.findViewById(R.id.topic_content_describe_text);
		topic_content_button = (Button) topicDescribe
				.findViewById(R.id.topic_content_button);
		// 初始化发表评论按钮
		Button butCommnet = (Button) topicComment
				.findViewById(R.id.topic_content_comment_publish_but);
		spinner = (Button) topicComment
				.findViewById(R.id.topic_content_commnent_publish_order_type);
		lv_topic_comment_list = (ListView) topicComment
				.findViewById(R.id.topic_content_comment_list);
		progressBarSupport = (ProgressBar) topicComment
				.findViewById(R.id.topic_content_comment_toupao_kanhao_progressBar);
		progressBarDisSupport = (ProgressBar) topicComment
				.findViewById(R.id.topic_content_comment_toupao_buqingchu_progressBar);
		tv_progressBarSupport = (TextView) topicComment
				.findViewById(R.id.topic_content_comment_toupao_kanhao_textview);
		tv_progressBarDisSupport = (TextView) topicComment
				.findViewById(R.id.topic_content_comment_toupao_buqingchu_textview);
		// et_replay = (EditText) topicComment.findViewById(R.id.et_replay);
		// listView1 = (ListView) topicComment.findViewById(R.id.listView1);
		commentAdapter = new OrgCommentAdapter(this, commentInfo);
		lv_topic_comment_list.setAdapter(commentAdapter);
		spinner.setOnClickListener(this);

		butCommnet.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
	public void onPopupButtonClick(View button) {
		PopupMenu popup = new PopupMenu(this, button);
		popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				spinner.setText(item.getTitle());
				return true;
			}
		});
		popup.show();
	}

	public void initDate() {
		// 这里进行http请求
		api = HomeIndexGoodsAPI.getInstance();
		orgAPI = CommentAPI.getInstance();

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("id", creativeID);
		// 获取创意基本信息
		api.getOriginalityItemInfo(paramsMap, this, REQUESTCODE);

		// 获取表态统计数
		paramsMap.clear();
		paramsMap.put("oid", creativeID);
		orgAPI.getCommentNum(paramsMap, this, GETCOMMENT_NUM_CODE);

		// 获取评论："params":{"oid":"22","ctype":3,"total_count":1}
		paramsMap.clear();
		paramsMap.put("oid", creativeID);
		paramsMap.put("ctype", "3");
		paramsMap.put("total_count", "1");
		orgAPI.getCommentInfo(paramsMap, this, GETCOMMENT_INFO_CODE);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		// 初始化请求
		case REQUESTCODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						JSONObject jsonmode = json.getJSONObject("content");

						if (jsonmode != null && jsonmode.length() > 0) {
							originalityItem.setOrg_name(jsonmode
									.getString("org_name"));
							originalityItem.setOrg_desc(jsonmode
									.getString("org_desc"));
							originalityItem.setId(jsonmode.getString("id"));
							originalityItem.setUser_id(jsonmode
									.getString("user_id"));
							originalityItem.setOrg_explain(jsonmode
									.getString("org_explain"));
							originalityItem.setOrg_video(jsonmode
									.getString("org_video"));
							originalityItem.setProvince_id(jsonmode
									.getString("province_id"));
							originalityItem.setCity_id(jsonmode
									.getString("city_id"));
							originalityItem.setCategory_id(jsonmode
									.getString("category_id"));
							originalityItem.setScancount(jsonmode
									.getString("scancount"));
							originalityItem.setAddtime(jsonmode
									.getString("addtime"));
							originalityItem.setUpdatetime(jsonmode
									.getString("updatetime"));
							originalityItem.setOrg_state(jsonmode
									.getString("org_state"));
							originalityItem.setGzcount(jsonmode
									.getString("gzcount"));
							originalityItem.setComment_nums(jsonmode
									.getString("comment_nums"));
							originalityItem.setFavorite_nums(jsonmode
									.getString("favorite_nums"));
							originalityItem.setCategory_name(jsonmode
									.getString("category_name"));
							originalityItem.setProvince_name(jsonmode
									.getString("province_name"));
							originalityItem.setCity_name(jsonmode
									.getString("city_name"));
							originalityItem.setUser_realname(jsonmode
									.getString("user_realname"));
						}
					} else {
						Util.toastInfo(this, "请求失败");
					}
					// adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 更新界面
			refreshUI(originalityItem);
			break;
		// 发送表态
		case POSTCOMMENTCODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						Util.toastInfo(this, "表态成功！");
					} else if (status == 1) {
						Util.toastInfo(this, "表态失败，请重试！");
					} else if (status == 2) {
						Util.toastInfo(this, "您已经表态过，不能再次表态！");
					}
					// adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		// 回复评论是否成功
		case POSTCOMMENTCONCENTCODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						Util.toastInfo(this, "回复成功！");
					} else if (status == 1) {
						Util.toastInfo(this, "回复失败，请重试！");
					} else if (status == 2) {
						Util.toastInfo(this, "您已经对该创意发表过评论，不能再发表！");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		// 获取评论信息
		case GETCOMMENT_INFO_CODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						JSONArray mJSONArray = json.getJSONArray("content");
						for (int i = 0; i < mJSONArray.length(); i++) {
							JSONObject jsonmode = mJSONArray.getJSONObject(i);
							if (jsonmode != null && jsonmode.length() > 0) {
								Log.d("", "yuanqikai jsonmode");
								setCommentInfo(jsonmode, true);
								// info.(jsonmode.getString("comment_groups"));
								if (!jsonmode.getString("comment_groups")
										.startsWith("[]")) {

									Log.d("",
											"yuanqikai comment_groups111 = "
													+ jsonmode
															.getString("comment_groups"));

									setCommentArrayInfo(jsonmode
											.getJSONArray("comment_groups"));
								} else {
									Log.d("",
											"yuanqikai comment_groups222 = "
													+ jsonmode
															.getString("comment_groups"));
								}
							}
						}
						commentAdapter.notifyDataSetChanged();
					} else {
						Util.toastInfo(this, "查询失败！");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		// 获取表态信息
		case GETCOMMENT_NUM_CODE:
			if (param.length > 0 && param[1] != null
					&& param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						JSONObject jsonmode = json.getJSONObject("content");
						if (jsonmode != null && jsonmode.length() > 0) {
							agreecount = jsonmode.getString("agreecount");
							disagreecount = jsonmode.getString("disagreecount");
							refreshSupportInfo(agreecount, disagreecount);
						}
					} else {
						Util.toastInfo(this, "查询失败！");
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

	void setCommentInfo(JSONObject jsonmode, boolean isMainComment)
			throws JSONException {
		CommentInfo info = new CommentInfo();
		info.setId(jsonmode.getString("id"));
		info.setContent(jsonmode.getString("content"));
		info.setDnum(jsonmode.getString("dnum"));
		info.setFdnum(jsonmode.getString("fdnum"));
		info.setCtype(jsonmode.getString("ctype"));
		info.setUserid(jsonmode.getString("userid"));
		info.setAddtime(jsonmode.getString("addtime"));
		info.setReplyid(jsonmode.getString("replyid"));
		// info.setReplyid((jsonmode.getString("r_userid"));
		info.setObjtid(jsonmode.getString("objtid"));
		info.setSid(jsonmode.getString("sid"));
		info.setUserName(jsonmode.getString("user_name"));
		if (isMainComment) {
			info.setUserImage(jsonmode.getString("photo"));
			commentInfo.add(info);
		} else {
			//这个数据只有非主评论才有！
			info.setR_user_name(jsonmode.getString("r_user_name"));
			
			commentInfo.get(commentInfo.size() - 1).getComment_groups()
					.add(info);
		}
	}

	void setCommentArrayInfo(JSONArray mJSONArray) throws JSONException {
		for (int i = 0; i < mJSONArray.length(); i++) {
			JSONObject jsonmode = mJSONArray.getJSONObject(i);
			if (jsonmode != null && jsonmode.length() > 0) {
				setCommentInfo(jsonmode, false);
			}
		}
	}

	private void refreshUI(OriginalityItemDetails item) {
		topic_content_describe_title.setText(item.getOrg_name());
		final String mimeType = "text/html;charset=UTF-8";
		topic_content_describe_text
				.loadData(item.getOrg_desc(), mimeType, null);
		// topic_content_describe_text.setu
		// settings.setUseWideViewPort(true);
		// settings.setLoadWithOverviewMode(true);
		topic_content_describe_region.setText("所属地域：" + item.getProvince_name()
				+ "·" + item.getCity_name());
		topic_content_describe_hangye
				.setText("所属行业：" + item.getCategory_name());
		// 判断useid
		SharedPreferences spf = getSharedPreferences("login_user", 0);
		String openid = spf.getString("open_id", "0");
		if ((item.getPid() != null) && (item.getPid().trim() != "0")
				&& (item.getPid().trim() != "")) {
			topic_content_button.setText(getResources().getString(
					R.string.topic_content_button2));
			topic_content_button.setVisibility(View.VISIBLE);
			topic_content_button.setTag("details");
		} else if (openid.trim().equals(item.getUser_id())) {
			topic_content_button.setVisibility(View.VISIBLE);
			topic_content_button.setTag("submit");
		}
		Log.d("openid", "openid = " + openid);
	}

	// 刷新投票UI
	public void refreshSupportInfo(String agree, String disagree) {
		int agr = 0;
		int disAgr = 0;
		int sum = 0;
		if (agree != null && (!"".endsWith(agree.trim()))) {
			agr = Integer.valueOf(agree);
		}
		if (disagree != null && (!"".endsWith(disagree.trim()))) {
			disAgr = Integer.valueOf(disagree);
		}

		sum = agr + disAgr;
		if (sum != 0) {
			agr = agr * 100 / sum;
			disAgr = disAgr * 100 / sum;
		}
		progressBarSupport.setProgress(agr);
		progressBarDisSupport.setProgress(disAgr);
		tv_progressBarSupport.setText(agree + "票");
		tv_progressBarDisSupport.setText(disagree + "票");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topic_content_comment_publish_but:
			Intent intent = new Intent(this, PublishComment.class);
			intent.putExtra("creativeID", creativeID);
			startActivity(intent);
			break;
		case R.id.topic_content_commnent_publish_order_type:
			onPopupButtonClick(v);
			break;
		case R.id.topic_content_button:
			String tag = (String) v.getTag();
			if (tag != null) {
				if (tag.trim().equals("submit")) {
					Toast.makeText(this, "发布项目", Toast.LENGTH_SHORT).show();
				} else if (tag.trim().equals("details")) {
					Toast.makeText(this, "项目详情", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.topic_content_comment_toupao_kanhao_button:
			// 获取登入信息
			String[] userInfo = getOpen_idOrToken();
			if ("".equals(userInfo[0])) {
				Util.toastInfo(this, "未登录、或登录超时，请先登录后评论！");
				Intent loginIntent = new Intent(this, LoginActivity.class);
				startActivity(loginIntent);
				return;
			}
			// 发送请求：1代表看好
			postComments(1 + "");
			break;
		case R.id.topic_content_comment_toupao_buqingchu_button:
			// 获取登入信息
			String[] userInfo2 = getOpen_idOrToken();
			if ("".equals(userInfo2[0])) {
				Util.toastInfo(this, "未登录、或登录超时，请先登录后评论！");
				Intent loginIntent = new Intent(this, LoginActivity.class);
				startActivity(loginIntent);
				return;
			}
			// 发送请求:2代表不看好
			postComments(2 + "");
			break;
		case R.id.btn_replay_send:
			// 提交回复
			OrgCommentAdapter.ViewHolder holder = (OrgCommentAdapter.ViewHolder) v
			.getTag();
			String coments = holder.et_replay.getText().toString();
			if ("".endsWith(coments.trim())) {
				Util.toastInfo(this, "评论不能为空，请填写评论，然后再发表！");
				return;
			}
			String[] userInfo3 = getOpen_idOrToken();
			if ("".endsWith(userInfo3[0].trim())) {
				Util.toastInfo(this, "未登录，或登录超时，请登录！");
				Intent loginIntent = new Intent(this,LoginActivity.class);
				startActivity(loginIntent);
				return;
			}
			
			String[] rId = (String[])holder.et_replay.getTag();
			String id = holder.id;
			if(rId[0]!=null&&!("".endsWith(rId[0].trim()))){
				id = rId[0];
			}
			Log.d("", "yuanqikai id = "+id);
			// 发表回复
			CommentAPI.getInstance().postCommentContent(userInfo3, id,
					creativeID, coments, "3", this, POSTCOMMENTCONCENTCODE);
			break;
		case R.id.ll_replay_item:
			// 点击子评论
			CommentItemAdapter.ViewHolder holder2 = (CommentItemAdapter.ViewHolder) v
					.getTag();
			replyid = holder2.id;
			replyName = holder2.userName;
			
			break;

		default:
			break;
		}
	}

	/**
	 * @Title: postComments
	 * @Description: TODO(发表态度)
	 * @param @param t 1：看好；2：不看好
	 * @return void 返回类型
	 * @throws
	 */
	void postComments(String t) {
		Map<String, String> params = new HashMap<String, String>();
		String[] temp = getOpen_idOrToken();
		if ("".endsWith(temp[0])) {
			Util.toastInfo(this, "未登录、或登录超时，请先登录后评论！");
			return;
		}
		params.put("open_id", temp[0]);
		params.put("token", temp[1]);
		params.put("oid", creativeID);
		params.put("t", t);
		orgAPI.postComments(params, this, POSTCOMMENTCODE);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		// TODO Auto-generated method stub
		// spinner选择触发事件
		Log.d("yuanqikai", "yuanqikai pos = " + pos);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// spinner未选择触发事件
		Log.d("yuanqikai", "yuanqikai onNothingSelected arg0 = " + arg0);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

}
