package controller;

import model.UserRentalService;

// A class that serves the user through his menu
// Taking care for all the logics and validations related to user's information and input
// Calls the UserRentalService to continue operations

public class UserRentalController {

	private UserRentalService urs;

	public UserRentalController() {
		urs = UserRentalService.getInstance();
	}

	// Receives the user's email and the name of the user's chosen movie
	// Sends both to the Service
	public void rentMovie(String email, String chosenMovie) throws Exception {
		int result = urs.newRental(email, chosenMovie);
		switch (result) {
		case 0:
			throw new Exception("You have reached the maximum amount of rented movies.\n");
		case 1:
			throw new Exception("Movie is already rented.\n");
		case 2:
			return;
		}
	}

	// Receives user's chosen movie's name and rate number
	// Validates that the rate input is valid
	// Sends the info to the Service
	public void rateMovie(String chosenMovie, int rate) throws Exception {
		if (rate < 1 || rate > 5) {
			throw new Exception("Invaild rate number.\n");
		}
		urs.rateMovieDB(chosenMovie, rate);
	}

	public void updateMap() {
		urs.writeMapToDB();
	}
	
}
