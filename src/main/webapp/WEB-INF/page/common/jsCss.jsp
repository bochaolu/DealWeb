<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
<!--
	//js中请求的base路径
	var BASE_PATH = '${ctx}';
	//js中本地静态文件base路径(包括:js,特殊的本地图片,所有插件的文件)
	var LOC_RES_BASE_PATH = '${locResPath}';
//-->
</script>


<!-- 网站图标 -->
<%-- <link rel="shortcut icon" href="${ctx}/meta/images/favicon.ico" /> --%>

<!-- 引入css -->
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap.min.css">
<!-- 引入自定义css -->
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/layout.css"> --%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/tip.css?_v=${vs}" />

<!-- 引入font -->

<!-- 引入js -->
<script type="text/javascript" src="${ctx}/meta/js/common/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/jquery.scrollUp.min.js"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/bootstrap.min.js"></script>
<!-- 引入自定义 js -->
<script type="text/javascript" src="${ctx}/meta/js/common/comm.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/validation.js?_v=${vs}"></script>


