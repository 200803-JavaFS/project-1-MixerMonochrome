package com.revature.java.service;

import com.revature.daos.UserDao;
import com.revature.models.LoginDTO;
import com.revature.models.Users;

public class LoginServices {
	UserDao udao = new UserDao();
	public Users login(LoginDTO l){
		String username = l.username;
		Integer hashed = l.password.hashCode() * username.hashCode();
		String password = hashed.toString();
		if(udao.checkPass(username,password)) {
			System.out.println("passwords match!");
			return udao.findUserByUsername(username);
		}
		System.out.println("passwords don't match");
		return null;
	}
}
