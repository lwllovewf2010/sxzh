package com.sanxian.sxzhuanhuan.function.homeindex.project;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.MetaData;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @Title: ProjectAdapter.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex
 * @Description: 项目内容首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-14 上午10:42:45
 * @version V1.0
 */
public class ProjectAdapter extends BaseAdapter implements OnClickListener {

	private Context context = null;
	private ArrayList<MetaData> infos = null;
	private LayoutInflater inflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null ;
	private ProjectInfo projectInfo = null ;

	public ProjectAdapter(Context context,
			ArrayList<? extends MetaData> infos) {
		this.context = context;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.sort_detail_item, null);
			holder = new ViewHolder();
			init(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		projectInfo = (ProjectInfo) infos.get(position);
		setData(holder, projectInfo);

		options = UIHelper.setOption() ;
		
		convertView.setOnClickListener(this);
		return convertView;
	}

	private void init(View convertView, ViewHolder holder) {
		holder.tvName = (TextView) convertView
				.findViewById(R.id.sort_detail_item_list_top_content);
		holder.ivLogo = (ImageView) convertView
				.findViewById(R.id.sort_detail_item_list_image);
		holder.tvProfile = (TextView) convertView
				.findViewById(R.id.sort_detail_item_list_right_text);
		holder.ivProgress = (ProgressBar) convertView
				.findViewById(R.id.sort_detail_item_progressBar);

		holder.ivParticipate = (ImageView) convertView
				.findViewById(R.id.sort_detail_item_ivParticipate);
		holder.tvParticipate = (TextView) convertView
				.findViewById(R.id.sort_detail_item_tvParticipate);
		holder.ivDiscuss = (ImageView) convertView
				.findViewById(R.id.sort_detail_item_ivDiscuss);
		holder.tvDiscuss = (TextView) convertView
				.findViewById(R.id.sort_detail_item_tvDiscuss);
		holder.ivDays = (ImageView) convertView
				.findViewById(R.id.sort_detail_item_ivDays);
		holder.tvDays = (TextView) convertView
				.findViewById(R.id.sort_detail_item_tvDays);
		holder.ivMoney = (ImageView) convertView
				.findViewById(R.id.sort_detail_item_ivMoney);
		holder.tvMoney = (TextView) convertView
				.findViewById(R.id.sort_detail_item_tvMoney);

	}

	private void setData(ViewHolder holder, ProjectInfo info) {
		holder.tvName.setText(info.getProject_name());
//		holder.ivLogo.setImageDrawable(context.getResources().getDrawable(
//				R.drawable.logo));
		imageLoader.displayImage(info.getProject_logo(),holder.ivLogo, options, null);
		
//		holder.tvProfile.setText(Html.fromHtml(info.getProject_describe()));
		holder.tvProfile.setText(info.getProject_explain()) ;
		
		holder.ivProgress.setProgress(25) ;
//		holder.ivProgress.setProgress(Integer.parseInt(info.getPurchase_money()) * 100 / info.getProject_money() ) ;

		holder.ivParticipate.setImageDrawable(context.getResources()
				.getDrawable(R.drawable.groupico));
		holder.tvParticipate.setText("" + 12 + "人");
		holder.ivDiscuss.setImageDrawable(context.getResources().getDrawable(
				R.drawable.groupico));
		holder.tvDiscuss.setText("" + 122 + "讨论");
		holder.ivDays.setImageDrawable(context.getResources().getDrawable(
				R.drawable.groupico));
		holder.tvDays.setText("" + info.getProject_days() + "天");
		holder.ivMoney.setImageDrawable(context.getResources().getDrawable(
				R.drawable.groupico));
		holder.tvMoney.setText("￥" + info.getProject_money());
		
		holder.project_id = info.getId() ;
	}

	class ViewHolder {
		public TextView tvName;
		public ImageView ivLogo;
		public TextView tvProfile;
		public ProgressBar ivProgress;

		public ImageView ivParticipate;
		public TextView tvParticipate;
		public ImageView ivDiscuss;
		public TextView tvDiscuss;
		public ImageView ivDays;
		public TextView tvDays;
		public ImageView ivMoney;
		public TextView tvMoney;
		
		public String project_id ; 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("-------project-------");
		System.out.println("----" + projectInfo.getId());
		UIHelper.showProjectDetailActivity(context ,((ViewHolder)v.getTag()).project_id);
	}

}
