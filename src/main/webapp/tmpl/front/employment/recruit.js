angular.module('recruitModule', ['infinite-scroll']).controller('recruitCtrl',function($scope,$http,$stateParams,$timeout){

	var schoolId = $stateParams.schoolId;
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 0,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 9, //每页展示多少条数据
        schoolId:schoolId
    };

    $scope.recruitList = [];
	$scope.getNewsListBySchoolId = function(){
		$http.post(hostPath+'/employment/getRecruitList',$.param($scope.paginationConf)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				$scope.recruitList.push(result.data[i]);
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
	    	$scope.getNewsListBySchoolId();
	    }
	}
}).controller('recruitInfoCtrl',function($scope,$http,$stateParams,$state){
	$scope.getNewsInfo = function(){
		$http.post(hostPath+'/employment/getRecruitInfo',$.param({newsId:$stateParams.recruitId})).success(function(result){
			if(result.status == 1){
				$scope.news = result.data;
				$scope.addNewsView();
			}
		}).error(function(){
			console.error('request error!');
		});
	}

	$scope.getNewsInfo();
	
	
	//阅读次数加
	$scope.addNewsView = function(){
		$http.post(hostPath+'/employment/RecruitView',$.param({"id":$stateParams.recruitId})).success(function(result){
			if(result.status == 1){
			}
		}).error(function(){
			console.error('request error!');
		});
	}

	$scope.back = function(){
		$state.go('^');
	}
}).filter("imgSrcFilter",function(){ //图片路径过滤器
	return function(src){
		if(src=="" || src ==null || src == undefined){
			src=hostPath+"/images/tm-2.png";
		}else{
			src = hostPath+"/"+src;
		}
		return src;
	}
})