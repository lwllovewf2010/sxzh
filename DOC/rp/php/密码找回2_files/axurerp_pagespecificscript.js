for(var i = 0; i < 43; i++) { var scriptId = 'u' + i; window[scriptId] = document.getElementById(scriptId); }

$axure.eventManager.pageLoad(
function (e) {

if ((GetGlobalVariableValue('OnLoadVariable')) == ('1')) {

}

if ((GetGlobalVariableValue('OnLoadVariable')) == ('2')) {

}

});
gv_vAlignTable['u16'] = 'center';gv_vAlignTable['u17'] = 'top';gv_vAlignTable['u29'] = 'top';gv_vAlignTable['u30'] = 'top';gv_vAlignTable['u6'] = 'top';u42.tabIndex = 0;

u42.style.cursor = 'pointer';
$axure.eventManager.click('u42', function(e) {

if (true) {

	SetPanelState('u28', 'pd1u28','none','',500,'none','',500);

}
});
gv_vAlignTable['u42'] = 'top';gv_vAlignTable['u13'] = 'top';gv_vAlignTable['u14'] = 'top';gv_vAlignTable['u41'] = 'center';gv_vAlignTable['u11'] = 'center';gv_vAlignTable['u1'] = 'center';
u38.style.cursor = 'pointer';
$axure.eventManager.click('u38', function(e) {

if (true) {

	SetPanelVisibility('u39','','none',500);

}
});
gv_vAlignTable['u12'] = 'top';gv_vAlignTable['u26'] = 'center';gv_vAlignTable['u9'] = 'center';gv_vAlignTable['u3'] = 'center';u32.tabIndex = 0;

u32.style.cursor = 'pointer';
$axure.eventManager.click('u32', function(e) {

if (true) {

	SetPanelState('u28', 'pd0u28','none','',500,'none','',500);

}
});
gv_vAlignTable['u32'] = 'top';gv_vAlignTable['u24'] = 'center';gv_vAlignTable['u18'] = 'top';gv_vAlignTable['u19'] = 'top';gv_vAlignTable['u36'] = 'top';gv_vAlignTable['u5'] = 'center';gv_vAlignTable['u22'] = 'center';
u37.style.cursor = 'pointer';
$axure.eventManager.click('u37', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('密码找回3.html');

}
});
gv_vAlignTable['u33'] = 'top';
u31.style.cursor = 'pointer';
$axure.eventManager.click('u31', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('邮箱验证提示_2.html');

}
});
gv_vAlignTable['u34'] = 'top';