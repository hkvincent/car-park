package vincent.service.impl;

import vincent.dao.AccountDao;
import vincent.entity.User;
import vincent.service.IAccountSerivce;

/**
 * the BLL implement for user login or register
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class AccountService implements IAccountSerivce {
	AccountDao accountSqlite = new AccountDao();

	@Override
	public boolean login(User user) {
		User result = accountSqlite.select(user);
		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean register(User user) {
		int insert = accountSqlite.insert(user);
		if (insert > 0) {
			return true;
		}
		return false;
	}

}
