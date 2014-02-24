package com.sanxian.sxzhuanhuan.function.homeindex.originality;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

class MyListView extends ListView {
	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	

    @Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

        int expandSpec = MeasureSpec.makeMeasureSpec( 
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
        super.onMeasure(widthMeasureSpec, expandSpec); 
    } 
	@Override
	public View getChildAt(int index) {
		return super.getChildAt(index);
	}
}