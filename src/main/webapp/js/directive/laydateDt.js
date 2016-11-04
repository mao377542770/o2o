var mindate = null;
var laydateConf = {
    istime:true,
    format: 'YYYY-MM-DD hh:mm',
    choose:function(datas){
        mindate = datas;
    }
}
angular.module('laydateDt', []).directive('laydate', function($timeout){
        return {
            restrict: 'AE',
            scope:{
                date:"="
            },
            template: '<input placeholder="日期必填"  ng-model="date" class="form-control input-sm fudong-l magn" style="width:130px;" type="text"/>',
            replace: true,
            link: function($scope, iElm, iAttrs, controller) {
                iElm.blur(function(){
                    $scope.date = $(iElm).val();
                    $scope.$apply();
                    /*
                    var time = $timeout(function(){

                    },50)
                    */
                });
                iElm.click(function(){
                    if(mindate != null){
                        laydateConf.start = mindate;
                    }
                    laydate(laydateConf);
                })

            }
        };
    });