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

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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

	private Activity context = null;
	private ArrayList<CommentInfo> infos = null;
	private LayoutInflater inflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null;
	private CommentInfo commentInfo = null;

	public OrgCommentAdapter(Activity context, ArrayList<CommentInfo> infos) {
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

//		convertView.setOnClickListener(this);
		return convertView;
	}

	private void init(View convertView, ViewHolder holder) {
		holder.iv_userImage = (ImageView) convertView
				.findViewById(R.id.iv_userImage);
		holder.tv_userName = (TextView) convertView
				.findViewById(R.id.tv_userName);
		holder.tv_addtime = (TextView) convertView.findViewById(R.id.tv_date);
		holder.tv_dnum = (TextView) convertView.findViewById(R.id.tv_dnum);
		holder.et_replay = (EditText) convertView.findViewById(R.id.et_replay);
		//评论
		holder.ll_comment = (LinearLayout) convertView.findViewById(R.id.ll_comment);
		holder.ll_comment.setOnClickListener(this);
		holder.ll_comment.setTag(holder);
		//赞
		holder.ll_zan = (LinearLayout) convertView.findViewById(R.id.ll_zan);
//		holder.ll_zan.setOnClickListener(this); //还是放到activity中去处理，要好点。
		holder.ll_zan.setTag(holder);
		
		holder.et_replay.setTag(new String[2]);
		holder.tv_content = (TextView) convertView
				.findViewById(R.id.tv_content);
		holder.tv_content.setTag(holder);
		holder.tv_content.setOnClickListener(this);
		holder.listView1 = (ListView) convertView
				.findViewById(R.id.listView1);
		holder.submit = (Button)convertView.findViewById(R.id.btn_replay_send);
		holder.submit.setTag(holder);
	}

	private void setData(ViewHolder holder, CommentInfo info) {
		imageLoader.displayImage(info.getUserImage(), holder.iv_userImage,
				options, null);

		holder.tv_userName.setText(info.getUserName());
		holder.tv_addtime.setText(info.getAddtime());
		holder.tv_content.setText(info.getContent());
		holder.tv_dnum.setText(info.getDnum()+"人觉得很赞");
		holder.id = info.getId();
		holder.username = info.getUserName();
		holder.listView1.setAdapter(new CommentItemAdapter(context, info.getComment_groups(),holder));
	}

	public class ViewHolder {
		public ImageView iv_userImage;
		public TextView tv_userName;
		public TextView tv_addtime;
		public TextView tv_content;
		public TextView tv_dnum;
		public TextView tv_fdnum;
		public EditText et_replay;
		public ListView listView1;
		public LinearLayout ll_comment;//评论
		public LinearLayout ll_zan;//赞
		public Button submit;
		public String id;//该评论的ID
		public String username;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_comment:
		case R.id.tv_content:
			ViewHolder holder = (ViewHolder)v.getTag();
			holder.et_replay.setHint("我也说一句...");
			Log.d("", "yuanle 111111111111");
			String[] arr = new String[2];
			arr[0] = null;
			arr[1] = null;
			//如果是回复主评论，则将holder.et_replay的tag设置为空，避免bug（因为在子评论中这个不为空）
			holder.et_replay.setTag(arr);
			holder.et_replay.requestFocus();
			break;
		default:
			break;
		}
	}

}
