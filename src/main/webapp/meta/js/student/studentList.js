/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	initList();
});

/****************************demo js*************************** */
Student = function() {
	dialog : null;
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('studentForm', 'LoadCxt', BASE_PATH + '/students/q', function() {

		pageInfo("studentForm", function() {
			initList();
		});

	});
};

/**
 * 查询
 * 
 */
Student.search = function() {
	$('#curPage').val('1');
	initList();
};

/**
 * 弹窗测试
 */
Student.toView = function(id) {

	var url = BASE_PATH + '/students/t/' + id;
	var title = '学生信息预览';

	var params = {
	};

	var dialogOptions = {

			/*width : 800,
		 height : 760, */

		ok : function() {

			// 确定
			var reback = Student.save();

			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		//dialog.position('50%', '25%');

		Student.dialog = dialog;

	}, dialogOptions);

};

/**
 * 返回、取消
 */
Student.back = function() {

	location.href = BASE_PATH + "/students";

};

/**
 * 关闭弹窗
 */
Student.close = function() {
	Student.dialog.close();
};