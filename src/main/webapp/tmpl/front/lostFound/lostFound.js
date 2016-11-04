/**
 * 失物招领
* lostFoundModule Module
*
* Description
*/

angular.module('lostFoundModule', []).controller('lostFoundCtrl', function($scope,$http,$stateParams,$timeout){
	$scope.state = {
		activity:true,
		vote:false
	}

	$scope.changeState = function(num){
		if(num == 1){
			$scope.state.activity = true;
			$scope.state.vote = false;
		}else{
			$scope.state.activity = false;
			$scope.state.vote = true;
		}
	}
	
	
	var schoolId = $stateParams.schoolId;
//	console.log("--------schoolId-------"+schoolId);
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 5, //每页展示多少条数据
        schoolId:schoolId
    };


    $scope.lostFoundList = [];

	$scope.getlostFoundListBySchoolId = function(){
		$http.post(hostPath+'/lostFound/getFrontLostFoundList',$.param($scope.paginationConf)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				$scope.lostFoundList.push(result.data[i]);
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
	    	$scope.getlostFoundListBySchoolId();
	    }
	}
	
	 $scope.updateSelection = function($event){ 
	    var checkbox = $event.target; 
	    var action = (checkbox.checked?'add':'remove'); 
	    if(action == 'remove'){
	    	$scope.paginationConf = {
		            currentPage: 1,  //当前页码
		            totalItems: 0,  //总条数
		            itemsPerPage: 5, //每页展示多少条数据
		            schoolId:schoolId
		        };
	    }else{
	    	$scope.paginationConf = {
		            currentPage: 1,  //当前页码
		            totalItems: 0,  //总条数
		            itemsPerPage: 5, //每页展示多少条数据
		            schoolId:schoolId,
		            state:0
		        };
	    }
	    $scope.lostFoundList = [];
	    $scope.getlostFoundListBySchoolId();
	  } 
	
});