package com.revature.daos;
import org.hibernate.Session;

import com.revature.models.ReimStatus;
import com.revature.util.HiberUtil;

import java.util.List;



public class StatusDao {
	public StatusDao() {
		super();
	}
	
	public boolean insertStatus(ReimStatus s) {
		Session ses = HiberUtil.getSession();
		try {
			ses.save(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateStatus(ReimStatus s) {
		Session ses = HiberUtil.getSession();
		try {
			ses.update(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteStatus(ReimStatus s) {
		Session ses = HiberUtil.getSession();
		try {
			ses.delete(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
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
			List<ReimStatus> list = ses.createQuery("From ReimStatus").list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
