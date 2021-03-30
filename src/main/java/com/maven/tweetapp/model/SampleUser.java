package com.maven.tweetapp.model;

import java.util.Date;

public class SampleUser {
	public int id;
	public String firstName;
	public String lastName;
	public String gender;
	public Date dob;
	public String email;
	public String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SampleUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SampleUser(int id, String firstName, String lastName, String gender, Date dob, String email,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}
	public SampleUser(SampleUser s) {
		// TODO Auto-generated constructor stub
		this.id = s.id;
		this.firstName = s.firstName;
		this.lastName = s.lastName;
		this.gender = s.gender;
		this.dob = s.dob;
		this.email = s.email;
		this.password = s.password;
	}
	@Override
	public String toString() {
		return "SampleUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", dob=" + dob + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}
