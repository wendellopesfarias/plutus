package com.plutus.model;

import java.util.Map;

public class Person extends Bean {
	
	private String firstName;
	private String lastName;
	private int age;
	private Map<String,String> phones;
	private Map<String,String> addresses;

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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, String> getPhones() {
		return phones;
	}
	public void setPhones(Map<String, String> phones) {
		this.phones = phones;
	}
	public Map<String, String> getAddresses() {
		return addresses;
	}
	public void setAddresses(Map<String, String> addresses) {
		this.addresses = addresses;
	}
	
	
}
