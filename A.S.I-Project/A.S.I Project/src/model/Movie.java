package model;

import java.io.Serializable;

public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Name;
	private String Description;
	private String Genre;
	private int Length;
	private int Year;
	private int Rate = 0;
	private int NumOfRates = 0;
	private int RateSum = 0;
	private int NumOfRents = 0;
	private static int Price = 50;

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getGenre() {
		return this.Genre;
	}

	public void setGenre(String genre) {
		this.Genre = genre;
	}

	public int NumOfRates() {
		return NumOfRates;
	}

	public void setNumOfRates(int numOfRates) {
		NumOfRates = numOfRates;
	}
	
	public int getNumOfRates () {
		return NumOfRates;
	}

	public int getYear() {
		return this.Year;
	}

	public void setYear(int year) {
		this.Year = year;
	}

	public int getRate() {
		return Rate;
	}

	public void setRate(int rate) {
		Rate = rate;
		NumOfRates++;
	}

	public int getNumOfRents() {
		return this.NumOfRents;
	}

	public void incNumOfRents() {
		this.NumOfRents++;
	}

	public int getLength() {
		return this.Length;
	}

	public void setLength(int length) {
		this.Length = length;
	}

	public static int getPrice() {
		return Price;
	}

	public static void setPrice(int price) {
		Price = price;
	}

	public int getRateSum() {
		return this.RateSum;
	}

	public  void setRateSum(int ratesSum) {
		this.RateSum = ratesSum;
	}
}
