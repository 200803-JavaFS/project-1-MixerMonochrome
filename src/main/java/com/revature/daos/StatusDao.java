package com.revature.daos;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReimStatus;
import com.revature.util.HiberUtil;

import java.util.List;



public class StatusDao {
	public StatusDao() {
		super();
	}
	
	public boolean insertStatus(ReimStatus s) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.save(s);
			t.commit();
			return true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean updateStatus(ReimStatus s) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.update(s);
			t.commit();
			return true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean deleteStatus(ReimStatus s) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.delete(s);
			t.commit();
			return true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public ReimStatus getTypeById(int id) {
		Session ses = HiberUtil.getSession();
		try {
			ReimStatus s = ses.get(ReimStatus.class, id);
			return s;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ReimStatus> findAllStatus() {
		Session ses = HiberUtil.getSession();
		try {
			List<ReimStatus> list = ses.createQuery("From ReimStatus",ReimStatus.class).list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
