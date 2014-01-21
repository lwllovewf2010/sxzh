package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.InvitationUser;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter.InvitationNoteAdapter;
import com.sanxian.sxzhuanhuan.util.Util;
/**
 * 邀请用户记录页面
 * @author joe
 *
 */
public class MyAccoInvitationNoteActivity extends BaseActivity {
    private ListView note_list;
    private InvitationNoteAdapter adapter;
    private List<InvitationUser> invitelist;
    private final int INVITEUSERLIST = 10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_invitation_note);
		initView();
		initListener();
		initData();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setRight("清除");
		setTitle("邀请记录");
		note_list = (ListView)findViewById(R.id.invitation_note_list);
		invitelist = new ArrayList<InvitationUser>();
		adapter = new InvitationNoteAdapter(this,invitelist);
		note_list.setAdapter(adapter);
	}
 
	public void initData(){
		SharedPreferences spf = this.getSharedPreferences(
				"login_user", 0);
		String open_id = spf.getString("open_id", null);
		String token = spf.getString("token", null);
		if (open_id == null || token == null) {
			UIHelper.showLoginActivity(MyAccoInvitationNoteActivity.this);
		}else{
		CommonAPI api = new CommonAPI();
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",open_id);
		paramsmap.put("token",token);
		api.inviteUserOrList("get_invite_list",paramsmap, this, INVITEUSERLIST);
	   }
	}
	
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer)param[0]).intValue();
		switch (flag) {
		case INVITEUSERLIST:
			if(param.length > 0 && param[1] != null && param[1] instanceof String){
				String data = param[1].toString();
				try {
					JSONObject json = new JSONObject(data);
					int status = json.getInt("ret");
					if(status == 0){
						if(json.optString("content").equals("null") ){
							Util.toastInfo(this, "暂无邀请记录");
							return;
						}
						JSONArray array = json.getJSONArray("content");
						if(array.length() > 0){
							for(int i = 0; i < array.length();i++){
								JSONObject object = array.getJSONObject(i);
								InvitationUser inviteuser = new InvitationUser();
								inviteuser.setInvite_name(object.optString("friend_name"));
								inviteuser.setInvite_phone(object.optString("friend_mobile"));
								inviteuser.setInvite_status(object.optString("invite_state"));
								inviteuser.setInvite_time(object.optString("invite_time"));
								inviteuser.setInvite_userid(object.optString("u_open_id"));
								invitelist.add(inviteuser);
						  }
						}
					}else if(status == 1001){
						
					}else{
						
					}
					adapter.notifyDataSetChanged();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Constant.RESULT_LOGIN_CODE){
			initData();
		}
	}
}
