/**
*  Module
*
* Description
*/
angular.module('userInfoModule', []).controller('userInfoCtrl', ['$scope','$rootScope','$http',function($scope,$rootScope,$http){
	$scope.getUserInfo = function(){
		$http.post(hostPath+'/user/getUserInfo',$.param({'id':$rootScope.user.id})).success(function(result){
			result.data.md5code = result.data.passWord;
			result.data.passWord = null;
			$scope.user = result.data;
		}).error(function(){
			console.error('请求出错');
		})
	}
	$scope.getUserInfo();

	$scope.updatePwd = function(){
		if($scope.user.oldPassWord == $scope.user.passWord){
			alert('新密码不能和旧密码相同');
			return;
		}
		$http.post(hostPath+'/user/updatePwd',$.param({
			userId:$scope.user.id,
			oldPassWord:$scope.user.oldPassWord,
			passWord:$scope.user.passWord
		})).success(function(result){
			if(result.status == 1){				
				alert('修改成功');
				$scope.switchBt = true;
			}else{
				alert(result.msg);
			}
		}).error(function(){
			console.error('请求出错');
		})
	}

	//修改个人信息
	$scope.modify = function(){
		if($scope.modifyStatus){
			$scope.modifyStatus = !$scope.modifyStatus;
			return;
		}
		if($scope.myForm.$invalid){
			alert('信息有误,请检查红色标记框数据!');
			return;
		}
		$scope.user.passWord = null;
		$http.post(hostPath+'/user/updateUserInfo',$.param({
			id:$scope.user.id,
			name:$scope.user.name,
			nickName:$scope.user.nickName,
			telphone:$scope.user.telphone,
			gender:$scope.user.gender
		})).success(function(result){
			if(result.status == 1){				
				//修改cookie和session信息
				$http.post(hostPath+'/user/updateLoginStatus',$.param({userId:$rootScope.user.id}));
				alert('修改成功');
				$scope.modifyStatus = true;
				$rootScope.user.name = $scope.user.name;
				$rootScope.user.nickName = $scope.user.nickName;
				$rootScope.user.telphone = $scope.user.telphone;
				$rootScope.user.gender = $scope.user.gender;
			}else{
				alert(result.msg);
			}
		}).error(function(){
			console.error('请求出错');
		})
	}
}]).directive('confirm',function(){   //密码相同验证
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