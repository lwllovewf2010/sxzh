$(function(){
	var ye_v = Number($("#pay-ye").text());//获取余额值
	var zf_v = Number($("#pay-zf").text());//获取支付值
	//余额充足，选择其中一种支付方式
	if(ye_v-zf_v>=0){
		$("#pay-main .filter-slide-span").each(function(){
			$(this).addClass("filter-siggle");
		});
	}
	//选择支付方式单击事件
	$("span.filter-slide-span").click(function(){
		//余额充足，选择其中一种支付方式
		if($(this).hasClass("filter-siggle")){
			$(this).toggleClass("active").next("div.filter-slide-box").toggle();
			$(this).parent("div.div-pay-box").siblings("div.div-pay-box").find("span.filter-slide-span").removeClass("active").next().hide();
		}else{
			$(this).toggleClass("active").next("div.filter-slide-box").toggle();//余额不足时，两种支付方式都可选
		}
		//判断支付方式
		var check_num = $("#pay-main .filter-slide-span.active").length;
		if(check_num == 2){
			$("#pay-method").val(1);
		}else if(check_num == 1){
			var id_v = $("#pay-main .filter-slide-span.active").parent().attr("id");
			id_v == "pay-overage"?$("#pay-method").val(2):$("#pay-method").val(3);
		}else{
			$("#pay-method").val("");
		}
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