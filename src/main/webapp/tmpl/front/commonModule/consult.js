/**
* consultModule Module   咨询模块
*
* Description
*/
angular.module('consultModule', []).controller('consultCtrl',function($rootScope,$scope,$http,$stateParams,$timeout,$state){
	$scope.state = true;    //用于区分是全部  还是  我的

	$scope.deptId = $stateParams.deptId;

	//分页的配置文件
    $scope.consultPage = {
    	deptId:$stateParams.deptId,
    	schoolId:$rootScope.schoolInfo.schoolId,
        currentPage: 0,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 9 //每页展示多少条数据
    };


	$scope.consultList = [];
	$scope.getData = function(){
		if($scope.state){
			$http.post(hostPath+"/library/getDeptConsult",$.param($scope.consultPage)).
	        success(function(result){
	        	if(result.status == 1){
	        		for (var i = 0; i < result.data.length; i++) {
		            	$scope.consultList.push(result.data[i]);
		            }
		            if(result.msg != null){
		                $scope.consultPage.totalItems = result.msg;
		            }
		            $scope.consultPage.currentPage ++ ;
	        	}
	            
	            var timer = $timeout(function(){
	            	$scope.reddit.busy = false;
	            	$timeout.cancel(timer);
	            },100);
	        }).error(function(){
	            console.error('出错了!');
	        })
		}
	}

	$scope.myList = [];
	$scope.getMyConsult = function(){
		$scope.myList = [];
		$scope.state = false;
		$http.post(hostPath+"/library/stuGetConsult",$.param({userId:$rootScope.user.id})).
	        success(function(result){
	        	if(result.status == 1){
	        		for (var i = 0; i < result.data.length; i++) {
		            	$scope.myList.push(result.data[i]);
		            }
		            if(result.msg != null){
		                $scope.paginationConf.totalItems = result.msg;
		            }
		            $scope.consultPage.currentPage ++ ;
	        	}
	            
	            var timer = $timeout(function(){
	            	$scope.reddit.busy = false;
	            	$timeout.cancel(timer);
	            },100);
	        }).error(function(){
	            console.error('出错了!');
	        })
	}

	//分页标签参数
	$scope.reddit = {
	    busy:false,   //用来控制是否正在加载
	    nextPage:function(){  //翻页方法
	    	if(this.busy){
	    		return;
	    	}
	    	this.busy = true;
	    	$scope.getData();
	    }
	}	
	
	//先判断是否登录，再跳转到提交咨询页面
	$scope.toConsult = function(){
		if($rootScope.user.id == null || $rootScope.user.id == ''){
			alert("请确认是否登录");
			return;
		}else{
			$state.go('toConsult',{deptId:$rootScope.user.deptId});
		}	
	}
}).controller('consultInfoCtrl', ['$scope','$http','$stateParams', function($scope,$http,$stateParams){
	//获得详细信息
	$scope.getConsultInfo = function(){
		$http.post(hostPath + "/library/getConsultInfo", $.param({id:$stateParams.id})).
		success(function(result) {
			$scope.consult = result.data;
		}).error(function() {
			console.error('出错了!');
		})
	}

	$scope.getConsultInfo();
}]).controller('toConsultCtrl', ['$scope','$http','$stateParams','$rootScope', function($scope,$http,$stateParams,$rootScope){

	$scope.consult = {
		askUser:$rootScope.user.id,
		deptId:$stateParams.deptId,
		schoolId:$rootScope.schoolInfo.schoolId
	}

	$scope.submit = function(){
		if($rootScope.user.id == null || $rootScope.user.id == ''){
			alert("请确认是否登录");
			return;
		}
		$http.post(hostPath + "/library/saveConsult", $.param($scope.consult)).
		success(function(result) {
			if(result.status == 1){
				alert('提交成功');
				window.history.back(-1);
			}else{
				alert('提交失败');
			}			
		}).error(function() {
			console.error('出错了!');
		})
	}
}])

