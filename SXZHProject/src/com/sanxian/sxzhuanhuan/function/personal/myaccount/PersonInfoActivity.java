package com.sanxian.sxzhuanhuan.function.personal.myaccount;

import java.io.File;
import java.io.FileOutputStream;
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
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.util.ImageUtil;
/**
 * 个人资料页面
 * @author joe
 *
 */
public class PersonInfoActivity extends BaseActivity implements OnClickListener{
	private RelativeLayout avatar_layout,username_layout,sex_layout,area_layout,signature_layout,person_data_layout;//头像，姓名，性别，地区，个性签名,整个布局
	private boolean is_show = true;
	private Animation showAction, hideAction;
	private LinearLayout bottom_layout;//底部布局
	private Button take_photo_btn,album_btn,cancel_btn;//拍照，从相册，取消
	
	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 6;
	private static final int CAMERA_REQUEST_CODE = 7;
	private static final int RESULT_REQUEST_CODE = 8;
	
	 
	private final String avatorpath = Environment.getExternalStorageDirectory()+ "/sanxian/sxzhproject/avator/";
	private String sheariamgepath = ""; // 裁剪后头像路径
	private String photographpath = ""; // 拍照完后头像路径
	
	private ImageView myacc_avatar;//头像
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myacc_personinfo);
		initView();
		initListener();
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		displayRight();
		setTitle("个人信息");
		
		avatar_layout = (RelativeLayout)findViewById(R.id.avatar_layout);
		username_layout = (RelativeLayout)findViewById(R.id.username_layout);
		sex_layout = (RelativeLayout)findViewById(R.id.sex_layout);
		area_layout = (RelativeLayout)findViewById(R.id.area_layout);
		signature_layout = (RelativeLayout)findViewById(R.id.signature_layout);
		person_data_layout = (RelativeLayout)findViewById(R.id.person_data_layout);
		bottom_layout = (LinearLayout)findViewById(R.id.bottom_layout);
		showAction = AnimationUtils.loadAnimation(this,R.anim.slide_up_in);
     	hideAction = AnimationUtils.loadAnimation(this, R.anim.slide_down_out);
     	take_photo_btn = (Button)findViewById(R.id.take_photo_btn);
     	album_btn = (Button)findViewById(R.id.album_btn);
     	cancel_btn = (Button)findViewById(R.id.cancel_btn);
     	
     	myacc_avatar = (ImageView)findViewById(R.id.myacc_avatar);
		
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		avatar_layout.setOnClickListener(this);
		username_layout.setOnClickListener(this);
		sex_layout.setOnClickListener(this);
		area_layout.setOnClickListener(this);
		signature_layout.setOnClickListener(this);
		person_data_layout.setOnClickListener(this);
		take_photo_btn.setOnClickListener(this);
		album_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;

		case R.id.avatar_layout:
			if(is_show){
				showBottomLayout();
			}else{
				hideBottomLayout();
			}
			break;
		case R.id.username_layout:
			break;
		case R.id.sex_layout:
			break;
		case R.id.area_layout:
			break;
		case R.id.signature_layout:
			break;
		case R.id.person_data_layout:
			if(!is_show){
				hideBottomLayout();
			}
			break;
		case R.id.take_photo_btn:// 拍照
			getPhotoByCamere();
			hideBottomLayout();
			break;
		case R.id.album_btn://从相册选取
			getPhotoFromAlum();
			hideBottomLayout();
			break;
		case R.id.cancel_btn://取消
			hideBottomLayout();
			break;
		default:
			break;
		}
		
	}
	/**
	 * 显示底部菜单
	 * joe
	 */
	
	private void showBottomLayout(){
		bottom_layout.setVisibility(View.VISIBLE);	
		bottom_layout.startAnimation(showAction);
		is_show = false;
	};
	
	/**
	 * 隐藏底部菜单
	 * joe
	 */
	private void hideBottomLayout(){
		bottom_layout.setVisibility(View.GONE);	
		bottom_layout.startAnimation(hideAction);
		is_show = true;
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
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
					Toast.makeText(PersonInfoActivity.this, "未找到存储卡，无法存储照片！",
							Toast.LENGTH_LONG).show();
				}

				break;
				
			case RESULT_REQUEST_CODE:
				if (data != null) {
				    if(state.equals(Environment.MEDIA_MOUNTED)){
					getImageToView(data);}
				    else{
				    Toast.makeText(PersonInfoActivity.this, "未找到存储卡，无法存储照片！",
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
			myacc_avatar.setImageDrawable(drawable);
			Log.e("qiao ", "原图路径 " + sheariamgepath);
			if ("".equals(sheariamgepath)) {
				sheariamgepath = app.sheariamgepath;
			}
			try {
				FileOutputStream out = new FileOutputStream(sheariamgepath);
				photo.compress(Bitmap.CompressFormat.PNG, 90, out);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
