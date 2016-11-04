/**
* literature Module   文献资料
*
* Description
*/
angular.module('literatureModule', []).controller('literatureCtrl',function($scope,$http,$stateParams,$timeout,urlFilter){
	
	//获得所有文献类型
	$scope.getAllType = function(){
		$http.post(hostPath + '/library/getAllType',$.param({
            'schoolId':$stateParams.schoolId
        })).success(function(result){
            if(result.status == 1){
                $scope.literaturetypeList = result.data;
                $scope.selectType(0);
            }
        }).error(function(){
            console.error('请求出错!');
        })
	}

	$scope.getAllType();

	//分页信息
	$scope.searchConf = {
        "currentPage":0,
        "itemsPerPage":7,
        "totalItems":0,
        "schoolId":$stateParams.schoolId,
        "typeId":null
	};


	//获得文献列表
	$scope.getAllLiterature = function(){
		if($scope.searchConf.typeId == null){
			return;
		}
		$http.post(hostPath+'/library/getAllLiterature',$.param($scope.searchConf)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				result.data[i].showInfo = false;
				$scope.literatureList.push(result.data[i]);
			}
			if(result.msg != null){
                $scope.searchConf.totalItems = result.msg;
            }
			$scope.searchConf.currentPage ++ ;
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
	    	$scope.getAllLiterature();
	    }
	}

	//选择类型
	$scope.selectType = function(index){
		$scope.setMenu(index);
		if($scope.searchConf.typeId == $scope.literaturetypeList[index].id){
			return;
		}
		//初始化分页信息
		$scope.searchConf = {
	        "currentPage":0,
	        "itemsPerPage":7,
	        "totalItems":0,
	        "schoolId":$stateParams.schoolId,
	        "typeId":null
		}
		$scope.searchConf.typeId = $scope.literaturetypeList[index].id;
		$scope.literatureList = [];
		//获得该类型下的所有资源
		$scope.getAllLiterature();
	}


	//设置菜单样式变化
	$scope.setMenu = function(index){
		if($scope.literaturetypeList != null && $scope.literaturetypeList.length > 0){
			for (var i = $scope.literaturetypeList.length - 1; i >= 0; i--) {
				$scope.literaturetypeList[i].state = false;
			}
			$scope.literaturetypeList[index].state = true;
		}
	}

	$scope.changeState = function(literature,$event){
		literature.showInfo = !literature.showInfo;
		$event.stopPropagation();
	}

	$scope.toUrl = function(literature){
		var url = urlFilter(literature.literatureUrl);
		window.open(url);
	}
}).factory("urlFilter",function(){
	//url 过滤器
	return function(url){
		var strStart = url.indexOf("https://");
		var strStart1 = url.indexOf("http://");
		if(strStart ==0 || strStart1==0){
			return url;
		}else{
			url = "http://"+url;
		}
		return url;
	}
})