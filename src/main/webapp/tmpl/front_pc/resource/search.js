/**
 * resourceSearchModule Module
 *
 * Description
 */
angular.module('resourceSearchModule', ['tm.pagination']).controller('searchListCtrl', ['$scope', '$http', '$stateParams',
	function($scope, $http, $stateParams) {
		$scope.resourceList = [];

		$scope.pageInfo = {
			currentPage: 1, //当前页码
			totalItems: 0, //总条数
			itemsPerPage: 10, //每页展示多少条数据
			type: ($stateParams.type == undefined ? null :  $stateParams.type),
			keyWord: $stateParams.searchKey,
			perPageOptions: [10, 15, 20], //可以选每一页展示多少
			onChange: function() {
				$scope.getNewResource();
			}
		}

		//获得最新的10条文档
		var loading = false; //防止重复提交
		$scope.getNewResource = function() {
			if (loading == true) {
				return;
			}
			loading = true;
			$http.post(hostPath + '/resource/getResourceList', $.param({
				currentPage: $scope.pageInfo.currentPage - 1, //当前页码
				totalItems: $scope.pageInfo.totalItems, //总条数
				itemsPerPage: $scope.pageInfo.itemsPerPage, //每页展示多少条数据
				type: $scope.pageInfo.type,
				keyWord: $scope.pageInfo.keyWord
			})).success(function(result) {
				if (result.status == 1) {
					$scope.resourceList = result.data;
					$scope.pageInfo.totalItems = result.msg;
					loading = false;
				}
			}).error(function() {
				console.error('请求失败');
				loading = false;
			})
		}
		$scope.getNewResource();
	}
])