package view;

import java.util.ArrayList;

import controller.MovieSearchController;

public class CommonMenu extends IOValidation {

	private MovieSearchController msc;

	public CommonMenu() {
		msc = new MovieSearchController();
	}

	public void showMenu(String email) {

	}

	public boolean option1(ArrayList<String> allMovies) {
		allMovies.clear();
		boolean result = msc.showAllMovies(allMovies);
		if (!result) {
			System.out.println("Database is currently empty.\n");
			return false;
		}
		System.out.println("Showing all movies currently in the database:");
		for (int i = 0; i < allMovies.size(); i++) {
			System.out.println(i + 1 + ". " + allMovies.get(i));
		}
		System.out.println();
		return true;
	}

	public boolean option2(ArrayList<String> searchResults) {
		searchResults.clear();
		System.out.print("Search for: ");
		String searchKey = stringScan();
		try {
			msc.searchMovie(searchResults, searchKey);
			showMovies(searchResults);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		System.out.println();
		return true;
	}

	public ArrayList<String> getMovieDetails(ArrayList<String> list, int moviechoice) {
		ArrayList<String> movieDetails = new ArrayList<String>();
		String chosenMovie = list.get(moviechoice - 1);
		msc.getMovieDetails(movieDetails, chosenMovie);
		return movieDetails;
	}

	public void detailsUI(ArrayList<String> list) {
		boolean showDetailsFlag = true;
		while (showDetailsFlag) {
			System.out.println("Enter a movie number to see its details.");
			System.out.println("Enter 0 to continue.");
			int answerInt = guaranteeRange(list);
			if (answerInt != 0) {
				ArrayList<String> details = new ArrayList<String>();
				details = getMovieDetails(list, answerInt);
				for (int i = 0; i < details.size(); i++) {
					System.out.println(details.get(i));
				}
				details.clear();
				System.out.print("Do you want to show another movie's details - y/n? ");
				boolean answer = guaranteeInput();
				if (!answer) {
					showDetailsFlag = false;
				} else {
					showMovies(list);
				}
			} else
				showDetailsFlag = false;
		}
	}

	public void showMovies(ArrayList<String> list) {
		System.out.println("Showing Movies list:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + ". " + list.get(i));
		}
	}
}
