package com.sanxian.sxzhuanhuan.function.personal.myorder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.FootDialog;
import com.sanxian.sxzhuanhuan.dialog.FootDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.entity.OrderInfo;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
 * @ClassName: MyOrderIndexActivity
 * @Description: 我的订单首页
 * @author honaf
 * @date 2014-1-6 下午1:51:24
 */
public class MyOrderIndexActivity extends BaseActivity implements IBaseActivity, OnClickListener {

	private ListView myorder_listview;

	private MyOrderListviewAdapter adapter;
	private List<OrderInfo> orderList = new ArrayList<OrderInfo>();

	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myorder_index);
//		initPopuWindow(R.layout.bottom_commom_popupwindow,arrayStrings);
		intImageUtil();
		this.initView();
		this.initListener();
		getData();
		adapter = new MyOrderListviewAdapter(this, orderList);
		myorder_listview.setAdapter(adapter);

	}

	private void getData() {
		for (int i = 0; i < 20; i++) {
			OrderInfo orderBean = new OrderInfo();
			orderBean.setAvatar("http://g.hiphotos.baidu.com/image/w%3D2048/sign=56cec885013b5bb5bed727fe02ebd439/7dd98d1001e939015fc5441079ec54e737d196dd.jpg");
			orderBean.setGoods_name("mac air"+i);
			orderList.add(orderBean);
		}

	}

	@Override
	public void initView() {
		super.initView();

//		super.button_right.setVisibility(View.GONE);
		
		super.title_txt.setText("我的订单");
		myorder_listview = (ListView) this.findViewById(R.id.myorder_listview);
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);
		myorder_listview.setOnItemClickListener(new MyOrderListView());
		super.button_right.setOnClickListener(this);

	}

	class MyOrderListView implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Util.toastInfo(MyOrderIndexActivity.this, orderList.get(arg2).getGoods_name());
		}

	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
//		if(v==super.menu[0]){
//			Util.toastInfo(this, "看1");
//			mPopupWindow.dismiss();
//		}else if(v==super.menu[1]){
//			Util.toastInfo(this, "看2");
//			mPopupWindow.dismiss();
//		}else if(v==super.menu[2]){
//			Util.toastInfo(this, "看3");
//			mPopupWindow.dismiss();
//		}
		switch (v.getId()) {

		case R.id.title_Left:
			this.finish();
			break;
		case R.id.title_right:
//			showDialog();
			showDialog2();
			break;
		default:
			break;
		}
	}
	public void showDialog(){
		Intent intent=new Intent(this,FootDialog.class);
		FootDialogInfo info=new FootDialogInfo();
		String []arrayStrings={"看美女","还是看美女","算了吧"};
		info.setMenu(arrayStrings);
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_FOOT);
	}
	public void showDialog2(){
		Intent intent=new Intent(this,MiddleDialog.class);
		MiddleDialogInfo info=new MiddleDialogInfo("提示", "对面的女孩看过来，看过来......", false, "", "", "看过来", "不看我走了");
		
//		(String title, String content, boolean isEdit, String edit_hint, String edit_content, String ok, String cancel)
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_MIDDLE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==DialogConstant.REQUEST_FOOT){
			if(resultCode==DialogConstant.FOOTDIALOG_ONE){
				Util.toastInfo(this, "1");
			}else if(resultCode==DialogConstant.FOOTDIALOG_TWO){
				Util.toastInfo(this, "2");
			}else if(resultCode==DialogConstant.FOOTDIALOG_THREE){
				Util.toastInfo(this, "3");
			}
		}else if(requestCode==DialogConstant.REQUEST_MIDDLE){
			if(resultCode==DialogConstant.MIDDLE_OK){
				Util.toastInfo(this, "1");
			}else if(resultCode==DialogConstant.MIDDLE_CANCEL){
				Util.toastInfo(this, "2");
			}
		}
	}
	class MyOrderListviewAdapter extends BaseAdapter {
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		private LayoutInflater mInflater = null;
		private Context context;

		public MyOrderListviewAdapter(Context context, List<OrderInfo> list) {
			super();
			this.context = context;
			this.list = list;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);

		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			OrderInfo orderBean = list.get(position);

			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.myorder_index_lv_item, null);
				holder.lvitem_img = (ImageView) convertView.findViewById(R.id.lvitem_img);
				holder.lvitem_name = (TextView) convertView.findViewById(R.id.lvitem_name);
				holder.lvitem_number = (TextView) convertView.findViewById(R.id.lvitem_number);
				holder.lvitem_total_price = (TextView) convertView.findViewById(R.id.lvitem_total_price);

				holder.btn_comment = (Button) convertView.findViewById(R.id.btn_comment);
				holder.btn_payment = (Button) convertView.findViewById(R.id.btn_payment);
				holder.btn_getgoods = (Button) convertView.findViewById(R.id.btn_getgoods);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.lvitem_name.setText(orderBean.getGoods_name());
			holder.lvitem_number.setText(orderBean.getNumber());
			holder.lvitem_total_price.setText(orderBean.getTotal_price());
			imageLoader.displayImage(orderBean.getAvatar(), holder.lvitem_img, options);
			holder.btn_comment.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Util.toastInfo(context, "评论");
				}
			});
			holder.btn_payment.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Util.toastInfo(context, "付款");
				}
			});
			holder.btn_getgoods.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Util.toastInfo(context, "收货");
				}
			});

			return convertView;
		}

		public final class ViewHolder {

			public ImageView lvitem_img;
			public TextView lvitem_total_price;
			public TextView lvitem_name;
			public TextView lvitem_number;
			public Button btn_comment;
			public Button btn_payment;
			public Button btn_getgoods;
		}
	}

}
