package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.entity.InvitationUser;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter.InvitationNoteAdapter;
/**
 * 邀请用户记录页面
 * @author joe
 *
 */
public class MyAccoInvitationNoteActivity extends BaseActivity {
    private ListView note_list;
    private InvitationNoteAdapter adapter;
    private List<InvitationUser> invitelist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_invitation_note);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setRight("清除");
		setTitle("邀请记录");
		initData();
		note_list = (ListView)findViewById(R.id.invitation_note_list);
		adapter = new InvitationNoteAdapter(this,invitelist);
		note_list.setAdapter(adapter);
	}
 
	public void initData(){
		invitelist = new ArrayList<InvitationUser>();
		for(int i = 0; i < 20;i++){
			InvitationUser inviteuser = new InvitationUser();
			inviteuser.setInvite_name("张三" + i);
			inviteuser.setInvite_phone("13423987" + i);
			inviteuser.setInvite_status("已成功" + i);
			inviteuser.setInvite_time("2014/1/" + i);
			inviteuser.setInvite_userid(""+i);
			invitelist.add(inviteuser);
			
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
	}

}
