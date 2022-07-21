package vincent.entity;

import javax.swing.ImageIcon;

/**
 * coaches class records coaches information
 * 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class Coaches extends Vehicle {

	private int numOfPassengers;
	private boolean touristOperator;
	public int hours=0;
	public Coaches() {

	}

	public Coaches(String regNumber, int numOfPassengers,
			boolean touristOperator) {
		super(regNumber);
		this.numOfPassengers = numOfPassengers;
		this.touristOperator = touristOperator;
	}

	@Override
	public String toString() {
		return "Coaches [numOfPassengers=" + numOfPassengers
				+ ", touristOperator=" + touristOperator + "]";
	}

	public int getNumOfPassengers() {
		return numOfPassengers;
	}

	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;

		if (numOfPassengers < 20) {
			this.charge = 4.5;
		} else {
			this.charge = 6;
		}
	}

	public boolean isTouristOperator() {
		return touristOperator;
	}

	public void setTouristOperator(boolean touristOperator) {
		this.touristOperator = touristOperator;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return new ImageIcon(this.getClass().getResource("res/coach.png"));
	}

	@Override
	public double calcCharge() {
		if (touristOperator) {
			return 0.9 * this.charge;
		}
		return charge;
	}

	
}
