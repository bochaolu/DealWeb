<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
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

<form id = "stuEditForm" >

<input type="hidden" name="_method" value="put"/>


 <input type = "hidden"  id = "id"   name = "id" value = "${studentInfo.id}"/>

<div id="wrap">

  <div class="content mb20">
     <span class="curPos">您当前的位置:
        修改学生
       </span>
    <div class="barmain">
       
      <div class="Rsiderbar">
        <h3 class="editex">
	        修改学生
        </h3>
        <div class="basems">
          <h2 class="addcrte">基本信息</h2>
          <div class="base_table">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" >
              <tbody>
                
                <tr>
                  <th scope="row"><span>学生编号：</span></th>
                  <td>
                    <input type="text" class="s160_text" name="stuNo" id = "stuNO" value="${studentInfo.stuNo}" >
                    <span class="namejg  marleft10"><b></b></span>
                   </td>
                </tr>
                
                <tr>
                  <th scope="row"><span>学生姓名：</span></th>
                  <td><input type="text" class="s160_text" name = "stuName"  value="${studentInfo.stuName}">
                   </td>
                </tr>
                <tr>
                  <th scope="row"><span>学生年龄：</span></th>
                  <td>
                     <input type="text" class="s160_text" name = "stuAge"  value="${studentInfo.stuAge}" >
                   </td>
                </tr>
                
              </tbody>
            </table>
          </div>
        </div>
        
      
        <div class="orange">
         <div class="savesend">
	         <a href="javascript:Student.update();" class="save">保存</a>
	         <a href="${ctx}/students" class="cancl">返回</a>
	        <div class="clr"></div>
	      </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
</div></div>
</form>

</body>

</html>
