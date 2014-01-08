package com.sembjtu.service;

import com.sembjtu.domain.User;

public interface UserService {
	/**
	 * 根据用户名和密码登录
	 */
	User getLoginInfo(String username, String password);
	
	void SetUserInfo(User user);
	
	int isValidUser(String username, String password);
}
