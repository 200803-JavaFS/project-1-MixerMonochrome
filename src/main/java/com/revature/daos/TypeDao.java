package com.revature.daos;
import org.hibernate.Session;

import com.revature.models.ReimType;
import com.revature.util.HiberUtil;

import java.util.List;

public class TypeDao {
	public TypeDao() {
		super();
	}
	
	public boolean insertType(ReimType t) {
		Session ses = HiberUtil.getSession();
		try {
			ses.save(t);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateType(ReimType t) {
		Session ses = HiberUtil.getSession();
		try {
			ses.update(t);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteType(ReimType t) {
		Session ses = HiberUtil.getSession();
		try {
			ses.delete(t);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
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
			List<ReimType> list = ses.createQuery("From ReimType").list();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
