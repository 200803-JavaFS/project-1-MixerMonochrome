package com.revature.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.java.service.ViewPastServices;
import com.revature.models.Reimbursement;
import com.revature.models.TicketDTO;

public class ViewPastTicketsCont {
	public static ViewPastServices vpS = new ViewPastServices();
	public static ObjectMapper om = new ObjectMapper();
	
	public void getTicks(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession ses = req.getSession();
		Integer id = (Integer) ses.getAttribute("userId");
		res.setStatus(200);
		List<Reimbursement> reims = vpS.grabPast(id);
		List<TicketDTO> ticks = new ArrayList<>();
		for(Reimbursement r : reims) {
			TicketDTO t = new TicketDTO();
			t.author = r.getAuthor().getUsername();
			t.reimbAmnt = r.getReimbamount();
			t.reimbId = r.getReimbId();
			if(r.getResolver()!= null) {
				t.resolver = r.getResolver().getUsername();
			}
			else {
				t.resolver = null;
			}
			t.reimbDesc = r.getReimbDesc();
			t.reimbSubbed = r.getReimbSubbed().toString();
			if(r.getReimbReslvd() != null) {
				t.reimbResolved = r.getReimbReslvd().toString();
			}
			else {
				t.reimbResolved = null;
			}
			if(r.getReimbRecpt() != null) {
				t.reimbRecpt = r.getReimbRecpt();
			}
			else {
				t.reimbRecpt = null;
			}
			t.status = r.getStatus().getStatus();
			t.type = r.getType().getType();
			ticks.add(t);
		}
		System.out.println(ticks.toString());
		res.getWriter().println(om.writeValueAsString(ticks));
	}

}
