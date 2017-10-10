<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>

<head>

<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/user/login.js?_v=${vs}"></script>
<script type="text/javascript">
	var r = /login$/;
	if (!r.test(window.location.href)) {
		window.location.href = BASE_PATH + '/login';
	}
</script>
</head>

<body
	style="background:url('${ctx}/meta/images/login-bg.jpg') no-repeat;">
	<h3 class="fon-white mgl20">
		<strong>CRM客户关系管理</strong>
	</h3>
	<div class="panel panel-primary login">
		<div class="panel-body pd100" style="background:#F8F8F8">
			<h3 class="text-center">
				<strong>登录</strong>
			</h3>
			<form id="loginForm" method="POST">

				<div class="form-group">
					<label for="exampleInputPassword1" class="w100 fon-normal fs14">用户名</label>
					<input type="email" class="form-control" id="username"
						name="username">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1" class="w100 fon-normal fs14">密码</label>
					<input type="password" class="form-control" id="password"
						name="password">
				</div>
				<div class="form-group" id="randCodeLi" style="display:none">
						<input type="text" id="randCode1" name="randCode"
							placeholder="请输入验证码" class="w10 fon-normal fs14" ">
							<img id="randCode" alt="" src="${ctx}/meta/js/common/code.jsp"
							align="middle" width="70" height="30" onclick="changeRandCode()">
				</div>
			</form>
			<p></p>
			
			<div class="text-center mgt20">
				<button type="button" class="btn btn-primary"
					onclick="Login.login();">登录</button>
			</div>
		</div>
	</div>

</body>

</html>