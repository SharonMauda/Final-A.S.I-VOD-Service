package model;

import java.util.HashMap;

// A singleton class that serves the admin through his contoller
// Handling getting the movie map and write it into the DB file

public class UpdateDBService {

	private static UpdateDBService updateDB = null;
	private static MovieDB moviedbObj;
	
	private UpdateDBService() {
		moviedbObj = MovieDB.getInstance();
	}
	
	// Ensures the service has only one instance
	public static UpdateDBService getInstance() {
		if (updateDB == null) {
			updateDB = new UpdateDBService();
		}
		return updateDB;
	}
	
	// Pulls map using movieDB checks if the local map is empty if not call movieDB with the given map
	public boolean mapToFile() {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		if (movieMap.isEmpty()) {
			return false;
		}
		moviedbObj.writeToFile(movieMap);
		return true;
	}
	
}
