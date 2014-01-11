package com.sanxian.sxzhuanhuan.function.sort;

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
import com.sanxian.sxzhuanhuan.entity.SortInfo;


/**   
 * @Title: SearchHistoryAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-7 下午3:42:36 
 * @version V1.0   
 */
public class SearchHistoryAdapter extends BaseAdapter{

	private Context context ;
	private ArrayList<SortInfo> sorts ;
	private LayoutInflater inflater ;
	
	public SearchHistoryAdapter(Context context, ArrayList<SortInfo> sorts) {
		this.context = context ;
		if (sorts != null)
			this.sorts = sorts;
		else
			this.sorts = new ArrayList<SortInfo>();
		inflater = LayoutInflater.from(context);
		// loader = new ImageAsyncLoader();
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sorts.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sorts.get(position);
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
			convertView = inflater.inflate(R.layout.search_history_item, null);
			holder = new ViewHolder();
			holder.ivHistoryLogo = (ImageView) convertView.findViewById(R.id.sort_search_history_item_list_image) ;
			holder.tvHistorySortName = (TextView) convertView.findViewById(R.id.sort_search_history_item_list_text) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		SortInfo sort = sorts.get(position) ;
//		holder.ivSortLogo.setImageBitmap(loadLogo(sort.getSortLogoUrl()));
		holder.ivHistoryLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher)) ;
		holder.tvHistorySortName.setText(sort.getSortName()) ;
		
		return convertView;
	}
	
	class ViewHolder {
		private ImageView ivHistoryLogo ;
		private TextView tvHistorySortName ;
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
