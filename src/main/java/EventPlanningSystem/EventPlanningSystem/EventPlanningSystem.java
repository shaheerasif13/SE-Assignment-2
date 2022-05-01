package EventPlanningSystem.EventPlanningSystem;

import EventPlanningSystem.EventPlanningSystem.CustomerException.PasswordException;
import EventPlanningSystem.EventPlanningSystem.CustomerException.UsernameException;
import EventPlanningSystem.EventPlanningSystem.PersonException.AddressException;
import EventPlanningSystem.EventPlanningSystem.PersonException.AgeException;
import EventPlanningSystem.EventPlanningSystem.PersonException.CNICException;
import EventPlanningSystem.EventPlanningSystem.PersonException.GenderException;
import EventPlanningSystem.EventPlanningSystem.PersonException.NameException;
import EventPlanningSystem.EventPlanningSystem.PersonException.PhoneNumberException;

public class EventPlanningSystem {

	private Customer activeCustomer;
	private DBHandler dbController;

	public EventPlanningSystem() {
		this.activeCustomer = new Customer();
		this.dbController = new DBHandler();

		//////////////////////////////////////////////////////////////////////////////////
		this.dbController.populateDatabase();
		//////////////////////////////////////////////////////////////////////////////////
	}

	// *************************
	// Getter and setter methods
	// *************************

	public Customer getActiveCustomer() {
		return activeCustomer;
	}

	public DBHandler getDbController() {
		return dbController;
	}

	public void setActiveCustomer(Customer activeCustomer) {
		this.activeCustomer = activeCustomer;
	}

	public void setDbController(DBHandler dbController) {
		this.dbController = dbController;
	}

	// ********************
	// Method to login user
	// ********************

	public boolean loginUser(String username, String password) {
		Customer c = this.dbController.loginUser(username, password);

		if (c != null) {
			this.activeCustomer = c;
			return true;
		}

		return false;
	}

	// *********************
	// Method to signup user
	// *********************

	public int signupUser(String firstName, String lastName, String CNIC, char gender, int age, String address,
			String phoneNumber, String username, String password) {

		if (this.dbController.verifyNewUser(username, CNIC)) {
			Customer newCustomer = new Customer();

			try {
				newCustomer.setCNIC(CNIC);
			} catch (CNICException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setName(firstName + " " + lastName);
			} catch (NameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setGender(gender);
			} catch (GenderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setAge(age);
			} catch (AgeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setAddress(address);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setPhoneNumber(phoneNumber);
			} catch (PhoneNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setUsername(username);
			} catch (UsernameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			try {
				newCustomer.setPassword(password);
			} catch (PasswordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

			this.dbController.saveNewCustomer(newCustomer);
			this.activeCustomer = newCustomer;
			return 1;
		}

		return -1;
	}
}