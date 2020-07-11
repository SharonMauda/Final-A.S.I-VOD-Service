package model;

import java.io.Serializable;

public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String Firstname;
	protected String Lastname;
	protected String ID;
	protected int Age = 0;
	protected String Country;
	protected String PhoneNumber;
	protected String Email;
	protected String Password;

	public abstract String getFirstname();

	public abstract void setFirstname(String firstname);

	public abstract String getLastname();

	public abstract void setLastname(String lastname);

	public abstract String getID();

	public abstract void setID(String ID);

	public abstract int getAge();

	public abstract void setAge(int age);

	public abstract String getCountry();

	public abstract void setCountry(String country);

	public abstract String getPhoneNumber();

	public abstract void setPhoneNumber(String phoneNumber);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract String getPassword();

	public abstract void setPassword(String password);

}
