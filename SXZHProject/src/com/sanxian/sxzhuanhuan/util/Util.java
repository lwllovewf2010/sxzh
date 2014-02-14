package com.sanxian.sxzhuanhuan.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.entity.RequestInputInfo;

/**
 * 通用工具方法类
 * 
 */
public class Util {

	// private final static MCipher phoneCipher = new
	// MCipher(MeeetDataIF.PHO_KEY);
	// private final static MCipher nameCipher = new
	// MCipher(MeeetDataIF.NAME_key);

	/**
	 * 把数值型字符串转换为日期型字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timeTran(String time) {
		String date = "";
		if (time != null && time.length() == 14) {
			String year = time.substring(0, 4);
			String mouth = time.substring(4, 6);
			String day = time.substring(6, 8);
			String hour = time.substring(8, 10);
			String minute = time.substring(10, 12);
			String second = time.substring(12, 14);
			date = String.format("%s-%s-%s %s:%s:%s", year, mouth, day, hour, minute, second);
		}
		return date;
	}

	/**
	 * 把日期型字符串转换为数字型字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String tranTime(String time) {
		if (time != null) {
			String year;
			String month;
			String day;
			String hour;
			String minute;
			String second;
			year = time.substring(0, 4);
			month = time.substring(5, 7);
			day = time.substring(8, 10);
			hour = time.substring(11, 13);
			minute = time.substring(14, 16);
			second = time.substring(17, 19);
			String newTime = String.format("%s%s%s%s%s%s", year, month, day, hour, minute, second);
			return newTime;
		} else
			return time;
	}

	public static String timeToDay(String timeStr) {
		Calendar cal = Calendar.getInstance();
		String dayStart = String.format("%04d%02d%02d000000", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
		String nextDayStart = String.format("%04d%02d%02d000000", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 2, cal.get(Calendar.DATE));
		String lastYearEnd = String.format("%04d1231606060", cal.get(Calendar.YEAR) - 1);
		String strText = "";
		if (timeStr != null) {
			if (timeStr.compareTo(dayStart) >= 0 && timeStr.compareTo(nextDayStart) < 0) {
				strText = "今天  " + timeStr.substring(8, 10) + ":" + timeStr.substring(10, 12);
			} else {
				if (timeStr.compareTo(lastYearEnd) >= 0) {
					strText = timeStr.substring(4, 6) + "-" + timeStr.substring(6, 8) + " " + timeStr.substring(8, 10) + ":" + timeStr.substring(10, 12);
				} else {
					strText = timeStr.substring(0, 4) + "-" + timeStr.substring(4, 6) + "-" + timeStr.substring(6, 8) + " " + timeStr.substring(8, 10) + ":" + timeStr.substring(10, 12);
				}
			}
		}
		return strText;
	}

	/**
	 * 转换获取指定中、小的图片连接
	 * 
	 * @param url
	 * @param type
	 * @return
	 */
	public static String getUrlStrImgType(String url, String type) {
		String newUrl = url;
		if (!Util.isEmpty(url)) {
			int index = newUrl.indexOf(".jpg");
			if (index != -1) {
				newUrl = newUrl.substring(0, index) + "_" + type + newUrl.substring(index);
			}
		}
		return newUrl;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return (String.valueOf(o).trim().length() == 0);
		else if (o instanceof List)
			return ((List) o).isEmpty();
		else if (o instanceof Map)
			return ((Map) o).isEmpty();
		else
			return false;
	}

	/**
	 * 判断当前网络是否可用
	 * 
	 * @param act
	 * @return
	 */
	public static boolean checkNet(Activity act) {
		ConnectivityManager manager = (ConnectivityManager) act.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/**
	 * 加密密码
	 * 
	 * @param str
	 * @return
	 */
	/*
	 * public String encode(String str) { String result = null; try { String key
	 * = Configuration.getProperty("meeet.encry_pwd_key"); MCipher cipher = new
	 * MCipher(key);
	 * 
	 * byte[] bytes = str.getBytes(); MessageDigest md5 =
	 * MessageDigest.getInstance("MD5");
	 * 
	 * bytes = md5.digest(bytes); result = this.bytes2Hex(bytes); result =
	 * result.substring(8, 24); bytes = result.getBytes();
	 * 
	 * bytes = cipher.encrypt(bytes); bytes = new Base64().encode(bytes); result
	 * = new String(bytes); result = result.trim(); //
	 * result=java.net.URLEncoder.encode(result, "utf-8"); // byte[] encrypted1
	 * = new Base64().decode(result); // byte[] dePwd =
	 * cipher.decrypt(encrypted1); // String password = new String(dePwd,
	 * "UTF-8"); // System.out.println(password); } catch (Exception e) {//
	 * 此异常通常不会抛出 e.printStackTrace();
	 * 
	 * } return result; }
	 * 
	 * private String bytes2Hex(byte[] bts) { String des = ""; String tmp =
	 * null;
	 * 
	 * for (int i = 0; i < bts.length; i++) { tmp = (Integer.toHexString(bts[i]
	 * & 0xFF)); if (tmp.length() == 1) { des += "0"; } des += tmp; } return
	 * des; }
	 */
	/**
	 * 判断给定字符串是否空白串。<br>
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串<br>
	 * 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */

	public static boolean isBlank(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	// /判断是否为数字
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 解密手机号码
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */

	/*
	 * public static String decodePhone(String phone) throws Exception {
	 * 
	 * byte[] encrypted1 = new Base64().decode(phone); byte[] dePwd =
	 * phoneCipher.decrypt(encrypted1); String phoStr = new String(dePwd,
	 * "UTF-8"); return phoStr; }
	 */
	/**
	 * 加密手机号码
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static String encodePhone(String phone) throws Exception { byte[]
	 * byteContent = phone.getBytes("utf-8"); byte[] result =
	 * phoneCipher.encrypt(byteContent); phone = new String(new
	 * Base64().encode(result)); return phone; }
	 */

	/**
	 * 加密名字
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static String encodeName(String name) throws Exception { byte[]
	 * byteContent = name.getBytes("utf-8"); byte[] result =
	 * nameCipher.encrypt(byteContent); name = new String(new
	 * Base64().encode(result)); return name; }
	 */

	/**
	 * 使用当前时间戳拼接一个唯一的文件名
	 * 
	 * @param format
	 * @return
	 */
	public static String getFileName() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS");
		String fileName = format.format(new Timestamp(System.currentTimeMillis()));
		return fileName;
	}

	/**
	 * 根据路径获取适应屏幕大小的Bitmap 16倍原图大小
	 * 
	 * @param context
	 * @param filePath
	 * @param maxWidth
	 * @return
	 */
	public static Bitmap getScaleBitmap(Context context, String filePath) {
		if (Util.isEmpty(filePath)) {
			return null;
		}
		/*
		 * BitmapFactory.Options opts = new BitmapFactory.Options();
		 * opts.inSampleSize = 4; return BitmapFactory.decodeFile(filePath,
		 * opts);
		 */

		// BitmapFactory.Options opts = new BitmapFactory.Options();
		// opts.inSampleSize = 0;
		Bitmap realImage = BitmapFactory.decodeFile(filePath);
		// Bitmap realImage = createImageThumbnail(filePath);
		return Util.getRotateBitmap(filePath, realImage);
		/*
		 * ExifInterface exif=null; try { exif = new ExifInterface(filePath); }
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } if(exif!=null){ Log.d("nilai exif ",
		 * exif.getAttribute(ExifInterface.TAG_ORIENTATION));
		 * if(exif.getAttribute
		 * (ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")){
		 * 
		 * realImage=MeeetUtil.rotate(realImage, 90); }else
		 * if(exif.getAttribute(
		 * ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")){
		 * realImage=MeeetUtil.rotate(realImage, 270); }else
		 * if(exif.getAttribute
		 * (ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")){
		 * realImage=MeeetUtil.rotate(realImage, 180); }
		 * 
		 * return realImage; }
		 * 
		 * return null;
		 */
	}

	public static Bitmap getRotateBitmap(String filePath, Bitmap readImage) {

		Bitmap readimage = readImage;

		ExifInterface exif = null;
		try {
			exif = new ExifInterface(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (exif != null) {
			Log.d("nilai exif ", exif.getAttribute(ExifInterface.TAG_ORIENTATION));
			if (exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")) {

				readimage = Util.rotate(readimage, 90);
			} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")) {
				readimage = Util.rotate(readimage, 270);
			} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")) {
				readimage = Util.rotate(readimage, 180);
			}

			return readimage;
		}

		return null;
	}

	/**
	 * 创建缩略图
	 * 
	 * @param filePath
	 * @return
	 */

	public static Bitmap createImageThumbnail(String filePath) {
		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);

		opts.inSampleSize = computeSampleSize(opts, -1, 800 * 600);
		Log.e("inSampleSize", opts.inSampleSize + "");
		Log.e("width&height", opts.outWidth + "  " + opts.outHeight);
		opts.inJustDecodeBounds = false;

		try {
			bitmap = BitmapFactory.decodeFile(filePath, opts);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bitmap;
	}

	public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	public static Bitmap rotate(Bitmap bitmap, int degree) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		Matrix mtx = new Matrix();
		mtx.postRotate(degree);

		return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
	}

	/**
	 * 判断当前Url是否标准的content://样式，如果不是，则返回绝对路径
	 * 
	 * @param uri
	 * @return
	 */
	public static String getAbsolutePathFromNoStandardUri(Uri mUri) {
		String filePath = null;

		String mUriString = mUri.toString();
		mUriString = Uri.decode(mUriString);

		String pre1 = "file://" + FileUtils.SDCARD + File.separator;
		String pre2 = "file://" + FileUtils.SDCARD_MNT + File.separator;

		if (mUriString.startsWith(pre1)) {
			filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + mUriString.substring(pre1.length());
		} else if (mUriString.startsWith(pre2)) {
			filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + mUriString.substring(pre2.length());
		}
		return filePath;
	}

	/**
	 * 获得位置
	 * 
	 * @param context
	 * @return
	 */
	// Get the Location by GPS or WIFI

	/*
	 * public static Location getLocation(Context context) {
	 * 
	 * try{ LocationManager locMan = (LocationManager) context
	 * .getSystemService(Context.LOCATION_SERVICE); Location location = locMan
	 * .getLastKnownLocation(LocationManager.GPS_PROVIDER); if (location ==
	 * null) { location = locMan
	 * .getLastKnownLocation(LocationManager.NETWORK_PROVIDER); }
	 * CApplication.location = location; return location; }catch(Exception e){
	 * Log.w("getlocation", e.toString()); }
	 * 
	 * return null; }
	 */
	/**
	 * 把单个英文字母或者字符串转换成数字ASCII码
	 * 
	 * @param input
	 * @return
	 */
	public static int character2ASCII(String input) {
		char[] temp = input.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (char each : temp) {
			builder.append((int) each);
		}
		String result = builder.toString();
		return Integer.parseInt(result);
	}

	// /////////中文转换成拼音///////////
	public static String converterToPinYin(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		if (chinese == null)
			return "";

		// 取出中文标点符号，以防转换错误

		String pinyinString = "";
		chinese = chinese.replaceAll("\\p{P}", "");
		char[] charArray = chinese.toCharArray();
		// 根据需要定制输出格式，我用默认的即可
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// 遍历数组，ASC码大于128进行转换
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] > 128) {
				// charAt(0)取出首字母

				String[] strarray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
				if (strarray != null && strarray.length > 0) {
					String tmpstr = strarray[0];
					if (tmpstr != null && tmpstr.length() > 0)
						pinyinString += tmpstr.charAt(0);
				}
				/*
				 * try{ pinyinString += PinyinHelper.toHanyuPinyinStringArray(
				 * charArray[i], defaultFormat)[0].charAt(0); }catch (Exception
				 * e) { Log.v("converterToPinYinException", e.toString()); }
				 */
			} else {
				pinyinString += charArray[i];
				pinyinString = pinyinString.toLowerCase();
			}
		}
		return pinyinString;
	}

	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			Log.e("getVerCode", e.getMessage());
		}
		return verCode;
	}

	public static String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			Log.e("getVerCode", e.getMessage());
		}
		return verName;
	}

	public static Bitmap createRepeater(int width, Bitmap src) {
		int count = (width + src.getWidth() - 1) / src.getWidth();

		Bitmap bitmap = Bitmap.createBitmap(width, src.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		for (int idx = 0; idx < count; ++idx) {
			canvas.drawBitmap(src, idx * src.getWidth(), 0, null);
		}

		return bitmap;
	}

	/**
	 * 提示消息框的提取
	 * 
	 * @param activity
	 * @param 提示消息字符串
	 */
	public static void toastInfo(Context context, String string) {
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0); // 设置居中与屏幕
		toast.show();
	}

	/**
	 * 通用input JSON字符串 joe
	 * 
	 * @param inputinfo
	 *            请求参数对象
	 * @return
	 */
	public static String toJSONObject(RequestInputInfo inputinfo) {
		JSONObject json = new JSONObject();
		try {
			json.put("action", inputinfo.getAction());
			json.put("type", inputinfo.getType());
			json.put("mcr", inputinfo.getMcr());
			Map<String, String> params = inputinfo.getParams();
			if (params != null && params.size() > 0) {
				JSONObject jsonItems = new JSONObject();
				for (String key : params.keySet()) {
					jsonItems.put(key, params.get(key));
				}

				json.put("params", jsonItems);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	public static String toJSONObject(RequestInputInfo inputinfo, SApplication app) {
		JSONObject json = new JSONObject();
		try {
			json.put("action", inputinfo.getAction());
			json.put("type", inputinfo.getType());
			json.put("mcr", inputinfo.getMcr());
			Map<String, String> params = inputinfo.getParams();
			JSONObject jsonItems = new JSONObject();
			if (app.getOpen_idOrToken() != null && app.getOpen_idOrToken().length != 0) {
				jsonItems.put("open_id", app.getOpen_idOrToken()[0]);
				jsonItems.put("token", app.getOpen_idOrToken()[1]);
			}
			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					jsonItems.put(key, params.get(key));
				}
				// "open_id":"5_1278_539947","token":"b1fccbf52f67ca26"
			}
			json.put("params", jsonItems);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 手机号是否合法验证
	 * 中国移动134,135,136,137,138,139,150,151,152,157,158,159,182,183,187,188,147
	 * 中国联通130,131,132,155,156,185,186,145 中国电信133,153,180,181,189 
	 * joe
	 */
	public static boolean checkMobile(String mobile) {
		String regex = "^1(3[0-9]|4[57]|5[012356789]|8[012356789])\\d{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		return m.find();
	}

	/**
	 * 判断email格式是否正确 joe
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
