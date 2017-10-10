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


<div id="wrap">

  <div class="content mb20">
     <span class="curPos">您当前的位置:
        查看学生
       </span>
    <div class="barmain">
       
      <div class="Rsiderbar">
        <h3 class="editex">
	          查看学生
        </h3>
        <div class="basems">
          <h2 class="addcrte">基本信息</h2>
          <div class="base_table">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" >
              <tbody>
                
                <tr>
                  <th scope="row"><span>学生编号：</span></th>
                  <td>
                    ${studentInfo.stuNo}
                    <span class="namejg  marleft10"><b></b></span>
                   </td>
                </tr>
                
                <tr>
                  <th scope="row"><span>学生姓名：</span></th>
                  <td>${studentInfo.stuName}
                   </td>
                </tr>
                <tr>
                  <th scope="row"><span>学生年龄：</span></th>
                  <td>
                     ${studentInfo.stuAge}
                   </td>
                </tr>
                
                 <tr>
                  <th scope="row"><span>创建日期：</span></th>
                  <td>
                     ${studentInfo.dateCreate}
                   </td>
                </tr>
                
              </tbody>
            </table>
          </div>
        </div>
        
      <div class="orange">
         <div class="savesend">
	         <a href="${ctx}/students" class="cancl">返回</a>
	        <div class="clr"></div>
	      </div>
      </div>
       
      <div class="clr"></div>
    </div>
  </div>
</div></div>


</body>

</html>
