$(function() {

});

// 左侧级菜单点击
function leftMenuClick(authId, authName) {

	var url = BASE_PATH + "/user/setleftmenu";

	//TODO,暂时写死
	if (authId == 16) {

		authId = 27;

	} 
//	else if (authId == 17) {
//
//		authId = 31;
//
//	} else if (authId == 18) {
//
//		authId = 36;
//	}

	var params = {
		authId : authId,
		authName : authName
	};

	submitSave(url, params, function() {
	}, function() {
	});
}

/**
 * 隐藏展开三级菜单
 */
function expandThreeMenu(self) {

	// 收起三级菜单
	$(self).closest("div.men").find("ul").addClass("hide");

	var findUl = $(self).closest("div").find("ul");

	var isHideThreeMenu = $(findUl).hasClass("hide");
	if (isHideThreeMenu) {
		$(findUl).removeClass("hide");
	} else {
		$(findUl).addClass("hide");
	}

}