package JUnitTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.UserInfoController;
 

class UserInfoControllerTest {

	private static UserInfoController uic;
	
	@BeforeAll
	public static void setup() {
		uic = new UserInfoController();
	}
	
	@Test
	void validateAgeTestUnder0() {
		try {
			uic.validateAge(-10);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal age entered.", e.getMessage());
		}
	}
	
	@Test
	void validateAgeTestOver120() {
		try {
			uic.validateAge(300);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal age entered.", e.getMessage());
		}
	}
	
	@Test
	void validatePhoneTest0atBegining() {
		try {
			uic.validatePhone("1234567890");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Phone number must begin with 0.", e.getMessage());
		}
	}
	
	
	@Test
	void validatePhoneTest10Digits() {
		try {
			uic.validatePhone("01230");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Phone number must include 10 digits", e.getMessage());
		}
	}
	
	@Test
	void validatePhoneTestOnlyDigits() {
		try {
			uic.validatePhone("0bcdefghij");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Phone number must only include digits.", e.getMessage());
		}
	}

	@Test
	void validateCCNumberTestOnlyDigits() {
		try {
			uic.validateCCNumber("12345678tt");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Credit card number must only include digits.", e.getMessage());
		}
	}
	
	@Test
	void validateCCNumberTestUnder9() {
		try {
			uic.validateCCNumber("123");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Credit card number must contain 9-16 digits.", e.getMessage());
		}
	}
	
	@Test
	void validateCCNumberTestOver16() {
		try {
			uic.validateCCNumber("11111111111111111111");
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Credit card number must contain 9-16 digits.", e.getMessage());
		}
	}
	
	@Test
	void validateEDTestMonthUnder1() {
		try {
			uic.validateED(-2,2022);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal month or year entered.", e.getMessage());
		}
	}
	
	@Test
	void validateEDTestMonthOver12() {
		try {
			uic.validateED(20,2022);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal month or year entered.", e.getMessage());
		}
	}
	
	@Test
	void validateEDTestYearUnder1900() {
		try {
			uic.validateED(2,250);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal month or year entered.", e.getMessage());
		}
	}
	
	@Test
	void validateEDTestCCExpired() {
		try {
			uic.validateED(3,2020);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal expiration date.", e.getMessage());
		}
	}
	
	@Test
	void validateThreeTestOver999() {
		try {
			uic.validateThree(1254);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal three digit input.", e.getMessage());
		}
	}
	
	@Test
	void validateThreeTestUnder100() {
		try {
			uic.validateThree(80);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Illegal three digit input.", e.getMessage());
		}
	}
	
}
