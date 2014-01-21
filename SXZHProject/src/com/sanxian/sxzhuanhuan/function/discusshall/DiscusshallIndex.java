package com.sanxian.sxzhuanhuan.function.discusshall;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscusshallIndexAdapter;
/**
 * 讨论大厅首页
 * @author Administrator
 *
 */
public class DiscusshallIndex extends BaseFragment implements OnClickListener{

	
	private Context context;
	private ListView discuss_listview;
	DiscusshallIndexAdapter adapter;
	List<DiscusshallInfo> list=new ArrayList<DiscusshallInfo>();
	public DiscusshallIndex() {
		super();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.discusshall_index, container,false);
		initView(view);
		getData();
		adapter=new DiscusshallIndexAdapter(context, list);
		discuss_listview.setAdapter(adapter);
		return view;
	}
		private void getData() {
			for (int i = 0; i < 20; i++) {
				DiscusshallInfo orderBean = new DiscusshallInfo();
				orderBean.setAvatar("http://g.hiphotos.baidu.com/image/w%3D2048/sign=56cec885013b5bb5bed727fe02ebd439/7dd98d1001e939015fc5441079ec54e737d196dd.jpg");
				list.add(orderBean);
			}

		}

	@Override
	public void initView(View view) {
		super.initView(view);
		discuss_listview=(ListView) view.findViewById(R.id.discuss_listview);
//		 setTitle("讨论大厅");
//	     displayLeft();
//	     displayRight();
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
	}

	@Override
	public Context getContext() {
		return super.getContext();
	}

	@Override
	public void onClick(View v) {
	}
	
	
}
