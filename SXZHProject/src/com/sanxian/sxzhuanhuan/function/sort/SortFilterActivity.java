package com.sanxian.sxzhuanhuan.function.sort;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: SortFilterActivity.java
 * @Package com.sanxian.sxzhuanhuan.function.sort
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-8 下午2:17:34
 * @version V1.0
 */
public class SortFilterActivity extends BaseActivity implements OnItemClickListener{
	private Context context = null ;
	/**筛选下的地域按钮 */
	private Button btnFilterZone = null ;
	/**筛选下的排序按钮 */
	private Button btnFilterSort = null ;
	/**筛选地域按钮下的线性布局 */
	private LinearLayout llFilterZone = null ;
	/**筛选排序按钮下的线性布局 */
	private LinearLayout llFilterSequence = null ;
	/**筛选地域按钮下左边的线性布局 */
	private LinearLayout llFilterLeftProvince = null ;
	private ListView lvFilterLeftProvince = null ;
	/**筛选地域按钮下右边的线性布局 */
	private LinearLayout llFilterRightCity = null ;
	private ListView lvFilterRightCity = null ;
	
	/**排序下的按默认排序 */
	private TextView tvSeqDefault = null ;
	/**按发布时间最新排序 */
	private TextView tvSeqNearpub = null ;
	/**价格的↑ */
	private ImageView ivSeqPriceup = null ;
	/**价格下降 */
	private ImageView ivSeqPricedown = null ;
	/**按点击量 */
	private TextView tvSeqClickcount = null ;
	/**按参与人数 */
	private TextView tvSeqPartispate = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = SApplication.getInstance();
		
		setContentView(R.layout.sort_filter) ;
		
		initWidget() ;
		
		//android.R.layout.simple_expandable_list_item_1
		lvFilterLeftProvince.setAdapter(new ArrayAdapter<String>(this, R.layout.sort_filter_item ,getResources().getStringArray(R.array.array_china_province))); 
		lvFilterRightCity.setAdapter(new ArrayAdapter<String>(this , R.layout.sort_filter_item , getResources().getStringArray(R.array.array_hunan))) ;
	}
	
	private void initWidget() {
		btnFilterZone = (Button) findViewById(R.id.sort_filter_btn_zone) ;
		btnFilterSort = (Button) findViewById(R.id.sort_filter_btn_sort) ;
		llFilterZone = (LinearLayout) findViewById(R.id.sort_filter_ll_zone) ;
		llFilterSequence = (LinearLayout) findViewById(R.id.sort_filter_ll_sequence) ; 
		llFilterLeftProvince = (LinearLayout) findViewById(R.id.sort_filter_ll_left_province) ;
		lvFilterLeftProvince = (ListView) findViewById(R.id.sort_filter_ll_left_province_listview) ;
		llFilterRightCity = (LinearLayout) findViewById(R.id.sort_filter_ll_right_city) ;
		lvFilterRightCity = (ListView) findViewById(R.id.sort_filter_ll_right_city_listview) ;
		tvSeqDefault = (TextView) findViewById(R.id.sort_filter_ll_sequence_default) ;
		tvSeqNearpub = (TextView) findViewById(R.id.sort_filter_ll_sequence_pub_time) ;
		ivSeqPriceup = (ImageView) findViewById(R.id.sort_filter_ll_sequence_price_up) ;
		ivSeqPricedown = (ImageView) findViewById(R.id.sort_filter_ll_sequence_price_down) ;
		tvSeqClickcount = (TextView) findViewById(R.id.sort_filter_ll_sequence_click_count) ;
		tvSeqPartispate = (TextView) findViewById(R.id.sort_filter_ll_sequence_partipete) ;
		
		
		btnFilterZone.setOnClickListener(this) ;
		btnFilterSort.setOnClickListener(this) ;
		lvFilterLeftProvince.setOnItemClickListener(this) ;
		lvFilterRightCity.setOnItemClickListener(this) ;
		
		llFilterZone.setVisibility(View.VISIBLE) ;
		llFilterSequence.setVisibility(View.GONE) ;
		llFilterLeftProvince.setVisibility(View.VISIBLE) ;
		llFilterRightCity.setVisibility(View.INVISIBLE) ;
		tvSeqDefault.setOnClickListener(this) ;
		tvSeqNearpub.setOnClickListener(this) ;
		ivSeqPriceup.setOnClickListener(this) ;
		ivSeqPricedown.setOnClickListener(this) ;
		tvSeqClickcount.setOnClickListener(this) ;
		tvSeqPartispate.setOnClickListener(this) ;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		Util.toastInfo(context, ((TextView)view).getText().toString()) ;
		// TODO 还要根据城市ID来进行判断：是地区还是城市，两者不一样
//		view.setBackgroundColor(getResources().getColor(R.color.red)) ;
		if("湖南".equals(((TextView)view).getText().toString())) {
			llFilterRightCity.setVisibility(View.VISIBLE) ;
		} else {
			llFilterRightCity.setVisibility(View.INVISIBLE) ;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.sort_filter_btn_zone :
				btnFilterZone.setEnabled(false) ;
				btnFilterSort.setEnabled(true) ;
				
				llFilterSequence.setVisibility(View.GONE) ;
				llFilterZone.setVisibility(View.VISIBLE) ;
				llFilterLeftProvince.setVisibility(View.VISIBLE) ;
				llFilterRightCity.setVisibility(View.INVISIBLE) ;
				break ;
			case R.id.sort_filter_btn_sort:
				btnFilterZone.setEnabled(true) ;
				btnFilterSort.setEnabled(false) ;
				
				llFilterSequence.setVisibility(View.VISIBLE) ;
				llFilterZone.setVisibility(View.GONE) ;
//				llFilterLeftProvince.setVisibility(View.GONE) ;
//				llFilterRightCity.setVisibility(View.GONE) ;
				break ;
				
			case R.id.sort_filter_ll_sequence_default :
				Util.toastInfo(context, "default") ;
				break ;
			case R.id.sort_filter_ll_sequence_pub_time :
				Util.toastInfo(context, "pub time") ;
				break ;
			case R.id.sort_filter_ll_sequence_price_up :
				Util.toastInfo(context, "price up " ) ;
				break ;
			case R.id.sort_filter_ll_sequence_price_down :
				Util.toastInfo(context, "price down") ;
				break ;
			case R.id.sort_filter_ll_sequence_click_count :
				Util.toastInfo(context, "click count") ;
				break ;
			case R.id.sort_filter_ll_sequence_partipete :
				Util.toastInfo(context, "partipate" ) ;
				break ;
		}
	}
}
