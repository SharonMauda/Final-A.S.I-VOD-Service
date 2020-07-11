package model;

import java.io.Serializable;

public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getFirstname() {
		return this.Firstname;
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

}
