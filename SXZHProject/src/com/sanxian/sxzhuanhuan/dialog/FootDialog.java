package com.sanxian.sxzhuanhuan.dialog;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FootDialog extends Activity {
	// private MyDialog dialog;
	private LinearLayout layout;
	FootDialogInfo info = null;
	private Button button[]=new Button[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		info = (FootDialogInfo) this.getIntent().getSerializableExtra("info");
		if (info == null) {
			setResult(DialogConstant.DIALOG_RETURN);
			this.finish();
			return;
		}

		setContentView(R.layout.bottom_commom_popupwindow);
		initView();
		initData();
		layout = (LinearLayout) findViewById(R.id.main_dialog_layout);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}
	public void initView(){
		button[0]=(Button)this.findViewById(R.id.button1);
		button[1]=(Button)this.findViewById(R.id.button2);
		button[2]=(Button)this.findViewById(R.id.button3);
	}
	public void initData(){
		int length=info.getMenu().length;
		for (int i = 0; i < length; i++) {
			button[i].setText(info.getMenu()[i]);
		}
		if(length==2){
			button[3].setVisibility(View.GONE);
		}
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setResult(DialogConstant.DIALOG_RETURN);
		finish();
		return true;
	}

	public void one(View v) {
		setResult(DialogConstant.FOOTDIALOG_ONE);
		this.finish();
	}

	public void two(View v) {
		setResult(DialogConstant.FOOTDIALOG_TWO);
		this.finish();
	}

	public void three(View v) {
		setResult(DialogConstant.FOOTDIALOG_THREE);
		this.finish();
	}

}
