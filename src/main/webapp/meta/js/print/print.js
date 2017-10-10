/** ************************公共部分***************************** */
$(function() {

	//load 外部页面
	loadOutPage();
	
	//二维码
	qrCode();
});

/** **************************demo js*************************** */

//load 外部页面
function loadOutPage(){
	
	var url = BASE_PATH + $("#myUrl").val();
	
	var param = {
			
	};
	
	ajaxLoad("outPage",url,param,function(){
		
	});
}


/**
 * 打印
 */
function printHtml(self){
	
	//隐藏按钮
	$(self).hide();
	
	//window打印
	window.print();
	
}

/**
 * 二维码
 */
function qrCode(){
	$('#btn-print').on('click', function(){
		window.print();
		return false;
	});

	var QRdiv = document.createElement('div');
	var width = 140;
	$(QRdiv).qrcode({
		width: width,
		height: width,
		text: 'test qrcode',
		complete: function(){
//			$('#qrcode').html('<img id="QRimg" '+ (this.isRetina ? 'style="width:'+ (width) +'px;height:'+ (width) +'px;"' : '') +' src="'+ this.toDataURL("image/png") +'"><p>手机扫一扫</p><p>查看订单详情</p>');
			$('#qrcode').html('<img id="QRimg" '+ 'style="width:140px;height:140px;"'+' src="'+BASE_PATH+"/meta/images/qrcode_258.jpg" +'"><p>手机扫一扫</p><p>查看订单详情</p>');
		}
	});
}
/**
 * 禁止右键
 * @param oEvent
 */
function block(oEvent){
	if(window.event)
	{
		oEvent=window.event;
		oEvent.returnValue=false; //取消默认事件
	}else
		oEvent.preventDefault();//取消默认事件
}
document.oncontextmenu=block;

//禁止F5刷新
document.onkeydown = function (e) {
var ev = window.event || e;
var code = ev.keyCode || ev.which;
	if (code == 116) {
	ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
	cancelBubble = true;
	return false;
	}
};