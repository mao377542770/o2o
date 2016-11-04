<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.net.URLDecoder"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">    
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="./app/view/back/images/favicon.ico" rel="bookmark" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="./app/view/back/images/favicon.ico" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="./app/view/back/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/angular.js/1.5.6/angular.min.js"></script>
<script src="//cdn.bootcss.com/angular-ui-router/0.3.0/angular-ui-router.js"></script>
<script src="//cdn.bootcss.com/angular-touch/1.5.7/angular-touch.min.js"></script>
<script src="${pageContext.request.contextPath}/js/directive/numInputDt.js"></script>
<script src="${pageContext.request.contextPath}/js/tm.pagination.js"></script>
<!-- 文件上传组件 -->
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.4/js/fileinput.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.4/js/locales/zh.min.js"></script>
<!--获取服务器状态值-->
<%
	String name = null;
	String telphone = null;
	Integer patientId = null;
	try{
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
	    	  	if("wxPtStatus".equals(cookie.getName())){
	    	  		name = URLDecoder.decode(cookie.getValue().split("\\|")[0], "utf-8");
	    	  		telphone = URLDecoder.decode(cookie.getValue().split("\\|")[1], "utf-8");
	    	  		patientId = Integer.parseInt(cookie.getValue().split("\\|")[3]);
	    	  	}
	     }
	     if(name == null){
	     	name = (String)session.getAttribute("name");
			telphone = (String)session.getAttribute("telphone");
			patientId = (Integer) session.getAttribute("patientId");
	     }
	}catch(Exception e){
		
	}
%>
<script>
var name = '<%=name%>';
var telphone = '<%=telphone%>';
var patientId = '<%=patientId%>';
var hostPath = '${pageContext.request.contextPath}';
</script>