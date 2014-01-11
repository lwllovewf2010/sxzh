package com.sanxian.sxzhuanhuan.entity;

/**   
 * @Title: Constant.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 项目中用到的常量，配置等信息
 * @author zhangfl@sanxian.com
 * @date 2014-1-6 下午4:35:33 
 * @version V1.0   
 */
public class Constant {
	/**取消登录 */
	public final static int LOGIN_CANCLE = 0x01 ;
	/**登录 */
	public final static int LOGIN_OK = 0x02 ;
	/**登录请求码 */
	public final static int REQUEST_LOGIN_CODE = 0x04 ;
	
	/** 
	 * 注册设计到的步骤 
	 */
	public static class RegisterStep {
		public final static int STEP1 = 0x01 ;
		public final static int STEP2 = 0x02 ;
		public final static int STEP3 = 0x03 ;
	}
	
	/**
	 * 注册的状态
	 */
	public static class RegisterStatus {
		public final static int REGISTER_OK  = 0x01 ;
		public final static int REGISTER_PWD_NULL = 0x02 ;
		public final static int REGISTER_PWD_WRONG = 0x03 ;
	}
	
	/**
	 * 验证码的状态
	 */
	public static class VertifyCode {
		public final static int VC_OK = 0x01 ; 
		public final static int VC_WRONG = 0x02 ; //出错
		public final static int VC_EXPIRED = 0x03 ; //过期
	}
	
	/**分类相关的选项 */
	public static class Sort {
		/**分类中的创意选项 */
		public final static int SORT_CREATIVE = 1001 ;
		/**分类中的项目选项 */
		public final static int SORT_PROJECT = 1002 ;
		/**分类中的商品选项 */
		public final static int SORT_PRODUCT = 1003 ;
		
		public final static String[] SORTS_NAME = {"创意" , "项目" , "商品"} ;
		
		/**
		 * 根据分类名称得到分类ID
		 * @param sort
		 * @return
		 */
		public final static int getSortID(String sort) {
			int result = 0 ;
			for (int i = 0; i < SORTS_NAME.length ; i++) {
				if(sort.equals(SORTS_NAME[i]))
					return i ;
			}
			return result ;
		}
	}
	
	/**
	 * 分类标签，用于在SlidingMenu中
	 */
	public static final class TAGS {
		public static final String CREATIVE_TAG = "creative";
		public static final String PROJECT_TAG = "project";
		public static final String PRODUCT_TAG = "product";
	}
	
}
