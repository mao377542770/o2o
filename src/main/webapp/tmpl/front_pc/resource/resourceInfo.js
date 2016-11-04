/**
* resourceInfoModule Module
*
* Description
*/
angular.module('resourceInfoModule', []).controller('resourceInfoCtrl', ['$http','$scope','$stateParams',
 function($http,$scope,$stateParams){
 	var resourceId = $stateParams.id;
	$scope.getResourceInfoById = function(){
		$http.post(hostPath+'/resource/getResourceInfoById',$.param({
			id:resourceId
		})).success(function(result){
			if(result.status == 1){
				$scope.resourceInfo = result.data;
			}
		}).error(function(){
			console.error('请求失败!');
		})
	}
	$scope.getResourceInfoById();


	$scope.downResource = function(id){
		$scope.resourceInfo.downloadTotal++;
		location.href = hostPath + "/resource/downResource?id="+id;
	}
}])