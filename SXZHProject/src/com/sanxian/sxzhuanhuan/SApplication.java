package com.sanxian.sxzhuanhuan;

import java.io.File;
import java.util.ArrayList;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;

/**
 * 应用程序全局
 *
 */
public class SApplication extends Application {
	public static Context context;
	public NotificationManager notifyManager;
	public static ActivityManager activityManager;
	public static boolean isSee = true; // 是否保存到手机功能(此项目暂时不用)
	public static Bitmap default_ico = null;
	public boolean isActive = true;
	public static String photographpath = "";//头像上传拍照保存路径
	public static String sheariamgepath = "";//头像裁剪拍照保存路径
	public static SApplication instance;

	// /注册获取验证码的冷却事件
	private int codeSeconds = 0;

	// 将当前的activity加到Service中方便管理和调用
	public ArrayList<Activity> allActivity = new ArrayList<Activity>();


	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		// 通过隐式意图启动Service
		SApplication.context = this.getApplicationContext();
		notifyManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		SApplication.activityManager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		initImageLoader(instance);
	}

	
	public static void initImageLoader(Context context) {

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
		ImageLoader.getInstance().init(config);
	}
	
	public static SApplication getInstance() {
		return instance;
	}
	
	public NotificationManager getNotifyManager() {
		return notifyManager;
	}

	public void setNotifyManager(NotificationManager notifyManager) {
		this.notifyManager = notifyManager;
	}

	public int getCodeSeconds() {
		return codeSeconds;
	}

	public void setCodeSeconds(int codeSeconds) {
		this.codeSeconds = codeSeconds;
	}

	public void onLowMemory() {

	}

	public ArrayList<Activity> getAllActivity() {
		return allActivity;
	}

	public void setAllActivity(ArrayList<Activity> allActivity) {
		this.allActivity = allActivity;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	// 遍历所有activity 根据名称在 allActivity 中找到需要的activity
	public Activity getActivityByName(String name) {
		for (Activity ac : allActivity) {
			String ssString = ac.getClass().getName();
			if (ac.getClass().getName().indexOf(name) >= 0) {
				// Log.i("status", ACTIVITY_SERVICE.getClass().getName()
				// .toString());
				return ac;
			}
		}
		return null;
	}

	public boolean existSDcard() {
		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState())) {
			return true;
		} else
			return false;
	}

	/**
	 * 返回/data目录的大小。
	 */
	private long getDataDirectorySize() {
		File tmpFile = Environment.getDataDirectory();
		if (tmpFile == null) {
			return 0l;
		}
		String strDataDirectoryPath = tmpFile.getPath();
		StatFs localStatFs = new StatFs(strDataDirectoryPath);
		long size = localStatFs.getBlockSize() * localStatFs.getBlockCount();
		return size;
	}
}
