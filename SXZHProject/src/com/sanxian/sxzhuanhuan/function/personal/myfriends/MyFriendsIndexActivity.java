package com.sanxian.sxzhuanhuan.function.personal.myfriends;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
 * @ClassName: MyFriendsIndexActivity
 * @Description: 好友列表首页
 * @author honaf
 * @date 2014-1-2 下午2:46:15
 */
public class MyFriendsIndexActivity extends BaseActivity implements IBaseActivity, OnClickListener {
	private Spinner spinner;
	private EditText content_et;
	private Button search_btn;
	private ListView friends_listview;
	private Button near_people;
	private String spinnerDataItems[] = { "按最新联系人排序", "按姓名首字母排序", "只显示互加好友" };
	private int spinnerIdItems[] = { 0, 1, 2 };
	private FriendListviewAdapter adapter;
	private List<UserInfo> friendList = new ArrayList<UserInfo>();
	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfriends_index);
		intImageUtil();
		this.initView();
		this.initListener();
		spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerDataItems));
		getData();
		adapter = new FriendListviewAdapter(this, friendList);
		friends_listview.setAdapter(adapter);
	}

	@Override
	public void initView() {
		super.initView();
		spinner = (Spinner) this.findViewById(R.id.spinner);
		content_et = (EditText) this.findViewById(R.id.content_et);
		search_btn = (Button) this.findViewById(R.id.search_btn);
		friends_listview = (ListView) this.findViewById(R.id.friends_listview);
		near_people = (Button) this.findViewById(R.id.near_people);
		near_people.setVisibility(View.VISIBLE);
	}

	public void getData() {
		UserInfo userInfo = null;
		for (int i = 0; i < 20; i++) {
			userInfo = new UserInfo();
			userInfo.setUid(i + "");
			userInfo.setFullname("乔布斯" + i);
			userInfo.setAddress("深圳" + i);
			userInfo.setAvatar("http://h.hiphotos.baidu.com/image/w%3D2048/sign=443597d217ce36d3a20484300ecb3b87/3801213fb80e7bec4b6768e92d2eb9389b506b7c.jpg");
			friendList.add(userInfo);
		}
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);
		super.button_right.setOnClickListener(this);
		title_txt.setText("我的好友");
		button_right.setText("添加");
		search_btn.setOnClickListener(this);
		near_people.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.search_btn:// 搜索按钮
			Util.toastInfo(this, "搜索");
			break;
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.title_right:
			intent = new Intent(this, MyFriendsAddFriendActivity.class);
			startActivity(intent);
			break;
		case R.id.near_people:// 附近的人
			intent = new Intent(this, MyFriendsNearPeopleActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	/**
	 * 
	* @ClassName: FriendListviewAdapter  
	* @Description: 好友列表适配器  
	* @author honaf
	* @date 2014-1-3 下午3:41:06
	 */
	class FriendListviewAdapter extends BaseAdapter {
		List<UserInfo> list = new ArrayList<UserInfo>();
		private LayoutInflater mInflater = null;
		private Context context;

		public FriendListviewAdapter(Context context, List<UserInfo> list) {
			super();
			this.context = context;
			this.list = list;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);

		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			UserInfo userInfo = list.get(position);

			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.myfriend_index_lv_item, null);
				holder.lvitem_img = (ImageView) convertView.findViewById(R.id.lvitem_img);
				holder.lvitem_address = (TextView) convertView.findViewById(R.id.lvitem_address);
				holder.lvitem_operation = (LinearLayout) convertView.findViewById(R.id.lvitem_operation);
				holder.lvitem_username = (TextView) convertView.findViewById(R.id.lvitem_username);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			imageLoader.displayImage(userInfo.getAvatar(), holder.lvitem_img, options);
			holder.lvitem_address.setText(userInfo.getAddress());
			holder.lvitem_username.setText(userInfo.getFullname());
			holder.lvitem_operation.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Util.toastInfo(MyFriendsIndexActivity.this, "消息入口");
				}
			});
			return convertView;
		}

		public final class ViewHolder {

			public ImageView lvitem_img;
			public TextView lvitem_username;
			public TextView lvitem_address;
			public LinearLayout lvitem_operation;// 点击聊天入口
		}
	}
	/*
	 * @Override public void refresh(Object... param) {
	 * 
	 * super.refresh(param); int falg = ((Integer) param[0]).intValue(); switch
	 * (falg) { case GETMYFRIENDLIST: if (param.length > 0 && param[1] != null
	 * && param[1] instanceof String) { String data = param[1].toString(); try {
	 * JSONObject json = new JSONObject(data); int status =
	 * json.getInt("status"); JSONObject dataObj = null;
	 * Log.e("honaf-contactindex", "getfriendlist" + data); if (status == 1) {
	 * 
	 * if (friendList != null) { friendList.clear(); } dataObj =
	 * json.optJSONObject("data"); if (dataObj == null) { return; } JSONArray
	 * dataArray = null; UserInfo u = null;
	 * 
	 * for (int i = 0; i < CApplication.defaultLetters.length; i++) {
	 * 
	 * dataArray = dataObj.optJSONArray(CApplication.defaultLetters[i]);
	 * JSONObject zimuObj = null;
	 * 
	 * if (dataArray != null && dataArray.length() != 0) {
	 * 
	 * for (int j = 0; j < dataArray.length(); j++) { zimuObj =
	 * dataArray.optJSONObject(j);
	 * 
	 * if (zimuObj != null) {
	 * 
	 * u = new UserInfo(); u.setName_letter(CApplication.defaultLetters[i]);
	 * u.setUid(zimuObj.optString("uid"));
	 * u.setFullname(zimuObj.optString("name"));
	 * u.setMobile(zimuObj.optString("mobile"));
	 * u.setCompany(zimuObj.optString("company"));
	 * u.setSex(zimuObj.optString("sex"));
	 * u.setPosition(zimuObj.optString("duty"));
	 * u.setAvatar(zimuObj.optString("avatar"));
	 * u.setTrade(zimuObj.optString("industry")); friendList.add(u);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } if (!"".equals(dataObj.optString("changetime"))) { last_changetime =
	 * dataObj.optString("changetime"); } refreshHandler.sendEmptyMessage(0);
	 * 
	 * } else { Util.toastInfo(guide, "还没有好友，人气不行哦"); }
	 * 
	 * } catch (Exception e) { Util.toastInfo(guide, "还没有好友，人气不行哦");
	 * e.printStackTrace(); } finally {
	 * 
	 * }
	 * 
	 * } break;
	 * 
	 * case DELFRIEND: if (param.length > 0 && param[1] != null && param[1]
	 * instanceof String) { String data = param[1].toString(); try { JSONObject
	 * json = new JSONObject(data); int status = json.getInt("status"); if
	 * (status == 1) {
	 * 
	 * Util.toastInfo(guide, "删除成功");
	 * 
	 * api.updateCache("friend", app.getLoginUserInfo().getUid(),
	 * last_changetime + "", false, ContactsIndex.this, UPDATECACHE);
	 * 
	 * 
	 * } else { Util.toastInfo(guide, "删除失败"); }
	 * 
	 * } catch (Exception e) { Util.toastInfo(guide, "解析异常");
	 * e.printStackTrace(); } finally {
	 * 
	 * }
	 * 
	 * } break;
	 * 
	 * case DELFRIENDFORSEARCH: if (param.length > 0 && param[1] != null &&
	 * param[1] instanceof String) { String data = param[1].toString(); try {
	 * JSONObject json = new JSONObject(data); int status =
	 * json.getInt("status"); if (status == 1) {
	 * searchlist.remove(tempLongPosition);
	 * resultAdapter.notifyDataSetChanged(); } else { Util.toastInfo(guide,
	 * "没有返回数据"); }
	 * 
	 * } catch (Exception e) { Util.toastInfo(guide, "解析异常");
	 * e.printStackTrace(); } finally {
	 * 
	 * }
	 * 
	 * } break; case UPDATECACHE: if (param.length > 0 && param[1] != null &&
	 * param[1] instanceof String) { String data = param[1].toString(); try {
	 * JSONObject json = new JSONObject(data); int status =
	 * json.getInt("status"); JSONObject dataObj = null;
	 * Log.e("honaf-contactindex", "updatecache" + data); if (status == 1) {
	 * 
	 * dataObj = json.optJSONObject("data");
	 * 
	 * if (dataObj != null) { if (!"".equals(dataObj.optString("changetime"))) {
	 * last_changetime = dataObj.optString("changetime"); }
	 * 
	 * JSONObject insertObj = dataObj.optJSONObject("insert"); if (insertObj !=
	 * null) { insertfriendcacheList = convertJsonObject(insertObj); } else {
	 * insertfriendcacheList.clear(); }
	 * 
	 * JSONObject deleteObj = dataObj.optJSONObject("delete"); if (deleteObj !=
	 * null) { deletefriendcacheList = convertJsonObject(deleteObj); } else {
	 * deletefriendcacheList.clear(); }
	 * 
	 * JSONObject updateObj = dataObj.optJSONObject("update"); if (updateObj !=
	 * null) { updatefriendcacheList = convertJsonObject(updateObj); } else {
	 * updatefriendcacheList.clear(); }
	 * 
	 * refreshHandler.sendEmptyMessage(UPDATECACHESUCCESS); }
	 * 
	 * } else { }
	 * 
	 * } catch (Exception e) { Util.toastInfo(guide, "解析异常");
	 * e.printStackTrace(); } finally {
	 * 
	 * }
	 * 
	 * } break; } }
	 */

}
