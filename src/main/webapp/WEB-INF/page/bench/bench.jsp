<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
</head>

<body class="hold-transition fixed skin-red sidebar-mini">
<div class="wrapper">

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="" name="navSelectId" />
	</jsp:include>
	
	<!-- 左边栏 -->
	<jsp:include page="/WEB-INF/page/common/leftMenu.jsp">
		<jsp:param value="${leftMenuSelect}" name="leftMenuSelectId" />
	</jsp:include>

	<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content">
	  	<div class="row">
			<div class="col-xs-12">
				<div class="box box-danger">
		            <div class="box-header with-border">
		            	<h3 class="box-title">首页</h3>
		            </div>
		            
		           
					
	            </div>
			</div>
		</div>
	</section>
</div>
<!-- /.content-wrapper -->

	<!-- footer -->
	<jsp:include page="/WEB-INF/page/common/footer.jsp"></jsp:include>	

</div>


</body>

</html>
