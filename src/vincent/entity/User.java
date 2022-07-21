package vincent.entity;

/**
 * the java bean of user for record login or register information 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class User {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

	
}
