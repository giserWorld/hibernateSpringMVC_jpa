package com.sina.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sina.dao.BaseDAO;
import com.sina.entity.UserBean;
import com.sina.service.TestService;


@Service("TestService")//暴露出自定义名字的service bean
@Transactional//事务注解为transactional bean
public class TestServiceImpl implements TestService {
	
	//自动注入
	@Resource
	private BaseDAO<UserBean> uDAO;
	@Resource
	private BaseDAO<Map<String, Object>> mDAO;
	
	
	@Override
	public Map<String, Object> getByUsernameAndpassword(String username, String password,String path) {
		String sql = "select * from tb_user where userid='1'";
		Map<String, Object> user = mDAO.getBySQL1(sql);
		return user;
	}
}
