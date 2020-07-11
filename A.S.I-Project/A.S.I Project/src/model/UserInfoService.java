package model;

import java.util.ArrayList;
import java.util.HashMap;

// A singleton class that serves the admin and the user through their controllers
// Taking care for getting information from the local map and writing into it and also writing into the DB files

public class UserInfoService {

	private static UserInfoService uis = null;
	private static UserDB userDBObj;

	private UserInfoService() {
		userDBObj = UserDB.getInstance();
	}

	// Ensures the service has only one instance
	public static UserInfoService getInstance() {
		if (uis == null)
			uis = new UserInfoService();
		return uis;
	}

	// Getting information from the local map and updating two empty lists with required information
	public void getActiveMovie(String email, ArrayList<String> nameList, ArrayList<Long> timeList) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		ArrayList<ActiveMovie> activeMovies = user.getUserMovies();
		if (!activeMovies.isEmpty()) {
			for (ActiveMovie key : activeMovies) {
				nameList.add(key.getMovie().getName());
				timeList.add(key.getRemainingTime());
			}
		}
	}

	// Checks validation of credit card values with a given email input.
	public boolean checkValidCC(String email) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		return !((user.getExpiration_Month() == 0) || (user.getExpiration_Year() == 0)
				|| (user.getCredit_Card_Num() == null || user.getThree_digits() == 0));
	}
	
	// Checks existence of a given movie name in Regular_user by email and extend the rental time.
	public void extendMovie(String email, String chosenMovie) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		ArrayList<ActiveMovie> activeMovies = user.getUserMovies();
		for (ActiveMovie key : activeMovies) {
			if (key.getMovie().getName().equals(chosenMovie)) {
				key.extendOrderTime();
			}
		}
	}

	// Checks if any of the given Regular_user by email  movies is out of renting time and removes if true.
	public void refreshDB(String email) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		ArrayList<ActiveMovie> activeMovies = user.getUserMovies();
		for (ActiveMovie key : activeMovies) {
			if (key.getRemainingTime() <= 0) {
				activeMovies.remove(key);
			}
		}
	}

	// Return toString function of a given User by email.
	public String getUserDetailsDB(String email) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		return user.toString();
	}

	// Edits an existing User and creates a new one with the new edited variables
	public void editUserDB(String email, String first, String last, int age, String country, String phone) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User) userMap.get(email);
		if (!first.equals(""))
			user.setFirstname(first);
		if (!last.equals(""))
			user.setLastname(last);
		if (age != 0)
			user.setAge(age);
		if (!country.equals(""))
			user.setCountry(country);
		if (phone.equals("0"))
			user.setPhoneNumber(user.getPhoneNumber());
		else {
			user.setPhoneNumber(phone);
		}
		userMap.remove(email);
		userMap.put(email, user);
	}

	// Writes local map to File
	public void writeUserToDB(String email) {
		HashMap<String, User> userMap = userDBObj.getMap();
		if (userMap.isEmpty()) {
			return;
		}
		userDBObj.writeToFile(userMap);
	}

	// Returns a String of user credit card information.
	public String getUserCCDB(String email) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		return user.CCtoString();
	}

	// Updates credit card information to a given user by email
	public void updateCCDB(String email, String ccNum, int month, int year, int three) {
		HashMap<String, User> userMap = userDBObj.getMap();
		Regular_User user = (Regular_User)userMap.get(email);
		user.setCredit_Card_Num(ccNum);
		user.setExpiration_Year(year);
		user.setExpiration_Month(month);
		user.setThree_digits(three);
	}

}
