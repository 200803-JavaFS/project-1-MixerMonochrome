package com.revature.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.ViewRequestsServices;
import com.revature.models.Reimbursement;

public class ViewRequestsCont {
	public static ViewRequestsServices vrs = new ViewRequestsServices();
	public static ObjectMapper om = new ObjectMapper();
	
	public void getAll(HttpServletRequest req, HttpServletResponse res) throws IOException{
		List<Reimbursement> reims = vrs.grabAll();
		res.setStatus(200);
		res.getWriter().println(om.writeValueAsString(reims));
	}
}
