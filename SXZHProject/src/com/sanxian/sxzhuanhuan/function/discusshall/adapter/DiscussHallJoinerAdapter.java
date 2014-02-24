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
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;
import com.sanxian.sxzhuanhuan.util.Util;

/**   
 * @Title: DiscussHallJoinerAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.discusshall.adapter 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-2-21 上午10:40:01 
 * @version V1.0   
 */
public class DiscussHallJoinerAdapter extends BaseAdapter implements OnClickListener {
	private Context context = null ;
	private List<DiscussData> infos;
	private DiscussData disInfo ;
	private LayoutInflater mInflater;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private int flag = 0 ;  //1,单选 2，多选

	public DiscussHallJoinerAdapter(Context context,
			List<DiscussData> infos , int flag) {
		this.context = context ;
		this.flag = flag ;
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<DiscussData>();
		
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
			convertView = mInflater.inflate(R.layout.discuss_join_item, null);
			holder = new ViewHolder();
			init(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		options = UIHelper.setOption() ;
		
		disInfo = (DiscussData) infos.get(position);
		setData(holder, disInfo);

		convertView.setOnClickListener(this);
		return convertView;
	}
	
	private void init(View convertView, ViewHolder holder) {
		holder.title = (TextView) convertView.findViewById(R.id.discuss_hall_join_title) ;
		holder.ivJoined = (ImageView) convertView.findViewById(R.id.discuss_hall_join_iv_joined) ;
		holder.pubname = (TextView) convertView.findViewById(R.id.discuss_hall_join_pubname) ;
		holder.pubtime = (TextView) convertView.findViewById(R.id.discuss_hall_join_pubtime) ;
		holder.ivGoing = (ImageView) convertView.findViewById(R.id.discuss_hall_join_iv_going) ;
		holder.surplusTime = (TextView) convertView.findViewById(R.id.discuss_hall_join_surplustime) ;
		holder.endTime = (TextView) convertView.findViewById(R.id.discuss_hall_join_endtime) ;
	}
	
	private void setData(ViewHolder holder, DiscussData disInfo) {
		holder.title.setText(disInfo.getTitle()) ;
		holder.pubname.setText(disInfo.getPubName()) ;
		holder.pubtime.setText("发起于   " + disInfo.getPubTime()) ;
//		imageLoader.displayImage(disInfo.getDisLogo() , holder.disLogo, options, null);
		
		if(1 == flag ) {
			holder.surplusTime.setText(disInfo.getSurplusTime()) ;
			holder.endTime.setVisibility(View.GONE) ;
		} else if (2== flag) {
			holder.surplusTime.setVisibility(View.GONE) ;
			holder.ivGoing.setVisibility(View.GONE) ;
			holder.endTime.setText("结束时间:" + disInfo.getEndTime() ) ;
		}
		
		
		holder.topicID = disInfo.getId() ;
	}

	class ViewHolder {
		public TextView title;
		public ImageView ivJoined ;
		public TextView pubname;
		public TextView pubtime;
		public ImageView ivGoing;
		public TextView surplusTime;
		public TextView endTime ;

		public String topicID ;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Util.toastInfo(context, "ssss") ;
		if(1 == flag ) {
			UIHelper.showDiscussVoteActivity(context, ((ViewHolder)v.getTag()).topicID , flag) ;
		} else {
			UIHelper.showDiscussVoteResultActivity(context, ((ViewHolder)v.getTag()).topicID , flag) ;
		}
	}
}
