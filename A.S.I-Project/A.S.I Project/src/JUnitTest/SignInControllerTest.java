package JUnitTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.SignInController;

class SignInControllerTest {

	private static SignInController sic;
	
	@BeforeAll
	public static void setup() {
		sic = new SignInController();
	}
	
	@Test
	void is_SignInValidTestBlankEmail() {
		try {
			sic.is_SignInValid("","123");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email / Password is blank. Please fill in the required fields.", e.getMessage());
		}
	}
	
	@Test
	void is_SignInValidTestNotFoundEmail() {
		try {
			sic.is_SignInValid("444","123");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email address couldn't be found.", e.getMessage());
		}
	}
	
	@Test
	void is_SignInValidTestIncorrectDetails() {
		try {
			sic.is_SignInValid("itay@gmail.com","123");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email / Password is incorrect.", e.getMessage());
		}
	}

	@Test
	void is_SignInValidTestBlankPassword() {
		try {
			sic.is_SignInValid("itay@gmail.com","");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email / Password is blank. Please fill in the required fields.", e.getMessage());
		}
	}
	
	@Test
	void emailInputValidateTestNoStrdl() {
		try {
			sic.emailInputValidate("itaygmail.com");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email address is incorrect. Please enter a valid email address.\n", e.getMessage());
		}
	}
	@Test
	void emailInputValidateTestNoDot() {
		try {
			sic.emailInputValidate("itay@gmailcom");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email address is incorrect. Please enter a valid email address.\n", e.getMessage());
		}
	}
}
