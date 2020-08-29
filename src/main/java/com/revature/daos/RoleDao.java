package com.revature.daos;
import org.hibernate.Session;

import com.revature.models.UserRole;
import com.revature.util.HiberUtil;

import java.util.List;

public class RoleDao {
	public RoleDao() {
		super();
	}
	
	public boolean insertRole(UserRole r) {
		Session ses = HiberUtil.getSession();
		try {
			ses.save(r);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateRole(UserRole r) {
		Session ses = HiberUtil.getSession();
		try {
			ses.merge(r);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteRole(UserRole r) {
		Session ses = HiberUtil.getSession();
		try {
			ses.delete(r);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
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
			List<UserRole> list = ses.createQuery("From UserRole").list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
