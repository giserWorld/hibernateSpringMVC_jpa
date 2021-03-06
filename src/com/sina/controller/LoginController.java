package com.sina.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sina.service.LoginService;

@Controller//控制层
public class LoginController {

	@Resource//java自动注入bean
	private LoginService LoginService;//用户登录服务
	
	//登录用户
	@RequestMapping("/login.action")
	@ResponseBody 
	public Object loginController(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mgs","获取数据成功！");
		map.put("code",0);
		map.put("data",null);
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//查询登录用户
		Map<String, Object> usermap = LoginService.getByUsernameAndpassword(username, password);
		if(usermap!=null){
			map.put("data",usermap);
		}
		return map;
	}
}
