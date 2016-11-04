<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="${pageContext.request.contextPath}/images/xg11.ico" rel="bookmark" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="${pageContext.request.contextPath}/images/xg11.ico" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/images/xg11.ico" mce_href="${pageContext.request.contextPath}/images/xg11.ico" rel="shortcut icon" type="image/x-icon" />
<title>讯果高校互联平台登录</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.min.css">
<!--指定样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/min.css">
<!--颜色样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/color.css">
<!--边框样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/border.css">
<!--登录页样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/loginstyle.css">

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/angular.js/1.5.6/angular.min.js"></script>
</head>

<body ng-app="myApp" ng-controller="loginCtrl">
<!--导航栏-->
<div class="TM bgd-white" style="margin-top:10%;">
    <div class="TM_A border1 bor-gray2">
        <div class="TM_C">
        	<div class="TM_C_right">
                <img class="col-fg-8 "style="margin:8%;" src="${pageContext.request.contextPath}/images/xg9.png" />
            </div>

            <ng-form name="loginForm">
                <div class="TM_C_left">
                    <table class="TM_C_T">
                        <tr>
                            <td width="70">用户名：</td><td><input onkeydown="if(event.keyCode==13){loginButton.click()}" required ng-model="user.userName" name="userName" class="form-control" type="text" placeholder="用户名/手机号"></td>
                        </tr>
                        <tr>
                            <td>密码：</td><td><input onkeydown="if(event.keyCode==13){loginButton.click()}" required name="passWord" ng-model="user.passWord" class="form-control" type="password" placeholder="用户密码"></td>
                        </tr>
                        <tr>
                            <td>验证码：</td>
                            <td class="TM_C_Tf">
                            <input onkeydown="if(event.keyCode==13){loginButton.click()}" required name="patcha" ng-model="user.patchca" class="form-control" type="text" placeholder="验证码"  maxlength="4" style="width:60px;">
                                <span ng-init="patchca = '${pageContext.request.contextPath}/patchca'" style="margin-top:3px;"><img
                                        style="width:110px;height:30px;cursor:pointer"
                                        src="{{patchca}}" ng-click="changePatchca()"/></span>
                            </td>
                        </tr>
                    </table>
                    <p ng-bind="error" class="TM_C_P"></p>
                    <div>
                        <button id="loginButton" ng-click="goLogin(user)" ng-disabled="loginForm.$invalid" type="submit" type="button" class="btn btn-primary" style="width:250px;">登录</button>
                    </div>
                    <div style="margin-top:15px; width:245px; overflow:hidden">
                        <div style="width:125px; float:left;">
                        <input ng-model="user.chkRememberMe" ng-init="user.chkRememberMe = true" name="chkRememberMe" type="checkbox" class="jdcheckbox" tabindex="3" style="outline: rgb(109, 109, 109) none 0px;">
                        <label for="chkRememberMe" class="tx-red" style="font-weight:normal;" >下次记住我</label>
                        </div>
                        <a class="TM_C_W" style="cursor: pointer" onClick="alert('请联系管理员重置密码!')">忘记密码？</a>
                    </div>
                </div>
            </ng-form>
            
        </div>
    </div>	
</div>
</body>
<script type="text/javascript">
var hostPath = '${pageContext.request.contextPath}';

var myApp = angular.module('myApp', [])
.controller('loginCtrl', function($scope,$http) {
    //初始化$http 请求方式为 json
    //$http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
    //初始化请求方式为 x-application
    $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
	
    //更新验证码
    $scope.changePatchca = function() {
        $scope.patchca = hostPath + '/patchca?' + Math.random();
    }

    $scope.changePatchca();

    $scope.goLogin = function(user) {
        $http.post(hostPath + "/back/goLogin", $.param({
            'userName': user.userName,
            'passWord': user.passWord,
            'patchca': user.patchca,
            'chkRememberMe': user.chkRememberMe
        })).success(function(result) {
            if (result.status == 1) {
                location.href = hostPath+"/back";
            } else if (result.status == 2) {
                $scope.error = '账号或密码错误';
            } else if (result.status == 3) {
                $scope.error = '验证码有误';
            } else if (result.status == 4) {
                $scope.error = '账号无效';
            }else if(result.status == 5){
        		$scope.error = result.msg;
        	}else if(result.status == 6){
        		$scope.error = '所属部门未开通或到期';
        	}
        }).error(function() {
			$scope.error = '登录出错';
        })
    }
})
</script>
</html>
