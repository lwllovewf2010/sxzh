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

/**
 * @Title: DiscusshallIndexAdapter.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscusshallIndexAdapter
 * @Description: 讨论大厅首页数据
 * @author zhangfl@sanxian.com
 * @date 2014-2-20 下午3:27:42
 * @version V1.0
 */
public class DiscusshallIndexAdapter extends BaseAdapter implements OnClickListener {
	private Context context = null ;
	private List<DiscusshallInfo> infos;
	private DiscusshallInfo disInfo ;
	private LayoutInflater mInflater;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();;

	public DiscusshallIndexAdapter(Context context,
			List<DiscusshallInfo> infos) {
		this.context = context ;
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<DiscusshallInfo>();
		
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
			convertView = mInflater.inflate(R.layout.discusshall_index_item, null);
			holder = new ViewHolder();
			init(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		options = UIHelper.setOption() ;
		
		disInfo = (DiscusshallInfo) infos.get(position);
		setData(holder, disInfo);

		convertView.setOnClickListener(this);
		return convertView;
	}
	
	private void init(View convertView, ViewHolder holder) {
		holder.disTitle = (TextView) convertView.findViewById(R.id.dis_index_tv_distitle) ;
		holder.lastTime = (TextView) convertView.findViewById(R.id.dis_index_tv_lasttime) ;
		holder.disCount = (TextView) convertView.findViewById(R.id.dis_index_tv_discount) ;
		holder.disLogo = (ImageView) convertView.findViewById(R.id.dis_index_iv_dislogo) ;
		holder.lastDis = (TextView) convertView.findViewById(R.id.dis_index_tv_lastdis) ;
	}
	
	private void setData(ViewHolder holder, DiscusshallInfo disInfo) {
		holder.disTitle.setText(disInfo.getDisTitle()) ;
		holder.lastTime.setText(disInfo.getLastTime()) ;
		holder.disCount.setText(disInfo.getDisCount()) ;
		imageLoader.displayImage(disInfo.getDisLogo() , holder.disLogo, options, null);
		holder.lastDis.setText(disInfo.getLastDis()) ;
		
		holder.topicID = disInfo.getId() ;
	}

	class ViewHolder {
		public TextView disTitle;
		public TextView lastTime;
		public TextView disCount;
		public ImageView disLogo;
		public TextView lastDis;

		public String topicID ;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		UIHelper.showTopicDetailActivity(context, ((ViewHolder)v.getTag()).topicID) ;
	}
}
