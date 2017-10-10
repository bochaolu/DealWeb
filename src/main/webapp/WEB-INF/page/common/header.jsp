<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<!-- 引入js -->


<header class="main-header">
	<!-- Logo -->
	<a href="${ctx}/bench" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>OP</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>OP</b>营运管理</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<!-- <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
		<span class="sr-only">Toggle navigation</span>
	  </a> -->

		<ul class="nav navbar-nav">

			<!-- 岗位list循环 -->
			<c:forEach items="${userInfo.postList}" var="postList">

				<!-- 用户选中岗位Id匹配 -->
				<c:if test="${userInfo.selectpostId eq postList.postId}">

					<!-- 权限信息-菜单 -->
					<c:forEach items="${postList.authList}" var="list">

						<!-- 循环出一级 -->
						<c:if test="${list.parentId eq 0}">
							<li><a href="#"><b>${list.authName}</b></a>
								<ul class="nav-subset1">

									<!-- 循环出该一级菜单下的  二级菜单-->
									<c:forEach items="${postList.authList}" var="listSecond">
										<c:if test="${listSecond.parentId eq list.authId}">

											<li><c:if test="${not empty listSecond.url }">
													<a href="${ctx}${listSecond.url}">${listSecond.authName}</a>
												</c:if> <c:if test="${empty listSecond.url }">
													<a>${listSecond.authName}</a>
												</c:if>

												<ul class="nav-subset2">

													<!-- 循环获取该二级菜单的 三级菜单 -->
													<c:forEach items="${postList.authList}" var="listThird">
														<c:if test="${listThird.parentId eq listSecond.authId}">

															<li><a href="${ctx}${listThird.url}">${listThird.authName}</a>
																<!-- <ul class="nav-subset3">
													            		<li><a href="#">业务结算1-3</a>
													            			<ul class="nav-subset4">
															            		<li><a href="#">业务结算1-4</a></li>
															            		<li><a href="#">业务结算2-4</a></li>
															            		<li><a href="#">业务结算3-4</a></li>
															            		<li><a href="#">业务结算4-4</a></li>
															            	</ul>	
													            		</li>
													            		<li><a href="#">业务结算2-3</a></li>
													            		<li><a href="#">业务结算3-3</a></li>
													            	</ul>	 --></li>

														</c:if>

													</c:forEach>

												</ul></li>

										</c:if>
									</c:forEach>

								</ul></li>

						</c:if>

					</c:forEach>
					<!-- 权限信息-菜单 -->
				</c:if>
				<!-- 用户选中岗位Id匹配 -->
			</c:forEach>
			<!-- 岗位list循环 -->

		</ul>


		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"> <span
						class="hidden-xs">${userInfo.userName}</span>
				</a></li>
				<!-- Control Sidebar Toggle Button -->
				<li><a href="${ctx}/logout">退出</a></li>
			</ul>
		</div>
	</nav>
	
</header>


<script>
<!--
	var navSelectId = '${param.navSelectId}';
//-->
</script>
