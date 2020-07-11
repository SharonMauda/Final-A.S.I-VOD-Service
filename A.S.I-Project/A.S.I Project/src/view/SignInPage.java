package view;

import controller.SignInController;

public class SignInPage extends IOValidation {

	private SignInController signControl;
	private MenuFactory mf;
	private CommonMenu cm;

	public SignInPage() {
		signControl = new SignInController();
		mf = new MenuFactory();
		cm = new CommonMenu();
	}

	public void signIn() {
		String email = null;
		boolean wrongSignIn = true;
		while (wrongSignIn) {
			System.out.println("Please enter your sign-in information.");
			boolean wrongEmail = true;
			while (wrongEmail) {
				System.out.print("Enter your email: ");
				email = cm.stringScan();
				try {
					signControl.emailInputValidate(email);
					wrongEmail = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			boolean wrongPass = true;
			while (wrongPass) {
				System.out.print("Enter your password: ");
				String password = cm.stringScan();
				try {
					signControl.is_SignInValid(email, password);
					wrongPass = false;
					wrongSignIn = false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Do you want to try sign in again - y/n?");
					boolean answer = guaranteeInput();
					if (!answer)
						return;
					else {
						wrongPass = false;
					}
				}
			}
		}
		String firstName = signControl.getFirstName(email);
		String lastName = signControl.getLastName(email);
		System.out.println("You are now signed in as " + firstName + " " + lastName + "!");
		cm = mf.menuNavigate(email);
		cm.showMenu(email);
	}
}
