/**
* forgetPwdModule Module
*
* Description
*/
angular.module('forgetPwdModule', []).controller('forgetPwdCtrl', ['$rootScope','$scope','$http','$interval'
	, function($rootScope,$scope,$http,$interval){
    $scope.canSend = false;
    $scope.user = {
        userName:null,
        code:null,
        passWord:null
    }

    
    //发送验证码  点击按钮
    $scope.sendCode4Email = function(){
    	$scope.canSend = true;

    	$http.post(hostPath+'/getForgetPwdCode',$.param({userName:$scope.user.userName})).success(function(result){
    		if(result.status == 1){

    		}else{
    			console.error('发送邮件失败');
    		}
    	}).error(function(){
    		console.error('请求失败');
    	})

    	//开始
    	$scope.time = 60;
    	$scope.timer = $interval(function(){
    		$scope.time --;
    		if($scope.time <= 1){
    			$scope.time = null;
    			$scope.canSend = false;
    			$interval.cancel($scope.timer);
    		}
    	},1000)
    }

    $scope.updatePwdByCode = function(){
    	$http.post(hostPath+'/updatePwdByCode',$.param({
    		userName:$scope.user.userName,
    		passWord:$scope.user.passWord,
    		code:$scope.user.code
    	})).success(function(result){
    		if(result.status == 1){
    			alert('修改成功');
    			location.href = hostPath+'/front/login';
    		}else{
    			alert('修改失败,请检查验证码是否正确');
    		}
    	}).error(function(){
    		console.error('请求失败');
    	})
    }

}])