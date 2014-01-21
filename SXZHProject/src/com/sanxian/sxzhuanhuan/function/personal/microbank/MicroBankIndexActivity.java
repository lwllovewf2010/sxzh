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
import com.sanxian.sxzhuanhuan.entity.MicroBankBillInfo;
import com.sanxian.sxzhuanhuan.function.personal_microbank.adapter.MicroBankIndexAdapter;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * 
 * @ClassName: MicroBankIndexActivity
 * @Description: 微银行首页
 * @author honaf
 * @date 2014-1-16 上午9:52:48
 */
public class MicroBankIndexActivity extends BaseActivity implements IBaseActivity, OnClickListener {

	private MicroBankIndexAdapter adapter;
	private List<MicroBankBillInfo> list = new ArrayList<MicroBankBillInfo>();
	private ListView listView;
	private Button rechargeButton;
	private Button cashoutButton;
	TopDialogInfo dialogInfo = null;
	public ImageLoader imageLoader;
	public DisplayImageOptions options;

	public void intImageUtil() {

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_avatar).cacheInMemory().cacheOnDisc().build();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.microbank_index);
		intImageUtil();
		this.initView();
		this.initListener();
		getData();
		adapter = new MicroBankIndexAdapter(this, list);
		listView.setAdapter(adapter);

	}

	private void getData() {
		for (int i = 0; i < 15; i++) {
			MicroBankBillInfo info = new MicroBankBillInfo(i + "", "2013-1-4", "产品", "6s", 400 + "", 500 + "");
			list.add(info);
		}
	}

	@Override
	public void initView() {
		super.initView();
		listView = (ListView) this.findViewById(R.id.listView);
		super.title_txt.setText("微银行");
		rechargeButton = (Button) this.findViewById(R.id.recharge);
		cashoutButton = (Button) this.findViewById(R.id.cashout);
	}

	@Override
	public void initListener() {
		super.initListener();
		super.button_left.setOnClickListener(this);
		super.button_right.setOnClickListener(this);
		listView.setOnItemClickListener(new MyOnItemClick());
		rechargeButton.setOnClickListener(this);
		cashoutButton.setOnClickListener(this);

	}

	class MyOnItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Util.toastInfo(MicroBankIndexActivity.this, arg2 + "");
		}

	}

	@Override
	public void refresh(Object... param) {
	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.title_right:
			String menu[] = new String[] { "查看全部", "只看充值", "只看提现", "只看购买", "只看借贷", "只看还贷", "只看公司分红", "只看借贷分红", "只看项目投资" };
			dialogInfo = new TopDialogInfo(DialogConstant.TRIGHT, menu);
			intent.setClass(MicroBankIndexActivity.this, TopRightOrLeftDialog.class);
			intent.putExtra("info", dialogInfo);
			startActivityForResult(intent, DialogConstant.REQUEST_TOP);
			break;
		case R.id.title_Left:
			this.finish();
			break;
		case R.id.recharge:
			intent.setClass(MicroBankIndexActivity.this, AccountRechargeActivity.class);
			startActivity(intent);
			break;
		case R.id.cashout:
			intent.setClass(MicroBankIndexActivity.this, AccountCashoutActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == DialogConstant.REQUEST_TOP) {
			if (resultCode != DialogConstant.DIALOG_RETURN) {
				Util.toastInfo(MicroBankIndexActivity.this, dialogInfo.getMenu()[resultCode]);
			}

		}
	}

}
