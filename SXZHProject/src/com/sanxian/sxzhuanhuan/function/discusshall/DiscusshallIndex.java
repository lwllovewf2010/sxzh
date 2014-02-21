package com.sanxian.sxzhuanhuan.function.discusshall;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.entity.DiscusshallInfo;
import com.sanxian.sxzhuanhuan.function.discusshall.adapter.DiscusshallIndexAdapter;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: DiscusshallIndex.java
 * @Package com.sanxian.sxzhuanhuan.function.discusshall.DiscusshallIndex
 * @Description: 讨论大厅首页
 * @author zhangfl@sanxian.com
 * @date 2014-2-20 下午3:27:42
 * @version V1.0
 */
public class DiscusshallIndex extends BaseFragment implements OnClickListener {

	private Context context = null ;
	private ImageView ivSearch = null ;
	private EditText etSearch = null ;
	private ListView discuss_listview = null ;
	DiscusshallIndexAdapter adapter = null ;
	List<DiscusshallInfo> disInfos = null ;

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
		View view = inflater.inflate(R.layout.discusshall_index, container,
				false);
		initView(view);
		
		getData();
		adapter = new DiscusshallIndexAdapter(getActivity(), disInfos);
		discuss_listview.setAdapter(adapter);
		return view;
	}

	private void getData() {
		disInfos = new ArrayList<DiscusshallInfo>();
		DiscusshallInfo info = null ;
		for (int i = 0; i < 20; i++) {
			info = new DiscusshallInfo();
			info.setId("" + i) ;
			info.setDisTitle("交互设计师经常上什么网站寻找灵感？" + i) ; 
			info.setLastTime("2014-02-" + i) ;
			info.setDisCount("" + i * i ) ;
			info.setDisLogo("http://192.168.1.9/mobileapi/1.jpg") ;
			info.setLastDis("经常上什么网站寻经常上什么网站寻经常上什么网站寻经常上什么网站寻") ;
			disInfos.add(info);
		}

	}

	@Override
	public void initView(View view) {
		super.initView(view);
		ivSearch = (ImageView) view.findViewById(R.id.dis_index_search_iv) ;
		etSearch = (EditText) view.findViewById(R.id.dis_index_search_et) ;
		discuss_listview = (ListView) view.findViewById(R.id.discuss_listview);
		ivSearch.setOnClickListener(this) ;
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
	}

	@Override
	public void onClick(View v) {
		int viewID = v.getId() ;
		switch (viewID) {
			case R.id.dis_index_search_iv:
				Util.toastInfo(getActivity(), etSearch.getText().toString()) ;
				break;
	
			default:
				break;
		}
	}

}
