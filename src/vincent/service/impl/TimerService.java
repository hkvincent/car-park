package vincent.service.impl;

import vincent.dao.TimeDao;
import vincent.service.ITimerService;

/**
 * the timer implement
 * 
 * @author vincent @date 2017-4-28
 * @version 1.0.0
 */
public class TimerService implements ITimerService {
	TimeDao timerDao = new TimeDao();

	@Override
	public int find(String name) {
		int select = timerDao.select(name);
		return select;
	}

	@Override
	public boolean edit(String name, String value) {
		int update = timerDao.update(name, value);
		if (update > 0) {
			return true;
		}
		return false;
	}
}
