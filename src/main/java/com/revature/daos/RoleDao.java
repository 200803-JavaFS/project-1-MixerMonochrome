package com.revature.daos;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.UserRole;
import com.revature.util.HiberUtil;

import java.util.List;

public class RoleDao {
	public RoleDao() {
		super();
	}
	
	public boolean insertRole(UserRole r) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.save(r);
			t.commit();
			return true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
		finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean updateRole(UserRole r) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.merge(r);
			t.commit();
			return true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
		finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean deleteRole(UserRole r) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.delete(r);
			t.commit();
			return true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
		finally {
			HiberUtil.closeSes();
		}
	}
	
	public UserRole getRoleById(int i) {
		Session ses = HiberUtil.getSession();
		try {
			UserRole r = ses.get(UserRole.class, i);
			return r;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<UserRole> findAllRoles(){
		Session ses = HiberUtil.getSession();
		try {
			List<UserRole> list = ses.createQuery("From UserRole", UserRole.class).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
