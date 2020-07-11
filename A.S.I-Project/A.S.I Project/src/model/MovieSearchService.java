package model;

import java.util.ArrayList;
import java.util.HashMap;

// A singleton class that serves the admin and the user through their controllers
// Handling getting required details from movie DB

public class MovieSearchService {

	private static MovieSearchService mssObj = null;
	private static MovieDB moviedbObj;

	private MovieSearchService() {
		moviedbObj = MovieDB.getInstance();
	}
	
	// Ensures the service has only one instance
	public static MovieSearchService getInstance() {
		if (mssObj == null) {
			mssObj = new MovieSearchService();
		}
		return mssObj;
	}

	// Pulls map from DB and adding the hash keys to a list.
	public boolean putMoviesInList(ArrayList<String> list) throws Exception {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		if (movieMap.isEmpty()) {
			throw new Exception("Database is currently empty.\n");
		}
		list.addAll(movieMap.keySet());
		return true;
	}

	// Pulls map from DB using MovieDB and searching for the searchKey.
	public int searchMovieDB(ArrayList<String> list, String searchKey) {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		if (movieMap.isEmpty())
			return 0;
		else {
			for (String key : movieMap.keySet()) {
				if (key.contains(searchKey)) {
					list.add(key);
				}
			}
			if (list.isEmpty()) {
				return 1;
			}
		}
		return 2;
	}

	// Pulls map from DB using MovieDB and adds its variables into a arrayList.
	public void getDetailsDB(ArrayList<String> list, String name) {
		HashMap<String, Movie> movieMap = moviedbObj.getMap();
		Movie movieObj = movieMap.get(name);
		list.add("Name: " + movieObj.getName());
		list.add("Description: " + movieObj.getDescription());
		list.add("Genre: " + movieObj.getGenre());
		list.add("Length: " + movieObj.getLength());
		list.add("Year: " + movieObj.getYear());
		list.add("Rate: " + movieObj.getRate());
		list.add("Popularity: " + movieObj.getNumOfRents() + " times rented");
		list.add("Price: " + Movie.getPrice() + " NIS");
		return;
	}
}
