package com.sanxian.sxzhuanhuan.dialog;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BottomRightOrLeftDialog extends Activity {
	// private MyDialog dialog;
	private LinearLayout layout;
	TopDialogInfo info = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		info = (TopDialogInfo) this.getIntent().getSerializableExtra("info");
		if (info == null) {
			setResult(DialogConstant.DIALOG_RETURN);
			this.finish();
			return;
		}
		if (DialogConstant.BLEFT == info.getDirection()) {
			setContentView(R.layout.mycollection_bottom_left_dialog);
		} else if (DialogConstant.BRIGHT == info.getDirection()) {
			setContentView(R.layout.mycollection_bottom_right_dialog);
		}

		layout = (LinearLayout) findViewById(R.id.main_dialog_layout);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// setResult(-1);
				// Util.toastInfo(TopRightOrLeftDialog.this, "hello_layout");
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setResult(DialogConstant.DIALOG_RETURN);
		finish();
		return true;
	}

	public void all(View v) {
		setResult(DialogConstant.TOPDIALOG_ONE);
		this.finish();
	}

	public void onlyforproduct(View v) {
		setResult(DialogConstant.TOPDIALOG_TWO);
		this.finish();
	}

	public void onlyforproject(View v) {
		setResult(DialogConstant.TOPDIALOG_THREE);
		this.finish();
	}

	
	public void four(View v) {
		setResult(DialogConstant.TOPDIALOG_FOUR);
		this.finish();
	}

	public void five(View v) {
		setResult(DialogConstant.TOPDIALOG_FIVE);	
		this.finish();
	}
}
