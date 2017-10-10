<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录超时</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/favicon.ico" />
<link href="${pageContext.request.contextPath}/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/common/navigate.js"></script>

<script type="text/javascript">
    var win = window;
    if (win != win.parent)
    {
	    while (win != win.parent) 
	    {
	        win = win.parent;
	    }
	    
	    win.location.replace("${pageContext.request.contextPath}/timeout.jsp");
    }
    
</script>

</head>
<body>
<div class="add_tab_box01">
  <table width="100%">
    <tr>
      <td width="30%" align="right" rowspan="4" valign="top" height="50" class="add_tab_img"><img src="${pageContext.request.contextPath}/resource/images/icon_chaoshi.png"></td>
      <td width="70%" valign="top" height="50" class="add_tab_til">对不起，登录已超时！</td>
    </tr>
    <tr>
      <td width="20%" class="add_tab_til01"></td>
    </tr>
    <tr>
      <td class="add_tab_til02"></td>
    </tr>
    <tr>
      <td class="add_tab_til02"></td>
    </tr>
  </table>
  <div class="add_tab_bottom"> <a href="#" class="button_lant_pad" onClick="back2index('${pageContext.request.contextPath}');">重新登录</a></div></div>
</body>
</html>
