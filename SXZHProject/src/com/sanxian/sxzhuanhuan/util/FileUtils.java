package com.sanxian.sxzhuanhuan.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.entity.UserInfo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 类说明： 文件读写
 * 
 * @author @Cundong
 * @weibo http://weibo.com/liucundong
 * @blog http://www.liucundong.com
 * @date Apr 29, 2011 2:50:48 PM
 * @version 1.0
 */
public class FileUtils {

	public final static String SDCARD_MNT = "/mnt/sdcard";
	public final static String SDCARD = "/sdcard";
	private final static String company_path = "/sanxuan";
	private final static String context_path = "/sxzhproject";
	private  static String pic_path = "/pic";
	private  static String portrait_path = "/portrait";
	private final static String data_path = "/.data";
	private final static String voice = "/.voice";
//	public final static String application_name = "meeet.apk";

	/**
	 * 写文本文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
	 * 
	 * @param context
	 * @param msg
	 */
	public static void write(Context context, String fileName, String content) {
		if (content == null)
			content = "";

		try {
			FileOutputStream fos = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			fos.write(content.getBytes());

			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文本文件 文件保存在 /data/data/PACKAGE_NAME/files
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String read(Context context, String fileName) {
		try {
			FileInputStream in = context.openFileInput(fileName);
			return readInStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static FileInputStream readFileFromDataCache(String fileName,
			String folderPath) throws FileNotFoundException {
		FileInputStream input = null;
		if (!Util.isEmpty(fileName) && !Util.isEmpty(folderPath)) {
			File file = new File(folderPath + File.separator + fileName);
			input = new FileInputStream(file);
		}
		return input;
	}

	public static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}

	/**
	 * 创建文件
	 * 
	 * @param folderPath
	 * @param fileName
	 * @return
	 */
	public static File createFile(String folderPath, String fileName) {
		File destDir = new File(folderPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(folderPath, fileName + fileName);
	}

	/**
	 * 写图片
	 * 
	 * @param bitmap
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(Bitmap bitmap, String folder,
			String fileName) {

		boolean writeSucc = false;
		if (Util.isEmpty(folder) || bitmap == null || Util.isEmpty(fileName)) {
			return writeSucc;
		}
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		String folderPath = "";
		if (sdCardExist) {
			folderPath = folder + File.separator;
		} else {
			writeSucc = false;
		}

		File fileDir = new File(folderPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			out = new FileOutputStream(file);
			bufferedOutputStream = new BufferedOutputStream(out);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
					bufferedOutputStream);
			writeSucc = true;
		} catch (Exception e) {
			// Log.e("writeFile", e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
				if (bufferedOutputStream != null)
					bufferedOutputStream.close();
			} catch (IOException e) {
				Log.e("file Stream close", e.getMessage());
			}
		}

		return writeSucc;
	}

	/**
	 * 写图片
	 * 
	 * @param buffer
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(byte[] buffer, String folder,
			String fileName) {
		boolean writeSucc = false;

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		String folderPath = "";
		if (sdCardExist) {
			folderPath = folder + File.separator;
		} else {
			writeSucc = false;
		}

		File fileDir = new File(folderPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(buffer);
			writeSucc = true;
		} catch (Exception e) {
			// Log.e("writeFile", e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				Log.e("file Stream close", e.getMessage());
			}
		}

		return writeSucc;
	}

	/**
	 * 把对象写入缓存中
	 * 
	 * @param object
	 * @param fileName
	 * @param folder
	 */
	public static void write2cache(Object object, String fileName, String folder) {
		if (Util.isEmpty(folder) || object == null || Util.isEmpty(fileName)) {
			return;
		}
		String folderPath = folder + File.separator + fileName;
		File file = new File(folderPath);
		FileOutputStream out = null;
		ObjectOutputStream outputStream = null;
		try {
			out = new FileOutputStream(file);
			outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(object);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			 Log.e("write2cache", e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
//				Log.e("write2cache", e.getMessage());
			}

		}

	}

	public static Object readFromCache(String fileName, String folder) {
		Object object = null;
		if (!Util.isEmpty(folder) && !Util.isEmpty(fileName)) {
			String folderPath = folder + File.separator + fileName;
			File file = new File(folderPath);
			FileInputStream out = null;
			ObjectInputStream obi = null;
			try {
				out = new FileInputStream(file);
				obi = new ObjectInputStream(out);
				object = obi.readObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				object = null;
				// Log.e("write2cache", e.getMessage());
			} finally {
				try {
					if (out != null)
						out.close();
					if (obi != null)
						obi.close();
				} catch (IOException e) {
					Log.e("write2cache", e.getMessage());
				}

			}
		}
		return object;
	}

	/**
	 * 根据文件绝对路径获取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		if (Util.isBlank(filePath))
			return "";
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	}

	/**
	 * 根据文件的绝对路径获取文件名但不包含扩展名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameNoFormat(String filePath) {
		if (Util.isBlank(filePath)) {
			return "";
		}
		int point = filePath.lastIndexOf('.');
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
				point);
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileFormat(String fileName) {
		if (Util.isBlank(fileName))
			return "";

		int point = fileName.lastIndexOf('.');
		return fileName.substring(point + 1);
	}

	/**
	 * 获取文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		long size = 0;

		File file = new File(filePath);
		if (file != null && file.exists()) {
			size = file.length();
		}
		return size;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param size
	 *            字节
	 * @return
	 */
	public static String getFileSize(long size) {
		if (size <= 0)
			return "0";
		java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
		float temp = (float) size / 1024;
		if (temp >= 1024) {
			return df.format(temp / 1024) + "M";
		} else {
			return df.format(temp) + "K";
		}
	}

	public static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			out.write(ch);
		}
		byte buffer[] = out.toByteArray();
		out.close();
		return buffer;
	}

	/**
	 * 压缩并保存
	 * 
	 * @param imgName
	 * @return
	 */
	public static String compressAndSaveImage(String imgName) {
		String newImgName = null;
		String fileName = null;
		if (imgName != null) {
			newImgName = String.format("%s_new%s",
					imgName.substring(0, imgName.lastIndexOf(".")),
					imgName.substring(imgName.lastIndexOf(".")));
			Bitmap bitmap = null;

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeFile(imgName, options);
			// 缩略后的参数尺寸
			BitmapFactory.Options newOptions = new BitmapFactory.Options();
			if (options.outWidth < 320) {
				newOptions.inSampleSize = 1;
			} else {
				newOptions.inSampleSize = options.outWidth / 320;
			}
			bitmap = BitmapFactory.decodeFile(imgName, newOptions);
			if(bitmap == null)
			{
				return "";
			}
			bitmap = Util.getRotateBitmap(imgName, bitmap);
			
			File filePath = new File(newImgName.substring(0,
					newImgName.lastIndexOf("/")));
			fileName = newImgName.substring(newImgName.lastIndexOf("/") + 1);
			FileOutputStream fOut = null;
			File f = null;
			try {

				f = File.createTempFile(fileName, ".jpg", filePath);
				fOut = new FileOutputStream(f);
			} catch (Exception ex) {
				Log.v("compress_save", ex.getMessage());
			}
			
			boolean result = bitmap.compress(Bitmap.CompressFormat.JPEG, 50,
					fOut);
			if (result) {
				fileName = f.getAbsolutePath();
			}
			try {
				fOut.flush();
			} catch (IOException e) {
				Log.v("compress_save", e.getMessage());
				// e.printStackTrace();
			}
			try {
				fOut.close();
			} catch (IOException e) {
				Log.v("compress_save", e.getMessage());
				// e.printStackTrace();
			}
		}
		return fileName;
	}

	public static String compressAndSaveImage(String imgName, Bitmap bitmap) {
		String newImgName = null;
		String fileName = null;
		if (imgName != null) {
			newImgName = String.format("%s_new%s",
					imgName.substring(0, imgName.lastIndexOf(".")),
					imgName.substring(imgName.lastIndexOf(".")));

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			// 缩略后的参数尺寸
			BitmapFactory.Options newOptions = new BitmapFactory.Options();
			if (options.outWidth < 320) {
				newOptions.inSampleSize = 1;
			} else {
				newOptions.inSampleSize = options.outWidth / 320;
			}
			File filePath = new File(newImgName.substring(0,
					newImgName.lastIndexOf("/")));
			fileName = newImgName.substring(newImgName.lastIndexOf("/") + 1);
			FileOutputStream fOut = null;
			File f = null;
			try {

				f = File.createTempFile(fileName, ".jpg", filePath);
				fOut = new FileOutputStream(f);
			} catch (Exception ex) {
				Log.v("compress_save", ex.getMessage());
			}
			boolean result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
					fOut);
			if (result) {
				fileName = f.getAbsolutePath();
			}
			try {
				fOut.flush();
			} catch (IOException e) {
				Log.v("compress_save", e.getMessage());
				// e.printStackTrace();
			}
			try {
				fOut.close();
			} catch (IOException e) {
				Log.v("compress_save", e.getMessage());
				// e.printStackTrace();
			}
		}
		return fileName;
	}
	

	public static void createCacheFile() {
		// /先创建公司文件夹
		if(SApplication.isSee){
			pic_path="/.pic";
			portrait_path="/.portrait";
		}else{
			pic_path="/pic";
			portrait_path="/portrait";
		}
		File company = new File(getCompanyPath());
		if (!(company.exists()) && !(company.isDirectory())) {
			company.mkdir();
		}
		// //程序路径 创建创许文件夹
		File context = new File(getContextPath());
		if (!(context.exists()) && !(context.isDirectory())) {
			context.mkdir();
		}

		// ///////图像文件夹

		File images_path = new File(getImagesPath());
		if (!(images_path.exists()) && !(images_path.isDirectory())) {
			images_path.mkdir();
		}

		// ///////头像文件夹
		File portrait_path = new File(getPortraitPath());
		if (!(portrait_path.exists()) && !(portrait_path.isDirectory())) {
			portrait_path.mkdir();
		}

		// ///////数据文件夹
		File data_path = new File(getDataPath());
		if (!(data_path.exists()) && !(data_path.isDirectory())) {
			data_path.mkdir();
		}

		// ///////音频文件夹
		File voice_path = new File(getVideoPath());
		if (!(voice_path.exists()) && !(voice_path.isDirectory())) {
			voice_path.mkdir();
		}
	}

	/**
	 * 获取相册使用的目录
	 * 
	 * @return
	 */
	public static String getImagesPath() {
		try {
			if(SApplication.isSee){
				pic_path="/.pic";
				portrait_path="/.portrait";
			}else{
				pic_path="/pic";
				portrait_path="/portrait";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path = getContextPath() + pic_path;
		File file = new File(path);
		if (!(file.exists()) && !(file.isDirectory())) {
			file.mkdir();
		}
		return path;
	}

	/**
	 * 获取头像文件夹路径
	 * 
	 * @return
	 */
	public static String getPortraitPath() {
		if(SApplication.isSee){
			pic_path="/.pic";
			portrait_path="/.portrait";
		}else{
			pic_path="/pic";
			portrait_path="/portrait";
		}
		String path = getContextPath() + portrait_path;
		File file = new File(path);
		if (!(file.exists()) && !(file.isDirectory())) {
			file.mkdir();
		}
		return path;
	}

	/**
	 * 获取音频文件夹路径
	 * 
	 * @return
	 */
	/*
	 * public static String getVoicePath() { return
	 * Environment.getExternalStorageDirectory() + company_path + context_path +
	 * voice; }
	 */
	/**
	 * 获取数据文件夹路径
	 * 
	 * @return
	 */
	public static String getDataPath() {
		String path = getContextPath() + data_path;
		File file = new File(path);
		if (!(file.exists()) && !(file.isDirectory())) {
			file.mkdir();
		}
		return path;
	}

	/**
	 * 获取音频文件夹路径
	 * 
	 * @return
	 */
	public static String getVideoPath() {
		String path = getContextPath() + voice;
		File file = new File(path);
		if (!(file.exists()) && !(file.isDirectory())) {
			file.mkdir();
		}
		return path;
	}

	/**
	 * 公司文件路径
	 * 
	 * @return
	 */
	public static String getCompanyPath() {
		String path = Environment.getExternalStorageDirectory() + company_path;
		File file = new File(path);
		if (!(file.exists()) && !(file.isDirectory())) {
			file.mkdir();
		}
		return path;
	}

	/**
	 * 程序文件路径
	 * 
	 * @return
	 */
	public static String getContextPath() {
		
		String path = getCompanyPath() + context_path;
		File file = new File(path);
		if (!(file.exists()) && !(file.isDirectory())) {
			file.mkdir();
		}
		return path;
	}

	/**
	 * 保存至手机内存中
	 * @param app
	 * @param filename
	 * @param object
	 * @return
	 */
	public static void saveDataToCache(SApplication app, String filename,
			Object object) {
		if (app != null /* && object != null */) {
			//filename += app.getLoginUserInfo().getUid();
			filename = MD5Util.MD5(filename);
			try {
				FileOutputStream ops = app.openFileOutput(filename,
						Context.MODE_PRIVATE);
				ObjectOutputStream outputStream = null;
				outputStream = new ObjectOutputStream(ops);
				outputStream.writeObject(object);
				outputStream.flush();
				outputStream.close();
			}catch (Exception e) {
				Log.w("saveDataToCache file is :" + filename, e.toString());
			}
		}
	}
	public static void deleteFile(SApplication app,String filename){
		if(filename!=null){
			//filename += app.getLoginUserInfo().getUid();
			filename = MD5Util.MD5(filename);
			File file=new File(filename);
			if(file.exists()){
				file.delete();
			}
		}
	}
	/**
	 * 保存数据到缓存中  吴超凡
	 * @param app
	 * @param filename
	 * @param object
	 */
	public static void saveDataToCache1(SApplication app, String filename,Object object) {
		if (app != null && object != null) {
			//filename += app.getLoginUserInfo().getUid();
			filename = MD5Util.MD5(filename);
			File file=new File(filename);
			try {
				if(file.exists()){
					file.delete();
					}
				FileOutputStream ops = app.openFileOutput(filename,
						Context.MODE_PRIVATE);
				ObjectOutputStream outputStream = null;
				outputStream = new ObjectOutputStream(ops);
				outputStream.writeObject(object);
				outputStream.flush();
				outputStream.close();
			}catch (Exception e) {
				Log.w("saveDataToCache file is :" + filename, e.toString());
			}
		}
	}
	/**
	 * 读取缓存  
	 * @param app
	 * @param filename
	 * @return
	 */
	public static Object getDataFromCache(SApplication app, String filename) {
		Object obj = null;
		if (app != null ) {
			//filename += app.getLoginUserInfo().getUid();
			filename = MD5Util.MD5(filename);
			try {
				InputStream ies = app.openFileInput(filename);
				ObjectInputStream obi = new ObjectInputStream(ies);
				obj = obi.readObject();
				obi.close();
				ies.close();
			} catch (Exception e) {
				Log.w("getDataFromCache file is :" + filename, e.toString());
			}
		}
		return obj;
	}
	
	/**
	 * 保存用户数据
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveuserInfo(Context context, UserInfo user) {

		String filename = "userinfo";
		filename = MD5Util.MD5(filename);
		try {
			HashMap<String, Object> parm = new HashMap<String, Object>();
			parm.put("uu", user);
			FileOutputStream ops = context.openFileOutput(filename,
					Context.MODE_PRIVATE);
			ObjectOutputStream outputStream = null;
			outputStream = new ObjectOutputStream(ops);
			outputStream.writeObject(parm);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读取用户数据 
	 * @param context
	 * @return
	 */
	public static UserInfo readuserInfo(Context context) {

		String filename = "userinfo";
		filename = MD5Util.MD5(filename);
		try {
			InputStream ies = context.openFileInput(filename);
			ObjectInputStream obi = new ObjectInputStream(ies);
			// UserEntity tmpuser = (UserEntity)obi.readObject();
			Object obj = obi.readObject();
			obi.close();
			ies.close();
			if (obj != null) {
				if (obj instanceof HashMap<?, ?>) {
					@SuppressWarnings("unchecked")
					HashMap<String, Object> parm = (HashMap<String, Object>) obj;
					UserInfo tmpuser = (UserInfo) parm.get("uu");
					return tmpuser;
				}
			}
		} catch (Exception e) {
			UserInfo temp = new UserInfo();
			temp.setUid("0");
//			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 通过uri获取文件的绝对路径	
	 * @param uri
	 * @return
	 */
	public static String getAbsoluteImagePath(Context context, Uri uri) {
		String imagePath = "";
		String[] proj = { MediaStore.Images.Media.DATA };
		Activity activity = (Activity) context;
		Cursor cursor = activity.managedQuery(uri, proj, // Which columns to return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		if (cursor != null) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			if (cursor.getCount() > 0 && cursor.moveToFirst()) {
				imagePath = cursor.getString(column_index);
			}
		}
		return imagePath;
	}

	/**
	 * 递归删除文件和文件夹
	 * @param file
	 *要删除的根目录
	 */
	public static void RecursionDeleteFile(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				// file.delete();
				return;
			}
			for (File f : childFile) {
				RecursionDeleteFile(f);
			}
			file.delete();
		}
	}
}