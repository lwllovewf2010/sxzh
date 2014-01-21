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
	/**登录成功后返回码 */
	public final static int RESULT_LOGIN_CODE = 0x05 ;
	
	/**发布创意话题 */
	public final static int PUBLISH_TOPIC = 0x00 ;
	/**发布集资项目 */
	public final static int PUBLISH_ORIGINALITY = 0x01 ;
	
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
		public final static int SORT_BASE = 0x01 ;
		/**分类中的创意选项 */
		public final static int SORT_CREATIVE = 0x01 ;
		/**分类中的项目选项 */
		public final static int SORT_PROJECT = 0x02 ;
		/**分类中的商品选项 */
		public final static int SORT_PRODUCT = 0x03 ;
		
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
	
	/**
	 * 访问服务器时的请求码 
	 */
	public static final class REQUESTCODE {
		public static final int MODE_REQUEST = 0x01 ; //分类请求码
		public static final int CATEGORY_REQUEST = 0x02 ; //行业请求码
		public static final int AREA_REQUEST = 0x03 ; //城市请求码
		public static final int CHECK_UNAME_REQUEST = 0x04 ; //检查用户名
		public static final int CHECK_UTELEPHONE_REQUEST = 0x05 ; //检查手机号
		public static final int SEND_VERTIFY_CODE_REQUEST = 0x06 ; //向指定的手机发送验证码
		public static final int CHECK_VERTIFY_CODE_REQUEST = 0x07 ; //检查用户提交的短信验证码是否有效
		public static final int REGISTER_REQUEST = 0x08 ; //用户注册
		public static final int LOGIN_REQUEST = 0x09 ; //用户登录
		public static final int INDEX_IMGS_REQUEST = 0x0a ; //获取首页广告位图片
		public static final int CREATIVE_LIST_REQUEST = 0x0b ; //创意列表
		public static final int PROJECT_LIST_REQUEST = 0x0c ; //项目列表
		public static final int PRODUCT_LIST_REQUEST = 0x0d ; //商品列表
		public static final int PROJECT_GET_ROW_REQUEST = 0x0e ; //查找单个项目
		
		public static final int SORT_PARENT_REQUEST = 0x20 ; //大分类列表
		public static final int SORT_CHILD_REQUEST = 0x21 ; //小分类列表
		public static final int SORT_PROVINCE_REQUEST = 0x22 ; //省列表
		public static final int SORT_CITY_REQUEST = 0x23 ; //城市列表
		
		public static final int CHECK_USER_REGISTER_REQUEST = 0x50 ; //user_register
	}
	
	/**
	 * 请求服务器的返回状态
	 */
	public static final class ResultStatus {
		public static final int RESULT_OK = 0x00 ; //成功
		public static final int RESULT_FAIL = 0x01 ;  //失败
		public static final int RESULT_USER_EXIT = 0x02 ; //用户已经存在
		
	}
	
	public static final class TimeLimit {
		public static final int TIMER_60 = 60 ;
		public static final int TIMER_120 = 120 ;
	}
	
}
