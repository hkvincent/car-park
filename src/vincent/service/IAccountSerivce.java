package vincent.service;

import vincent.entity.User;

/**
 * the account BLL interface
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public interface IAccountSerivce {
	
	public boolean login(User user);
	public boolean register(User user);

}
