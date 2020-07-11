package model;

import java.util.ArrayList;
import java.util.HashMap;

// A singleton class that serves the user through his controller
// Taking care for getting information from the local maps and writing into them
// Also taking care for writing edited data to the DB files

public class UserRentalService {

	private static UserRentalService urs = null;
	private static MovieDB movieDBObj;
	private static UserDB userDBObj;

	private UserRentalService() {
		movieDBObj = MovieDB.getInstance();
		userDBObj = UserDB.getInstance();
	}
	
	// Ensures the service has only one instance
	public static UserRentalService getInstance() {
		if (urs == null)
			urs = new UserRentalService();
		return urs;
	}

	// Updates Regular_User active movies list by given email.
	public int newRental(String email, String chosenMovie) {
		HashMap<String, User> userMap = userDBObj.getMap();
		HashMap<String, Movie> movieMap = movieDBObj.getMap();
		Regular_User user = (Regular_User) userMap.get(email);
		if (user.getUserMovies().size() > 9) {
			return 0;
		}
		ArrayList<ActiveMovie> currentActive = user.getUserMovies();
		for (ActiveMovie key : currentActive) {
			if (key.getMovie().getName().equals(chosenMovie))
				return 1;
		}
		movieMap = movieDBObj.getMap();
		Movie m = movieMap.get(chosenMovie);
		ActiveMovie newActiveMovie = new ActiveMovie(m);
		user.getUserMovies().add(newActiveMovie);
		movieMap.get(chosenMovie).incNumOfRents();
		return 2;
	}

	// Adds new rate to a given Movie by a given rate.
	public void rateMovieDB(String chosenMovie, int rate) {
		HashMap<String, Movie> movieMap = movieDBObj.getMap();
		Movie movie = movieMap.get(chosenMovie);
		int numOfRates = movie.getNumOfRates();
		int rateSum = movieMap.get(chosenMovie).getRateSum();
		movieMap.get(chosenMovie).setRate((rate + rateSum) / (1+numOfRates));
		movieMap.get(chosenMovie).setRateSum(rateSum + rate);
		writeMapToDB();
	}

	public void writeMapToDB() {
		HashMap<String, Movie> movieMap = movieDBObj.getMap();
		if (movieMap.isEmpty()) {
			return;
		}
		movieDBObj.writeToFile(movieMap);
	}
}
