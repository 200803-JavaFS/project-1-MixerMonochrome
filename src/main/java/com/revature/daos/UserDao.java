package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.Users;
import com.revature.util.HiberUtil;

import java.util.List;

import org.hibernate.Transaction;

public class UserDao {
	public UserDao(){
		super();
	}
	
	public boolean insertUser(Users u) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.save(u);
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
	
	public boolean updateUser(Users u) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.merge(u);
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
	
	public boolean deleteUser(Users u) {
		Session ses = HiberUtil.getSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.delete(u);
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
	
	public Users findUserById(int id) {
		Session ses = HiberUtil.getSession();
			Users u = ses.get(Users.class,id);
			return u;
	}
	
	public Users findUserByUsername(String username) {
		Session ses = HiberUtil.getSession();
		try {
			Users u = ses.createQuery("From Users Where username = '" + username + "'",Users.class).getSingleResult();
			return u;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Users> findAllUsers(){
		Session ses = HiberUtil.getSession();
		try {
			List<Users> list = ses.createQuery("From Users",Users.class).list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean checkPass(String username, String password) {
		Session ses = HiberUtil.getSession();
		try {
			Users u = findUserByUsername(username);
	        String pass = ses.createNativeQuery("SELECT psswrd FROM users " +
	        	"where user_id = " +
	        	u.getUserId())
	            .getSingleResult().toString();
	           if(pass.equals(password)) {
	        	   return true;
	           }
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
