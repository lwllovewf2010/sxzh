package com.sanxian.sxzhuanhuan.function.homeindex.originality;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.CustomProgress;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.CommentInfo;
import com.sanxian.sxzhuanhuan.entity.MetaData;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @Title: CommentItemAdapter.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex.originality
 * @Description:评论页
 * @author yuanqk@sanxian.com
 * @date 2014-1-14 上午10:42:45
 * @version V1.0
 */
public class CommentItemAdapter extends BaseAdapter implements OnClickListener {

	private Context context = null;
	private ArrayList<CommentInfo> infos = null;
	private LayoutInflater inflater;
	private CommentInfo commentInfo = null;
	public static String[] itemInfo = new String[2];//Item点击后保存Item数据
	//holder 是从主评论传进来的
	private OrgCommentAdapter.ViewHolder holder;

	public CommentItemAdapter(Context context, ArrayList<CommentInfo> infos,OrgCommentAdapter.ViewHolder holder) {
		this.context = context;
		this.holder = holder;
		if (infos != null)
			this.infos = (ArrayList<CommentInfo>) infos;
		else
			this.infos = new ArrayList<CommentInfo>();
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return infos.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		 return infos.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//特别注意：position的值总是在变，这是个严重BUG
		ViewHolder holder = null;
		Log.d("", "yuanqikai123 getView getView "+position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.org_comment_item_child, null);
			holder = new ViewHolder();
			init(convertView, holder);
			convertView.setTag(holder);
			if (infos != null) {
				commentInfo = (CommentInfo) infos.get(position);
				setData(holder, commentInfo);
			}
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		convertView.setOnClickListener(this);
		return convertView;
	}

	private void init(View convertView, ViewHolder holder) {
		holder.tv_userName = (TextView) convertView
				.findViewById(R.id.tv_userName);
		holder.tv_replay_character = (TextView) convertView.findViewById(R.id.tv_replay_character);
		holder.tv_r_user_name = (TextView) convertView.findViewById(R.id.tv_userName2);
		holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);

	}
	//设置数据
	private void setData(ViewHolder holder, CommentInfo info) {
		holder.tv_userName.setText(info.getUserName());
		if(!"0".equals(info.getReplyid())&&!(info.getSid().equals(info.getReplyid()))){
			holder.tv_replay_character.setVisibility(View.VISIBLE);
			holder.tv_r_user_name.setText(info.getR_user_name());
			holder.tv_r_user_name.setVisibility(View.VISIBLE);
		}
		holder.tv_content.setText(info.getContent());
		holder.id = info.getId();
		holder.userName = info.getUserName();
	}

	public class ViewHolder {
		public TextView tv_userName;
		public TextView tv_replay_character;
		public TextView tv_r_user_name;
		public TextView tv_content;
		public String id;
		public String userName;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// UIHelper.showProjectDetailActivity(context
		
		 CommentItemAdapter.ViewHolder holder2 = (CommentItemAdapter.ViewHolder) v
					.getTag();
		 itemInfo[0] = holder2.id;
		 itemInfo[1] = holder2.userName;
		 holder.et_replay.setHint("回复"+itemInfo[1]);
		 holder.et_replay.setTag(itemInfo);
		 holder.et_replay.requestFocus();
	}

}
