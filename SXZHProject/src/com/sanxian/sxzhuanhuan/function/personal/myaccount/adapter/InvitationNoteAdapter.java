package com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.InvitationUser;
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
		holder.invitation_name.setText(list.get(position).getInvite_name());
		holder.invitation_phone.setText(list.get(position).getInvite_phone());
		holder.invitation_time.setText(list.get(position).getInvite_time());
		holder.invitation_status.setText(list.get(position).getInvite_status());
		return convertView;
	}
	class Holder {
		TextView invitation_name;
		TextView invitation_phone;
		TextView invitation_time;
		TextView invitation_status;
		TextView invitation_look_data;
	}
}
