package view;

import controller.RegisterController;
import controller.UserInfoController;

public class RegistrationPage extends IOValidation {

	private boolean answer;
	private RegisterController rc;
	private UserInfoController uic;

	public RegistrationPage() {
		rc = new RegisterController();
		uic = new UserInfoController();
	}

	public void showRegisterMenu() {
		String id = null, password = null, email = null, first = null, last = null, phone = null;
		int age = 0;
		System.out.println("Welcome to A.S.I registration process.");
		System.out.println("This will guide you step by step for creating your account.");
		System.out.print("Cotninue? - y/n? ");
		answer = guaranteeInput();
		if (answer) {
			System.out.println("Note: fields marked with * are mandatory and must not be blank.");
			System.out.print("Please enter your first name*: ");
			first = mandatoryString();
			System.out.print("Please enter your last name*: ");
			last = mandatoryString();
			boolean idAgain = true;
			while (idAgain) {
				System.out.print("Please enter your ID*: ");
				id = stringScan();
				try {
					rc.validateID(id);
					idAgain = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			boolean emailFormat = true;
			while (emailFormat) {
				System.out.print("Please enter your email*: ");
				email = stringScan();
				try {
					rc.validateEmail(email);
					emailFormat = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			boolean emailExist = true;
			while (emailExist) {
				try {
					rc.checkEmailExistance(email);
					emailExist = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.print("Please enter another email address: ");
					email = stringScan();
				}
			}
			boolean passAgain = true;
			while (passAgain) {
				System.out.print("Please choose a password*: ");
				System.out.println("The password must contain 8-16 characters.");
				password = stringScan();
				try {
					rc.validatePass(password);
					passAgain = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			boolean ageAgain = true;
			while (ageAgain) {
				System.out.println("Enter your age:");
				age = intScan();
				try {
					uic.validateAge(age);
					ageAgain = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.print("Please enter your country (leave blank to skip): ");
			String country = stringScan();
			boolean phoneAgain = true;
			while (phoneAgain) {
				System.out.println("Enter your phone number (enter 0 to skip): ");
				phone = stringScan();
				try {
					uic.validatePhone(phone);
					phoneAgain = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			rc.createUser(first, last, id, email, password, age, country, phone);
			System.out.println("Registeration completed!");
		}
	}

	public String mandatoryString() {
		boolean repeat = true;
		String input = null;
		while (repeat) {
			input = stringScan();
			if (!input.isEmpty()) {
				repeat = false;
			} else {
				System.out.println("This field must not be blank.");
				System.out.print("Please try again: ");
			}
		}
		return input;
	}
}
