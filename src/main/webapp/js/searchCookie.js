function setCookie(userArr){
	//1 天过期
	var userJson = JSON.stringify(userArr);
	$.cookie("searchInfo",userJson,{expires:1,path:"/"});  
}



//这里的arr 是一个对象
function getCookie(){
	var jsonStr = $.cookie("searchInfo");
	if(typeof(jsonStr) == 'undefined'){
		return null;
	}
	return $.parseJSON(jsonStr);
}
//清除cookie  
function clearCookie() {  
    $.cookie("searchInfo",null);
}  

