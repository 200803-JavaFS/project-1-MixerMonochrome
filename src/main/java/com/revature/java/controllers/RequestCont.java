package com.revature.java.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.RequestServices;
import com.revature.models.TicketDTO;

public class RequestCont {
	public static RequestServices rs = new RequestServices();
	public static ObjectMapper om = new ObjectMapper();

	public void submitTicket(HttpServletRequest req, HttpServletResponse res) throws IOException{
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		System.out.println(body);
		
		TicketDTO t = om.readValue(body, TicketDTO.class);
		Integer uid = (Integer) req.getSession().getAttribute("userId");
		if(rs.submitTicket(t,uid.intValue())) {
			res.setStatus(200);
		}
		else {
			res.setStatus(401);
		}
	}

}
