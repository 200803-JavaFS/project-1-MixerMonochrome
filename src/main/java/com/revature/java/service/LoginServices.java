package com.revature.java.service;

import com.revature.daos.UserDao;
import com.revature.models.LoginDTO;

public class LoginServices {
	UserDao udao = new UserDao();
	public boolean login(LoginDTO l){
		String username = l.username;
		Integer hashed = l.password.hashCode() * username.hashCode();
		String password = hashed.toString();
		if(udao.checkPass(username,password)) {
			return true;
		}
		return false;
	}
}
