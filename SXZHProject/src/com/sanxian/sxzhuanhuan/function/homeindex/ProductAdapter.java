package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.ArrayList;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.MetaData;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.function.homeindex.goods.GoodsContentActivity;

/**
* @Title: HomeIndexCommodityAdapter.java 
* @Package com.sanxian.sxzhuanhuan.function.homeindex 
* @Description: TODO
* @author zhangfl@sanxian.com
* @date 2014-1-17 下午2:30:21 
* @version V1.0
 */
public class ProductAdapter extends BaseAdapter implements OnClickListener{
	


	private Context context = null ;
	private ArrayList<MetaData> infos = null ;
	private LayoutInflater inflater ;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null ;
	
	private ProductInfo productInfo = null ;
	
	public ProductAdapter(Context context, ArrayList<? extends MetaData> infos) {
		this.context = context ;
		if (infos != null)
			this.infos = (ArrayList<MetaData>) infos;
		else
			this.infos = new ArrayList<MetaData>();
		inflater = LayoutInflater.from(context);
		// loader = new ImageAsyncLoader();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
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
			convertView = inflater.inflate(R.layout.product_index_item, null);
			holder = new ViewHolder();
			init(convertView, holder) ;
			convertView.setTag(holder) ;
		} else {
			holder = (ViewHolder) convertView.getTag() ;
		}
		
		productInfo = (ProductInfo) infos.get(position) ;
		Log.d("yuanqikai", "yuanqikai goodsID  1 = "+productInfo.getId());
		System.out.println("------" + productInfo.getId());
		holder.goodsID = productInfo.getId();
		setData(holder , productInfo) ;
		
		options = UIHelper.setOption() ;
		
		convertView.setOnClickListener(this) ;
		return convertView;
	}
	
	private void init(View convertView , ViewHolder holder) {
		holder.tvName = (TextView) convertView.findViewById(R.id.product_index_item_pro_name) ;
		holder.ivLogo = (ImageView) convertView.findViewById(R.id.product_index_item_pro_img) ;
		holder.tvPrice = (TextView) convertView.findViewById(R.id.product_index_item_pro_price) ;
		holder.tvSaleCount = (TextView) convertView.findViewById(R.id.product_index_item_pro_sales_count) ;
		
	}
	
	private void setData(ViewHolder holder , ProductInfo info) {
		holder.tvName.setText(info.getGoods_name()) ;
//		holder.ivLogo.setImageDrawable(context.getResources().getDrawable(
//				R.drawable.logo));
		
		imageLoader.displayImage(info.getGoods_image(),holder.ivLogo, options, null);
		holder.tvPrice.setText(info.getGoods_price()) ;
		holder.tvSaleCount.setText(info.getSales_num()) ;
	}

	class ViewHolder {
		public TextView tvName ;
		public ImageView ivLogo ;
		public TextView tvPrice ;
		public TextView tvSaleCount ;
		//added by yuanqk
		public String goodsID;
	}
	

	@Override
	public void onClick(View v) {
		// added by yuanqk
		ViewHolder hold =  (ViewHolder)v.getTag();
		Intent intent=new Intent(context, GoodsContentActivity.class);
		intent.putExtra("goodsID", hold.goodsID);
		
		context.startActivity(intent);
	}

	
}
