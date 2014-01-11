package com.sanxian.sxzhuanhuan.dialog;

import java.io.Serializable;

import android.R.integer;

/**
 * 
* @ClassName: MiddleDialogInfo  
* @Description: 中间弹出框
* @author honaf
* @date 2014-1-8 下午3:58:37
 */
public class MiddleDialogInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title="";    //头部提示
	private String content="";  //内容提示
	private boolean isEdit=false;//是否是编辑框
	private String edit_hint="";//编辑框的hint的文本
	private String edit_content="";//编辑框的文本
	private String ok="";       //左边按钮
	private String cancel="";   //右边按钮
	
	public String getEdit_hint() {
		return edit_hint;
	}
	public void setEdit_hint(String edit_hint) {
		this.edit_hint = edit_hint;
	}
	public String getEdit_content() {
		return edit_content;
	}
	public void setEdit_content(String edit_content) {
		this.edit_content = edit_content;
	}
	public boolean isEdit() {
		return isEdit;
	}
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public MiddleDialogInfo(String title, String content, boolean isEdit, String edit_hint, String edit_content, String ok, String cancel) {
		super();
		this.title = title;
		this.content = content;
		this.isEdit = isEdit;
		this.edit_hint = edit_hint;
		this.edit_content = edit_content;
		this.ok = ok;
		this.cancel = cancel;
	}
	
	
	
}
