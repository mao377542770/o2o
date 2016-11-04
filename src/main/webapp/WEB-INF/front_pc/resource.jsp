<%@page import="com.ugfind.model.User"%>
<%@page import="com.ugfind.util.LoginUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<%@include file="common/head.jsp" %>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_pc.css" />
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/box.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/tmpl/front_pc/resource/fullInfo.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/directive/comm.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/tmpl/front_pc/resource/resourceInfo.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/tmpl/front_pc/resource/search.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/tmpl/front_pc/resource/userInfo.js"></script>
    <title>讯果平台</title>
  </head>
  <%
		User user =  LoginUtil.getUserInfo(request);
 %>
  <body ng-app="myApp" ng-controller="indexCtrl">
    <nav class="navbar navbar-default navbar-static-top myNav" role="navigation">
    	 <div class="container-fluid">
    		<!-- Brand and toggle get grouped for better mobile display -->
    		<div class="navbar-header">
    			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
    				<span class="sr-only">讯果平台</span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    			</button>
    			<a class="navbar-brand" href="#">讯果平台</a>
    		</div>
    		<!-- 按钮组 -->
    		<div class="collapse navbar-collapse navbar-ex1-collapse">
    			<ul class="nav navbar-nav">
                    <!--
    				<li class="active"><a href="#">开发中</a></li>
    				<li><a href="#">开发中</a></li>
                    -->
    			</ul>
    			<ul class="nav navbar-nav navbar-right">
    				<li ng-hide="user.nickName != null && user.nickName != ''"><a href="${pageContext.request.contextPath}/resource/login">登录</a></li>
    				<li ng-show="user.nickName != null && user.nickName != ''" class="dropdown">
    					<a href="#" class="dropdown-toggle dropdown_minsize" data-toggle="dropdown">
    						<img ng-src="{{user.pic}}" err-src="images/avatar5.png" class="user-image" alt="{{user.nickName}}">
    						<span class="hidden-xs" ng-bind="user.nickName"></span>
    					</a>
    					<ul class="dropdown-menu">
    						<li><a href="#" ui-sref="userInfo">个人信息</a></li>
                            <!--
    						<li><a href="#">功能1</a></li>
    						<li><a href="#">功能2</a></li>
                            -->
    						<li><a href="javascript:void(0)" ng-click="logout()">注销登录</a></li>
    					</ul>
    				</li>
    			</ul>
    		</div>
    	</div>
    </nav>

    <header class="site-header">
    	<div class="container">
    		<div class="row">
    			<div class="col-xs-12">
    				<h1>讯果资源</h1>
    				<p>
    					快速,免费,优质的学习资源共享平台
    					<br>    
    					<span class="package-amount">
    						共收录了 <strong>${resourceTotal}</strong>
    						个相关资源
    					</span>
    				</p>
    				<form role="search" class="">
    					<div class="form-group">
    						<input search="toSearch()" ng-model="searchKey" type="text" placeholder="搜索资源，例如：jquery" class="form-control search clearable"> <i class="fa glyphicon glyphicon-search"></i>
    					</div>
    					<div class="form-group" class="">
    						<button ui-sref="upload" type="button" class="btn btn-info btn-shadow">我要上传</button>
    					</div>
    				</form>
    			</div>
    		</div>
    	</div>
    </header>
	
	<nav class="main-navigation">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="navbar-header">
						<span data-target="#main-menu" data-toggle="collapse" class="nav-toggle-button collapsed">
							<span class="sr-only">Toggle navigation</span> <i class="fa fa-bars"></i>
						</span>
						<i class="fa fa-bars"></i>
					</div>
					<div id="main-menu" class="collapse navbar-collapse">
						<ul class="menu">
							<li role="presentation" ui-sref-active="nav-current">
								<a ui-sref="index">首页</a>
							</li>
							<li ui-sref="search({searchKey:null,type:1})" role="presentation" ui-sref-active="nav-current">
								<a href="#">人文社会</a>
							</li>
							<li ui-sref="search({searchKey:null,type:2})" role="presentation" ui-sref-active="nav-current">
								<a title="Lumen中文文档" href="#">经管营销</a>
							</li>
							<li ui-sref="search({searchKey:null,type:3})" role="presentation" ui-sref-active="nav-current">
								<a title="Laravel问答社区" href="#">工程科技</a>
							</li>
							<li ui-sref="search({searchKey:null,type:4})" role="presentation" ui-sref-active="nav-current">
								<a title="Laravel 中文文档" href="#">IT/计算机</a>
							</li>
							<li ui-sref="search({searchKey:null,type:5})" role="presentation" ui-sref-active="nav-current">
								<a title="下载 Laravel 中文文档离线版" href="#">自然科学</a>
							</li>
							<li ui-sref="search({searchKey:null,type:6})" role="presentation">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
									医药卫生
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li>
										<a href="">功能1</a>
										<a href="">功能2</a>
										<a href="">功能3</a>
										<a href="">功能4</a>
									</li>
								</ul>
							</li>
							<li role="presentation">
								<a title="下载 Laravel 中文文档离线版" href="#">更多</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
	
	<!-- 内容部分 -->
	<div ng-controller="mainCtrl" class="container-fluid" ui-view=""></div>

	<!-- 公司信息 -->
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<span>
						Copyright &copy;
						<a href="http://www.ghostchina.com/">优超科技有限公司</a>
					</span>
					|
					<span>
						<a target="_blank" href="http://www.miibeian.gov.cn/">京ICP备11008151号</a>
					</span>
					|
					<span>京公网安备11010802014853</span>
				</div>
			</div>
		</div>
	</div>

  </body>
</html>

<script type="text/javascript">
var myApp = angular.module('myApp', ['ui.router','fullInfoModule','resourceInfoModule','comm','resourceSearchModule',
    'userInfoModule']);

myApp.config(['$stateProvider','$urlRouterProvider',
 function($stateProvider,$urlRouterProvider) {
	//配置路由
    $urlRouterProvider.when("", "/index");
    $stateProvider.state("index",{
        url: "/index",
        templateUrl: hostPath+"/tmpl/front_pc/resource/index.html"
    }).state("upload",{			//上传资源
        url: "/upload",
        templateUrl: hostPath+"/tmpl/front_pc/resource/upload.html"
    }).state("fullInfo",{   //填写详情
        url: "/fullInfo/:resourceId",
        templateUrl: hostPath+"/tmpl/front_pc/resource/fullInfo.html"
    }).state("finish",{         //上传资源
        url: "/finish",
        templateUrl: hostPath+"/tmpl/front_pc/resource/finish.html"
    }).state("resourceInfo",{         //资源详情
        url: "/resourceInfo/:id",
        templateUrl: hostPath+"/tmpl/front_pc/resource/resourceInfo.html"
    }).state("search",{
        url: "/search/:searchKey?:type",
        templateUrl: hostPath+"/tmpl/front_pc/resource/search.html"
    }).state("userInfo",{   //个人信息
        url:"/userInfo",
        templateUrl: hostPath+"/tmpl/front_pc/resource/userInfo.html"
    })
}])


myApp.controller('indexCtrl', ['$rootScope','$scope','$http','$state',
 function($rootScope,$scope,$http,$state){
	 //初始化$http 请求方式为 json
    //$http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
    //初始化请求方式为 x-application
    $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $rootScope.user = {
      id:null,nickName:null,schoolId:null,userType:null
    };
    
   //初始化登录用户信息
    <%if(user != null){%>
  	    $rootScope.user = {
  	        id:'<%=user.getId()%>',
  	        nickName:'<%=user.getNickName()%>',
  	        schoolId:'<%=user.getSchoolId()%>',
  	        userType:'<%=user.getUserType()%>',
            pic:'<%=user.getPic()%>',
            attestationState:'<%=user.getAttestationState()%>'
  	    }
    <%}%>


    $scope.toSearch = function(){
        if($scope.searchKey!=null || $scope.searchKey != ''){
            $state.go('search',{searchKey:$scope.searchKey,type:null});
        }
    }

    //注销登录
    $scope.logout = function(){
       //先删除cookie
       //$.cookie('UGO2OLogin',null); 
       $http.post(hostPath + '/logout').success(function(result) {
          if(result.status == 1){            
            location.reload();
          }
        }).error(function() {
          console.error('请求出错!');
        })
    }

}]).controller('mainCtrl', ['$rootScope','$scope','$http',
 function($rootScope,$scope,$http){
    //获得最新的10条文档
    $scope.getNewResource = function(){
        var pageInfo = {
            currentPage: 0,  //当前页码
            totalItems: 0,  //总条数
            itemsPerPage: 10 //每页展示多少条数据
        }
        $http.post(hostPath+'/resource/getResourceList',$.param(pageInfo))
        .success(function(result){
            if(result.status == 1){
                $scope.newResourceList = result.data;
            }
        }).error(function(){
            console.error('请求失败');
        })
    }
    $scope.getNewResource();

    //根据下载次数排序
    $scope.getResourceOrderByDown = function(){
        $http.post(hostPath+'/resource/getResourceByDown')
        .success(function(result){
            if(result.status == 1){
                $scope.topResourceList = result.data;
            }
        }).error(function(){
            console.error('请求失败');
        })
    }
    $scope.getResourceOrderByDown();
}])


/**
 * [description] 过滤null为指定值
 * @param  {[type]} ){} [description] param指定的值
 * @return {[type]}       [description]
 */
myApp.filter('changeNull',function(){
	return function(ipt,param){
		if(ipt == null){
			return param;
		}
	}
})

//图片加载错误的过滤器
myApp.directive('errSrc', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('error', function() {
                if (attrs.src != attrs.errSrc) {
                    attrs.$set('src', attrs.errSrc);
                }
            });
        }
    };
})
</script>