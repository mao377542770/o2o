﻿<%@page import="com.ugfind.model.User"%>
<%@page import="com.ugfind.util.LoginUtil"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@include file="common/head.jsp" %>
<script src="${pageContext.request.contextPath}/tmpl/front/deptMenu/deptMenu.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/library/policy.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/library/news.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/library/literature.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/schoolActivity/schoolActivity.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/contact/contact.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/commonModule/consult.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/personalShow/affect.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/personalShow/relax.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/userInfo/userInfo.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/userInfo/forgetPwd.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/lostFound/lostFound.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/office/roomReservation.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/employment/news.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/employment/workStory.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/employment/recruit.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/tmpl/front/resource/resource.js?time=<%=time%>"></script>
<script src="${pageContext.request.contextPath}/js/directive/ng-infinite-scroll.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>

<!-- 时间插件js -->
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/mobiscroll.core.js"></script>
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/mobiscroll.frame.js"></script>
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/mobiscroll.scroller.js"></script>
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/mobiscroll.util.datetime.js"></script>
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/mobiscroll.datetimebase.js"></script>
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/mobiscroll.datetime.js"></script>
<script src="${pageContext.request.contextPath}/js/mobiscroll-master/i18n/mobiscroll.i18n.zh.js"></script>

<title>讯果高校互联平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="height = device-hight,width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">       
</head>
<%
		User user =  LoginUtil.getUserInfo(request);
 %>
<body ng-app="myApp" ng-controller="indexCtrl" onselectstart="return true;" ondragstart="return false;">
<div class="body">
<div ui-view=""></div>


<!--底部bottom-->

	<div class="col-fg-10 bottom tx-black bgd-gray8 ">
    	<a class="fudong-l tx-c col-fg-5 border-r-sg line-H0 ft-3 tx-gray5"  ui-sref="index"><img class="yj_p" src="images/home1.png">&nbsp;首页</a>
       <!--  <li><a><img class="yj_p" src="images/collect">&nbsp;收藏夹</a></li>
        <li><a><img class="yj_p" src="images/message">&nbsp;消息</a></li> -->
       <!-- <div style="width:25%" ui-sref="contact" style="border:none"><a><img class="yj_p" src="images/contact.png">&nbsp;联系我们</a></div>-->
        <a class="fudong-l tx-c col-fg-5 line-H0 ft-3 tx-gray5"   ui-sref="contact" style="border:none"><img class="yj_p" src="images/contact.png">&nbsp;联系我们</a>
    </div>
 </div>
</body>

<script type="text/javascript">
var hostPath = '${pageContext.request.contextPath}';
var myApp = angular.module('myApp', ['ui.router','ngTouch',
			'deptMenuModule','policyModule','lb_newsModule','schoolActivityModule',
			'contactModule','literatureModule','consultModule','affectMomentModule',
			'relaxMomentModule','userInfoModule','roomReservationModule','forgetPwdModule',
			'lostFoundModule','em_newsModule','workStoryModule','recruitModule',
      'resourceModule']);

var ver = 201608291806;

myApp.config(function($stateProvider,$urlRouterProvider){
    //配置路由
    $urlRouterProvider.when("", "/index");
    $urlRouterProvider.when("/index", "/index/library");
    $stateProvider.state("index",{
        url: "/index",
        templateUrl: hostPath+"/tmpl/front/index.html?"+ver
    }).state("userInfo",{  //个人信息
        url:"/userInfo/",
        templateUrl: hostPath+"/tmpl/front/userInfo/userInfo.html?"+ver
    }).state("forgetPwd",{ 
        url:"/forgetPwd/",
        templateUrl: hostPath+"/tmpl/front/userInfo/forgetPwd.html?"+ver
    }).state("index.library",{  
        url: "/library",
        templateUrl: hostPath+"/tmpl/front/deptMenu/library.html?"+ver
    }).state("index.jwc",{  
        url: "/jwc",
        templateUrl: hostPath+"/tmpl/front/deptMenu/jwc.html?"+ver
    }).state("index.employ",{  
        url: "/employ",
        templateUrl: hostPath+"/tmpl/front/deptMenu/employ.html?"+ver
    }).state("index.workspace",{  
        url: "/workspace",
        templateUrl: hostPath+"/tmpl/front/deptMenu/workspace.html?"+ver
    }).state("selectSchool",{
        url: "/selectSchool",
        templateUrl: hostPath+"/tmpl/front/selectSchool.html?"+ver
    }).state("subscribe",{  //申请开通
        url:"/subscribe",
        templateUrl: hostPath+"/tmpl/front/deptMenu/subscribe.html?"+ver
    }).state("policy",{  //入馆须知
        url:"/policy/:schoolId",
        templateUrl: hostPath+"/tmpl/front/library/policy.html?"+ver
    }).state("policy.introduction",{  
        url:"/introduction",
        templateUrl: hostPath+"/tmpl/front/library/introduction.html?"+ver
    }).state("policy.openTime",{ 
        url:"/openTime",
        templateUrl: hostPath+"/tmpl/front/library/openTime.html?"+ver
    }).state("policy.rule",{ 
        url:"/rule",
        templateUrl: hostPath+"/tmpl/front/library/rule.html?"+ver
    }).state("policy.ruleInfo",{ 
        url:"/ruleInfo/:ruleId",
        templateUrl: hostPath+"/tmpl/front/library/ruleInfo.html?"+ver
    }).state("lb_news",{  
        url:"/lb_news/:schoolId",
        templateUrl: hostPath+"/tmpl/front/library/news.html?"+ver
    }).state("lb_newsInfo",{
        url:"/lb_newsInfo/:newsId",
        templateUrl: hostPath+"/tmpl/front/library/newsInfo.html?"+ver
    }).state("literature",{
        url:"/literature/:schoolId",
        templateUrl: hostPath+"/tmpl/front/library/literature.html?"+ver
    }).state("consult",{
        url:"/consult/:deptId",   //咨询模块
        templateUrl: hostPath+"/tmpl/front/commonModule/consult.html?"+ver
    }).state("consultInfo",{
        url:"/consultInfo/:id",   //咨询模块
        templateUrl: hostPath+"/tmpl/front/commonModule/consultInfo.html?"+ver
    }).state("toConsult",{
        url:"/toConsult/:deptId",   //咨询模块
        templateUrl: hostPath+"/tmpl/front/commonModule/toConsult.html?"+ver
    }).state("schoolActivity",{
        url:"/schoolActivity/:schoolId",
        templateUrl: hostPath+"/tmpl/front/schoolActivity/schoolActivity.html?"+ver
    }).state("schoolActivityInfo",{
        url:"/schoolActivityInfo/:activityId",
        templateUrl: hostPath+"/tmpl/front/schoolActivity/schoolActivityInfo.html?"+ver
    }).state("contact",{  //联系我们
        url:"/contact",
        templateUrl: hostPath+"/tmpl/front/contact/contact.html?"+ver
    }).state("affectMoment",{  //感动瞬间
        url:"/affectMoment",
        templateUrl: hostPath+"/tmpl/front/personalShow/affectMoment.html?"+ver
    }).state("affectMomentInfo",{  //感动瞬间-详情
        url:"/affectMomentInfo/:affectId",
        templateUrl: hostPath+"/tmpl/front/personalShow/affectMomentInfo.html?"+ver
    }).state("relaxMoment",{  //轻松时刻
        url:"/relaxMoment",
        templateUrl: hostPath+"/tmpl/front/personalShow/relaxMoment.html?"+ver
    }).state("relaxMomentInfo",{  //轻松时刻-详情
        url:"/relaxMoment/:relaxId",
        templateUrl: hostPath+"/tmpl/front/personalShow/relaxMomentInfo.html?"+ver
    }).state("roomReservation",{  //会室预约
        url:"/roomReservation/:schoolId",
        templateUrl: hostPath+"/tmpl/front/office/roomReservation.html?"+ver
    }).state("lostFound",{  //失物招领
        url:"/lostFound/:schoolId",
        templateUrl: hostPath+"/tmpl/front/lostFound/lostFound.html?"+ver
    }).state("attestation",{  //老师认证
        url:"/attestation",
        templateUrl: hostPath+"/tmpl/front/office/attestation.html?"+ver
    }).state("em_news",{  //就业办公告 
        url:"/em_news/:schoolId",
        templateUrl: hostPath+"/tmpl/front/employment/news.html?"+ver
    }).state("em_newsInfo",{
        url:"/em_newsInfo/:newsId",
        templateUrl: hostPath+"/tmpl/front/employment/newsInfo.html?"+ver
    }).state("workStory",{  //职场故事
        url:"/workStory/:schoolId",
        templateUrl: hostPath+"/tmpl/front/employment/workStory.html?"+ver
    }).state("workStoryInfo",{
        url:"/workStoryInfo/:newsId",
        templateUrl: hostPath+"/tmpl/front/employment/workStoryInfo.html?"+ver
    }).state("recruit",{  //招聘资讯
        url:"/recruit/:schoolId",
        templateUrl: hostPath+"/tmpl/front/employment/recruit.html?"+ver
    }).state("recruitInfo",{
        url:"/recruitInfo/:recruitId",
        templateUrl: hostPath+"/tmpl/front/employment/recruitInfo.html?"+ver
    }).state("resource",{  //优质资源
        url:"/resource",
        templateUrl: hostPath+"/tmpl/front/resource/resource.html?"+ver
    }).state("resourceInfo",{
        url:"/resourceInfo/:id",
        templateUrl: hostPath+"/tmpl/front/resource/resourceInfo.html?"+ver
    })
})


myApp.controller('indexCtrl',function($rootScope,$scope,$http,$state,deptMenu){
    //初始化$http 请求方式为 json
    //$http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
    //初始化请求方式为 x-application
    $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $scope.deptMenu = deptMenu.menu;  //设置部门菜单

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
  	//初始化学校信息
    $rootScope.schoolInfo = {
      schoolId:'${schoolInfo.schoolId}',
      schoolName:'${schoolInfo.schoolName}',
      schoolVisits:'${schoolInfo.schoolVisits}',
      schoolDepts:[]    //用于存放该学校开通的部门id
    }

    //选择学校
    $scope.selectSchool = function(){
      	$state.go('selectSchool');
    }

    //根据学校id 获取开通部门
    $scope.getDeptBySchoolId = function() {
      if($rootScope.schoolInfo.schoolId == null || $rootScope.schoolInfo.schoolId == ''){
        return;
      }
      $http.post(hostPath + '/getDeptBySchoolId', $.param({
        'schoolId':$rootScope.schoolInfo.schoolId
      })).success(function(result) {
        $rootScope.schoolInfo.schoolDepts = [];
        if (result.status == 1) {
          for (var i = 0; i < result.data.length; i++) {
            $rootScope.schoolInfo.schoolDepts.push(result.data[i].deptId);
          }
        }
        $state.go('index.library',{},{reload:true});   //刷新,第二个是参数
      }).error(function() {
        console.error('请求出错!');
      })
    }
    
    $scope.getDeptBySchoolId();
    
    //获得游客登录状态
    $scope.getCookieState = function(){
      if($rootScope.user.id == null || $rootScope.user.id == ''){
        var schoolId = $.cookie('UGO2Ovisiter');
        if(schoolId != null){
          $rootScope.schoolInfo.schoolId = schoolId;
          //获得学校访问量等信息
          $scope.getSchoolVisitiInfo(schoolId);
        }
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

    //设置游客cookie 登录状态
    $scope.setCookieState = function(){
      //主要是为游客记录学校状态
      if($rootScope.user.id == null || $rootScope.user.id == ''){
        $.cookie('UGO2Ovisiter',$rootScope.schoolInfo.schoolId,{ expires:360,path:'/'}); //游客信息
        $scope.addSchoolVisits($rootScope.schoolInfo.schoolId);
        //$scope.getSchoolVisitiInfo($rootScope.schoolInfo.schoolId);
      }
    }

    $scope.getSchoolVisitiInfo = function(schoolId,schoolName){
      if(schoolId == null || schoolId == ''){
      	return;
      }
      $http.post(hostPath + '/getSchoolVisitiInfo', $.param({
        'schoolId':schoolId
      })).success(function(result) {
        if(result.status == 1){
          if(result.data.schoolName == null){
            result.data.schoolName = schoolName;
          }
          $rootScope.schoolInfo = result.data;
          console.log($rootScope.schoolInfo);
          $scope.getDeptBySchoolId();
        }
      }).error(function() {
        console.error('请求出错!');
      })
    }
    
    //获取轮播图列表
    $rootScope.schoolIdBanna = 0;
	$scope.getCarouselList = function(){
		$scope.bannerPic1 ="";
		$scope.bannerPic2 ="";
		$scope.bannerPic3 ="";
		$scope.Clength = 2;
		if($rootScope.schoolIdBanna ==undefined||$rootScope.schoolIdBanna ==null||$rootScope.schoolIdBanna ==0){
			$rootScope.schoolIdBanna = $rootScope.user.schoolId;
		}
		$http.post(hostPath+"/carousel/getCarouselListFront",$.param({"schoolId":$rootScope.schoolIdBanna})).success(function(response){
			if(response.status == 1){
				$scope.carouselList = response.data;
				/* if($scope.carouselList.length>0){
					$(".carousel-inner div:first").addClass("active"); //src="images/banna1.jpg"   src="images/banna2.png"
				} */
				if(response.data.length>0){
					if(response.data.length==1){
						 $scope.Clength = 1;
						 $scope.bannerPic1 = response.data[0].bannaPic;
					}else if(response.data.length==2){
						$scope.Clength = 2;
						$scope.bannerPic1 = response.data[0].bannaPic;
						$scope.bannerPic2 = response.data[1].bannaPic;
					}else{
						$scope.Clength = 3;
						$scope.bannerPic1 = response.data[0].bannaPic;
						$scope.bannerPic2 = response.data[1].bannaPic;
						$scope.bannerPic3 = response.data[2].bannaPic;
					}
				}else{
					$scope.Clength = 2;
				}
			}
		});
	}
	$scope.getCarouselList();
	
  	$scope.linkToContent = function(url){
  		if(url==""||url==undefined||url==null){
  		}else{
  			window.location.href=url;///ng-click="linkToContent(carousel.contentUrl)"
  		}
  	}
    //增加一个该学校人数 
    $scope.addSchoolVisits = function(schoolId){
      $http.post(hostPath+'/front/addSchoolVisits',$.param({'schoolId':schoolId}));
    }
    
    $scope.getCookieState();

    $scope.leftSwipe = function(){
      $('#myCarousel').carousel("next");
    }

    $scope.rightSwipe = function(){
      $('#myCarousel').carousel("prev");
    }

    $scope.backHref = function(){
      $state.go('^');
    }
}).filter('trustHtml', function($sce) {
    return function(input) {
        return $sce.trustAsHtml(input);
    }
}).directive('errSrc', function($rootScope) {
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
    }).filter("imgSrcFilter",function(){ //图片路径过滤器
		return function(src,state){
			if(src=="" || src ==null || src == undefined){
				if(state==1){
					src=hostPath+"/images/banner1.jpg";
				}else if(state==2){
					src=hostPath+"/images/banner2.png";
				}else if(state==3){
					src=hostPath+"/images/banner3.png";
				}
			}else{
				src = hostPath+"/"+src;
			}
			return src;
		}
	})

//选择学校
myApp.controller('selectSchoolCtrl',function($scope,$http,$state,$rootScope){
    $scope.city = '昆明市';
    $scope.keyWord = null;
    $scope.schoolList = [];

    //获取学校列表
    $scope.getSchoolList = function(){
        $http.post(hostPath + '/getSchoolList',$.param({
            'keyWord':$scope.keyWord,
            'city':$scope.city
        })).success(function(result){
            if(result.status == 1){
                $scope.schoolList = result.data;
            }
        }).error(function(){
            console.error('请求出错!');
        })
    }
    $scope.getSchoolList();

    //选择该学校
    $scope.selectTheSchool = function(school){
      $rootScope.schoolInfo.schoolId = school.id;
      $rootScope.schoolInfo.schoolName = school.name;
      //$scope.getDeptBySchoolId(); //切换后获取该学校部门的信息
      $scope.setCookieState();  //如果是游客,则设置该游客状态
      $scope.getSchoolVisitiInfo(school.id,school.name);
      $rootScope.schoolIdBanna = school.id;
      $scope.getCarouselList(); //选择学校后获取学校的banna图

    }
});

myApp.factory('deptMenu', function() {
  return {
    menu: {
      'library':false,
      'jwc':false,
      'employ':false,
      'workspace':false
    },
    setMenu: function(param) {
      menu = this.menu;
      $.each(menu, function(key, value) {
        menu[key] = false;
      })
      menu[param] = true;
    }
  };
});

//发布日期字符串,"2016-08-08 12:12:12" 去除时分秒
myApp.filter("publishDateFilter",function(){
    return function(str){
        var out = "";
        if(str != null && str != ''){
        	out = str.substring(0,str.indexOf(" "));
        }
        return out;
    }
});

//标题长度截取
myApp.filter("titleFilter",function(){
    return function(str,len){
        var out = str;
        var length = 25;
        if(len != null && len != ''){
        	length = len;
        }
        if(str != null && str != '' && str.length > length){
        	out = str.substring(0,length) + "...";
        }
        return out;
    }
});

function myAddCalss(){
	//console.log($(".carousel-inner").find("div"));
	$(".carousel-inner div:first").addClass("active");
}
window.onload=myAddCalss;

</script>

</html>
