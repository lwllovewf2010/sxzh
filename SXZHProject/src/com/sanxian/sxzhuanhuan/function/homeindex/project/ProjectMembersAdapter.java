package com.sanxian.sxzhuanhuan.function.homeindex.project;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.UserInfo;

/**
* @Title: ProjectMembersAdapter.java 
* @Package com.sanxian.sxzhuanhuan.function.homeindex.project 
* @Description: TODO
* @author zhangfl@sanxian.com
* @date 2014-1-14 下午3:47:04 
* @version V1.0
 */
public class ProjectMembersAdapter extends BaseAdapter{

	private Context context ;
	private ArrayList<UserInfo> userinfos ;
	private LayoutInflater inflater ;
	
	public ProjectMembersAdapter(Context context, ArrayList<UserInfo> userinfos) {
		this.context = context ;
		if (userinfos != null)
			this.userinfos = userinfos;
		else
			this.userinfos = new ArrayList<UserInfo>();
		inflater = LayoutInflater.from(context);
		// loader = new ImageAsyncLoader();
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return userinfos.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return userinfos.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.project_member_item, null);
			holder = new ViewHolder();
			holder.userAvatar = (ImageView) convertView.findViewById(R.id.project_members_avatar) ;
			holder.username = (TextView) convertView.findViewById(R.id.project_members_name) ;
			holder.userjointime = (TextView) convertView.findViewById(R.id.project_members_join_time) ;
			holder.userjoin_pro_count = (TextView) convertView.findViewById(R.id.project_members_join_project) ;
			holder.usershare_pro_count = (TextView) convertView.findViewById(R.id.project_members_shares_project) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		UserInfo userinfo = userinfos.get(position) ;
		holder.userAvatar.setImageDrawable(context.getResources().getDrawable(R.drawable.default_avatar)) ;
		holder.username.setText(userinfo.getUsername()) ;
		holder.userjointime.setText(userinfo.getJointime() + " 加入项目") ;
		holder.userjoin_pro_count.setText("TA总共加入了" + userinfo.getJoinProCount() + "个项目") ;
		holder.usershare_pro_count.setText("TA总共入股了" + userinfo.getShareProCount() + "个项目") ;
		
		return convertView;
	}
	
	class ViewHolder {
		public ImageView userAvatar ;
		public TextView username ;
		public TextView userjointime ;
		public TextView userjoin_pro_count ;
		public TextView usershare_pro_count ;
	}
	
	/**
	 * 获取图库图片
	 * @param url  图片地址
	 * @return Bitmap
	 */
	private Bitmap loadLogo(String url) {
		Bitmap logoBitmap = null;
		// TODO 
		return logoBitmap ;
	}

}
