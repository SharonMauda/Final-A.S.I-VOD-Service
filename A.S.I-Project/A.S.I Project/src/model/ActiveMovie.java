package model;

import java.io.Serializable;
import java.util.Date;

public class ActiveMovie implements Serializable {

	private static final long serialVersionUID = 1L;
	private Movie newMovie;
	private long orderTime;

	public ActiveMovie(Movie movieObj) {
		this.newMovie = movieObj;
		this.orderTime = new Date().getTime();
	}

	public long getOrderTime() {
		return this.orderTime;
	}

	public Movie getMovie() {
		return this.newMovie;
	}

	public void extendOrderTime() {
		this.orderTime += 86400000;
	}

	public long getRemainingTime() {
		return (48 - ((new Date().getTime() - this.getOrderTime()) / 3600000));
	}

}
