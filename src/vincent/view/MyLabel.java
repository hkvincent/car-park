package vincent.view;

import javax.swing.JLabel;

import vincent.entity.Car;
import vincent.entity.Coaches;
import vincent.entity.Lorries;
import vincent.entity.Vehicle;

/**
 * extends to jlabel is for storing necessary data
 * 
 * @author vincent @date 2017-3-9
 * @version 1.0.0
 */
public class MyLabel extends JLabel {

	private boolean flag = false;
	public Car car;
	public Coaches coaches;
	public Lorries lorries;
	private int place;

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public MyLabel(String text) {
		super(text);
	}

}
