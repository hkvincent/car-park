package vincent.utils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import vincent.entity.Car;
import vincent.entity.Coaches;
import vincent.entity.Lorries;
import vincent.entity.Vehicle;
import vincent.view.CarPark;
import vincent.view.MyLabel;

/**
 * timer for calculating how many hours are car having park
 * 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class CarTimer {

	Timer timer;
	CarPark carPark;
	int time = 0;
	public static boolean pause = true;

	ExecutorService pool = Executors.newFixedThreadPool(3);

	public CarTimer(CarPark carPark) {
		this.carPark = carPark;
	}

	public void startTimer(int seconds, int time) {
		this.time = time;
		timer = new Timer();
		timer.schedule(new CarTask(), 0, seconds * 1000);

	}

	/**
	 * inner class for main task
	 * 
	 * @author vincent @date 2017-4-27
	 * @version 1.0.0
	 */
	class CarTask extends TimerTask {
		@Override
		public void run() {
			// System.out.println("current thread:" +
			// Thread.currentThread().getName());
			if (pause) {
				time++;
				carPark.setHourCount(time);
				carPark.setTimerLabelText(time);
				for (int i = carPark.vehicleList.size() - 1; i > -1; i--) {
					if (carPark.vehicleList.get(i) instanceof Car) {
						final Car car = (Car) carPark.vehicleList.get(i);
						int numOfHours = car.getNumOfHours() + 1;
						car.setNumOfHours(numOfHours);
						if (car.getHourYouPark() <= numOfHours) {
							pool.submit(() -> quit(car, 0));
							/*
							 * new Thread(new Runnable() {
							 * 
							 * @Override public void run() {
							 * 
							 * } }).start();
							 */
						}

					} else if (carPark.vehicleList.get(i) instanceof Lorries) {
						final Lorries lorries = (Lorries) carPark.vehicleList
								.get(i);
						int hours = lorries.hours + 1;
						System.out.println(hours);
						lorries.hours = hours;
						if (hours > 1 && hours % 24 == 0) {
							int numOfDay = lorries.getNumOfDay();
							lorries.setNumOfDay(++numOfDay);

						}
						if (lorries.getHourYouPark() <= hours) {
							pool.submit(() -> quit(lorries, 1));
						}

					} else if (carPark.vehicleList.get(i) instanceof Coaches) {
						final Coaches coaches = (Coaches) carPark.vehicleList
								.get(i);
						int hours = coaches.hours + 1;
						coaches.hours = hours;
						System.out.println(hours);
						if (coaches.getHourYouPark() <= hours) {
							pool.submit(new Runnable() {
								@Override
								public void run() {
									quit(coaches, 2);
								}
							});
						}

					}
				}
			}
		}
	}

	/**
	 * the Vehicle will be out of park when the Vehicle time's up
	 * 
	 * @param vehicle
	 * @param category
	 *            type of the Vehicle
	 */
	// there have a problem, the car place doesn't match jlabel place.because
	// the equals method has some problem.to solve this problem we implement
	// equal in Vehicle not its sub class
	public void quit(Vehicle vehicle, int category) {
		/*
		 * System.out.println("quit method's current thread:" +
		 * Thread.currentThread().getName());
		 */
		if (category == 0) {
			Car car = (Car) vehicle;
			for (MyLabel carList : CarPark.jlabelCarList) {
				if (car.getPlace() == carList.getPlace()) {
					carList.setIcon(null);
					carList.setFlag(false);
					CarPark.vehicleList.remove(car);
					carList.car = null;
					CarPark.overallIncome.add(car.calcCharge());
					return;
				}
			}
		} else if (category == 1) {
			Lorries lorries = (Lorries) vehicle;
			for (MyLabel bigCarList : CarPark.jlabelList) {
				if (lorries.getPlace() == bigCarList.getPlace()) {
					bigCarList.setIcon(null);
					bigCarList.setFlag(false);
					CarPark.vehicleList.remove(lorries);
					bigCarList.lorries = null;
					CarPark.overallIncome.add(lorries.calcCharge());
					return;
				}
			}
		} else if (category == 2) {
			Coaches coaches = (Coaches) vehicle;
			for (MyLabel bigCarList : CarPark.jlabelList) {
				if (coaches.getPlace() == bigCarList.getPlace()) {
					bigCarList.setIcon(null);
					bigCarList.setFlag(false);
					CarPark.vehicleList.remove(coaches);
					bigCarList.coaches = null;
					CarPark.overallIncome.add(coaches.calcCharge());
					return;
				}
			}
		}
	}

}
