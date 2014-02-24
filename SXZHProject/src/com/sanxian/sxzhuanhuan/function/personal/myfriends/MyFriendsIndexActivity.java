package com.sanxian.sxzhuanhuan.function.personal.myfriends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
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
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.MyFriendsAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.function.personal.setting.SetIndexActiVity;
import com.sanxian.sxzhuanhuan.message.ChatActivity;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppService;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppUtils;
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
//	private EditText content_et;
//	private Button search_btn;
	private ListView friends_listview;
	private Button near_people;
	private String spinnerDataItems[] = { "按最新联系人排序", "按姓名首字母排序", "只显示互加好友" };
	private int spinnerIdItems[] = { 0, 1, 2 };
	private FriendListviewAdapter adapter;
	private List<UserInfo> friendList = new ArrayList<UserInfo>();

	MyFriendsAPI api = new MyFriendsAPI();
	private final int GETFRIENDLIST = 100;
	private final int DELETEFRIEND  =101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfriends_index);
		intImageUtil();
		this.initView();
		this.initListener();
		spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerDataItems));
		adapter = new FriendListviewAdapter(this, friendList);
		friends_listview.setAdapter(adapter);
		api.getFriendList(new HashMap<String, String>(), this, GETFRIENDLIST);
	}

	@Override
	public void initView() {
		super.initView();
		spinner = (Spinner) this.findViewById(R.id.spinner);
//		content_et = (EditText) this.findViewById(R.id.content_et);
//		search_btn = (Button) this.findViewById(R.id.search_btn);
		friends_listview = (ListView) this.findViewById(R.id.friends_listview);
		near_people = (Button) this.findViewById(R.id.near_people);
		near_people.setVisibility(View.VISIBLE);
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);
		super.button_right.setOnClickListener(this);
		title_txt.setText("我的好友");
		button_right.setText("添加");
//		search_btn.setOnClickListener(this);
		near_people.setOnClickListener(this);
		friends_listview.setOnItemLongClickListener(new MyOnItemLongClick());
	}
	class MyOnItemLongClick implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			tempPosition=position;
			showMidDialog();
			return false;
		}
		
	}
	private void showMidDialog() {
		Intent intent = new Intent(this , MiddleDialog.class);
		MiddleDialogInfo info = new MiddleDialogInfo("提示", "你确定删除吗？", 
				false, "", "", "确定", "取消");
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_MIDDLE);
	}
	int tempPosition=-1;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

			if (resultCode == DialogConstant.MIDDLE_OK) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("friend_id", friendList.get(tempPosition).getUid());
				api.deleteFriend(map, this, DELETEFRIEND);
			} else if (resultCode == DialogConstant.MIDDLE_CANCEL) {
				
			}
		}
	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		try {
			switch (flag) {
			case GETFRIENDLIST:
				if (param.length > 0 && param[1] != null && param[1] instanceof String) {
					String jsondata = param[1].toString();
					if (Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						JSONObject jsonObject = new JSONObject(jsondata);
						JSONArray contentArray = jsonObject.optJSONArray("content");

						if (contentArray != null && contentArray.length() != 0) {
							UserInfo userInfo = null;
							JSONObject contentObject = null;
							for (int i = 0; i < contentArray.length(); i++) {
								contentObject = contentArray.optJSONObject(i);
								if (contentObject != null) {
									userInfo = new UserInfo();
									userInfo.setUid(contentObject.optString("open_id"));
									userInfo.setUsername(contentObject.optString("user_name"));
									userInfo.setAvatar(contentObject.optString("photo"));
									userInfo.setAddress(contentObject.optString("area"));
									friendList.add(userInfo);
								}

							}
							adapter.notifyDataSetChanged();
						}

					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
					}
				}
				break;
			case DELETEFRIEND:
				if (param.length > 0 && param[1] != null && param[1] instanceof String) {
					String jsondata = param[1].toString();
					if (Constant.ResultStatus.RESULT_OK == JSONParser.getReturnFlag(jsondata)) {
						friendList.remove(tempPosition);
						adapter.notifyDataSetChanged();
					} else if (Constant.ResultStatus.RESULT_FAIL == JSONParser.getReturnFlag(jsondata)) {
						
					}
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
//		case R.id.search_btn:// 搜索按钮
//			Util.toastInfo(this, "搜索");
//			break;
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
			final UserInfo userInfo = list.get(position);

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
			holder.lvitem_username.setText(userInfo.getUsername());
			holder.lvitem_operation.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MyFriendsIndexActivity.this, ChatActivity.class);
					intent.putExtra("userinfo", userInfo);
					startActivity(intent);

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

}
