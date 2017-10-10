/**   
 * 全局公共js ,封装ajax、提示框等
 * @author (li_xiaodong)
 * @date 2016年02月16日 下午3:25:18
 */

/**
 * HTTP GET
 * 
 * @param formId
 * @param boxId
 * @param url
 * @param callBack
 * @returns {Boolean}
 */
function httpGet(formId, boxId, url, callBack) {

	systemLoading('#' + boxId, true);

	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}

	// 没有form的时候
	if (!$('#' + formId).attr('id')) {

		$('#' + boxId).load(url, function() {
			systemLoaded('#' + boxId);
			// 表单聚焦验证
			Validator.onblur($(document));
			// 绑定全局的时间
			GlobalEvent.init();
			if (callBack) {
				callBack();
			}
		});
		return;
	}

	var options = { // ajaxform表单提交设置
		target : '#' + boxId, // 结果显示目标//
		url : url, // action目标
		type : 'GET',
		success : function() {
			systemLoaded('#' + boxId);
			// 表单聚焦验证
			 Validator.onblur($(document));
			// 绑定全局的时间
			GlobalEvent.init();
			if (callBack) {
				callBack();
			}
		},
		error : function() {
			systemLoaded('#' + boxId);
		}
	};

	$('#' + formId).ajaxSubmit(options);
	return false;
}

/**
 * HTTP POST
 * 
 * @param formId
 * @param url
 * @param successback
 * @param failback
 * @returns {Boolean}
 */
function httpPost(formId, url, successback, failback) {

	if (!formId) {
		alert('formId不能为空！');
		return false;
	}

	// 提交前默认值清空
	$('#' + formId).find("input[placeholder],textarea[placeholder]").each(
			function() {
				if ($(this).val() == $(this).attr('placeholder')) {
					$(this).val('');
				}
			});
	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	var options = { // ajaxform表单提交设置
		url : url, // action目标
		type : 'POST',
		dataType : "json",
		success : function(data) {
			if (data && data.returnCode == '200') {
				if (successback) {
					successback(data);
				}
				return true;
			}
			if (failback) {
				failback(data);
			}
			return false;
		}
	};
	$('#' + formId).ajaxSubmit(options);
	return true;

}

/**
 * HTTP PUT
 * 
 * @param url
 * @param params
 * @param successback
 * @param failback
 * @returns {Boolean}
 */
function httpPut(url, params, successback, failback) {

	$.ajax({
		url : url,
		data : params,
		type : 'PUT',
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
				if (successback) {
					successback(data);
				}
				return;
			}
			if (failback) {
				failback(data);
			}
		}
	});
	return true;
}

/**
 * HTTP DELETE
 * 
 * @param url
 * @param params
 * @param successback
 * @param failback
 * @returns {Boolean}
 */
function httpDelete(url, params, successback, failback) {

	$.ajax({
		url : url,
		data : params,
		type : 'DELETE',
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
				if (successback) {
					successback(data);
				}
				return;
			}
			if (failback) {
				failback(data);
			}
		}
	});
	return true;
}

/**
 * HTTP POST rest
 * 
 * @param formId
 * @param url
 * @param successback
 * @param failback
 * @returns {Boolean}
 */
function restPost(url, params, successback, failback) {

	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	$.ajax({
		url : url,
		data : params,
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
				if (successback) {
					successback(data);
				}
				return;
			}
			if (failback) {
				failback(data);
			}
		}
	});
	return true;

}



function ajaxGet(url, params, successback, failback) {
	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	$.ajax({
		url : url,
		data : params,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
				if (successback) {
					successback(data);
				}
				return;
			}
			if (failback) {
				failback(data);
			}
		}
	});
	return true;
}



/**
 * 
 * @param formId
 * @param boxId
 * @param url
 * @param callBack
 * @returns {Boolean}
 */

Dialog = {
	systemCode : {
		'499' : '后台异常!',
		'500' : '后台异常!',
		'502' : '数据异常!',
		'503' : '数据重复!',
		'508' : '数据违反长度约束!',

		'900' : '会话失效!',
		'901' : '参数错误!',
		'902' : '操作异常!',
		'903' : '文件过大!',
		'904' : '文件格式错误!'
	}
};

Dialog.alertErrorCodeMsg = function(returnCode) {
	var msg = Dialog.systemCode[returnCode];
	if (msg) {
		Dialog.alertError(msg);
	}
};
Dialog.alertInfo = function(ctt, autoClose) {
	var infoDialogOp = {
		id : 'sysInfo',
		title : false,
		content : '<div class="sysAlert showSweetAlert"><h2>' + ctt
				+ '</h2></div>',
		lock : true,
		okVal : '确定',
		ok : true
	};
	if (autoClose) {
		infoDialogOp.title = false;
		infoDialogOp.ok = false;
	}
	var dialog = $.dialog(infoDialogOp);
	if (autoClose) {
		setTimeout(function() {
			dialog.close();
		}, 1000);
	}
};
Dialog.alertSuccess = function(ctt) {
	var des = ctt ? ctt : '操作成功';
	$('#sysSuccDes').empty().html(" " + des);
	$('#sysSuccDiv').show();
	setTimeout(function() {
		$('#sysSuccDiv').slideUp('normal');
	}, 800);
};

Dialog.alertError = function(ctt) {
	$
			.dialog({
				id : 'sysError',
				title : false,
				content : '<div class="sysAlert showSweetAlert"><div class="icon error animateErrorIcon"><i class="icon-remove-sign"></i></div><h2>'
						+ ctt + '</h2></div>',
				lock : true,
				okVal : '关闭',
				ok : true
			});
};
/**
 * 确认
 * 
 * @param content
 * @param yes
 * @param no
 * @param parent
 */
Dialog.confirm = function(ctt, yes, no) {
	$
			.dialog({
				id : 'sysConfirm',
				title : false,
				content : '<div class="sysAlert showSweetAlert"><div class="icon warning animateErrorIcon"><i class="icon-question-sign"></i></div><div>'
						+ ctt + '</div></div>',
				lock : true,
				okVal : '确定',
				ok : function() {
					yes ? yes() : $.noop();
					return true;
				},
				cancelVal : '取消',
				cancel : function(){
					no ? no() : $.noop();
					return true;
				}
			});
};
/**
 * dialog插件自带的锁屏层显示
 */
Dialog.showLockMask = function() {
	$('#ldg_lockmask').show();
};

/**
 * ajax异步请求打开弹出框
 * 
 * @param url
 *            打开的url地址
 * @param params
 *            参数
 * @param title
 *            标题
 * @param ajaxCallback
 *            ajax回调函数 callback(dialog,resData)
 * @param dialogOptions
 *            dialog插件的dialog传入参数对象{width:123,height:456...}
 */
Dialog.ajaxOpenDialog = function(url, params, title, ajaxCallback,
		dialogOptions) {
	systemLoading(null, true);
	dialogOptions = dialogOptions || {};
	dialogOptions.title = false;// title?title:"操作";
	dialogOptions.lock = true;
	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	$.ajax({
		url : url,
		data : params,
		type : "GET",
		success : function(data) {
			dialogOptions.content = data;
			var dialog = $.dialog(dialogOptions);
			$('.sysCloseBtn').eq(0).click(function() {
				dialog.close();
			});
			// 表单聚焦验证
			 Validator.onblur($(document));
			// 绑定全局的时间
			GlobalEvent.init();
			systemLoaded();
			if (ajaxCallback) {
				ajaxCallback(dialog, data);
			}
		}
	});
};
Dialog.ajaxSubmitFormOpen = function(formId, url, title, ajaxCallback,
		dialogOptions) {
	systemLoading(null, true);
	// 提交前清空
	$('#' + formId).find("input[placeholder]").each(function() {
		if ($(this).val() == $(this).attr('placeholder')) {
			$(this).val('');
		}
	});
	dialogOptions = dialogOptions || {};
	dialogOptions.title = false;// title?title:"操作";
	dialogOptions.lock = true;
	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	$("#" + formId).ajaxSubmit({
		url : url,
		type : "POST",
		success : function(data) {
			dialogOptions.content = data;
			var dialog = $.dialog(dialogOptions);
			// 表单聚焦验证
			Validator.onblur($(document));
			// 绑定全局的时间
			GlobalEvent.init();
			systemLoaded();
			if (ajaxCallback) {
				ajaxCallback(dialog, data);
			}
		},
		error : function() {
			Dialog.alertError("操作失败!");
		}
	});
};

// 随机码
function rnd() {
	var random = Math.floor(Math.random() * 10001);
	var id = (new Date().getTime() * random).toString();
	id = id.split('').reverse().join('');
	return 'randomId=' + id;
}

/**
 * 系统显示加载中
 * 
 * @param jquery选择符
 * @param isLock
 *            是否锁屏
 * @param desc
 *            加载的描述字符
 */
function systemLoading(selector, isLock, desc) {
	var isBodySel = selector || true;
	selector = selector || 'body';
	var container = $(selector);
	// 控制弹出层和loading的层的z-index
	var ldgMask = $('#ldg_lockmask');
	var ldgMaskIndex = 1976;
	if (ldgMask.length > 0) {
		ldgMaskIndex = parseInt($('#ldg_lockmask').css('z-index'));
	}
	var width = container.width();
	var height = container.height();

	container
			.each(function() {
				// loading层控制
				var dataLoadingDiv = $(selector + ' > .sys_loading');
				if (dataLoadingDiv.length <= 0) {
					dataLoadingDiv = $('<div class="sys_loading"><img src="'
							+ BASE_PATH
							+ '/meta/js/common/dialog/skins/icons/loading.gif"><span class="sys_loading_des">'
							+ (desc ? desc : '加载中请稍候...') + '</span></div>');
					$(this).addClass('pos_rel').append(dataLoadingDiv);
				} else {
					$(this).addClass('pos_rel').append(dataLoadingDiv);
				}
				// 修改样式位置
				var loadingWidth = $(selector + ' > .sys_loading').width();
				var leftPencent = parseInt(((width / 2.0 - loadingWidth / 2.0) / width) * 100);

				var loadingHeight = $(selector + ' > .sys_loading').height();
				var topPencent = isBodySel ? parseInt(((height / 2.0 - loadingHeight / 2.0) / height) * 100)
						: 25;
				dataLoadingDiv.css({
					left : leftPencent + '%',
					top : topPencent + '%',
					'z-index' : ldgMaskIndex + 8
				});

				// 控制显示容器最小高度
				if (loadingHeight > height) {
					$(this).addClass('sysLoadingMinHeight');
				}

				// 锁屏层
				var dataMask = $(selector + ' > .sys_masklock');
				if (isLock && dataMask.length <= 0) {
					dataMask = $('<div class="sys_masklock"></div>');
					$(this).append(dataMask);
					dataMask.css({
						'z-index' : ldgMaskIndex + 7
					});
					dataMask.show();
					dataLoadingDiv.show();
				} else if (isLock && dataMask.length == 1) {
					dataMask.css({
						'z-index' : ldgMaskIndex + 7
					});
					dataMask.show();
					dataLoadingDiv.show();
				}
				// dataMask.css({'z-index':ldgMaskIndex+7});
				// dataMask.show();
				// dataLoadingDiv.show();
			});
}
/**
 * 系统加载完成
 */
function systemLoaded(selector) {
	selector = selector || 'body';
	var container = $(selector);
	container.each(function() {
		$(this).removeClass('sysLoadingMinHeight').removeClass('pos_rel');
		;
		$(selector + ' > .sys_loading').hide();
		$(selector + ' > .sys_masklock').hide();
	});
}
// ////////////////////////ajax 初始化全局共用信息/////////////////////////////
$(function() {
	// //返回顶部
	/* $.scrollUp({scrollText:'<i class="icon-chevron-up"></i>'}); */
	// //默认操作提示
	$('body')
			.append(
					'<div class="mtip-success" id="sysSuccDiv"><i class="icon-ok-sign"></i><span id="sysSuccDes"></span></div>');
	// $('body').append('<div class="sysBtnTip"><span class="arrow"></span><span
	// id="tipVal">公众号</span></div>');
	// $('body').append('<div id="helpBtn" class="go-top"><a
	// href="javascript:void(0)" onclick="View.viewHelpGuide();"
	// class="help"></a></div>');
	// 对话框默认全局设置
	$.dialog.setting.lock = true;
	$.dialog.setting.min = false;
	$.dialog.setting.max = false;
	$.dialog.setting.title = false;
	// 表单聚焦验证
	 Validator.onblur($(document));
	// 绑定全局的时间
	GlobalEvent.init();
	// 全局菜单设定
	GlobalMenu.init();
	$(document).ajaxSuccess(function(event, XMLHttpRequest, ajaxOptions) {
		// 此处有bug。 应该直接判断返回结果是否是json，而不是通过dataType来判断。有可能dataType会省略。
		if (ajaxOptions.dataType == 'json' && XMLHttpRequest != null) {
			var rs = eval('(' + XMLHttpRequest.responseText + ')');
			if (rs.returnType == '0') {
				// 如果returnCode=200，表示操作正常完成
				if (rs.returnCode == '200') {
					rs.returnMsg = rs.returnMsg ? rs.returnMsg : '操作成功';
					Dialog.alertSuccess(rs.returnMsg);
				} else { // 如果非等于200，代表操作失败。则给出操作失败的原因。如：因为有关系数据不能正常删除等。
					rs.returnMsg = rs.returnMsg ? rs.returnMsg : '操作失败';
					Dialog.alertError(rs.returnMsg);
				}
			}
		}
	});
	$(document).ajaxError(
			function(event, jqxhr, settings, exception) {
				systemLoaded();
				var resStatus = jqxhr.getResponseHeader('status');
				if (resStatus == '900') {
					window.location.href = BASE_PATH;
				} else if (resStatus == '905') {
					// 表单重复提交不做任何处理
				} else {
					var readyState = jqxhr.readyState - 0;
					var status = jqxhr.status;
					var statusText = jqxhr.statusText;
					typeof console != 'undefined' ? console.info('readyState:'
							+ readyState + ',status:' + status + ',statusText:'
							+ statusText) : $.noop();
					if (readyState < 3 && readyState > 0) {
						Dialog.alertError("网络异常!");
					}
					/*
					 * if(readyState==4){ Dialog.alertError("后台异常!"); }
					 */
				}
			});
});
GlobalMenu = {};
GlobalMenu.init = function() {
	// 需要初始化的菜单
	GlobalMenu.mainMenuInit();

};
/**
 * 主菜单初始化
 */
GlobalMenu.mainMenuInit = function() {
	
	//顶部菜单选中
	if(typeof navSelectId!='undefined' && navSelectId){
		$('li[id^="nav"]').removeClass('active');
		$('#nav'+navSelectId).addClass('active');
	}
	
	//左边菜单选中
	if(typeof leftMenuSelectId!='undefined' && leftMenuSelectId){
		
		//选中二级菜单
		$('.col-md-2 li').removeClass('ho');
		$('#leftMenu'+leftMenuSelectId).addClass('ho');
	}
	
	
	//导航信息生成
//	if(typeof navigationItems !='undefined'){
//		var navItems = $.parseJSON(navigationItems);
//		if(navItems && navItems.length>0){
//			var html = '<ul>';
//			for ( var index in navItems) {
//				var href = navItems[index].href;
//				var name = navItems[index].name;
//				href = href?href:'javascript:void(0);';
//				name = name?name:'';
//				var tag = '<li>';
//				if(name){
//					tag += '<a href="'+href+'">'+name+'</a>';
//				}
//				if(index < navItems.length-1 && name) {
//					tag += ' <i class="icon-angle-right"></i>';
//				}
//				tag+='</li>';
//				html+=tag;
//			}
//			html+='</ul>';
//			$(".breadcrumb").empty().html(html);
//		}
//	}


	// 导航信息生成
 	if (typeof leftMenuSelectId != 'undefined' && leftMenuSelectId) {
 		GlobalMenu.foldMenu(leftMenuSelectId);
 	}
};

/**
 * 折叠菜单
 */
GlobalMenu.foldMenu = function(leftMenuSelectId) {

	// if(leftMenuSelectId){
	//		
	// //当前二级菜单下所有三级菜单的div hide的css类名切换
	// $('#leftMenu'+leftMenuSelectId).toggleClass('hide');
	// //判断三级菜单是否是隐藏
	// var hasHideSubMenu = $('#leftMenu'+leftMenuSelectId).hasClass('hide');
	// //修改二级菜单a标签的class
	// if(hasHideSubMenu){
	// $('#leftMenu'+leftMenuSelectId).parent().show();
	// }else{
	// $('#leftMenu'+leftMenuSelectId).parent().hide();
	// }
	// }

};

/**
 * 全局事件
 */
var GlobalEvent = {};
GlobalEvent.init = function() {
	// textarea长度限制
	// $('textarea').textarealimit();
	// //弹出层事件绑定
	// GlobalEvent.bindPopupEvent();
	// //小提示框 tip
	// GlobalEvent.bindSysTipEvent();
	// //输入框文本初始化事件
	// GlobalEvent.inputInit();
	// //下拉框设置
	// SelectEl.init();
};
/**
 * 绑定浮动层的事件 ajax loaded页面需要手动调用
 */
GlobalEvent.bindPopupEvent = function() {
	$('.popupParentEl').each(function() {
		$(this).unbind('mouseover.popup').bind('mouseover.popup', function(e) {
			$(this).find('.popupEl').show();
			e.stopPropagation();
		}).unbind('mouseout.popup').bind('mouseout.popup', function(e) {
			$(this).find('.popupEl').hide();
			e.stopPropagation();
		});
	});
};
/**
 * 鼠标移动至按钮提示tip 必备条件：1.mouseover的元素必须添加class为"sysElTip"
 * 2.mouseover的元素必须要有sysTipCtx属性（提示的内容）
 */
GlobalEvent.bindSysTipEvent = function() {
	var sysBtnTipDiv = $('.sysBtnTip');
	$('.sysElTip').each(function() {
		var self = $(this);
		self.unbind('mouseover.sysTip').bind('mouseover.sysTip', function(e) {
			var title = self.attr('sysTipCtx') || self.attr('title') || '';
			sysBtnTipDiv.find('#tipVal').empty().html(title);
			var tipWidth = sysBtnTipDiv.outerWidth();
			var tipHeight = sysBtnTipDiv.outerHeight();
			var arrowHeight = sysBtnTipDiv.find('.arrow').outerHeight();

			var left = self.offset().left;
			var top = self.offset().top;
			var width = self.outerWidth();
			var height = self.outerHeight();

			var tipLeft = left + (width / 2 - tipWidth / 2);
			var tipTop = top - tipHeight - arrowHeight;
			sysBtnTipDiv.css({
				left : tipLeft + 'px',
				top : tipTop + 'px'
			}).show();
			e.stopPropagation();
			return false;
		}).unbind('mouseout.sysTip').bind('mouseout.sysTip', function(e) {
			sysBtnTipDiv.hide();
			e.stopPropagation();
			return false;
		});
	});
};
/**
 * 文本框默认文字及鼠标聚焦失去焦点事件绑定 ajax load需要手动绑定
 */
GlobalEvent.inputInit = function() {
	$('input[placeholder],textarea[placeholder]').each(function() {

		var type = $(this).attr('type');
		if (type && type.toLowerCase() == 'password') {
			return;
		}

		if (!$(this).val()) {
			$(this).val($(this).attr("placeholder"));
			$(this).addClass("placeholder");
		}
		$(this).unbind('focus.placeholder');
		$(this).unbind('blur.placeholder');
		$(this).bind('focus.placeholder', function() {
			if ($(this).val() == $(this).attr("placeholder")) {
				$(this).val('');
				$(this).removeClass("placeholder");
			}
		});
		$(this).bind('blur.placeholder', function() {
			if (!$(this).val()) {
				$(this).val($(this).attr("placeholder"));
				$(this).addClass("placeholder");
			}
			if ($(this).val() == $(this).attr("placeholder")) {
				$(this).addClass("placeholder");
			}
		});
	});
};
/**
 * 自定义下拉框 select标签必须要有样式sys_sel 才能生成自定义下拉框
 */
var SelectEl = {};
SelectEl.init = function(container) {
	var selects = container && $(container).length > 0 ? $(container).find(
			'.sys_sel') : $('.sys_sel');
	for (var i = 0; i < selects.length; i++) {
		var selObj = $(selects[i]);
		var selectBox = selObj.prev();
		// 新建
		if (!selectBox.hasClass('select_box') || selectBox.length == 0) {
			SelectEl.createSelect(selObj);
		}
	}
	if (selects.length > 0) {
		$(document).unbind('click.selectExt').bind('click.selectExt',
				function() {
					$('.select_option').hide();
					$('.select_showbox').removeClass('up-arrow');
				});
	}
};
/**
 * 指定选中的index
 */
SelectEl.selectedIndex = function(selObj, index) {
	var selct_box = selObj.prev();
	var select_showbox = selct_box.find('.select_showbox');
	var selectedOption = selct_box.find('.select_option li:eq(' + index + ')');
	selct_box.find('.select_option li').removeClass('selected');
	selectedOption.addClass('selected');
	var text = selectedOption.text();
	var value = selectedOption.attr('val') || '';
	select_showbox.text(text);
	selObj.find('option').removeAttr('selected');
	selObj.find('option[value="' + value + '"]').attr('selected', 'selected');
};
SelectEl.reCreateSelect = function(selectObj) {
	var selectBox = selectObj.prev();
	// 新建
	if (selectBox.hasClass('select_box')) {
		selectBox.remove();
	}
	SelectEl.createSelect(selectObj);
	$(document).unbind('click.selectExt').bind('click.selectExt', function() {
		$('.select_option').hide();
		$('.select_showbox').removeClass('up-arrow');
	});
};
SelectEl.createSelect = function(select_container) {
	// 创建select容器，class为select_box，插入到select标签前
	var select_box = $('<div class="select_box"></div>');// div相当于select标签
	select_box.insertBefore(select_container);

	// 显示框class为select_showbox,插入到创建的select_box中
	var select_showbox = $('<div class="select_showbox"></div>');// 显示框
	select_showbox.css('cursor', 'pointer').appendTo(select_box);

	// 创建option容器，class为select_option，插入到创建的select_box中
	var ul_option = $('<ul class="select_option"></ul>');// 创建option列表
	ul_option.appendTo(select_box);
	SelectEl.createOptions(select_container, ul_option);// 创建option
	// 点击显示框
	select_box.click(function(event) {
		if (ul_option.is(':hidden')) {
			select_showbox.addClass('up-arrow');
			ul_option.show();
		} else {
			select_showbox.removeClass('up-arrow');
			ul_option.hide();
		}
		event.stopPropagation();
	});
	var li_option = ul_option.find('li');
	li_option.on('click', function(event) {
		$(this).addClass('selected').siblings().removeClass('selected');
		var text = $(this).text();
		var value = $(this).attr('val') || '';
		select_showbox.text(text);
		select_container.find('option').removeAttr('selected');
		select_container.find('option[value="' + value + '"]').attr('selected',
				'selected');
		select_showbox.removeClass('up-arrow');
		ul_option.hide();
		select_container.change();
		event.stopPropagation();
	});
	li_option.hover(function() {
		$(this).addClass('hover').siblings().removeClass('hover');
	}, function() {
		li_option.removeClass('hover');
	});
	select_container.hide();
};

SelectEl.createOptions = function(selectObj, ul_list) {
	// 获取被选中的元素并将其值赋值到显示框中
	var options = selectObj.find('option');
	if (options.length <= 0) {
		return;
	}
	var selected_option = options.filter(':selected');
	var selected_index = selected_option.index();
	// 没有选中的selected属性的时候默认是第一个
	if (selected_option.length <= 0) {
		selected_index = 0;
		selected_option = options.get(0);
	}
	var showbox = ul_list.prev();
	showbox.text(selected_option.text());
	// 为每个option建立个li并赋值
	for (var n = 0; n < options.length; n++) {
		var tag_option = $('<li></li>'), // li相当于option
		op = options.eq(n);
		tag_option.attr('val', op.attr('value') || '').text(op.text()).css(
				'cursor', 'pointer').appendTo(ul_list);
		// 为被选中的元素添加class为selected
		if (n == selected_index) {
			tag_option.attr('class', 'selected');
		}
	}
};

var View = {};
View.viewMsg = function(id) {
	Dialog.ajaxOpenDialog(BASE_PATH + '/production/common/view/' + id, null,
			null, null, {
				cancel : true,
				cencelVal : '取消'
			});

};

View.viewHelpGuide = function() {
	var url = BASE_PATH + "/workbench/help";
	Dialog.ajaxOpenDialog(url, null, null, function(dialog, data) {
		var openHelpGuide = CommonUtil.getCookie("openHelpGuide");
		if (openHelpGuide != null && openHelpGuide == "false") {
			$("#showAgain").attr("checked", 'true');
		}
		$("#closeHelp").click(function() {
			if ($("#showAgain").is(':checked')) {
				CommonUtil.setCookie("openHelpGuide", "false");
			} else {
				CommonUtil.setCookie("openHelpGuide", "true");
			}
			dialog.close();
		});
	}, null);
};

// ///////////////////////////////////////////////////////////////

/** ******************************js时间工具**************************************** */
DateUtil = {};

DateUtil.isLeapYear = function(date) {
	return (0 == date.getYear() % 4 && ((date.getYear() % 100 != 0) || (date
			.getYear() % 400 == 0)));
};

/**
 * 格式化日期
 */
DateUtil.fomatDate = function(date, fmt) {
	var yyyy = date.getFullYear();
	var MM = date.getMonth();
	var dd = date.getDate();
	var HH = date.getHours();
	var mm = date.getMinutes();
	var ss = date.getSeconds();
	var hh = HH > 12 ? HH - 12 : HH;
	var dateStr = fmt.replace('yyyy', yyyy).replace('MM',
			DateUtil.addZero(MM + 1)).replace('dd', DateUtil.addZero(dd))
			.replace('HH', DateUtil.addZero(HH)).replace('mm',
					DateUtil.addZero(mm)).replace('ss', DateUtil.addZero(ss))
			.replace('hh', DateUtil.addZero(hh));
	return dateStr;
};

DateUtil.addZero = function(num) {
	if (num < 10)
		return '0' + num;
	return num;
};

/**
 * 将日期字符串转成日期 fmt：yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd
 */
DateUtil.parseDate = function(str, fmt) {
	if (!str) {
		return null;
	}
	var date;
	var year = 0;
	var month = 0;
	var day = 0;
	var hour = 0;
	var minute = 0;
	var second = 0;
	var tempStrs = str.split(' ');
	if (tempStrs[0]) {
		var dateStrs = tempStrs[0].split("-");
		year = parseInt(dateStrs[0], 10);
		month = parseInt(dateStrs[1], 10) - 1;
		day = parseInt(dateStrs[2], 10);
	}
	if (tempStrs[1]) {
		var timeStrs = tempStrs[1].split(":");
		hour = parseInt(timeStrs[0], 10);
		minute = parseInt(timeStrs[1], 10);
		second = parseInt(timeStrs[2], 10);
	}

	if (fmt == 'yyyy-MM-dd') {
		date = new Date(year, month, day);
		return date;
	} else if (fmt == 'yyyy-MM-dd HH:mm:ss') {
		date = new Date(year, month, day, hour, minute, second);
		return date;
	}
	return null;
};
/**
 * 获取指定日期最后一天日期
 */
DateUtil.getLastDate = function(date) {
	date = arguments[0] || new Date();
	var newDate = new Date(date.getTime());
	newDate.setMonth(newDate.getMonth() + 1);
	newDate.setDate(1);
	var time = newDate.getTime() - 24 * 60 * 60 * 1000;
	newDate = new Date(time);
	return newDate;
};
/**
 * 获取指定日期第一天日期
 */
DateUtil.getFirstDate = function(date) {
	date = arguments[0] || new Date();
	var newDate = new Date(date.getTime());
	newDate.setDate(1);
	return newDate;
};
/**
 * 日期计算
 * 
 * @param strInterval
 *            string 可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param num
 *            int
 * @param date
 *            Date 日期对象
 * @return Date 返回日期对象
 */
DateUtil.dateAdd = function(strInterval, num, date) {
	date = arguments[2] || new Date();
	switch (strInterval) {
	case 's':
		return new Date(date.getTime() + (1000 * num));
	case 'n':
		return new Date(date.getTime() + (60000 * num));
	case 'h':
		return new Date(date.getTime() + (3600000 * num));
	case 'd':
		return new Date(date.getTime() + (86400000 * num));
	case 'w':
		return new Date(date.getTime() + ((86400000 * 7) * num));
	case 'm':
		return new Date(date.getFullYear(), (date.getMonth()) + num, date
				.getDate(), date.getHours(), date.getMinutes(), date
				.getSeconds());
	case 'y':
		return new Date((date.getFullYear() + num), date.getMonth(), date
				.getDate(), date.getHours(), date.getMinutes(), date
				.getSeconds());
	}
};

DateUtil.initDateTime = function(fmt, beginTimeId, endTimeId, monthDiff,
		showClear, hasDefaultVal) {
	var beginTimeInput = $('#' + beginTimeId);
	var endTimeInput = $('#' + endTimeId);
	monthDiff = monthDiff || 6;

	var checkDateTime = function(isBeginChanged) {
		var beginTimeVal = beginTimeInput.val();
		var endTimeVal = endTimeInput.val();
		var beginDate = DateUtil.parseDate(beginTimeVal, fmt);
		var endDate = DateUtil.parseDate(endTimeVal, fmt);
		if (isBeginChanged) {
			var tempDate = beginDate;
			tempDate.setMonth(beginDate.getMonth() + monthDiff);
			if (tempDate < endDate) {
				endTimeInput.val(DateUtil.fomatDate(tempDate, fmt));
			}
		} else {
			var tempDate = endDate;
			tempDate.setMonth(endDate.getMonth() - monthDiff);
			if (tempDate > beginDate) {
				beginTimeInput.val(DateUtil.fomatDate(tempDate, fmt));
			}
		}
	};

	var beginTimeChanged = function() {
		var beginTimeVal = beginTimeInput.val();
		if ('' != beginTimeVal) {
			var beginTime = DateUtil.parseDate(beginTimeVal, fmt);
			var endTime = DateUtil.parseDate(endTimeInput.val(), fmt);

			if (endTime == null || beginTime > endTime) {
				endTime = beginTime;
				endTime.setMonth(beginTime.getMonth() + 1);
				endTimeInput.val(DateUtil.fomatDate(endTime, fmt));
			}
			checkDateTime(true);
		}
	};

	var endTimeChanged = function() {
		var endTimeVal = endTimeInput.val();
		if ('' != endTimeVal) {
			var endTime = DateUtil.parseDate(endTimeVal, fmt);
			var beginTime = DateUtil.parseDate(beginTimeInput.val(), fmt);
			if (beginTime == null || beginTime > endTime) {
				beginTime = endTime;
				beginTime.setMonth(endTime.getMonth() - 1);
				beginTimeInput.val(DateUtil.fomatDate(beginTime, fmt));
			}
			checkDateTime(false);
		}
	};

	showClear = typeof showClear == undefined ? true : showClear;
	if (hasDefaultVal) {
		var nowDate = new Date();
		var defalutStartTime = DateUtil.fomatDate(DateUtil
				.getFirstDate(nowDate), fmt);
		var defalutEndTime = DateUtil.fomatDate(DateUtil.getLastDate(nowDate),
				fmt);
		beginTimeInput.val(defalutStartTime);
		endTimeInput.val(defalutEndTime);
	}
	beginTimeInput.click(function() {
		WdatePicker({
			dateFmt : fmt,
			maxDate : '#F{$dp.$D(\'' + endTimeId + '\')}',
			isShowClear : showClear,
			onpicked : beginTimeChanged
		});
	});
	endTimeInput.click(function() {
		WdatePicker({
			dateFmt : fmt,
			minDate : '#F{$dp.$D(\'' + beginTimeId + '\')}',
			isShowClear : showClear,
			onpicked : endTimeChanged
		});
	});
};


/**
 * 全选
 */
function checkAll(self, ckName) {
	if ($(self).attr('checked') == 'checked') {
		$('input[name="' + ckName + '"]').attr('checked', 'checked');
	} else {
		$('input[name="' + ckName + '"]').removeAttr('checked');
	}
	$('input[name="' + ckName + '"]').each(function() {
		$(this).unbind('click.ck').bind('click.ck', function() {
			var count = $('input[name="' + ckName + '"]').length;
			var ckCount = $('input[name="' + ckName + '"]:checked').length;
			if (ckCount < count) {
				$(self).removeAttr('checked');
			} else {
				$(self).attr('checked', 'checked');
			}
		});
	});

}
/**
 * 批量删除操作
 * 
 * @param ckName
 *            checkbox的name
 * @param delUrl
 *            删除的url
 * @param idsKey
 *            提交到后台存放id字符串的key
 * @returns
 */
function deletes(ckName, delUrl, idsKey, succsssCallBack, faildCallBack) {
	var idKey = idsKey ? idsKey : 'ids';
	var ids = [];
	$('input[name="' + ckName + '"]:checked').each(function() {
		if ($(this).attr('checked') == 'checked') {
			ids.push($(this).val());
		}
	});
	if (ids.length <= 0) {
		Dialog.alertInfo('请选择删除的对象');
		return;
	}
	var idStr = ids.join(',');
	submitSave(delUrl, idKey + '=' + idStr, succsssCallBack, faildCallBack);
}
/**
 * 批量删除操作
 * 
 * @param ckName
 *            checkbox的name
 * @param delUrl
 *            删除的url
 * @param idsKey
 *            提交到后台存放id字符串的key
 * @returns
 */
function deleteByArr(ckName, delUrl, idsKey, succsssCallBack, faildCallBack) {
	var idKey = idsKey ? idsKey : 'ids';
	var ids = [];
	$('input[name="' + ckName + '"]:checked').each(function() {
		if ($(this).attr('checked') == 'checked') {
			ids.push($(this).val());
		}
	});
	if (ids.length <= 0) {
		Dialog.alertInfo('请选择删除的对象');
		return;
	}
	var params = CommonUtil.converArrToParams(ids, idKey);
	submitSave(delUrl, params, succsssCallBack, faildCallBack);
}

/**
 * 关闭系统公告
 * 
 * @param
 * 
 */
function closeTopNotice(id) {
	$("#topnotice").remove();
	CommonUtil.setCookie('topnotice', id);
}

/**
 * 清空查询条件 注:根据id 清空form所有input内容 排除隐藏文本框
 * 
 * @return
 */
function formClear(formId) {
	var form = $('#' + formId);
	if (form.length > 0) {
		var radioSet = {};
		form.find('input[type="text"],textarea').each(function() {
			var placeholder = $(this).attr('placeholder');
			if (placeholder) {
				$(this).val(placeholder);
			} else {
				$(this).val('');
			}
		});
		form.find('select').each(function() {
			$(this).find('option').removeAttr('selected');
			$(this).find('option:eq(0)').attr('selected', 'selected');
		});
		form.find('input[type="radio"]').each(function() {
			var name = $(this).attr('name');
			if (name) {
				radioSet[name] = '';
			} else {
				$(this).removeAttr('checked');
			}
		});
		for ( var p in radioSet) {
			$('input[name="' + p + '"]').removeAttr('checked');
			$('input[name="' + p + '"]:eq(0)').attr('checked', 'checked');
		}
		form.find('input[type="checkbox"]').removeAttr('checked');
	}
};

/**
 * 下载远程服务器上的文件
 * 
 * @param name
 *            下载文件名 测试
 * @param path
 *            文件路径
 * 
 */
function downLoadFile(name, path) {
	var params = 'fileName=' + encodeURIComponent(name);
	params += '&filePath=' + encodeURIComponent(path);
	if (path.startWith('http://')) {
		window.open(BASE_PATH + '/common/downLoad!remoteFile.do?' + params);
	} else {
		window.open(BASE_PATH + '/common/downLoad!file.do?' + params);
	}
}

/**
 * 查看上传进度信息
 * 
 * @param progressId
 *            进度条元素ID
 * @param trackerId
 *            上传跟踪ID
 * @param fileIndex
 *            文件索引，第几个上传文件
 */
function getUploadProgress(trackerId, fileIndex, doProgress) {
	if (fileIndex == null || undefined == fileIndex)
		fileIndex = 0;
	var params = {
		trackerId : trackerId
	};
	var url = BASE_PATH + "/common/upload!getProgress.do";
	submitSave(url, params, function(data) {
		var returnValue = data.returnValue;
		if (returnValue && returnValue.length > 0) {
			var uploadInfo = returnValue[fileIndex];
			doProgress(uploadInfo);
		}
	});
}
function polyvplayer(id, obj) {
	var player = polyvObject('#' + id).videoPlayer(obj);
}

/**
 * 判断对象是否为空
 * 
 * @param name
 *            obj
 * @param
 * 
 */
function isEmptyObject(obj) {
	for ( var n in obj) {
		return false;
	}
	;
	return true;
};
/**
 * 阻止事件冒泡
 * 
 * @param e
 *            js事件对象
 */
function stopBubble(e) {
	if (e && e.stopPropagation) {
		e.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
}
var CommonUtil = {};

/**
 * 写cookie值
 * 
 * @param name
 * @param value
 * @param time
 *            过期时间，单位ms
 */
CommonUtil.setCookie = function(name, value, time) {
	var period = time;
	if (!time) {
		period = 30 * 24 * 60 * 60 * 1000;
	}
	var exp = new Date();
	exp.setTime(exp.getTime() + period);
	document.cookie = name + "=" + escape(value) + ";Path=/;expires="
			+ exp.toGMTString();
};

/**
 * 取得cookie值
 * 
 * @param name
 * @returns
 */
CommonUtil.getCookie = function(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg)) {
		return unescape(arr[2]);
	}
	return null;
};
/**
 * 通用工具方法 获取一个object数组中某个字段的数组或者请求参数的字符串
 */
CommonUtil.getFieldArrFromObjArr = function(objArr, fieldName,
		isConvertToparam, paramName) {
	var arr = [];
	var params = '';
	paramName = paramName || 'id';
	if (objArr && objArr.push) {
		for (var i = 0; i < objArr.length; i++) {
			var f = objArr[i][fieldName];
			if (f != undefined) {
				arr.push(f);
				params += '&' + paramName + '=' + f;
			}
		}
	}
	if (isConvertToparam) {
		return params;
	}
	return arr;
};
/**
 * 把数组转换成提交的参数
 */
CommonUtil.converArrToParams = function(arr, paramName) {
	var params = '';
	if (!arr || !arr.push) {
		return params;
	}
	paramName = paramName || 'id';
	for (var i = 0; i < arr.length; i++) {
		var f = arr[i];
		if (f != undefined) {
			params += '&' + paramName + '=' + f;
		}
	}
	return params;
};
// 字符串添加自定义方法
String.prototype.endWith = function(str) {
	if (str == null || str == '' || this.length == 0
			|| str.length > this.length) {

		return false;
	}
	if (this.substring(this.length - str.length) == str) {

		return true;
	}
	return false;
};
String.prototype.startWith = function(str) {
	if (str == null || str == '' || this.length == 0
			|| str.length > this.length) {

		return false;
	}
	if (this.substr(0, str.length) == str) {
		return true;

	}
	return false;
};
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, '');
};
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, '');
};
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)
			&& typeof reallyDo == 'string') {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};
String.prototype.getBytesLength = function() {
	var totalLength = 0;
	var charCode;
	for (var i = 0; i < this.length; i++) {
		charCode = this.charCodeAt(i);
		if (charCode < 0x007f) {
			totalLength++;
		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
			totalLength += 2;
		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
			totalLength += 3;
		} else {
			totalLength += 4;
		}
	}
	return totalLength;
};
// ------------------------------ie 的textArea
// 控制输入长度jquery插件------------------------------
(function($) {
	$.fn.textarealimit = function() {
		var maxLength = $(this).attr('maxlength');
		if (!maxLength) {
			return;
		}
		if ($.browser.msie) {
			$(this).unbind('keydown', doKeydown).unbind('keypress', doKeypress)
					.unbind('beforepaste', doBeforePaste).unbind('paste',
							doPaste);
			$(this).bind('keydown', doKeydown).bind('keypress', doKeypress)
					.bind('beforepaste', doBeforePaste).bind('paste', doPaste);
		}
		function doKeypress() {
			var oTR = document.selection.createRange();
			if (oTR.text.length >= 1) {
				event.returnValue = true;
			} else if (this.value.length > maxLength - 1) {
				event.returnValue = false;
			}
		}
		function doKeydown() {
			var _obj = this;
			setTimeout(function() {
				if (_obj.value.length > maxLength - 1) {
					var oTR = window.document.selection.createRange();
					oTR.moveStart('character', -1
							* (_obj.value.length - maxLength));
					oTR.text = '';
				}
			}, 1);
		}
		function doBeforePaste() {
			event.returnValue = false;
		}
		function doPaste() {
			event.returnValue = false;
			var oTR = document.selection.createRange();
			var iInsertLength = maxLength - this.value.length + oTR.text.length;
			var sData = window.clipboardData.getData('Text').substr(0,
					iInsertLength);
			oTR.text = sData;
		}
	};
})(jQuery);

// ------------------------------获取验证码倒计时------------------------------
// 忘记密码--发送验证码--倒数*秒
timedown = function(time) {

	if ((time == undefined || time == "") && time != 0) {
		time = 120;
	}
	$("#second").html(time);
	time = time - 1;
	if (time >= 0) {
		setTimeout(function() {
			timedown(time);
		}, 1000);
	} else {
		$("#captcha").text("重新发送").show();
		$("#resend").hide();
	}
};

// 忘记密码--发送验证码--倒数*秒
timedownCom = function(time, formFlag) {

	if ((time == undefined || time == "") && time != 0) {
		time = 120;
	}
	$("#" + formFlag).find("#second").html(time);
	time = time - 1;
	if (time >= 0) {
		setTimeout(function() {
			timedownCom(time, formFlag);
		}, 1000);
	} else {
		$("#" + formFlag).find("#captcha").text("重新发送").show();
		$("#" + formFlag).find("#resend").hide();
	}
};

// 换一张
function changeRandCode(code) {
	if (code != '' && code != null) {
		$("#randCode_" + code).attr(
				"src",
				BASE_PATH + "/meta/js/common/code.jsp?" + rndForCode()
						+ "&codeKey=REPLY_CODE_" + code);
	} else {
		$("#randCode").attr("src",
				BASE_PATH + "/meta/js/common/code.jsp?" + rndForCode());
	}

};

// 随机码
function rndForCode() {

	var random = Math.floor(Math.random() * 10001);
	var id = (new Date().getTime() * random).toString();
	id = id.split('').reverse().join('');
	return 'random' + random + 'id=' + id;
};
/** *******************常量定义************************* */
/**
 * 获取form表单,转换成json对象
 */
(function($) {
	$.fn.fjson = function() {
		var serializeObj = {};
		$(this.serializeArray()).each(function() {
			serializeObj[this.name] = this.value;
		});
		return serializeObj;
	};
})(jQuery);




/** *************************调用 打印******************************** */
/**
 * 进入打印
 */
function toPrint(myUrl) {
	myUrl = myUrl.replace("&", "|");
	var url = BASE_PATH + '/print/init';
	// window.location.href = url + "?myUrl=" + myUrl;
	window.open(url + "?myUrl=" + myUrl, "_blank");
}


/** *************************处理分页******************************** */

/**
 * 处理分页方法
 * @param formId
 * @param callBack
 */
function pageInfo(formId,callBack){
	if($('#'+formId).length==0){
		return;
	}
	$('#'+formId).find('a[name="sysPageBtn"]').each(function(){
		$(this).click(function(){
			var pageNum = $(this).attr('value');
			//判断是否是数字,能否分页
			if(isNumber(pageNum)){
				var curPageInput = $('#'+formId).find('#curPage');
				//当前页和跳转页不相等时进行翻页操作
				if(curPageInput.val()!=pageNum){
					curPageInput.val(parseInt(pageNum));
					callBack?callBack():$.noop();
				}
			}
		});
	});
}

/**
 * 处理分页方法
 * 
 * @param formId
 * @param callBack
 */
function subPageInfo(formId, callBack) {
	if ($('#' + formId).length == 0) {
		return;
	}
	$('#' + formId).find('a[name="sysSubPageBtn"]').each(function() {
		$(this).click(function() {
			var pageNum = $(this).attr('value');
			// 判断是否是数字,能否分页
			if (isPosNumWithOutZero(pageNum)) {
				var curPageInput = $('#' + formId).find('#subCurPage');
				// 当前页和跳转页不相等时进行翻页操作
				if (curPageInput.val() != pageNum) {
					curPageInput.val(parseInt(pageNum));
					callBack ? callBack() : $.noop();
				}
			}
		});
	});
}



/**
 * 城市、行政区、板块 三级联动
 */
function linkage() {
	$("#cityNo").change(
			function() {

				var url = BASE_PATH + "/linkages/city/" + $("#cityNo").val();
				var params = {};

				ajaxGet(url, params, function(data) {
					var result = "<option value=''>请选择行政区</option>";
					$.each(data.returnValue, function(n, value) {
						result += "<option value='" + value.districtNo + "'>"
								+ value.districtName + "</option>";
					});
					$("#districtNo").html('');
					$("#districtNo").append(result);
				}, function() {
				});

			});
	$("#districtNo").change(
			function() {

				var url = BASE_PATH + "/linkages/area/"
						+ $("#districtNo").val();
				var params = {};

				ajaxGet(url, params, function(data) {
					var result = "<option value=''>请选择板块</option>";
					$.each(data.returnValue, function(n, value) {
						result += "<option value='" + value.areaNo + "'>"
								+ value.areaName + "</option>";
					});
					$("#areaNo").html('');
					$("#areaNo").append(result);
				}, function() {
				});

			});
}

/**
 * 城市、行政区、板块 三级联动(乙方住所地用)
 */
function linkagePartyB() {
	$("#partyBcityNo").change(
			function() {

				var url = BASE_PATH + "/linkages/city/" + $("#partyBcityNo").val();
				var params = {};

				ajaxGet(url, params, function(data) {
					var result = "<option value=''>请选择区域</option>";
					$.each(data.returnValue, function(n, value) {
						result += "<option value='" + value.districtNo + "'>"
								+ value.districtName + "</option>";
					});
					$("#partyBdistrictNo").html('');
					$("#partyBdistrictNo").append(result);
				}, function() {
				});

			});
	$("#partyBdistrictNo").change(
			function() {

				var url = BASE_PATH + "/linkages/area/"
						+ $("#partyBdistrictNo").val();
				var params = {};

				ajaxGet(url, params, function(data) {
					var result = "<option value=''>请选择板块</option>";
					$.each(data.returnValue, function(n, value) {
						result += "<option value='" + value.areaNo + "'>"
								+ value.areaName + "</option>";
					});
					$("#partyBareaNo").html('');
					$("#partyBareaNo").append(result);
				}, function() {
				});

			});
}