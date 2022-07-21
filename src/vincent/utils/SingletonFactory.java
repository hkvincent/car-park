package vincent.utils;

import vincent.view.CarPark;
import vincent.view.EnterTable;

/**
 * the factory class to create EnterTable object, the purpose is only one
 * EnterTable object can be create at the same time
 * 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class SingletonFactory {

	private static EnterTable enterTable;

	public static EnterTable newInstance(boolean functionality, int category) {
		if (enterTable == null) {
			enterTable = new EnterTable(functionality, category);
		}
		return enterTable;

	}

	public static void remove() {
		enterTable = null;
	}

}
