package com.sanxian.sxzhuanhuan.function.discusshall.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.DiscussVoteResultInfo;
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: DiscussVoteResultAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.discusshall.adapter 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-2-21 上午10:40:01 
 * @version V1.0   
 */
public class DiscussVoteResultAdapter extends BaseAdapter implements OnClickListener {
	private Context context = null ;
	private List<DiscussVoteResultInfo> infos;
	private DiscussVoteResultInfo info ;
	private LayoutInflater mInflater;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();;

	public DiscussVoteResultAdapter(Context context,
			List<DiscussVoteResultInfo> infos) {
		this.context = context ;
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<DiscussVoteResultInfo>();
		
		System.out.println("--" + infos.size());
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.vote_result_item, null);
			holder = new ViewHolder();
			init(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		options = UIHelper.setOption() ;
		
		info = (DiscussVoteResultInfo) infos.get(position);
		setData(holder, info , position);

		convertView.setOnClickListener(this);
		return convertView;
	}
	
	private void init(View convertView, ViewHolder holder) {
		holder.votePubname = (TextView) convertView.findViewById(R.id.vote_pubname) ;
		holder.voteResult = (TextView) convertView.findViewById(R.id.vote_tv_prograss) ;
	}
	
	private void setData(ViewHolder holder, DiscussVoteResultInfo info , int position) {
		holder.votePubname.setText("" + (position + 1) + "." + info.getVotePubname()) ;
		holder.voteResult.setText(info.getVoteResult()) ;
		
		holder.topicID = info.getId() ;
	}

	class ViewHolder {
		public TextView votePubname;
		public TextView voteResult;

		public String topicID ;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Util.toastInfo(context, "fuck you") ;
//		UIHelper.showTopicDetailActivity(context, ((ViewHolder)v.getTag()).topicID) ;
	}
}
