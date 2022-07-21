package vincent.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vincent.entity.User;
import vincent.service.IAccountSerivce;
import vincent.service.ITimerService;
import vincent.service.impl.AccountService;
import vincent.service.impl.TimerService;
import vincent.utils.CarTimer;

/**
 * the GUI for login and register
 * 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class AccountFrme extends JFrame {
	private JTextField usernameText;
	private JPasswordField registerCode;
	private JPasswordField passwordText;
	IAccountSerivce account = new AccountService();

	public AccountFrme() {
		init();
	}

	public void init() {
		usernameText = new JTextField();
		passwordText = new JPasswordField();
		registerCode = new JPasswordField();
		JButton loginButton = new JButton("login");
		JButton registerButton = new JButton("register");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User check = check(0);
				if (check != null) {
					boolean login = account.login(check);
					if (login) {
						CarPark carPark = new CarPark();
						carPark.setLocationRelativeTo(null);
						CarTimer carTimer = new CarTimer(carPark);
						ITimerService time = new TimerService();
						int count = time.find("timer");
						if (count > 0) {
							carTimer.startTimer(3, count);
						} else {
							carTimer.startTimer(3, 0);
						}
						AccountFrme.this.dispose();
					} else {
						JOptionPane.showMessageDialog(AccountFrme.this,
								"username or password wrong");
					}
				} else {
					JOptionPane.showMessageDialog(AccountFrme.this,
							"input username and password please");
				}
			}
		});

		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User check = check(1);
				if (check != null) {
					boolean register = account.register(check);
					if (register) {
						JOptionPane.showMessageDialog(AccountFrme.this,
								"register successful");
					} else {
						JOptionPane.showMessageDialog(AccountFrme.this,
								"nuknow error,please contact our stuff");
					}
				} else {
					JOptionPane
							.showMessageDialog(AccountFrme.this,
									"input username, password and register code  please");
				}
			}

		});

		this.setLayout(new GridLayout(4, 2));
		this.add(new JLabel("username"));
		this.add(usernameText);
		this.add(new JLabel("password"));
		this.add(passwordText);
		this.add(new JLabel("register code"));
		this.add(registerCode);
		this.add(loginButton);
		this.add(registerButton);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * 
	 * @param category (login or register)
	 * @return the information of username and password are encapsulated by User POJO
	 */
	public User check(int category) {
		User user = null;
		if (category == 0) {
			if (usernameText.getText() != null
					&& !usernameText.getText().equals("")
					&& passwordText.getText() != null
					&& !passwordText.getText().equals("")) {
				user = new User();
				user.setUsername(usernameText.getText());
				user.setPassword(passwordText.getText());
				return user;
			}
		}
		if (category == 1) {
			if (usernameText.getText() != null
					&& !usernameText.getText().equals("")
					&& passwordText.getText() != null
					&& !passwordText.getText().equals("")
					&& registerCode.getText().equals("vincent")) {
				user = new User();
				user.setUsername(usernameText.getText());
				user.setPassword(passwordText.getText());
				return user;
			}
		}
		return user;
	}
}
