package vincent.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JFrame;

import org.junit.Test;

import vincent.dao.AccountDao;
import vincent.dao.TimeDao;
import vincent.entity.User;
import vincent.entity.Vehicle;

public class DeBugTest extends JFrame {

	@Test
	public void test() throws Exception {
		File file = new File(
				"C:\\Users\\Administrator.ZGC-20130905TJJ\\Desktop\\vincent.dat");
		ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(file));
		List<Vehicle> list = (List<Vehicle>) objectInputStream.readObject();
		for (Vehicle vehicle : list) {
			System.out.println(vehicle.calcCharge());
			System.out.println(vehicle.getRegNumber());
		}
	}

	@Test
	public void testDatabase() {
		AccountDao accountSqlite = new AccountDao();
		User user = new User();
		user.setUsername("vincent");
		user.setPassword("admin");
		int insert = accountSqlite.insert(user);
		System.out.println(insert);
	}

	@Test
	public void testDatabase1() {
		AccountDao accountSqlite = new AccountDao();
		User user = new User();
		User user2 = user;
		user2.setUsername("vincent");
		user2.setPassword("admin");
		User select = accountSqlite.select(user2);
		System.out.println(select);
	}

	@Test
	public void TestTime() {
		TimeDao timeDao = new TimeDao();
		timeDao.update("timer", "1");
		timeDao.update("timer", "3");
	}

	@Test
	public void TestTime1() {
		TimeDao timeDao = new TimeDao();
		int select = timeDao.select("timer");
		System.out.println(select);
	}
}
