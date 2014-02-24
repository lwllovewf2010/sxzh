package com.sanxian.sxzhuanhuan.entity;


/**
 * @Title: DiscussVoteResultInfo.java
 * @Package com.sanxian.sxzhuanhuan.entity
 * @Description: 讨论大厅投票结果
 * @author zhangfl@sanxian.com
 * @date 2014-2-24 下午3:27:42
 * @version V1.0
 */
public class DiscussVoteResultInfo {
	private String id ;
	private String votePubname;
	private String voteResult;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the votePubname
	 */
	public String getVotePubname() {
		return votePubname;
	}
	/**
	 * @param votePubname the votePubname to set
	 */
	public void setVotePubname(String votePubname) {
		this.votePubname = votePubname;
	}
	/**
	 * @return the voteResult
	 */
	public String getVoteResult() {
		return voteResult;
	}
	/**
	 * @param voteResult the voteResult to set
	 */
	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}
	
}
