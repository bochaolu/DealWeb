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

// 用户登录
Login.login = function() {

	
	systemLoading('#loginForm', true);
	
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

			$('.text-red').empty().html(returnMsg).show();
			$('#randCodeLi').show();
			changeRandCode();
			
			systemLoaded();
		});
	} else {
		systemLoaded();
	}
	// 解除loading
	systemLoaded('#loginForm', false);

};

// 校验登录信息
checkLogin = function() {

	var username = $("#username").val();
	var password = $("#password").val();
	var randCode = $("#randCode1").val();

	if (!username) {
		$('.text-red').empty().html("用户名不能为空!").show();
		return false;
	} else {
		$(".text-red").hide();
	}

	if (!password) {
		$('.text-red').empty().html("密码不能为空!").show();
		return false;
	} else {
		$(".text-red").hide();
	}

	var randCodeDisplay = $('#randCodeLi').css('display');
	if (randCodeDisplay != 'none' && $.trim(randCode) == "") {
		$('.text-red').empty().html("请输入验证码!").show();
		return false;
	}

//	if (randCode == "" || randCode == $("#randCode1").attr(
//					'placeholder')) {
//		$('.text-red').empty().html("请输入验证码!").show();
//		return false;
//		
//	} else {
//		$(".text-red").hide();
//	}
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
					Login.login();
					if (callback) {
						callback();
					}
				}
			});
}