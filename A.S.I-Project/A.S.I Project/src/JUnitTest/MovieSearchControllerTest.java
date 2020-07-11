package JUnitTest;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.MovieSearchController;
import model.Movie;

class MovieSearchControllerTest {

	ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> listfull = new ArrayList<String>();
	static HashMap<String, Movie> movieMap = new HashMap<String, Movie>();
	// static Movie =

	private static MovieSearchController msc;

	@BeforeAll
	public static void setup() {
		msc = new MovieSearchController();
	}

	@Test
	void showAllMoviesTest() {
		Assertions.assertTrue(msc.showAllMovies(list));
	}
}
