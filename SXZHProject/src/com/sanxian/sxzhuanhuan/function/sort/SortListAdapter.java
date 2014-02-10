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
import com.sanxian.sxzhuanhuan.entity.SortListItemEntity;


/**   
 * @Title: SortListAdapter.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort 
 * @Description: TODO
 * @author yuanqk@sanxian.com
 * @date 2014-1-3 下午3:42:36 
 * @version V1.0   
 */
public class SortListAdapter extends BaseAdapter{

	private Context context ;
	private ArrayList<SortListItemEntity> list ;
	ArrayList<View> viewsList = null;
	private LayoutInflater inflater ;
	
	public SortListAdapter(Context context, ArrayList<SortListItemEntity> list) {
		this.context = context ;
		if (list != null)
			this.list = list;
		else
			this.list = new ArrayList<SortListItemEntity>();
		inflater = LayoutInflater.from(context);
		
		notifyDataSetChanged();
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		int length = list.size();
		viewsList = new ArrayList<View>(length);
		for (int i = 0; i < length; ++i) {
			viewsList.add(null);
		}
	}
	

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		if (viewsList.get(position) != null){
//			return viewsList.get(position);
//		}
		
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.sort_list_item, null);
			holder = new ViewHolder();
			holder.sort_list_city = (TextView) convertView.findViewById(R.id.sort_list_city) ;
			holder.sort_list_num = (TextView) convertView.findViewById(R.id.sort_list_num) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		SortListItemEntity item = list.get(position) ;
		holder.sort_list_city.setText(item.getName()) ;
		holder.sort_list_num.setText(item.getNum()) ;
		holder.provinceId = item.getProvinceId();
		holder.cityId = item.getCityId();
		
		viewsList.set(position, convertView);
		return convertView;
	}
	
	public class ViewHolder {
		public TextView sort_list_city ;
		public TextView sort_list_num ;
		public String provinceId;
		public String cityId;
	}

}
