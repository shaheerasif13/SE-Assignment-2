package EventPlanningSystem.EventPlanningSystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBHandler {

	private Configuration connection;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	// Default constructor
	public DBHandler() {
		this.connection = new Configuration();
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(Person.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
		this.sessionFactory = connection.buildSessionFactory();
	}

	// ********************
	// Method to login user
	// ********************

	public Customer loginUser(String username, String password) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		Customer activeCustomer = (Customer) this.session.get(Customer.class, username);

		if (activeCustomer != null) {

			if (!password.equals(activeCustomer.getPassword())) {
				activeCustomer = null;
			}
		}

		this.session.close();
		return activeCustomer;
	}

	// **********************
	// Methods to signup user
	// **********************

	// Method to check if new user is unique or not
	public boolean verifyNewUser(String username, String CNIC) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		Customer temp = (Customer) this.session.get(Customer.class, username);

		if (temp == null) {
			temp = (Customer) this.session.get(Customer.class, CNIC);

			if (temp == null) {
				this.session.close();
				return true;
			}
		}

		this.session.close();
		return false;
	}

	// Method to save new customer in database
	public void saveNewCustomer(Customer targetCustomer) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.save(targetCustomer);
		this.transaction.commit();
		this.session.close();
	}

	//////////////////////////////////////////////////////////////////////////////////
	public void populateDatabase() {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();

		// Inserting some customers
		Customer c1 = new Customer("3420111111111", "Muhammad Shaheer", 'M', 20, "Gujrat", "03000000000",
				"shaheerasif13", "11111111");
		this.session.save(c1);
		Customer c2 = new Customer("3420122222222", "Faizan Shabbir", 'M', 20, "Islamabad", "03009999999",
				"lokalpotato", "22222222");
		this.session.save(c2);

		this.transaction.commit();
		this.session.close();
	}
	//////////////////////////////////////////////////////////////////////////////////
}