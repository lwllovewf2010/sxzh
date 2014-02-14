package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.io.File;
import java.util.Date;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.FootDialog;
import com.sanxian.sxzhuanhuan.dialog.FootDialogInfo;
import com.sanxian.sxzhuanhuan.util.ImageUtil;
/**
 * 我的账号实名认证页面
 * @author joe
 *
 */
public class MyAccoCertifyNameActivity extends BaseActivity {
    
	private ImageView id_card_image1;
	private ImageView id_card_image2;
	private LinearLayout add_image_layout;
	
	private final String avatorpath = Environment.getExternalStorageDirectory()+ "/sanxian/sxzhproject/avator/";
	private String sheariamgepath = ""; // 裁剪后头像路径
	private String photographpath = ""; // 拍照完后头像路径
	
	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 6;
	private static final int CAMERA_REQUEST_CODE = 7;
	private static final int RESULT_REQUEST_CODE = 8;
	
	private String images[] = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_certify_name);
		initView();
		initListener();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setRight("确定");
		setTitle("实名认证");
		id_card_image1 = (ImageView)findViewById(R.id.id_card_image1);
		id_card_image2 = (ImageView)findViewById(R.id.id_card_image2);
		add_image_layout = (LinearLayout)findViewById(R.id.add_image_layout);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		add_image_layout.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.add_image_layout:
			showDialog();
		    break;
		default:
			break;
		}
	}
	
	public void showDialog(){
		Intent intent = new Intent(this,FootDialog.class);
		FootDialogInfo info = new FootDialogInfo();
		String []arrayStrings={"拍照","从相册选择","取消"};
		info.setMenu(arrayStrings);
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_FOOT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == DialogConstant.REQUEST_FOOT){
			if(resultCode==DialogConstant.FOOTDIALOG_ONE){
				getPhotoByCamere();
			}else if(resultCode==DialogConstant.FOOTDIALOG_TWO){
				getPhotoFromAlum();
			}else if(resultCode==DialogConstant.FOOTDIALOG_THREE){
			}
		}
		if (resultCode != RESULT_CANCELED) {
			ContentResolver resolver = getContentResolver();
            String state = Environment.getExternalStorageState();
			Bitmap bm = null;
			switch (requestCode) {
			case IMAGE_REQUEST_CODE:
				try {
					Uri originalUri = data.getData();
					startPhotoZoom(originalUri);
					bm = MediaStore.Images.Media.getBitmap(resolver,
							originalUri);
					String[] proj = { MediaStore.Images.Media.DATA };
					Cursor cursor = managedQuery(originalUri, proj, null, null,null);
					int column_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path = cursor.getString(column_index);
					// 压缩大小
					sheariamgepath = avatorpath + "sximage" + new Date().getTime()+ ".jpg";
					app.sheariamgepath = sheariamgepath;
					ImageUtil.getimage(path,sheariamgepath,avatorpath);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case CAMERA_REQUEST_CODE:
				if (state.equals(Environment.MEDIA_MOUNTED)) {
					File fileDir = new File(avatorpath);
					if (!fileDir.exists()) {
						fileDir.mkdirs();// 创建文件夹
					}
					if ("".equals(photographpath)) {
						photographpath = app.photographpath;
					}
					File tempFile = new File(photographpath);
					startPhotoZoom(Uri.fromFile(tempFile));
					// 压缩大小
					sheariamgepath = avatorpath + "sximage" + new Date().getTime()+ ".jpg";
					app.sheariamgepath = sheariamgepath;
					ImageUtil.getimage(photographpath,sheariamgepath,avatorpath);

				} else {
					Toast.makeText(MyAccoCertifyNameActivity.this, "未找到存储卡，无法存储照片！",
							Toast.LENGTH_LONG).show();
				}

				break;
				
			case RESULT_REQUEST_CODE:
				if (data != null) {
				    if(state.equals(Environment.MEDIA_MOUNTED)){
					getImageToView(data);}
				    else{
				    Toast.makeText(MyAccoCertifyNameActivity.this, "未找到存储卡，无法存储照片！",
	                            Toast.LENGTH_LONG).show();    
					}
				 }
				break;
				}
			}
	}
	
	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}
	/*
	 * 从相册中获取照片
	 */
	public void getPhotoFromAlum() {
		Intent intentFromGallery = new Intent();
		intentFromGallery.setType("image/*"); // 设置文件类型
		intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
	}

	/*
	 * 手机拍照获取照片
	 */
	private void getPhotoByCamere() {
		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 判断存储卡是否可以用，可用进行存储
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			File fileDir = new File(avatorpath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();// 创建文件夹
			}
			photographpath = avatorpath + "sximage" + new Date().getTime()
					+ ".jpg";
			app.photographpath = photographpath;
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(photographpath)));
		}
		startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
	}
	
	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			if(images == null){
			   images = new String[2];
			}
			if(images[0] == null || images[0].equals("")){
				id_card_image1.setImageDrawable(drawable);
			}else {
				id_card_image2.setImageDrawable(drawable);
			}
			Log.e("qiao ", "原图路径 " + sheariamgepath);
//			if ("".equals(sheariamgepath)) {
//				sheariamgepath = app.sheariamgepath;
//			}
//			try {
//				FileOutputStream out = new FileOutputStream(sheariamgepath);
//				photo.compress(Bitmap.CompressFormat.PNG, 90, out);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

		}
	}
}
