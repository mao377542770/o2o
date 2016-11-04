angular.module('libraryModule', []).controller('newsController',function($rootScope,$scope,$http){
	/*
	 *  公告控制器
	 */
	//window.location.reload();
	UE.delEditor("editor");
	var ue = UE.getEditor('editor',{allowDivTransToP: false});
	$(function(){
		$("#navigation li").removeClass("active");
		$("#news").addClass("active");
	})
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

	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 10, //每页展示多少条数据
        pagesLength: 10,    
        perPageOptions: [10],   //可以选每一页展示多少
        onChange: function(){
          $scope.getNewsList();
        }
    };
    
  //获取公告列表
	$scope.getNewsList = function(){
		var searchConf = {
            "currentPage":$scope.paginationConf.currentPage-1,
            "itemsPerPage":$scope.paginationConf.itemsPerPage,
            "totalItems":$scope.paginationConf.totalItems,
            "schoolId":$rootScope.user.schoolId
        };
		$http.post(hostPath+"/library/getNewsList",$.param(searchConf)).success(function(response){
			if(response.status ==1){
				$scope.paginationConf.totalItems = parseInt(response.msg);
				$scope.newsList = response.data;
			}
		});
	}
	$scope.btnStatus = 0;
    //添加窗口
	$scope.openAdd = function(){
		$scope.btnStatus = 0;
		$scope.isDisabled = false;
		$scope.title =null;
		$("#imgDiv").hide();
		$("#file-upload-div").show();
		$(".fileinput-remove").click();
		//$scope.content=null;//
		ue.setContent("");
		ue.setEnabled();
		$scope.nickName = $rootScope.deptName;
		$("#addNewsWin").modal({backdrop: 'static', keyboard: false});
	}
	//保存添加
	$scope.saveNews = function(){
		var url=""; 
		var news = {
			"title":$scope.title,
			"content":ue.getContent(),
			"newsImage":$scope.filePath,
			"publishUser":$scope.nickName,
			"schoolId":$rootScope.user.schoolId,
			"publishName":$scope.nickName,
			"userId":$rootScope.user.id
		};
		if($scope.btnStatus == 0){
			url = hostPath+"/library/addNews";
		}else if($scope.btnStatus == 1){
			if($scope.filePath==""||$scope.filePath==undefined){
				$scope.filePath = $scope.hasImg;
			}
			url = hostPath+"/library/updateNews";
			news.id = $scope.newId;
		}
		news.newsImage = $scope.filePath;
		if($scope.title =="" ||$scope.title == null){
			alert("请填写标题");
			return;
		}
		var tempFileName = $(".file-caption-name").attr("title");
		if((tempFileName != undefined && $scope.isUpload == false)&&(tempFileName != '' && $scope.isUpload == false)){
 			alert('请先上传图片');
 			return;
 		}
		$http.post(url,$.param(news)).success(function(response){
			if(response.status ==1){
				$scope.filePath="";
				$("#addNewsWin").modal('hide');
				$scope.getNewsList();
			}
		});
	}
	//查看详情
	$scope.showContent = function(news){
		$scope.isDisabled = true;
		$("#winTitle").html("公告详情");
		$scope.title = news.title;
		//$scope.content = news.content;
		ue.setContent(news.content);
		ue.setDisabled();
		if(news.newsImage == "" || news.newsImage ==undefined ||news.newsImage ==null){
			$("#imgDiv").html('');
			$("#imgDiv").show();
			$("#file-upload-div").hide();
		}else{
			$("#file-upload-div").hide();
			var imageurl = hostPath + "/"+news.newsImage;
			var imgStr = '<span style=" float:left;">';
	    	imgStr += '<img src="'+imageurl+'" style="width:335px;height:200px;" />';
	    	imgStr += '</span>';
			$("#imgDiv").html(imgStr);
			$("#imgDiv").show();
		}
		$scope.nickName = news.publishUser;
		$("#addNewsWin").modal({backdrop: 'static', keyboard: false});
	}
	//修改公告
	$scope.updateNews = function(news){
		$scope.hasImg= news.newsImage;
		$scope.btnStatus = 1;
		$scope.isDisabled = false;
		$scope.newId = news.id;
		$("#winTitle").html("修改公告");
		$scope.title = news.title;
		//$scope.content = news.content;
		ue.setContent(news.content);
		ue.setEnabled();
		$scope.nickName = news.publishUser;
		if(news.newsImage == "" || news.newsImage ==undefined ||news.newsImage ==null){
			$("#imgDiv").html('');
			$("#imgDiv").hide();
			$(".fileinput-remove").click();
			$("#file-upload-div").show();
		}else{
			$("#file-upload-div").hide();
			var imageurl = hostPath + "/"+news.newsImage;
			var imgStr = '<span style=" float:left;">';
	    	imgStr += '<img src="'+imageurl+'" style="width:335px;height:200px;" />';
	    	imgStr += '</span><button type="button" onclick="delImg()" class="myclose" aria-label="Close" style="width:25px; height:25px; font-size:25px;"><span aria-hidden="true">&times;</span></button>';
			$("#imgDiv").html(imgStr);
			$("#imgDiv").show();
		}
		$("#addNewsWin").modal({backdrop: 'static', keyboard: false});
	}
	//删除公告
	$scope.deleteNews = function(id){
		if(confirm("确定要删除该公告吗？")){
			$http.post(hostPath+"/library/deleteNews",$.param({"newsId":id})).success(function(response){
				if(response.status == 1){
					$scope.getNewsList();
				}
			});
		}
	}
}).controller('literatureController',function($rootScope,$scope,$http){
	
	/*
	 * 文献资源控制器
	 */
	
	$(function() {
		$("#navigation li").removeClass("active");
		$("#literature").addClass("active");
	})
	
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 10, //每页展示多少条数据
        pagesLength: 10,    
        perPageOptions: [10],   //可以选每一页展示多少
        onChange: function(){
        	if($scope.order == true){
        		$scope.getLiteratureList($scope.currentType);
        	}else{
        		return;
        	}
        }
    };
	
	$scope.editType = [];//记录编辑状态
	$scope.currentType=0;//记录当前的文档类型
	$scope.isAdd = false; //记录是否点击添加标签按钮
	$scope.addBtn = 0;  //添加按钮的状态
	$scope.order = false;
	//获取文献类型
	$scope.getLiteratureType= function(){
		$http.post(hostPath+"/library/getAllType",$.param({"schoolId":$rootScope.user.schoolId})).success(function(response){
			if(response.status == 1){
				$scope.literatureTypes = response.data;
				$scope.currentType = $scope.literatureTypes[0].id;
				$scope.order = true;
				$scope.getLiteratureList($scope.currentType);
			}
		});
	}
	//按照文献类型获取文献列表
	$scope.isActive=[];
	$scope.lastIndex=0;//记录上一次的标签下标
	$scope.lastPage=0;
	$scope.getLiteratureList = function(typeId,index){
		$scope.currentType = typeId;
		if(typeId !=$scope.lastIndex){
			if($scope.editType[$scope.lastIndex]){
				$scope.editType[$scope.lastIndex] = false;
			}
			$("#literTypes li").removeClass("active");
			$scope.isActive[$scope.lastIndex] = false;
		}
		$scope.isActive[typeId] = true;
		var searchConf = {
	        "currentPage":$scope.paginationConf.currentPage-1,
	        "itemsPerPage":$scope.paginationConf.itemsPerPage,
	        "totalItems":$scope.paginationConf.totalItems,
	        "schoolId":$rootScope.user.schoolId,
	        "typeId":$scope.currentType
	    };
		$http.post(hostPath+"/library/getAllLiterature",$.param(searchConf)).success(function(response){
			if(response.status == 1){
				$scope.lastIndex = typeId;
				$scope.lastPage = $scope.paginationConf.currentPage-1;
				$scope.literatureList = response.data;
				$scope.paginationConf.totalItems = parseInt(response.msg);
			}
		});
	}
	$scope.getLiteratureType();
	//添加文献类型
	$scope.manageType = function(){
		$scope.newTypeName = "";
		$("#typeModal").modal({backdrop: 'static', keyboard: false});
		
		/*if($scope.addBtn == 0){
			//添加文本框开启
			$scope.isAdd = true;
			$scope.addBtn =1;
			$scope.typeName = "";
			$("#addLi").html("保存");
		}else if($scope.addBtn == 1){
			
		}*/
	}
	
	//编辑文献类型
	$scope.showEditType =function(id){
		$scope.editType[id] = true;
	} 
	//取消修改
	$scope.closeEditType = function(id){
		$scope.editType[id] = false;
	}
	
	//删除文献类型
	$scope.deleteType = function(id){
		$http.post(hostPath+"/library/deleteLiType",$.param({"id":id})).success(function(response){
			if(response.status == 1){
				for (var i = 0; i < $scope.literatureTypes.length; i++) {
					if($scope.literatureTypes[i].id == id){
						$scope.literatureTypes.splice(i, 1);
					}
				}
			}
		});
	}
	
	//类型修改保存
	$scope.saveTypeEdit = function(type){
		type.name = $("#editName"+type.id).val();
		$http.post(hostPath+"/library/updateLiType",$.param(type)).success(function(response){
			$scope.editType[type.id] = false;
			$scope.getLiteratureType();
		});
	}
	//添加保存类型
	$scope.saveAddType = function(){
		//添加保存
		var literType = {
			"name":$scope.newTypeName,
			"schoolId":$rootScope.user.schoolId
		}
		if($scope.newTypeName==""||$scope.newTypeName==null || $scope.newTypeName==undefined){
			alert("请先输入类型！");
		}else{
			$http.post(hostPath+"/library/addLiType",$.param(literType)).success(function(response){
				$scope.literatureTypes.push(literType);
			});
		}
	}
	//开启添加文献窗口
	$scope.isEditOrAdd = 0;//记录是修改还是添加
	$scope.opendAddWin = function(){
		$scope.isEditOrAdd = 0;//添加
		$("#myModalLabel").html("添加");
		$scope.showContent = false;
		$scope.literName = "";
		$scope.literUrl = "";
		$scope.literAbs = "";
		$("#literatureWin").modal({backdrop: 'static', keyboard: false});
	}
	//添加保存
	$scope.saveAddLiterature = function(){
		var url="";
		var literature = {
			"name":$scope.literName,
			"literatureUrl":$scope.literUrl,
			"abst":$scope.literAbs,
			"literatureType":$scope.currentType,
			"schoolId":$rootScope.user.schoolId
		};
		if($scope.isEditOrAdd == 0){
			url = hostPath+"/library/addLiterature";
		}else if($scope.isEditOrAdd == 1){
			literature.id= $scope.literId;
			url = hostPath+"/library/updateLiterature";
		}
		if($scope.literName==""||$scope.literName==null||$scope.literUrl == ""||$scope.literUrl == null){
			alert("请填写资源名称和访问地址");
		}else{
			$http.post(url,$.param(literature)).success(function(response){
				if(response.status == 1){
					if($scope.isEditOrAdd == 0){
						literature.id = parseInt(response.msg);
						$scope.literatureList.push(literature);
					}else{
						$scope.getLiteratureList($scope.currentType,1);
					}
					$("#literatureWin").modal('hide');
				}
			});
		}
	}
	//修改窗口
	$scope.showContent = false;
	$scope.showEdit = function(literature,type){
		if(type==0){
			$("#myModalLabel").html("简介");
			$scope.showContent = true;
		}else if(type==1){
			$scope.isEditOrAdd = 1;//修改
			$("#myModalLabel").html("修改");
			$scope.showContent = false;
		}
		$scope.literName=literature.name;
		$scope.literUrl=literature.literatureUrl;
		$scope.literAbs=literature.abst;
		$scope.literId = literature.id;
		$("#literatureWin").modal({backdrop: 'static', keyboard: false});
	}
	//删除文献
	$scope.deleteLiter = function(literature){
		if(confirm("确定要删除该文献吗？")){
			$http.post(hostPath+"/library/deleteLiter",$.param(literature)).success(function(response){
				if(response.status == 1){
					$scope.getLiteratureList($scope.currentType,1);
				}
			});
		}
	}
	
}).controller('customController',function($rootScope,$scope,$http){
	/*
	 * 功能定制控制器
	 */
	$(function(){
		$("#navigation li").removeClass("active");
		$("#customization").addClass("active");
	})
	
	//获取本部门已经开通的功能
	$scope.getMyDeptModule = function(){
		$http.post(hostPath+"/library/getMyModule",$.param({"schoolId":$rootScope.user.schoolId,"deptId":$rootScope.user.deptId})).success(function(response){
			if(response.status == 1){
				$scope.myModules = response.data;
				$scope.getAllOtherModule();
			}
		});
		return true;
	}
	//获取本部门可以开通但还没有开通的功能
	$scope.getAllOtherModule = function(){
		var temp = {"tempPro":"temp"};
		$http.post(hostPath+"/library/getDeptAllModule",$.param({"deptId":$rootScope.user.deptId})).success(function(response){
			if(response.status == 1){
				$scope.deptModules = response.data;//获取到本部门所有的功能
				//除去已经开通的功能
				for (var i = 0; i < $scope.deptModules.length; i++) {
					for (var j = 0; j < $scope.myModules.length; j++) {
						if($scope.deptModules[i].id == $scope.myModules[j].moduleId){
							$scope.deptModules.splice(i, 1,temp);
						}
					}
				}
				var k = 0;
				for (; k < $scope.deptModules.length; k++) {
					if($scope.deptModules[k].hasOwnProperty("tempPro")){
						array = $scope.deptModules.splice(k, 1);
						k--;
					}
				}
				console.log($scope.deptModules);
			}
		});
	}
	$scope.getMyDeptModule();
	
	$scope.applyStatus = [];
	//申请开通功能模块
	$scope.applyModule = function(module){
		$scope.apply = module.moduleName;
		$scope.applyId = module.id;
		$("#applyModule").modal({backdrop: 'static', keyboard: false});
	}
	
	//保存申请开通新功能
	$scope.saveApply = function(){
		var applyInfo ={
			"schoolId":$rootScope.user.schoolId,
			"moduleId":$scope.applyId,
			"deptId":$rootScope.user.deptId,
			"customer":$scope.customer,
			"phone":$scope.phone,
			"deptName":$scope.deptName,
			"schoolName":$scope.school,
			"moduleName":$scope.apply,
			"applyType":2,
			"applyDate":new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()
		};
		if($scope.school==undefined||$scope.deptName==undefined||$scope.phone==undefined){
			alert("请填写申请内容");
		}else{
			console.log(applyInfo);
			$http.post(hostPath+"/library/applyModule",$.param(applyInfo)).success(function(response){
				if(response.status == 1){
					//$scope.applyStatus[$scope.applyId] = true;
					alert("申请成功，我们会尽快和你取得联系，请耐心等待。");
					$("#applyModule").modal('hide');
				}
			});
		}
	}

	//拖动排序
	/*$(function() {
		var moduleObj =[];
	    $("#sortable" ).sortable({
	      placeholder: "ui-state-highlight",
	      deactivate: function( event, ui ) {
	    	  $('#sortable').find('li').each(function(index){
	    		  var idSequences = {};
	    		  idSequences.id = parseInt($(this).attr('id').substr(6));
	    		  idSequences.sequence = index+1;
	    		  moduleObj.push(idSequences);
	    	  })
	    	  $http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
	    	  //把拖动后的顺序保存发到数据库
	    	  $http({
					method: "POST", 
					url: hostPath+"/library/saveSequence",
					data:moduleObj
				}).
				success(function(data, status) {
				   $scope.users = data;
				   //修改完成要先清空数组
				   moduleObj=[];
				});
	    	  //console.log(moduleObj);
	    	  $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
	      }
	      
	    });
	    $("#sortable" ).disableSelection();
	});*/
	
	
}).controller('presention',function($rootScope,$scope,$http){
	//入馆须知和开放时间
	UE.delEditor("editor");
	var ue = UE.getEditor('editor');
	$("#editor").find('div:first').css("width","780px");
	$(".edui-editor-iframeholder").css("width","780px");
	$(function(){
		$("#navigation li").removeClass("active");
		$("#presention").addClass("active");
	})
	$scope.present = false; //入馆须知模块显示状态
	$scope.serviceTime = false;//开放时间模块
	$scope.rules = false;//规章制度
	$scope.editWin = false;//编辑框状态
	$scope.currentType=0; //记录正在修改的是入馆须知还是开放时间
	//显示入馆须知
	$scope.showPersent = function(){
		$("#libraryRules li").removeClass("active");
		$("#present").addClass("active");
		$scope.present = true;
		$scope.serviceTime = false;
		$scope.rules = false;
		$scope.editWin = false;
		//获取入馆须知
		$scope.getData();
	}
	//获取数据
	$scope.getData = function(){
		$http.post(hostPath+"/library/getPresention",$.param({"deptId":$rootScope.user.deptId,"schoolId":$rootScope.user.schoolId})).success(function(response){
			$scope.libraryNote = response.data;
			$scope.presentation = response.data.presentation;
			$scope.serviceNote = response.data.serviceTime;
			$scope.preId = response.data.id;
		});
	}
	//显示开放时间
	$scope.showServiceTime = function(){
		$("#libraryRules li").removeClass("active");
		$("#serviceTime").addClass("active");
		$scope.present = false;
		$scope.serviceTime = true;
		$scope.rules = false;
		$scope.editWin = false;
		//获取开放时间
		$scope.getData();
	}
	//修改入馆须知或者开放时间
	$scope.showEdit = function(libraryNote,type){
		$scope.currentType=type;
		if(type ===1){
			//入馆须知
			$scope.present = false; 
			$scope.editWin = true;
			$("#edui1").css("width","780px");
			$("#edui1_iframeholder").css("width","780px").css("height","400px");
			//$("#edui1_iframeholder").css("height","450px");
			ue.setContent(libraryNote.presentation);
		}else if(type == 2){
			//开放时间
			$scope.serviceTime = false;
			$scope.editWin = true;
			$("#edui1").css("width","780px");
			$("#edui1_iframeholder").css("width","780px").css("height","400px");
			//$("#edui1_iframeholder").css("height","450px");
			ue.setContent(libraryNote.serviceTime);
		}
	}
	//保存修改或者添加
	$scope.saveEdit = function(){
		if(ue.getContent()==""||ue.getContent()==null||ue.getContent()==undefined){
			alert("请填写内容");
		}
		var libraryNote = {
			"deptId":$rootScope.user.deptId,
			"schoolId":$rootScope.user.schoolId
		};
		if($scope.currentType == 1){
			libraryNote.presentation = ue.getContent();
			$scope.presentation = ue.getContent();
		}else if($scope.currentType == 2){
			libraryNote.serviceTime = ue.getContent();
			$scope.serviceNote = ue.getContent();
		}
		if($scope.preId==""||$scope.preId==null||$scope.preId==undefined){
			//id不存在，新添加
		}else{
			//id存在，修改
			libraryNote.id = $scope.preId;
		}
		$http.post(hostPath+"/library/manageLibraryNote",$.param(libraryNote)).success(function(response){
			if(response.status == 1){
				if($scope.currentType == 1){
					$scope.present = true; 
					$scope.editWin = false;
					$scope.libraryNote.presentation=libraryNote.presentation;
				}else if($scope.currentType == 2){
					$scope.serviceTime = true;
					$scope.editWin = false;
					$scope.libraryNote.serviceTime = libraryNote.serviceTime;
				}
			}
		});
	}
	//取消修改
	$scope.closeEdit = function(){
		if($scope.currentType == 1){
			$scope.present = true; 
			$scope.editWin = false;
		}else if($scope.currentType == 2){
			$scope.serviceTime = true;
			$scope.editWin = false;
		}
	}
	//显示规章制度
	$scope.showRules = function(){
		$("#libraryRules li").removeClass("active");
		$("#rules").addClass("active");
		$scope.present = false;
		$scope.serviceTime = false;
		$scope.rules = true;
		$scope.editWin = false;
		//获取本院的规章制度
		$scope.getRules();
	}
	//获取本院的规章制度
	$scope.getRules = function(){
		$http.post(hostPath+"/library/getRules",$.param({"deptId":$rootScope.user.deptId,"schoolId":$rootScope.user.schoolId})).success(function(response){
			$scope.rules = response.data;
		});
	}	
	// 添加规章制度窗
	$scope.winType =0;
	$scope.addRule = function(){
		$("#modalLabel").html("添加");
		$scope.details = false;
		$scope.winType =0;
		$scope.title="";
		$scope.content ="";
		$scope.publishUser = $rootScope.deptName;
		$("#ruleModal").modal({backdrop: 'static', keyboard: false});
	}
	//详情页
	$scope.details = false;
	$scope.showContent = function(rule){
		$("#modalLabel").html("详情");
		$scope.title=rule.title;
		$scope.content = rule.content;
		$scope.publishUser = rule.publishName;
		$scope.details = true;
		$("#ruleModal").modal({backdrop: 'static', keyboard: false});
	}
	//修改规章制度窗
	$scope.editRule = function(rule){
		$scope.details = false;
		$("#modalLabel").html("修改");
		$scope.winType =1;
		$scope.ruleId = rule.id;
		$scope.title=rule.title;
		$scope.publishUser = rule.publishName;
		$scope.content = rule.content;
		$("#ruleModal").modal({backdrop: 'static', keyboard: false});
	}
	// 保存添加或者修改
	$scope.saveRule = function(){
		var rule = {
			"title":$scope.title,
			"content":$scope.content,
			"deptId":$rootScope.user.deptId,
			"schoolId":$rootScope.user.schoolId,
			"publishUser":$rootScope.user.id,
			"publishName":$scope.publishUser,
			"publishDate":new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()
		}
		if($scope.winType == 1){
			rule.id = $scope.ruleId;
		}
		if($scope.content==""||$scope.content==undefined ||$scope.title==""||$scope.title==undefined){
			alert("请填写标题和内容！");
		}else{
			$http.post(hostPath+"/library/manageRule",$.param(rule)).success(function(response){
				if(response.status == 1){
					$("#ruleModal").modal("hide");
					$scope.getRules();
				}
			});
		}
	}
	//删除
	$scope.deleteRule = function(id){
		if(confirm("是否删除？")){
			$http.post(hostPath+"/library/deleteRule",$.param({"id":id})).success(function(response){
				if(response.status == 1){
					$scope.getRules();
				}
			});
		}
	}
	$scope.showPersent();
	
}).controller('consultController',function($rootScope,$scope,$http){
	//咨询
	$(function(){
		$("#navigation li").removeClass("active");
		$("#consult").addClass("active");
	})
	//分页的配置文件
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 0,  //总条数
        itemsPerPage: 10, //每页展示多少条数据
        pagesLength: 10,    
        perPageOptions: [5,10,15],   //可以选每一页展示多少
        onChange: function(){
        	$scope.getDeptConsult();
        }
    };
	//获取本部门的咨询
	$scope.getDeptConsult = function(){
		var searchConf = {
            "currentPage":$scope.paginationConf.currentPage-1,
            "itemsPerPage":$scope.paginationConf.itemsPerPage,
            "totalItems":$scope.paginationConf.totalItems,
            "schoolId":$rootScope.user.schoolId,
            "deptId":$rootScope.user.deptId
        };
		$http.post(hostPath+'/library/getDeptConsult',$.param(searchConf)).success(function(response){
			if(response.status == 1){
				$scope.paginationConf.totalItems = parseInt(response.msg);
				$scope.consults = response.data;
			}
		});
	}
	//回复提问
	$scope.editState = [];
	$scope.replyConsult = function(consult){
		if(consult.answer =="" || consult.answer ==null ||consult.answer == undefined){
			$scope.editState[consult.id] = true;
		}else{
			return;
		}
	}
	//保存回复
	$scope.saveReply= function(id){
		var consult ={
			"id":id,
			"answer":$("#reply"+id).val()
		};
		$http.post(hostPath+'/library/answerConsult',$.param(consult)).success(function(response){
			if(response.status == 1){
				$scope.editState[consult.id] = false;
				$scope.getDeptConsult();
			}
		});
	}
	//关闭回复框
	$scope.closeReply = function(id){
		$scope.editState[id] = false;
	}
	
}).filter("urlFilter",function(){
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
}).filter("imgSrcFilter",function(){ //图片路径过滤器
	return function(src){
		if(src=="" || src ==null || src == undefined){
			src=hostPath+"/images/tm-2.png";
		}else{
			src = hostPath+"/"+src;
		}
		return src;
	}
})

//删除图片 
function delImg(){
	$("#imgDiv").html('');
	$(".fileinput-remove").click();
	$("#file-upload-div").show();
}