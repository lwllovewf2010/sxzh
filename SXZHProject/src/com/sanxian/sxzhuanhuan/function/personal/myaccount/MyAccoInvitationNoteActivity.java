package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.InvitationUser;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
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
    private CommonAPI api = null;
    private String dealwithtype = ""; //操作类型
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_invitation_note);
		initView();
		initListener();
		initData("get_invite_list");
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
    /**
     * 初始化数据（获取，清除收货地址列表）
     * joe
     * @param type
     */
	public void initData(String type){
		dealwithtype = type;
		String array[] = getOpen_idOrToken();
		if(api == null){
			api = new CommonAPI();
		}
		Map<String,String> paramsmap = new HashMap<String, String>();
		paramsmap.put("open_id",array[0]);
		paramsmap.put("token", array[1]);
		api.inviteUserOrList(type,paramsmap, this, INVITEUSERLIST);
	}
	
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.title_right:
			if(invitelist.size() > 0){
			initData("remove_invite_list");	
			}
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
						if("get_invite_list".equals(dealwithtype)){
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
						}else if("remove_invite_list".equals(dealwithtype)){
							invitelist.clear();
							Util.toastInfo(this, "清除邀请记录成功");
						}
					}else if(status == 1001){
						Intent intent = new Intent(this, LoginActivity.class);
						startActivityForResult(intent,Constant.REQUEST_LOGIN_CODE);
					}else{
						if("get_invite_list".equals(dealwithtype)){
							Util.toastInfo(this, "获取邀请记录失败");
						}else if("remove_invite_list".equals(dealwithtype)){
							Util.toastInfo(this, "清除邀请记录失败");
						}
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
			initData("get_invite_list");
		}
	}
}
