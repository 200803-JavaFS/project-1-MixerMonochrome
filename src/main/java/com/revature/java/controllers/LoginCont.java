package com.revature.java.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.LoginServices;
import com.revature.models.LoginDTO;
import com.revature.models.Users;

public class LoginCont{
	private static LoginServices ls = new LoginServices();
	private static ObjectMapper om = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException{
					BufferedReader reader = req.getReader();

					StringBuilder sb = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						sb.append(line);
						line = reader.readLine();
					}

					String body = new String(sb);
					System.out.println(body);

					LoginDTO l = om.readValue(body, LoginDTO.class);
					Users u = ls.login(l);
					if (u != null) {
						HttpSession ses = req.getSession();
						ses.setAttribute("userId", u.getUserId());
						ses.setAttribute("loggedin", true);
						ses.setAttribute("userRole", u.getUserRole().getRoleId());
						res.setStatus(200);
						String json = om.writeValueAsString(u.getUserRole().getRoleId());
						System.out.println(json);
						res.getWriter().println(json);
					} else {
						HttpSession ses = req.getSession(false);
						if (ses != null) {
							ses.invalidate();
						}
						res.setStatus(401);
						res.getWriter().println("Login failed");
					}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession ses = req.getSession(false);

		if (ses != null) {
			ses.invalidate();
			res.setStatus(200);
			System.out.println(req.getSession(false));
			res.getWriter().println("Logged out! Thank you :)");
		} else {
			res.setStatus(400);
			res.getWriter().println("You're not logged in?");
		}
	}
}
