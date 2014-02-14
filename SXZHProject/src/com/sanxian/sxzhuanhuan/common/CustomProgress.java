package com.sanxian.sxzhuanhuan.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * @Title: CustomProgress.java
 * @Package com.sanxian.sxzhuanhuan.common
 * @Description: 带百分号的进度条
 * @author zhangfl@sanxian.com
 * @date 2014-1-22 下午6:02:43
 * @version V1.0
 */
public class CustomProgress extends ProgressBar {
	String text;
	Paint mPaint;

	public CustomProgress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initText();
	}

	public CustomProgress(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initText();
	}

	public CustomProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initText();
	}

	@Override
	public synchronized void setProgress(int progress) {
		// TODO Auto-generated method stub
		setText(progress);
		super.setProgress(progress);

	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// this.setText();
		Rect rect = new Rect();
		this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
		int x = (getWidth() / 2) - rect.centerX();
		int y = (getHeight() / 2) - rect.centerY();
		canvas.drawText(this.text, x, y, this.mPaint);
	}

	// 初始化，画笔
	private void initText() {
		this.mPaint = new Paint();
		this.mPaint.setColor(Color.WHITE);
//		this.mPaint.setTextSize(10.0f) ;
	}

	private void setText() {
		setText(this.getProgress());
	}

	// 设置文字内容
	private void setText(int progress) {
		int i = (progress * 100) / this.getMax();
		this.text = String.valueOf(i) + "%";
	}

}
