package com.sanxian.sxzhuanhuan.function.sort;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;


/**   
 * @Title: SortAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-3 下午3:42:36 
 * @version V1.0   
 */
public class SortAdapter extends BaseAdapter{

	private Context context ;
	private ArrayList<ICategory> sorts ;
	private LayoutInflater inflater ;
	
	public SortAdapter(Context context, ArrayList<ICategory> sorts) {
		this.context = context ;
		if (sorts != null)
			this.sorts = sorts;
		else
			this.sorts = new ArrayList<ICategory>();
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
			convertView = inflater.inflate(R.layout.sort_item, null);
			holder = new ViewHolder();
			holder.ivSortLogo = (ImageView) convertView.findViewById(R.id.sort_iv_logo) ;
			holder.tvSortName = (TextView) convertView.findViewById(R.id.sort_tv_name) ;
			holder.tvSortChild = (TextView) convertView.findViewById(R.id.sort_tv_sortchild) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		ICategory sort = sorts.get(position) ;
		holder.ivSortLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.logo)) ;
		holder.tvSortName.setText(sort.getTitle()) ;
//		String sortChild = "" ;
//		for (String s : sort.getSortChild()) {
//			sortChild += s + "/" ;
//		}
//		holder.tvSortChild.setText(sortChild.substring(0, sortChild.length() - 1 )) ;
		
		return convertView;
	}
	
	class ViewHolder {
		public ImageView ivSortLogo ;
		public TextView tvSortName ;
		public TextView tvSortChild ;
	}

}
