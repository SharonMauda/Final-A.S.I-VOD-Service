package view;

import java.util.ArrayList;

import controller.MovieEditController;
import controller.UpdateDBController;

public class AdminMenu extends CommonMenu {

	private MovieEditController mec;
	private UpdateDBController updateObj;
	private ArrayList<String> allMovies = new ArrayList<String>();
	private ArrayList<String> searchResults = new ArrayList<String>();

	public AdminMenu() {
		mec = new MovieEditController();
		updateObj = new UpdateDBController();
	}

	@Override
	public void showMenu(String email) {
		boolean answer;
		boolean menuAgainFlag = true;
		while (menuAgainFlag) {
			System.out.println("Main menu:");
			System.out.println("1. Show movies list");
			System.out.println("2. Search a movie");
			System.out.println("3. Add a movie");
			System.out.println("4. Remove a movie");
			System.out.println("5. Edit a movie");
			System.out.println("6. Update database");
			System.out.println("7. Logout");
			System.out.println("\nPlease choose an option:");
			int choice = intScan();
			switch (choice) {
			case 1:
				boolean result = option1(allMovies);
				if (result) {
					detailsUI(allMovies);
					allMovies.clear();
				}
				System.out.println("You are being directed to main menu.\n");
				break;
			case 2:
				boolean searchAgainFlag = true;
				while (searchAgainFlag) {
					boolean result2 = option2(searchResults);
					if (result2) {
						detailsUI(searchResults);
						searchResults.clear();
					}
					System.out.print("Do you want to perform another search - y/n? ");
					answer = guaranteeInput();
					if (!answer) {
						searchAgainFlag = false;
						System.out.println();
					}
				}
				break;
			case 3:
				boolean addAnotherFlag = true;
				while (addAnotherFlag) {
					option3();
					System.out.print("Do you want to add another movie - y/n? ");
					answer = guaranteeInput();
					if (!answer) {
						addAnotherFlag = false;
					}
				}
				System.out.println();
				break;
			case 4:
				boolean removeAnotherFlag = true;
				while (removeAnotherFlag) {
					boolean result4 = option1(allMovies);
					if (result4) {
						boolean secondResult4 = option4();
						if (secondResult4) {
							System.out.print("Do you want to remove another movie - y/n? ");
							answer = guaranteeInput();
							if (!answer) {
								removeAnotherFlag = false;
							}
						} else {
							removeAnotherFlag = false;
						}
					} else {
						removeAnotherFlag = false;
					}
					if (!allMovies.isEmpty()) {
						allMovies.clear();
					}
				}
				break;
			case 5:
				boolean result5 = option1(allMovies);
				boolean editAnotherFlag = true;
				if (result5) {
					detailsUI(allMovies);
					showMovies(allMovies);
					while (editAnotherFlag) {
						option5();
						System.out.print("Do you want to edit another movie = y/n? ");
						answer = guaranteeInput();
						if (!answer) {
							editAnotherFlag = false;
						}
					}
					allMovies.clear();
				}
				break;
			case 6:
				try {
					updateObj.syncDB();
					System.out.println("Map was written to database file successfully!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("You are being directed to main menu.\n");
				}
				break;
			case 7:
				System.out.print("Are you sure you want to log out - y/n? ");
				answer = guaranteeInput();
				if (answer) {
					try {
						updateObj.syncDB();
						System.out.println("Map was written to database file successfully!");
						menuAgainFlag = false;
						break;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("Continue with logout - y/n?");
						answer = guaranteeInput();
						if (answer) {
							menuAgainFlag = false;
						}
						break;
					}
				}
			default:
				System.out.println("Incorrect input.");
			}
		}
	}

	public void option3() {
		int newLength = 0, newYear = 0;
		String name = null;
		boolean answer;
		boolean existFlag = true;
		while (existFlag) {
			System.out.print("Enter new movie's name: ");
			name = stringScan();
			boolean movieExist = mec.isMovieExist(name);
			if (movieExist) {
				System.out.println("A movie with that name is already exists in the database.");
				System.out.print("Do you want to add another movie - y/n? ");
				answer = guaranteeInput();
				if (!answer) {
					break;
				}
			} else
				existFlag = false;
		}
		if (!existFlag) {
			System.out.print("Enter new movie's description: ");
			String description = stringScan();
			System.out.print("Enter new movie's genre: ");
			String genre = stringScan();
			newLength = lengthAgain();
			newYear = yearAgain();
			try {
				mec.addMovie(name, description, genre, newLength, newYear);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Movie was added successfully!");
		}
	}

	public boolean option4() {
		System.out.println("Please enter a movie number to remove it from the database.");
		System.out.println("Enter 0 to cancel.");
		int answerInt = guaranteeRange(allMovies);
		if (answerInt != 0) {
			String chosenMovie = allMovies.get(answerInt - 1);
			System.out.println("Are you sure you want to delete " + chosenMovie + " - y/n?");
			boolean answer = guaranteeInput();
			if (answer) {
				mec.removeMovie(chosenMovie);
				System.out.println("Movie was successfully removed from the database.");
				return true;
			} else {
				System.out.println("No action was taken.");
				return true;
			}
		}
		return false;
	}

	public void option5() {
		int newYear = 0, newLength = 0;
		System.out.println("Please enter a movie number to edit it in the database.");
		System.out.println("Enter 0 to cancel.");
		int answerInt = guaranteeRange(allMovies);
		if (answerInt != 0) {
			String chosenMovie = allMovies.get(answerInt - 1);
			System.out.println("Enter new name for " + chosenMovie + "(Leave blank to keep old info):");
			String newName = stringScan();
			System.out.println("Enter new description for " + chosenMovie + "(Leave blank to keep old info):");
			String newDesc = stringScan();
			System.out.println("Enter new genre for " + chosenMovie + "(Leave blank to keep old info):");
			String newGenre = stringScan();
			newLength = lengthAgain();
			newYear = yearAgain();
			mec.editMovie(chosenMovie, newName, newDesc, newGenre, newLength, newYear);
			System.out.println("Movie has been edited successfully!");
		} else
			return;
	}

	public int yearAgain() {
		int newYear = 0;
		boolean yearAgain = true;
		while (yearAgain) {
			System.out.print("Enter new movie's year: ");
			newYear = intScan();
			try {
				mec.validateMovieYear(newYear);
				yearAgain = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return newYear;
	}

	public int lengthAgain() {
		int newLength = 0;
		boolean lengthAgain = true;
		while (lengthAgain) {
			System.out.print("Enter new movie's length (number in minutes): ");
			newLength = intScan();
			try {
				mec.validateMovieLength(newLength);
				lengthAgain = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return newLength;
	}
}
