﻿for(var i = 0; i < 261; i++) { var scriptId = 'u' + i; window[scriptId] = document.getElementById(scriptId); }

$axure.eventManager.pageLoad(
function (e) {

});

u224.style.cursor = 'pointer';
$axure.eventManager.click('u224', function(e) {

if ((GetSelectedOption('u224')) == ('七天无理由退换货')) {

	SetPanelState('u225', 'pd1u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('收到的商品破损')) {

	SetPanelState('u225', 'pd2u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('商品错发')) {

	SetPanelState('u225', 'pd3u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('商品需要维修')) {

	SetPanelState('u225', 'pd4u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('发票问题')) {

	SetPanelState('u225', 'pd5u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('收到的商品与描述不符')) {

	SetPanelState('u225', 'pd6u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('商品质量问题')) {

	SetPanelState('u225', 'pd7u225','none','',500,'none','',500);

}

if ((GetSelectedOption('u224')) == ('未按约定时间发货')) {

	SetPanelState('u225', 'pd8u225','none','',500,'none','',500);

}
});
gv_vAlignTable['u32'] = 'center';gv_vAlignTable['u243'] = 'top';HookHover('u156', false);
gv_vAlignTable['u207'] = 'top';gv_vAlignTable['u45'] = 'center';gv_vAlignTable['u199'] = 'top';document.getElementById('u38_img').tabIndex = 0;

u38.style.cursor = 'pointer';
$axure.eventManager.click('u38', function(e) {

if (true) {

	SetPanelState('u9', 'pd1u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u128'] = 'center';gv_vAlignTable['u222'] = 'top';gv_vAlignTable['u135'] = 'top';document.getElementById('u42_img').tabIndex = 0;

u42.style.cursor = 'pointer';
$axure.eventManager.click('u42', function(e) {

if (true) {

	SetPanelState('u9', 'pd3u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u256'] = 'center';HookHover('u159', false);
gv_vAlignTable['u55'] = 'center';gv_vAlignTable['u14'] = 'center';gv_vAlignTable['u5'] = 'center';gv_vAlignTable['u105'] = 'center';gv_vAlignTable['u218'] = 'center';gv_vAlignTable['u235'] = 'top';gv_vAlignTable['u138'] = 'center';gv_vAlignTable['u192'] = 'top';gv_vAlignTable['u241'] = 'top';gv_vAlignTable['u181'] = 'top';document.getElementById('u65_img').tabIndex = 0;

u65.style.cursor = 'pointer';
$axure.eventManager.click('u65', function(e) {

if (true) {

	SetPanelState('u9', 'pd2u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u189'] = 'top';gv_vAlignTable['u24'] = 'center';gv_vAlignTable['u6'] = 'top';gv_vAlignTable['u205'] = 'top';gv_vAlignTable['u37'] = 'top';gv_vAlignTable['u238'] = 'top';gv_vAlignTable['u30'] = 'center';gv_vAlignTable['u62'] = 'top';gv_vAlignTable['u113'] = 'top';gv_vAlignTable['u8'] = 'center';gv_vAlignTable['u230'] = 'top';gv_vAlignTable['u3'] = 'center';document.getElementById('u88_img').tabIndex = 0;

u88.style.cursor = 'pointer';
$axure.eventManager.click('u88', function(e) {

if (true) {

	SetPanelState('u9', 'pd1u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u68'] = 'center';gv_vAlignTable['u176'] = 'center';gv_vAlignTable['u208'] = 'top';gv_vAlignTable['u47'] = 'center';gv_vAlignTable['u216'] = 'top';gv_vAlignTable['u149'] = 'top';gv_vAlignTable['u72'] = 'center';gv_vAlignTable['u103'] = 'center';HookHover('u154', false);
gv_vAlignTable['u99'] = 'center';gv_vAlignTable['u164'] = 'top';gv_vAlignTable['u146'] = 'top';gv_vAlignTable['u187'] = 'center';gv_vAlignTable['u170'] = 'top';document.getElementById('u44_img').tabIndex = 0;

u44.style.cursor = 'pointer';
$axure.eventManager.click('u44', function(e) {

if (true) {

	SetPanelState('u9', 'pd0u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u78'] = 'center';gv_vAlignTable['u179'] = 'top';gv_vAlignTable['u57'] = 'center';gv_vAlignTable['u231'] = 'top';gv_vAlignTable['u203'] = 'top';gv_vAlignTable['u125'] = 'center';
u213.style.cursor = 'pointer';
$axure.eventManager.click('u213', function(e) {

if ((GetSelectedOption('u213')) == ('不支付运费')) {

	SetPanelVisibility('u214','hidden','none',500);

SetGlobalVariableValue('23', '1');

}
else
if ((GetSelectedOption('u213')) == ('支付运费')) {

	SetPanelVisibility('u214','','none',500);

SetGlobalVariableValue('23', '2');

}
});
gv_vAlignTable['u202'] = 'top';gv_vAlignTable['u41'] = 'center';gv_vAlignTable['u246'] = 'top';gv_vAlignTable['u39'] = 'center';document.getElementById('u219_img').tabIndex = 0;

u219.style.cursor = 'pointer';
$axure.eventManager.click('u219', function(e) {

if (true) {

	SetPanelState('u193', 'pd1u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u210'] = 'top';
u195.style.cursor = 'pointer';
$axure.eventManager.click('u195', function(e) {

if ((GetSelectedOption('u195')) == ('七天无理由退换货')) {

}

if ((GetSelectedOption('u195')) == ('收到的商品破损')) {

	SetPanelState('u196', 'pd1u196','none','',500,'none','',500);

}

if ((GetSelectedOption('u195')) == ('商品错发')) {

	SetPanelState('u196', 'pd2u196','none','',500,'none','',500);

}

if ((GetSelectedOption('u195')) == ('商品需要维修')) {

	SetPanelState('u196', 'pd3u196','none','',500,'none','',500);

}

if ((GetSelectedOption('u195')) == ('发票问题')) {

}

if ((GetSelectedOption('u195')) == ('收到的商品与描述不符')) {

	SetPanelState('u196', 'pd4u196','none','',500,'none','',500);

}

if ((GetSelectedOption('u195')) == ('商品质量问题')) {

	SetPanelState('u196', 'pd5u196','none','',500,'none','',500);

}

if ((GetSelectedOption('u195')) == ('未按约定时间发货')) {

}
else
if ((GetSelectedOption('u195')) == ('未收到货')) {

}
});
gv_vAlignTable['u220'] = 'center';document.getElementById('u131_img').tabIndex = 0;

u131.style.cursor = 'pointer';
$axure.eventManager.click('u131', function(e) {

if (true) {

	self.location.href='#';

}
});
gv_vAlignTable['u174'] = 'center';gv_vAlignTable['u119'] = 'top';gv_vAlignTable['u51'] = 'center';gv_vAlignTable['u182'] = 'top';u258.tabIndex = 0;

u258.style.cursor = 'pointer';
$axure.eventManager.click('u258', function(e) {

if (true) {

	SetPanelState('u193', 'pd2u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u258'] = 'top';gv_vAlignTable['u226'] = 'top';u144.tabIndex = 0;

u144.style.cursor = 'pointer';
$axure.eventManager.click('u144', function(e) {

if (true) {

SetWidgetFormText('u136', 'search query four');

	SetPanelVisibility('u139','hidden','none',500);

}
});
gv_vAlignTable['u144'] = 'top';gv_vAlignTable['u229'] = 'top';gv_vAlignTable['u227'] = 'top';gv_vAlignTable['u82'] = 'center';gv_vAlignTable['u36'] = 'center';gv_vAlignTable['u101'] = 'center';gv_vAlignTable['u95'] = 'center';gv_vAlignTable['u61'] = 'center';u143.tabIndex = 0;

u143.style.cursor = 'pointer';
$axure.eventManager.click('u143', function(e) {

if (true) {

	SetPanelVisibility('u139','hidden','none',500);

}
});
gv_vAlignTable['u143'] = 'top';gv_vAlignTable['u240'] = 'top';gv_vAlignTable['u180'] = 'top';gv_vAlignTable['u74'] = 'center';gv_vAlignTable['u123'] = 'center';gv_vAlignTable['u20'] = 'center';gv_vAlignTable['u163'] = 'center';document.getElementById('u253_img').tabIndex = 0;

u253.style.cursor = 'pointer';
$axure.eventManager.click('u253', function(e) {

if (true) {

	SetPanelState('u193', 'pd1u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u157'] = 'center';gv_vAlignTable['u151'] = 'top';document.getElementById('u92_img').tabIndex = 0;

u92.style.cursor = 'pointer';
$axure.eventManager.click('u92', function(e) {

if (true) {

	SetPanelState('u9', 'pd3u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u34'] = 'center';gv_vAlignTable['u198'] = 'top';document.getElementById('u217_img').tabIndex = 0;

u217.style.cursor = 'pointer';
$axure.eventManager.click('u217', function(e) {

if (true) {

	SetPanelState('u193', 'pd2u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u223'] = 'top';gv_vAlignTable['u43'] = 'center';gv_vAlignTable['u160'] = 'center';u257.tabIndex = 0;

u257.style.cursor = 'pointer';
$axure.eventManager.click('u257', function(e) {

if (true) {

	SetPanelState('u193', 'pd1u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u257'] = 'top';gv_vAlignTable['u169'] = 'center';gv_vAlignTable['u112'] = 'center';gv_vAlignTable['u244'] = 'top';u142.tabIndex = 0;

u142.style.cursor = 'pointer';
$axure.eventManager.click('u142', function(e) {

if (true) {

SetWidgetFormText('u136', 'search query three');

	SetPanelVisibility('u139','hidden','none',500);

}
});
gv_vAlignTable['u142'] = 'top';gv_vAlignTable['u212'] = 'top';gv_vAlignTable['u191'] = 'top';gv_vAlignTable['u236'] = 'top';gv_vAlignTable['u87'] = 'top';gv_vAlignTable['u53'] = 'center';HookHover('u152', false);
gv_vAlignTable['u228'] = 'top';gv_vAlignTable['u121'] = 'center';document.getElementById('u67_img').tabIndex = 0;

u67.style.cursor = 'pointer';
$axure.eventManager.click('u67', function(e) {

if (true) {

	SetPanelState('u9', 'pd3u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u116'] = 'center';gv_vAlignTable['u155'] = 'center';gv_vAlignTable['u109'] = 'center';gv_vAlignTable['u84'] = 'center';gv_vAlignTable['u18'] = 'center';gv_vAlignTable['u171'] = 'top';gv_vAlignTable['u97'] = 'center';gv_vAlignTable['u130'] = 'center';gv_vAlignTable['u76'] = 'center';gv_vAlignTable['u134'] = 'center';gv_vAlignTable['u249'] = 'center';gv_vAlignTable['u190'] = 'top';document.getElementById('u255_img').tabIndex = 0;

u255.style.cursor = 'pointer';
$axure.eventManager.click('u255', function(e) {

if (true) {

	SetPanelState('u193', 'pd2u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u177'] = 'top';gv_vAlignTable['u118'] = 'center';gv_vAlignTable['u64'] = 'center';gv_vAlignTable['u185'] = 'center';gv_vAlignTable['u26'] = 'center';document.getElementById('u250_img').tabIndex = 0;

u250.style.cursor = 'pointer';
$axure.eventManager.click('u250', function(e) {

if (true) {

	SetPanelState('u193', 'pd1u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u234'] = 'top';u147.tabIndex = 0;

u147.style.cursor = 'pointer';
$axure.eventManager.click('u147', function(e) {

if (true) {

SetWidgetFormText('u136', 'search query one');

	SetPanelVisibility('u139','hidden','none',500);

}
});
gv_vAlignTable['u147'] = 'top';gv_vAlignTable['u167'] = 'center';gv_vAlignTable['u91'] = 'center';document.getElementById('u19_img').tabIndex = 0;

u19.style.cursor = 'pointer';
$axure.eventManager.click('u19', function(e) {

if (true) {

	SetPanelState('u9', 'pd0u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u70'] = 'center';gv_vAlignTable['u188'] = 'top';gv_vAlignTable['u204'] = 'top';gv_vAlignTable['u141'] = 'center';gv_vAlignTable['u89'] = 'center';
u247.style.cursor = 'pointer';
$axure.eventManager.click('u247', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('申请提交成功记录hj.html');

}
});

$axure.eventManager.keyup('u136', function(e) {

if ((GetWidgetText('u136')) == ('')) {

	SetPanelVisibility('u139','hidden','none',500);

}
else
if (true) {

	SetPanelVisibility('u139','','none',500);

}
});
gv_vAlignTable['u194'] = 'top';gv_vAlignTable['u132'] = 'center';gv_vAlignTable['u260'] = 'center';gv_vAlignTable['u150'] = 'top';gv_vAlignTable['u86'] = 'center';gv_vAlignTable['u11'] = 'center';gv_vAlignTable['u201'] = 'top';gv_vAlignTable['u66'] = 'center';u145.tabIndex = 0;

u145.style.cursor = 'pointer';
$axure.eventManager.click('u145', function(e) {

if (true) {

SetWidgetFormText('u136', 'search query five');

	SetPanelVisibility('u139','hidden','none',500);

}
});
gv_vAlignTable['u145'] = 'top';gv_vAlignTable['u153'] = 'center';gv_vAlignTable['u178'] = 'top';gv_vAlignTable['u16'] = 'center';gv_vAlignTable['u251'] = 'center';document.getElementById('u15_img').tabIndex = 0;

u15.style.cursor = 'pointer';
$axure.eventManager.click('u15', function(e) {

if (true) {

	SetPanelState('u9', 'pd2u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u49'] = 'center';gv_vAlignTable['u233'] = 'top';gv_vAlignTable['u80'] = 'center';gv_vAlignTable['u1'] = 'center';gv_vAlignTable['u254'] = 'center';u148.tabIndex = 0;

u148.style.cursor = 'pointer';
$axure.eventManager.click('u148', function(e) {

if (true) {

SetWidgetFormText('u136', 'search query two');

	SetPanelVisibility('u139','hidden','none',500);

}
});
gv_vAlignTable['u148'] = 'top';gv_vAlignTable['u93'] = 'center';gv_vAlignTable['u165'] = 'top';gv_vAlignTable['u12'] = 'top';gv_vAlignTable['u200'] = 'top';
u211.style.cursor = 'pointer';
$axure.eventManager.click('u211', function(e) {

if ((GetGlobalVariableValue('23')) == ('1')) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('申请提交成功记录hj.html');

}

if ((GetGlobalVariableValue('23')) == ('2')) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('支付页面.html');

}
});
document.getElementById('u248_img').tabIndex = 0;

u248.style.cursor = 'pointer';
$axure.eventManager.click('u248', function(e) {

if (true) {

	SetPanelState('u193', 'pd2u193','none','',500,'none','',500);

}
});
gv_vAlignTable['u59'] = 'center';document.getElementById('u137_img').tabIndex = 0;

u137.style.cursor = 'pointer';
$axure.eventManager.click('u137', function(e) {

if (true) {

	self.location.href='';

}
});
document.getElementById('u90_img').tabIndex = 0;

u90.style.cursor = 'pointer';
$axure.eventManager.click('u90', function(e) {

if (true) {

	SetPanelState('u9', 'pd2u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u22'] = 'center';document.getElementById('u13_img').tabIndex = 0;

u13.style.cursor = 'pointer';
$axure.eventManager.click('u13', function(e) {

if (true) {

	SetPanelState('u9', 'pd1u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u232'] = 'top';gv_vAlignTable['u107'] = 'center';document.getElementById('u69_img').tabIndex = 0;

u69.style.cursor = 'pointer';
$axure.eventManager.click('u69', function(e) {

if (true) {

	SetPanelState('u9', 'pd0u9','none','',500,'none','',500);

}
});
gv_vAlignTable['u197'] = 'top';gv_vAlignTable['u237'] = 'top';gv_vAlignTable['u28'] = 'center';