package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.ArrayList;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.MetaData;
import com.sanxian.sxzhuanhuan.function.homeindex.originality.OriginalityActivity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CreativeAdapter extends BaseAdapter implements OnClickListener{
	


	private Context context = null ;
	private ArrayList<MetaData> creativeInfos = null ;
	private LayoutInflater inflater ;
	
	public CreativeAdapter(Context context, ArrayList<? extends MetaData> creativeInfos) {
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
			convertView = inflater.inflate(R.layout.creative_index_item, null);
			holder = new ViewHolder();
			init(convertView, holder) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		CreativeInfo creativeInfo = (CreativeInfo) creativeInfos.get(position) ;
		setData(holder , creativeInfo) ;
		
		convertView.setOnClickListener(this) ;
		return convertView;
	}
	
	private void init(View convertView , ViewHolder holder) {
		holder.tvCreativeName = (TextView) convertView.findViewById(R.id.creative_index_item_list_creative_name) ;
		holder.tvCreativeDesc = (TextView) convertView.findViewById(R.id.creative_index_item_list_creative_desc) ;
		holder.tvCreativeCollected = (TextView) convertView.findViewById(R.id.creative_index_item_list_collect_count) ;
		holder.tvCreativeAnswer = (TextView) convertView.findViewById(R.id.creative_index_item_list_answer_count) ;
		
	}
	
	private void setData(ViewHolder holder , CreativeInfo creativeInfo) {
		holder.tvCreativeName.setText(creativeInfo.getOrg_name()) ;
		holder.tvCreativeDesc.setText(creativeInfo.getOrg_explain()) ;
		holder.tvCreativeCollected.setText(creativeInfo.getComment_nums()) ;
		holder.tvCreativeAnswer.setText(creativeInfo.getFavorite_nums()) ;
		//added by yuanqk
		holder.creativeID = creativeInfo.getId();
	}

	class ViewHolder {
		public TextView tvCreativeName ;
		public TextView tvCreativeDesc;
		public TextView tvCreativeCollected;
		public TextView tvCreativeAnswer;
		public String creativeID;
	}
	


	@Override
	public void onClick(View v) {
		// added by yuanqk
		ViewHolder holder = (ViewHolder)v.getTag();
		Intent intent=new Intent(context, OriginalityActivity.class);
		intent.putExtra("creativeID", holder.creativeID);
		context.startActivity(intent);
	}

	
}
