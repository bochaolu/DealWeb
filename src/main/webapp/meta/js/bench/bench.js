/** ************************公共部分***************************** */
$(function() {

	// 初始化
	
	// 绑定回车事件
	enterSubmit('#loginForm', function() {
		Login.login();
	});

});

/** **********************登录******************************* */
Login = function() {
};

//用户登录
Login.login = function() {

	// 校验输入信息
	if (checkLogin()) {
		
		var formId = "loginForm";
		var url = BASE_PATH + "/login";

		httpPost(formId, url, function(data) {

			window.location.href = BASE_PATH + "/bench";

		}, function(data) {
			
			// 返回码
			var returnCode = data.returnCode;

			var returnMsg = data.returnMsg;
			
			var tip = "<i class='fa fa-times-circle'></i>  ";
			$('.text-danger').removeClass("hide").empty().html(tip + returnMsg)
					.show();
			$('#randCodeLi').show();
			changeRandCode();
		});
	}

};

// 校验登录信息
checkLogin = function() {

	var username = $("#username").val();
	var password = $("#password").val();
	var randCode = $("#randCode1").val();

	var tip = "<i class='fa fa-minus-circle'></i>  ";

	if (!username) {
		$('.text-danger').removeClass("hide").empty().html(tip + "用户名不能为空!")
				.show();

		return false;
	} else {
		$(".text-danger").hide();
	}

	if (!password) {
		$('.text-danger').removeClass("hide").empty().html(tip + "密码不能为空!")
				.show();
		return false;
	} else {
		$(".text-danger").hide();
	}
	var randCodeDisplay = $('#randCodeLi').css('display');
	if (randCodeDisplay != 'none' && $.trim(randCode) == "") {
		Dialog.alertError("验证码不正确");
		return false;
	}
	// if (loginErrorCount >= 1 && (randCode == "" || randCode ==
	// $("#randCode1").attr('placeholder'))) {
	// $('.text-danger').removeClass("hide").empty().html(tip +
	// "请输入验证码!").show();
	// return false;
	// } else {
	// $(".text-danger").hide();
	// }

	return true;
};

/** **********************公共部分******************************* */
/**
 * 绑定回车提交
 * 
 * @param formId
 * @param callback
 */
function enterSubmit(form, callback) {
	// 绑定回车
	$('body').bind(
			'keydown',
			function() {
				var keyCode = event.keyCode ? event.keyCode
						: event.which ? event.which : event.charCode;
				if (keyCode == '13') {
					Login.login()
					if (callback) {
						callback();
					}
				}
			});
}