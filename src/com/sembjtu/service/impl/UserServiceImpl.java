/**
 * 
 */
package com.sembjtu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sembjtu.dao.BaseDao;
import com.sembjtu.domain.User;
import com.sembjtu.service.UserService;

/**
 * @author edc
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/* (non-Javadoc)
	 * @see com.sembjtu.service.UserService#getLoginInfo()
	 */
	@Autowired
	public BaseDao baseDao;
	//static String listUser = "select * from test_table";
	static String addUser = "insert into test_table(username, password) values(:userName, :password)";
	//static String isUser = "select * from "
	
	public User getLoginInfo(String username, String password) {
		String listUser = "select * from test_table where username = '" + username + "' and password = '" + password + "'";
		User user = baseDao.getObject(listUser, User.class, null);	
		return user;
	}

	public void SetUserInfo(User user) {
		// TODO Auto-generated method stub
		
	}
	
	public int isValidUser(String username, String password) {
		// TODO Auto-generated method stub
		String isUser = "select * from test_table where username="+username+" and password="+password;
		int isExist = baseDao.isExist(isUser, new Object[]{username, password});
		return isExist;
	}

}
