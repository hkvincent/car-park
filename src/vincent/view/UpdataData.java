package vincent.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vincent.entity.Car;
import vincent.entity.Coaches;
import vincent.entity.Lorries;
import vincent.entity.Vehicle;

/**
 * when we click mid button of the mouse, this frame will show to user to change
 * the car data and save the changes if user click ok.
 * 
 * @author vincent @date 2017-3-9
 * @version 1.0.0
 */
public class UpdataData extends JFrame {
	private int cate;
	private Vehicle vehicle;
	private JLabel InforLabel = new JLabel();
	private JLabel chargeLabel = new JLabel();
	private JLabel parkLabel = new JLabel();
	private JTextField InforField = new JTextField();
	private JTextField chargeField = new JTextField();
	private JTextField parkField = new JTextField("will park/hour");
	private JLabel hourLabel = new JLabel();
	private JTextField hourText = new JTextField();
	int carCate = 0;

	public UpdataData(int cate, Vehicle vehicle) {

		this.cate = cate;
		this.vehicle = vehicle;
		init();
	};

	/**
	 * initial the edit frame
	 */
	public void init() {
		JPanel labelPanel = new JPanel(new GridLayout(4, 1));
		JPanel textPanel = new JPanel(new GridLayout(4, 1));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		if (cate == 1) {
			InforLabel.setText("car length");
			chargeLabel.setText("charge per hour");
		} else if (cate == 2) {
			InforLabel.setText("Lorries weight");
			chargeLabel.setText("charge per day");
		} else if (cate == 3) {
			InforLabel.setText("Coaches passenger");
			chargeLabel.setText("charge per Coaches");
		}

		parkLabel.setText("Parking duration ");
		hourLabel.setText("the ticket hours");
		labelPanel.add(InforLabel);
		labelPanel.add(chargeLabel);
		labelPanel.add(parkLabel);
		labelPanel.add(hourLabel);

		if (vehicle instanceof Car) {
			Car car = (Car) vehicle;
			chargeField.setText(car.getCharge() + "");
			InforField.setText(car.getLength() + "");
			parkField.setText(car.getNumOfHours() + "");
			hourText.setText(car.getHourYouPark() + "");
			carCate = 1;
		} else if (vehicle instanceof Lorries) {
			Lorries lo = (Lorries) vehicle;
			chargeField.setText(lo.getCharge() + "");
			InforField.setText(lo.getWeight() + "");
			parkField.setText(lo.getNumOfDay() + "");
			hourText.setText(lo.getHourYouPark() + "");
			carCate = 2;
		} else if (vehicle instanceof Coaches) {
			chargeField.setText(((Coaches) vehicle).getCharge() + "");
			InforField.setText(((Coaches) vehicle).getNumOfPassengers() + "");
			hourText.setText(((Coaches) vehicle).getHourYouPark() + "");
			parkField.setEnabled(false);
			carCate = 3;
		}

		textPanel.add(InforField);
		textPanel.add(chargeField);
		textPanel.add(parkField);
		textPanel.add(hourText);

		JButton okButton = new JButton("ok");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (carCate == 1) {
					Car car = (Car) vehicle;
					car.setCharge(Double.valueOf(chargeField.getText()));
					car.setLength(Double.valueOf(InforField.getText()));
					car.setNumOfHours(Integer.valueOf(parkField.getText()));
					car.setHourYouPark(Integer.valueOf(hourText.getText()));
				} else if (carCate == 2) {
					Lorries lorries = (Lorries) vehicle;
					lorries.setCharge(Double.valueOf(chargeField.getText()));
					double weihgt = (Double.valueOf(InforField.getText()));
					if (weihgt > 35) {
						JOptionPane.showMessageDialog(UpdataData.this,
								"over weight");
						return;
					}
					lorries.setWeight(weihgt);
					lorries.setNumOfDay(Integer.valueOf(parkField.getText()));
					lorries.setHourYouPark(Integer.valueOf(hourText.getText()));
				} else {
					Coaches coaches = (Coaches) vehicle;
					coaches.setCharge(Double.valueOf(chargeField.getText()));
					coaches.setNumOfPassengers(Integer.valueOf(InforField
							.getText()));
					coaches.setHourYouPark(Integer.valueOf(hourText.getText()));
				}

				UpdataData.this.dispose();
			}
		});

		JButton cancelButton = new JButton("cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdataData.this.dispose();

			}
		});
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		this.add(labelPanel, "West");
		this.add(textPanel, "Center");
		this.add(buttonPanel, "South");
		this.pack();

	}
}
