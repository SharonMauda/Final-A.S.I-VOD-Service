package model;

import java.util.HashMap;

// A singleton class that serves the admin and the user for signing in
// Handling getting required information from the local map

public class SignInService {
	
	private static SignInService signinObj = null;
	private static UserDB userdbObj;
	private static HashMap<String, User> userMap;
	
	private SignInService () {
		userdbObj = UserDB.getInstance();
	}
	
	// Ensures the service has only one instance
	public static SignInService getInstance() {
		if (signinObj == null) {
			signinObj = new SignInService();
		}
			userMap = userdbObj.getMap();
			return signinObj;
	}
	
	// Gets input of email and password and validates the user existence on the DB
	public int validateSignIn(String email, String password) {
		HashMap<String, User> userMap = userdbObj.getMap();
		if (!userMap.containsKey(email)) {
			return 1;
		}
		User user = userMap.get(email);
		if (!email.equals(user.getEmail()) || !password.equals(user.getPassword())) {
			return 2;
		}
		else return 3;
	}
	
	// Pulls local map using UserDB and gets First name variable
	public String getFirst (String email) {
		HashMap<String, User> userMap = userdbObj.getMap();
		User user =  userMap.get(email);
		return (user.getFirstname());
	}
	
	// Pulls local map using UserDB and get Last name variable
	public String getLast (String email) {
		HashMap<String, User> userMap = userdbObj.getMap();
		User user = userMap.get(email);
		return (user.getLastname());
	}
	
}
