//package com.sanxian.sxzhuanhuan.function.homeindex.adapter;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.sanxian.sxzhuanhuan.R;
//import com.sanxian.sxzhuanhuan.function.homeindex.PublishOriginality;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//public class PublishOriginalityImageAdapter extends BaseAdapter {
//
//	// private Context context;
//	private ArrayList<String> list=new ArrayList<String>();
//	private PublishOriginality bindActivity;
//
//	public PublishOriginalityImageAdapter(ArrayList<String> list,
//			PublishOriginality bindActivity) {
//		this.list = list;
//		this.bindActivity = bindActivity;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		int count = 0;
//		if (list != null) {
//			if (list.size() < 5) {
//				count = list.size() + 1;
//			} else {
//				count = list.size();
//			}
//		}
//		return count;
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return list.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View view, ViewGroup group) {
//		Holder holder = null;
//		if (view == null) {
//			holder = new Holder();
//			view = LayoutInflater.from(bindActivity.getApplicationContext())
//					.inflate(R.layout.publish_images_item, null);
//			holder.add_image = (RelativeLayout) view
//					.findViewById(R.id.add_image);
//			holder.imageview = (ImageView) view.findViewById(R.id.images);
//			holder.delet = (TextView) view.findViewById(R.id.delet);
//			holder.note_text = (TextView) view.findViewById(R.id.note_text);
//			holder.mask = (TextView) view.findViewById(R.id.mask);
//			view.setTag(holder);
//		} else {
//			holder = (Holder) view.getTag();
//		}
//		if (list.size() == 0) {
//			holder.note_text.setVisibility(View.VISIBLE);
//			holder.delet.setVisibility(View.GONE);
//		} else if (list.size() != 0 && position == list.size()) {
//			holder.note_text.setVisibility(View.GONE);
//			holder.delet.setVisibility(View.GONE);
//		} else {
//			holder.note_text.setVisibility(View.GONE);
//			holder.delet.setVisibility(View.VISIBLE);
//		}
//		if (position == 0) {
//			holder.mask.setVisibility(View.VISIBLE);
//		} else {
//			holder.mask.setVisibility(View.GONE);
//		}
//		if (position != list.size() && list.size() != 0) {
//
//			holder.imageview.setImageBitmap(getDiskBitmap(list.get(position)));
//			
//		}
//
//		AddListener(position, holder.add_image, holder.delet);
//		return view;
//	}
//
//	private void AddListener(final int position, RelativeLayout add_image,
//			TextView deleTextView) {
//		deleTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				bindActivity.reMoveImage(position);
//			}
//		});
//		if (list.size() == 0 || (list.size() != 0 && position == list.size())) {
//			add_image.setOnClickListener(new RelativeLayout.OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					bindActivity.ShowAddImageDilog();
//				}
//			});
//		}
//	}
//	//读取本地图片
//	private Bitmap getDiskBitmap(String pathString)  
//	{  
//	    Bitmap bitmap = null;  
//	    try  
//	    {  
//	        File file = new File(pathString);  
//	        if(file.exists())  
//	        {  
//	            bitmap = BitmapFactory.decodeFile(pathString);  
//	        }  
//	    } catch (Exception e)  
//	    {  
//	    }  
//	      
//	      
//	    return bitmap;  
//	}  
//	class Holder {
//		RelativeLayout add_image;
//		ImageView imageview;
//		TextView delet;
//		TextView note_text;
//		TextView mask;
//	}
//
//}
