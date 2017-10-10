<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<!-- support level 3 -->
<!-- Left side column. contains the logo and sidebar -->
<%-- <aside class="main-sidebar">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
	  
	  <!-- sidebar menu: : style can be found in sidebar.less -->
		
		<!-- 岗位list循环 -->
		<c:forEach items="${userInfo.postList}" var="postList">
		
			<!-- 用户选中岗位Id匹配 -->
			<c:if test="${userInfo.selectpostId eq postList.postId}">
			
				<!-- 权限信息-菜单 -->
				<c:forEach items="${postList.authList}" var="list">
				
					<!-- 循环出一级 -->
					<c:if test="${list.parentId eq 0}">
					  <ul class="sidebar-menu">
							<li class="active treeview">
							  <a href="#">
								<i class="fa fa-dashboard"></i> <span>${list.authName}</span>
								<span class="pull-right-container">
								  <i class="fa fa-angle-left pull-right"></i>
								</span>
							  </a>
							  <ul class="treeview-menu">							  
								<!-- 循环出该一级菜单下的  二级菜单-->
								<c:forEach items="${postList.authList}" var="listSecond">								
									<c:if test="${listSecond.parentId eq list.authId}">									
									<li>					
										 <c:if test="${not empty listSecond.url }">
								   		 	  <a href="${ctx}${listSecond.url}"><i class="fa fa-circle-o"></i>${listSecond.authName}</a>									
										</c:if>	
										<c:if test="${empty listSecond.url }">
								   		 	  <a><i class="fa fa-circle-o"></i>${listSecond.authName}</a>									
										</c:if>	
									  	<ul class="treeview-menu">										
											<!-- 循环获取该二级菜单的 三级菜单 -->
											<c:forEach items="${postList.authList}" var="listThird">											
												<c:if test="${listThird.parentId eq listSecond.authId}">												
												    <li><a href="${ctx}${listThird.url}"><i class="fa fa-circle-o"></i>${listThird.authName}</a></li>
												
												</c:if>
											
											</c:forEach>
										</ul>								
									 </li>									
									</c:if>								
								</c:forEach>
								
							  </ul>
							</li>
						</ul>
					  </c:if>
					
				</c:forEach>
				<!-- 权限信息-菜单 -->
			</c:if>
			<!-- 用户选中岗位Id匹配 -->
		</c:forEach>
		<!-- 岗位list循环 -->
		
	</section>
	<!-- /.sidebar -->
	
</aside> --%>

    
<script type="text/javascript">
<!--
var leftMenuSelectId = '${param.leftMenuSelectId}';
//-->
</script>