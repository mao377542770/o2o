/**
 * 定义一个只能输入小数点,数字的input  
 */
angular.module('numInputDt', []).directive('num',function(){  //主意驼峰命名指令 如numInput,需要用 num-input来调用指令
    // Runs during compile
    return {
        restrict: 'AE',
        scope: {num:"="}, 
        template: '<input type="text" ng-model="num"/>',
        replace: true,
        link: function($scope, iElm, iAttrs, controller) {
        	iElm.keyup(function(){
    			$(this).val($(this).val().replace(/[^\?\d.]/g,''));
    			$scope.num = $(this).val();
        	});
        	
        }
    };
});