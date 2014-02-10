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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.myacc_address_index_item, null);
			holder = new Holder();
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.district = (TextView)convertView.findViewById(R.id.district);
            holder.address = (TextView)convertView.findViewById(R.id.address);
            holder.is_default = (ImageView)convertView.findViewById(R.id.is_default);
            convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		AddressInfo entity = list.get(position);
		String isdefault = entity.getIsDefault();
		if("1".equals(isdefault)){
			holder.is_default.setVisibility(View.VISIBLE);
		}else{
			holder.is_default.setVisibility(View.GONE);
		}
		holder.name.setText(entity.getName());
		holder.district.setText(entity.getProvince_name()+ " " + entity.getCity_name() + " " + entity.getArea_name() );
		holder.address.setText(entity.getAddress());
		return convertView;
	}


    class Holder {
        TextView name;
        TextView district;
        TextView address;
        ImageView is_default;

    }
}
