/**
* schoolActivityModule Module
*
* Description
*/

var currDate = new Date();
var yaer = currDate.getFullYear();
var month = currDate.getMonth()+1;
var day = currDate.getDate();
currDate = yaer + "-"+(month <10? "0"+month : month)+"-"+(day <10? "0"+day : day);

angular.module('schoolActivityModule', []).controller('schoolActivityCtrl', function($scope,$http,$stateParams,$timeout){
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


    $scope.activityList = [];

	$scope.getActivityListBySchoolId = function(){
		$http.post(hostPath+'/front/getActivityList',$.param($scope.paginationConf)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				result.data[i].isValid = null;
				if(result.data[i].type == 1 && result.data[i].endDate != null){
					if(result.data[i].endDate > currDate){
						//进行中
						result.data[i].isValid = 1;
					}else if(result.data[i].endDate < currDate){
						//已结束
						result.data[i].isValid = 0;
					}
				}
				$scope.activityList.push(result.data[i]);
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
	    	$scope.getActivityListBySchoolId();
	    }
	}
	
	 $scope.updateSelection = function($event){ 
	    var checkbox = $event.target; 
	    var action = (checkbox.checked?'add':'remove'); 
	    if(action == 'remove'){
	    	$scope.paginationConf = {
		            currentPage: 1,  //当前页码
		            totalItems: 0,  //总条数
		            itemsPerPage: 5 //每页展示多少条数据
		        };
	    }else{
	    	$scope.paginationConf = {
		            currentPage: 1,  //当前页码
		            totalItems: 0,  //总条数
		            itemsPerPage: 5, //每页展示多少条数据
		            schoolId:schoolId
		        };
	    }
	    $scope.activityList = [];
	    $scope.getActivityListBySchoolId();
	  } 
	
}).controller('activityInfoCtrl',function($rootScope,$scope,$http,$stateParams,$state){
	//是否可以投票
	$scope.isVote = false;
	//用户是否已经投票
	$scope.userVote = false;
	
	$scope.getActivityInfo = function(){
		$http.post(hostPath+'/front/getActivityInfo',$.param({activityId:$stateParams.activityId,userId:$rootScope.user.id})).success(function(result){
			if(result.status == 1){
				if(result.data.type == 1 && result.data.endDate != null){
					if(result.data.endDate > currDate){
						//进行中
						$scope.isVote = true;
					}else if(result.data.endDate < currDate){
						//已结束
						$scope.isVote = false;
					}
					var voteList = result.data.voteList;
					for(var i=0;i<voteList.length;i++){
						if(voteList[i].isVote){
							$scope.userVote = true;
						}
					}
				}
				$scope.activityInfo = result.data;
				if($scope.activityInfo != null){
					$scope.addActivityViewCount();
				}
			}
		}).error(function(){
			console.error('request error!');
		});
	}

	$scope.getActivityInfo();
	
	//增加阅读次数
	$scope.addActivityViewCount = function(){
		$http.post(hostPath+'/front/addActivityViewCount',$.param({activityId:$stateParams.activityId}))
			.success(function(result){
//				console.log("-----ViewCount-----");
			}).error(function(){
				console.error('request error!');
			});
	}
	
	//投票
	$scope.doVote = function(activityId,optionId){
		if($rootScope.user.id == null || $rootScope.user.id == ''){
			alert("请登录");
			return;
		}
		$http.post(hostPath+'/front/vote',$.param({activityId:activityId,optionId:optionId,voterId:$rootScope.user.id})).success(function(result){
			if(result.status == 1){
				$scope.getActivityInfo();
			}
		}).error(function(){
			console.error('request error!');
		});
	}

	$scope.back = function(){
		$state.go('schoolActivity', {  
		    id: '22'  
		}); 
	}
	
	//显示投票项内容
	$scope.showOptionDetail = function(info){
		$("#vTitle").html(info.title);
		$("#vContent").html(info.optionCount);
		$('#voteDetailModel').modal('show');
	}
}).filter("checkdateFilter",function(){
    return function(dateStr){
        var out = "";
        if(currDate > dateStr){
        	out = "已结束";
        }else if(currDate <= dateStr){
        	out = "进行中";
        }
        return out;
    }
}).filter("voteCountFilter",function(){
    return function(count){
        var out = "0";
        if(count != null && count != ''){
        	out = count;
        }
        return out;
    }
});