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
		var doc = $(this).parent().find("input.num-ip");
		var doc_p = $(this).parent().find("a.btn");
		var num = doc.val();
		var btn_n = doc_p.index($(this));
		btn_n==0?num--:num++;
		if(num<1) num=1;
		doc.val(num);
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
		$(this).find("ul").slideDown("fast")
	},function(){
		$(this).removeClass("active");
		$(this).find("ul").slideUp("fast");
	});
	//———————— 选项卡
	var arr_tab = ["发布创意","发布项目"];
	$(".process").click(function(){
		var curr = $(".process").index($(this));
		$(this).addClass("active").siblings("div.process").removeClass("active");
		$(".tab-box").eq(curr).show().siblings("div.tab-box").hide();
		$(".bread em").html(arr_tab[curr]);
	});
	//———————— 登录注册选项卡
	$(".login-tab li").click(function(){
		var curr = $(".login-tab li").index($(this));
		$(this).addClass("active").siblings().removeClass("active");
		$(".tab-item").eq(curr).show().siblings("div.tab-item").hide();
	});
});