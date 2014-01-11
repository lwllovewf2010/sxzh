package com.sanxian.sxzhuanhuan.function.personal.myaccount.adapter;

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
import com.sanxian.sxzhuanhuan.entity.AddressInfo;
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;

public class MyAccoAddressIndexAdapter extends BaseAdapter {
	private Context context;
	private List<AddressInfo> list;
	private LayoutInflater mInflater;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader ;

	public MyAccoAddressIndexAdapter(Context context, List<AddressInfo> comcardsList) {
		this.context = context;
		this.list = comcardsList;
		this.mInflater = LayoutInflater.from(context);
		this.intImageUtil();
	}

	private  void intImageUtil(){
		 imageLoader = ImageLoader.getInstance();
		 options = new DisplayImageOptions.Builder()
//		.showStubImage(R.drawable.default_ico)
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
		AddressInfo entity = list.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.myacc_address_index_item, null);
			   holder = new Holder();
//               holder.titleTV = (TextView)convertView.findViewById(R.id.message_index_title);
               convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		return convertView;
	}


    class Holder {
        ImageView avatarIV;

        TextView titleTV;

        TextView contentTV;

        TextView datelineTV;


    }
}
