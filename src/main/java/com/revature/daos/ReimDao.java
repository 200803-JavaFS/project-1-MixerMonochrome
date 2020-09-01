package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.util.HiberUtil;

import java.util.List;


public class ReimDao {
	public ReimDao(){
		super();
	}
	
	public boolean insertTicket(Reimbursement tick) {
		Session ses = HiberUtil.getSession();
		try {
			ses.save(tick);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			HiberUtil.closeSes();
		}
		
	}
	
	public boolean updateTicket(Reimbursement tick) {
		Session ses = HiberUtil.getSession();
		try {
			ses.merge(tick);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean deleteTicket(Reimbursement tick) {
		Session ses = HiberUtil.getSession();
		try {
			ses.delete(tick);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public Reimbursement findById(int id) {
		Session ses = HiberUtil.getSession();
		try {
			Reimbursement ticket = ses.get(Reimbursement.class, id);
			return ticket;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Reimbursement> findAll(){
		Session ses = HiberUtil.getSession();
		List<Reimbursement> tickets = ses.createQuery("from reimbursement").list();
		return tickets;
	}
}
