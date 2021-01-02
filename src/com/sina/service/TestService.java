package com.sina.service;

import java.util.Map;

import com.sina.entity.UserBean;

/**
 * TestService
 * @author 
 *
 */

public interface TestService {
	
	 //通过id查找用户
     public Map<String,Object> getByUserById(String userid);
}
