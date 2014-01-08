package com.sembjtu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sembjtu.domain.User;
import com.sembjtu.service.UserService;
import com.sembjtu.web.BaseController;

/**
 * @author edc
 *
 */
@Controller
public class UserLoginController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login.do")
	public String userLogin(String username, String password){
		//User user = (User) request.getParameterMap();
		try {
		User user = userService.getLoginInfo(username, password);
		//user.setUsername(username);
		//user.setPassword(password);
			if(username.equals(user.getUsername())&&password.equals(user.getPassword()));
				System.out.println("is valid user!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Index";
	}
	
	@RequestMapping(value="home.do")
	public String returnHome(){
		return "Index";
	}
}
