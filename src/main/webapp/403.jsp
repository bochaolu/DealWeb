<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>访问未被许可</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/favicon.ico" />
<link href="${pageContext.request.contextPath}/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/common/navigate.js"></script>
</head>
<body>
<div class="add_tab_box01">
  <table width="100%">
    <tr>
      <td width="30%" align="right" rowspan="4" valign="top" height="50" class="add_tab_img"><img src="${pageContext.request.contextPath}/resource/images/icon_chaoshi.png"></td>
      <td width="70%" valign="top" height="50" class="add_tab_til">对不起，您未被许可访问该页面！</td>
    </tr>
    <tr>
      <td width="20%" class="add_tab_til01">您可以：</td>
    </tr>
    <tr>
      <td class="add_tab_til02">1.检查您的网址是否正确</td>
    </tr>
    <tr>
      <td class="add_tab_til02">2.跳转至系统<a href="#" class="blue">帮助</a>页面</td>
    </tr>
  </table>
  <div class="add_tab_bottom"> <a href="#" class="button_lant_pad" onClick="back();">返回之前页面</a> <a href="#" class="button_gray_pad" onClick="back2index('${pageContext.request.contextPath}')">返回首页</a> </div></div>
</body>
</html>
