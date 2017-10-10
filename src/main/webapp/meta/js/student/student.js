/** ************************公共部分***************************** */
$(function() {

});

/** **************************demo js*************************** */
Student = function() {
};

/**
 * 创建
 */
Student.add = function() {

	var url = BASE_PATH + '/students';

	// 页面loading
	systemLoading("", true);

	// 校验输入信息
	if (Validator.validForm("stuAddForm")) {

		httpPost('stuAddForm', url, function(data) {

			location.href = BASE_PATH + "/students";

		}, function(data) {
			Dialog.alertError(data.returnMsg);

			// 解除loading
			systemLoaded();
		});
	} else {
		// 解除loading
		systemLoaded();
	}

};

/**
 * 修改
 */
Student.update = function() {

	var id = $("#id").val();

	var url = BASE_PATH + '/students/' + id;

	var params = $("#stuEditForm").serialize();

	// 页面loading
	systemLoading("", true);

	// 校验输入信息
	if (Validator.validForm("stuEditForm")) {
		httpPut(url, params, function(data) {

			location.href = BASE_PATH + "/students";

		}, function(data) {
			Dialog.alertError(data.returnMsg);

			// 解除loading
			systemLoaded();
		});
	} else {
		// 解除loading
		systemLoaded();
	}
};

/**
 * 删除
 */
Student.remove = function(id) {

	var result = "是否确定删除此学生?</h3>";

	var params = {
		'id' : id
	};

	Dialog.confirm(result, function() {

		// 页面loading
		systemLoading("", true);

		httpDelete(BASE_PATH + "/students/" + id, params, function(data) {

			// 刷新页面
			location.href = BASE_PATH + "/students";

		}, function(data) {
			Dialog.alertError(data.returnMsg);

			// 解除loading
			systemLoaded();
		});
	});
};