package com.sanxian.sxzhuanhuan.entity;

/**
 * @Title: InterfaceData.java
 * @Package com.sanxian.sxzhuanhuan.entity
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-15 上午11:05:01
 * @version V1.0
 */
public class InterfaceData {
	public static class IMode {
		private String id;
		private String type_name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType_name() {
			return type_name;
		}

		public void setType_name(String type_name) {
			this.type_name = type_name;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "IMode---" + "id:" + id + "   type_name:" + type_name ;
		}

	}

	public static class ICategory {
		private String id;
		private String title;
		private String parent_id;
		private String sub_count ;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getParent_id() {
			return parent_id;
		}

		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}

		public String getSub_count() {
			return sub_count;
		}

		public void setSub_count(String sub_count) {
			this.sub_count = sub_count;
		}

		@Override
		public String toString() {
			return "ICategory [id=" + id + ", title=" + title + ", parent_id="
					+ parent_id + ", sub_count=" + sub_count + "]";
		}
		
	}

	public static class IArea {
		private String id;
		private String title;
		private String reid;
		private String disorder;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getReid() {
			return reid;
		}

		public void setReid(String reid) {
			this.reid = reid;
		}

		public String getDisorder() {
			return disorder;
		}

		public void setDisorder(String disorder) {
			this.disorder = disorder;
		}
		
		@Override
		public String toString() {
			return "IArea----" + "id:" + id + "   title:" + title + "   reid:" + reid + "   disorder:" + disorder ; 
		}
	}

	public static class IImgs {
		private String url;
		private String imgpath;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getImgpath() {
			return imgpath;
		}

		public void setImgpath(String imgpath) {
			this.imgpath = imgpath;
		}
		
		@Override
		public String toString() {
			return "IImgs----" + "imgpath:" + imgpath + "   url:" + url ; 
		}
	}

	public static class IProjectInfo {
		private String id;
		private String user_id;
		private String project_name;
		private String project_explain;
		private String project_logo;
		private String project_money;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getProject_name() {
			return project_name;
		}

		public void setProject_name(String project_name) {
			this.project_name = project_name;
		}

		public String getProject_explain() {
			return project_explain;
		}

		public void setProject_explain(String project_explain) {
			this.project_explain = project_explain;
		}

		public String getProject_logo() {
			return project_logo;
		}

		public void setProject_logo(String project_logo) {
			this.project_logo = project_logo;
		}

		public String getProject_money() {
			return project_money;
		}

		public void setProject_money(String project_money) {
			this.project_money = project_money;
		}

		@Override
		public String toString() {
			return "IProjectInfo----" + "id:" + id + "   user_id:" + user_id  + "   project_name:" + project_name 
					+ "   project_explain:" + project_explain + "   project_logo:" + project_logo + "   project_money:" + project_money; 
		}
	}
	
	public static class ILogin {
		//"open_id": "5_1278_539947",
//        "user_name": "10629762@qq.com",
//        "photo": "",
//        "dname": null,
//        "mobile": "13811689766",
//        "email": "10629762@qq.com",
//        "token": "f9a4745d0a1c8ba7"
		private String open_id ; 
		private String user_name ; 
		private String photo ; 
		private String dname ; 
		private String mobile ; 
		private String email ; 
		private String token ;
		public String getOpen_id() {
			return open_id;
		}
		public void setOpen_id(String open_id) {
			this.open_id = open_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public String getDname() {
			return dname;
		}
		public void setDname(String dname) {
			this.dname = dname;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ILogin [open_id=" + open_id + ", user_name=" + user_name
					+ ", photo=" + photo + ", dname=" + dname + ", mobile="
					+ mobile + ", email=" + email + ", token=" + token + "]";
		} 
		
	}
}
