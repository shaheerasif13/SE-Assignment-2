package EventPlanningSystem.EventPlanningSystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class UIController implements Initializable {

	private static EventPlanningSystem system;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if (UIController.system == null) {
			//UIController.system = new EventPlanningSystem();
		}

		// Setting state of combo box before showing signup page
		if (genderComboBox != null) {
			genderComboBox.setItems(FXCollections.observableArrayList("M", "F"));
		}

		// Displaying dashboard by default after login or signup
		if (contentArea != null) {
			this.displayDashboard(null);
		}
	}

	// *********************************
	// Login page attributes and methods
	// *********************************

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Text invalidMessage;

	@FXML
	private Text emptyMessage;

	@FXML
	void loginUser(ActionEvent event) throws IOException {

		// If any field is empty
		if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			boolean loggedIn = UIController.system.loginUser(usernameField.getText(), passwordField.getText());

			// If invalid user name or password is entered
			if (!loggedIn) {
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			// Everything is ok
			else {
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(false);
				// App.setRoot("Application");
				System.out.println("Login successfull!");
			}
		}
	}

	@FXML
	public void showSignupPage(ActionEvent event) throws IOException {
		App.setRoot("Signup");
	}

	// **********************************
	// Signup page attributes and methods
	// **********************************

	@FXML
	private TextField CNICField;

	@FXML
	private TextField addressField;

	@FXML
	private TextField ageField;

	@FXML
	private TextField firstNameField;

	@FXML
	private ComboBox<String> genderComboBox;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField phoneNumberField;

	@FXML
	private Text alreadyExistsMessage;

	@FXML
	void showLoginPage(ActionEvent event) throws IOException {
		App.setRoot("Login");
	}

	@FXML
	void signupUser(ActionEvent event) throws IOException {

		// If any field is empty
		if (firstNameField.getText().equals("") || lastNameField.getText().equals("") || CNICField.getText().equals("")
				|| ageField.getText().equals("") || genderComboBox.getPromptText().equals("")
				|| addressField.getText().equals("") || phoneNumberField.getText().equals("")
				|| usernameField.getText().equals("") || passwordField.getText().equals("")) {
			alreadyExistsMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			int signedUp = UIController.system.signupUser(firstNameField.getText(), lastNameField.getText(),
					CNICField.getText(), genderComboBox.getValue().charAt(0), Integer.parseInt(ageField.getText()),
					addressField.getText(), phoneNumberField.getText(), usernameField.getText(),
					passwordField.getText());

			// If account already exists
			if (signedUp == -1) {
				invalidMessage.setVisible(false);
				emptyMessage.setVisible(false);
				alreadyExistsMessage.setVisible(true);
			}

			else if (signedUp == 0) {
				emptyMessage.setVisible(false);
				alreadyExistsMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			// Everything is ok
			else {
				System.out.println("Signup successfull!");
				// App.setRoot("Application");
				alreadyExistsMessage.setVisible(false);
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(false);
			}
		}
	}

	// *********************************************************************
	// Main application page attributes and methods (After login or sign up)
	// *********************************************************************

	@FXML
	private StackPane contentArea;

	@FXML
	void displayDashboard(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void displayEditProfile(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void registerComplaint(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Complaint.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}