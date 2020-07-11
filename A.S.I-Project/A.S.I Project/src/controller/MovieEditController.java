package controller;

import model.MovieEditService;

// A class that serves the admin through his menu
// Taking care for all the logics and validations related to changes made by admin over the movie DB
//		and admin's input
// Calls the MovieEditService to continue operations 

public class MovieEditController {

	private MovieEditService mesObj;

	public MovieEditController() {
		mesObj = MovieEditService.getInstance();
	}

	// Validates the admin's input before the Service creates the movie object
	public void addMovie(String name, String description, String genre, int length, int year) {
		if (name.equals("") || description.equals("") || genre.equals(""))
			throw new IllegalArgumentException("Field must not be blank.\n");
		mesObj.addMovieDB(name, description, genre, length, year);
	}

	// Validates that there's no movie with the exact name already in the DB
	// Returns true if there's already a movie with that name in the DB
	// Otherwise - returns false
	public boolean isMovieExist(String name) {
		boolean result = mesObj.checkExistanceDB(name);
		return result;
	}

	// Receives the chosen movie string and sends it to the Service 
	public void removeMovie(String name) {
		mesObj.removeMovieDB(name);
	}

	// Validates admin's input about new movie's length
	public void validateMovieLength(int length) throws Exception {
		if (length < 0)
			throw new Exception("Length can't be under 0 minutes.\n");
	}
	
	// Validates admin's input about new movie's year
	public void validateMovieYear(int year) throws Exception {
		if ((year < 1800 && year != 0) || year > 2020)
			throw new Exception("Year is not valid.\n");
	}
	
	// After validation is completed, receives all admin's input and sends it to the Service
	public void editMovie(String chosenMovie, String newName, String desc, String genre, int length, int year) {
		mesObj.editMovieDB(chosenMovie, newName, desc, genre, length, year);
	}

}
