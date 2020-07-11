package model;

import java.util.HashMap;

// A singleton class that serves the admin and the user for registration
// Handling getting data from the local map and writing into it

public class RegisterService {

	private static RegisterService rsObj = null;
	private static UserDB userdbObj;
	private static int AdminIDcount = 1;

	private RegisterService() {
		userdbObj = UserDB.getInstance();
	}

	// Ensures the service has only one instance
	public static RegisterService getInstance() {
		if (rsObj == null) {
			rsObj = new RegisterService();
		}
		return rsObj;
	}

	// Checks if the User on the map with the Email of the user as the hash key.
	public boolean checkExistanceDB(String email) {
		HashMap<String, User> userMap = userdbObj.getMap();
		if (!userMap.isEmpty()) {
			for (String key : userMap.keySet()) {
				if (key.equals(email)) {
					return true;
				}
			}
		}
		return false;
	}

	// Pulls local map using UserDB sets the variables given from the input and adds the new User to the map.
	public void addUserDB(String first, String last, String ID, String email, String password) {
		HashMap<String, User> userMap = userdbObj.getMap();
		Regular_User userObj = new Regular_User();
		userObj.setFirstname(first);
		userObj.setLastname(last);
		userObj.setID(ID);
		userObj.setEmail(email);
		userObj.setPassword(password);
		userMap.put(email, userObj);
	}

	// Pulls local map using UserDB sets the variables given from the input and adds the new Admin to the map.
	public void addAdminDB(String first, String last, String ID, String email, String password) {
		HashMap<String, User> userMap = userdbObj.getMap();
		Admin adminObj = new Admin();
		adminObj.setFirstname(first);
		adminObj.setLastname(last);
		adminObj.setID(ID);
		adminObj.setEmail(email);
		adminObj.setPassword(password);
		userMap.put(email, adminObj);
	}

	// Pulls local map and sets age variable to a User given by email
	public void addAgeDB(String email, int age) {
		HashMap<String, User> userMap = userdbObj.getMap();
		User user = userMap.get(email);
		user.setAge(age);
	}
	
	// Pulls local map and sets country variable to a User given by email
	public void addCountryDB(String email, String country) {
		HashMap<String, User> userMap = userdbObj.getMap();
		User user = userMap.get(email);
		user.setCountry(country);
	}
	
	// Pulls local map and sets phone variable to a User given by email
	public void addPhoneDB(String email, String phone) {
		HashMap<String, User> userMap = userdbObj.getMap();
		User user = userMap.get(email);
		user.setPhoneNumber(phone);
	}
	
	public void writeMapToFile () {
		HashMap<String, User> userMap = userdbObj.getMap();
		userdbObj.writeToFile(userMap);
	}
}
