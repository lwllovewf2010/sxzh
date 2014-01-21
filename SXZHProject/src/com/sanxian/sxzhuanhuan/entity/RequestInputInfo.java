package com.sanxian.sxzhuanhuan.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RequestInputInfo implements Serializable {

	/**
	 * 请求传参实体类（input）对应参数
	 * 
	 * @author joe
	 */
	private static final long serialVersionUID = 1L;

	private String action = ""; // 方法名称
	private String type = ""; // 类型
	private String mcr = ""; // 是否加密
	private Map<String,String> params; // 参数集合

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMcr() {
		return mcr;
	}

	public void setMcr(String mcr) {
		this.mcr = mcr;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
