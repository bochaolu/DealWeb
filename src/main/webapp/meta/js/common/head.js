$(function() {

});

// 岗位下拉变动
function postChange(postId) {
	//var postId = $("#postId").val();
	var url = BASE_PATH + "/user/changePost";
	var params = {
		selectedPostId : postId
	};
	ajaxGet(url, params, function(data) {
		var returnVal = data.returnValue;
		window.location.href = BASE_PATH + returnVal;
	}, function(data) {
	});
}

// 顶部菜单点击
function menuClick(authId, authName, actionUrl) {
	var url = BASE_PATH + "/user/clickmenu";
	var params = {
		authId : authId,
		authName : authName,
		actionUrl : actionUrl
	};

	ajaxGet(url, params, function(data) {
	}, function(data) {
	});
}
