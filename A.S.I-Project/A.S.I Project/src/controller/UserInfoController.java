package controller;

import java.util.ArrayList;
import java.util.Date;

import model.UserInfoService;

// A class that serves the user through his menu
// Taking care for all the logics and validations related to user's information and input 
// Calls the UserInfoService to continue operations

public class UserInfoController {

	private UserInfoService uis;

	public UserInfoController() {
		uis = UserInfoService.getInstance();
	}

	// Receives the user's email and two empty lists and sends them to the Service to fill the lists
	//		with movie names and rental time remaining for each movie
	public void activeMovies(String email, ArrayList<String> nameList, ArrayList<Long> timeList) {
		uis.getActiveMovie(email, nameList, timeList);
	}

	// Receives the user's email and sends it to the Service
	public boolean isCreditCardValid(String email) {
		boolean result = uis.checkValidCC(email);
		return result;
	}

	// Receives the user's email and chosen movie's name and sends both to the Service
	public void extendRent(String email, String chosenMovie) {
		uis.extendMovie(email, chosenMovie);
	}

	// Receives the user's email and sends it to the Service
	public void refreshUserData(String email) {
		uis.refreshDB(email);
	}

	// Receives the user's email and sends it to the Service
	public void updateUser(String email) {
		uis.writeUserToDB(email);
	}

	// Receives the user's email and sends it to the Service
	public String getUserDetails(String email) {
		return uis.getUserDetailsDB(email);
	}

	// After validation is completed, receives all user's input and sends it to the Service
	public void editUser(String email, String first, String last, int age, String country, String phone) {
		uis.editUserDB(email, first, last, age, country, phone);
	}

	// Validates that the age input is valid
	public void validateAge(int age) throws Exception {
		if (age < 0 || age > 120)
			throw new Exception("Illegal age entered.");
	}

	// Validates that the phone input is valid
	public void validatePhone(String phone) throws Exception {
		if (!phone.startsWith("0"))
			throw new Exception("Phone number must begin with 0.");
		for (int i = 0; i < phone.length(); i++) {
			if (!Character.isDigit(phone.charAt(i))) {
				throw new Exception("Phone number must only include digits.");
			}
		}
		if (!phone.equals("0") && phone.length() < 10)
			throw new Exception("Phone number must include 10 digits");
	}

	// Validates that the credit card input is valid
	public void validateCCNumber(String cc) throws Exception {
		for (int i = 0; i < cc.length(); i++) {
			if (!Character.isDigit(cc.charAt(i))) {
				throw new Exception("Credit card number must only include digits.");
			}
			if (cc.length() < 9 || cc.length() > 16) {
				throw new Exception("Credit card number must contain 9-16 digits.");
			}
		}
	}

	// Receives the user's email and sends it to the Service
	public String getUserCC(String email) {
		return uis.getUserCCDB(email);
	}

	// Validates that the credit card's expiration date input is valid
	@SuppressWarnings("deprecation")
	public void validateED(int month, int year) throws Exception {
		if (month < 1 || month > 12 || year < 1900)
			throw new Exception("Illegal month or year entered.");
		Date currentTime = new Date();
		if (currentTime.after(new Date(year - 1900, month - 1, 0)))
			throw new Exception("Illegal expiration date.");

	}

	// Validates that the credit's card three digits input is valid
	public void validateThree(int three) throws Exception {
		if (three > 999 || three < 100)
			throw new Exception("Illegal three digit input.");
	}

	// After validation is completed, receives all user's credit card's input and sends it to the Service
	public void updateCC(String email, String ccNum, int month, int year, int three) {
		uis.updateCCDB(email, ccNum, month, year, three);
	}

}
