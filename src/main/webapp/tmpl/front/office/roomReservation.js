/**
 * 会室预约
* roomReservationModule Module
*
* Description
*/
//今天
var currDate = new Date();
var yaer = currDate.getFullYear();
var month = currDate.getMonth()+1;
var day = currDate.getDate();
currDate = yaer + "-"+(month <10? "0"+month : month)+"-"+(day <10? "0"+day : day);
//明天
var date2 = new Date();
date2 = date2.valueOf()
date2 = date2 + 1 * 24 * 60 * 60 * 1000
date2 = new Date(date2)
yaer = date2.getFullYear();
month = date2.getMonth()+1;
day = date2.getDate();
var date2 = yaer + "-"+(month <10? "0"+month : month)+"-"+(day <10? "0"+day : day);

//后天
var date3 = new Date();
date3 = date3.valueOf()
date3 = date3 + 2 * 24 * 60 * 60 * 1000
date3 = new Date(date3)
yaer = date3.getFullYear();
month = date3.getMonth()+1;
day = date3.getDate();
var date3 = yaer + "-"+(month <10? "0"+month : month)+"-"+(day <10? "0"+day : day);

function checkDecimal(num){
	 var r= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
	 return r.test(num);
}

var timeArr = [
    {"id":6,"name":"time6"},{"id":7,"name":"time7"},
    {"id":8,"name":"time8"},{"id":9,"name":"time9"},
    {"id":10,"name":"time10"},{"id":11,"name":"time11"},
    {"id":12,"name":"time12"},{"id":13,"name":"time13"},
    {"id":14,"name":"time14"},{"id":15,"name":"time15"},
    {"id":16,"name":"time16"},{"id":17,"name":"time17"},
    {"id":18,"name":"time18"},{"id":19,"name":"time19"},
    {"id":20,"name":"time20"},{"id":21,"name":"time21"},
    {"id":22,"name":"time22"},{"id":23,"name":"time23"}
];


angular.module('roomReservationModule', []).controller('attestationCtrl',function($rootScope,$scope,$http){
	$scope.submitAttestation = function(){
		if($rootScope.user.id == null || $rootScope.user.id == ''){
			alert("请确认是否登录");
			return;
		}
		$scope.attestation.applyUserId = $rootScope.user.id;
		$scope.attestation.schoolId = $rootScope.schoolInfo.schoolId;
		$http.post(hostPath+'/office/submitAttestation',$.param($scope.attestation))
		.success(function(result){
			if(result.status == 1){
				alert('提交成功!请等待审核');
				history.go(-1);
			}else{
				alert("提交失败或者已经提交过认证! 请等待审核");
			}
		}).error(function(){
			console.error('request error!');
		});
	}
}).controller('roomCtrl', function($rootScope,$scope,$http,$stateParams,$timeout){
	//点击"预约"时，显示对话框
	$scope.isShow = false;
	
	//确定预约按钮控制
	$scope.reBtn = false;
	
	//当前选择的会议室
	$scope.selectRoom = null;
	
	//今天
	var day1 = new Object();  
    day1.day=currDate;  
    day1.state=false;  
    //明天
    var day2 = new Object();  
    day2.day=date2;  
    day2.state=false;  
    //后天
    var day3 = new Object();  
    day3.day=date3;  
    day3.state=false;  
    
    
	$scope.dayList = [day1,day2,day3];
	
	$scope.demoDateState = false;
	
	//设置菜单样式变化
	$scope.setMenu = function(index){
		$scope.demoDate = null;
		$scope.demoDateState = false;
		for (var i = $scope.dayList.length - 1; i >= 0; i--) {
			$scope.dayList[i].state = false;
		}
		$scope.dayList[index].state = true;
		$scope.selectDate = $scope.dayList[index].day;//默认选择今天
	}
	
	$scope.setMenu(0);
	
	//显示时间对话框
	$scope.showTimeDialog = function(){
		$scope.isShow = true;
	}
	//隐藏时间对话框
	$scope.hideTimeDialog = function(){
		$scope.isShow = false;
	}
	//切换时间
	$scope.changeDate = function(index){
		$scope.hideTimeDialog();
		$scope.setMenu(index);
		
		//获取数据
		$scope.roomList = [];
		 $scope.params = {
	        schoolId:schoolId, 
	        selectDate:$scope.selectDate
	    };
		$scope.getRoomListBySchoolId();
	}
	
	var schoolId = $stateParams.schoolId;
//	console.log("--currdate--"+$scope.selectDate);
//	console.log("--------schoolId-------"+schoolId);
	//分页的配置文件
    $scope.params = {
        schoolId:schoolId, 
        selectDate:$scope.selectDate
    };


    $scope.roomList = [];

	$scope.getRoomListBySchoolId = function(){
		$http.post(hostPath+'/office/getRoomReservationList',$.param($scope.params)).success(function(result){
			for (var i = 0; i < result.data.length; i++) {
				result.data[i].isDetail = false;
				
				result.data[i].time6 = false;//6-7
				result.data[i].time7 = false;//7-8
				result.data[i].time8 = false;//8-9
				result.data[i].time9 = false;//9-10
				result.data[i].time10 = false;//10-11
				result.data[i].time11 = false;//11-12
				result.data[i].time12 = false;//12-13
				result.data[i].time13 = false;//13-14
				result.data[i].time14 = false;//14-15
				result.data[i].time15 = false;//15-16
				result.data[i].time16 = false;//16-17
				result.data[i].time17 = false;//17-18
				result.data[i].time18 = false;//18-19
				result.data[i].time19 = false;//19-20
				result.data[i].time20 = false;//20-21
				result.data[i].time21 = false;//21-22
				result.data[i].time22 = false;//22-23
				result.data[i].time23 = false;//23-24
				if(result.data[i].list != null && result.data[i].list.length > 0){
					//处理时间轴
					var rlist = result.data[i].list;
					for (var j = 0; j < rlist.length; j++) {
						try{
							var startTime = rlist[j].startTime.substring(11,rlist[j].startTime.length);
							var endTime = rlist[j].endTime.substring(11,rlist[j].endTime.length);
							
							//var n1 = Number(startTime.replace(":","."));
							var sNum = parseInt(startTime.substring(0,2));
							var n2 = Number(endTime.replace(":","."));
							var eNum = parseInt(endTime.substring(0,2));
							
							var num = eNum - sNum;
							//console.log("num--1--"+num);
							var isDecimal = checkDecimal(n2);
							//console.log("isDecimal----"+isDecimal);
							//num = parseInt(num);
							//console.log(""+result.data[i][time7]);
							var tNum = sNum;
							//console.log("num------"+num+"snum------"+tNum);
							if(num <= 1){
								tNum = sNum + num;
								if(isDecimal){
									tNum = sNum + num + 1;
								}
							}else{
								tNum = sNum + num;
								if(isDecimal){
									tNum = sNum + num + 1;
								}
							}
							
							//console.log("sNum-------"+sNum+"----tnum----"+tNum);
							for(var m=0;m<timeArr.length;m++){
								if(timeArr[m].id >= sNum && timeArr[m].id < tNum){
									result.data[i][timeArr[m].name] = true;
								}
							}
							
						}catch(e){
							console.log("时间解析错误");
						}
					}
					result.data[i].list = [];
				}
				$scope.roomList.push(result.data[i]);
			}
//			console.log($scope.params);
//			console.log($scope.roomList);
		}).error(function(){
			console.error('request error!');
		});
	}
	$scope.getRoomListBySchoolId();
	
	
	//显示详情与否
	$scope.showDetail = function(room,$event){
		if(room.isDetail == false){
			room.isDetail=true;
			$event.target.value="详情 -";
			$scope.getDetail(room);
		}else{
			room.isDetail=false;
			$event.target.value="详情 +";
		}
	}
	
	$scope.getDetail = function(room){
		$http.post(hostPath+'/office/getReservationDetail',$.param({roomId:room.id,selectDate:$scope.selectDate}))
		.success(function(result){
			if(result.status == 1){
				if(result.data != null){
//					console.log(result.data);
					room.list = result.data;
//					$("#room_re_"+room.id).val("详情 -");
					//时间轴
					room.time6 = false;//6-7
					room.time7 = false;//7-8
					room.time8 = false;//8-9
					room.time9 = false;//9-10
					room.time10 = false;//10-11
					room.time11 = false;//11-12
					room.time12 = false;//12-13
					room.time13 = false;//13-14
					room.time14 = false;//14-15
					room.time15 = false;//15-16
					room.time16 = false;//16-17
					room.time17 = false;//17-18
					room.time18 = false;//18-19
					room.time19 = false;//19-20
					room.time20 = false;//20-21
					room.time21 = false;//21-22
					room.time22 = false;//22-23
					room.time23 = false;//23-24
					for (var i = 0; i < room.list.length; i++) {
						try{
							var startTime = room.list[i].startTime.substring(11,room.list[i].startTime.length);
							var endTime =room.list[i].endTime.substring(11,room.list[i].endTime.length);
							
							var sNum = parseInt(startTime.substring(0,2));
							var n2 = Number(endTime.replace(":","."));
							var eNum = parseInt(endTime.substring(0,2));
							
							var num = eNum - sNum;
							var isDecimal = checkDecimal(n2);
							var tNum = sNum;
							if(num <= 1){
								tNum = sNum + num;
								if(isDecimal){
									tNum = sNum + num + 1;
								}
							}else{
								tNum = sNum + num;
								if(isDecimal){
									tNum = sNum + num + 1;
								}
							}
							for(var m=0;m<timeArr.length;m++){
								if(timeArr[m].id >= sNum && timeArr[m].id < tNum){
									room[timeArr[m].name] = true;
								}
							}
							
						}catch(e){
							console.log("时间解析错误");
						}
					}
				}
			}else{
				console.log(result.msg);
			}
		}).error(function(){
			console.error('request error!');
		});
	}
	
	//显示增加预约的输入框
	$scope.showInput = function(room,$event){
		if($scope.selectRoom != null){
			if($scope.selectRoom.id == room.id){
				if($scope.isShow == false){
					$scope.selectRoom = room;
					 $scope.showTimeDialog();
				    var x = $event.target.offsetLeft;
				    var y = $event.target.offsetTop;
				    var box = $("#timeDialog").show(50);
				    box.css('top',y + 15);
				    box.css('left',x);
					   // $event.target.stopPropagation();
				}else{
					$scope.hideTimeDialog();
				}
			}else{
				//当预约对话框已经显示时，如果点击其他会室
				if($scope.isShow == false){
					$scope.selectRoom = room;
					 $scope.showTimeDialog();
				    var x = $event.target.offsetLeft;
				    var y = $event.target.offsetTop;
				    var box = $("#timeDialog").show(50);
				    box.css('top',y + 15);
				    box.css('left',x);
				   
					   // $event.target.stopPropagation();
				}else if($scope.isShow == true){
					$scope.selectRoom = room;
					$scope.showTimeDialog();
				    var x = $event.target.offsetLeft;
				    var y = $event.target.offsetTop;
				    var box = $("#timeDialog").show(50);
				    box.css('top',y + 15);
				    box.css('left',x);
				}
			}
		}else{
			if($scope.isShow == false){
				$scope.selectRoom = room;
				$scope.showTimeDialog();
			    var x = $event.target.offsetLeft;
			    var y = $event.target.offsetTop;
			    var box = $("#timeDialog").show(50);
			    box.css('top',y + 15);
			    box.css('left',x);
				   // $event.target.stopPropagation();
			}else{
				$scope.selectRoom = room;
				$scope.showTimeDialog();
			    var x = $event.target.offsetLeft;
			    var y = $event.target.offsetTop;
			    var box = $("#timeDialog").show(50);
			    box.css('top',y + 15);
			    box.css('left',x);
			}
		}
	}
	
	//添加会室预约记录
	$scope.addReservation = function(){
//		console.log("当前选择的日期"+$scope.selectDate);
		if($scope.selectRoom == null){
			console.log("room is null");
			return;
		}
		if($scope.selectDate == null || $scope.selectDate == ''){
			alert("请选择会议日期");
			return;
		}
		var stime = $("#demo_stime").val();
		if(stime == ''){
			alert("请选择会议开始时间");
			return;
		}
		var etime = $("#demo_etime").val();
		if(stime == ''){
			alert("请选择会议结束时间点");
			return;
		}
		if(etime <= stime){
			alert("结束时间不能小于或等于开始时间");
			return;
		}
		stime = $scope.selectDate + " "+stime+":00";
		etime = $scope.selectDate + " "+etime+":00";
		var params = {roomId:$scope.selectRoom.id,startTime:stime,endTime:etime};
		$scope.reBtn = true;//设置为不可点击
		$http.post(hostPath+'/office/addReservation',$.param(params))
			.success(function(result){
				$scope.reBtn = false;
				if(result.status ==1){
					$scope.hideTimeDialog();
					$scope.clearTime();
					$scope.getDetail($scope.selectRoom);
				}else{
					alert(result.msg);
				}
			}).error(function(){
				console.error('request error!');
			});
	}
	
	//取消 ---- 添加会室预约记录
	$scope.cancelReservation = function(){
		$scope.selectRoom = null;
		$scope.clearTime();
		$scope.hideTimeDialog();
	}
	$scope.clearTime = function(){
		$("#demo_stime").val('');
		$("#demo_etime").val('');
	}
	
	
	//删除会室预约记录(只能删除自己的记录)
	$scope.deleteModal = function(room,re){
		$scope.reservationRoom = null;
		$scope.reservation = null;
		$scope.reservationRoom = room;
		$scope.reservation = re;
		$("#warnModel").css("top","35%");
		$("#warnModel").modal({backdrop: 'static', keyboard: false});
	}
	
	$scope.deleteRoomReservation = function(){
		$("#warnModel").modal('hide');
		$http.post(hostPath+'/office/deleteRoomReservation',$.param({id:$scope.reservation.id}))
		.success(function(result){
			if(result.status == 1){
				$scope.getDetail($scope.reservationRoom);
			}else{
				alert(result.msg);
			}
		}).error(function(){
			console.error('request error!');
		});
	}
	
	//获取时间插件的时间value
	$scope.getDemoDate = function(demoDate){
		if(demoDate != ''){
			$scope.demoDateState = true;
			$scope.selectDate = demoDate.replace(/\//g,"-");
		}else{
			$scope.selectDate = "";
		}
		for (var i = $scope.dayList.length - 1; i >= 0; i--) {
			$scope.dayList[i].state = false;
		}
		//获取数据
		$scope.roomList = [];
		$scope.params = {
	        schoolId:schoolId, 
	        selectDate:$scope.selectDate
	    };
		$scope.getRoomListBySchoolId();
	}
}).filter("reserverTimeFilter",function(){
    return function(str){
        var out = str;
        if(str != null && str != ''){
        	out = str.substring(str.indexOf(" ")+1,str.length);
        }
        return out;
    }
});


