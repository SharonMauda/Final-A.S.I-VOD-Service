package JUnitTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.RegisterController;

class RegisterControllerTest {

	private static RegisterController rc;

	@BeforeAll
	public static void setup() {
		rc = new RegisterController();
	}

	@Test
	void checkEmailExistanceTest() {
		try {
			rc.checkEmailExistance("itay@gmail.com");// email must be in DB
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email already exists.", e.getMessage());
		}
	}

	@Test
	void validateIDTestUnder9() {
		try {
			rc.validateID("12345");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("ID length is not valid. Please enter 9 digits ID.", e.getMessage());
		}
	}

	@Test
	void validateIDTestOver9() {
		try {
			rc.validateID("1234567890");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("ID length is not valid. Please enter 9 digits ID.", e.getMessage());
		}
	}

	@Test
	void validateIDTestDigitsOnly() {
		try {
			rc.validateID("adas78989");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("ID must only include digits.", e.getMessage());
		}
	}

	@Test
	void validateEmailTest() {
		try {
			rc.validateEmail("adas7898");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Email address is incorrect. Please enter a valid email address.\n",
					e.getMessage());
		}
	}

	@Test
	void validatePassTestUnder8() {
		try {
			rc.validatePass("adas");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Wrong password length.", e.getMessage());
		}
	}

	@Test
	void validatePassTestOver16() {
		try {
			rc.validatePass("adas123456789abse");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Wrong password length.", e.getMessage());
		}
	}
}
