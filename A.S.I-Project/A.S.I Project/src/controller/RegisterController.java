package controller;

import model.RegisterService;

// A class that allows a new admin or user to register
// Taking care for all the logics and validations related to showing movies and their details from the DB
// Calls the MovieSearchService to continue operations

public class RegisterController {

	private RegisterService rs;

	public RegisterController() {
		rs = RegisterService.getInstance();
	}

	// Check if the entered email address is already found in the DB
	public void checkEmailExistance(String email) throws Exception {
		if (rs.checkExistanceDB(email))
			throw new Exception("Email already exists.");
	}

	// After validation is completed, receives all user's / admin's input and sends it to the Service
	public void createUser(String first, String last, String ID, String email, String password, int age, String country,
			String phone) {
		if (!email.contains("@asi.il")) {
			rs.addUserDB(first, last, ID, email, password);
		} else
			rs.addAdminDB(first, last, ID, email, password);
		if (age >= 0 ) {
			rs.addAgeDB(email, age);
		}
		if (!country.isEmpty()) {
			rs.addCountryDB(email, country);
		}
		if (phone.equals("0")) {
			rs.addPhoneDB(email, "");
		}
		else {
			rs.addPhoneDB(email, phone);
		}
		rs.writeMapToFile();
	}

	// Validates that the ID input is valid
	public void validateID(String id) throws Exception {
		if (id.length() != 9)
			throw new Exception("ID length is not valid. Please enter 9 digits ID.");
		for (int i = 0; i < id.length(); i++) {
			if (!Character.isDigit(id.charAt(i))) {
				throw new Exception("ID must only include digits.");
			}
		}
	}

	// Validates that the email input is valid
	public void validateEmail(String email) throws Exception {
		if (!email.contains("@") || !email.contains("."))
			throw new Exception("Email address is incorrect. Please enter a valid email address.\n");
	}

	// Validates that the password input is valid
	public void validatePass(String password) throws Exception {
		if (password.length() < 8 || password.length() > 16)
			throw new Exception("Wrong password length.");
	}

}
