$(function(){	
	//———————— 侧栏下拉导航
	$("#slide .child-box").eq(0).slideDown(100);//初始化侧栏第一个展开
	//侧栏单击事件
	$("#slide .title").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active").next("div.child-box").slideUp();
		}else{
			$("div.slide-box.title").removeClass("active").next("div.child-box").slideUp("fast");
			$(this).addClass("active").next("div.child-box").slideDown(100);
		}
	});
	//侧栏固定
	var doc_h = $("#slide").offset().top;// 侧栏距顶的高度
	var foot_h = $("#footer").offset().top;//底部距顶的高度
	//滚动条滚动
	$(window).scroll(function(){
		var win_h = $(window).scrollTop();
		if(win_h>=doc_h && win_h<foot_h-500){
			$("#slide").addClass("fixed");
		}else{
			$("#slide").removeClass("fixed");
		}
	});
	
});