angular.module('officeModule', []).controller('bespokeCtr',function($rootScope,$scope,$http){
	$(function(){
		$("#navigation li").removeClass("active");
		$("#bespoke").addClass("active");
	})
	
	//获取本校的所有会室
	$scope.deleteState=0;//记录显示删除页面还是会室预约页面
	$scope.loadingOrder = false;//控制页面先进入预约界面
	$scope.getRooms = function(){
		$scope.deleteState=0;
		$("#bespokeMenu li").removeClass("active");
		$("#boardroom").addClass("active");
		$http.post(hostPath+"/office/getAllBoardroom",$.param({"schoolId":$rootScope.user.schoolId})).success(function(response){
			$scope.boardRooms = response.data;
			$scope.loadingOrder = true;//
		});
	}
	$scope.getRooms ();
	//增加会室窗口
	$scope.isEdit = false;
	$scope.addOrEdit=0;//修改还是增加  0 增加 1修改
	$scope.showAddWin = function(){
		$scope.isEdit = false;
		$scope.addOrEdit=0;
		$scope.roomName = "";
		$scope.roomAddress = "";
		$("#roomWin").modal({backdrop: 'static', keyboard: false});
	}
	//修改会室信息窗口
	$scope.editRoom = function(room){
		$scope.isEdit = false;
		$scope.addOrEdit=1;
		$scope.roomName = room.roomName;
		$scope.roomAddress = room.position;
		$scope.roomId = room.id;
		$("#roomWin").modal({backdrop: 'static', keyboard: false});
	}
	//会室信息
	$scope.showDetails = function(room){
		$scope.isEdit = true;
		$scope.roomName = room.roomName;
		$scope.roomAddress = room.position;
		$("#roomWin").modal({backdrop: 'static', keyboard: false});
	}
	//保存会室信息
	$scope.saveRoom = function(){
		var roomInfo = {
			"roomName":	$scope.roomName,
			"position":$scope.roomAddress,
			"schoolId":$rootScope.user.schoolId
		};
		if($scope.addOrEdit == 1){
			roomInfo.id = $scope.roomId;
		}
		if($scope.roomName==""||$scope.roomName==undefined||$scope.roomAddress==""||$scope.roomAddress==undefined){
			alert("请填写会室信息！");
			return;
		}
		$http.post(hostPath+"/office/manageRoom",$.param(roomInfo)).success(function(response){
			if(response.status == 1){
				$("#roomWin").modal("hide");
				$scope.getRooms ();
			}
		});
	}
	//记录是否显示会室预约详情
	$scope.showContent = [];
	$scope.lastIndex = 0;//记录上一次查看的会室
	//获取该会室的预约详情
	$scope.showBespkes = function(id){
		if($scope.lastIndex == id && $scope.showContent[id]){
			$scope.showContent[id] = false;
			$("#deatilsBtn"+id).html("详情+");
		}else{
			$scope.showContent[$scope.lastIndex] = false;
			$("#deatilsBtn"+$scope.lastIndex).html("详情+");
			if($scope.showContent[id]){
				$scope.showContent[id] = false;
				$("#deatilsBtn"+id).html("详情+");
			}else{
				$scope.showContent[id] = true;
				$("#deatilsBtn"+id).html("收起-");
			}
			$http.post(hostPath+"/office/getBespokeBack",$.param({"roomId":id})).success(function(response){
				$scope.lastIndex = id;
				$scope.bespokes = response.data;
				/*if($scope.bespokes.length<=0){
					$scope.showContent[id] = false;
					$("#deatilsBtn"+id).html("详情+");
				}*/
			});
		}
	}
	//删除会室
	$scope.deleteRom = function(room){
		if(confirm("确认删除"+room.roomName+"?")){
			$http.post(hostPath+"/office/deleteRomm",$.param({"roomId":room.id})).success(function(response){
				if(response.status == 1){
					for (var i = 0; i < $scope.boardRooms.length; i++) {
						if($scope.boardRooms[i].id==room.id){
							$scope.boardRooms.splice(i, 1);
						}
					}
				}else{
					alert("有关联数据不允许删除！");
				}
			});
		}
	}
	
	//会室预约窗口
	$scope.bespokeRoom= function(id){
		$scope.bespokeRoomId = id;
		$scope.isExist1 = false;
		$scope.isExist2 = false;
		$scope.isBespokeBtn=true;
		$("#startTime").val("");
		$("#endTime").val("");
		$("#bespokeRoom").modal({backdrop: 'static', keyboard: false});
	}
	//是否可以点击预约按钮
	$scope.isBespokeBtn=true;
	//判断开始时间
	$scope.judgeStartTime = function(){
		var startTime =new Date($("#startTime").val().replace(/\-/g, "\/"));
		if(startTime<new Date().getTime()){
			$scope.isExist1 = true;
			$scope.isBespokeBtn=false;
			$scope.judgeInfoSta = "当前时间不合法，请选择其他时间。";
			return;
		}
		$http.post(hostPath+"/office/judgeTime",$.param({"roomId":$scope.bespokeRoomId,"startDate":$("#startTime").val()})).success(function(response){
			if(response.status ==2){
				$scope.isExist1 = true;
				$scope.isBespokeBtn=false;
				$scope.judgeInfoSta = "该时间已经被占用，请选择其他时间。";
			}else{
				$scope.isExist1 = false;
				$scope.isBespokeBtn=true;
			}
		});
	}
	//判断结束时间	
	$scope.judgeEndTime=function(){
		var startTime =new Date($("#startTime").val().replace(/\-/g, "\/"));
		var endTime =new Date($("#endTime").val().replace(/\-/g, "\/"));
		if(endTime<startTime){  
			$scope.isExist2 = true;
			$scope.isBespokeBtn=false;
			$scope.judgeInfo = "会议结束时间不能小于开始时间。";
			return;
		}else{
			$scope.isExist2 = false;
			$scope.isBespokeBtn=true;
		}
		$http.post(hostPath+"/office/judgeTime",$.param({"roomId":$scope.bespokeRoomId,"startDate":$("#endTime").val()})).success(function(response){
			if(response.status ==2){
				$scope.isExist2 = true;
				$scope.isBespokeBtn=false;
				$scope.judgeInfo = "该时间已经被占用，请选择其他时间。";
			}else{
				$scope.isExist2 = false;
				$scope.isBespokeBtn=true;
			}
		});
	}
	
	//保存会室预约
	$scope.saveBespoke = function(){
		$scope.isBespokeBtn=false;
		/*var thisBespoke = {
			"startTime":new Date($("#startTime").val().replace(/-/g,'/')).getTime(),
			"endTime":new Date($("#endTime").val().replace(/-/g,'/')).getTime(),
			"nickName":$rootScope.user.name
		}*/
		//var createTime = new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()+ " " + new Date().getHours()+ ":" + new Date().getMinutes() +":"+ new Date().getSeconds();
		if($("#startTime").val()==""||$("#startTime").val()==null||$("#startTime").val()==undefined||$("#endTime").val()==""||$("#endTime").val()==null||$("#endTime").val()==undefined){
			alert("请填写开始时间和结束时间！");
			return;
		}
		//console.log(thisBespoke);
		$http.post(hostPath+"/office/bespokeRoom",$.param({"roomId":$scope.bespokeRoomId,"startDate":$("#startTime").val(),"endDate":$("#endTime").val(),"userId":$rootScope.user.id})).success(function(response){
			if(response.status == 1){
				$scope.isBespokeBtn=true;
				/*if($scope.bespokes!=null||$scope.bespokes!==undefined){
					$scope.bespokes.push(thisBespoke);
				}*/
				$("#bespokeRoom").modal('hide');
				if($scope.showContent[$scope.lastIndex]){
					$scope.showContent[$scope.lastIndex] = false;
				}
			}else{
				alert(response.msg);
			}
		})
	} 
	//删除会室预约记录
	$scope.deleteBespoke = function(bespoke){
		if(confirm("确认删除本条预约记录？")){
			$http.post(hostPath+"/office/changeDeleteState",$.param({"bsId":bespoke.id})).success(function(response){
				if(response.status == 1){
					for (var i = 0; i < $scope.bespokes.length; i++) {
						if($scope.bespokes[i].id==bespoke.id){
							$scope.bespokes.splice(i, 1);
						}
					}
				}
			})
		}
	}
	
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 10, //每页展示多少条数据
        pagesLength: 10,    
        perPageOptions: [10,15],   //可以选每一页展示多少
        onChange: function(){
        	if($scope.loadingOrder){
        		$scope.geDeleteList();
        	}
        }
    };
    
	//获取会室预约删除记录
	$scope.geDeleteList = function(){
		$scope.loadingOrder = true;
		$scope.deleteState=1;
		$("#bespokeMenu li").removeClass("active");
		$("#deketeBe").addClass("active");
		var pageConfig = {
	        "currentPage":$scope.paginationConf.currentPage-1,
	        "itemsPerPage":$scope.paginationConf.itemsPerPage,
	        "totalItems":$scope.paginationConf.totalItems,
	        "schoolId":$rootScope.user.schoolId
	    };
		$http.post(hostPath+"/office/getDeleteList",$.param(pageConfig)).success(function(response){
			if(response.status==1){
				$scope.deleteBsList = response.data;
				$scope.paginationConf.totalItems = parseInt(response.msg);
			}
		});
	}
	
}).filter('stateFilter',function(){
	return function(state){
		if(state == 0){
			state = "申请中";
		}else if(state == 1){
			state = "已通过";
		}else if(state == 2){
			state = "已拒绝";
		}
		return state;
	}
})
