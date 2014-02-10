/*
	创意大厅详细页
	右侧栏  支持者和不支持者左右滚动插件
	Autor：yangbin
	time：2014-1-11
	email：453708503@qq.com
*/
(function($){
	$.fn.sideSlide = function(options){
		//defaults
		var defaults = {
			"width":300,
			"time":5000,
			"delay":400,
		};
		var options = $.extend(defaults,options);
		return this.each(function(){
			var $this = $(this);
			var $sides = $(this).find(".people-list-boxs");
			var $slides = $(this).find(".people-list-boxs .people-list-box");
			var length = $slides.length;
			var curr = 0;
			var Timer ="";
			
			//初始化
			function winLoad(){
				//设置总宽度
				$sides.width(length*options.width);
				auto();
				manual();
			}
			//左右单击事件
			function manual(){
				var $btn = $this.prev("h2.title").find("i.btns");
				$btn.click(function(){
					clearInterval(Timer);
					if($(this).hasClass(".right-btn")){
						btnClick(1);
					}else{
						btnClick(-1);
					}
				});
			}
			//左右单击函数
			function btnClick(n){
				n==1?curr++:curr--;
				if(curr >length-1) curr = 0;
				if(curr<0) curr= length-1;
				go(curr);
			}
			//定时滚动
			function auto(){
				Timer = setInterval(function(){
					curr++;
					if(curr>length-1) curr = 0;
					go(curr);
				},options.time);
			}
			//滚动效果
			function go(n){
				$sides.stop().animate({'marginLeft':-options.width*n},options.delay);
			}
			winLoad();
		});
	}
})(jQuery)