package com.revature.daos;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StringType;

import com.revature.models.Users;
import com.revature.util.HiberUtil;

import java.util.List;
import java.util.Map;

public class UserDao {
	public UserDao(){
		super();
	}
	
	public boolean insertUser(Users u) {
		Session ses = HiberUtil.getSession();
		try {
			ses.save(u);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean updateUser(Users u) {
		Session ses = HiberUtil.getSession();
		try {
			ses.merge(u);
			return true;
		}catch(Exception e){
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public boolean deleteUser(Users u) {
		Session ses = HiberUtil.getSession();
		try {
			ses.delete(u);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			HiberUtil.closeSes();
		}
	}
	
	public Users findUserById(int id) {
		Session ses = HiberUtil.getSession();
		try {
			Users u = ses.get(Users.class,id);
			return u;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
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
	        List<String> sql = ses.createNativeQuery("SELECT psswrd FROM users " +
	        	"where user_id = " +
	        	u.getUserId(),String.class)
	            .list();
	           String pass = sql.get(0).toString();
	           if(pass.equals(password)) {
	        	   System.out.println("passwords match!");
	        	   return true;
	           }
	           System.out.println("passwords don't match");
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
