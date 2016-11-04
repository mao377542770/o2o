<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%
	long time = 20160826;
%>
<meta charset="utf-8" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="height = device-hight,width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="${pageContext.request.contextPath}/images/xg11.ico" rel="bookmark" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="${pageContext.request.contextPath}/images/xg11.ico" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="${pageContext.request.contextPath}/images/xg11.ico" rel="shortcut icon" type="image/x-icon" />    
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<!--指定样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css?time=<%=time%>" />
<!--颜色样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/color.css?time=<%=time%>" />
<!--边框样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/border.css?time=<%=time%>" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/button.css?time=<%=time%>" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/min.css?time=<%=time%>" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/general.css?time=<%=time%>" />
<!-- 时间插件样式 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobiscroll-master/mobiscroll.animation.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobiscroll-master/mobiscroll.frame.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobiscroll-master/mobiscroll.scroller.css" />
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/angular.js/1.5.6/angular.min.js"></script>
<script src="//cdn.bootcss.com/angular-ui-router/0.3.0/angular-ui-router.js"></script>
<script src="//cdn.bootcss.com/angular-touch/1.5.7/angular-touch.min.js"></script>
<script src="${pageContext.request.contextPath}/js/directive/numInputDt.js"></script>
<script src="${pageContext.request.contextPath}/js/directive/ng-infinite-scroll.js"></script>


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