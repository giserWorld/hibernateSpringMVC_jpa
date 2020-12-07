$(function(){
	
	//退出系统
	$("#tcxt").click(function(){
		window.location.href="http://localhost:8082/hibernateSpringMVC/login.html";
	});
	//登录
	$("#login").click(function(){
		var userid=$("#username").val();
		var password=$("#password").val();
		var url="http://localhost:8082/hibernateSpringMVC/login.action";
		var param={
			userid:userid,
			password:password
		};
		var requestType="POST";
		jqueryUtilityFun.ajax_jquery_form(url,param,requestType,function(result){
			var param=result.param;//参数对象
			var responseData=result.returnData;//请求响应数据
			if(responseData.code==0){
				window.location.href="http://localhost:8082/hibernateSpringMVC/main/goMessage.html";
			}
			else{
				alert("登录失败！");
			}
		});
	});
});