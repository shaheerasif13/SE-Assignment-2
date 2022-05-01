package EventPlanningSystem.EventPlanningSystem;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer extends Person {

	private String username;
	private String password;

	// Default constructor
	public Customer() {
		super();
		this.username = null;
		this.password = null;
	}

	// Parameterized constructor
	public Customer(String CNIC, String name, char gender, int age, String address, String phoneNumber, String username,
			String password) {
		super(CNIC, name, gender, age, address, phoneNumber);
		this.username = username;
		this.password = password;
	}

	// *************************
	// Getter and setter methods
	// *************************

	@Id
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) throws CustomerException.UsernameException {

		if (username == null) {
			throw new CustomerException.UsernameException("Username can not be null!");
		}

		else if (username.length() < 5) {
			throw new CustomerException.UsernameException("Username too short!");
		}

		else if (username.length() > 15) {
			throw new CustomerException.UsernameException("Username too long!");
		}

		this.username = username;
	}

	public void setPassword(String password) throws CustomerException.PasswordException {

		if (password == null) {
			throw new CustomerException.PasswordException("Password can not be null!");
		}

		else if (password.length() < 8) {
			throw new CustomerException.PasswordException("Password too short!");
		}

		this.password = password;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(super.toString() + "\n" + this.username + "\n" + this.password);
	}
}