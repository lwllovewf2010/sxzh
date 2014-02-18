package com.sanxian.sxzhuanhuan.function.homeindex;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.CommonAPI;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.FootDialog;
import com.sanxian.sxzhuanhuan.dialog.FootDialogInfo;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.function.personal.myaccount.PersonInfoActivity;
import com.sanxian.sxzhuanhuan.util.ImageUtil;
import com.sanxian.sxzhuanhuan.util.Util;
import com.sanxian.sxzhuanhuan.view.HorizontalListViews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 发布需求
 * 
 * @author joe
 * 
 */
public class PublishOriginality extends BaseActivity implements OnClickListener {

	private EditText originalityTitle, originalityIntroduce, originalityNetaddress, originalityDetailedIntroduce;
	private TextView originalityType, originalityEara, originalityProfession;
	// private ImageView originalityImage;
	private RelativeLayout originalityTypeRel, originalityAreaRel, originalityProfessionRel;

	private static final int SHOW_TYPE = 1;
	private static final int SHOW_AREA = 2;
	private static final int SHOW_PROFESSION = 3;

	private final int FROM_PUBLI_ROOT_CODE = 112;
	private final int FROM_PUBLI_SUB_CODE = 118;
	private final int REQUESTCODE = 113;
	private String mode_id = "";
	private String mode_name = "";
	private String category_id = "";
	private String category_name = "";
	private String area_root_id = "";
	private String area_root_name = "";
	private String area_sub_id = "";
	private String area_sub_name = "";

	private CharSequence[] testType = { "创意", "项目" };
	private CharSequence[] testArea = { "北京", "上海", "深圳" };
	private CharSequence[] testProfession = { "互联网", "科技" };

	private Button submit;
	private Button cancel_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_originality);
		init();
		initListener();
		bindAdapter();
	}

	@Override
	protected Dialog onCreateDialog(int id, final Bundle bundle) {
		switch (id) {
		case SHOW_TYPE:

			return new AlertDialog.Builder(PublishOriginality.this).setItems(testType, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					originalityType.setText(testType[which].toString());
				}

			}).create();

		case SHOW_AREA:
			return new AlertDialog.Builder(PublishOriginality.this).setItems(testArea, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					originalityEara.setText(testArea[which]);
				}

			}).create();
		case SHOW_PROFESSION:
			return new AlertDialog.Builder(PublishOriginality.this).setItems(testProfession, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					originalityProfession.setText(testProfession[which]);
				}

			}).create();

		default:
			return null;
		}

	}

	/**
	 * 初始化数据
	 */
	private void init() {
		initView();
		setLeft("取消");
		// setRight("下一步");
		displayRight();
		setTitle("需求发布");
		TextView titleIndo = (TextView) findViewById(R.id.tvTitleIndo);
		titleIndo.setText("第一步/共三步");
		originalityTitle = (EditText) findViewById(R.id.publish_originality_originality_title); // 需求标题
		originalityIntroduce = (EditText) findViewById(R.id.publish_originality_originality_introduce); // 需求简介
		originalityNetaddress = (EditText) findViewById(R.id.publish_originality_originality_netaddress); // 需求视频播放地址
		originalityDetailedIntroduce = (EditText) findViewById(R.id.publish_originality_originality_detailed_introduce); // 需求详细介绍
		// originalityType = (TextView)
		// findViewById(R.id.publish_originality_type); // 发布类型
		originalityEara = (TextView) findViewById(R.id.publish_originality_eara); // 发布地区
		originalityProfession = (TextView) findViewById(R.id.publish_originality_profession); // 发布行业
		// originalityImage=(ImageView)findViewById(R.id.publish_originality_image_1);
		// //添加需求图片
		// originalityTypeRel = (RelativeLayout)
		// findViewById(R.id.publish_originality_originality_type_rel); // 类型
		originalityAreaRel = (RelativeLayout) findViewById(R.id.publish_originality_originality_area_rel); // 地区
		originalityProfessionRel = (RelativeLayout) findViewById(R.id.publish_originality_originality_profession_rel); // 行业

		HListView = (HorizontalListViews) this.findViewById(R.id.HlistView);

		submit = (Button) this.findViewById(R.id.submit);
		cancel_submit = (Button) this.findViewById(R.id.cancel_submit);

	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		// originalityTypeRel.setOnClickListener(this);
		originalityAreaRel.setOnClickListener(this);
		originalityProfessionRel.setOnClickListener(this);
		button_left.setOnClickListener(this);
		button_right.setOnClickListener(this);

		submit.setOnClickListener(this);
		cancel_submit.setOnClickListener(this);
	}

	public boolean checkInput() {
		if ("".equals(originalityTitle.getText().toString().trim())) {
			Util.toastInfo(this, "需求名字必填");
			originalityTitle.setFocusable(true);
			return false;
		} else if ("".equals(originalityDetailedIntroduce.getText().toString().trim())) {
			Util.toastInfo(this, "详细介绍必填");
			originalityDetailedIntroduce.setFocusable(true);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// // 类型
		// case R.id.publish_originality_originality_type_rel:
		// // removeDialog(SHOW_TYPE);
		// // showDialog(SHOW_TYPE, null);
		// Intent typeintent = new Intent(PublishOriginality.this,
		// GetRootCommonList.class);
		// typeintent.putExtra("type", "mode");
		// typeintent.putExtra("is_get_last", "0");
		// startActivityForResult(typeintent, REQUESTCODE);
		// break;
		// 地区
		case R.id.publish_originality_originality_area_rel:
			// removeDialog(SHOW_AREA);
			// showDialog(SHOW_AREA, null);
			Intent areaintent = new Intent(PublishOriginality.this, GetRootCommonList.class);
			areaintent.putExtra("type", "region_province");
			areaintent.putExtra("is_get_last", "0");
			startActivityForResult(areaintent, REQUESTCODE);
			break;
		// 行业
		case R.id.publish_originality_originality_profession_rel:
			// removeDialog(SHOW_PROFESSION);
			// showDialog(SHOW_PROFESSION, null);
			Intent categoryintent = new Intent(PublishOriginality.this, GetRootCommonList.class);
			categoryintent.putExtra("type", "category");
			categoryintent.putExtra("is_get_last", "0");
			startActivityForResult(categoryintent, REQUESTCODE);
			break;
		case R.id.title_right:

			break;
		case R.id.submit:
			// 此处还需判断是否为空
			if (checkInput()) {
				getMap();
				Intent intent = new Intent(this, PublishProjectReport.class);
				startActivity(intent);
				finish();

			}
			break;
		case R.id.cancel_submit:
			app.getImagelist().clear();
			this.finish();
			break;
		case R.id.title_Left:
			app.getImagelist().clear();
			this.finish();
			break;
		default:
			break;
		}

	}

	String tempImages = "";

	public Map<String, String> getMap() {
		PublishConstant.paramMap.clear();
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("open_id", getOpen_idOrToken()[0]);
		tempMap.put("project_name", originalityTitle.getText().toString());
		tempMap.put("mode_id", "1");
		tempMap.put("province_id", area_root_id);
		tempMap.put("city_id", area_sub_id);
		tempMap.put("category_id", category_id);
		tempMap.put("project_explain", originalityIntroduce.getText().toString());
		tempMap.put("project_logo", "");
		tempMap.put("project_video", "");
		tempMap.put("project_describe", originalityDetailedIntroduce.getText().toString());
		// 项目图片（获取）
		// for (int i = 0; i < list.size(); i++) {
		// HashMap<String, Object> map=list.get(i);
		// tempImages+=map.get("images")+",";
		//
		// }
		// tempMap.put("project_logo", tempImages);

		PublishConstant.paramMap.putAll(tempMap);
		return PublishConstant.paramMap;

	}

	@Override
	public void refresh(Object... param) {

	}

	private ArrayList<String> list;
	private PublishOriginalityImageAdapter adapter;
	private com.sanxian.sxzhuanhuan.view.HorizontalListViews HListView;

	public void reMoveImage(int position) {

		list.remove(position);
		adapter.notifyDataSetChanged();
		Log.e("honaf--app.getimagesize", app.getImagelist().size() + "");

	}

	private void bindAdapter() {

		list = new ArrayList<String>();
		adapter = new PublishOriginalityImageAdapter(this);
		HListView.setAdapter(adapter);
	}

	// 弹出底部菜单（待续）
	public void ShowAddImageDilog() {
		FootDialogInfo info = new FootDialogInfo(new String[] { "拍照", "相册提取", "取消" });
		Intent intent = new Intent(this, FootDialog.class);
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_FOOT);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == FROM_PUBLI_ROOT_CODE) {
			Bundle bundle = data.getExtras();
			String type = bundle.getString("type");
			if ("mode".equals(type)) {
				mode_id = bundle.getString("root_id");
				mode_name = bundle.getString("root_name");
				originalityType.setText(mode_name);
			} else if ("category".equals(type)) {
				category_id = bundle.getString("root_id");
				category_name = bundle.getString("root_name");
				originalityProfession.setText(category_name);
			}

		} else if (resultCode == FROM_PUBLI_SUB_CODE) {
			Bundle bundle = data.getExtras();
			String type = bundle.getString("type");
			if ("region_city".equals(type)) {
				area_root_id = bundle.getString("root_id");
				area_root_name = bundle.getString("root_name");
				area_sub_id = bundle.getString("sub_id");
				area_sub_name = bundle.getString("sub_name");
				originalityEara.setText(area_root_name + " " + area_sub_name);
			} else if ("category".equals(type)) {
				category_id = bundle.getString("sub_id");
				category_name = bundle.getString("sub_name");
				originalityProfession.setText(bundle.getString("root_name") + " " + category_name);
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
					bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
					String[] proj = { MediaStore.Images.Media.DATA };
					Cursor cursor = managedQuery(originalUri, proj, null, null, null);
					int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path = cursor.getString(column_index);
					// 压缩大小
					sheariamgepath = avatorpath + "sximage" + new Date().getTime() + ".jpg";
					app.sheariamgepath = sheariamgepath;
					ImageUtil.getimage(path, sheariamgepath, avatorpath);

				} catch (Exception e) {
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
					sheariamgepath = avatorpath + "sximage" + new Date().getTime() + ".jpg";
					app.sheariamgepath = sheariamgepath;
					ImageUtil.getimage(photographpath, sheariamgepath, avatorpath);

				} else {
					Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
				}

				break;

			case RESULT_REQUEST_CODE:
				if (data != null) {
					if (state.equals(Environment.MEDIA_MOUNTED)) {
						getImageToView(data);
					} else {
						Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
					}
				}
				break;
			}

		}
		if (requestCode == DialogConstant.REQUEST_FOOT) {
			if (resultCode == DialogConstant.FOOTDIALOG_ONE) {
				getPhotoByCamere();
			} else if (resultCode == DialogConstant.FOOTDIALOG_TWO) {
				getPhotoFromAlum();
			} else if (resultCode == DialogConstant.FOOTDIALOG_THREE) {
			}
		}

	}

	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 6;
	private static final int CAMERA_REQUEST_CODE = 7;
	private static final int RESULT_REQUEST_CODE = 8;

	private final String avatorpath = Environment.getExternalStorageDirectory() + "/sanxian/sxzhproject/avator/";
	private String sheariamgepath = ""; // 裁剪后头像路径
	private String photographpath = ""; // 拍照完后头像路径

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
			photographpath = avatorpath + "sximage" + new Date().getTime() + ".jpg";
			app.photographpath = photographpath;
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photographpath)));
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
			// myacc_avatar.setImageDrawable(drawable);
			Log.e("qiao ", "原图路径 " + sheariamgepath);
			if ("".equals(sheariamgepath)) {
				sheariamgepath = app.sheariamgepath;
			}

			try {

				FileOutputStream out = new FileOutputStream(sheariamgepath);
				photo.compress(Bitmap.CompressFormat.PNG, 90, out);
				app.getImagelist().add(sheariamgepath);
				list = (ArrayList<String>) app.getImagelist();
				adapter.notifyDataSetChanged();
				// api.uploadAvatarForProject(new HashMap<String, String>(), new
				// File(sheariamgepath), this, UPLOADAVATAR);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	class PublishOriginalityImageAdapter extends BaseAdapter {

		private PublishOriginality bindActivity;

		public PublishOriginalityImageAdapter(PublishOriginality bindActivity) {
			this.bindActivity = bindActivity;
		}

		@Override
		public int getCount() {
			int count = 0;
			if (list != null) {
				if (list.size() < 5) {
					count = list.size() + 1;
				} else {
					count = list.size();
				}
			}
			return count;
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup group) {
			Holder holder = null;
			if (view == null) {
				holder = new Holder();
				view = LayoutInflater.from(bindActivity.getApplicationContext()).inflate(R.layout.publish_images_item, null);
				holder.add_image = (RelativeLayout) view.findViewById(R.id.add_image);
				holder.imageview = (ImageView) view.findViewById(R.id.images);
				holder.delet = (TextView) view.findViewById(R.id.delet);
				holder.note_text = (TextView) view.findViewById(R.id.note_text);
				holder.mask = (TextView) view.findViewById(R.id.mask);
				view.setTag(holder);
			} else {
				holder = (Holder) view.getTag();
			}
			if (list.size() == 0) {
				holder.note_text.setVisibility(View.VISIBLE);
				holder.delet.setVisibility(View.GONE);
			} else if (list.size() != 0 && position == list.size()) {
				holder.note_text.setVisibility(View.GONE);
				holder.delet.setVisibility(View.GONE);
			} else {
				holder.note_text.setVisibility(View.GONE);
				holder.delet.setVisibility(View.VISIBLE);
			}
			if (position == 0) {
				holder.mask.setVisibility(View.VISIBLE);
			} else {
				holder.mask.setVisibility(View.GONE);
			}
			if (position != list.size() && list.size() != 0) {

				holder.imageview.setImageBitmap(getLocalPictureBitmap(list.get(position)));

			}

			AddListener(position, holder.add_image, holder.delet);
			return view;
		}

		private void AddListener(final int position, RelativeLayout add_image, TextView deleTextView) {
			deleTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					bindActivity.reMoveImage(position);
					// if(app.getImagelist().size()>position){
					// app.getImagelist().remove(position);
					// }

				}
			});
			if (list.size() == 0 || (list.size() != 0 && position == list.size())) {
				add_image.setOnClickListener(new RelativeLayout.OnClickListener() {

					@Override
					public void onClick(View v) {
						bindActivity.ShowAddImageDilog();
					}
				});
			}
		}

		// 读取本地图片
		private Bitmap getLocalPictureBitmap(String pathString) {
			Bitmap bitmap = null;
			try {
				File file = new File(pathString);
				if (file.exists()) {
					bitmap = BitmapFactory.decodeFile(pathString);
				}
			} catch (Exception e) {
			}

			return bitmap;
		}

		class Holder {
			RelativeLayout add_image;
			ImageView imageview;
			TextView delet;
			TextView note_text;
			TextView mask;
		}

	}

}
