/**
*  Module
*
* Description
*/
angular.module('comm', []).directive('search',function(){ //该指令主要是为了执行回车键搜索
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
})