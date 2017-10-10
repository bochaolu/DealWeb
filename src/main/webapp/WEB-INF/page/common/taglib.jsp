<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://eju.com/sdk" prefix="sdk" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="t" uri="/eju-tags"%>
<%
	/* String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; */
	pageContext.setAttribute("httpProtocol", request.getScheme());
%>
<c:set var="locResPath" value="${ctx}"/>
<c:set var="vs" value="${sysConfig.jsCssVersion}"/>

