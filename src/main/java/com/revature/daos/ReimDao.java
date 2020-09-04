package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.util.HiberUtil;

import java.util.List;

import org.hibernate.Transaction;


public class ReimDao {
	public ReimDao(){
		super();
	}
	
	public boolean insertTicket(Reimbursement tick) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.save(tick);
			t.commit();
			return true;
		}catch(Exception e){
			t.rollback();
			e.printStackTrace();
			return false;
		}
		finally {
			HiberUtil.closeSes();
		}
		
	}
	
	public boolean updateTicket(Reimbursement tick) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.merge(tick);
			t.commit();
			return true;
		}catch(Exception e){
			t.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean deleteTicket(Reimbursement tick) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.delete(tick);
			t.commit();
			return true;
		}catch(Exception e){
			t.rollback();
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
		List<Reimbursement> tickets = ses.createQuery("from reimbursement",Reimbursement.class).list();
		return tickets;
	}
}
