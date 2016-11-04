/**
 * fullInfoCtrl Module
 *
 * Description
 */
angular.module('fullInfoModule', []).controller('fullInfoCtrl'
	, ['$scope', '$http', '$stateParams', function($scope, $http,$stateParams) {
	$scope.typeList = [];
	$scope.resource = {
		id:$stateParams.resourceId
	}
	//获得文档类型
	$scope.getTypeList = function() {
		$http.post(hostPath + '/resource/getTypeList').success(function(result) {
			$scope.typeList = result.data;
		}).error(function() {
			console.error('请求出错!');
		})
	}
	$scope.getTypeList();

	$scope.save = function() {
		if($scope.resource.type == ''){
			$scope.resource.type == null;
		}
		$http.post(hostPath + '/resource/updateResource',$.param($scope.resource)).success(function(result) {
			if(result.status == 1){
				location.href=hostPath+"/resource#/finish";
			}else{
				alert(result.msg);
			}
		}).error(function() {
			console.error('请求出错!');
		})
	}
}])