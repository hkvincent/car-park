package vincent.entity;

import javax.swing.ImageIcon;

/**
 * Lorries class records the Lorries information
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class Lorries extends Vehicle {

	private double weight;
	private int numOfDay = 0;
	public int hours=0;

	public Lorries(String regNumber, double weight, int numOfDay) {
		super(regNumber);
		this.weight = weight;
		this.numOfDay = numOfDay;
	}

	public Lorries() {
		super();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
		if (weight < 20) {
			this.charge = 5;
		} else if (weight < 35) {
			this.charge = 8;
		}
	}

	public int getNumOfDay() {
		return numOfDay;
	}

	public void setNumOfDay(int numOfDay) {
		this.numOfDay = numOfDay;
	}

	@Override
	public ImageIcon getImage() {

		return new ImageIcon(this.getClass().getResource("res/lorry.jpg"));
	}

	@Override
	public String toString() {
		return "Lorries [weight=" + weight + ", numOfDay=" + numOfDay + "]";
	}

	@Override
	public double calcCharge() {

		return this.charge * numOfDay;
	}


	

}
