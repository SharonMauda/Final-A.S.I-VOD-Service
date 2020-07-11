package JUnitTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.MovieEditController;

class MovieEditControllerTest {

	private static MovieEditController mec;

	@BeforeAll
	public static void setup() {
		mec = new MovieEditController();
	}

	@Test
	void addMovieTestEmptyName() {
		try {
			mec.addMovie("", "stam", "action", 100, 2020);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Field must not be blank.\n", e.getMessage());
		}

	}

	@Test
	void addMovieTestEmptyDescription() {
		try {
			mec.addMovie("sharon", "", "action", 100, 2020);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Field must not be blank.\n", e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	@Test
	void addMovieTestEmptyGenre() {
		try {
			mec.addMovie("sharon", "stam", "", 100, 2020);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Field must not be blank.\n", e.getMessage());
		}
	}

	@Test
	void validateMovieLengthTest() {
		try {
			mec.validateMovieLength(-8);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Length can't be under 0 minutes.\n", e.getMessage());
		}
	}

	@Test
	void validateMovieYearTestOver() {
		try {
			mec.validateMovieYear(2030);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Year is not valid.\n", e.getMessage());
		}
	}

	@Test
	void validateMovieYearTestUnder() {
		try {
			mec.validateMovieYear(1700);
			fail("Should fail but passed");
		} catch (Exception e) {
			Assertions.assertEquals("Year is not valid.\n", e.getMessage());
		}
	}

}
