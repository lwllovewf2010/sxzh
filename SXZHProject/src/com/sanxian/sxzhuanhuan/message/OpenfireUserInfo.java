package com.sanxian.sxzhuanhuan.message;

/**
 * 当前登入用户的信息
 * @author luozhiren
 *
 */
public class OpenfireUserInfo {
	
	public static OpenfireUserInfo info=null;
	
	private String currentLoginUserJid;
//	private String currentOpenSuffix;  //当前服务器的后缀
	
	
	public String getCurrentLoginUserJid() {
		return currentLoginUserJid;
	}

	public void setCurrentLoginUserJid(String currentLoginUserJid) {
		this.currentLoginUserJid = currentLoginUserJid;
		//setCurrentOpenSuffix(currentLoginUserJid.s  );
	}

//	public String getCurrentOpenSuffix() {
//		return currentOpenSuffix;
//	}

//	private void setCurrentOpenSuffix(String currentOpenSuffix) {
//		this.currentOpenSuffix = currentOpenSuffix;
//	}

	private OpenfireUserInfo(){
		
	}
	
	public static OpenfireUserInfo getOpenFireUserInfo(){
		if(info!=null){
			return info;
		}
		return new OpenfireUserInfo();
	}
	
	
	
}
