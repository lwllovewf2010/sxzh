package com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.InvitationUser;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.PersonInfoActivity;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.RegisteredPersonInfoActivity;
/**
 * 邀请记录页面Adapter
 * @author joe
 *
 */
public class InvitationNoteAdapter extends BaseAdapter {
    private int listsize = 0;
    private LayoutInflater inflater = null;
    private Holder holder = null;
    private List<InvitationUser> list;
    private Context context;
    
	public InvitationNoteAdapter(Context context,List<InvitationUser> invitelist){
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.list = invitelist;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			holder = new Holder();
			convertView = inflater.inflate(R.layout.invitation_note_item, null);
			holder.invitation_name = (TextView)convertView.findViewById(R.id.invitation_name);
			holder.invitation_phone = (TextView)convertView.findViewById(R.id.invitation_phone);
			holder.invitation_time = (TextView)convertView.findViewById(R.id.invitation_time);
			holder.invitation_status = (TextView)convertView.findViewById(R.id.invitation_status);
			holder.invitation_look_data = (TextView)convertView.findViewById(R.id.invitation_look_data);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		String see_uid = list.get(position).getInvite_userid();
		if("".equals(see_uid)){
			holder.invitation_look_data.setVisibility(View.INVISIBLE);
		}else{
			holder.invitation_look_data.setVisibility(View.VISIBLE);
		}
		holder.invitation_name.setText(list.get(position).getInvite_name());
		holder.invitation_phone.setText(list.get(position).getInvite_phone());
		holder.invitation_time.setText(list.get(position).getInvite_time());
		String status = list.get(position).getInvite_status().trim();
		//0未注册 1已注册 2邀请已过期
		if("0".equals(status)){
			holder.invitation_status.setText("未注册");
		}else if("1".equals(status)){
			holder.invitation_status.setText("已注册");
		}else{
			holder.invitation_status.setText("邀请已过期");
		}
		holder.invitation_look_data.setOnClickListener(new ClickListener(position));
		return convertView;
	}
	class Holder {
		TextView invitation_name;
		TextView invitation_phone;
		TextView invitation_time;
		TextView invitation_status;
		TextView invitation_look_data;
	}
	
	class ClickListener implements OnClickListener{
		private int position;
        public ClickListener(int position){
        	this.position = position;
        }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context,RegisteredPersonInfoActivity.class);
			intent.putExtra("see_uid", list.get(position).getInvite_userid());
			context.startActivity(intent);
		}
		
	}
}
