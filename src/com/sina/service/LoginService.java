package com.sina.service;

import java.util.Map;

/**
 * LoginService
 * @author zzy
 *
 */

public interface LoginService {
	
	
	/**
	 * 通过用户名与密码查询实体对象
	 * @param w_username
	 * @param password
	 * @return
	 */
     public Map<String, Object> getByUsernameAndpassword(String username,String password,String path);
     
}
