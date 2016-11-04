<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
 <%@include file="common/head.jsp" %>
<title>登录</title>
<script src="${pageContext.request.contextPath}/tmpl/front/userInfo/forgetPwd.js"></script>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="height = device-hight,width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">       
<style>
.dianji {
    border-color: #0184dc;
    border-bottom: 2px solid;
    color: #0184dc;
}
.c_box{
    width: 50%
}
</style>
</head>
<body ng-app="myApp" onselectstart="return true;" ondragstart="return false;" class="bgd-gray9">
<div class="body">
<!--===top  顶部===-->
<div ng-controller="menuCtrl" class="FD tx-c  bor-gray bgd-white" style="line-height:50px;font-size:16px; position:fixed; top:0; width:100%; z-index:10">
	<div ui-sref="login"  class="col-fg-5 xiaos pull-left c_box  bor-gray" ng-class={'dianji':menu.login}>登录</div>
    <div ui-sref="regist" class="col-fg-5 xiaos pull-left c_box border-b-s bor-gray" ng-class={'dianji':menu.regist}>注册</div>
</div>

<div style="height:50px;"></div>

<div ui-view=""></div>
</div>
</body>

<script type="text/javascript">
var myApp = angular.module('myApp',['ui.router','numInputDt','forgetPwdModule']);

myApp.config(function($stateProvider,$urlRouterProvider){
     $urlRouterProvider.when("", "/login");
     $stateProvider
        .state("login", {
            url: "/login",
            templateUrl: hostPath+"/tmpl/front/login.html"
        }).state("regist",{
            url:"/regist",
            templateUrl:hostPath+"/tmpl/front/regist.html" 
        }).state("forgetPwd",{ 
            url:"/forgetPwd/",
            templateUrl: hostPath+"/tmpl/front/userInfo/forgetPwd.html"
        })
})

myApp.controller('menuCtrl', function($scope,$http,menu){
    $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $scope.menu = menu.menu;
})

myApp.controller('loginCtrl',function($scope,$http,menu){
    menu.setMenu('login');
    $scope.login = function(user){
        $http.post(hostPath+'/front/goLogin',$.param({
        	 'userName': user.userName,
             'passWord': user.passWord,
             'chkRememberMe': user.remember
        })).success(function(result){
        	if(result.status == 1){
        		location.href = hostPath+"/front";
        	}else if(result.status == 5){
        		alert(result.msg);
        	}else{
        		$scope.error = true;
        	}
        }).error(function(){
            console.error('出错了');
        });
    }
})

myApp.controller('registCtrl',function($scope,$http,menu){
    menu.setMenu('regist');

    $scope.city = '昆明市';
    $scope.keyWord = null;
    $scope.user = {
        'schoolId':null,
    }
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
        $scope.user.schoolId = school.id;
        $scope.schoolName = school.name;
        $scope.regDiv = true;
        
        $scope.registBtn = false;
        $scope.selectSchool = false;
    }

    $scope.save = function() {
    	if( $scope.user.schoolId == null || $scope.user.schoolId == ''){
    		$scope.selectSchool = true;
    		return;
    	}
    	//用该属性控制注册按钮禁用,注册成功则设为false
   		$scope.registBtn = true;
        $http.post(hostPath + '/registUser', $.param(
                $scope.user
            ))
            .success(function(result) {
                if (result.status == 1) {
                	//$scope.registBtn = false;
                    $scope.login($scope.user);
                } else {
                    alert('注册失败,请检查信息是否有误');
                }
            }).error(function() {
                alert('注册失败,请检查信息是否有误');
            })
    }

    $scope.login = function(user) {
        $http.post(hostPath + '/front/goLogin', $.param({
            'userName': user.userName,
            'passWord': user.passWord,
            'chkRememberMe': true
        })).success(function(result) {
            if (result.status == 1) {
                location.href = hostPath + "/front";
            } else if (result.status == 5) {
                alert(result.msg);
            } else {
                $scope.error = true;
            }
        }).error(function() {
            console.error('出错了');
        });
    }


}).directive('search',function(){  //该指令主要是为了执行回车键搜索
    return {
        scope: {
            'search':'&'
        }, 
        restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
        template: '<input ng-keyup="toSearch($event)"/>',
        replace: true,
        link: function($scope, iElm, iAttrs, controller) {
            $scope.toSearch = function(e){
                 var keycode = window.event?e.keyCode:e.which;
                if(keycode==13){
                    $scope.search();
                }
            }
        }
    };
}).directive('checkUsername',function($http){   //验证手机号是否重复
    return {
        require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
        link: function($scope, iElm, iAttrs, controller) {
            iElm.blur(function(){  //失去焦点
                $http.post(hostPath+'/checkUserName',$.param({userName:iElm.val()}))
                .success(function(result){
                    if(result.status == 1){
                        controller.$setValidity('us',true);
                    }else{
                        controller.$setValidity('us',false);
                    }
                }).error(function(){
                    controller.$setValidity('us',false);
                })
            });
        }
    };
}).directive('checkTel',function($http){   //验证手机号是否重复
    return {
        require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
        link: function($scope, iElm, iAttrs, controller) {
            iElm.blur(function(){  //失去焦点
                $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
                $http.post(hostPath+'/checkUserTelphone',$.param({telphone:iElm.val()}))
                .success(function(result){
                    if(result.status == 1){
                        controller.$setValidity('tel',true);
                    }else{
                        controller.$setValidity('tel',false);
                    }
                }).error(function(){
                    controller.$setValidity('tel',false);
                })
            });
        }
    };
}).directive('checkNickname',function($http){   //验证手机号是否重复
    return {
        require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
        link: function($scope, iElm, iAttrs, controller) {
            iElm.blur(function(){  //失去焦点
                $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
                $http.post(hostPath+'/checkNickName',$.param({nickName:iElm.val()}))
                .success(function(result){
                    if(result.status == 1){
                        controller.$setValidity('nn',true);
                    }else{
                        controller.$setValidity('nn',false);
                    }
                }).error(function(){
                    controller.$setValidity('nn',false);
                })
            });
        }
    };
}).directive('confirm',function(){   //密码相同验证
    return {
        scope: {
            obj:'='
        }, // {} = isolate, true = child, false/undefined = no change
        require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
        link: function($scope, iElm, iAttrs, controller) {
            iElm.keyup(function(){
                if($scope.obj.passWord == $scope.obj.confirm){
                    $scope.obj.checkPwd = false;
                }else{
                    $scope.obj.checkPwd = true;
                }
                $scope.$apply();
            })

            iElm.blur(function(){
                if($scope.obj.passWord == $scope.obj.confirm){
                    $scope.obj.checkPwd = false;
                }else{
                    $scope.obj.checkPwd = true;
                }
                $scope.$apply();
            })
        }
    };
});

myApp.factory('menu',function(){
    return {
        menu:{
            login:true,
            regist:false
        },
        setMenu:function(param){
            menu = this.menu;
            $.each(menu,function(key,value){
                menu[key] = false;
            })
            menu[param] =  true;
        }
    }
}).filter('schoolFilter',function(){
    return function(ipt){
        return (ipt == null || ipt == '' || ipt == undefined ) ? '选择学校 ▼' : ipt;
    }
})
</script>
</html>
