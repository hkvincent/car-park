package vincent.entity;

import javax.swing.ImageIcon;

/**
 * javabean for storing data and cal charge
 * 
 * @author vincent @date 2017-3-9
 * @version 1.0.0
 */
public class Car extends Vehicle {

	private double length;
	private boolean disabledBadge;
	private int numOfHours = 0;

	public Car() {

	}

	public Car(String regNumber, double length, boolean disabledBadge,
			int numOfHours) {
		super(regNumber);
		this.length = length;
		this.disabledBadge = disabledBadge;
		this.numOfHours = numOfHours;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
		if (length <= 6) {
			this.charge = 1;
		} else {
			this.charge = 1.5;
		}
	}

	public boolean isDisabledBadge() {
		return disabledBadge;
	}

	public void setDisabledBadge(boolean disabledBadge) {
		this.disabledBadge = disabledBadge;
	}

	public int getNumOfHours() {
		return numOfHours;
	}

	public void setNumOfHours(int numOfHours) {
		this.numOfHours = numOfHours;
	}

	@Override
	public ImageIcon getImage() {
		System.out.println(this.getClass().getResource("res/car.jpg"));
		return new ImageIcon(this.getClass().getResource("res/car.jpg"));
	}

	@Override
	public double calcCharge() {
		if (isDisabledBadge()) {
			return 0;
		}
		return getNumOfHours() * this.charge;
	}

	@Override
	public String toString() {
		return "Car [length=" + length + ", disabledBadge=" + disabledBadge
				+ ", numOfHours=" + numOfHours + "]";
	}

	
	
	

}
