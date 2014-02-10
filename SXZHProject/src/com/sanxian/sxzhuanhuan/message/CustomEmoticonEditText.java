/*
 * Copyright (C) Winkee Technologies Co., Ltd. 2005-2012. 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information 
 * of Winkee Technologies Co., Ltd. ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Winkee. 
 */
package com.sanxian.sxzhuanhuan.message;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 自定义EditText
 * 
 * @author luozhiren
 * @date 2012-5-25
 * @version 1.0
 */
public class CustomEmoticonEditText extends EditText{
	public static final String TAG = "CustomEmoticonEditText";
	public static boolean isFrist=false;
	public static Context context;
	public CustomEmoticonEditText(Context context) {
		super(context);
		this.context = context;
	}

	public CustomEmoticonEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 设置表情图片
	 * 
	 * @param id
	 *            :资源id
	 * @param drawableName
	 *            :资源名称
	 * @return
	 * @date 2012-5-25
	 * @author luozhiren
	 */
	public void chooseAnEmoticonById(int id, String drawableName) {
		if (drawableName != null) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					id);
			// 根据Bitmap对象创建ImageSpan对象
			ImageSpan imageSpan = new ImageSpan(context, bitmap);
			// 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
			final SpannableString spannableString = new SpannableString(drawableName);
			// 用ImageSpan对象替换face
			spannableString.setSpan(imageSpan, 0, drawableName.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 将随机获得的图像追加到EditText控件的最后
			getText().insert(getSelectionStart(), spannableString);
		}

	}

}
