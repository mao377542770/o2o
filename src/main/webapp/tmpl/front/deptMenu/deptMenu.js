/**
*  Module
*
* libraryMenuCtrlDescription  部门id为1
*/
angular.module('deptMenuModule', []).controller('libraryMenuCtrl',function($rootScope,$scope,$http,moduleService){
	var DEPTID = 1;  //这是该模块ID
	$scope.getDeptState = function(){
		var schoolDepts = $rootScope.schoolInfo.schoolDepts;
		if(schoolDepts != undefined && schoolDepts.length > 0){
			for (var i = 0; i < schoolDepts.length; i++) {
				if(schoolDepts[i] == DEPTID){
					//去除需要开通的按钮
					$scope.needOpened = true;
				}
			}
		}
	}
	//刷新该部门状态
	$scope.getDeptState();
	//moduleService.getModuleDept(DEPTID,$rootScope.schoolInfo.schoolId,$scope);
}).controller('jwcMenuCtrl',function($rootScope,$scope,$http,moduleService){
	var DEPTID = 2;  //这是该模块ID
	$scope.getDeptState = function(){
		var schoolDepts = $rootScope.schoolInfo.schoolDepts;
		for (var i = 0; i < schoolDepts.length; i++) {
			if(schoolDepts[i] == DEPTID){
				//去除需要开通的按钮
				$scope.needOpened = true;
			}
		}
	}
	//刷新该部门状态
	$scope.getDeptState();
	//moduleService.getModuleDept(DEPTID,$rootScope.schoolInfo.schoolId,$scope);
}).controller('employMenuCtrl',function($rootScope,$scope,$http,moduleService){
	var DEPTID = 3;  //这是该模块ID
	$scope.getDeptState = function(){
		var schoolDepts = $rootScope.schoolInfo.schoolDepts;
		for (var i = 0; i < schoolDepts.length; i++) {
			if(schoolDepts[i] == DEPTID){
				//去除需要开通的按钮
				$scope.needOpened = true;
			}
		}
	}
	//刷新该部门状态
	$scope.getDeptState();
	//moduleService.getModuleDept(DEPTID,$rootScope.schoolInfo.schoolId,$scope);
}).controller('workspaceMenuCtrl',function($rootScope,$scope,$http,moduleService,$state,$timeout){
	var DEPTID = 4;
	$scope.getDeptState = function(){
		var schoolDepts = $rootScope.schoolInfo.schoolDepts;
		for (var i = 0; i < schoolDepts.length; i++) {
			if(schoolDepts[i] == DEPTID){
				//去除需要开通的按钮
				$scope.needOpened = true;
			}
		}
	}
	//刷新该部门状态
	$scope.getDeptState();
	//moduleService.getModuleDept(DEPTID,$rootScope.schoolInfo.schoolId,$scope);
	$scope.goRoomReservation = function(){
		if(!$scope.needOpened){
			alert("请确认开通");
			return;
		}
		if($rootScope.user.id == null || $rootScope.user.id == ''){
			alert("请确认是否登录");
			return;
		}
		if($rootScope.user.schoolId != $rootScope.schoolInfo.schoolId){
			alert("请选择所属学校");
			return;
		}
		if($rootScope.user.uesrType == 1 || $rootScope.user.uesrType == 3){
			$state.go('roomReservation',{schoolId:$scope.schoolInfo.schoolId});
		}else{
			$http.post(hostPath + '/office/getUserAttestation').success(function(result) {
				if(result.status == 1){
					var u = result.data;
					if(u.userType == 3 || u.userType == 1){
						$state.go('roomReservation',{schoolId:$scope.schoolInfo.schoolId});
						return;
					}else{
						//弹出申请开通modal 框
						$http.post(hostPath + '/office/getAttestation').success(function(result) {
							if(result.status == 1){
								var att = result.data;
								if(att != null){
									//弹出申请开通modal 框
									$('#att_p').html('您的认证信息正在审核中,请耐心等待');
									$('#att_btn').hide();
									$('#applyModel').modal('show');
								}else{
									//弹出申请开通modal 框
									$('#applyModel').modal('show');
								}
							}
						}).error(function() {
							console.error('请求出错');
						});
					}
				}
			}).error(function() {
				console.error('请求出错');
			});
			
			
		}
	}

	$scope.toApply = function(){
		$('#applyModel').modal('hide');
		$timeout(function(){
			$state.go('attestation');
		},300)
		
	}


}).factory('moduleService', ['$http', function($http){    
	//该service用于获得开通模块列表
	return {
		getModuleDept: function(deptId,schoolId,scope) {
			$http.post(hostPath + '/getMyModule', $.param({
				schoolId: schoolId,
				deptId: deptId
			})).success(function(result) {
				scope.moduleList = result.data;
			}).error(function() {
				console.error('请求出错');
			})
		}
	};
}]).controller('subscribeCtrl',function($rootScope,$scope,$http,$state){
	//保存
	$scope.toSave = function() {
		if($rootScope.user.id == null || $rootScope.user.id == ''){
			alert("请您先登录");
			return;
		}
		$scope.subscribe.userId = $rootScope.user.id;
		$http.post(hostPath + '/front/saveSubscribe', 
			$.param($scope.subscribe)).success(function(result) {
			if (result.status == 1) {
				alert('提交成功,请耐心等待,我们将与您详细联系');
				$state.go('index');
			} else {
				alert(result.msg);
			}
		}).error(function() {
			console.error('请求出错');
		})
	}
})