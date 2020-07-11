package controller;

import java.util.ArrayList;

import model.MovieSearchService;

// A class that serves both admin and user through their menus
// Taking care for all the logics and validations related to showing movies and their details from the DB
// Calls the MovieSearchService to continue operations

public class MovieSearchController {

	private MovieSearchService mss;

	public MovieSearchController() {
		mss = MovieSearchService.getInstance();
	}

	// Receives an empty list and calls the Service to add all movies from DB into the list
	public boolean showAllMovies(ArrayList<String> list) {
		try {
			mss.putMoviesInList(list);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// Receives an empty list and a string from the user
	// Calls the Service to perform a search in the DB and fill the list with movie names
	public void searchMovie(ArrayList<String> list, String searchKey) throws Exception {
			int result = mss.searchMovieDB(list, searchKey);
			switch (result) {
			case 0:
				throw new Exception("Database is currently empty.");
			case 1:
				throw new Exception("No movies were found.");
			case 2:
				return;
			}
	}

	// Receives an empty list and a string from the user
	// Calls the Service to perform a search in the DB and fill the list with chosen movie's details
	public void getMovieDetails(ArrayList<String> list, String name) {
		mss.getDetailsDB(list, name);
		return;
	}

}
