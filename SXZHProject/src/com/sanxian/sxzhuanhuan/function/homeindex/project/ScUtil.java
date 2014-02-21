package com.sanxian.sxzhuanhuan.function.homeindex.project;

import java.util.List;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.CommonHeader;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
* @Title: ScUtil.java 
* @Package com.sanxian.sxzhuanhuan.function.homeindex 
* @Description: TODO
* @author zhangfl@sanxian.com
* @date 2014-1-14 上午11:16:25  
* @version V1.0
 */
public class ScUtil {

	private ViewPager mPager;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t1, t2;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private Activity content;
	private CommonHeader commonHeader = null ;
	public ScUtil(Activity activity) {
		content = activity;
	}

	public void showSc(String text1Name, String text2Name, List<View> list ) {
		InitTextView(text1Name, text2Name);
		InitViewPager(list);
		InitImageView();
	}
	
	public void showSc(String text1Name, String text2Name, List<View> list , CommonHeader commonHeader) {
		this.commonHeader = commonHeader ;
		InitTextView(text1Name, text2Name);
		InitViewPager(list);
		InitImageView();
	}

	/**
	 * 初始化头标
	 */
	private void InitTextView(String text1Name, String text2Name) {
		t1 = (TextView) content.findViewById(R.id.common_content_profile);
		t2 = (TextView) content.findViewById(R.id.common_content_comment);
		t1.setText(text1Name);
		t2.setText(text2Name);
		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));

	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager(List<View> list) {
		mPager = (ViewPager) content.findViewById(R.id.vPager);
		listViews = list;
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) content.findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(content.getResources(),
				R.drawable.a).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		content.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
//
//		System.out.println("bmpW=" + bmpW + "---screenW" + screenW
//				+ " ---offset " + offset);
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		// int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		// int two = one * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageSelected(int arg0) {
			int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
			int two = one * 2;// 页卡1 -> 页卡3 偏移量

//			System.out.println(" bmpW " + bmpW + "offset" + offset
//					+ "   currIndex" + currIndex + "--arg0" + arg0 + "-one "
//					+ one + "-two " + two);
			Animation animation = null;
			switch (arg0) {
			case 0:
				if(null != commonHeader) 
					commonHeader.show(true, true , "返回", true, t1.getText().toString(), false , "" , false) ;
				if (currIndex == 1) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				// else if (currIndex == 2) {
				// animation = new TranslateAnimation(two, 0, 0, 0);
				// }
				break;
			case 1:
				if(null != commonHeader) 
					commonHeader.show(true, true , "返回", true, t2.getText().toString(), false , "" , false) ;
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				}
				// else if (currIndex == 2) {
				// animation = new TranslateAnimation(two, one, 0, 0);
				// }
				break;
			// case 2:
			// if (currIndex == 0) {
			// animation = new TranslateAnimation(offset, two, 0, 0);
			// } else if (currIndex == 1) {
			// animation = new TranslateAnimation(one, two, 0, 0);
			// }
			// break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

}
