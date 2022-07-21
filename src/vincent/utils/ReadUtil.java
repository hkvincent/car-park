package vincent.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import vincent.entity.Car;
import vincent.entity.Coaches;
import vincent.entity.Lorries;
import vincent.entity.Vehicle;
import vincent.view.CarPark;

/**
 * read util for reading data
 * 
 * @author vincent @date 2017-3-9
 * @version 1.0.0
 */
public class ReadUtil {

	/**
	 * this method is using read the dat file had beed saved in last time
	 * 
	 * @param file
	 *            which need to read in object format
	 */
	@SuppressWarnings("unchecked")
	public static void Loading(File file) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new FileInputStream(file));
			List<Vehicle> list = (List<Vehicle>) objectInputStream.readObject();
			for (Vehicle vehicle : list) {
				if (vehicle instanceof Car) {
					for (int i = 0; i < CarPark.jlabelCarList.size(); i++) {
						if (!CarPark.jlabelCarList.get(i).isFlag()) {
							if (CarPark.jlabelCarList.get(i).getPlace() == vehicle
									.getPlace()) {
								CarPark.jlabelCarList.get(i).setIcon(
										vehicle.getImage());
								CarPark.jlabelCarList.get(i).setFlag(true);
								CarPark.jlabelCarList.get(i).car = (Car) vehicle;
								CarPark.vehicleList.add(vehicle);
								break;
							}
						}
					}

				} else if (vehicle instanceof Lorries) {
					for (int i = 0; i < CarPark.jlabelList.size(); i++) {
						if (!CarPark.jlabelList.get(i).isFlag()) {
							if (CarPark.jlabelList.get(i).getPlace() == vehicle
									.getPlace()) {
								CarPark.jlabelList.get(i).setIcon(
										vehicle.getImage());
								CarPark.jlabelList.get(i).setFlag(true);
								CarPark.jlabelList.get(i).lorries = (Lorries) vehicle;
								CarPark.vehicleList.add(vehicle);
								break;
							}
						}
					}
				} else if (vehicle instanceof Coaches) {
					for (int i = 0; i < CarPark.jlabelList.size(); i++) {
						if (!CarPark.jlabelList.get(i).isFlag()) {
							if (CarPark.jlabelList.get(i).getPlace() == vehicle
									.getPlace()) {
								CarPark.jlabelList.get(i).setIcon(
										vehicle.getImage());
								CarPark.jlabelList.get(i).setFlag(true);
								CarPark.jlabelList.get(i).coaches = (Coaches) vehicle;
								CarPark.vehicleList.add(vehicle);
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
