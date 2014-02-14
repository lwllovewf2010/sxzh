package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.CustomProgress;
import com.sanxian.sxzhuanhuan.entity.MetaData;

/**   
 * @Title: SortDetailAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午6:20:01 
 * @version V1.0   
 */
public class SortDetailAdapter extends BaseAdapter {

	private Context context = null ;
	private ArrayList<MetaData> creativeInfos = null ;
	private LayoutInflater inflater ;
	
	public SortDetailAdapter(Context context, ArrayList<? extends MetaData> creativeInfos) {
		this.context = context ;
		if (creativeInfos != null)
			this.creativeInfos = (ArrayList<MetaData>) creativeInfos;
		else
			this.creativeInfos = new ArrayList<MetaData>();
		inflater = LayoutInflater.from(context);
		// loader = new ImageAsyncLoader();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return creativeInfos.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return creativeInfos.get(position);
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
			convertView = inflater.inflate(R.layout.sort_detail_item, null);
			holder = new ViewHolder();
			init(convertView, holder) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		MetaData creativeInfo = creativeInfos.get(position) ;
		setData(holder , creativeInfo) ;
		
		return convertView;
	}
	
	private void init(View convertView , ViewHolder holder) {
		holder.tvName = (TextView) convertView.findViewById(R.id.sort_detail_item_list_top_content) ;
		holder.ivLogo = (ImageView) convertView.findViewById(R.id.sort_detail_item_list_image) ;
		holder.tvProfile = (TextView) convertView.findViewById(R.id.sort_detail_item_list_right_text) ;
//		holder.ivProgress = (ProgressBar) convertView.findViewById(R.id.sort_detail_item_progressBar) ;
		
		holder.ivProgress = (CustomProgress) convertView
				.findViewById(R.id.sort_detail_item_progressBar);

		holder.tvCurMoney = (TextView) convertView
				.findViewById(R.id.sort_detail_item_cur_money);
		holder.tvJoinPerson = (TextView) convertView
				.findViewById(R.id.sort_detail_item_person_join);
		holder.tvAllMoney = (TextView) convertView
				.findViewById(R.id.sort_detail_item_all_money);
		holder.tvShenTime = (TextView) convertView
				.findViewById(R.id.sort_detail_item_shen_time);
		
	}
	
	private void setData(ViewHolder holder , MetaData creativeInfo) {
//		holder.tvName.setText(creativeInfo.getMetaName()) ;
//		holder.ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.logo)) ;
//		holder.tvProfile.setText(creativeInfo.getMetaDetail()) ;
////		holder.ivProgress.set
//		
//		holder.ivParticipate.setImageDrawable(context.getResources().getDrawable(R.drawable.groupico)) ;
//		holder.tvParticipate.setText("" + creativeInfo.getParticipate() + "人") ;
//		holder.ivDiscuss.setImageDrawable(context.getResources().getDrawable(R.drawable.groupico)) ;
//		holder.tvDiscuss.setText("" + creativeInfo.getDiscuss() + "讨论") ;
//		holder.ivDays.setImageDrawable(context.getResources().getDrawable(R.drawable.groupico) ) ;
//		holder.tvDays.setText("" + creativeInfo.getDays() + "天") ;
//		holder.ivMoney.setImageDrawable(context.getResources().getDrawable(R.drawable.groupico)) ;
//		holder.tvMoney.setText("￥" + creativeInfo.getMoney()) ;
	}

	class ViewHolder {
		public TextView tvName;
		public ImageView ivLogo;
		public TextView tvProfile;
//		public ProgressBar ivProgress;
		public CustomProgress ivProgress ;

		public TextView tvCurMoney;
		public TextView tvJoinPerson;
		public TextView tvAllMoney;
		public TextView tvShenTime;
		
		public String project_id ; 
	}

	
}
