<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th width="" scope="col">选择</th>
			<th scope="col">学生编号</th>
			<th width="" scope="col">学生姓名</th>
			<th width="" scope="col">学生年纪</th>
			<th class="text-center">操作</th>
		</tr>

		<c:forEach items="${contentlist}" var="list">

			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td align="center"><input type="checkbox" name="blackCheck" value="${list.id}" /></td>
				<td align="center">${list.stuNo}</td>
				<td align="center">${list.stuName}</td>
				<td align="center">${list.stuAge}</td>
				
				<td align="text-center">
					<div class="mailopre">
						<a href="javascript:Student.remove('${list.id}')">删除</a>
						<a href="${ctx}/students/u/${list.id}">修改</a> 
						<a href="${ctx}/students/${list.id}">查看</a> 
					</div>
				</td>
			</tr>

		</c:forEach>

	</table>

</div>

${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
