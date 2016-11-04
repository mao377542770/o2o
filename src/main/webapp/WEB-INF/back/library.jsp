<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>讯果高校互联平台</title>
<%@include file="common/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/closeImg.css" />
	<script src="${pageContext.request.contextPath}/tmpl/back/library/library.js"></script>
	<script src="${pageContext.request.contextPath}/tmpl/back/office/office.js"></script>
	<script src="${pageContext.request.contextPath}/tmpl/back/common/common.js"></script>	
	<style type="text/css">
		.fix-modal-open {
		  overflow: initial;
		}
	</style>
</head>

<body ng-app="myApp" ng-controller="libraryCtrl">
<div class="magn-a container">
	<!--网页首行导航栏-->
	<%@include file="common/header.jsp" %>
    <!--内容开始-->
    <div class="">
        <div class="" style="margin-top:10px;">
            <div class="">
                <!--内容-->
                <div class="">
                    <!--内容-->
                    <div class="container-fluid row">
                    	<!--子导航-->
                        <div class="col-sm-2 bgd-gray7 pad" style="margin-top:50px;">
                        	<div class="line-H1 tx-c font-3 b tx-blue">功能导航</div>
                        	<ul id="navigation" class="nav nav-pills nav-stacked nav-pills-stacked-example tx-c">
                            	<!--  <li ng-repeat="module in myModules" id="{{module.moduleUrl}}"><a ui-sref="{{module.moduleUrl}}" ng-bind="module.moduleName"></a></li> -->
                               <!--  <li id="customization"><a ui-sref="customization">功能导航</a></li> -->
                                <li id="news"><a ui-sref="news">公告</a></li>
                                <li id="literature"><a ui-sref="literature">文献资源</a></li>
                                <li id="presention"><a ui-sref="presention">入馆须知</a></li>
                                <li id="consult"><a ui-sref="consult">咨询</a></li>
                                <li id="activity"><a ui-sref="activity">活动投票</a></li>  
                                <li id="lostFound"><a ui-sref="lostFound">失物招领</a></li>
                                <li id="attestation"><a ui-sref="attestation">老师认证</a></li>
                                <li id="bespoke"><a ui-sref="bespoke" ng-if="isOpenDept">会室预约</a></li>
                                <li id="carousel"><a ui-sref="carousel">轮播图管理</a></li>
                                <li id="userInfo"><a ui-sref="userInfo">个人信息</a></li>
							</ul>
                        </div>
                        <!--子导航END-->
                        <!--主内容-->
                        <div ui-view=""></div>
                        <!--主内容END-->  
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script>
var hostPath = '${pageContext.request.contextPath}';
var myApp = angular.module('myApp', ['ui.router','libraryModule','commonModule','officeModule','tm.pagination','ngSanitize']);

myApp.config(function($stateProvider,$urlRouterProvider){
    //配置路由
    $urlRouterProvider.when("", "/news");
    $stateProvider.state("news",{
        url: "/news",
        templateUrl: hostPath+"/tmpl/back/library/news.html"
    }).state("literature",{
        url: "/literature",
        templateUrl: hostPath+"/tmpl/back/library/literature.html"
    }).state("customization",{
        url: "/customization",
        templateUrl: hostPath+"/tmpl/back/library/customization.html"
    }).state("userInfo",{
        url: "/userInfo",
        templateUrl: hostPath+"/tmpl/back/common/userInfo.html"
    }).state("presention",{
        url: "/presention",
        templateUrl: hostPath+"/tmpl/back/library/presention.html"
    }).state("activity",{
        url: "/activity",
        templateUrl: hostPath+"/tmpl/back/common/activity.html"
    }).state("consult",{
        url: "/consult",
        templateUrl: hostPath+"/tmpl/back/library/consult.html"
    }).state("lostFound",{
        url: "/lostFound",
        templateUrl: hostPath+"/tmpl/back/common/lostFound.html"
    }).state("carousel",{
        url: "/carousel",
        templateUrl: hostPath+"/tmpl/back/common/carousel.html"
    }).state("attestation",{
        url: "/attestation",
        templateUrl: hostPath+"/tmpl/back/common/attestation.html"
    }).state("bespoke",{
        url: "/bespoke",
        templateUrl: hostPath+"/tmpl/back/office/bespoke.html"
    })

})

myApp.controller('libraryCtrl',function($scope,$rootScope,$http){
    //初始化请求方式为 x-application
    $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

	//初始化登录用户信息
    $rootScope.user = {
        id:'<%=user.getId()%>',
        nickName:'<%=user.getNickName()%>',
        schoolId:'<%=user.getSchoolId()%>',
        userType:'<%=user.getUserType()%>',
        deptId:'<%=user.getDeptId()%>',
        name:'<%=user.getName()%>',
        attestationState:'<%=user.getAttestationState()%>'
    }
    
    $rootScope.deptName = '${schoolInfo.deptName}';
    //是否开通了办公区
    $scope.isOpenDept = false;
    $scope.isOpenDept = function(){
		$http.post(hostPath+"/back/isOpenDept",$.param({"schoolId":$rootScope.user.schoolId})).success(function(response){
			if(response.status == 1){
				$scope.isOpenDept = true;
			}else{
				 $scope.isOpenDept = false;
			}
		});
	}
   $scope.isOpenDept();
   
    //注销登录
    $scope.logout = function(){
       $http.post(hostPath + '/logout').success(function(result) {
          if(result.status == 1){            
            location.href = hostPath+"/back/login";
          }
        }).error(function() {
          console.error('请求出错!');
        })
    }
    
})
</script>
</html>
