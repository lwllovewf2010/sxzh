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
		holder.ivProgress = (ProgressBar) convertView.findViewById(R.id.sort_detail_item_progressBar) ;
		
		holder.ivParticipate = (ImageView) convertView.findViewById(R.id.sort_detail_item_ivParticipate) ;
		holder.tvParticipate = (TextView) convertView.findViewById(R.id.sort_detail_item_tvParticipate) ;
		holder.ivDiscuss = (ImageView) convertView.findViewById(R.id.sort_detail_item_ivDiscuss) ;
		holder.tvDiscuss = (TextView) convertView.findViewById(R.id.sort_detail_item_tvDiscuss) ;
		holder.ivDays = (ImageView) convertView.findViewById(R.id.sort_detail_item_ivDays) ;
		holder.tvDays = (TextView) convertView.findViewById(R.id.sort_detail_item_tvDays) ;
		holder.ivMoney = (ImageView) convertView.findViewById(R.id.sort_detail_item_ivMoney) ;
		holder.tvMoney = (TextView) convertView.findViewById(R.id.sort_detail_item_tvMoney) ;
		
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
		public TextView tvName ;
		public ImageView ivLogo ;
		public TextView tvProfile ;
		public ProgressBar ivProgress ;
		
		public ImageView ivParticipate ;
		public TextView tvParticipate ;
		public ImageView ivDiscuss ;
		public TextView tvDiscuss ;
		public ImageView ivDays ; 
		public TextView tvDays ;
		public ImageView ivMoney ;
		public TextView tvMoney ;
		
	}
	
}
