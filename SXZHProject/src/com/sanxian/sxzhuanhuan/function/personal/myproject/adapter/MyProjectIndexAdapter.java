package com.sanxian.sxzhuanhuan.function.personal.myproject.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.CustomProgress;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;

public class MyProjectIndexAdapter extends BaseAdapter implements OnClickListener {

	private Context context = null;
	private List<ProjectInfo> infos = null;
	private LayoutInflater inflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null ;
	private ProjectInfo projectInfo = null ;

	public MyProjectIndexAdapter(Context context,List<ProjectInfo> infos) {
		this.context = context;
		this.infos = infos;
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
//		holder.ivProgress = (ProgressBar) convertView
//				.findViewById(R.id.sort_detail_item_progressBar);
		holder.ivProgress = (CustomProgress) convertView
				.findViewById(R.id.sort_detail_item_progressBar);

		holder.ivProgress = (CustomProgress) convertView
				.findViewById(R.id.sort_detail_item_progressBar);

		holder.tvCurMoney = (TextView) convertView
				.findViewById(R.id.sort_detail_item_cur_money);
		holder.tvJoinPerson = (TextView) convertView
				.findViewById(R.id.sort_detail_item_person_join);
		holder.tvAllMoney = (TextView) convertView
				.findViewById(R.id.sort_detail_item_all_money);
		holder.tvShenTime = (TextView) convertView
				.findViewById(R.id.sort_detail_item_shen_time);

	}

	private void setData(ViewHolder holder, ProjectInfo info) {
		holder.tvName.setText(info.getProject_name());
//		holder.ivLogo.setImageDrawable(context.getResources().getDrawable(
//				R.drawable.logo));
		imageLoader.displayImage(info.getProject_logo(),holder.ivLogo, options, null);
		
//		holder.tvProfile.setText(Html.fromHtml(info.getProject_describe()));
		holder.tvProfile.setText(info.getProject_explain()) ;
		
//		holder.ivProgress.setProgress(25) ;
		holder.ivProgress.setProgress(Integer.parseInt(info.getPurchase_money()) * 100 / info.getProject_money() ) ;
		

		holder.tvCurMoney.setText("￥" + info.getPurchase_money() );
		holder.tvJoinPerson.setText("" + info.getAttention_nums() );
		holder.tvAllMoney.setText("￥" + info.getProject_money());
		holder.tvShenTime.setText("---" );
		
		holder.project_id = info.getId() ;
	}

	class ViewHolder {
		public TextView tvName;
		public ImageView ivLogo;
		public TextView tvProfile;
//		public ProgressBar ivProgress;
		public CustomProgress ivProgress ;

		public TextView tvCurMoney;
		public TextView tvJoinPerson;
		public TextView tvAllMoney;
		public TextView tvShenTime;
		
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
