import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.revature.daos.RoleDao;
import com.revature.daos.UserDao;
import com.revature.daos.ReimDao;
import com.revature.daos.StatusDao;
import com.revature.daos.TypeDao;

import com.revature.models.Users;
import com.revature.util.HiberUtil;
import com.revature.models.ReimStatus;
import com.revature.models.ReimType;
import com.revature.models.Reimbursement;
import com.revature.models.UserRole;

public class TestHibernate {
	RoleDao rDao = new RoleDao();
	UserDao uDao = new UserDao();
	ReimDao reDao = new ReimDao();
	StatusDao sDao = new StatusDao();
	TypeDao tDao = new TypeDao();

	@Test
	public void testConnections() {
		List<UserRole> rl = rDao.findAllRoles();
		assertTrue(rl != null);
		assertTrue(rl.size() == 2);
		List<Users> ul = uDao.findAllUsers();
		assertTrue(ul != null);
		List<Reimbursement> rel = reDao.findAll();
		assertTrue(rel != null);
		List<ReimStatus> sl = sDao.findAllStatus();
		assertTrue(sl != null);
		List<ReimType> tl = tDao.findAllTypes();
		assertTrue(tl != null);
	}
	
	@Test
	public void testInsert() {
		Reimbursement re = new Reimbursement();
		re.setAuthor(uDao.findUserById(1));
		re.setReimbamount(100);
		re.setReimbDesc("Gas Money $$");
		re.setReimbSubbed(new Timestamp(System.currentTimeMillis()));
		re.setStatus(sDao.getTypeById(1));
		re.setType(tDao.getTypeById(2));
		reDao.insertTicket(re);
	}
}
