<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/student/student.js?_v=${vs}"></script>   
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

        <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
              <h4><strong>学生</strong></h4>
                 <!--  <ul class="list-inline">
                          <li>分类：</li>  
                          <li>公司</li>
                          <li>我的下属公司</li>
                  </ul> -->
                  
               <!-- 搜索条件区域 -->
                <form id="studentForm">
                
                    <!-- 默认排序字段、排序类型 -->
                    <input type="hidden" name="orderName" value="dateCreate" >
    				<input type="hidden" name="orderType" value="DESC" >
    				
    				<!-- 筛选条件隐藏域 -->
                
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-inline">
                              <div class="col-md-9">
                                <div class="form-group">
                                    <label class="">学生编号</label>
                                    <input type="text" class="form-control" id="stuNo" name="stuNo">
                                </div>
                                <div class="form-group">
                                    <label class="">学生姓名</label>
                                    <input type="text" class="form-control" id="stuName" name="stuName">
                                </div>
                                
                                
                                <button type="button" class="btn btn-primary" id="J_search"  onclick="javascript:Student.search();">搜索</button>
                                
                                
                              </div>
                              <div class="col-md-3 text-right">
                                <a type="button" class="btn btn-primary" href="${ctx}/students/c" >新增学生</a>
                              </div>
                                
                            </div>
                        </div>
                    </div>
                
                
              
              
               <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
              
              </form>
             
            </div>
        </div>
    </div>

</body>

</html>
