/** ************************公共部分***************************** */
$(function() {

});

/** **********************登录******************************* */
User = function() {
	dialog: null;
};

/**
 * 获取手机验证码
 */
User.getAuthCode = function() {

	var url = BASE_PATH + '/code';

	var userCode = $("#userCode").val();

	if (!userCode) {
		return false;
	}

	var params = {
		userCode : userCode
	};

	systemLoading("", true);

	restPost(url, params, function(data) {

		$("#captcha").hide();
		$("#resend").show();
		User.timedown();

		systemLoaded();

	}, function(data) {

		$("#authCodeTip").find("b").empty().html("获取验证码失败！");

		systemLoaded();
	});

};

// 发送验证码--倒数*秒
User.timedown = function(time) {

	if ((time == undefined || time == "") && time != 0) {
		time = 60;
	}
	$("#second").html(time);
	time = time - 1;
	if (time >= 0) {
		setTimeout(function() {
			User.timedown(time);
		}, 1000);
	} else {
		$("#captcha").text("重新发送").show();
		$("#resend").hide();
	}
};

/**
 * 重置密码
 */
User.toResetPwd = function() {

	var url = BASE_PATH + '/pwd';
	var title = '重置密码';

	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
		orderName : orderName,
		orderType : orderType
	};

	var dialogOptions = {

		width : 800,
		/* height : 760, */

		ok : function() {

			// 确定
			var reback = User.resetPwd();

			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		dialog.position('50%', '25%');

		User.dialog = dialog;

	}, dialogOptions);

};

/**
 * 重置密码
 */
User.resetPwd = function() {
	
	var userCode = $("#userCode").val();
	var authCode = $('#authCode').val();
	var password = $('#newPwd').val();

	var url = BASE_PATH + '/pwd';

	var params = {
		userCode : userCode,
		authCode : authCode,
		password : password
	};

	// 校验输入信息
	if (Validator.validForm("resetPwdForm")) {
	
		$("#errorTip").find(".fc-warning").empty();
		restPost(url, params, function(data) {			
			Dialog.alertInfo("密码重置成功", true);
			setTimeout(
					function() {
						location.reload();
					}, 1500);
		}, function(data) {
			var msg = data.returnMsg;
			if(!msg){
				msg = "密码重置失败";
			}
			
			$("#errorTip").find(".fc-warning").empty().html(msg);
			
			systemLoaded();
		});
	} else {
		systemLoaded();
	}
	
	return false;

};

// 关闭，取消
User.close = function() {
	User.dialog.close();
};