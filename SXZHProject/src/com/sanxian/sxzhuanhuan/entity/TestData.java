package com.sanxian.sxzhuanhuan.entity;

import java.util.ArrayList;

/**   
 * @Title: TestData.java 
 * @Package com.sanxian.sxzhuanhuan.entity 
 * @Description: 模拟相关数据，作为测试
 * @author zhangfl@sanxian.com
 * @date 2014-1-6 下午4:43:45 
 * @version V1.0   
 */
public class TestData {
	/**
	 * 初始化分类测试数据
	 * @return
	 */
	public static ArrayList<SortInfo> initSort() {
		ArrayList<SortInfo> sortinfos = new ArrayList<SortInfo>() ;
		SortInfo sort = null ;
		ArrayList<String> sortChild = null ;
		for (int i = 0; i < 5; i++) {
			sort = new SortInfo() ;
			sort.setSortLogoUrl("http://xxx" ) ;
			sort.setSortName("大分类" + (i + 1 ) ) ;
			sortChild = new ArrayList<String>() ;
			for (int j = 0; j <= i; j++) {
				sortChild.add("小分类" + (j+1)) ;
			}
			sort.setSortChild(sortChild) ;
			
			sortinfos.add(sort) ;
		}
		return sortinfos ;
	}
	/** 
	 * 初始化创意测试数据
	 * @return
	 */
	public static ArrayList<CreativeInfo> initCreative() {
		ArrayList<CreativeInfo> creativeInfos = new ArrayList<CreativeInfo>() ;
		CreativeInfo creativeInfo = null ;
		for (int i = 1; i < 10; i++) {
			creativeInfo = new CreativeInfo() ;
			creativeInfo.setMetaName("metaName" + i) ;
			creativeInfo.setLogo("logo---url" ) ;
			creativeInfo.setMetaDetail("CreativeInfoCreativeInfoCreativeInfo" + i) ;
			creativeInfo.setMoney(100.0f * i) ;
			creativeInfo.setMetaMoney(10000.0f * i) ;
			creativeInfo.setParticipate(10 * i) ;
			creativeInfo.setDiscuss(123 * i ) ;
			creativeInfo.setDays(4 * i ) ;
			
			creativeInfos.add(creativeInfo) ;
		}
		return creativeInfos ;
	}
	
	/**
	 * 初始化项目测试数据
	 * @return
	 */
	public static ArrayList<ProjectInfo> initProject() {
		ArrayList<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>() ;
		ProjectInfo projectInfo = null ;
		for (int i = 1; i < 10; i++) {
			projectInfo = new ProjectInfo() ;
			projectInfo.setMetaName("metaName" + i) ;
			projectInfo.setLogo("logo---url" ) ;
			projectInfo.setMetaDetail("ProjectInfoProjectInfoProjectInfoProjectInfo" + i) ;
			projectInfo.setMoney(100.0f * i) ;
			projectInfo.setMetaMoney(10000.0f * i) ;
			projectInfo.setParticipate(10 * i) ;
			projectInfo.setDiscuss(123 * i ) ;
			projectInfo.setDays(4 * i ) ;
			
			projectInfos.add(projectInfo) ;
		}
		return projectInfos ;
	}
	/**
	 * 初始化商品测试数据
	 * @return
	 */
	public static ArrayList<ProductInfo> initProduct() {
		ArrayList<ProductInfo> productInfos = new ArrayList<ProductInfo>() ;
		ProductInfo productInfo = null ;
		for (int i = 1; i < 10; i++) {
			productInfo = new ProductInfo() ;
			productInfo.setMetaName("metaName" + i) ;
			productInfo.setLogo("logo---url" ) ;
			productInfo.setMetaDetail("ProductInfoProductInfoProductInfoProductInfo" + i) ;
			productInfo.setMoney(100.0f * i) ;
			productInfo.setMetaMoney(10000.0f * i) ;
			productInfo.setParticipate(10 * i) ;
			productInfo.setDiscuss(123 * i ) ;
			productInfo.setDays(4 * i ) ;
			
			productInfos.add(productInfo) ;
		}
		return productInfos ;
	}
}
