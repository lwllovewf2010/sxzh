package com.sanxian.sxzhuanhuan.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.IArea;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ICategory;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.IImgs;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.ILogin;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.IMode;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;

/**
 * @Title: JSONParser.java
 * @Package com.sanxian.sxzhuanhuan.api
 * @Description: Json数据的解析
 * @author zhangfl@sanxian.com
 * @date 2014-1-15 上午10:31:53
 * @version V1.0
 */
public class JSONParser {
	/**
	 * 根据json数据获取到返回的标志
	 * @param jsondata
	 * @return
	 */
	public static int getReturnFlag(String jsondata) {
		int ret = 0 ;
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			ret = object.optInt("ret") ;
//			JSONArray mJSONArray = object.getJSONArray("ret");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret ;
	}
	
	/**
	 * 得到失败信息
	 * @param jsondata
	 * @return
	 */
	public static String getFailString(String jsondata) {
		String result = "" ;
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			result = object.optString("content") ;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	/**
	 * @param jsondata
	 * @return 获取分类的数据 success { "ret":0, "content": [
	 *         {"id":"1","type_name":"项目" }, {"id":"2","type_name":"创意" } ] }
	 *         fail { ret: 1, content: "failure" }
	 */
	public static List<IMode> getModes(String jsondata) {
		List<IMode> sorts = new ArrayList<IMode>();
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			JSONArray mJSONArray = object.getJSONArray("content");
			IMode sort = null;
			for (int i = 0; i < mJSONArray.length(); i++) {
				sort = new IMode();
				JSONObject mJSONObject = mJSONArray.getJSONObject(i);
				System.out.println("id:" + mJSONObject.optString("id"));
				System.out.println("type_name:"
						+ mJSONObject.optString("type_name"));
				sort.setId(mJSONObject.optString("id"));
				sort.setType_name(mJSONObject.optString("type_name"));

				sorts.add(sort);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sorts;
	}

	/**
	 * 获取行业的数据
	 * 
	 * @param jsondata
	 * @return { "ret":0, "content": [
	 *         {"id":"1","title":"科技","parent_id":"0"},
	 *         {"id":"2","title":"影视","parent_id":"0"} ] }
	 */
	public static List<ICategory> getCategory(String jsondata) {
		List<ICategory> categories = new ArrayList<ICategory>();
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			JSONArray mJSONArray = object.getJSONArray("content");
			ICategory category = null;
			for (int i = 0; i < mJSONArray.length(); i++) {
				category = new ICategory();
				JSONObject mJSONObject = mJSONArray.getJSONObject(i);
//				System.out.println("id:" + mJSONObject.optString("id"));
//				System.out.println("title:"
//						+ mJSONObject.optString("title"));
//				System.out.println("parent_id:"
//						+ mJSONObject.optString("parent_id"));
				category.setId(mJSONObject.optString("id"));
				category.setTitle(mJSONObject.optString("title"));
				category.setParent_id(mJSONObject.optString("parent_id"));
				category.setSub_count(mJSONObject.optString("sub_count"));

				categories.add(category);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	/**
	 * 获取城市的数据
	 * 
	 * @param jsondata
	 * @return { "ret":0, "content": [
	 *         {"id":"1","name":"北京市","reid":"0","disorder":"0"},
	 *         {"id":"2","name":"广东省","reid":"0","disorder":"0"} ] }
	 */
	public static List<IArea> getArea(String jsondata) {
		List<IArea> areas = new ArrayList<IArea>();
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			JSONArray mJSONArray = object.getJSONArray("content");
			IArea area = null;
			for (int i = 0; i < mJSONArray.length(); i++) {
				area = new IArea();
				JSONObject mJSONObject = mJSONArray.getJSONObject(i);
				System.out.println("id:" + mJSONObject.optString("id"));
				System.out.println("name:" + mJSONObject.optString("name"));
				System.out.println("reid:" + mJSONObject.optString("reid"));
				System.out.println("disorder:"
						+ mJSONObject.optString("disorder"));
				area.setId(mJSONObject.optString("id"));
				area.setTitle(mJSONObject.optString("name"));
				area.setReid(mJSONObject.optString("reid"));
				area.setDisorder(mJSONObject.optString("disorder"));

				areas.add(area);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areas;
	}
	
	/**
	 * 获取登录成功返回的相关数据
	 * @param jsondata
	 * @return
	 * {
		    "ret": 0,
		    "content": {
		        "open_id": "5_1278_539947",
		        "user_name": "10629762@qq.com",
		        "photo": "",
		        "dname": null,
		        "mobile": "13811689766",
		        "email": "10629762@qq.com",
		        "token": "f9a4745d0a1c8ba7"
		    }
		}
	 */
	public static ILogin getLoginData(String jsondata) {
		JSONObject object = null;
		ILogin login = null;
		try {
			object = new JSONObject(jsondata);
			JSONObject mJSONObject = object.getJSONObject("content");
			login = new ILogin();
			System.out.println("open_id:" + mJSONObject.optString("open_id"));
			System.out.println("token:" + mJSONObject.optString("token"));
			login.setOpen_id(mJSONObject.optString("open_id"));
			login.setToken(mJSONObject.optString("token"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login;
	}

	/**
	 * 获取首页广告位图片
	 * 
	 * @param jsondata
	 * @return { "ret":0, "content":[
	 *         {"imgpath":"http:\/\/192.168.1.75\/1.jpg","url":"#"},
	 *         {"imgpath":"http:\/\/192.168.1.75\/2.jpg","url":"#"},
	 *         {"imgpath":"http:\/\/192.168.1.75\/3.jpg","url":"#"} ] }
	 */
	public static List<IImgs> getIndexImgs(String jsondata) {
		List<IImgs> imgs = new ArrayList<IImgs>();
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			JSONArray mJSONArray = object.getJSONArray("content");
			IImgs img = null;
			for (int i = 0; i < mJSONArray.length(); i++) {
				img = new IImgs();
				JSONObject mJSONObject = mJSONArray.getJSONObject(i);
				System.out.println("imgpath:" + mJSONObject.optString("imgpath"));
//				System.out.println("url:" + mJSONObject.optString("url"));
				img.setImgpath(mJSONObject.optString("imgpath"));
				img.setUrl(mJSONObject.optString("url"));

				imgs.add(img);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgs;
	}

	/**
	 * 获取创意相关数据信息
	 * @param jsondata
	 * @return
	 */
	 public static List<CreativeInfo> getCreativeInfo(String jsondata) {
		 List<CreativeInfo> infos = new ArrayList<CreativeInfo>();
			JSONObject object = null;
			try {
				object = new JSONObject(jsondata);
				JSONArray mJSONArray = object.getJSONArray("content");
				CreativeInfo info = null;
				for (int i = 0; i < mJSONArray.length(); i++) {
					info = new CreativeInfo();
					JSONObject mJSONObject = mJSONArray.getJSONObject(i);
					
//					"id": "1",
//		            "user_id": "6_1140_559945",
//		            "org_name": "这是我发的创意",
//		            "org_desc": "创意说明在这里",
//		            "org_explain": "-",
//		            "org_video": "-",
//		            "province_id": "102",
//		            "city_id": "1002",
//		            "category_id": "10",
//		            "scancount": "10",
//		            "addtime": "2014-01-18 10:22:11",
//		            "updatetime": null,
//		            "org_state": "0",
//		            "pid": null,
//		            "gzcount": "0"
		            	
					info.setId(mJSONObject.optString("id")) ;
					info.setUser_id(mJSONObject.optString("user_id")) ; 
					info.setOrg_name(mJSONObject.optString("org_name")) ;
					info.setOrg_desc(mJSONObject.optString("org_desc")) ;
					info.setOrg_explain(mJSONObject.optString("org_explain")) ;
					info.setOrg_video(mJSONObject.optString("org_video")) ;
					info.setOrg_state(mJSONObject.optString("org_state")) ;
					info.setProvince_id(mJSONObject.optString("province_id")) ;
					info.setCity_id(mJSONObject.optString("city_id")) ;
					info.setCategory_id(mJSONObject.optString("category_id")) ;
					info.setScancount(mJSONObject.optString("scancount")) ;
					info.setAddtime(mJSONObject.optString("addtime")) ;
					info.setUpdatetime(mJSONObject.optString("updatetime")) ;
					info.setPid(mJSONObject.optString("pid")) ;
					info.setGzcount(mJSONObject.optString("gzcount")) ;
					
					info.setComment_nums("11" ) ; //mJSONObject.optString("comment_nums")) ;
					info.setFavorite_nums("22" ) ; //mJSONObject.optString("favorite_nums")) ;
					
//					System.out.println(info.toString());
					infos.add(info);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return infos;
	 }

	//
	/**
	 * 获取项目相关的数据信息
	 * @param jsondata
	 * @return
	 */
	public static List<ProjectInfo> getProjectInfo(String jsondata) {
		List<ProjectInfo> projectinfos = new ArrayList<ProjectInfo>();
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			JSONArray mJSONArray = object.getJSONArray("content");
			ProjectInfo info = null;
			for (int i = 0; i < mJSONArray.length(); i++) {
				info = new ProjectInfo();
				JSONObject mJSONObject = mJSONArray.getJSONObject(i);

				info.setId(mJSONObject.optString("id"));
				info.setProject_gender(mJSONObject.optString("project_gender"));
				info.setUser_id(mJSONObject.optString("user_id"));
				info.setCategory_id(Integer.parseInt(mJSONObject
						.optString("category_id")));
				info.setProject_name(mJSONObject.optString("project_name"));
				info.setProject_state(Integer.parseInt(mJSONObject
						.optString("project_state")));
				info.setProvince_id(Integer.parseInt(mJSONObject
						.optString("province_id")));
				info.setCity_id(Integer.parseInt(mJSONObject
						.optString("city_id")));
				info.setProject_explain(mJSONObject
						.optString("project_explain"));
				info.setProject_logo(mJSONObject.optString("project_logo"));
				info.setProject_video(mJSONObject.optString("project_video"));
				info.setProject_describe(mJSONObject
						.optString("project_describe"));
				info.setProject_money(Integer.parseInt(mJSONObject
						.optString("project_money")));
				info.setProject_realname(mJSONObject
						.optString("project_realname"));
				info.setProject_mobile(mJSONObject.optString("project_mobile"));
				info.setProject_qq(mJSONObject.optString("project_qq"));
				info.setProject_days(Integer.parseInt(mJSONObject
						.optString("project_days")));
				info.setProject_step(Integer.parseInt(mJSONObject
						.optString("project_step")));
				info.setMode_id(Integer.parseInt(mJSONObject
						.optString("mode_id")));
				info.setReward_mode(Integer.parseInt(mJSONObject
						.optString("reward_mode")));
				info.setProject_money_refund(mJSONObject
						.optString("project_money_refund"));
				info.setProject_createtime(mJSONObject
						.optString("project_createtime"));
				info.setProject_shentime(mJSONObject
						.optString("project_shentime"));
				info.setProject_updatetime(mJSONObject
						.optString("project_updatetime"));
				info.setManager_name(mJSONObject.optString("manager_name"));
				info.setManager_id(mJSONObject.optString("manager_id"));
				info.setManager_time(mJSONObject.optString("manager_time"));
				info.setCompany_id(mJSONObject.optString("company_id"));
				info.setProd_brand(mJSONObject.optString("prod_brand"));
				info.setProd_sendaddr(mJSONObject.optString("prod_sendaddr"));
				info.setProd_type(mJSONObject.optString("prod_type"));
				info.setShowtop(mJSONObject.optString("showtop"));
				info.setConvert(mJSONObject.optString("convert"));
				info.setProject_fj(mJSONObject.optString("project_fj"));
				info.setProject_zlh(mJSONObject.optString("project_zlh"));
				info.setProject_zlname(mJSONObject.optString("project_zlname"));
				info.setPurchase_money(mJSONObject.optString("purchase_money"));
				info.setPurchase_user_num(mJSONObject
						.optString("purchase_user_num"));
//				System.out.println(info.toString());
				projectinfos.add(info);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectinfos;
	}

	public static List<ProductInfo> getProductInfo(String jsondata) {
		 List<ProductInfo> infos = new ArrayList<ProductInfo>();
			JSONObject object = null;
			try {
				object = new JSONObject(jsondata);
				JSONArray mJSONArray = object.getJSONArray("content");
				ProductInfo info = null;
				for (int i = 0; i < mJSONArray.length(); i++) {
					info = new ProductInfo();
					JSONObject mJSONObject = mJSONArray.getJSONObject(i);
					
					info.setId(mJSONObject.optString("id")) ;
					info.setGoods_name(mJSONObject.optString("goods_name")) ;
					info.setGoods_image(mJSONObject.optString("goods_image")) ;
					info.setProvince_id(mJSONObject.optString("province_id")) ;
					info.setGoods_status(mJSONObject.optString("goods_status")) ;
					info.setCompany_id(mJSONObject.optString("company_id")) ;
					info.setGoods_post_min(mJSONObject.optString("goods_post_min")) ;
					info.setGoods_price(mJSONObject.optString("goods_price")) ;
					info.setGoods_post_type(mJSONObject.optString("goods_post_type")) ;
					info.setGoods_post_free(mJSONObject.optString("goods_post_free")) ;
					info.setGoods_post_price(mJSONObject.optString("goods_post_price")) ;
					info.setGoods_sales_time(mJSONObject.optString("goods_sales_time")) ;
					info.setAttention_num(mJSONObject.optString("attention_num")) ;
					info.setSales_num(mJSONObject.optString("sales_num")) ;
					info.setCompany_name(mJSONObject.optString("company_name")) ;
		            
//					System.out.println(info.toString());
					infos.add(info);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return infos;
	}
	
	
	/**
	 * 解析单个项目信息
	 * @param jsondata
	 * @return
	 */
	public static ProjectInfo parseProjectInfo(String jsondata) {
		ProjectInfo info = new ProjectInfo();
		JSONObject object = null;
		try {
			object = new JSONObject(jsondata);
			JSONObject mJSONObject = object.getJSONObject("content");

			info.setId(mJSONObject.optString("id"));
			info.setProject_gender(mJSONObject.optString("project_gender"));
			info.setUser_id(mJSONObject.optString("user_id"));
			info.setCategory_id(Integer.parseInt(mJSONObject
					.optString("category_id")));
			info.setProject_name(mJSONObject.optString("project_name"));
			info.setProject_state(Integer.parseInt(mJSONObject
					.optString("project_state")));
			info.setProvince_id(Integer.parseInt(mJSONObject
					.optString("province_id")));
			info.setCity_id(Integer.parseInt(mJSONObject.optString("city_id")));
			info.setProject_explain(mJSONObject.optString("project_explain"));
			info.setProject_logo(mJSONObject.optString("project_logo"));
			info.setProject_video(mJSONObject.optString("project_video"));
			info.setProject_describe(mJSONObject.optString("project_describe"));
			info.setProject_money(Integer.parseInt(mJSONObject
					.optString("project_money")));
			info.setProject_realname(mJSONObject.optString("project_realname"));
			info.setProject_mobile(mJSONObject.optString("project_mobile"));
			info.setProject_qq(mJSONObject.optString("project_qq"));
			info.setProject_days(Integer.parseInt(mJSONObject
					.optString("project_days")));
			info.setProject_step(Integer.parseInt(mJSONObject
					.optString("project_step")));
			info.setMode_id(Integer.parseInt(mJSONObject.optString("mode_id")));
			info.setReward_mode(Integer.parseInt(mJSONObject
					.optString("reward_mode")));
			info.setProject_money_refund(mJSONObject
					.optString("project_money_refund"));
			info.setProject_createtime(mJSONObject
					.optString("project_createtime"));
			info.setProject_shentime(mJSONObject.optString("project_shentime"));
			info.setProject_updatetime(mJSONObject
					.optString("project_updatetime"));
			info.setManager_name(mJSONObject.optString("manager_name"));
			info.setManager_id(mJSONObject.optString("manager_id"));
			info.setManager_time(mJSONObject.optString("manager_time"));
			info.setCompany_id(mJSONObject.optString("company_id"));
			info.setProd_brand(mJSONObject.optString("prod_brand"));
			info.setProd_sendaddr(mJSONObject.optString("prod_sendaddr"));
			info.setProd_type(mJSONObject.optString("prod_type"));
			info.setShowtop(mJSONObject.optString("showtop"));
			info.setConvert(mJSONObject.optString("convert"));
			info.setProject_fj(mJSONObject.optString("project_fj"));
			info.setProject_zlh(mJSONObject.optString("project_zlh"));
			info.setProject_zlname(mJSONObject.optString("project_zlname"));
			info.setPurchase_money(mJSONObject.optString("purchase_money"));
			info.setPurchase_user_num(mJSONObject
					.optString("purchase_user_num"));
//			System.out.println(info.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return info ;

	}

}
