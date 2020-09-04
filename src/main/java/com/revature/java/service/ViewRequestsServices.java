package com.revature.java.service;

import java.util.List;

import com.revature.daos.ReimDao;
import com.revature.models.Reimbursement;

public class ViewRequestsServices {
	private static ReimDao redao = new ReimDao();
	public List<Reimbursement> grabAll() {
		return redao.findAll();
	}

}
