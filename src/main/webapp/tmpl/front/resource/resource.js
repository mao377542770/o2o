/**
 * resourceModule Module
 *
 * Description
 */
angular.module('resourceModule', []).controller('resourceCtrl', ['$scope', '$http','$timeout',
	function($scope, $http,$timeout) {

		//获得资源所有类型
		$scope.getResourceType = function() {
			$http.post(hostPath+'/resource/getTypeList').success(function(result){
				$scope.resourceTypeList = result.data;
			}).error(function(){
				console.error('请求出错!');
			})
		}
		$scope.getResourceType();

		$scope.selectState = true; //大标签样式
		$scope.typeName = '分类';
		$scope.selectTheType = function(resourceType){
			$scope.typeState = false;  //滑动标签
			$scope.selectState = false;
			$scope.typeName = resourceType.typeName;
			$scope.pageInfo.type = resourceType.id;
			$scope.pageInfo.keyWord = null;
			$scope.resetPage();
			$scope.getResourceList();
		}

		//获得资源列表
		$scope.getNewResource = function() {
			$scope.selectState = true;
			$scope.typeState = false;
			$scope.typeName = '分类';
			$scope.pageInfo.type = null;
			$scope.pageInfo.keyWord = null;
			$scope.resetPage();
			$scope.getResourceList();
		}

		$scope.toSearch = function(){
			if($scope.keyWord == ''){
				return;
			}
			$scope.selectState = true;
			$scope.typeState = false;
			$scope.typeName = '分类';
			$scope.pageInfo.type = null;
			$scope.pageInfo.keyWord = $scope.keyWord;
			console.log($scope.pageInfo);
			$scope.resetPage();
			$scope.getResourceList();
		}

		$scope.resetPage = function(){
			$scope.pageInfo.currentPage = 0;
			$scope.resourceList = [];
		}

		$scope.pageInfo = {
			currentPage: 0, //当前页码
			totalItems: 0, //总条数
			itemsPerPage: 10, //每页展示多少条数据
			type: null,
			keyWord:null
		}

		$scope.resourceList = [];
		//获得文献列表
		$scope.getResourceList = function() {
			$http.post(hostPath + '/resource/getResourceList', $.param($scope.pageInfo)).
			success(function(result) {
				for (var i = 0; i < result.data.length; i++) {
					result.data[i].showInfo = false;
					$scope.resourceList.push(result.data[i]);
				}
				if (result.msg != null) {
					$scope.pageInfo.totalItems = result.msg;
				}
				$scope.pageInfo.currentPage++;
				var timer = $timeout(function() {
					$scope.reddit.busy = false;
					$timeout.cancel(timer);
				}, 100);
			}).error(function() {

			});
		}

		//分页标签参数
		$scope.reddit = {
			busy: false, //用来控制是否正在加载
			nextPage: function() { //翻页方法
				if (this.busy) {
					return;
				}
				this.busy = true;
				$scope.getResourceList();
			}
		}

		$scope.changeState = function(resource,$event){
			resource.showInfo = !resource.showInfo;
			$event.stopPropagation();
		}

		//search动画效果
		$scope.showSearch = function($event){
			$event.stopPropagation();
			$scope.searchState = true;
		}

		$scope.closeSearch = function($event){
			$event.stopPropagation();
			$scope.searchState = false;
			$scope.typeState = false;  //滑动标签
		}
	}
]).directive('stop',function(){ //阻止点击事件冒泡
	return {
		restrict: 'A',
		replace: false,
		link: function($scope, iElm, iAttrs, controller) {
			iElm.click(function(event){
				event.stopPropagation();
			});
		}
	};
}).directive('search',function(){ //该指令主要是为了执行回车键搜索
    return {
        scope: {
            'search':'&'
        }, 
        restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
        template: '<input ng-keyup="toSearch($event)"/>',
        replace: true,
        link: function($scope, iElm, iAttrs, controller) {
            $scope.toSearch = function(e){
                 var keycode = window.event?e.keyCode:e.which;
                if(keycode==13){
                    $scope.search();
                }
            }
        }
    };
}).controller('resourceInfoCtrl', ['$http','$scope','$stateParams',
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