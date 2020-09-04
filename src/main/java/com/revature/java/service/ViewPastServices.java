package com.revature.java.service;

import java.util.List;

import com.revature.daos.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

public class ViewPastServices {
	UserDao uDao = new UserDao();
	public List<Reimbursement> grabPast(int uId) {
		Users u = uDao.findUserById(uId);
		if(u != null) {
			return u.getReimbursements();
		}
		else {
			return null;
		}
	}
}
