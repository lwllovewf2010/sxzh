package com.sanxian.sxzhuanhuan.dialog;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.entity.UserInfo;
import com.sanxian.sxzhuanhuan.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
* @ClassName: MiddleDialog  
* @Description: 中间弹出dialog （包括提示和输入）
* @author honaf
* @date 2014-1-9 下午5:27:29
 */
public class MiddleDialog extends Activity {
	// private MyDialog dialog;
	private LinearLayout layout;
	MiddleDialogInfo info = null;
	private Button button[]=new Button[2];
	private TextView dialog_title;
	private TextView message;
	private EditText edit_message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		info = (MiddleDialogInfo) this.getIntent().getSerializableExtra("info");
		if (info == null) {
			setResult(DialogConstant.DIALOG_RETURN);
			this.finish();
			return;
		}
		
		setContentView(R.layout.middle_dialog);
		initView();
		layout = (LinearLayout) findViewById(R.id.main_dialog_layout);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}
	public void initView(){
		button[0]=(Button)this.findViewById(R.id.ok);
		button[1]=(Button)this.findViewById(R.id.cancel);
		dialog_title=(TextView)this.findViewById(R.id.dialog_title);
		message=(TextView)this.findViewById(R.id.message);
		edit_message=(EditText)this.findViewById(R.id.edit_message);
		if(info.isEdit()){
			edit_message.setVisibility(View.VISIBLE);
			message.setVisibility(View.GONE);
		}else{
			message.setVisibility(View.VISIBLE);
			edit_message.setVisibility(View.GONE);
		}
		button[0].setText(info.getOk());
		button[1].setText(info.getCancel());
		dialog_title.setText(info.getTitle());
		message.setText(info.getContent());
		edit_message.setText(info.getEdit_content());
		edit_message.setHint(info.getEdit_hint());
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setResult(DialogConstant.DIALOG_RETURN);
		finish();
		return true;
	}
	String return_value="";
	public void one(View v) {
		Intent intent=new Intent();
		return_value=edit_message.getText().toString().trim();
		if(info.isEdit()){
			if(!"".equals(return_value)){
				intent.putExtra("return_value",return_value);
			}
			
		}
		setResult(DialogConstant.MIDDLE_OK, intent);
		this.finish();
	}

	public void two(View v) {
		setResult(DialogConstant.MIDDLE_CANCEL);
		this.finish();
	}


}
