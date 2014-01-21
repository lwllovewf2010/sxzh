package com.sanxian.sxzhuanhuan.function.personal.mycollection;

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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sanxian.sxzhuanhuan.MainActivity;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;
import com.sanxian.sxzhuanhuan.dialog.BottomRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.entity.CollectionInfo;
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;
import com.sanxian.sxzhuanhuan.function.discusshall.DiscusshallIndex;
import com.sanxian.sxzhuanhuan.function.homeindex.HomeIndex;
import com.sanxian.sxzhuanhuan.function.personal.PersonalConstant;
import com.sanxian.sxzhuanhuan.function.personal.PersonalIndex;
import com.sanxian.sxzhuanhuan.function.sort.SortIndex;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
 * @ClassName: MyCollectionIndexActivity
 * @Description: 我的收藏首页
 * @author honaf
 * @date 2014-1-6 下午1:51:24
 */
public class MyCollectionIndexActivity extends BaseActivity implements IBaseActivity, OnClickListener {

	private ListView mycollection_listview;
	private Button edit_btn;
	private MyCollectionAdapter adapter;
	private List<CollectionInfo> collectionList = new ArrayList<CollectionInfo>();
	private List<CollectionInfo> productList = new ArrayList<CollectionInfo>();
	private List<CollectionInfo> projectList = new ArrayList<CollectionInfo>();
	boolean isEdit = false;
	private final int SHOWTOPRIGHTDIALOG = 1212;
	private Button return_btn;
	private Button menu_btn;

	public ImageLoader imageLoader;
	public DisplayImageOptions options;
	public String type=PersonalConstant.ALL;
	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().displayer(new RoundedBitmapDisplayer(9)).build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycollection_index);
		intImageUtil();
		this.initView();
		this.initListener();
		getData();
		adapter = new MyCollectionAdapter(this, collectionList,type);
		mycollection_listview.setAdapter(adapter);

	}

	private void getData() {
		for (int i = 0; i < 10; i++) {
			CollectionInfo orderBean = new CollectionInfo();
			orderBean.setAvatar("http://g.hiphotos.baidu.com/image/w%3D2048/sign=56cec885013b5bb5bed727fe02ebd439/7dd98d1001e939015fc5441079ec54e737d196dd.jpg");
			orderBean.setName("iphone 5s 外壳生产");
			orderBean.setType(PersonalConstant.PRODUCT);
			collectionList.add(orderBean);
		}
		for (int i = 0; i < 10; i++) {
			CollectionInfo orderBean = new CollectionInfo();
			orderBean.setAvatar("http://g.hiphotos.baidu.com/image/w%3D2048/sign=56cec885013b5bb5bed727fe02ebd439/7dd98d1001e939015fc5441079ec54e737d196dd.jpg");
			orderBean.setName("针对各种资金回笼投资");
			orderBean.setType(PersonalConstant.PROJECT);
			collectionList.add(orderBean);
		}

	}

	@Override
	public void initView() {
		super.initView();

		// super.button_right.setVisibility(View.GONE);
		edit_btn = (Button) this.findViewById(R.id.edit_btn);
		super.title_txt.setText("我的收藏");
		// super.button_right.setText("menu");
		super.button_right.setText("");
		super.button_right.setBackgroundResource(R.drawable.button_normal);
		mycollection_listview = (ListView) this.findViewById(R.id.mycollection_listview);
		return_btn = (Button) this.findViewById(R.id.return_btn);
		menu_btn = (Button) this.findViewById(R.id.menu_btn);
		super.button_left.setVisibility(View.INVISIBLE);
	}

	@Override
	public void initListener() {
		super.initListener();
		// super.button_left.setOnClickListener(this);
		mycollection_listview.setOnItemClickListener(new MyOrderListView());
		super.button_right.setOnClickListener(this);
		edit_btn.setOnClickListener(this);
		return_btn.setOnClickListener(this);
		menu_btn.setOnClickListener(this);
	}

	class MyOrderListView implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Util.toastInfo(MyCollectionIndexActivity.this, collectionList.get(arg2).getName());
		}

	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit_btn:
			if (isEdit) {
				isEdit = false;

			} else {
				isEdit = true;

			}

			adapter.notifyDataSetChanged();
			break;
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.return_btn:
			this.finish();
			break;
		case R.id.title_right:
			showTopRightDialog();
			break;
		case R.id.menu_btn:
			showBottomRightDialog();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @Description: 跳出右侧dialog
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void showTopRightDialog() {
		Intent intent = new Intent(MyCollectionIndexActivity.this, TopRightOrLeftDialog.class);
		TopDialogInfo info = new TopDialogInfo();
		info.setDirection(DialogConstant.TRIGHT);
		info.setMenu(new String[] { "查看全部", "只看产品", "只看项目" });
		intent.putExtra("info", info);
		startActivityForResult(intent, SHOWTOPRIGHTDIALOG);
	}

	public void showBottomRightDialog() {
		Intent intent = new Intent(MyCollectionIndexActivity.this, BottomRightOrLeftDialog.class);
		TopDialogInfo info = new TopDialogInfo();
		info.setDirection(DialogConstant.BRIGHT);
		// info.setMenu(new String[]{"首页","只看产品","只看项目"});
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_FOOT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Intent intent = new Intent();
		if (requestCode == SHOWTOPRIGHTDIALOG) {

			if (resultCode == DialogConstant.TOPDIALOG_ONE) {
				Util.toastInfo(this, "查看全部");
				type=PersonalConstant.ALL;
				
			} else if (resultCode == DialogConstant.TOPDIALOG_TWO) {
				Util.toastInfo(this, "查看产品");
				type=PersonalConstant.PRODUCT;
				
			} else if (resultCode == DialogConstant.TOPDIALOG_THREE) {
				Util.toastInfo(this, "查看项目");
				type=PersonalConstant.PROJECT;
				
			}
			adapter.notifyDataSetChanged();
		} else if (requestCode == DialogConstant.REQUEST_FOOT) {
			intent.setClass(MyCollectionIndexActivity.this, MainActivity.class);
			if (resultCode == DialogConstant.TOPDIALOG_ONE) {// 首页
				intent.putExtra("flag", 0);
			} else if (resultCode == DialogConstant.TOPDIALOG_TWO) {// 需求大厅
				intent.putExtra("flag", 4);
			} else if (resultCode == DialogConstant.TOPDIALOG_THREE) {// 分类搜索
				intent.putExtra("flag", 1);
			} else if (resultCode == DialogConstant.TOPDIALOG_FOUR) {// 讨论大厅
				intent.putExtra("flag", 2);
			} else if (resultCode == DialogConstant.TOPDIALOG_FIVE) {// 个人中心
				intent.putExtra("flag", 3);
			}
			startActivity(intent);
		}

	}

	class MyCollectionAdapter extends BaseAdapter {
		List<CollectionInfo> list =null;
		private LayoutInflater mInflater = null;
		private Context context;
		private String type=PersonalConstant.ALL;
		private void getList(List<CollectionInfo> tempList){
			list = new ArrayList<CollectionInfo>();
			if(type.equals(PersonalConstant.ALL)){
				list=tempList;
				
			}else if(type.equals(PersonalConstant.PRODUCT)){
				for (CollectionInfo collectionInfo : tempList) {
					if(PersonalConstant.PRODUCT.equals(collectionInfo.getType())){
						list.add(collectionInfo);
					}
				}
			}else if(type.equals(PersonalConstant.PROJECT)){
				for (CollectionInfo collectionInfo : tempList) {
					if(PersonalConstant.PROJECT.equals(collectionInfo.getType())){
						list.add(collectionInfo);
					}
				}
			}
		}
		public MyCollectionAdapter(Context context, List<CollectionInfo> list,String type) {
			super();
			this.context = context;
		
			this.type=type;
			getList(list);
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
			CollectionInfo orderBean = list.get(position);

			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.mycollection_index_lv_item, null);
				holder.lvitem_img = (ImageView) convertView.findViewById(R.id.lvitem_img);
				holder.tongyong_icon_shanchu = (ImageView) convertView.findViewById(R.id.tongyong_icon_shanchu);
				// holder.lvitem_name = (TextView)
				// convertView.findViewById(R.id.lvitem_name);
				// holder.lvitem_number = (TextView)
				// convertView.findViewById(R.id.lvitem_number);
				// holder.lvitem_total_price = (TextView)
				// convertView.findViewById(R.id.lvitem_total_price);
				//
				// holder.btn_comment = (Button)
				// convertView.findViewById(R.id.btn_comment);
				// holder.btn_payment = (Button)
				// convertView.findViewById(R.id.btn_payment);
				// holder.btn_getgoods = (Button)
				// convertView.findViewById(R.id.btn_getgoods);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (isEdit) {
				holder.tongyong_icon_shanchu.setVisibility(View.VISIBLE);
			} else {
				holder.tongyong_icon_shanchu.setVisibility(View.INVISIBLE);
			}
			// holder.lvitem_name.setText(orderBean.getGoods_name());
			// holder.lvitem_number.setText(orderBean.getNumber());
			// holder.lvitem_total_price.setText(orderBean.getTotal_price());
			imageLoader.displayImage(orderBean.getAvatar(), holder.lvitem_img, options);
			// holder.btn_comment.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// Util.toastInfo(context, "评论");
			// }
			// });
			// holder.btn_payment.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// Util.toastInfo(context, "付款");
			// }
			// });
			// holder.btn_getgoods.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// Util.toastInfo(context, "收货");
			// }
			// });

			return convertView;
		}

		public final class ViewHolder {
			private ImageView tongyong_icon_shanchu;
			public ImageView lvitem_img;
			// public TextView lvitem_total_price;
			// public TextView lvitem_name;
			// public TextView lvitem_number;
			// public Button btn_comment;
			// public Button btn_payment;
			// public Button btn_getgoods;
		}
	}

}
