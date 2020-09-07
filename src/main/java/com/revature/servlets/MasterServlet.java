package com.revature.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.java.controllers.AppDenCont;
import com.revature.java.controllers.LoginCont;
import com.revature.java.controllers.RequestCont;
import com.revature.java.controllers.ViewPastTicketsCont;
import com.revature.java.controllers.ViewRequestsCont;

public class MasterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static AppDenCont adCont = new AppDenCont();
	private static LoginCont lCont = new LoginCont();
	private static RequestCont rCont = new RequestCont();
	private static ViewPastTicketsCont vptCont = new ViewPastTicketsCont();
	private static ViewRequestsCont vrCont = new ViewRequestsCont();
	
	public MasterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(400);
		final String URI = req.getRequestURI().replace("/project0/", "");
		System.out.println(req.getSession().getAttribute("userId"));
		String[] portions = URI.split("/");
		System.out.println(Arrays.toString(portions));
		
		switch(portions[0]) {
		case "test":
			System.out.println(req.getSession());
			res.setStatus(200);
			res.getWriter().print("Gets to Master Servlet and reads 'test'");
			break;
		case "login":
			lCont.login(req, res);
			break;
		case "logout":
			HttpSession ses = req.getSession();
			if ((boolean)ses.getAttribute("loggedin") == true) {
				lCont.logout(req, res);
			}
			else {
				res.setStatus(401);
				res.getWriter().print("Must be logged in to logout");
			}
			break;
		case "appden":
			adCont.changeStatus(req,res);
			break;
		case "request":
			rCont.submitTicket(req,res);
			break;
		case "viewPast":
			vptCont.getTicks(req, res);
			break;
		case "viewAll":
			vrCont.getAll(req, res);
			break;
		//case "filterView":
			//break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
