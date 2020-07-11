package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

// A singleton class which is responsible to edit the movie DB and read from it

public class MovieDB {
	
	private static MovieDB searchdb = null;
	private static HashMap<String, Movie> moviesMap = new HashMap<String, Movie>();
	private static String moviefilepath = "D:/MovieDB.txt";
	
	// Ensures the service has only one instance
	public static MovieDB getInstance() {
		if (searchdb == null) {
			searchdb = new MovieDB();
		}
		moviesMap = readFromFile(moviesMap);
		return searchdb;
	}
	
	public HashMap<String, Movie> getMap(){
		return moviesMap;
	}
	
	//read map from file and handles Exceptions if any occurred
	public static HashMap<String, Movie> readFromFile (HashMap<String, Movie> map) {
		File file = new File(moviefilepath);
		try {
			if (!file.exists()) {
				file.createNewFile();
				return map;
			}
			FileInputStream fIn = new FileInputStream(moviefilepath);
			if (fIn.available() == 0) {
				fIn.close();
				return map;
			}
			ObjectInputStream objIn = new ObjectInputStream(fIn);
			map = (HashMap<String, Movie>) objIn.readObject();
			fIn.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
		} catch (IOException e) {
			System.out.println("Error intializing stream.");
		} catch (ClassNotFoundException e) {
			System.out.println("Class could not be found");
		}
		return map;
	}

	//write map to file and handles Exceptions if any occurred.
	public boolean writeToFile (HashMap<String, Movie> map) {
		try {
			FileOutputStream fOut = new FileOutputStream(moviefilepath);
			new FileOutputStream(moviefilepath).close();
			ObjectOutputStream objOut = new ObjectOutputStream(fOut);
			objOut.writeObject(map);
			objOut.close();
			fOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			return false;
		} catch (IOException e) {
			System.out.println("Error intializing stream");
			return false;
		}
		return true;
	}
}
