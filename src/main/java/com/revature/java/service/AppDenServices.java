package com.revature.java.service;

import java.sql.Timestamp;

import com.revature.daos.ReimDao;
import com.revature.daos.StatusDao;
import com.revature.daos.UserDao;
import com.revature.models.AccStatDTO;
import com.revature.models.ReimStatus;
import com.revature.models.Reimbursement;

public class AppDenServices {
	private static ReimDao redao = new ReimDao();
	private static StatusDao sdao = new StatusDao();
	private static UserDao udao = new UserDao();
	
	public boolean changeStatus(AccStatDTO a, int uId) {
		Reimbursement r = redao.findById(a.aId);
		ReimStatus s = sdao.getTypeById(a.sId);
		r.setStatus(s);
		r.setResolver(udao.findUserById(uId));
		r.setReimbReslvd(new Timestamp(System.currentTimeMillis()));
		redao.updateTicket(r);
		return false;
	}
}
