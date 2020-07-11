package controller;

import model.SignInService;

// A class that allows an existing admin or user to sign-in
// Taking care for all the logics and validations related to signing-in process
// Calls the SignInService to continue operations

public class SignInController {

	private SignInService sisObj;

	public SignInController() {
		sisObj = SignInService.getInstance();
	}

	// Validates that neither email nor password is blank and sends both to the Service
	public void is_SignInValid(String email, String password) throws Exception {
		if (email.equals("") || password.equals(""))
			throw new Exception("Email / Password is blank. Please fill in the required fields.");
		int result = sisObj.validateSignIn(email, password);
		switch (result) {
		case 1:
			throw new Exception("Email address couldn't be found.");
		case 2:
			throw new Exception("Email / Password is incorrect.");
		case 3:
			return;
		}
	}

	// Receives the email address of the user / admin and sends it to the Service to get their first name
	public String getFirstName(String email) {
		String firstName = sisObj.getFirst(email);
		return firstName;
	}

	// Receives the email address of the user / admin and sends it to the Service to get their last name
	public String getLastName(String email) {
		String lastName = sisObj.getLast(email);
		return lastName;
	}

	// Validates that the email input contains the mandatory characters
	public void emailInputValidate(String email) throws Exception {
			if (!email.contains("@") || !email.contains("."))
				throw new Exception("Email address is incorrect. Please enter a valid email address.\n");
	}

}
