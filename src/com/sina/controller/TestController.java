package com.sina.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sina.service.TestService;

@Controller//控制层
public class TestController {

	@Resource//java自动注入bean
	private TestService testService;
	
	//测试服务
	@RequestMapping("/test.action")
	@ResponseBody 
	public Object testController(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mgs","测试成功！");
		map.put("code",0);
		map.put("data",null);
		String userid=request.getParameter("userid");
		Map<String, Object> usermap = testService.getByUserById(userid);
		if(usermap!=null){
			map.put("data",usermap);
		}
		return map;
	}
}
