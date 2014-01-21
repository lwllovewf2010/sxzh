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
 * @Title: SearchResultAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午5:42:36 
 * @version V1.0   
 */
public class SearchResultAdapter extends BaseAdapter{

	private Context context ;
	private ArrayList<SortInfo> sorts ;
	private LayoutInflater inflater ;
	
	public SearchResultAdapter(Context context, ArrayList<SortInfo> sorts) {
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
			convertView = inflater.inflate(R.layout.search_result_item, null);
			holder = new ViewHolder();
			holder.ivSortLogo = (ImageView) convertView.findViewById(R.id.sort_search_result_item_list_image_sort) ;
			holder.tvSortName = (TextView) convertView.findViewById(R.id.sort_search_result_item_list_text_search) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		SortInfo sort = sorts.get(position) ;
//		holder.ivSortLogo.setImageBitmap(loadLogo(sort.getSortLogoUrl()));
		holder.ivSortLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher)) ;
		holder.tvSortName.setText(sort.getSortName()) ;
		
		return convertView;
	}
	
	class ViewHolder {
		public ImageView ivSortLogo ;
		public TextView tvSortName ;
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
