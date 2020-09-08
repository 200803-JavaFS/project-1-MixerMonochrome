package com.revature.java.service;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ReimDao;
import com.revature.daos.StatusDao;
import com.revature.daos.UserDao;
import com.revature.java.controllers.AppDenCont;
import com.revature.models.AccStatDTO;
import com.revature.models.ReimStatus;
import com.revature.models.Reimbursement;

public class AppDenServices {
	private static final Logger log = LogManager.getLogger(AppDenServices.class);
	private static ReimDao redao = new ReimDao();
	private static StatusDao sdao = new StatusDao();
	private static UserDao udao = new UserDao();

	public boolean changeStatus(AccStatDTO a, int uId) {
		log.info("Enters changeStatus method in AppDenServices");
		Reimbursement r = redao.findById(a.aId);
		log.info(r + " Grabbed from database");
		if (r.getStatus().getStatusId() == 1) {
			log.info("Status Pending, move forward with update");
			ReimStatus s = sdao.getStatusById(a.sId);
			r.setStatus(s);
			r.setResolver(udao.findUserById(uId));
			r.setReimbReslvd(new Timestamp(System.currentTimeMillis()));
			log.info("Updating Ticket...");
			redao.updateTicket(r);
			log.info("Ticket updated");
			return true;
		} else {
			log.info("Status " + r.getStatus().getStatus() + ", cancel update");
			return false;
		}
	}
}
