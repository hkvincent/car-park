package vincent.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import vincent.entity.Vehicle;
import vincent.service.ITimerService;
import vincent.service.impl.TimerService;
import vincent.utils.CarTimer;
import vincent.utils.DatFileFilter;
import vincent.utils.ReadUtil;
import vincent.utils.SingletonFactory;

/**
 * the main GUI for interacting with user 
 * 
 * @author vincent @date 2017-3-9
 * @version 1.0.0
 */
public class CarPark extends JFrame {

	// for setting how many place can park
	private int carRow = 3;
	private int carCol = 4;
	private int places = 4;

	// counting variable for storing data
	private static int hourCount;

	private JLabel timerJLabel;

	// List for storing Jlabel
	public static List<MyLabel> jlabelCarList = new ArrayList<MyLabel>();
	public static List<MyLabel> jlabelList = new ArrayList<MyLabel>();

	// List for storing car
	public static List<Vehicle> vehicleList = new ArrayList<Vehicle>();

	// list for storing overall income
	public static List<Double> overallIncome = new ArrayList<Double>();

	public CarPark() {
		init();
	}

	public CarPark(int carRow, int carCol, int places) throws HeadlessException {
		super();
		this.carRow = carRow;
		this.carCol = carCol;
		this.places = places;
		init();
	}

	/**
	 * initial the GUI and add button label to the jframe
	 */
	// initial method to set GUI
	public void init() {
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new GridLayout(8, 1));
		JPanel centerPanel = new JPanel();
		JPanel carPanel = new JPanel(new GridLayout(carRow, carCol));
		JPanel otherCarPanel = new JPanel(new GridLayout(1, places));
		JPanel timerPanel = new JPanel();
		final JButton pauseButton = new JButton("pause");

		timerPanel.setLayout(new GridLayout(1, 2));
		timerJLabel = new JLabel("time:");
		timerPanel.add(pauseButton);
		timerPanel.add(timerJLabel);

		centerPanel.setLayout(new GridLayout(2, 1));

		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String state = pauseButton.getText().equals("pause") ? "start"
						: "pause";
				pauseButton.setText(state);
				CarTimer.pause = !CarTimer.pause;

			}
		});

		for (int i = 0; i < carRow * carCol; i++) {
			final MyLabel tempLabel = new MyLabel(i + "");
			tempLabel.setPlace(i);
			tempLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.isMetaDown()) {
						System.out.println("right");
						if (tempLabel.isFlag()) {
							int answer = JOptionPane.showConfirmDialog(null,
									"Are you sure?", "Confirm Window ",
									JOptionPane.YES_NO_OPTION);
							if (answer == JOptionPane.YES_OPTION) {
								tempLabel.setIcon(null);
								tempLabel.setFlag(false);
								vehicleList.remove(tempLabel.car);
								tempLabel.car = null;
							}
						}
					} else if (e.getButton() == MouseEvent.BUTTON2) {
						System.out.println("mid");
						if (!tempLabel.isFlag()) {
							JOptionPane.showMessageDialog(CarPark.this,
									"this space is empty");
							return;
						}
						UpdataData updataData = new UpdataData(1, tempLabel.car);
						updataData.setLocationRelativeTo(null);
						updataData.setVisible(true);
					} else {
						System.out.println("left");
						if (!tempLabel.isFlag()) {
							JOptionPane.showMessageDialog(CarPark.this,
									"this space is empty");
						} else {
							JOptionPane.showMessageDialog(CarPark.this,
									"car length:" + tempLabel.car.getLength()
											+ "\r\n" + "car park hours:"
											+ tempLabel.car.getNumOfHours()
											+ "\r\n"
											+ "car registration number:"
											+ tempLabel.car.getRegNumber()
											+ "\r\n" + "charge :"
											+ tempLabel.car.calcCharge()
											+ "\r\n" + "will park/hours :"
											+ tempLabel.car.getHourYouPark()

							);
						}
					}
				}

			});
			tempLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			carPanel.add(tempLabel);
			jlabelCarList.add(tempLabel);

		}

		for (int i = 0; i < places; i++) {
			final MyLabel tempLabel = new MyLabel(i + "");
			tempLabel.setPlace(i);
			tempLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.isMetaDown()) {
						System.out.println("right");
						if (tempLabel.isFlag()) {
							int answer = JOptionPane.showConfirmDialog(null,
									"Are you sure?", "Confirm Window ",
									JOptionPane.YES_NO_OPTION);
							if (answer == JOptionPane.YES_OPTION) {
								tempLabel.setIcon(null);
								tempLabel.setFlag(false);
								if (tempLabel.lorries != null) {
									vehicleList.remove(tempLabel.lorries);
									tempLabel.lorries = null;
								} else if (tempLabel.coaches != null) {
									vehicleList.remove(tempLabel.coaches);
									tempLabel.coaches = null;
								}

							}
						}
					} else if (e.getButton() == MouseEvent.BUTTON2) {
						System.out.println("mid");
						if (!tempLabel.isFlag()) {
							JOptionPane.showMessageDialog(CarPark.this,
									"this space is empty");
							return;
						}
						if (tempLabel.lorries != null) {
							UpdataData updataData = new UpdataData(2,
									tempLabel.lorries);
							updataData.setLocationRelativeTo(null);
							updataData.setVisible(true);
						} else if (tempLabel.coaches != null) {
							UpdataData updataData = new UpdataData(3,
									tempLabel.coaches);
							updataData.setLocationRelativeTo(null);
							updataData.setVisible(true);
						}

					} else {
						System.out.println("left");
						if (!tempLabel.isFlag()) {
							JOptionPane.showMessageDialog(CarPark.this,
									"this space is empty");
						} else {
							if (tempLabel.lorries != null) {
								JOptionPane
										.showMessageDialog(
												CarPark.this,
												"lorries Weight:"
														+ tempLabel.lorries
																.getWeight()
														+ "\r\n"
														+ "lorries park day:"
														+ tempLabel.lorries
																.getNumOfDay()
														+ "\r\n"
														+ "lorries registration number:"
														+ tempLabel.lorries
																.getRegNumber()
														+ "\r\n"
														+ "charge :"
														+ tempLabel.lorries
																.calcCharge()
																+ "\r\n" + "will park/hours :"
											+ tempLabel.lorries.getHourYouPark()
												);
							} else if (tempLabel.coaches != null) {
								JOptionPane
										.showMessageDialog(
												CarPark.this,
												"coaches NumOfPassengers:"
														+ tempLabel.coaches
																.getNumOfPassengers()
														+ "\r\n"
														+ "coaches registration number:"
														+ tempLabel.coaches
																.getRegNumber()
														+ "\r\n"
														+ "charge :"
														+ tempLabel.coaches
																.getCharge()+
																"\r\n" + "will park/hours :"
											+ tempLabel.coaches.getHourYouPark()
												);
							}
						}
					}
				}

			});
			otherCarPanel.add(tempLabel);
			tempLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			jlabelList.add(tempLabel);
		}

		JButton addCar = new JButton("Add Car");
		addCar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EnterTable enterTable = SingletonFactory.newInstance(true, 1);
				enterTable.setLocationRelativeTo(null);
				enterTable.setVisible(true);

				/*
				 * EnterTable enterTable = new EnterTable(true, 1);
				 * enterTable.setLocationRelativeTo(null);
				 * enterTable.setVisible(true);
				 */

			}
		});

		JButton addLorry = new JButton("Add Lorry");
		addLorry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EnterTable enterTable = SingletonFactory.newInstance(false, 2);
				enterTable.setLocationRelativeTo(null);
				enterTable.setVisible(true);

				/*
				 * EnterTable enterTable = new EnterTable(false, 2);
				 * enterTable.setLocationRelativeTo(null);
				 * enterTable.setVisible(true);
				 */
			}
		});

		JButton addCoach = new JButton("Add Coach");
		addCoach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EnterTable enterTable = SingletonFactory.newInstance(true, 3);
				enterTable.setLocationRelativeTo(null);
				enterTable.setVisible(true);

				/*
				 * EnterTable enterTable = new EnterTable(true, 3);
				 * enterTable.setLocationRelativeTo(null);
				 * enterTable.setVisible(true);
				 */

			}
		});

		JButton clearAll = new JButton("Clear All");
		clearAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null,
						"Are you sure?", "Confirm Window ",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					vehicleList.clear();
					for (int i = 0; i < jlabelCarList.size(); i++) {
						jlabelCarList.get(i).setIcon(null);
						jlabelCarList.get(i).setFlag(false);
						jlabelCarList.get(i).car = null;
					}
					for (int i = 0; i < jlabelList.size(); i++) {
						jlabelList.get(i).setIcon(null);
						jlabelList.get(i).setFlag(false);
						jlabelList.get(i).coaches = null;
						jlabelList.get(i).lorries = null;
					}

				}
			}
		});

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				DatFileFilter datFileFilter = new DatFileFilter();
				jfc.addChoosableFileFilter(datFileFilter);
				int result = jfc.showSaveDialog(CarPark.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					File newFile = file.getAbsolutePath().endsWith(".dat") ? file
							: new File(file.getAbsolutePath() + ".dat");
					try {
						ObjectOutputStream out = new ObjectOutputStream(
								new FileOutputStream(newFile));
						out.writeObject(vehicleList);
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

			}

		});

		JButton load = new JButton("load");
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				DatFileFilter datFileFilter = new DatFileFilter();
				jfc.addChoosableFileFilter(datFileFilter);
				int result = jfc.showOpenDialog(CarPark.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					ReadUtil.Loading(file);
				}

			}
		});

		JButton currentTotal = new JButton("Current Total");
		currentTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				for (Vehicle v : vehicleList) {
					sum += v.calcCharge();
				}

				JOptionPane.showMessageDialog(CarPark.this, "the total income"
						+ sum);

			}
		});

		JButton TotalForDay = new JButton("overall total");
		TotalForDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double income = 0d;
				for (Double incomeUnit : overallIncome) {
					income += incomeUnit;
				}
				JOptionPane.showMessageDialog(CarPark.this, "overall income "
						+ income);

			}
		});

		ButtonPanel.add(addCar);
		ButtonPanel.add(addLorry);
		ButtonPanel.add(addCoach);
		ButtonPanel.add(clearAll);
		ButtonPanel.add(save);
		ButtonPanel.add(load);
		ButtonPanel.add(currentTotal);
		ButtonPanel.add(TotalForDay);

		centerPanel.add(otherCarPanel);
		centerPanel.add(carPanel);

		this.add(centerPanel, "Center");
		this.add(ButtonPanel, "West");
		this.add(timerPanel, "South");
		this.setSize(350, 350);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ITimerService time = new TimerService();
				time.edit("timer", CarPark.getHourCount() + "");
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	public int getCarRow() {
		return carRow;
	}

	public void setCarRow(int carRow) {
		this.carRow = carRow;
	}

	public int getCarCol() {
		return carCol;
	}

	public void setCarCol(int carCol) {
		this.carCol = carCol;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public static void setHourCount(int hour) {
		hourCount = hour;
	}

	public static int getHourCount() {
		return hourCount;
	}

	public void setTimerLabelText(int time) {
		this.timerJLabel.setText("time:" + time + " hours");
	}

}
