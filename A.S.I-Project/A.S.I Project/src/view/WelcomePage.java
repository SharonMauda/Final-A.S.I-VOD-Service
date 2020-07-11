package view;

public class WelcomePage extends IOValidation {
	
	private SignInPage signPage = new SignInPage();
	private RegistrationPage rp = new RegistrationPage();

	public void welcome() {
		boolean welcomeFlag = true;
		while (welcomeFlag) {
			System.out.println("Welcome to A.S.I VOD Service!");
			System.out.println("Menu:");
			System.out.println("1. Sign-in");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			int choice = intScan();
			switch (choice) {
			case 1:
				signPage.signIn();
				break;
			case 2:
				rp.showRegisterMenu();
				break;
			case 3:
				System.exit(0);
			default:
				while (choice < 1 || choice > 3) {
					System.out.print("Incorrect input. Please try again: ");
					choice = intScan();
				}
			}
		}
	}

	public static void main(String args[]) {
		WelcomePage newMenu = new WelcomePage();
		newMenu.welcome();
	}

	
	
}
