package view;

import java.util.ArrayList;

import controller.UserInfoController;
import controller.UserRentalController;

public class UserMenu extends CommonMenu {

	private ArrayList<String> allMovies = new ArrayList<String>();
	private ArrayList<String> searchResults = new ArrayList<String>();
	private ArrayList<String> activeMovies = new ArrayList<String>();
	private ArrayList<Long> timeRemaining = new ArrayList<Long>();
	private UserInfoController uic;
	private UserRentalController urc;

	public UserMenu() {
		uic = new UserInfoController();
		urc = new UserRentalController();
	}

	@Override
	public void showMenu(String email) {
		refreshData(email);
		boolean menuAgainFlag = true;
		while (menuAgainFlag) {
			System.out.println("Main menu:");
			System.out.println("1. Show movies list");
			System.out.println("2. Search a movie");
			System.out.println("3. Show my active movies");
			System.out.println("4. Rent a movie");
			System.out.println("5. Extend an active rental");
			System.out.println("6. Rate a movie");
			System.out.println("7. Show / Edit profile");
			System.out.println("8. Show / update credit card info");
			System.out.println("9. Logout");
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
					boolean answer = guaranteeInput();
					if (!answer) {
						searchAgainFlag = false;
						System.out.println();
					}
				}
				break;
			case 3:
				refreshData(email);
				option3(email);
				System.out.println("You are being directed to main menu.\n");
				break;
			case 4:
				refreshData(email);
				result = uic.isCreditCardValid(email);
				if (!result) {
					System.out.println("Credit card info is invalid / doesn't exist.");
					System.out.println("You are being redirected to main menu.");
					break;
				}
				result = option1(allMovies);
				if (result) {
					detailsUI(allMovies);
				}
				option4(email);
				uic.updateUser(email);
				urc.updateMap();
				break;
			case 5:
				refreshData(email);
				result = option3(email);
				if (!result) {
					System.out.println("You are being directed to main menu.\n");
					break;
				}
				System.out.println("Select a movie to extend its current rental time: ");
				System.out.println("Press 0 to go back to main menu.");
				int answerInt = guaranteeRange(activeMovies);
				if (answerInt != 0) {
					String chosenMovie = activeMovies.get(answerInt - 1);
					uic.extendRent(email, chosenMovie);
					System.out.println("Movie was extended successfully.");
					uic.updateUser(email);
				}
				System.out.println("You are being directed to main menu.");
				break;
			case 6:
				result = option1(allMovies);
				if (!result) {
					System.out.println("You are being directed to main menu.");
					break;
				}
				System.out.println("Select a movie to rate: ");
				System.out.println("Press 0 to go back to main menu.");
				answerInt = guaranteeRange(allMovies);
				if (answerInt != 0) {
					String chosenMovie = allMovies.get(answerInt - 1);
					boolean rateAgain = true;
					while (rateAgain) {
						System.out.println("Rate " + chosenMovie + " from 1 to 5: ");
						answerInt = intScan();
						try {
							urc.rateMovie(chosenMovie, answerInt);
							System.out.println("Movie was rated successfully.");
							rateAgain = false;
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					System.out.println("You are being directed to main menu.\n");
					break;
				}
				System.out.println("You are being directed to main menu.\n");
				break;
			case 7:
				int newAge = 0;
				String newPhone = null;
				System.out.println(uic.getUserDetails(email));
				System.out.print("Do you want to edit profile details - y/n? ");
				boolean answer = guaranteeInput();
				if (!answer) {
					System.out.println("You are being directed to main menu.\n");
					break;
				}
				System.out.println("Enter new first name (Leave blank to keep old info):");
				String newFirst = stringScan();
				System.out.println("Enter new last name (Leave blank to keep old info):");
				String newLast = stringScan();
				boolean ageAgain = true;
				while (ageAgain) {
					System.out.println("Enter new age (Enter 0 to keep old info):");
					newAge = intScan();
					try {
						uic.validateAge(newAge);
						ageAgain = false;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				System.out.println("Enter new country (Leave blank to keep old info):");
				String newCountry = stringScan();
				boolean phoneAgain = true;
				while (phoneAgain) {
					System.out.println("Enter new phone number (Enter 0 to keep old info):");
					newPhone = stringScan();
					try {
						uic.validatePhone(newPhone);
						phoneAgain = false;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				uic.editUser(email, newFirst, newLast, newAge, newCountry, newPhone);
				System.out.println("Profile was edited successfully.");
				uic.updateUser(email);
				System.out.println("You are being directed to main menu.\n");
				break;
			case 8:
				result = uic.isCreditCardValid(email);
				if (result) {
					System.out.println(uic.getUserCC(email));
					System.out.print("Do you want to edit your crdeit card details - y/n? ");
					answer = guaranteeInput();
					if (!answer) {
						System.out.println("You are being directed to main menu.\n");
						break;
					}
					updateCC(email);
					System.out.println("Showing new credit card info: ");
					System.out.println(uic.getUserCC(email));
				} else {
					System.out.println("Credit card info is invalid / doesn't exist.");
				}
				System.out.print("Do you want to edit your crdeit card details - y/n? ");
				answer = guaranteeInput();
				if (!answer) {
					System.out.println("You are being directed to main menu.\n");
					break;
				}
				updateCC(email);
				System.out.println("Showing new credit card info: ");
				System.out.println(uic.getUserCC(email) + "\n");
				uic.updateUser(email);
				System.out.println("You are being directed to main menu.\n");
				break;
			case 9:
				System.out.print("Are you sure you want to log out - y/n? ");
				answer = guaranteeInput();
				if (answer) {
					refreshData(email);
					uic.updateUser(email);
					menuAgainFlag = false;
					break;
				}
				System.out.println("You are being directed to main menu.\n");
				break;
			default:
				System.out.println("Incorrect input.\n");
			}
		}

	}

	private boolean option3(String email) {
		activeMovies.clear();
		timeRemaining.clear();
		uic.activeMovies(email, activeMovies, timeRemaining);
		if (!activeMovies.isEmpty()) {
			System.out.println("Showing currently active movies: ");
			for (int i = 0; i < activeMovies.size(); i++) {
				System.out.println((i + 1 + ". Name: " + activeMovies.get(i) + ". Time remaining: "
						+ timeRemaining.get(i) + " hours."));
			}
			return true;
		} else {
			System.out.println("No movies were rented yet.");
			return false;
		}
	}

	private void option4(String email) {
		boolean rentAgain = true;
		while (rentAgain) {
			showMovies(allMovies);
			System.out.println("Select a movie to begin rental process: ");
			System.out.println("Press 0 to go back to main menu.");
			int answerInt = guaranteeRange(allMovies);
			if (answerInt != 0) {
				String chosenMovie = allMovies.get(answerInt - 1);
				try {
					urc.rentMovie(email, chosenMovie);
					System.out.println("Movie was rented for 48 hours successfully.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					if (!e.getMessage().equals("Movie is already rented.\n"))
						rentAgain = false;
				}
				System.out.print("Do you want to rent another movie - y/n? ");
				boolean answer = guaranteeInput();
				if (!answer) {
					rentAgain = false;
					System.out.println();
				}
			} else
				rentAgain = false;
		}
	}

	public void refreshData(String email) {
		uic.refreshUserData(email);
	}

	public void updateCC(String email) {
		String ccNum = null;
		int year = 0, month = 0, three = 0;
		boolean ccAgain = true;
		while (ccAgain) {
			System.out.print("Enter your credit card number: ");
			ccNum = stringScan();
			try {
				uic.validateCCNumber(ccNum);
				ccAgain = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		boolean edAgain = true;
		while (edAgain) {
			System.out.print("Enter your credit card expiration month: ");
			month = intScan();
			System.out.print("Enter your credit card expiration year: ");
			year = intScan();
			try {
				uic.validateED(month, year);
				edAgain = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		boolean digitAgain = true;
		while (digitAgain) {
			System.out.print("Enter your credit card 3 digit numbers: ");
			three = intScan();
			try {
				uic.validateThree(three);
				digitAgain = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		uic.updateCC(email, ccNum, month, year, three);
	}

}