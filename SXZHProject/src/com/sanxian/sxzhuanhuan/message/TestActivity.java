package com.sanxian.sxzhuanhuan.message;

import java.util.HashMap;
import java.util.Map;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.function.login.LoginActivity;
import com.sanxian.sxzhuanhuan.message.xmpp.XmppService;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class TestActivity extends Activity implements OnClickListener{
	
	private Button loginBut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		loginBut=(Button)findViewById(R.id.test_testtest);
		loginBut.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.test_testtest:
			MessageLogin login=new MessageLogin(){

				@Override
				protected void onPostExecute(Boolean result) {
					// TODO Auto-generated method stub
					if(result){
						Log.d("loginactivity", "登入成功了启动服务");
						Toast toast=new Toast(TestActivity.this);
						toast.makeText(TestActivity.this, "openfire登入成功", Toast.LENGTH_LONG).show();
						//登入成功
						Intent intentService = new Intent(TestActivity.this,XmppService.class);
						startService(intentService);
						
						Intent intent=new Intent(TestActivity.this, ChatActivity.class);
						startActivity(intent);
					}
				}
				
			};
			Map<String, String> map=new HashMap<String, String>();
//			map.put("username", etAccount.getText().toString());
//			map.put("username", etPassword.getText().toString());
			map.put("username", "luo");
			map.put("username", "123456");						
			login.execute(map);
			break;
		default:
			break;
		}
	}


}
