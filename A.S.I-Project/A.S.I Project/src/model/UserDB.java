package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

// A singleton class which is responsible to edit the user DB and read from it

public class UserDB {

	private static UserDB userdbObj = null;
	private static HashMap<String, User> userMap = new HashMap<String, User>();
	private static String userfilepath = "D:/UserDB.txt";

	// Ensures the model has only one instance
	public static UserDB getInstance() {
		if (userdbObj == null) {
			userdbObj = new UserDB();
		}
		userMap = readFromFile(userMap);
		return userdbObj;
	}

	// Returns local map
	public HashMap<String, User> getMap() {
		return userMap;
	}

	// Read map from file and handles Exceptions if any occurred
	public static HashMap<String, User> readFromFile(HashMap<String, User> map) {
		File file = new File(userfilepath);
		try {
			if (!file.exists()) {
				file.createNewFile();
				return map;
			}
			FileInputStream fIn = new FileInputStream(userfilepath);
			if (fIn.available() == 0) {
				fIn.close();
				return map;
			}
			ObjectInputStream objIn = new ObjectInputStream(fIn);
			map = (HashMap<String, User>) objIn.readObject();
			fIn.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Class could not be found");
		}
		return map;
	}

	// Write map to file and handles Exceptions if any occurred
	public void writeToFile(HashMap<String, User> map) {
		try {
			FileOutputStream fOut = new FileOutputStream(userfilepath);
			new FileOutputStream(userfilepath).close();
			ObjectOutputStream objOut = new ObjectOutputStream(fOut);
			objOut.writeObject(map);
			objOut.close();
			fOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
		} catch (IOException e) {
			System.out.println("Error intializing stream");
		}
	}
}
