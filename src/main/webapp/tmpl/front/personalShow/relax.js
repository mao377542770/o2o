/**
* relaxMomentModule Module
*
*/

/**
 * 轻松时刻
 */
angular.module('relaxMomentModule', ['infinite-scroll']).controller('relaxCtrl',function($scope,$http,$stateParams,$timeout){

	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 0,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 9 //每页展示多少条数据
    };


    $scope.relaxList = [];

	$scope.getRelaxList = function(){
		$http.post(hostPath+'/front/getRelaxList',$.param($scope.paginationConf)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				$scope.relaxList.push(result.data[i]);
			}
			if(result.msg != null){
                $scope.paginationConf.totalItems = result.msg;
            }
			$scope.paginationConf.currentPage ++ ;
            var timer = $timeout(function(){
            	$scope.reddit.busy = false;
            	$timeout.cancel(timer);
            },100);
		}).error(function(){
			console.error('request error!');
		});
	}
	
	//分页标签参数
	$scope.reddit = {
	    busy:false,   //用来控制是否正在加载
	    nextPage:function(){  //翻页方法
	    	if(this.busy){
	    		return;
	    	}
	    	this.busy = true;
	    	$scope.getRelaxList();
	    }
	}
}).controller('relaxInfoCtrl',function($scope,$http,$stateParams,$state){
	$scope.getRelaxInfo = function(){
		$http.post(hostPath+'/front/getRelaxInfo',$.param({relaxId:$stateParams.relaxId})).success(function(result){
			if(result.status == 1){
				$scope.relax = result.data;
				$scope.addRelaxViewCount();
			}
		}).error(function(){
			console.error('request error!');
		});
	}

	$scope.getRelaxInfo();
	
	//增加阅读次数
	$scope.addRelaxViewCount = function(){
		$http.post(hostPath+'/front/addRelaxView',$.param({relaxId:$stateParams.relaxId}))
			.success(function(result){
//				console.log("-----ViewCount-----");
			}).error(function(){
				console.error('request error!');
			});
	}

	$scope.back = function(){
		$state.go('^');
	}
});