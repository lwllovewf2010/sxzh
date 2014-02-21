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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @Title: ProjectAdapter.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex
 * @Description: 项目内容首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-14 上午10:42:45
 * @version V1.0
 */
public class OrgCommentAdapter extends BaseAdapter implements OnClickListener {

	private Context context = null;
	private ArrayList<CommentInfo> infos = null;
	private LayoutInflater inflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null;
	private CommentInfo commentInfo = null;

	public OrgCommentAdapter(Context context, ArrayList<CommentInfo> infos) {
		this.context = context;
		if (infos != null)
			this.infos = (ArrayList<CommentInfo>) infos;
		else
			this.infos = new ArrayList<CommentInfo>();
		inflater = LayoutInflater.from(context);
		options = UIHelper.setOption();
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
		return 0;
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.org_comment_item, null);
			holder = new ViewHolder();
			init(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (infos != null) {
			commentInfo = (CommentInfo) infos.get(position);
			setData(holder, commentInfo);
		}

		convertView.setOnClickListener(this);
		return convertView;
	}

	private void init(View convertView, ViewHolder holder) {
		holder.iv_userImage = (ImageView) convertView
				.findViewById(R.id.iv_userImage);
		holder.tv_userName = (TextView) convertView
				.findViewById(R.id.tv_userName);
		holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
		holder.tv_content = (TextView) convertView
				.findViewById(R.id.tv_content);

	}

	private void setData(ViewHolder holder, CommentInfo info) {
		imageLoader.displayImage(info.getUserImage(), holder.iv_userImage,
				options, null);

		holder.tv_userName.setText(info.getUserName());
		holder.tv_date.setText(info.getDate());
		holder.tv_content.setText(info.getContent());
	}

	class ViewHolder {
		public ImageView iv_userImage;
		public TextView tv_userName;
		public TextView tv_date;
		public TextView tv_content;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// UIHelper.showProjectDetailActivity(context
		// ,((ViewHolder)v.getTag()).project_id);
	}

}
