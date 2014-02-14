$(function(){
	var ye_v = Number($("#pay-ye").text());//获取余额值
	var zf_v = Number($("#pay-zf").text());//获取支付值
	//选择支付方式单击事件
	$("span.filter-slide-span").click(function(){
		$(this).toggleClass("active").next("div.filter-slide-box").toggle();
	});
	//判断余额是否为0
	if(ye_v==0){
		$("#pay-overage span.filter-slide-span").addClass("no-active");//当余额为0时，余额支付不可点击
		$("#pay-other span.filter-slide-span").trigger("click");//其它支付自动显示
	}
	$("span.filter-slide-span.no-active").unbind("click");//当余额为0时，余额支付不可点击
	//支付方式选择!
	$("input[name='rd-pay']").click(function(){
		$("input[name='rd-pay']").parent("label").removeClass("active");//未选中银行
		$(this).parent("label").addClass("active");//选中银行
	});
	//确认支付
	$("#pay-submit").click(function(){
		var self = $(this);
		var content = $('.content');
		$("#pop-alert").bPopup({
			onOpen: function() {
				content.html("<div class='main-box'><h3 class='main-text cent'>请您在新打开网上银行页面进行支付，支付完成前请不要关闭该窗口。</h3><div class='btns cent'><button class='btn-btn btn-success'>已完成支付</button><button class='btn-btn btn-fff btn-false'>支付遇到问题</button></div></div>");
			},
			onClose: function() {
				content.empty();
			}
		});
	});
});