package com.sanxian.sxzhuanhuan.function.personal.microbank;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.common.IBaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.entity.BankCardInfo;
import com.sanxian.sxzhuanhuan.entity.MicroBankBillInfo;
import com.sanxian.sxzhuanhuan.function.personal_microbank.adapter.MicroBankAccountListAdapter;
import com.sanxian.sxzhuanhuan.function.personal_microbank.adapter.MicroBankIndexAdapter;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
* @ClassName: AccountListActivity  
* @Description:账户卡列表  
* @author honaf
* @date 2014-1-18 上午10:18:12
 */
public class AccountListActivity extends BaseActivity implements IBaseActivity, OnClickListener {

	private ListView listView;
	private List<BankCardInfo> list=new ArrayList<BankCardInfo>();
	private MicroBankAccountListAdapter adapter;
	
	
	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.microbank_account_cashout);
		intImageUtil();
		this.initView();
		this.initListener();
		getData();
		adapter=new MicroBankAccountListAdapter(this, list);
		listView.setAdapter(adapter);
		


	}

	private void getData() {
		for (int i = 0; i < 20; i++) {
			BankCardInfo info=new BankCardInfo(i+"", "中国招商银行（6954）", "1");
			list.add(info);
		}
	}

	@Override
	public void initView() {
		super.initView();
		super.title_txt.setText("账户提现");
		super.title_right.setVisibility(View.INVISIBLE);
		listView=(ListView)this.findViewById(R.id.listView);
		
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);
		listView.setOnItemClickListener(new MyOnItemClick());

	}

	class MyOnItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Util.toastInfo(AccountListActivity.this, arg2+"");
		}

	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_right:
			
			break;
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.recharge:
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
	}

}
