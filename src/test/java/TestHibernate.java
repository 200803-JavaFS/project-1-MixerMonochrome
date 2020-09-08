import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.ReimDao;
import com.revature.daos.RoleDao;
import com.revature.daos.StatusDao;
import com.revature.daos.TypeDao;
import com.revature.daos.UserDao;
import com.revature.java.service.AppDenServices;
import com.revature.java.service.LoginServices;
import com.revature.java.service.RequestServices;
import com.revature.models.AccStatDTO;
import com.revature.models.LoginDTO;
import com.revature.models.ReimStatus;
import com.revature.models.ReimType;
import com.revature.models.Reimbursement;
import com.revature.models.TicketDTO;
import com.revature.models.UserRole;
import com.revature.models.Users;

public class TestHibernate {
	private static RoleDao rDao = new RoleDao();
	private static UserDao uDao = new UserDao();
	private static ReimDao reDao = new ReimDao();
	private static StatusDao sDao = new StatusDao();
	private static TypeDao tDao = new TypeDao();
	private static RequestServices rs = new RequestServices();
	private static AppDenServices ads = new AppDenServices();
	private static LoginServices ls = new LoginServices();
	private static Users e;
	private static Reimbursement r;
	private static ReimStatus pend;
	private static ReimType lodg;
	
	@BeforeClass
	public static void setupAccts() {
		e = uDao.findUserById(1);
		pend = sDao.getStatusById(1);
		lodg = tDao.getTypeById(1);
		r = new Reimbursement();
	}
	
	@Before
	public void resetAcct() {
		r.setReimbId(0);
		r.setReimbamount(0);
		r.setReimbDesc("");
		r.setAuthor(null);
		r.setReimbRecpt(null);
		r.setReimbReslvd(null);
		r.setResolver(null);
		r.setStatus(null);
		r.setType(null);
	}
	
	@Test
	public void testConnections() {
		List<UserRole> rl = rDao.findAllRoles();
		assertTrue(rl != null);
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
	public void testRequest() {
		TicketDTO t = new TicketDTO();
		t.reimbAmnt = 400;
		t.reimbDesc = "TESTING DESC";
		t.reimbRecpt = null;
		t.typeId = 3;
		assertTrue(rs.submitTicket(t, 1));
		List<Reimbursement> rl = reDao.findAll();
		for (Reimbursement re : rl) {
			if (re.getReimbDesc().equals("TESTING DESC")) {
				r = re;
				break;
			}
		}
		t.typeId = 55;
		assertTrue(r.getReimbId() != 0);
		assertFalse(rs.submitTicket(t, 1));
		assertTrue(reDao.deleteTicket(r));
	}
	
	@Test
	public void testAppDenCont() {
		r = new Reimbursement();
		System.out.println(r.getReimbId());
		r.setReimbamount(100);
		r.setReimbDesc("TESTER");
		r.setAuthor(e);
		r.setReimbSubbed(new Timestamp(System.currentTimeMillis()));
		r.setReimbRecpt(null);
		r.setReimbReslvd(null);
		r.setResolver(null);
		r.setStatus(pend);
		r.setType(lodg);
		reDao.insertTicket(r);
		AccStatDTO ac = new AccStatDTO();
		List<Reimbursement> rl = reDao.findAll();
		for(Reimbursement re :rl) {
			if(re.getReimbDesc().equals("TESTER")) {
				ac.aId = re.getReimbId();
				break;
			}
		}
		ac.sId = 2;
		assertTrue(ads.changeStatus(ac, 2));
		assertFalse(ads.changeStatus(ac, 2));
		assertTrue(reDao.deleteTicket(reDao.findById(ac.aId)));
	}
	
	@Test
	public void testLogin() {
		LoginDTO l = new LoginDTO();
		l.username = "dummy";
		l.password = "woohoo";
		assertTrue(ls.login(l) == null);
		l.password = "wooHoo";
		assertTrue(ls.login(l) == e);
		l.username = "Boom";
		assertTrue(ls.login(l) == null);
	}
	
}
