package com.sanxian.sxzhuanhuan.function.homeindex.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.ProjectBean;
import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsContentActivity;
import com.sanxian.sxzhuanhuan.function.homeindex.originality.OriginalityActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeIndexOriginalityAdapter extends BaseAdapter implements OnClickListener{
	
	
	private List<ProjectBean> list;
	private Context context;	
	public ImageLoader imageLoader;
	public DisplayImageOptions options;
	
	
	
	
	@SuppressLint("NewApi")
	public HomeIndexOriginalityAdapter(Context context,List<ProjectBean> list){
		this.context=context;
		this.list=list;
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
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
		final ProjectBean projectBean=list.get(position);
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(context).inflate(R.layout.home_index_list, null);
		} else {
			view = convertView;
		}
		final ImageView photo = (ImageView) view.findViewById(R.id.home_index_list_image);
		
		final TextView projectTitle=(TextView)view.findViewById(R.id.home_index_list_top_content);
		final TextView projectContent=(TextView)view.findViewById(R.id.home_index_list_right_text);
		projectTitle.setText(projectBean.getProjecTitle());
		projectContent.setText(projectBean.getContent());
		imageLoader.displayImage(projectBean.getImageURL(), photo, options);
		view.setOnClickListener(this);
		return view;
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(context, OriginalityActivity.class);
		context.startActivity(intent);
	}

	
}
