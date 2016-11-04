/**
* policyModule Module
*
* Description
*/
angular.module('policyModule', [])
.controller('policyCtrl',function($scope,$http,$state,$stateParams){
	$scope.schoolId = $stateParams.schoolId;
	if($scope.schoolId != null || $scope.schoolId != undefined){
		$state.go('policy.introduction');
	}

	//获得图书馆入馆信息
	$scope.getLibraryInfo =function(){
		$http.post(hostPath + '/library/getPresention',$.param({
            'deptId':1,
            'schoolId':$scope.schoolId
        })).success(function(result){
            if(result.status == 1){
                $scope.librarynote = result.data;
            }
        }).error(function(){
            console.error('请求出错!');
        })
	}
	$scope.getLibraryInfo();

	$scope.getRules = function(){
		$http.post(hostPath + '/library/getRules',$.param({
            'deptId':1,
            'schoolId':$scope.schoolId
        })).success(function(result){
            if(result.status == 1){
                $scope.rules = result.data;
            }
        }).error(function(){
            console.error('请求出错!');
        })
	}
}).controller('ruleInfoCtrl', ['$scope','$http','$stateParams', function($scope,$http,$stateParams){
    $scope.getRuleById = function(ruleId){
        $http.post(hostPath + '/library/getRuleById',$.param({
            'id':ruleId
        })).success(function(result){
            if(result.status == 1){
                $scope.rule =result.data;
            }
        }).error(function(){
            console.error('请求出错!');
        })
    }

    $scope.getRuleById( $stateParams.ruleId);
}])