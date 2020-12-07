package com.sina.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//用户登录
public class LoginController {

	//@Resource//java自动注入bean
	//private LoginService loginService;
	@RequestMapping("login.action")
	@ResponseBody 
	public Object login(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		if(userid.equals("1")&&password.equals("123")){
			map.put("mgs","登录成功！");
			map.put("code",0);
			map.put("data","success");
		}
		else{
			map.put("mgs","账号或密码！");
			map.put("code",1);
			map.put("data","faill");
		}
		return map;
	}
}
