/*
	首页banner动画效果
	Autor：yangbin
	time：2014-01-09
	email：453708503@qq.com
*/
$(function () {
	var slides = {
		items: $("#slideBox .slide"),
		length: $("#slideBox .slide").length,
		btns: $("#slideBox .banner-btn"),
		btnc:$("#slideBox .banner-nav span"),
		index: 0,
		pauseTime: 5000,
		timer: null,
		go: function (n) {
			slides.items.eq(slides.index).stop().fadeOut();
			slides.index = !isNaN(n) ? n : slides.index < slides.length - 1 ? slides.index + 1 : 0;
			slides.items.eq(slides.index).stop().fadeIn();
			slides.btnc.removeClass("active");
			slides.btnc.eq(slides.index).addClass("active");
		}
	};
	if (slides.length > 1) {
		$(".prev", slides.btns).click(function () {
			slides.go(slides.index == 0 ? slides.length - 1 : slides.index - 1);
		});
		$(".next", slides.btns).click(function () {
			slides.go();
		});
		$("#slideBox").hover(function () {
			clearInterval(slides.timer);
			slides.btns.stop().fadeIn();
		}, function () {
			slides.timer = setInterval(slides.go, slides.pauseTime);
			slides.btns.stop().fadeOut();
		});
		slides.timer = setInterval(slides.go, slides.pauseTime);
	};
	slides.go(slides.length - 1);
}); 