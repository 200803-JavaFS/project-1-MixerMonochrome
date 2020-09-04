package com.revature.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.ViewPastServices;
import com.revature.models.Reimbursement;

public class ViewPastTicketsCont {
	public static ViewPastServices vpS = new ViewPastServices();
	public static ObjectMapper om = new ObjectMapper();
	
	public void getTicks(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession ses = req.getSession();
		Integer id = (Integer) ses.getAttribute("userId");
		List<Reimbursement> reims = vpS.grabPast(id);
		String json = om.writeValueAsString(reims);
		System.out.println(json);
		res.getWriter().println(json);
	}

}
