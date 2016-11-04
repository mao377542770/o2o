<%@page import="com.ugfind.model.User"%>
<%@page import="com.ugfind.util.LoginUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
		User user =  LoginUtil.getUserInfo(request);
 %>
<!--网页首行导航栏-->
<div class="clearfix header row" style="margin-bottom:20px;">
    <nav style="margin-top:30px;">
      <ul class="nav navbar-nav navbar-right magn">
        　<li><a class="xiaos tx-blue"><%=user.getNickName() %><span style="color:#9d9d9d;" class="font-1">管理员</span></a></li>
        　<li style="margin-top: 10px; margin-left:10px;"><button type="button" class="btn btn-sm btn-default" ng-click="logout()">&nbsp;退&nbsp;&nbsp;&nbsp;出&nbsp;</button></li>
    　</ul>
    </nav>
    <h3 class="text-muted line-H1 col-fg-3 magn-lr" style="margin-top:10px;" >${schoolInfo.schoolName}</h3>
    <div class="tx-r font-6 col-fg-3" >${schoolInfo.deptName}<span class="font-1 tx-gray2 pad-l">服务至${schoolInfo.serviceTime}</span></div>
</div>
