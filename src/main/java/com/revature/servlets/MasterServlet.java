package com.revature.servlets;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private static final Logger log = LogManager.getLogger(MasterServlet.class);
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
		log.info("Enters Master Servlet");
		res.setContentType("application/json");
		res.setStatus(400);
		final String URI = req.getRequestURI().replace("/project0/", "");
		HttpSession ses = req.getSession(false);
		String[] portions = URI.split("/");
		log.info(Arrays.toString(portions));
		//Switch statement to call controllers based on URI
		switch(portions[0]) {
		case "test":
			log.info("Enters Test Case of Master Servlet");
			System.out.println(req.getSession());
			res.setStatus(200);
			res.getWriter().print("Gets to Master Servlet and reads 'test'");
			break;
		case "login":
			log.info("Enters login case of master servlet");
			lCont.login(req, res);
			break;
		case "logout":
			log.info("Enters logout case of master servlet");
			if ((boolean)ses.getAttribute("loggedin") == true) {
				lCont.logout(req, res);
			}
			else {
				log.info("No user to log out");
				res.setStatus(401);
				res.getWriter().print("Must be logged in to logout");
			}
			break;
		case "appden":
			log.info("Enters appden case of Master Servlet");
			adCont.changeStatus(req,res);
			break;
		case "request":
			log.info("Enters request case of Master Servlet");
			rCont.submitTicket(req,res);
			break;
		case "viewPast":
			log.info("Enters viewPast case of Master Servlet");
			if (ses != null && (boolean)ses.getAttribute("loggedin") == true && (Integer)ses.getAttribute("userRole") == 1) {
				vptCont.getTicks(req, res);
			}else {
				log.info("User is either not logged in or is not an Employee");
				res.setStatus(401);
				res.getWriter().print("Must be logged in as Employee.");
			}
			break;
		case "viewAll":
			log.info("Enters viewAll case of Master Servlet");
			if (ses != null && (boolean)ses.getAttribute("loggedin") == true && (Integer)ses.getAttribute("userRole") == 2) {
				vrCont.getAll(req, res);
			}else {
				log.info("User is either not logged in or is not a Financial Mangager");
				res.setStatus(401);
				res.getWriter().print("Must be logged in as Financial Manager.");
			}
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
