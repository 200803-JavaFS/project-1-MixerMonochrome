package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.Users;
import com.revature.util.HiberUtil;

import java.util.List;

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
		}
	}
	
	public boolean updateUser(Users u) {
		Session ses = HiberUtil.getSession();
		try {
			ses.merge(u);
			return true;
		}catch(Exception e){
			return false;
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
			Users u = (Users)ses.createQuery("From Users Where username = " + username).getSingleResult();
			return u;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Users> findAllUsers(){
		Session ses = HiberUtil.getSession();
		try {
			List<Users> list = ses.createQuery("From Users").list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
