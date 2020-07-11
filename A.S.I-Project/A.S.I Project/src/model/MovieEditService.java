package model;

import java.util.HashMap;

// A singleton class that serves the admin through his controller
// Handling changes made by admin to movie objects

public class MovieEditService {

	private static MovieEditService mesObj = null;
	private static MovieDB moviedbObj;

	private MovieEditService() {
		moviedbObj = MovieDB.getInstance();
	}
	
	// Ensures the service has only one instance
	public static MovieEditService getInstance() {
		if (mesObj == null) {
			mesObj = new MovieEditService();
		}
		return mesObj;
	}
	
	// Checks if the movie on the map with the name of the movie as the hash key.
	public boolean checkExistanceDB(String name) {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		if (!movieMap.isEmpty()) {
			for (String key : movieMap.keySet()) {
				if (key.equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// Create a Movie object sets the input variables and add it to the map.
	public void addMovieDB(String name, String description, String genre, int length, int year) {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		Movie movieObj = new Movie();
		movieObj.setName(name);
		movieObj.setDescription(description);
		movieObj.setGenre(genre);
		movieObj.setLength(length);
		movieObj.setYear(year);
		movieMap.put(name, movieObj);
	}
	
	// Removes a Movie object from map
	public void removeMovieDB(String name) {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		movieMap.remove(name);
	}
	
	// Edits the object found by the hash key by the user's input.
	public void editMovieDB(String chosenMovie, String newName, String desc, String genre, int length, int year) {
		String rightName = newName;
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		Movie oldMovie = movieMap.get(chosenMovie);
		Movie newMovie = new Movie();
		if (newName.equals("")) {
			rightName = oldMovie.getName();
			newMovie.setName(oldMovie.getName());}
		else {
			newMovie.setName(newName);
		}
		if (desc.equals("")) {
			newMovie.setDescription(oldMovie.getDescription());
		} else
			newMovie.setDescription(desc);
		if (genre.equals("")) {
			newMovie.setGenre(oldMovie.getGenre());
		} else
			newMovie.setGenre(genre);
		if (length != 0) {
			newMovie.setLength(length);
		} else
			newMovie.setLength(oldMovie.getLength());
		if (year != 0) {
			newMovie.setYear(year);
		} else
			newMovie.setYear(oldMovie.getYear());
		movieMap.remove(chosenMovie);
		movieMap.put(rightName, newMovie);
	}
}
