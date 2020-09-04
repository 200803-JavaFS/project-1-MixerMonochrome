package com.revature.java.service;

import java.sql.Timestamp;

import com.revature.daos.ReimDao;
import com.revature.daos.TypeDao;
import com.revature.daos.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.TicketDTO;

public class RequestServices {
	private static UserDao udao = new UserDao();
	private static TypeDao tdao = new TypeDao();
	private static ReimDao redao = new ReimDao(); 
	public boolean submitTicket(TicketDTO t,int uid) {
		Reimbursement r = new Reimbursement();
		r.setAuthor(udao.findUserById(uid));
		r.setReimbamount(t.reimbAmnt);
		r.setReimbDesc(t.reimbDesc);
		r.setReimbRecpt(t.reimbRecpt);
		r.setType(tdao.getTypeById(t.typeId));
		r.setReimbSubbed(new Timestamp(System.currentTimeMillis()));
		if(redao.insertTicket(r)) {
			return true;
		}
		return false;
	}
}
