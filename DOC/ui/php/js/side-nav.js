$(function(){	
	//���������������� ������������
	$("#slide .child-box").eq(0).slideDown(100);//��ʼ��������һ��չ��
	//���������¼�
	$("#slide .title").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active").next("div.child-box").slideUp();
		}else{
			$("div.slide-box.title").removeClass("active").next("div.child-box").slideUp("fast");
			$(this).addClass("active").next("div.child-box").slideDown(100);
		}
	});
	//�����̶�
	var doc_h = $("#slide").offset().top;// �����ඥ�ĸ߶�
	var foot_h = $("#footer").offset().top;//�ײ��ඥ�ĸ߶�
	//����������
	$(window).scroll(function(){
		var win_h = $(window).scrollTop();
		if(win_h>=doc_h && win_h<foot_h-500){
			$("#slide").addClass("fixed");
		}else{
			$("#slide").removeClass("fixed");
		}
	});
	
});