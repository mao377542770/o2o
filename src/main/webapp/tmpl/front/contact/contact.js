/**
* contactCtrl Module
*
* Description
*/
angular.module('contactModule', []).controller('contactCtrl',function($scope,$http,$state,$rootScope){
	$scope.contact = {
		"name":null,
		"content":null,
		"telphone":null
	}
	$scope.setContactUs = function(contact){
		if($rootScope.user.id != null && $rootScope.user.id != ''){
			contact.userId = $rootScope.user.id;
		}
		$http.post(hostPath+'/setContactUs',$.param(contact)).success(function(result){
			if(result.status == 1){
				alert('提交成功,感谢您的建议');
				$state.go('index');
			}else{
				alert('提交失败!');
			}
		}).error(function(){
			console.error('请求失败!');
		})
	}
})