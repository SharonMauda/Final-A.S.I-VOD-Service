package JUnitTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.UserRentalController;

class UserRentalControllerTest {
	
private static UserRentalController urc;
	
	@BeforeAll
	public static void setup() {
		urc = new UserRentalController();
	}
	
	@Test
	void rateMovieTestOver5() {
		try {
			urc.rateMovie("movie",8);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Invaild rate number.\n", e.getMessage());
		}
	}

}
