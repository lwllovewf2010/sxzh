/*
	公用js
	Autor：yangbin
	time：2014-01-09
	email：453708503@qq.com
*/
$(function(){
	//————————收索框变化
	$("input#search").focus(function(){
		$(".search-bg").addClass("active");
	});
	$("input#search").blur(function(){
		$(".search-bg").removeClass("active");
	});
	//———————— 筛选条件单选按钮
	$(".filter-span").click(function(){
		$(this).toggleClass("active").siblings("span.filter-span").removeClass("active");
	});
	//———————— 单选按钮
	$(".choose-icon").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	});
	//———————— 购物车数量按钮
	$(".prd-num-select a.btn").click(function(){
		var num = $(".num-ip").val();
		var btn_n = $(".prd-num-select a.btn").index($(this));
		btn_n==0?num--:num++;
		if(num<1) num=1;
		$(".num-ip").val(num);
	}); 
	//———————— 举报弹出框
	$(".report").click(function(){
		$("#pop").bPopup({
            contentContainer:'.content',
            loadUrl: 'dialog.html' 
        });
	});
	//———————— 下拉菜单
	$(".li-drop").hover(function(){
		$(this).addClass("active");
		$(this).find("ul").slideDown()
	},function(){
		$(this).removeClass("active");
		$(this).find("ul").slideUp();
	});
	//———————— 选项卡
	$(".process").click(function(){
		var curr = $(".process").index($(this));
		$(this).addClass("active").siblings("div.process").removeClass("active");
		$(".tab-box").eq(curr).show().siblings("div.tab-box").hide();
	});
	//———————— 登录注册选项卡
	$(".login-tab li").click(function(){
		var curr = $(".login-tab li").index($(this));
		$(this).addClass("active").siblings().removeClass("active");
		$(".tab-item").eq(curr).show().siblings("div.tab-item").hide();
	});
});