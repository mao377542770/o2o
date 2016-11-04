angular.module('employmentModule', []).controller('empNewsController',function($rootScope,$scope,$http){
	/*
	 *  公告控制器
	 */
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
		$http.post(hostPath+"/employment/getNewsList",$.param(searchConf)).success(function(response){
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
			"publishDate":new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate(),
			"publishName":$scope.nickName,
			"userId":$rootScope.user.id
		};
		if($scope.btnStatus == 0){
			url = hostPath+"/employment/addNews";
		}else if($scope.btnStatus == 1){
			if($scope.filePath==""||$scope.filePath==undefined){
				$scope.filePath = $scope.hasImg;
			}
			url = hostPath+"/employment/updateNews";
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
			$http.post(hostPath+"/employment/deleteNews",$.param({"newsId":id})).success(function(response){
				if(response.status == 1){
					$scope.getNewsList();
				}
			});
		}
	}
}).controller('recruitController',function($rootScope,$scope,$http){
	/*
	 *  招聘资讯
	 */
	UE.delEditor("editor");
	var ue = UE.getEditor('editor',{allowDivTransToP: false});
	$(function(){
		$("#navigation li").removeClass("active");
		$("#recruit").addClass("active");
	})

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
    
  //获取招聘资讯
	$scope.getNewsList = function(){
		var searchConf = {
            "currentPage":$scope.paginationConf.currentPage-1,
            "itemsPerPage":$scope.paginationConf.itemsPerPage,
            "totalItems":$scope.paginationConf.totalItems,
            "schoolId":$rootScope.user.schoolId
        };
		$http.post(hostPath+"/employment/getRecruitList",$.param(searchConf)).success(function(response){
			if(response.status ==1){
				$scope.paginationConf.totalItems = parseInt(response.msg);
				$scope.newsList = response.data;
			}
		});
	}
	$scope.btnStatus = 0;
    //添加窗口
	$scope.openAdd = function(){
		$("#winTitle").html("发布招聘资讯");
		$scope.btnStatus = 0;
		$scope.isDisabled = false;
		$scope.title =null;
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
			"publishUser":$rootScope.user.id,
			"schoolId":$rootScope.user.schoolId,
			"userName":$scope.nickName
		};
		if($scope.btnStatus == 0){
			url = hostPath+"/employment/addRecruitinfo";
		}else if($scope.btnStatus == 1){
			url = hostPath+"/employment/updateRecruitinfo";
			news.id = $scope.newId;
			news.publishDate = $scope.newsPublishDate;
		}
		news.newsImage = $scope.filePath;
		if($scope.title =="" ||$scope.title == null){
			alert("请填写标题");
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
		$("#winTitle").html("资讯详情");
		$scope.title = news.title;
		ue.setContent(news.content);
		ue.setDisabled();
		$scope.nickName = news.userName;
		$("#addNewsWin").modal({backdrop: 'static', keyboard: false});
	}
	//修改招聘资讯
	$scope.updateNews = function(news){
		$scope.hasImg= news.newsImage;
		$scope.btnStatus = 1;
		$scope.isDisabled = false;
		$scope.newId = news.id;
		$scope.newsPublishDate = news.publishDate;
		$("#winTitle").html("修改招聘资讯");
		$scope.title = news.title;
		ue.setContent(news.content);
		ue.setEnabled();
		$scope.nickName = news.userName;
		$("#addNewsWin").modal({backdrop: 'static', keyboard: false});
	}
	//删除招聘资讯
	$scope.deleteNews = function(id){
		if(confirm("确定要删除该公告吗？")){
			$http.post(hostPath+"/employment/deleteRecruitinfo",$.param({"id":id})).success(function(response){
				if(response.status == 1){
					$scope.getNewsList();
				}
			});
		}
	}
}).controller('workStoryController',function($rootScope,$scope,$http){
	/*
	 *  职场故事
	 */
	UE.delEditor("editor");
	var ue = UE.getEditor('editor',{allowDivTransToP: false});
	$(function(){
		$("#navigation li").removeClass("active");
		$("#workStory").addClass("active");
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
    
  //获取职场故事列表
	$scope.getNewsList = function(){
		var searchConf = {
            "currentPage":$scope.paginationConf.currentPage-1,
            "itemsPerPage":$scope.paginationConf.itemsPerPage,
            "totalItems":$scope.paginationConf.totalItems,
            "schoolId":$rootScope.user.schoolId
        };
		$http.post(hostPath+"/employment/getworkStoryList",$.param(searchConf)).success(function(response){
			if(response.status ==1){
				$scope.paginationConf.totalItems = parseInt(response.msg);
				$scope.newsList = response.data;
			}
		});
	}
	$scope.btnStatus = 0;
    //添加窗口
	$scope.openAdd = function(){
		$("#winTitle").html("发布职场故事");
		$scope.btnStatus = 0;
		$scope.isDisabled = false;
		$scope.title =null;
		$("#imgDiv").hide();
		$("#file-upload-div").show();
		$(".fileinput-remove").click();
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
			"userId":$rootScope.user.id
		};
		if($scope.btnStatus == 0){
			url = hostPath+"/employment/addWorkStory";
		}else if($scope.btnStatus == 1){
			if($scope.filePath==""||$scope.filePath==undefined){
				$scope.filePath = $scope.hasImg;
			}
			url = hostPath+"/employment/updateWorkstory";
			news.id = $scope.newId;
			news.publishDate = $scope.storyDate;
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
		$("#winTitle").html("职场故事详情");
		$scope.title = news.title;
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
	//修改职场故事
	$scope.updateNews = function(news){
		$scope.hasImg= news.newsImage;
		$scope.btnStatus = 1;
		$scope.isDisabled = false;
		$scope.newId = news.id;
		$scope.storyDate = news.publishDate;
		$("#winTitle").html("修改职场故事");
		$scope.title = news.title;
		ue.setContent(news.content);
		ue.setEnabled();
		$scope.nickName = news.publishUser;
		//alert(news.newsImage);
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
			$http.post(hostPath+"/employment/deleteWorkstory",$.param({"id":id})).success(function(response){
				if(response.status == 1){
					$scope.getNewsList();
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
})

//删除图片
function delImg(){
	$("#imgDiv").html('');
	$(".fileinput-remove").click();
	$("#file-upload-div").show();
}