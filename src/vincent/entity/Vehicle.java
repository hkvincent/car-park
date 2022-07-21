package vincent.entity;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * the abstract father class
 * 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public abstract class Vehicle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int place;
	double charge;
	String regNumber;
	int hourYouPark;

	public abstract ImageIcon getImage();

	public abstract double calcCharge();

	public int getPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Vehicle(double charge, String regNumber) {
		super();
		this.charge = charge;
		this.regNumber = regNumber;
	}

	public Vehicle() {
		super();
	}

	public Vehicle(String regNumber) {
		super();
		this.regNumber = regNumber;
	}

	@Override
	public String toString() {
		return "Vechicle [charge=" + charge + ", regNumber=" + regNumber + "]";
	}

	public int getHourYouPark() {
		return hourYouPark;
	}

	public void setHourYouPark(int hourYouPark) {
		this.hourYouPark = hourYouPark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((regNumber == null) ? 0 : regNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (regNumber == null) {
			if (other.regNumber != null)
				return false;
		} else if (!regNumber.equals(other.regNumber))
			return false;
		return true;
	}

}
