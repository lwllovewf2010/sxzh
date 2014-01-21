package com.sanxian.sxzhuanhuan.dialog;

import java.util.Arrays;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;
import com.sanxian.sxzhuanhuan.util.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TopRightOrLeftDialog extends Activity implements OnClickListener {
	private LinearLayout layout;
	TopDialogInfo info = null;
	private ListView listView;
	private List<String> list;
	private DialogAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		info = (TopDialogInfo) this.getIntent().getSerializableExtra("info");
		if (info == null) {
			setResult(DialogConstant.DIALOG_RETURN);
			this.finish();
			return;
		}
		if (DialogConstant.TLEFT == info.getDirection()) {
			setContentView(R.layout.mycollection_top_left_dialog);
		} else if (DialogConstant.TRIGHT == info.getDirection()) {
			setContentView(R.layout.mycollection_top_right_dialog);
		}
		// else if(DialogConstant.BLEFT==info.getDirection()){
		// setContentView(R.layout.mycollection_bottom_left_dialog);
		// }else if(DialogConstant.BRIGHT==info.getDirection()){
		// setContentView(R.layout.mycollection_bottom_right_dialog);
		// }
		layout = (LinearLayout) findViewById(R.id.main_dialog_layout);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		initView();
		initListener();
		getData();
		adapter = new DialogAdapter(this, list);
		listView.setAdapter(adapter);

	}

	private void getData() {
		if (info.getMenu() == null || info.getMenu().length == 0) {
			return;
		}
		list = Arrays.asList(info.getMenu());
	}

	private void initListener() {
		listView.setOnItemClickListener(new MyOnItemClick());
	}

	public void initView() {
		listView = (ListView) this.findViewById(R.id.listView);
	}

	class MyOnItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			setResult(position);
			finish();
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setResult(DialogConstant.DIALOG_RETURN);
		finish();
		return true;
	}

	public void all(View v) {

	}

	class DialogAdapter extends BaseAdapter {
		private Context context;
		private List<String> list;
		private LayoutInflater mInflater;

		public DialogAdapter(Context context, List<String> comcardsList) {
			this.context = context;
			this.list = comcardsList;
			this.mInflater = LayoutInflater.from(context);
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
			String entity = list.get(position);
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.top_dialog_lvitem, null);
				holder = new Holder();

				holder.contentTV = (TextView) convertView.findViewById(R.id.content);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.contentTV.setText(entity);
			return convertView;
		}

		class Holder {
			TextView contentTV;
		}
	}

	@Override
	public void onClick(View v) {

	}
}
