package vincent.view;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vincent.entity.Car;
import vincent.entity.Coaches;
import vincent.entity.Lorries;
import vincent.utils.SingletonFactory;

/**
 * the input data gui for setting car information
 * 
 * @author vincent @date 2017-3-9
 * @version 1.0.0
 */
public class EnterTable extends JFrame {
	private boolean radio;
	private int cate;// 1 for car,2 for lorry , 3 for bus;

	/**
	 * Creates new form NewJPanel
	 */
	public EnterTable() {
		initComponents();
	}

	public EnterTable(boolean radio, int cate) {
		this.radio = radio;
		this.cate = cate;
		initComponents();
	}

	/**
	 * enter the number of the car and the other data
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		rnTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		inforTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel("how many hours you park");
		hourTextField = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		disableRadio = new javax.swing.JRadioButton();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		final EnterTable en = this;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				en.dispose();
				SingletonFactory.remove();
			}
		});

		jLabel1.setText("registration number");


		if (cate == 1) {
			jLabel2.setText("car length");
			disableRadio.setText("disabled badge?");
		} else if (cate == 2) {
			jLabel2.setText("lorries weight");
		} else if (cate == 3) {
			jLabel2.setText("coaches passengers");
			disableRadio.setText("tourist Operator?");
		}

		jButton1.setText("OK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Cancel");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		JPanel centerPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		centerPanel.setLayout(new GridLayout(4, 2));
		centerPanel.add(jLabel1);
		centerPanel.add(rnTextField1);
		centerPanel.add(jLabel2);
		centerPanel.add(inforTextField);
		centerPanel.add(jLabel3);
		centerPanel.add(hourTextField);
		centerPanel.add(disableRadio);
		bottomPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.add(jButton1);
		bottomPanel.add(jButton2);
		this.add(centerPanel, "Center");
		this.add(bottomPanel, "South");
		pack();

		// for other condition,if true that mean this car type can have discount
		// or not
		this.disableRadio.setEnabled(this.radio ? true : false);
		this.disableRadio.setVisible(this.radio ? true : false);

	}



	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		SingletonFactory.remove();
		if (cate == 1) {
			for (MyLabel carLabel : CarPark.jlabelCarList) {
				if (carLabel.car != null
						&& carLabel.car.getRegNumber().equals(
								rnTextField1.getText())) {
					JOptionPane.showMessageDialog(this, "duplicate car ID");
					return;
				}
			}
			Car car = new Car();
			car.setRegNumber(rnTextField1.getText());
			car.setLength(Integer.valueOf(inforTextField.getText()));
			car.setDisabledBadge(disableRadio.isSelected() ? true : false);
			for (int i = 0; i < CarPark.jlabelCarList.size(); i++) {
				if (!CarPark.jlabelCarList.get(i).isFlag()) {
					CarPark.jlabelCarList.get(i).setIcon(car.getImage());
					CarPark.jlabelCarList.get(i).setFlag(true);
					car.setPlace(CarPark.jlabelCarList.get(i).getPlace());
					int hour = hourTextField.getText().equals("") ? 5 : Integer
							.parseInt(hourTextField.getText());
					car.setHourYouPark(hour);
					CarPark.jlabelCarList.get(i).car = car;
					CarPark.vehicleList.add(car);
					this.dispose();
					return;
				}
			}
		} else if (cate == 2) {

			for (MyLabel lorriesLabel : CarPark.jlabelList) {
				if (lorriesLabel.lorries != null
						&& lorriesLabel.lorries.getRegNumber().equals(
								rnTextField1.getText())) {
					JOptionPane.showMessageDialog(this, "duplicate lorries ID");
					return;
				}
			}
			Lorries lorries = new Lorries();
			lorries.setRegNumber(rnTextField1.getText());
			Integer tonnes = Integer.valueOf(inforTextField.getText());
			if (tonnes > 35) {
				JOptionPane
						.showMessageDialog(this,
								"not permitted  to use car park because of your car tonnes");
				return;
			}
			lorries.setWeight(tonnes);
			for (int i = 0; i < CarPark.jlabelList.size(); i++) {
				if (!CarPark.jlabelList.get(i).isFlag()) {
					CarPark.jlabelList.get(i).setIcon(lorries.getImage());
					CarPark.jlabelList.get(i).setFlag(true);
					lorries.setPlace(CarPark.jlabelList.get(i).getPlace());
					int hour = hourTextField.getText().equals("") ? 5 : Integer
							.parseInt(hourTextField.getText());
					lorries.setHourYouPark(hour);
					CarPark.jlabelList.get(i).lorries = lorries;
					CarPark.vehicleList.add(lorries);
					this.dispose();
					return;
				}
			}
		} else {

			for (MyLabel coachesLabel : CarPark.jlabelList) {
				if (coachesLabel.coaches != null
						&& coachesLabel.coaches.getRegNumber().equals(
								rnTextField1.getText())) {
					JOptionPane.showMessageDialog(this, "duplicate coaches ID");
					return;
				}
			}
			Coaches coaches = new Coaches();
			coaches.setRegNumber(rnTextField1.getText());
			coaches.setNumOfPassengers(Integer.valueOf(inforTextField.getText()));
			coaches.setTouristOperator(disableRadio.isSelected() ? true : false);
			for (int i = 0; i < CarPark.jlabelList.size(); i++) {
				if (!CarPark.jlabelList.get(i).isFlag()) {
					CarPark.jlabelList.get(i).setIcon(coaches.getImage());
					CarPark.jlabelList.get(i).setFlag(true);
					int hour = hourTextField.getText().equals("") ? 5 : Integer
							.parseInt(hourTextField.getText());
					coaches.setHourYouPark(hour);
					coaches.setPlace(CarPark.jlabelList.get(i).getPlace());
					CarPark.jlabelList.get(i).coaches = coaches;
					CarPark.vehicleList.add(coaches);
					this.dispose();
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(this, "can not find the park");
		this.dispose();

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		SingletonFactory.remove();
		this.dispose();
	}

	private javax.swing.JRadioButton disableRadio;
	private javax.swing.JTextField inforTextField;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField rnTextField1;
	private javax.swing.JTextField hourTextField;

}
