package com.sina.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sina.dao.BaseDAO;
import com.sina.entity.UserBean;
import com.sina.service.LoginService;


@Service("LoginService")//暴露出自定义名字的service bean
@Transactional//事务注解为transactional bean
public class LoginServiceImpl implements  LoginService{
	//自动注入
	//@Resource
	//private BaseDAO<UserBean> uDAO;
	@Resource
	private BaseDAO<Map<String,Object>> mDAO;
	
	
	@Override
	public Map<String, Object> getByUsernameAndpassword(String username,
			String password) {
		String sql = "select id,name from tb_user where id=1";
		Map<String, Object> mapUser = mDAO.getBySQL1(sql);
		return mapUser;
	}

}
