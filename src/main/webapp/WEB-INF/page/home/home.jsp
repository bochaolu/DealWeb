<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="" name="navSelectId" />
	</jsp:include>

	<div>
		<p>Hello, This is my demo, my name is lixiaoDong</p>

	</div>

	<jsp:include page="/WEB-INF/page/common/footer.jsp" flush="true"></jsp:include>

</body>

</html>
