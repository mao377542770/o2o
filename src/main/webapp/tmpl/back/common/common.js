angular.module('commonModule', []).controller('activityCtr',function($rootScope,$scope,$http){
	$(function(){
		$("#navigation li").removeClass("active");
		$("#activity").addClass("active");
	})
	
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 10, //每页展示多少条数据
        pagesLength: 10,    
        perPageOptions: [5,8],   //可以选每一页展示多少
        onChange: function(){
        	$scope.getAcyivity();
        }
    };
	
	$scope.isVote = false;//当前是活动公告还是投票公告
	$scope.isShow = false;  //是否显示投票项div
	$scope.isAddState = true;//是否显示选择投票的radio
	$scope.editOrDetails = 0;//修改页面还是详情页面

	//获取数据
	$scope.getAcyivity = function(){
		var searchConf = {
	        "currentPage":$scope.paginationConf.currentPage-1,
	        "itemsPerPage":$scope.paginationConf.itemsPerPage,
	        "totalItems":$scope.paginationConf.totalItems,
	        "schoolId":$rootScope.user.schoolId,
	        "deptId":$rootScope.user.deptId
	    };
		$http.post(hostPath+"/library/getActivity",$.param(searchConf)).success(function(response){
			$scope.paginationConf.totalItems = parseInt(response.msg);
			$scope.activitys = response.data;
		});
	}
	
	$scope.editStatus = 0;//记录是修改还是添加
	$scope.isEdit = true;
	//发布窗口
	$scope.showAddWin = function(){
		$scope.isEdit = false;
		$scope.isShow = false;
		$scope.isAddState = true;
		$("#optionsBtn").show();
		$scope.editStatus = 0;
		$("#acTitle").html("发布活动");
		$scope.title = "";
		$scope.endDate = "";
		$scope.content = "";
		$scope.publishUser = $rootScope.deptName;
		$("#addAcModal").modal({backdrop: 'static', keyboard: false});
	}
	//修改窗口
	$scope.nextOpenAct = null;//修改或者增加一个投票项只有重新打开修改窗口的activity对象
	$scope.showEdit = function(activity){
		$("#optionsBtn").show();
		$scope.nextOpenAct = activity;
		$scope.editOrDetails = 0;
		$scope.isEdit = false;
		$scope.isShow = true;
		$scope.editStatus = 1;
		$scope.isAddState = false;
		$("#acTitle").html("修改活动");
		$scope.title = activity.title;
		$scope.endDate = activity.endDate;
		$scope.content = activity.content;
		$scope.publishUser = activity.publishName;
		$scope.actiId = activity.id;
		if(activity.type == 1){
			$scope.isVote = true;
			$http.post(hostPath+"/library/getOPtionsByActivity",$.param({"activityId":activity.id})).success(function(response){
				if(response.status == 1){
					$scope.options = response.data;
				}
			});
		}else{
			$scope.isVote = false;
		}
		$("#addAcModal").modal({backdrop: 'static', keyboard: false});
	}
	//详情窗口
	$scope.showContent = function(activity){
		$scope.editOrDetails = 1;
		$scope.isEdit = true;
		$scope.isShow = true;
		$scope.isAddState = false;
		$("#acTitle").html("详情");
		$("#optionsBtn").hide();
		$scope.title = activity.title;
		$scope.endDate = activity.endDate;
		$scope.content = activity.content;
		$scope.publishUser = activity.publishName;
		if(activity.type == 1){
			$scope.isVote = true;
			$http.post(hostPath+"/library/getOPtionsByActivity",$.param({"activityId":activity.id})).success(function(response){
				if(response.status == 1){
					$scope.options = response.data;
				}
			});
		}else{
			$scope.isVote = false;
		}
		$http.post(hostPath+"/library/addViewCount",$.param({"id":activity.id})).success(function(response){
			if (response.status == 1) {
				$("#addAcModal").modal({backdrop: 'static', keyboard: false});
			}
		});
	}
	//保存发布公告
	$scope.optionId = 0; //记录修改的投票项id
	$scope.optionType = 0;//记录当前是修改还是增加投票项
	$scope.saveActivity = function(){
		var activityInfo = {
			"title":$scope.title,
			"content":$scope.content,
			"schoolId":$rootScope.user.schoolId,
			"deptId":$rootScope.user.deptId,
			"publishUser":$rootScope.user.id,
			"type":$scope.isVoteOption,
			"publishName":$scope.publishUser
		};
		if($scope.title==undefined || $scope.title=="" || $scope.content=="" ||$scope.content==undefined){
			alert("请填写标题和内容");
			return;
		}
		if($scope.isVoteOption==undefined || $scope.isVoteOption =="" || $scope.isVoteOption==null){
			alert("请选择是否投票");
			return;
		}
		if($scope.isVoteOption==1){
			if($("#endDate").val() == ""){
				alert("请选择截止日期");
				return;
			}
			activityInfo.endDate = $("#endDate").val();
		}
		if($scope.editStatus == 1){
			//修改
			activityInfo.id = $scope.actiId;
		}else if($scope.editStatus == 0){
			//添加
			activityInfo.type = $scope.isVoteOption;
		}
		$http.post(hostPath+"/library/manageActivity",$.param(activityInfo)).success(function(response){
			if(response.status == 1){
				//发布成功，关闭窗口
				$("#addAcModal").modal('hide');
				$scope.nextOpenAct = activityInfo;
				$scope.getAcyivity();
			}
		});
		
	} 
	//删除
	$scope.deleteAc = function(id){
		if(confirm("是否删除本条公告？")){
			$http.post(hostPath+"/library/deleteActivity",$.param({"id":id})).success(function(response){
				if(response.status == 1){
					$scope.getAcyivity();
				}
			});
		}
	}
	//$scope.showActivity();
	
	//上传图片
	$scope.isUpload = false;
	$("#file-0").fileinput({
	    uploadUrl: hostPath+'/library/fileUpload', 
	    allowedFileExtensions : ['jpg', 'png','gif'],
	    overwriteInitial: false,
	    maxFileSize: 200,
	    maxFilesNum: 1,
	    maxFileCount: 1,
	    initialPreviewCount:1,
	    allowedFileTypes: ['image'],
	    slugCallback: function(filename) {
	        return filename.replace('(', '_').replace(']', '_');
	    }
	}).on('fileuploaded', function(event, result) { //回调函数
		var res = result.response;
		if (res.status == 1) {
			$scope.filePath = res.data;
			$scope.isUpload = true;
		}else{
			$scope.filePath = "";
			$scope.isUpload = false;
		}
		//$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
		//$http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
	});
	
	//测试用的方法，没什么意义
	$scope.showAddoption = function(){
		$("#addOPtion").modal({backdrop: 'static', keyboard: false});
	}
	
	//保存添加投票项    修改
	$scope.addAddoption = function(){
		var optionInfo ={};
		var url="";
		if($scope.optionType == 0){
			//增加
			optionInfo = {
				"activityId":$scope.actiId,
				"title":$scope.optionTitle,
				"optionCount":$scope.optionContent,
				"optionImg":$scope.filePath
			};
			url=hostPath+'/library/addOption';
		}else if($scope.optionType == 1){
			//修改
			optionInfo = {
				"id":$scope.optionId,
				"title":$scope.optionTitle,
				"optionCount":$scope.optionContent,
				"optionImg":$scope.filePath
			};
			url=hostPath+'/library/updateOption';
		}
		var tempFileName = $(".file-caption-name").attr("title");
		if((tempFileName != undefined && $scope.isUpload == false)&&(tempFileName != '' && $scope.isUpload == false)){
 			alert('请先上传图片');
 			return;
 		}
		$http.post(url,$.param(optionInfo)).success(function(response){
			if(response.status == 1){
				$("body").css("overflow","auto");
				$("#addOPtion").modal('hide');
				$scope.showEdit($scope.nextOpenAct);
			}
		});
		
	}
	
	//修改投票项窗口
	$scope.editOptions = function(option){
		$scope.optionType = 1;
		//$("#addAcModal").modal('hide');
		$("#optionLable").html("修改投票项");
		$scope.optionId = option.id;
		if(option.optionImg == "" || option.optionImg ==undefined ||option.optionImg ==null){
			$("#imgDiv").html('');
			$("#imgDiv").hide();
			$(".fileinput-remove").click();
			$("#file-upload-div").show();
		}else{
			$("#file-upload-div").hide();
			var imageurl = hostPath + "/"+option.optionImg;
			var imgStr = '<span style=" float:left;">';
	    	imgStr += '<img src="'+imageurl+'" style="width:335px;height:200px;" />';
	    	imgStr += '</span><button type="button" onclick="delImg()" class="myclose" aria-label="Close" style="width:25px; height:25px; font-size:25px;"><span aria-hidden="true">&times;</span></button>';
			$("#imgDiv").html(imgStr);
			$("#imgDiv").show();
		}
		$scope.optionContent = option.optionCount;
		$scope.optionTitle = option.title;
		$("#addOPtion").css("overflow","auto").css("overflow-y","scroll");
		$("body").css("overflow","hidden").css("padding-right","0px");
		$("#addOPtion").modal({backdrop: 'static', keyboard: false});
		$("body").addClass("fix-modal-open");
	}
	//添加投票项窗口
	$scope.addOption = function(){
		$("#optionLable").html("添加投票项");
		$scope.optionType = 0;
		$("#imgDiv").html('');
		$("#imgDiv").hide();
		$scope.optionContent="";
		$scope.optionTitle = "";
		$("#file-upload-div").show();
		$(".fileinput-remove").click();
		//$("#addAcModal").modal('hide');
		$("#addOPtion").css("overflow","auto").css("overflow-y","scroll");
		$("body").css("overflow","hidden").css("padding-right","0px");
		$("#addOPtion").modal({backdrop: 'static', keyboard: false});
		$("body").addClass("fix-modal-open");
	}
	//删除投票项
	$scope.deleteOption = function(id){
		if(confirm("是否删除该项？")){
			$http.post(hostPath+'/library/deleteOption',$.param({"id":id})).success(function(response){
				if(response.status == 1){
					$("#addAcModal").modal('hide');
				}
			});
		}
	}
}).controller('userinfoCtr',function($rootScope,$scope,$http){
	$(function(){
		$("#navigation li").removeClass("active");
		$("#userInfo").addClass("active");
	})
		
	//获取用户个人信息
	$scope.getUserInfo = function(){
		$http.post(hostPath+"/user/getUserInfo",$.param({"id":$rootScope.user.id})).success(function(response){
			$scope.userName = response.data.userName;
			$scope.nickName = response.data.nickName;
			$scope.gender = response.data.gender;
			$scope.school = response.data.schoolName;
			$scope.dept = response.data.deptName;
			$scope.phone = response.data.telphone;
			$scope.email = response.data.email;
			$scope.passWord = response.data.passWord;
			$scope.name = response.data.name;
		});
	}
	$scope.getUserInfo();
	//修改状态
	$scope.editInfo = function(){
		$scope.edit = true;
	}
	//保存修改
	$scope.saveUpdate = function(){
		var user = {
			"id":$rootScope.user.id,
			"nickName":$scope.nickName,
			"gender":$scope.gender,
			"telphone":$scope.phone,
			"email":$scope.email,
			"name":$scope.name
		}
		$http.post(hostPath+"/user/updateUserInfo",$.param(user)).success(function(response){
			if(response.status == 1){
				$rootScope.user.name = $scope.name;
				//alert($rootScope.user.name);
				$scope.edit = false;
			}
		});
	}
	//关闭修改
	$scope.closeUpdate = function(){
		$scope.edit = false;
	}
	//修改密码窗口
	$scope.showPass = function(){
		$("#passWordWin").modal({backdrop: 'static', keyboard: false});
	}
	//保存修改
	$scope.savePassword = function(){
		if($scope.newpassword==undefined || $scope.oldpassword==undefined ||$scope.newpassword=="" || $scope.oldpassword==""){
			return;
		}
		if($scope.newpassword != $scope.renewpassword){
			alert("两次输入不一致");
		}else{
			$http.post(hostPath+"/user/updatePassWord",$.param({"oldPass":$scope.passWord,"oldPass1":$scope.oldpassword,"newPass":$scope.renewpassword,"id":$rootScope.user.id})).success(function(response){
				if(response.status == 1){
					alert(response.msg);
					$("#passWordWin").modal('hide');
				}else{
					alert(response.msg);
				}
			});
		}
		
	}
}).controller('LostFoundCtr',function($rootScope,$scope,$http){
	//失物招领
	$(function(){
		$("#navigation li").removeClass("active");
		$("#lostFound").addClass("active");
	})
	
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 10, //每页展示多少条数据
        pagesLength: 10,    
        perPageOptions: [5,8],   //可以选每一页展示多少
        onChange: function(){
        	$scope.getLostFoundList($scope.currentState);
        }
    };
	
	//获取失物招领列表
	$scope.currentState = 0;
	$scope.getLostFoundList = function(state){
		$scope.currentState = state;
		$("#attestationMenu li").removeClass("active");
		if(state==0){
			//未认领
			$("#pass").addClass("active");
		}else if(state==1){
			//已认领
			$("#refuse").addClass("active");
		}
		var searchConf = {
	        "currentPage":$scope.paginationConf.currentPage,
	        "itemsPerPage":$scope.paginationConf.itemsPerPage,
	        "totalItems":$scope.paginationConf.totalItems,
	        "schoolId":$rootScope.user.schoolId,
	        "deptId":$rootScope.user.deptId,
	        "state":state
	    };
		$http.post(hostPath+"/lostFound/getFrontLostFoundList",$.param(searchConf)).success(function(response){
			if(response.status == 1){
				$scope.paginationConf.totalItems = parseInt(response.msg);
				$scope.lostFounds = response.data;
			}
		});
	}
	
	//添加失物招领窗口
	$scope.isEditOrAdd = 0;
	$scope.openAddlfWin = function(){
		$scope.isRemark = false;
		$scope.isShowEdit = false;
		$scope.isEditOrAdd = 0;
		$scope.title="";
		$scope.content = "";
		$scope.publishser = $rootScope.deptName;
		$("#lfTitle").html("发布失物招领信息");
		$scope.isEdit = true;
		$("#addlfModal").modal({backdrop: 'static', keyboard: false});
	}
	//详情和修改  
	$scope.showDetailsAndEdit = function(lostFound){
		if($scope.currentState == 1){
			$scope.isRemark = true;
			$scope.reMarkContent = lostFound.remark;
		}else if($scope.currentState == 0){
			$scope.isRemark = false;
		}
		if($scope.currentState == 1){
			$scope.isShowEdit = false;
		}else{
			$scope.isShowEdit = true;
		}
		
		$scope.title=lostFound.title;
		$scope.content = lostFound.content;
		$scope.publishser = lostFound.publisher;
		$scope.editLfId = lostFound.id; 
		$("#lfTitle").html("失物招领信息");
		$scope.isEdit = false;
		$("#addlfModal").modal({backdrop: 'static', keyboard: false});
	}
	//改变成可修改状态
	$scope.changToEdit = function(){
		$scope.isEditOrAdd = 1;
		$scope.isEdit = true;
	}
	//保存添加
	$scope.saveLostFound = function(){
		var lostFound={
			"title":$scope.title,
			"content":$scope.content,
			"deptId":$rootScope.user.deptId,
			"schoolId":$rootScope.user.schoolId,
			"publishUser":$rootScope.user.id,
			"publisher":$scope.publishser,
			"publishDate":new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()
		}
		var url = "";
		if($scope.title==""||$scope.title==undefined){
			alert("请填写完整的失物招领内容。");
			return;
		}
		if($scope.isEditOrAdd == 0){
			url= hostPath+"/lostFound/addLostFound";
		}else{
			lostFound.id = $scope.editLfId;
			url= hostPath+"/lostFound/updateLostFound";
		}
		$http.post(url,$.param(lostFound)).success(function(response){
			if(response.status == 1){
				$("#addlfModal").modal('hide');
				$scope.getLostFoundList($scope.currentState);
			}
		});
	}
	//添加认领人信息窗口
	$scope.openAddrmWin = function(id){
		$scope.remark ="";
		$scope.remarkId = id;
		$("#addrmModal").modal({backdrop: 'static', keyboard: false});
	}
	//保存添加认领备注
	$scope.saveRemark = function(){
		var remark = {
			"id":$scope.remarkId,
			"remark":$scope.remark,
			"state":1,
			"claimDate":new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()+ " " + new Date().getHours()+ ":" + new Date().getMinutes() +":"+ new Date().getSeconds(),
		};
		if($scope.remark==""||$scope.remark==undefined){
			alert("请填写认领备注信息。");
			return;
		}
		$http.post(hostPath+"/lostFound/updateLostFound",$.param(remark)).success(function(response){
			if(response.status == 1){
				$("#addrmModal").modal('hide');
				$scope.getLostFoundList($scope.currentState);
			}
		});
	}
	
	//删除失物招领
	$scope.deletelostfound = function(lostFound){
		if(confirm("确认删除这条信息？")){
			$http.post(hostPath+"/lostFound/deleteLostFound",$.param(lostFound)).success(function(response){
				if(response.status == 1){
					for (var i = 0; i < $scope.lostFounds.length; i++) {
						if($scope.lostFounds[i].id==lostFound.id){
							$scope.lostFounds.splice(i, 1);
						}
					}
				}
			});
		}
	}
}).controller('attestationCtr',function($rootScope,$scope,$http){
	//老师认证
	$(function(){
		$("#navigation li").removeClass("active");
		$("#attestation").addClass("active");
	})
	
	//获取不同状态的认证申请
	$scope.getAttestationByState = function(state){
		$scope.attState = state;
		$("#attestationMenu li").removeClass("active");
		if(state==0){
			//申请中
			$("#applying").addClass("active");
		}else if(state==1){
			//通过
			$("#pass").addClass("active");
		}else if(state==2){
			//拒绝
			$("#refuse").addClass("active");
		}
		$http.post(hostPath+"/office/getAttestationByState",$.param({"state":state,"schoolId":$rootScope.user.schoolId})).success(function(response){
			$scope.attestations = response.data;
		});
	}
	$scope.getAttestationByState(0);
	
	//通过或者拒绝老师认证
	$scope.updateAttestationState = function(attestation,state){
		//attestation.atteDate = new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate();
		attestation.atteState = state;
		attestation.atteUserId = $rootScope.user.id;
		var user = {
			"id":attestation.applyUserId,
			"attestationState":state,
			"userType":3,
			"name":attestation.applyName
		};
		
		if($rootScope.user.name == null ||$rootScope.user.name == "" ||$rootScope.user.name == undefined){
			$http.post(hostPath+"/user/getUserInfo",$.param({"id":$rootScope.user.id})).success(function(response){
				$scope.name = response.data.name;
				if($scope.name==""||$scope.name==undefined||$scope.name==null){
					alert("请先前往个人信息页面完善个人信息，填写姓名！");
					return;
				}
			});
			return;
		}
		$http.post(hostPath+"/office/updateAttestation",$.param(attestation)).success(function(response){
			if(response.status == 1 && state == 1){
				//通过认证之后，修改user表的认证老师状态和姓名
				$http.post(hostPath+"/user/updateUserInfo",$.param(user)).success(function(result){
					if(result.status == 1){
						$scope.getAttestationByState(0);
					}
				});
			}else if(response.status == 1){
				$scope.getAttestationByState(0);
			}
		});
	}
	
	//删除被拒绝的老师认证申请
	$scope.deleteAttestation = function(id){
		$http.post(hostPath+"/office/deleteAttestation",$.param({"id":id})).success(function(result){
			if(result.status == 1){
				for (var i = 0; i < $scope.attestations.length; i++) {
					if($scope.attestations[i].id==id){
						$scope.attestations.splice(i, 1);
					}
				}
			}
		});
	}
	//姓名为空则先添加姓名
	$scope.addName = function(){
		var userInfo = {
			"id":$rootScope.user.id,
			"name":$scope.checkName
		}
		$http.post(hostPath+"/user/updateUserInfo",$.param(userInfo)).success(function(result){
			if(result.status == 1){
				$("#nameModal").modal('hide');
				$scope.getAttestationByState(0);
			}
		});
	}
	
}).controller('carouselCtr',function($rootScope,$scope,$http){
	//轮播图管理
	$(function(){
		$("#navigation li").removeClass("active");
		$("#carousel").addClass("active");
	})
	
	//获取轮播图列表
	$scope.getCarouselList = function(){
		$http.post(hostPath+"/carousel/getCarouselList",$.param({"schoolId":$rootScope.user.schoolId})).success(function(response){
			if(response.status == 1){
				$scope.carouselList = response.data;
			}
		});
	}
	$scope.getCarouselList();
	//增加窗口
	$scope.isEditOrAdd = 0;
	$scope.showAddWin = function(){
		$scope.isEditOrAdd = 0;
		$scope.carouselUrl = "";
		$("#optionLable").html("添加轮播图片");
		$(".fileinput-remove").click();
		$("#imgDiv").html('');
		$("#imgDiv").hide();
		$("#file-upload-div").show();
		$("#addOCarousel").modal({backdrop: 'static', keyboard: false});
	}
	//修改窗口
	$scope.editCarWin = function(carousel){
		$scope.isEditOrAdd = 1;
		$("#optionLable").html("修改轮播图片");
		$scope.carouselId = carousel.id;
		$scope.carouselUrl = carousel.contentUrl;
		if(carousel.bannaPic == "" || carousel.bannaPic ==undefined ||carousel.bannaPic ==null){
			$("#imgDiv").html('');
			$("#imgDiv").hide();
			$("#file-upload-div").show();
			$(".fileinput-remove").click();
		}else{
			$("#file-upload-div").hide();
			var imageurl = hostPath + "/"+carousel.bannaPic;
			var imgStr = '<span style=" float:left;">';
	    	imgStr += '<img src="'+imageurl+'" style="width:335px;height:200px;" />';
	    	imgStr += '</span><button type="button" onclick="delImg()" class="myclose" aria-label="Close" style="width:25px; height:25px; font-size:25px;"><span aria-hidden="true">&times;</span></button>';
			$("#imgDiv").html(imgStr);
			$("#imgDiv").show();
		}
		$("#addOCarousel").modal({backdrop: 'static', keyboard: false});
	}
	$scope.filePath = "";
	$scope.isUpload = false;
	//上传图片
	$scope.isUpload = false;
	$("#file-0").fileinput({
	    uploadUrl: hostPath+'/library/fileUpload', 
	    allowedFileExtensions : ['jpg', 'png','gif'],
	    overwriteInitial: false,
	    maxFileSize: 200,
	    maxFilesNum: 1,
	    maxFileCount: 1,
	    initialPreviewCount:1,
	    allowedFileTypes: ['image'],
	    slugCallback: function(filename) {
	        return filename.replace('(', '_').replace(']', '_');
	    }
	}).on('fileuploaded', function(event, result) { //回调函数
		var res = result.response;
		if (res.status == 1) {
			$scope.filePath = res.data;
			$scope.isUpload = true;
		}else{
			$scope.filePath = "";
			$scope.isUpload = false;
		}
	});
	
	//保存添加或者修改
	$scope.saveAddCarousel = function(){
		var tempFileName = $(".file-caption-name").attr("title");
		if((tempFileName != undefined && $scope.isUpload == false)&&(tempFileName != '' && $scope.isUpload == false)){
 			alert('请先上传图片');
 			return;
 		}
		if($scope.filePath == "" || $scope.filePath == undefined){
			alert('需要先上传一张轮播图片');
			return;
		}
		var carousel={
			"bannaPic":	$scope.filePath,
			"contentUrl":$scope.carouselUrl,
			"schoolId":$rootScope.user.schoolId
		}
		var url = "";
		if($scope.isEditOrAdd == 0){
			url = hostPath+"/carousel/addCarousel";
		}else if($scope.isEditOrAdd == 1){
			url = hostPath+"/carousel/updateCarousel";
			carousel.id = $scope.carouselId;
		}
		$http.post(url,$.param(carousel)).success(function(response){
			if(response.status == 1){
				$("#addOCarousel").modal('hide');
				$scope.getCarouselList();
			}
		});
	}
	//删除一个轮播图
	$scope.delteCarousel = function(id){
		if(confirm("确认删除该图？")){
			$http.post(hostPath+"/carousel/deleteCarousel",$.param({"id":id})).success(function(response){
				if(response.status == 1){
					for (var i = 0; i < $scope.carouselList.length; i++) {
						if($scope.carouselList[i].id == id){
							$scope.carouselList.splice(i, 1);
						}
					}
				}
			});
		}
	}
	
}).filter("imgSrcFilter",function(){ //图片路径过滤器
	return function(src){
		if(src=="" || src ==null || src == undefined){
			src=hostPath+"/images/tm-2.png";
		}else{
			src = hostPath+"/"+src;
		}
		return src;
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
}).filter('numberFormatFilter',function(){
	return function(number){
		if(number==null||number=='null'||number==""){
			number =  0;
		}
		return number;
	}
})

//删除图片
function delImg(){
	$("#imgDiv").html('');
	$("#file-upload-div").show();
	$(".fileinput-remove").click();
}