/**
* affectMomentModule Module
*
* 感动瞬间
*/
angular.module('affectMomentModule', ['infinite-scroll']).controller('affectCtrl',function($scope,$http,$stateParams,$timeout){
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 0,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 9 //每页展示多少条数据
    };


    $scope.affectList = [];

	$scope.getAffectList = function(){
		$http.post(hostPath+'/front/getAffectList',$.param($scope.paginationConf)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				$scope.affectList.push(result.data[i]);
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
	    	$scope.getAffectList();
	    }
	}
}).controller('affectInfoCtrl',function($scope,$http,$stateParams,$state){
	$scope.getAffectInfo = function(){
		$http.post(hostPath+'/front/getAffectInfo',$.param({affectId:$stateParams.affectId})).success(function(result){
			if(result.status == 1){
				$scope.affect = result.data;
				$scope.addAffectViewCount();
			}
		}).error(function(){
			console.error('request error!');
		});
	}

	$scope.getAffectInfo();
	
	//增加阅读次数
	$scope.addAffectViewCount = function(){
		$http.post(hostPath+'/front/addAffectView',$.param({affectId:$stateParams.affectId}))
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

