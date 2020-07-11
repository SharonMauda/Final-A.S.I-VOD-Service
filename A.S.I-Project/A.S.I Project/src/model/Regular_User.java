package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Regular_User extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Credit_Card_Num;
	private int Expiration_Month = 0;
	private int Expiration_Year = 0;
	private int Three_Digits = 0;
	private ArrayList<ActiveMovie> activeMovies = new ArrayList<ActiveMovie>();

	@Override
	public String getFirstname() {
		return Firstname;
	}

	@Override
	public void setFirstname(String firstname) {
		this.Firstname = firstname;
	}

	@Override
	public String getLastname() {
		return this.Lastname;
	}

	@Override
	public void setLastname(String lastname) {
		this.Lastname = lastname;
	}

	@Override
	public String getID() {
		return this.ID;
	}

	@Override
	public void setID(String iD) {
		this.ID = iD;
	}

	@Override
	public String getEmail() {
		return this.Email;
	}

	@Override
	public void setEmail(String email) {
		this.Email = email;
	}

	@Override
	public String getPassword() {
		return this.Password;
	}

	@Override
	public void setPassword(String password) {
		this.Password = password;
	}

	public String getCredit_Card_Num() {
		return this.Credit_Card_Num;
	}

	public void setCredit_Card_Num(String credit_Card_Num) {
		this.Credit_Card_Num = credit_Card_Num;
	}

	public int getThree_digits() {
		return this.Three_Digits;
	}

	public void setThree_digits(int three_digits) {
		this.Three_Digits = three_digits;
	}

	@Override
	public int getAge() {
		return this.Age;
	}

	@Override
	public void setAge(int age) {
		this.Age = age;
	}

	@Override
	public String getCountry() {
		return this.Country;
	}

	@Override
	public void setCountry(String country) {
		this.Country = country;
	}

	@Override
	public String getPhoneNumber() {
		return this.PhoneNumber;
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}

	public ArrayList<ActiveMovie> getUserMovies() {
		return this.activeMovies;
	}

	public String toString() {
		return ("First name: " + this.getFirstname() + "\nLast name: " + this.getLastname() + "\nID: " + this.getID()
				+ "\nEmail: " + this.getEmail() + "\nAge: " + this.getAge() + "\nCountry: " + this.getCountry()
				+ "\nPhone number: " + this.getPhoneNumber());
	}

	public String CCtoString() {
		return ("Credit card number: " + this.getCredit_Card_Num() + "\nExpiration date: " + this.getExpiration_Month()
				+ "/" + this.getExpiration_Year() + "\nThree digits: " + this.getThree_digits());
	}

	public int getExpiration_Month() {
		return this.Expiration_Month;
	}

	public void setExpiration_Month(int expiration_Month) {
		this.Expiration_Month = expiration_Month;
	}

	public int getExpiration_Year() {
		return this.Expiration_Year;
	}

	public void setExpiration_Year(int expiration_Year) {
		this.Expiration_Year = expiration_Year;
	}

}
