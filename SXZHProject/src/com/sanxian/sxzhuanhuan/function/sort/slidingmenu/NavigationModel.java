package com.sanxian.sxzhuanhuan.function.sort.slidingmenu;

/**   
 * @Title: NavigationModel.java 
 * @Package com.sanxian.sxzhuanhuan.function.sort.slidingmenu
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-6 下午1:47:15 
 * @version V1.0   
 */
public class NavigationModel {
	
	private String name;
	//作为唯一标识符 creative project product 方便于每个页面请求相对应的地址
	private String tags;
	
	public  NavigationModel(String name1,String tags1){
		this.name = name1;
		this.tags = tags1;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	

}
