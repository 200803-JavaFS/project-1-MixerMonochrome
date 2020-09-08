package com.revature.java.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.AppDenServices;
import com.revature.java.service.LoginServices;
import com.revature.models.AccStatDTO;
import com.revature.servlets.MasterServlet;

public class AppDenCont {
	
	private static final Logger log = LogManager.getLogger(AppDenCont.class);
	private static AppDenServices ads = new AppDenServices();
	private static ObjectMapper om = new ObjectMapper();

	public void changeStatus(HttpServletRequest req, HttpServletResponse res) throws IOException{
		log.info("Enters changeStatus method in AppDenCont");
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		
		AccStatDTO a = om.readValue(body, AccStatDTO.class);
		log.info("Mapped json to AccStatDTO : " + a);
		HttpSession ses = req.getSession();
		Integer uid = (Integer)ses.getAttribute("userId");
		if(ads.changeStatus(a, uid.intValue())) {
			log.info("Succeeded in changing status of Account : " + a.aId);
			res.setStatus(200);
			res.getWriter().println("Change Successful");
		}
		else {
			log.info("Account : " + a.aId + " not changed, status was not Pending");
			res.setStatus(401);
			res.getWriter().println("Account is not open for updating");
		}
		log.info("Exiting AppDenCont");
	}

}
