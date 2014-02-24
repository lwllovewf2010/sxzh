package com.sanxian.sxzhuanhuan.function.personal.myfriends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.MyFriendsAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.function.personal.myfriends.adapter.SearchResultAdapter;
import com.sanxian.sxzhuanhuan.function.personal.setting.SetIndexActiVity;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
 * @ClassName: MyFriendsSearchActivity
 * @Description: 搜用户
 * @author honaf
 * @date 2014-1-3 下午4:11:38
 */
public class MyFriendsSearchActivity extends BaseActivity implements IBaseActivity, OnClickListener {
	private ListView listView;
	private LinearLayout soso_linear;
	public SearchResultAdapter adapter;
	public List<UserInfo> list = new ArrayList<UserInfo>();
	private EditText searchinput;
	private TextView search_cancel;
	private final int SEARCHUSER = 102;
	private final int ADDFRIEND=103;
	private MyFriendsAPI api = new MyFriendsAPI();

	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	@SuppressWarnings("deprecation")
	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfriends_searchpeople);
		intImageUtil();
		this.initView();
		this.initListener();

		adapter = new SearchResultAdapter(this, list);
		listView.setAdapter(adapter);

	}

	@Override
	public void initView() {
		super.initView();

		// super.button_right.setVisibility(View.GONE);
		super.button_right.setOnClickListener(this);
		super.button_right.setText("搜索");
		super.title_txt.setText("搜用户");

		listView = (ListView) this.findViewById(R.id.result_listview);
		listView.setVisibility(View.VISIBLE);
		soso_linear = (LinearLayout) this.findViewById(R.id.soso_linear);
		soso_linear.setVisibility(View.VISIBLE);
		searchinput = (EditText) this.findViewById(R.id.input);
		search_cancel = (TextView) this.findViewById(R.id.search_cancel);
		search_cancel.setOnClickListener(this);
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_right.setOnClickListener(this);
		super.button_left.setOnClickListener(this);
		listView.setOnItemClickListener(new MyOnItemClick());

	}
	int tempPosition=-1;
	class MyOnItemClick implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// if (position == 0) {
			// return;
			// }
			// Intent intent = new Intent(ContactsAddFriendSearchByName.this,
			// ContactsAddFriend.class);
			// intent.putExtra("userinfo", list.get(position - 1));
			// startActivity(intent);
			tempPosition=position;
			Map<String, String> map = new HashMap<String, String>();
			map.put("friend_id", list.get(position).getUid());
			api.addFriend(map, MyFriendsSearchActivity.this, ADDFRIEND);

		}
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case SEARCHUSER:
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						JSONArray contentArray = json.getJSONArray("content");
						JSONObject contentObject = null;
						UserInfo userInfo = null;
						for (int i = 0; i < contentArray.length(); i++) {
							contentObject = contentArray.getJSONObject(i);
							userInfo = new UserInfo();
							userInfo.setUsername(contentObject.getString("user_name"));
							userInfo.setAddress(contentObject.getString("area"));
							userInfo.setAvatar(contentObject.getString("photo"));
							userInfo.setUid(contentObject.getString("open_id"));
							if(!app.getLoginUserInfo().getUid().equals(userInfo.getUid())){
								list.add(userInfo);
							}
							

						}

					}
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;
		case ADDFRIEND:
			if (param.length > 0 && param[1] != null && param[1] instanceof String) {
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if (status == 0) {
						Util.toastInfo(this, "添加好友成功！");
						list.remove(tempPosition);
						adapter.notifyDataSetChanged();
					}else{
						Util.toastInfo(this, "添加好友失败！");	
					}
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.title_Left:
			this.finish();
			break;
		case R.id.title_right:
//			if ("".equals(searchinput.getText().toString().trim())) {
//				Util.toastInfo(this, "请输入关键字");
//				return;
//			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("keyword", searchinput.getText().toString().trim());
			api.getUnFriendList(map, this, SEARCHUSER);
			break;
		default:
			break;
		}
	}

}
