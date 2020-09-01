package com.revature.java.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.AppDenServices;
import com.revature.java.service.LoginServices;
import com.revature.models.AccStatDTO;

public class AppDenCont {
	

	private static AppDenServices ads = new AppDenServices();
	private static ObjectMapper om = new ObjectMapper();

	public void changeStatus(HttpServletRequest req, HttpServletResponse res) throws IOException{
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		
		AccStatDTO a = om.readValue(body, AccStatDTO.class);
		
		if(ads.changeStatus(a)) {
			res.setStatus(200);
			res.getWriter().println("Change Successful");
		}
		else {
			res.setStatus(401);
			
		}
	}

}
