package com.revature.daos;
import org.hibernate.Session;

import com.revature.models.ReimType;
import com.revature.util.HiberUtil;

import java.util.List;

import org.hibernate.Transaction;

public class TypeDao {
	public TypeDao() {
		super();
	}
	
	public boolean insertType(ReimType t) {
		Session ses = HiberUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.save(t);
			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean updateType(ReimType t) {
		Session ses = HiberUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.update(t);
			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean deleteType(ReimType t) {
		Session ses = HiberUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.delete(t);
			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public ReimType getTypeById(int id) {
		Session ses = HiberUtil.getSession();
		try {
			ReimType t = ses.get(ReimType.class, id);
			return t;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ReimType> findAllTypes() {
		Session ses = HiberUtil.getSession();
		try {
			List<ReimType> list = ses.createQuery("From ReimType",ReimType.class).list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
