package com.sanxian.sxzhuanhuan.function.homeindex.adapter;

import java.util.List;
import java.util.Map;

import com.sanxian.sxzhuanhuan.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 获取县区等数据适配器
 * @author joe
 *
 */
public class GetSubSubCommonAdapter extends BaseAdapter {
	
	private LayoutInflater minflater;
	private Context context;
	private List<Map<String, String>> lt;
    public GetSubSubCommonAdapter(Context context,List<Map<String, String>> list){
    	this.context = context;
    	this.minflater = LayoutInflater.from(context);
    	this.lt = list;
    	
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lt.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder view;
		if(convertView == null){
			view = new ViewHolder();
			convertView = minflater.inflate(R.layout.getcommondata_item, null);
			view.name_table = (TextView)convertView.findViewById(R.id.name_table);
			view.arrow_img = (ImageView)convertView.findViewById(R.id.arrow_img);
			convertView.setTag(view);
		}else{
			view = (ViewHolder)convertView.getTag();
		}
		view.name_table.setText(lt.get(position).get("type_name"));
		view.arrow_img.setVisibility(View.INVISIBLE);
		Log.e("joe", "lt.size() " + lt.size());
		return convertView;
	}
	
	final class ViewHolder{
		TextView  name_table;
//		RelativeLayout content_item_layout;
		ImageView arrow_img;
	}
}
