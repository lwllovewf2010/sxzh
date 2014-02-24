package com.sanxian.sxzhuanhuan.function.personal.myfriends.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.UserInfo;

public class SearchResultAdapter extends BaseAdapter {
	private Context context;
	private List<UserInfo> list;
	private LayoutInflater mInflater;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader ;

	public SearchResultAdapter(Context context, List<UserInfo> comcardsList) {
		this.context = context;
		this.list = comcardsList;
		this.mInflater = LayoutInflater.from(context);
		this.intImageUtil();
	}

	private  void intImageUtil(){
		 imageLoader = ImageLoader.getInstance();
		 options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.default_avatar)
//		.showImageForEmptyUri(R.drawable.default_ico)//uri为空的时候
//		.showImageOnFail(R.drawable.default_ico)//加载失败的时候
		.cacheOnDisc()
		.build();		
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder;
		UserInfo entity = list.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.contacts_addfriend_byname_item, null);
			holder = new Holder();
			holder.nickname = (TextView) convertView.findViewById(R.id.commomcards_lvitem_username);
			holder.position = (TextView) convertView.findViewById(R.id.commom_lvitem_position);
			holder.company = (TextView) convertView.findViewById(R.id.commom_lvitem_company);
			holder.lvitem_img = (ImageView) convertView.findViewById(R.id.commomcards_lvitem_img);
			holder.seperation_line = (ImageView)convertView.findViewById(R.id.seperation_line);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.nickname.setText(entity.getUsername());
		String posit = entity.getPosition();
		String company = entity.getCompany();
		if("".equals(posit) || "".equals(company)){
			holder.seperation_line.setVisibility(View.GONE);
		}else{
			holder.seperation_line.setVisibility(View.VISIBLE);
		}
		holder.position.setText(posit);
		holder.company.setText(company);
		imageLoader.displayImage(entity.getAvatar(), holder.lvitem_img, options);
		return convertView;
	}

	class Holder {
		ImageView lvitem_img;
		TextView nickname;
		TextView position;
		TextView company;
		ImageView seperation_line; //职位公司分割线
	}
}
