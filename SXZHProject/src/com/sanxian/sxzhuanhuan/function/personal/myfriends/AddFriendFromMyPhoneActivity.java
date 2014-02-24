package com.sanxian.sxzhuanhuan.function.personal.myfriends;

import java.util.ArrayList;
import java.util.List;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.util.PingYinUtil;
import com.sanxian.sxzhuanhuan.util.Util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



/**
 * 
 * @ClassName: ContactsAddFriendFromMyPhoneActivity
 * @Description: 获得手机通讯录
 * @author honaf
 * @date 2013-8-5 下午5:08:38
 */
public class AddFriendFromMyPhoneActivity extends BaseActivity
		implements OnClickListener {
	private ListView listView;
	private LinearLayout soso_linear;
	public AddFriendFromMyCardsAdapter adapter;
	public AddFriendFromMyCardsAdapter searchadapter;
	public String type = "default";
	private EditText searchinput;
	private TextView search_cancel;

	public List<UserInfo> list = new ArrayList<UserInfo>();
	public List<UserInfo> searchlist = new ArrayList<UserInfo>();
	private LayoutInflater inflater;
	private LinearLayout progressbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_addfriend_find);
		inflater=LayoutInflater.from(this);
		initView();
		initListener();
		
		adapter = new AddFriendFromMyCardsAdapter(this, list);
		View headerView = inflater.inflate(
				R.layout.contacts_head_item_for_transparent, null);
		listView.addHeaderView(headerView);
		listView.setAdapter(adapter);
		searchadapter = new AddFriendFromMyCardsAdapter(this, searchlist);
		GetPhoneListThread thread=new GetPhoneListThread();
		thread.start();
	}
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				progressbar.setVisibility(View.GONE);
				list.addAll(tempUserList);
				if(list!=null){
					if(list.size()==0){
						Util.toastInfo(AddFriendFromMyPhoneActivity.this, "没发现联系人哦");
					}
				}
				adapter.notifyDataSetChanged();
				break;
			}

			super.handleMessage(msg);
		}
	};
    List<UserInfo> tempUserList=new ArrayList<UserInfo>();
	class GetPhoneListThread extends Thread {
		public void run() {
			tempUserList=getNamecardFromPhone();
			mHandler.sendEmptyMessage(0);
		};
	
	}

	@Override
	public void initView() {
		super.initView();
		progressbar=(LinearLayout)this.findViewById(R.id.pro_bar_linear);
		listView = (ListView) this.findViewById(R.id.result_listview);
		listView.setVisibility(View.VISIBLE);
		soso_linear = (LinearLayout) this.findViewById(R.id.soso_linear);
		soso_linear.setVisibility(View.VISIBLE);
		searchinput = (EditText)this.findViewById(R.id.input);
		search_cancel=(TextView)this.findViewById(R.id.search_cancel);
		search_cancel.setOnClickListener(this);

	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_right.setOnClickListener(this);
		super.button_left.setOnClickListener(this);
//		super.button_right.setText(R.string.addfriends_soso);
		super.title_txt.setText("查看手机通讯录");
		displayRight();
		listView.setOnItemClickListener(new MyOnItemClick());
		searchinput.addTextChangedListener(new InputListener());
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
	}

	@Override
	public void onClick(View v) {
		if (v == button_left) {
			this.finish();
		} else if (v == button_right) {
		
		}else if(v==search_cancel){
			searchinput.setText("");
			listView.setAdapter(adapter);
			type = "default";
		}
	}

	public void getData(String keyword){
		if(list.size() > 0){
		  for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getRealName().contains(keyword)||list.get(i).getName_letter().toLowerCase().contains(keyword.toLowerCase())){
				searchlist.add(list.get(i));
			};
		 }	
		}
	}
	
	class MyOnItemClick implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if(position == 0){
				return;
			}
//			Intent intent = new Intent(AddFriendFromMyPhoneActivity.this,ContactsAddFriendInviteFriendActitivity.class);
//			intent.putExtra("from", AddFriendFromMyPhoneActivity.this.getIntent().getStringExtra("from"));//card
//			if("default".equals(type)){
//				intent.putExtra("userinfo", list.get(position - 1));
//			}else if("search".equals(type)){
//				intent.putExtra("userinfo", searchlist.get(position - 1));
//			}
//			startActivity(intent);
		}
	}

	public   List<UserInfo> getNamecardFromPhone() {
		List<UserInfo> phoneList = new ArrayList<UserInfo>();
		Cursor cursor = null;
		try {
			// 得到ContentResolver对象
			ContentResolver cr = this.getContentResolver();
			// 取得电话本中开始一项的光标
			cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
					null, null, null);
			// 向下移动光标
			UserInfo user = null;
			while (cursor.moveToNext()) {
				// 取得联系人名字
				user = new UserInfo();
				int nameFieldColumnIndex = cursor
						.getColumnIndex(PhoneLookup.DISPLAY_NAME);
				String contact = cursor.getString(nameFieldColumnIndex);
				// 取得电话号码
				String ContactId = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));
				Cursor phone = cr.query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
								+ "=" + ContactId, null, null);
				// 暂时只读取一个号码
				if (phone.moveToNext()) {
					user.setRealName(contact);
					user.setName_letter(PingYinUtil.getPingYin(contact));
					String PhoneNumber = phone
							.getString(phone
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					PhoneNumber = PhoneNumber.replace("+86", " ").trim();
					if (Util.checkMobile(PhoneNumber)) {
						user.setMobile(PhoneNumber);
						phoneList.add(user);
					}

				}

			}
			return phoneList;

		} catch (Exception e) {
			return null;
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			
		}

	}

	class AddFriendFromMyCardsAdapter extends BaseAdapter {
		private Context context;
		private List<UserInfo> list;
		private LayoutInflater mInflater;

		public AddFriendFromMyCardsAdapter(Context context,
				List<UserInfo> comcardsList) {
			this.context = context;
			this.list = comcardsList;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Holder holder;
			UserInfo entity = list.get(position);
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.contacts_addfriend_frommyphone_lvitem, null);
				holder = new Holder();
				holder.name = (TextView) convertView
						.findViewById(R.id.username);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.name.setText(entity.getRealName()
					);
			return convertView;
		}

		class Holder {
			TextView name;

		}
	}
   class InputListener implements TextWatcher{

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		String keyword = searchinput.getText().toString().trim();
		if(keyword.length() > 0){
			searchlist.clear();
			getData(keyword);
			listView.setAdapter(searchadapter);
			type = "search";
		}else{
			listView.setAdapter(adapter);
			type = "default";
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		
	}
	   
   }
}
