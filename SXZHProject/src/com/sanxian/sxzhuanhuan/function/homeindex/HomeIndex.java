package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.entity.ProjectBean;

/**
 * 首页
 * @author Administrator
 *
 */
public class HomeIndex extends BaseFragment implements OnClickListener{
	
	/**
	 * 请求广告图片
	 */
	private static final int HOME_INDEX_REQUEST_ADVERTISING=1;
	private static final String TAG="HomeIndex";	
	private Button imageBut1, imageBut2 ,imageBut3;
	
	
	private CharSequence[] publishType= {"创意话题","集资项目"};
	
	
	//项目列表
	private ListView projectList;
	private HomeIndexAdapter indexAdapter;
	
	
    private Context context;
    public HomeIndex( ){
        super();     
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.home_index, container,false);
		init(view);
		return view;
	}

	

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		Log.d(TAG, param.length+"/                  ///////////");
		
		
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//发布创意
		case R.id.title_right:
			new AlertDialog.Builder(getActivity()).setItems(publishType, new DialogInterface.OnClickListener() {				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub	
					switch (which) {
					//创意话题
					case 0:
						Intent topicIntent=new Intent(getActivity(), PublishTopic.class);
						getActivity().startActivity(topicIntent);
						break;
					//集资项目
					case 1:
						Intent intent=new Intent(getActivity(), PublishOriginality.class);
						getActivity().startActivity(intent);
						break;
					default:
						break;
					}
				}
			}).show();
			
			break;

		default:
			break;
		}
		
	}
	
	
	/**
	 * 初始化数据
	 */
	private void init(View view){
		//导航左边的按钮
		Button leftBut=(Button)view.findViewById(R.id.title_right);
		leftBut.setText("我要发布");
		leftBut.setOnClickListener(this);
		
		imageBut1=(Button)view.findViewById(R.id.home_index_image1);
		imageBut2=(Button)view.findViewById(R.id.home_index_image2);
		imageBut3=(Button)view.findViewById(R.id.home_index_image3);
		
		
//		IndexAPI indexRequest=new IndexAPI();
//		indexRequest.findAdvertisingImage("gethomepage", "ad", this, HOME_INDEX_REQUEST_ADVERTISING);
		
		projectList=(ListView)view.findViewById(R.id.home_index_content_list);
		List<ProjectBean> list=new ArrayList<ProjectBean>();
		for(int i=0;i<30;i++){
			ProjectBean bean=new ProjectBean();
			bean.setProjecTitle("这是一个很好的项目"+i);
			bean.setContent("这是一个类似于qq的项目 在这个项目中你将可以于你的好友进行沟通.............");
			bean.setImageURL("https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/" +
					"AAAAAAAAAbs/rWlj1RUKrYI/s160-c/A%252520Photographer.jpg");
			list.add(bean);
		}
		indexAdapter=new HomeIndexAdapter(getActivity(), list);
		projectList.setAdapter(indexAdapter);
	}

}
